package com.expenses;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/** The Class Expenses Api Integration Test. */
class ExpensesApiIT extends IntegrationTestSupport {

    @Autowired
    private MockMvc mockMvc;

    /** Test get expenses without filters returns success. */
    @Test
    void getExpensesWithoutFiltersReturnsOk() throws Exception {

        this.mockMvc.perform(get("/v1/expenses/expenses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.expenses").isArray());
    }

    /** Test get categories without movement type filter returns all categories. */
    @Test
    void getCategoriesWithoutMovementTypeReturnsIncomeAndExpense() throws Exception {

        this.mockMvc.perform(get("/v1/expenses/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories.length()").value(greaterThanOrEqualTo(8)));
    }

    /** Test create and delete expense lifecycle. */
    @Test
    void createAndDeleteExpenseLifecycle() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": 1,
                                  "amount": "12.50",
                                  "description": "Integration test expense",
                                  "expenseDate": "2026-07-05",
                                  "movementType": "EXPENSE"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.expense.id").exists())
                .andExpect(jsonPath("$.expense.id").isNumber());
    }

    /** Test profile balance is computed from movements. */
    @Test
    void profileBalanceReflectsMovements() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": 1,
                                  "amount": "100.00",
                                  "description": "Balance test expense",
                                  "expenseDate": "2026-07-05",
                                  "movementType": "EXPENSE"
                                }
                                """))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/v1/expenses/profile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profile.balance").exists());
    }

    /** Test delete category with expenses returns conflict. */
    @Test
    void deleteCategoryInUseReturnsConflict() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "categoryId": 1,
                                  "amount": "5.00",
                                  "description": "Blocks category delete",
                                  "expenseDate": "2026-07-05",
                                  "movementType": "EXPENSE"
                                }
                                """))
                .andExpect(status().isCreated());

        this.mockMvc.perform(delete("/v1/expenses/categories/1"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("EXP-CATEGORY-003"));
    }

    /** Test analytics endpoints respond successfully. */
    @Test
    void analyticsEndpointsReturnOk() throws Exception {

        this.mockMvc.perform(post("/v1/expenses/analytics/averages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "referenceDate": "2026-07-05"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.analyticsAverages").exists());

        this.mockMvc.perform(post("/v1/expenses/analytics/projections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "referenceDate": "2026-07-05"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.analyticsProjections").exists());
    }
}
