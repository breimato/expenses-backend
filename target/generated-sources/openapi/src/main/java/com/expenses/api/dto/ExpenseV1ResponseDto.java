package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.ExpenseV1Dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Expense V1 Response
 */

@Schema(name = "ExpenseV1Response", description = "Expense V1 Response")
@JsonTypeName("ExpenseV1Response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class ExpenseV1ResponseDto {

  private ExpenseV1Dto expense;

  public ExpenseV1ResponseDto expense(ExpenseV1Dto expense) {
    this.expense = expense;
    return this;
  }

  /**
   * Get expense
   * @return expense
   */
  @Valid 
  @Schema(name = "expense", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expense")
  public ExpenseV1Dto getExpense() {
    return expense;
  }

  public void setExpense(ExpenseV1Dto expense) {
    this.expense = expense;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExpenseV1ResponseDto expenseV1Response = (ExpenseV1ResponseDto) o;
    return Objects.equals(this.expense, expenseV1Response.expense);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expense);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExpenseV1ResponseDto {\n");
    sb.append("    expense: ").append(toIndentedString(expense)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public static class Builder {

    private ExpenseV1ResponseDto instance;

    public Builder() {
      this(new ExpenseV1ResponseDto());
    }

    protected Builder(ExpenseV1ResponseDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ExpenseV1ResponseDto value) { 
      this.instance.setExpense(value.expense);
      return this;
    }

    public ExpenseV1ResponseDto.Builder expense(ExpenseV1Dto expense) {
      this.instance.expense(expense);
      return this;
    }
    
    /**
    * returns a built ExpenseV1ResponseDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ExpenseV1ResponseDto build() {
      try {
        return this.instance;
      } finally {
        // ensure that this.instance is not reused
        this.instance = null;
      }
    }

    @Override
    public String toString() {
      return getClass() + "=(" + instance + ")";
    }
  }

  /**
  * Create a builder with no initialized field (except for the default values).
  */
  public static ExpenseV1ResponseDto.Builder builder() {
    return new ExpenseV1ResponseDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ExpenseV1ResponseDto.Builder toBuilder() {
    ExpenseV1ResponseDto.Builder builder = new ExpenseV1ResponseDto.Builder();
    return builder.copyOf(this);
  }

}

