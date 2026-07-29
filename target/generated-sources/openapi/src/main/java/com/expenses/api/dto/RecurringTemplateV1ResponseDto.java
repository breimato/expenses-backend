package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.RecurringTemplateV1Dto;
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
 * Recurring Template V1 Response
 */

@Schema(name = "RecurringTemplateV1Response", description = "Recurring Template V1 Response")
@JsonTypeName("RecurringTemplateV1Response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class RecurringTemplateV1ResponseDto {

  private RecurringTemplateV1Dto recurringTemplate;

  public RecurringTemplateV1ResponseDto recurringTemplate(RecurringTemplateV1Dto recurringTemplate) {
    this.recurringTemplate = recurringTemplate;
    return this;
  }

  /**
   * Get recurringTemplate
   * @return recurringTemplate
   */
  @Valid 
  @Schema(name = "recurringTemplate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("recurringTemplate")
  public RecurringTemplateV1Dto getRecurringTemplate() {
    return recurringTemplate;
  }

  public void setRecurringTemplate(RecurringTemplateV1Dto recurringTemplate) {
    this.recurringTemplate = recurringTemplate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecurringTemplateV1ResponseDto recurringTemplateV1Response = (RecurringTemplateV1ResponseDto) o;
    return Objects.equals(this.recurringTemplate, recurringTemplateV1Response.recurringTemplate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recurringTemplate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecurringTemplateV1ResponseDto {\n");
    sb.append("    recurringTemplate: ").append(toIndentedString(recurringTemplate)).append("\n");
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

    private RecurringTemplateV1ResponseDto instance;

    public Builder() {
      this(new RecurringTemplateV1ResponseDto());
    }

    protected Builder(RecurringTemplateV1ResponseDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(RecurringTemplateV1ResponseDto value) { 
      this.instance.setRecurringTemplate(value.recurringTemplate);
      return this;
    }

    public RecurringTemplateV1ResponseDto.Builder recurringTemplate(RecurringTemplateV1Dto recurringTemplate) {
      this.instance.recurringTemplate(recurringTemplate);
      return this;
    }
    
    /**
    * returns a built RecurringTemplateV1ResponseDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public RecurringTemplateV1ResponseDto build() {
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
  public static RecurringTemplateV1ResponseDto.Builder builder() {
    return new RecurringTemplateV1ResponseDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public RecurringTemplateV1ResponseDto.Builder toBuilder() {
    RecurringTemplateV1ResponseDto.Builder builder = new RecurringTemplateV1ResponseDto.Builder();
    return builder.copyOf(this);
  }

}

