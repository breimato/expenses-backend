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
 * Get Analytics Projections V1 Request
 */

@Schema(name = "GetAnalyticsProjectionsV1Request", description = "Get Analytics Projections V1 Request")
@JsonTypeName("GetAnalyticsProjectionsV1Request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class GetAnalyticsProjectionsV1RequestDto {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate referenceDate;

  public GetAnalyticsProjectionsV1RequestDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GetAnalyticsProjectionsV1RequestDto(LocalDate referenceDate) {
    this.referenceDate = referenceDate;
  }

  public GetAnalyticsProjectionsV1RequestDto referenceDate(LocalDate referenceDate) {
    this.referenceDate = referenceDate;
    return this;
  }

  /**
   * Reference date for projections calculation
   * @return referenceDate
   */
  @NotNull @Valid 
  @Schema(name = "referenceDate", description = "Reference date for projections calculation", requiredMode = Schema.RequiredMode.REQUIRED)
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
    GetAnalyticsProjectionsV1RequestDto getAnalyticsProjectionsV1Request = (GetAnalyticsProjectionsV1RequestDto) o;
    return Objects.equals(this.referenceDate, getAnalyticsProjectionsV1Request.referenceDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(referenceDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetAnalyticsProjectionsV1RequestDto {\n");
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

    private GetAnalyticsProjectionsV1RequestDto instance;

    public Builder() {
      this(new GetAnalyticsProjectionsV1RequestDto());
    }

    protected Builder(GetAnalyticsProjectionsV1RequestDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(GetAnalyticsProjectionsV1RequestDto value) { 
      this.instance.setReferenceDate(value.referenceDate);
      return this;
    }

    public GetAnalyticsProjectionsV1RequestDto.Builder referenceDate(LocalDate referenceDate) {
      this.instance.referenceDate(referenceDate);
      return this;
    }
    
    /**
    * returns a built GetAnalyticsProjectionsV1RequestDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public GetAnalyticsProjectionsV1RequestDto build() {
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
  public static GetAnalyticsProjectionsV1RequestDto.Builder builder() {
    return new GetAnalyticsProjectionsV1RequestDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public GetAnalyticsProjectionsV1RequestDto.Builder toBuilder() {
    GetAnalyticsProjectionsV1RequestDto.Builder builder = new GetAnalyticsProjectionsV1RequestDto.Builder();
    return builder.copyOf(this);
  }

}

