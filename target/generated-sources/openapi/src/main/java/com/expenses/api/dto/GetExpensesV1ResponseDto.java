package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.ExpenseV1Dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Get Expenses V1 Response
 */

@Schema(name = "GetExpensesV1Response", description = "Get Expenses V1 Response")
@JsonTypeName("GetExpensesV1Response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class GetExpensesV1ResponseDto {

  @Valid
  private List<@Valid ExpenseV1Dto> expenses = new ArrayList<>();

  public GetExpensesV1ResponseDto expenses(List<@Valid ExpenseV1Dto> expenses) {
    this.expenses = expenses;
    return this;
  }

  public GetExpensesV1ResponseDto addExpensesItem(ExpenseV1Dto expensesItem) {
    if (this.expenses == null) {
      this.expenses = new ArrayList<>();
    }
    this.expenses.add(expensesItem);
    return this;
  }

  /**
   * Get expenses
   * @return expenses
   */
  @Valid 
  @Schema(name = "expenses", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expenses")
  public List<@Valid ExpenseV1Dto> getExpenses() {
    return expenses;
  }

  public void setExpenses(List<@Valid ExpenseV1Dto> expenses) {
    this.expenses = expenses;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetExpensesV1ResponseDto getExpensesV1Response = (GetExpensesV1ResponseDto) o;
    return Objects.equals(this.expenses, getExpensesV1Response.expenses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expenses);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetExpensesV1ResponseDto {\n");
    sb.append("    expenses: ").append(toIndentedString(expenses)).append("\n");
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

    private GetExpensesV1ResponseDto instance;

    public Builder() {
      this(new GetExpensesV1ResponseDto());
    }

    protected Builder(GetExpensesV1ResponseDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(GetExpensesV1ResponseDto value) { 
      this.instance.setExpenses(value.expenses);
      return this;
    }

    public GetExpensesV1ResponseDto.Builder expenses(List<@Valid ExpenseV1Dto> expenses) {
      this.instance.expenses(expenses);
      return this;
    }
    
    /**
    * returns a built GetExpensesV1ResponseDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public GetExpensesV1ResponseDto build() {
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
  public static GetExpensesV1ResponseDto.Builder builder() {
    return new GetExpensesV1ResponseDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public GetExpensesV1ResponseDto.Builder toBuilder() {
    GetExpensesV1ResponseDto.Builder builder = new GetExpensesV1ResponseDto.Builder();
    return builder.copyOf(this);
  }

}

