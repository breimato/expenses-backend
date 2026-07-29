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
 * Api Error V1
 */

@Schema(name = "ApiError", description = "Api Error V1")
@JsonTypeName("ApiError")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class ApiErrorDto {

  private String code;

  private String message;

  public ApiErrorDto code(String code) {
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

  public ApiErrorDto message(String message) {
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
    ApiErrorDto apiError = (ApiErrorDto) o;
    return Objects.equals(this.code, apiError.code) &&
        Objects.equals(this.message, apiError.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiErrorDto {\n");
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

    private ApiErrorDto instance;

    public Builder() {
      this(new ApiErrorDto());
    }

    protected Builder(ApiErrorDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ApiErrorDto value) { 
      this.instance.setCode(value.code);
      this.instance.setMessage(value.message);
      return this;
    }

    public ApiErrorDto.Builder code(String code) {
      this.instance.code(code);
      return this;
    }
    
    public ApiErrorDto.Builder message(String message) {
      this.instance.message(message);
      return this;
    }
    
    /**
    * returns a built ApiErrorDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ApiErrorDto build() {
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
  public static ApiErrorDto.Builder builder() {
    return new ApiErrorDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ApiErrorDto.Builder toBuilder() {
    ApiErrorDto.Builder builder = new ApiErrorDto.Builder();
    return builder.copyOf(this);
  }

}

