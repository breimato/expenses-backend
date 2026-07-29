package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.AnalyticsAveragesV1Dto;
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
 * Get Analytics Averages V1 Response
 */

@Schema(name = "GetAnalyticsAveragesV1Response", description = "Get Analytics Averages V1 Response")
@JsonTypeName("GetAnalyticsAveragesV1Response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class GetAnalyticsAveragesV1ResponseDto {

  private AnalyticsAveragesV1Dto analyticsAverages;

  public GetAnalyticsAveragesV1ResponseDto analyticsAverages(AnalyticsAveragesV1Dto analyticsAverages) {
    this.analyticsAverages = analyticsAverages;
    return this;
  }

  /**
   * Get analyticsAverages
   * @return analyticsAverages
   */
  @Valid 
  @Schema(name = "analyticsAverages", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("analyticsAverages")
  public AnalyticsAveragesV1Dto getAnalyticsAverages() {
    return analyticsAverages;
  }

  public void setAnalyticsAverages(AnalyticsAveragesV1Dto analyticsAverages) {
    this.analyticsAverages = analyticsAverages;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetAnalyticsAveragesV1ResponseDto getAnalyticsAveragesV1Response = (GetAnalyticsAveragesV1ResponseDto) o;
    return Objects.equals(this.analyticsAverages, getAnalyticsAveragesV1Response.analyticsAverages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(analyticsAverages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetAnalyticsAveragesV1ResponseDto {\n");
    sb.append("    analyticsAverages: ").append(toIndentedString(analyticsAverages)).append("\n");
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

    private GetAnalyticsAveragesV1ResponseDto instance;

    public Builder() {
      this(new GetAnalyticsAveragesV1ResponseDto());
    }

    protected Builder(GetAnalyticsAveragesV1ResponseDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(GetAnalyticsAveragesV1ResponseDto value) { 
      this.instance.setAnalyticsAverages(value.analyticsAverages);
      return this;
    }

    public GetAnalyticsAveragesV1ResponseDto.Builder analyticsAverages(AnalyticsAveragesV1Dto analyticsAverages) {
      this.instance.analyticsAverages(analyticsAverages);
      return this;
    }
    
    /**
    * returns a built GetAnalyticsAveragesV1ResponseDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public GetAnalyticsAveragesV1ResponseDto build() {
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
  public static GetAnalyticsAveragesV1ResponseDto.Builder builder() {
    return new GetAnalyticsAveragesV1ResponseDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public GetAnalyticsAveragesV1ResponseDto.Builder toBuilder() {
    GetAnalyticsAveragesV1ResponseDto.Builder builder = new GetAnalyticsAveragesV1ResponseDto.Builder();
    return builder.copyOf(this);
  }

}

