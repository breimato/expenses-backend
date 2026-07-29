package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
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
 * Analytics Projections V1
 */

@Schema(name = "AnalyticsProjectionsV1", description = "Analytics Projections V1")
@JsonTypeName("AnalyticsProjectionsV1")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class AnalyticsProjectionsV1Dto {

  private String projectedMonthlyExpense;

  private String projectedEndOfMonthBalance;

  private Integer daysRemainingInMonth;

  public AnalyticsProjectionsV1Dto projectedMonthlyExpense(String projectedMonthlyExpense) {
    this.projectedMonthlyExpense = projectedMonthlyExpense;
    return this;
  }

  /**
   * Projected monthly expense as decimal string
   * @return projectedMonthlyExpense
   */
  
  @Schema(name = "projectedMonthlyExpense", description = "Projected monthly expense as decimal string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("projectedMonthlyExpense")
  public String getProjectedMonthlyExpense() {
    return projectedMonthlyExpense;
  }

  public void setProjectedMonthlyExpense(String projectedMonthlyExpense) {
    this.projectedMonthlyExpense = projectedMonthlyExpense;
  }

  public AnalyticsProjectionsV1Dto projectedEndOfMonthBalance(String projectedEndOfMonthBalance) {
    this.projectedEndOfMonthBalance = projectedEndOfMonthBalance;
    return this;
  }

  /**
   * Projected end of month balance as decimal string
   * @return projectedEndOfMonthBalance
   */
  
  @Schema(name = "projectedEndOfMonthBalance", description = "Projected end of month balance as decimal string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("projectedEndOfMonthBalance")
  public String getProjectedEndOfMonthBalance() {
    return projectedEndOfMonthBalance;
  }

  public void setProjectedEndOfMonthBalance(String projectedEndOfMonthBalance) {
    this.projectedEndOfMonthBalance = projectedEndOfMonthBalance;
  }

  public AnalyticsProjectionsV1Dto daysRemainingInMonth(Integer daysRemainingInMonth) {
    this.daysRemainingInMonth = daysRemainingInMonth;
    return this;
  }

  /**
   * Days remaining in the current month
   * @return daysRemainingInMonth
   */
  
  @Schema(name = "daysRemainingInMonth", description = "Days remaining in the current month", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("daysRemainingInMonth")
  public Integer getDaysRemainingInMonth() {
    return daysRemainingInMonth;
  }

  public void setDaysRemainingInMonth(Integer daysRemainingInMonth) {
    this.daysRemainingInMonth = daysRemainingInMonth;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnalyticsProjectionsV1Dto analyticsProjectionsV1 = (AnalyticsProjectionsV1Dto) o;
    return Objects.equals(this.projectedMonthlyExpense, analyticsProjectionsV1.projectedMonthlyExpense) &&
        Objects.equals(this.projectedEndOfMonthBalance, analyticsProjectionsV1.projectedEndOfMonthBalance) &&
        Objects.equals(this.daysRemainingInMonth, analyticsProjectionsV1.daysRemainingInMonth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectedMonthlyExpense, projectedEndOfMonthBalance, daysRemainingInMonth);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnalyticsProjectionsV1Dto {\n");
    sb.append("    projectedMonthlyExpense: ").append(toIndentedString(projectedMonthlyExpense)).append("\n");
    sb.append("    projectedEndOfMonthBalance: ").append(toIndentedString(projectedEndOfMonthBalance)).append("\n");
    sb.append("    daysRemainingInMonth: ").append(toIndentedString(daysRemainingInMonth)).append("\n");
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

    private AnalyticsProjectionsV1Dto instance;

    public Builder() {
      this(new AnalyticsProjectionsV1Dto());
    }

    protected Builder(AnalyticsProjectionsV1Dto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(AnalyticsProjectionsV1Dto value) { 
      this.instance.setProjectedMonthlyExpense(value.projectedMonthlyExpense);
      this.instance.setProjectedEndOfMonthBalance(value.projectedEndOfMonthBalance);
      this.instance.setDaysRemainingInMonth(value.daysRemainingInMonth);
      return this;
    }

    public AnalyticsProjectionsV1Dto.Builder projectedMonthlyExpense(String projectedMonthlyExpense) {
      this.instance.projectedMonthlyExpense(projectedMonthlyExpense);
      return this;
    }
    
    public AnalyticsProjectionsV1Dto.Builder projectedEndOfMonthBalance(String projectedEndOfMonthBalance) {
      this.instance.projectedEndOfMonthBalance(projectedEndOfMonthBalance);
      return this;
    }
    
    public AnalyticsProjectionsV1Dto.Builder daysRemainingInMonth(Integer daysRemainingInMonth) {
      this.instance.daysRemainingInMonth(daysRemainingInMonth);
      return this;
    }
    
    /**
    * returns a built AnalyticsProjectionsV1Dto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public AnalyticsProjectionsV1Dto build() {
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
  public static AnalyticsProjectionsV1Dto.Builder builder() {
    return new AnalyticsProjectionsV1Dto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AnalyticsProjectionsV1Dto.Builder toBuilder() {
    AnalyticsProjectionsV1Dto.Builder builder = new AnalyticsProjectionsV1Dto.Builder();
    return builder.copyOf(this);
  }

}

