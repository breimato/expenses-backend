package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.AnalyticsProjectionsV1Dto;
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
 * Get Analytics Projections V1 Response
 */

@Schema(name = "GetAnalyticsProjectionsV1Response", description = "Get Analytics Projections V1 Response")
@JsonTypeName("GetAnalyticsProjectionsV1Response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class GetAnalyticsProjectionsV1ResponseDto {

  private AnalyticsProjectionsV1Dto analyticsProjections;

  public GetAnalyticsProjectionsV1ResponseDto analyticsProjections(AnalyticsProjectionsV1Dto analyticsProjections) {
    this.analyticsProjections = analyticsProjections;
    return this;
  }

  /**
   * Get analyticsProjections
   * @return analyticsProjections
   */
  @Valid 
  @Schema(name = "analyticsProjections", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("analyticsProjections")
  public AnalyticsProjectionsV1Dto getAnalyticsProjections() {
    return analyticsProjections;
  }

  public void setAnalyticsProjections(AnalyticsProjectionsV1Dto analyticsProjections) {
    this.analyticsProjections = analyticsProjections;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetAnalyticsProjectionsV1ResponseDto getAnalyticsProjectionsV1Response = (GetAnalyticsProjectionsV1ResponseDto) o;
    return Objects.equals(this.analyticsProjections, getAnalyticsProjectionsV1Response.analyticsProjections);
  }

  @Override
  public int hashCode() {
    return Objects.hash(analyticsProjections);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetAnalyticsProjectionsV1ResponseDto {\n");
    sb.append("    analyticsProjections: ").append(toIndentedString(analyticsProjections)).append("\n");
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

    private GetAnalyticsProjectionsV1ResponseDto instance;

    public Builder() {
      this(new GetAnalyticsProjectionsV1ResponseDto());
    }

    protected Builder(GetAnalyticsProjectionsV1ResponseDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(GetAnalyticsProjectionsV1ResponseDto value) { 
      this.instance.setAnalyticsProjections(value.analyticsProjections);
      return this;
    }

    public GetAnalyticsProjectionsV1ResponseDto.Builder analyticsProjections(AnalyticsProjectionsV1Dto analyticsProjections) {
      this.instance.analyticsProjections(analyticsProjections);
      return this;
    }
    
    /**
    * returns a built GetAnalyticsProjectionsV1ResponseDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public GetAnalyticsProjectionsV1ResponseDto build() {
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
  public static GetAnalyticsProjectionsV1ResponseDto.Builder builder() {
    return new GetAnalyticsProjectionsV1ResponseDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public GetAnalyticsProjectionsV1ResponseDto.Builder toBuilder() {
    GetAnalyticsProjectionsV1ResponseDto.Builder builder = new GetAnalyticsProjectionsV1ResponseDto.Builder();
    return builder.copyOf(this);
  }

}

