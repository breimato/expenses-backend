package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Api Error V1
 */

@Schema(name = "ApiErrorV1", description = "Api Error V1")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class ApiErrorV1 {

  private String code;

  private String message;

  public ApiErrorV1 code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Error code
   * @return code
   */
  
  @Schema(name = "code", description = "Error code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ApiErrorV1 message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Error message
   * @return message
   */
  
  @Schema(name = "message", description = "Error message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiErrorV1 apiErrorV1 = (ApiErrorV1) o;
    return Objects.equals(this.code, apiErrorV1.code) &&
        Objects.equals(this.message, apiErrorV1.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiErrorV1 {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

    private ApiErrorV1 instance;

    public Builder() {
      this(new ApiErrorV1());
    }

    protected Builder(ApiErrorV1 instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ApiErrorV1 value) { 
      this.instance.setCode(value.code);
      this.instance.setMessage(value.message);
      return this;
    }

    public ApiErrorV1.Builder code(String code) {
      this.instance.code(code);
      return this;
    }
    
    public ApiErrorV1.Builder message(String message) {
      this.instance.message(message);
      return this;
    }
    
    /**
    * returns a built ApiErrorV1 instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ApiErrorV1 build() {
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
  public static ApiErrorV1.Builder builder() {
    return new ApiErrorV1.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ApiErrorV1.Builder toBuilder() {
    ApiErrorV1.Builder builder = new ApiErrorV1.Builder();
    return builder.copyOf(this);
  }

}

