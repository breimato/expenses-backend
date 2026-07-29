package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.CategoryV1Dto;
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
 * Get Categories V1 Response
 */

@Schema(name = "GetCategoriesV1Response", description = "Get Categories V1 Response")
@JsonTypeName("GetCategoriesV1Response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class GetCategoriesV1ResponseDto {

  @Valid
  private List<@Valid CategoryV1Dto> categories = new ArrayList<>();

  public GetCategoriesV1ResponseDto categories(List<@Valid CategoryV1Dto> categories) {
    this.categories = categories;
    return this;
  }

  public GetCategoriesV1ResponseDto addCategoriesItem(CategoryV1Dto categoriesItem) {
    if (this.categories == null) {
      this.categories = new ArrayList<>();
    }
    this.categories.add(categoriesItem);
    return this;
  }

  /**
   * Get categories
   * @return categories
   */
  @Valid 
  @Schema(name = "categories", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("categories")
  public List<@Valid CategoryV1Dto> getCategories() {
    return categories;
  }

  public void setCategories(List<@Valid CategoryV1Dto> categories) {
    this.categories = categories;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetCategoriesV1ResponseDto getCategoriesV1Response = (GetCategoriesV1ResponseDto) o;
    return Objects.equals(this.categories, getCategoriesV1Response.categories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(categories);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetCategoriesV1ResponseDto {\n");
    sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
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

    private GetCategoriesV1ResponseDto instance;

    public Builder() {
      this(new GetCategoriesV1ResponseDto());
    }

    protected Builder(GetCategoriesV1ResponseDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(GetCategoriesV1ResponseDto value) { 
      this.instance.setCategories(value.categories);
      return this;
    }

    public GetCategoriesV1ResponseDto.Builder categories(List<@Valid CategoryV1Dto> categories) {
      this.instance.categories(categories);
      return this;
    }
    
    /**
    * returns a built GetCategoriesV1ResponseDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public GetCategoriesV1ResponseDto build() {
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
  public static GetCategoriesV1ResponseDto.Builder builder() {
    return new GetCategoriesV1ResponseDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public GetCategoriesV1ResponseDto.Builder toBuilder() {
    GetCategoriesV1ResponseDto.Builder builder = new GetCategoriesV1ResponseDto.Builder();
    return builder.copyOf(this);
  }

}

