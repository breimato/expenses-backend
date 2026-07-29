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
 * Analytics Averages V1
 */

@Schema(name = "AnalyticsAveragesV1", description = "Analytics Averages V1")
@JsonTypeName("AnalyticsAveragesV1")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class AnalyticsAveragesV1Dto {

  private String dailyAverage;

  private String weeklyAverage;

  private String monthlyAverage;

  private String yearlyAverage;

  public AnalyticsAveragesV1Dto dailyAverage(String dailyAverage) {
    this.dailyAverage = dailyAverage;
    return this;
  }

  /**
   * Daily average net spending for the current month (from day 1)
   * @return dailyAverage
   */
  
  @Schema(name = "dailyAverage", description = "Daily average net spending for the current month (from day 1)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dailyAverage")
  public String getDailyAverage() {
    return dailyAverage;
  }

  public void setDailyAverage(String dailyAverage) {
    this.dailyAverage = dailyAverage;
  }

  public AnalyticsAveragesV1Dto weeklyAverage(String weeklyAverage) {
    this.weeklyAverage = weeklyAverage;
    return this;
  }

  /**
   * Weekly average expense as decimal string
   * @return weeklyAverage
   */
  
  @Schema(name = "weeklyAverage", description = "Weekly average expense as decimal string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("weeklyAverage")
  public String getWeeklyAverage() {
    return weeklyAverage;
  }

  public void setWeeklyAverage(String weeklyAverage) {
    this.weeklyAverage = weeklyAverage;
  }

  public AnalyticsAveragesV1Dto monthlyAverage(String monthlyAverage) {
    this.monthlyAverage = monthlyAverage;
    return this;
  }

  /**
   * Monthly average expense as decimal string
   * @return monthlyAverage
   */
  
  @Schema(name = "monthlyAverage", description = "Monthly average expense as decimal string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("monthlyAverage")
  public String getMonthlyAverage() {
    return monthlyAverage;
  }

  public void setMonthlyAverage(String monthlyAverage) {
    this.monthlyAverage = monthlyAverage;
  }

  public AnalyticsAveragesV1Dto yearlyAverage(String yearlyAverage) {
    this.yearlyAverage = yearlyAverage;
    return this;
  }

  /**
   * Yearly average expense as decimal string
   * @return yearlyAverage
   */
  
  @Schema(name = "yearlyAverage", description = "Yearly average expense as decimal string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("yearlyAverage")
  public String getYearlyAverage() {
    return yearlyAverage;
  }

  public void setYearlyAverage(String yearlyAverage) {
    this.yearlyAverage = yearlyAverage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnalyticsAveragesV1Dto analyticsAveragesV1 = (AnalyticsAveragesV1Dto) o;
    return Objects.equals(this.dailyAverage, analyticsAveragesV1.dailyAverage) &&
        Objects.equals(this.weeklyAverage, analyticsAveragesV1.weeklyAverage) &&
        Objects.equals(this.monthlyAverage, analyticsAveragesV1.monthlyAverage) &&
        Objects.equals(this.yearlyAverage, analyticsAveragesV1.yearlyAverage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dailyAverage, weeklyAverage, monthlyAverage, yearlyAverage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnalyticsAveragesV1Dto {\n");
    sb.append("    dailyAverage: ").append(toIndentedString(dailyAverage)).append("\n");
    sb.append("    weeklyAverage: ").append(toIndentedString(weeklyAverage)).append("\n");
    sb.append("    monthlyAverage: ").append(toIndentedString(monthlyAverage)).append("\n");
    sb.append("    yearlyAverage: ").append(toIndentedString(yearlyAverage)).append("\n");
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

    private AnalyticsAveragesV1Dto instance;

    public Builder() {
      this(new AnalyticsAveragesV1Dto());
    }

    protected Builder(AnalyticsAveragesV1Dto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(AnalyticsAveragesV1Dto value) { 
      this.instance.setDailyAverage(value.dailyAverage);
      this.instance.setWeeklyAverage(value.weeklyAverage);
      this.instance.setMonthlyAverage(value.monthlyAverage);
      this.instance.setYearlyAverage(value.yearlyAverage);
      return this;
    }

    public AnalyticsAveragesV1Dto.Builder dailyAverage(String dailyAverage) {
      this.instance.dailyAverage(dailyAverage);
      return this;
    }
    
    public AnalyticsAveragesV1Dto.Builder weeklyAverage(String weeklyAverage) {
      this.instance.weeklyAverage(weeklyAverage);
      return this;
    }
    
    public AnalyticsAveragesV1Dto.Builder monthlyAverage(String monthlyAverage) {
      this.instance.monthlyAverage(monthlyAverage);
      return this;
    }
    
    public AnalyticsAveragesV1Dto.Builder yearlyAverage(String yearlyAverage) {
      this.instance.yearlyAverage(yearlyAverage);
      return this;
    }
    
    /**
    * returns a built AnalyticsAveragesV1Dto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public AnalyticsAveragesV1Dto build() {
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
  public static AnalyticsAveragesV1Dto.Builder builder() {
    return new AnalyticsAveragesV1Dto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AnalyticsAveragesV1Dto.Builder toBuilder() {
    AnalyticsAveragesV1Dto.Builder builder = new AnalyticsAveragesV1Dto.Builder();
    return builder.copyOf(this);
  }

}

