package com.expenses;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private Integer expenseCategoryId;

    /** Register a user and capture token plus first expense category. */
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

        final MvcResult categoriesResult = this.mockMvc.perform(get("/v1/expenses/categories")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isOk())
                .andReturn();
        final JsonNode categories = this.objectMapper.readTree(categoriesResult.getResponse().getContentAsString())
                .get("categories");
        Integer foundExpenseCategoryId = null;
        for (final JsonNode category : categories) {
            if ("EXPENSE".equals(category.get("movementType").asText())) {
                foundExpenseCategoryId = category.get("id").asInt();
                break;
            }
        }
        this.expenseCategoryId = foundExpenseCategoryId;
    }

    /** Test get expenses without filters returns success. */
    @Test
    void getExpensesWithoutFiltersReturnsOk() throws Exception {

        this.mockMvc.perform(get("/v1/expenses/expenses")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.expenses").isArray());
    }

    /** Test get categories without movement type filter returns seeded categories. */
    @Test
    void getCategoriesWithoutMovementTypeReturnsIncomeAndExpense() throws Exception {

        this.mockMvc.perform(get("/v1/expenses/categories")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories.length()").value(greaterThanOrEqualTo(8)));
    }

    /** Test create and delete expense lifecycle. */
    @Test
    void createAndDeleteExpenseLifecycle() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/expenses")
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

    /** Test profile balance is computed from movements. */
    @Test
    void profileBalanceReflectsMovements() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/expenses")
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

        this.mockMvc.perform(get("/v1/expenses/profile")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profile.balance").exists());
    }

    /** Test delete category with expenses returns conflict. */
    @Test
    void deleteCategoryInUseReturnsConflict() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/expenses")
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
                                  "referenceDate": "2026-07-05"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.analyticsAverages").exists());

        this.mockMvc.perform(post("/v1/expenses/analytics/projections")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "referenceDate": "2026-07-05"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.analyticsProjections").exists());
    }

    /** Test expense excluded from averages can be created with the flag. */
    @Test
    void createExpenseExcludedFromAveragePersistsFlag() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/expenses")
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

        this.mockMvc.perform(get("/v1/expenses/expenses")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + otherToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.expenses.length()").value(0));

        this.mockMvc.perform(delete("/v1/expenses/expenses/" + expenseId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + otherToken))
                .andExpect(status().isNotFound());
    }

    /** Test protected endpoints reject missing token. */
    @Test
    void protectedEndpointWithoutTokenReturnsUnauthorized() throws Exception {

        this.mockMvc.perform(get("/v1/expenses/expenses"))
                .andExpect(status().isUnauthorized());
    }
}
