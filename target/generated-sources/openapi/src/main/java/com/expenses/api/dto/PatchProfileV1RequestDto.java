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
 * Patch Profile V1 Request
 */

@Schema(name = "PatchProfileV1Request", description = "Patch Profile V1 Request")
@JsonTypeName("PatchProfileV1Request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class PatchProfileV1RequestDto {

  private String displayName;

  public PatchProfileV1RequestDto displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * User display name
   * @return displayName
   */
  
  @Schema(name = "displayName", description = "User display name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("displayName")
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatchProfileV1RequestDto patchProfileV1Request = (PatchProfileV1RequestDto) o;
    return Objects.equals(this.displayName, patchProfileV1Request.displayName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PatchProfileV1RequestDto {\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
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

    private PatchProfileV1RequestDto instance;

    public Builder() {
      this(new PatchProfileV1RequestDto());
    }

    protected Builder(PatchProfileV1RequestDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PatchProfileV1RequestDto value) { 
      this.instance.setDisplayName(value.displayName);
      return this;
    }

    public PatchProfileV1RequestDto.Builder displayName(String displayName) {
      this.instance.displayName(displayName);
      return this;
    }
    
    /**
    * returns a built PatchProfileV1RequestDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PatchProfileV1RequestDto build() {
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
  public static PatchProfileV1RequestDto.Builder builder() {
    return new PatchProfileV1RequestDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PatchProfileV1RequestDto.Builder toBuilder() {
    PatchProfileV1RequestDto.Builder builder = new PatchProfileV1RequestDto.Builder();
    return builder.copyOf(this);
  }

}

