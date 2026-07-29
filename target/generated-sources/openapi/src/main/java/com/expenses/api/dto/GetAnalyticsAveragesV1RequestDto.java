package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Get Analytics Averages V1 Request
 */

@Schema(name = "GetAnalyticsAveragesV1Request", description = "Get Analytics Averages V1 Request")
@JsonTypeName("GetAnalyticsAveragesV1Request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class GetAnalyticsAveragesV1RequestDto {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate referenceDate;

  public GetAnalyticsAveragesV1RequestDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GetAnalyticsAveragesV1RequestDto(LocalDate referenceDate) {
    this.referenceDate = referenceDate;
  }

  public GetAnalyticsAveragesV1RequestDto referenceDate(LocalDate referenceDate) {
    this.referenceDate = referenceDate;
    return this;
  }

  /**
   * Reference date for averages calculation
   * @return referenceDate
   */
  @NotNull @Valid 
  @Schema(name = "referenceDate", description = "Reference date for averages calculation", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("referenceDate")
  public LocalDate getReferenceDate() {
    return referenceDate;
  }

  public void setReferenceDate(LocalDate referenceDate) {
    this.referenceDate = referenceDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetAnalyticsAveragesV1RequestDto getAnalyticsAveragesV1Request = (GetAnalyticsAveragesV1RequestDto) o;
    return Objects.equals(this.referenceDate, getAnalyticsAveragesV1Request.referenceDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(referenceDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetAnalyticsAveragesV1RequestDto {\n");
    sb.append("    referenceDate: ").append(toIndentedString(referenceDate)).append("\n");
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

    private GetAnalyticsAveragesV1RequestDto instance;

    public Builder() {
      this(new GetAnalyticsAveragesV1RequestDto());
    }

    protected Builder(GetAnalyticsAveragesV1RequestDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(GetAnalyticsAveragesV1RequestDto value) { 
      this.instance.setReferenceDate(value.referenceDate);
      return this;
    }

    public GetAnalyticsAveragesV1RequestDto.Builder referenceDate(LocalDate referenceDate) {
      this.instance.referenceDate(referenceDate);
      return this;
    }
    
    /**
    * returns a built GetAnalyticsAveragesV1RequestDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public GetAnalyticsAveragesV1RequestDto build() {
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
  public static GetAnalyticsAveragesV1RequestDto.Builder builder() {
    return new GetAnalyticsAveragesV1RequestDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public GetAnalyticsAveragesV1RequestDto.Builder toBuilder() {
    GetAnalyticsAveragesV1RequestDto.Builder builder = new GetAnalyticsAveragesV1RequestDto.Builder();
    return builder.copyOf(this);
  }

}

