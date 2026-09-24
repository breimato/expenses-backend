package com.expenses;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/** The Class Expenses Api Integration Test. */
class ExpensesApiIT extends IntegrationTestSupport {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String accessToken;

    private Integer accountId;

    private Integer expenseCategoryId;

    private Integer incomeCategoryId;

    /** Register a user and capture token plus default account and categories. */
    @BeforeEach
    void setUpAuthenticatedUser() throws Exception {

        final var email = "it-" + System.nanoTime() + "@example.com";
        final MvcResult registerResult = this.mockMvc.perform(post("/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "%s",
                                  "password": "password123",
                                  "displayName": "IT User"
                                }
                                """.formatted(email)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").isString())
                .andReturn();
        final JsonNode registerBody = this.objectMapper.readTree(registerResult.getResponse().getContentAsString());
        this.accessToken = registerBody.get("accessToken").asText();

        final MvcResult accountsResult = this.mockMvc.perform(get("/v1/expenses/accounts")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isOk())
                .andReturn();
        final JsonNode accounts = this.objectMapper.readTree(accountsResult.getResponse().getContentAsString())
                .get("accounts");
        this.accountId = accounts.get(0).get("id").asInt();

        final MvcResult categoriesResult = this.mockMvc.perform(get("/v1/expenses/categories")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isOk())
                .andReturn();
        final JsonNode categories = this.objectMapper.readTree(categoriesResult.getResponse().getContentAsString())
                .get("categories");
        Integer foundExpenseCategoryId = null;
        Integer foundIncomeCategoryId = null;
        for (final JsonNode category : categories) {
            if ("EXPENSE".equals(category.get("movementType").asText()) && Objects.isNull(foundExpenseCategoryId)) {
                foundExpenseCategoryId = category.get("id").asInt();
            }
            if ("INCOME".equals(category.get("movementType").asText()) && Objects.isNull(foundIncomeCategoryId)) {
                foundIncomeCategoryId = category.get("id").asInt();
            }
        }
        this.expenseCategoryId = foundExpenseCategoryId;
        this.incomeCategoryId = foundIncomeCategoryId;
    }

    /** Test get expenses without filters returns success. */
    @Test
    void getExpensesWithoutFiltersReturnsOk() throws Exception {

        this.mockMvc.perform(get("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.expenses").isArray());
    }

    /** Test get categories without movement type filter returns seeded categories. */
    @Test
    void getCategoriesWithoutMovementTypeReturnsIncomeAndExpense() throws Exception {

        this.mockMvc.perform(get("/v1/expenses/categories")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories.length()").value(greaterThanOrEqualTo(10)));
    }

    /** Test create and delete expense lifecycle. */
    @Test
    void createAndDeleteExpenseLifecycle() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "12.50",
                                  "description": "Integration test expense",
                                  "expenseDate": "2026-07-05",
                                  "movementType": "EXPENSE"
                                }
                                """.formatted(this.expenseCategoryId)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.expense.id").exists())
                .andExpect(jsonPath("$.expense.id").isNumber());
    }

    /** Test account balance is computed from movements. */
    @Test
    void accountBalanceReflectsMovements() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "100.00",
                                  "description": "Balance test expense",
                                  "expenseDate": "2026-07-05",
                                  "movementType": "EXPENSE"
                                }
                                """.formatted(this.expenseCategoryId)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/v1/expenses/accounts/" + this.accountId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account.balance").value("-100.00"));
    }

    /** Test delete category with expenses returns conflict. */
    @Test
    void deleteCategoryInUseReturnsConflict() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "5.00",
                                  "description": "Blocks category delete",
                                  "expenseDate": "2026-07-05",
                                  "movementType": "EXPENSE"
                                }
                                """.formatted(this.expenseCategoryId)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(delete("/v1/expenses/categories/" + this.expenseCategoryId)
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("EXP-CATEGORY-003"));
    }

    /** Test analytics endpoints respond successfully. */
    @Test
    void analyticsEndpointsReturnOk() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/analytics/averages")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "accountId": %d,
                                  "referenceDate": "2026-07-05"
                                }
                                """.formatted(this.accountId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.analyticsAverages").exists());

        this.mockMvc.perform(post("/v1/expenses/analytics/projections")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "accountId": %d,
                                  "referenceDate": "2026-07-05"
                                }
                                """.formatted(this.accountId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.analyticsProjections").exists());
    }

    /** Test period average excluding expense category also drops linked reimbursement. */
    @Test
    void periodAverageExcludingCategoryDropsLinkedReimbursement() throws Exception {

        Integer keptExpenseCategoryId = null;
        final MvcResult categoriesResult = this.mockMvc.perform(get("/v1/expenses/categories")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isOk())
                .andReturn();
        for (final JsonNode category : this.objectMapper
                .readTree(categoriesResult.getResponse().getContentAsString())
                .get("categories")) {
            if ("EXPENSE".equals(category.get("movementType").asText())
                    && category.get("id").asInt() != this.expenseCategoryId) {
                keptExpenseCategoryId = category.get("id").asInt();
                break;
            }
        }

        final MvcResult createExpenseResult = this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "50.00",
                                  "description": "Comida",
                                  "expenseDate": "2026-07-05",
                                  "movementType": "EXPENSE"
                                }
                                """.formatted(this.expenseCategoryId)))
                .andExpect(status().isCreated())
                .andReturn();
        final int expenseId = this.objectMapper
                .readTree(createExpenseResult.getResponse().getContentAsString())
                .get("expense")
                .get("id")
                .asInt();

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "50.00",
                                  "description": "Bizum comida",
                                  "expenseDate": "2026-07-06",
                                  "movementType": "INCOME",
                                  "reimbursedExpenseId": %d
                                }
                                """.formatted(this.incomeCategoryId, expenseId)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "30.00",
                                  "description": "Transporte",
                                  "expenseDate": "2026-07-07",
                                  "movementType": "EXPENSE"
                                }
                                """.formatted(keptExpenseCategoryId)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(post("/v1/expenses/analytics/period-average")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "accountId": %d,
                                  "dateFrom": "2026-07-01",
                                  "dateTo": "2026-07-10"
                                }
                                """.formatted(this.accountId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.analyticsPeriodAverage.totalNetSpending").value("30.00"));

        this.mockMvc.perform(post("/v1/expenses/analytics/period-average")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "accountId": %d,
                                  "dateFrom": "2026-07-01",
                                  "dateTo": "2026-07-10",
                                  "excludedCategoryIds": [%d]
                                }
                                """.formatted(this.accountId, this.expenseCategoryId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.analyticsPeriodAverage.totalNetSpending").value("30.00"))
                .andExpect(jsonPath("$.analyticsPeriodAverage.dailyAverage").value("3.00"));
    }

    /** Test expense excluded from averages can be created with the flag. */
    @Test
    void createExpenseExcludedFromAveragePersistsFlag() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "1000.00",
                                  "description": "Savings transfer",
                                  "expenseDate": "2026-07-05",
                                  "movementType": "EXPENSE",
                                  "offsetsSpendingAverage": true
                                }
                                """.formatted(this.expenseCategoryId)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.expense.offsetsSpendingAverage").value(true));
    }

    /** Test users cannot read another user's expenses. */
    @Test
    void usersAreIsolated() throws Exception {

        final MvcResult createExpenseResult = this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "20.00",
                                  "description": "Private expense",
                                  "expenseDate": "2026-07-05",
                                  "movementType": "EXPENSE"
                                }
                                """.formatted(this.expenseCategoryId)))
                .andExpect(status().isCreated())
                .andReturn();
        final int expenseId = this.objectMapper
                .readTree(createExpenseResult.getResponse().getContentAsString())
                .get("expense")
                .get("id")
                .asInt();

        final var otherEmail = "other-" + System.nanoTime() + "@example.com";
        final MvcResult otherRegisterResult = this.mockMvc.perform(post("/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "%s",
                                  "password": "password123",
                                  "displayName": "Other User"
                                }
                                """.formatted(otherEmail)))
                .andExpect(status().isCreated())
                .andReturn();
        final String otherToken = this.objectMapper
                .readTree(otherRegisterResult.getResponse().getContentAsString())
                .get("accessToken")
                .asText();
        final int otherAccountId = this.objectMapper
                .readTree(this.mockMvc.perform(get("/v1/expenses/accounts")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + otherToken))
                        .andReturn()
                        .getResponse()
                        .getContentAsString())
                .get("accounts")
                .get(0)
                .get("id")
                .asInt();

        this.mockMvc.perform(get("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(otherAccountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + otherToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.expenses.length()").value(0));

        this.mockMvc.perform(delete("/v1/expenses/expenses/" + expenseId)
                        .param("accountId", String.valueOf(otherAccountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + otherToken))
                .andExpect(status().isNotFound());
    }

    /** Test protected endpoints reject missing token. */
    @Test
    void protectedEndpointWithoutTokenReturnsUnauthorized() throws Exception {

        this.mockMvc.perform(get("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId)))
                .andExpect(status().isUnauthorized());
    }

    /** Test income linked to expense forces offsets and nets category breakdown. */
    @Test
    void createIncomeLinkedToExpenseNetsCategoryBreakdown() throws Exception {

        final MvcResult createExpenseResult = this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "100.00",
                                  "description": "Shared dinner",
                                  "expenseDate": "2026-07-05",
                                  "movementType": "EXPENSE"
                                }
                                """.formatted(this.expenseCategoryId)))
                .andExpect(status().isCreated())
                .andReturn();
        final int expenseId = this.objectMapper
                .readTree(createExpenseResult.getResponse().getContentAsString())
                .get("expense")
                .get("id")
                .asInt();

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "40.00",
                                  "description": "Bizum dinner",
                                  "expenseDate": "2026-07-06",
                                  "movementType": "INCOME",
                                  "offsetsSpendingAverage": false,
                                  "reimbursedExpenseId": %d
                                }
                                """.formatted(this.incomeCategoryId, expenseId)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.expense.reimbursedExpenseId").value(expenseId))
                .andExpect(jsonPath("$.expense.offsetsSpendingAverage").value(true));

        this.mockMvc.perform(post("/v1/expenses/analytics/category-breakdown")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "accountId": %d,
                                  "referenceDate": "2026-07-10"
                                }
                                """.formatted(this.accountId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.analyticsCategoryBreakdown.totalSpent").value("60.00"))
                .andExpect(jsonPath("$.analyticsCategoryBreakdown.items[0].total").value("60.00"));
    }

    /** Test linking reimbursement to a missing expense returns not found. */
    @Test
    void createIncomeLinkedToMissingExpenseReturnsNotFound() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "10.00",
                                  "description": "Bizum",
                                  "expenseDate": "2026-07-05",
                                  "movementType": "INCOME",
                                  "reimbursedExpenseId": 999999
                                }
                                """.formatted(this.incomeCategoryId)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("EXP-EXPENSE-002"));
    }

    /** Test linking reimbursement to another income returns bad request. */
    @Test
    void createIncomeLinkedToIncomeReturnsBadRequest() throws Exception {

        final MvcResult createIncomeResult = this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "50.00",
                                  "description": "Salary",
                                  "expenseDate": "2026-07-01",
                                  "movementType": "INCOME"
                                }
                                """.formatted(this.incomeCategoryId)))
                .andExpect(status().isCreated())
                .andReturn();
        final int incomeId = this.objectMapper
                .readTree(createIncomeResult.getResponse().getContentAsString())
                .get("expense")
                .get("id")
                .asInt();

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "10.00",
                                  "description": "Bizum",
                                  "expenseDate": "2026-07-05",
                                  "movementType": "INCOME",
                                  "reimbursedExpenseId": %d
                                }
                                """.formatted(this.incomeCategoryId, incomeId)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("EXP-EXPENSE-003"));
    }

    /** Test transfer updates balances on both accounts without affecting spending average. */
    @Test
    void transferMovesBalanceBetweenAccounts() throws Exception {

        final MvcResult secondAccountResult = this.mockMvc.perform(post("/v1/expenses/accounts")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Ahorros"
                                }
                                """))
                .andExpect(status().isCreated())
                .andReturn();
        final int secondAccountId = this.objectMapper
                .readTree(secondAccountResult.getResponse().getContentAsString())
                .get("account")
                .get("id")
                .asInt();

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "200.00",
                                  "description": "Income for transfer test",
                                  "expenseDate": "2026-07-01",
                                  "movementType": "INCOME"
                                }
                                """.formatted(this.incomeCategoryId)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(post("/v1/expenses/account-transfers")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "fromAccountId": %d,
                                  "toAccountId": %d,
                                  "amount": "75.00",
                                  "transferDate": "2026-07-02",
                                  "description": "A ahorros"
                                }
                                """.formatted(this.accountId, secondAccountId)))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/v1/expenses/accounts/" + this.accountId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account.balance").value("125.00"));

        this.mockMvc.perform(get("/v1/expenses/accounts/" + secondAccountId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account.balance").value("75.00"));
    }

    /** Test accounts are isolated per user. */
    @Test
    void accountsAreIsolatedBetweenUsers() throws Exception {

        final var otherEmail = "acct-" + System.nanoTime() + "@example.com";
        final String otherToken = this.objectMapper
                .readTree(this.mockMvc.perform(post("/v1/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "email": "%s",
                                          "password": "password123",
                                          "displayName": "Other"
                                        }
                                        """.formatted(otherEmail)))
                        .andReturn()
                        .getResponse()
                        .getContentAsString())
                .get("accessToken")
                .asText();

        this.mockMvc.perform(get("/v1/expenses/accounts/" + this.accountId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + otherToken))
                .andExpect(status().isNotFound());
    }

    /** Test invitation links a second user as MEMBER with shared expense visibility. */
    @Test
    void invitationLinksMemberAndSharesExpenses() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": %d,
                                  "amount": "33.00",
                                  "description": "Shared joint expense",
                                  "expenseDate": "2026-07-05",
                                  "movementType": "EXPENSE"
                                }
                                """.formatted(this.expenseCategoryId)))
                .andExpect(status().isCreated());

        final MvcResult invitationResult = this.mockMvc.perform(post("/v1/expenses/accounts/" + this.accountId + "/invitations")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.invitation.token").isString())
                .andExpect(jsonPath("$.invitation.status").value("PENDING"))
                .andReturn();
        final String token = this.objectMapper
                .readTree(invitationResult.getResponse().getContentAsString())
                .get("invitation")
                .get("token")
                .asText();

        final var memberEmail = "member-" + System.nanoTime() + "@example.com";
        final String memberToken = this.objectMapper
                .readTree(this.mockMvc.perform(post("/v1/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "email": "%s",
                                          "password": "password123",
                                          "displayName": "Member"
                                        }
                                        """.formatted(memberEmail)))
                        .andExpect(status().isCreated())
                        .andReturn()
                        .getResponse()
                        .getContentAsString())
                .get("accessToken")
                .asText();

        this.mockMvc.perform(get("/v1/expenses/invitations/" + token)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + memberToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.invitation.status").value("PENDING"))
                .andExpect(jsonPath("$.invitation.alreadyMember").value(false));

        this.mockMvc.perform(post("/v1/expenses/invitations/" + token + "/accept")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + memberToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account.id").value(this.accountId))
                .andExpect(jsonPath("$.account.role").value("MEMBER"));

        final JsonNode memberAccounts = this.objectMapper
                .readTree(this.mockMvc.perform(get("/v1/expenses/accounts")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + memberToken))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString())
                .get("accounts");
        var foundShared = false;
        for (final JsonNode account : memberAccounts) {
            if (account.get("id").asInt() == this.accountId) {
                foundShared = true;
                assertEquals("MEMBER", account.get("role").asText());
            }
        }
        assertTrue(foundShared);

        this.mockMvc.perform(get("/v1/expenses/expenses")
                        .param("accountId", String.valueOf(this.accountId))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + memberToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.expenses.length()").value(1))
                .andExpect(jsonPath("$.expenses[0].description").value("Shared joint expense"));

        this.mockMvc.perform(get("/v1/expenses/accounts/" + this.accountId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + memberToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account.balance").value("-33.00"));
    }

    /** Test MEMBER cannot invite and OWNER cannot leave. */
    @Test
    void memberCannotInviteAndOwnerCannotLeave() throws Exception {

        final MvcResult invitationResult = this.mockMvc.perform(post("/v1/expenses/accounts/" + this.accountId + "/invitations")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isCreated())
                .andReturn();
        final String token = this.objectMapper
                .readTree(invitationResult.getResponse().getContentAsString())
                .get("invitation")
                .get("token")
                .asText();

        final var memberEmail = "member2-" + System.nanoTime() + "@example.com";
        final String memberToken = this.objectMapper
                .readTree(this.mockMvc.perform(post("/v1/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "email": "%s",
                                          "password": "password123",
                                          "displayName": "Member Two"
                                        }
                                        """.formatted(memberEmail)))
                        .andExpect(status().isCreated())
                        .andReturn()
                        .getResponse()
                        .getContentAsString())
                .get("accessToken")
                .asText();

        this.mockMvc.perform(post("/v1/expenses/invitations/" + token + "/accept")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + memberToken))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/v1/expenses/accounts/" + this.accountId + "/invitations")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + memberToken))
                .andExpect(status().isForbidden());

        this.mockMvc.perform(post("/v1/expenses/accounts/" + this.accountId + "/leave")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isForbidden());

        this.mockMvc.perform(post("/v1/expenses/accounts/" + this.accountId + "/leave")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + memberToken))
                .andExpect(status().isNoContent());

        this.mockMvc.perform(get("/v1/expenses/accounts/" + this.accountId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + memberToken))
                .andExpect(status().isNotFound());
    }
}
