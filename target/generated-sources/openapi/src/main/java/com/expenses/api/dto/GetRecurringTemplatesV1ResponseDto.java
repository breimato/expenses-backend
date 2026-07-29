package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.RecurringTemplateV1Dto;
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
 * Get Recurring Templates V1 Response
 */

@Schema(name = "GetRecurringTemplatesV1Response", description = "Get Recurring Templates V1 Response")
@JsonTypeName("GetRecurringTemplatesV1Response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class GetRecurringTemplatesV1ResponseDto {

  @Valid
  private List<@Valid RecurringTemplateV1Dto> recurringTemplates = new ArrayList<>();

  public GetRecurringTemplatesV1ResponseDto recurringTemplates(List<@Valid RecurringTemplateV1Dto> recurringTemplates) {
    this.recurringTemplates = recurringTemplates;
    return this;
  }

  public GetRecurringTemplatesV1ResponseDto addRecurringTemplatesItem(RecurringTemplateV1Dto recurringTemplatesItem) {
    if (this.recurringTemplates == null) {
      this.recurringTemplates = new ArrayList<>();
    }
    this.recurringTemplates.add(recurringTemplatesItem);
    return this;
  }

  /**
   * Get recurringTemplates
   * @return recurringTemplates
   */
  @Valid 
  @Schema(name = "recurringTemplates", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("recurringTemplates")
  public List<@Valid RecurringTemplateV1Dto> getRecurringTemplates() {
    return recurringTemplates;
  }

  public void setRecurringTemplates(List<@Valid RecurringTemplateV1Dto> recurringTemplates) {
    this.recurringTemplates = recurringTemplates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetRecurringTemplatesV1ResponseDto getRecurringTemplatesV1Response = (GetRecurringTemplatesV1ResponseDto) o;
    return Objects.equals(this.recurringTemplates, getRecurringTemplatesV1Response.recurringTemplates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recurringTemplates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetRecurringTemplatesV1ResponseDto {\n");
    sb.append("    recurringTemplates: ").append(toIndentedString(recurringTemplates)).append("\n");
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

    private GetRecurringTemplatesV1ResponseDto instance;

    public Builder() {
      this(new GetRecurringTemplatesV1ResponseDto());
    }

    protected Builder(GetRecurringTemplatesV1ResponseDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(GetRecurringTemplatesV1ResponseDto value) { 
      this.instance.setRecurringTemplates(value.recurringTemplates);
      return this;
    }

    public GetRecurringTemplatesV1ResponseDto.Builder recurringTemplates(List<@Valid RecurringTemplateV1Dto> recurringTemplates) {
      this.instance.recurringTemplates(recurringTemplates);
      return this;
    }
    
    /**
    * returns a built GetRecurringTemplatesV1ResponseDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public GetRecurringTemplatesV1ResponseDto build() {
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
  public static GetRecurringTemplatesV1ResponseDto.Builder builder() {
    return new GetRecurringTemplatesV1ResponseDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public GetRecurringTemplatesV1ResponseDto.Builder toBuilder() {
    GetRecurringTemplatesV1ResponseDto.Builder builder = new GetRecurringTemplatesV1ResponseDto.Builder();
    return builder.copyOf(this);
  }

}

