package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.CategoryV1Dto;
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
 * Category V1 Response
 */

@Schema(name = "CategoryV1Response", description = "Category V1 Response")
@JsonTypeName("CategoryV1Response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class CategoryV1ResponseDto {

  private CategoryV1Dto category;

  public CategoryV1ResponseDto category(CategoryV1Dto category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
   */
  @Valid 
  @Schema(name = "category", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("category")
  public CategoryV1Dto getCategory() {
    return category;
  }

  public void setCategory(CategoryV1Dto category) {
    this.category = category;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CategoryV1ResponseDto categoryV1Response = (CategoryV1ResponseDto) o;
    return Objects.equals(this.category, categoryV1Response.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CategoryV1ResponseDto {\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
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

    private CategoryV1ResponseDto instance;

    public Builder() {
      this(new CategoryV1ResponseDto());
    }

    protected Builder(CategoryV1ResponseDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CategoryV1ResponseDto value) { 
      this.instance.setCategory(value.category);
      return this;
    }

    public CategoryV1ResponseDto.Builder category(CategoryV1Dto category) {
      this.instance.category(category);
      return this;
    }
    
    /**
    * returns a built CategoryV1ResponseDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CategoryV1ResponseDto build() {
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
  public static CategoryV1ResponseDto.Builder builder() {
    return new CategoryV1ResponseDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CategoryV1ResponseDto.Builder toBuilder() {
    CategoryV1ResponseDto.Builder builder = new CategoryV1ResponseDto.Builder();
    return builder.copyOf(this);
  }

}

