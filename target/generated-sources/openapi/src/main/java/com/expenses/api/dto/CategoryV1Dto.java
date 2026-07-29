package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.MovementTypeV1;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Category V1
 */

@Schema(name = "CategoryV1", description = "Category V1")
@JsonTypeName("CategoryV1")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class CategoryV1Dto {

  private Integer id;

  private String name;

  private String color;

  private JsonNullable<String> icon = JsonNullable.<String>undefined();

  private Integer sortOrder;

  private MovementTypeV1 movementType;

  public CategoryV1Dto id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Category identifier
   * @return id
   */
  
  @Schema(name = "id", description = "Category identifier", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CategoryV1Dto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Category name
   * @return name
   */
  
  @Schema(name = "name", description = "Category name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CategoryV1Dto color(String color) {
    this.color = color;
    return this;
  }

  /**
   * Category color in hex format
   * @return color
   */
  
  @Schema(name = "color", description = "Category color in hex format", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("color")
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public CategoryV1Dto icon(String icon) {
    this.icon = JsonNullable.of(icon);
    return this;
  }

  /**
   * Category icon
   * @return icon
   */
  
  @Schema(name = "icon", description = "Category icon", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("icon")
  public JsonNullable<String> getIcon() {
    return icon;
  }

  public void setIcon(JsonNullable<String> icon) {
    this.icon = icon;
  }

  public CategoryV1Dto sortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
    return this;
  }

  /**
   * Display sort order
   * @return sortOrder
   */
  
  @Schema(name = "sortOrder", description = "Display sort order", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sortOrder")
  public Integer getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
  }

  public CategoryV1Dto movementType(MovementTypeV1 movementType) {
    this.movementType = movementType;
    return this;
  }

  /**
   * Get movementType
   * @return movementType
   */
  @Valid 
  @Schema(name = "movementType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("movementType")
  public MovementTypeV1 getMovementType() {
    return movementType;
  }

  public void setMovementType(MovementTypeV1 movementType) {
    this.movementType = movementType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CategoryV1Dto categoryV1 = (CategoryV1Dto) o;
    return Objects.equals(this.id, categoryV1.id) &&
        Objects.equals(this.name, categoryV1.name) &&
        Objects.equals(this.color, categoryV1.color) &&
        equalsNullable(this.icon, categoryV1.icon) &&
        Objects.equals(this.sortOrder, categoryV1.sortOrder) &&
        Objects.equals(this.movementType, categoryV1.movementType);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, color, hashCodeNullable(icon), sortOrder, movementType);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CategoryV1Dto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    icon: ").append(toIndentedString(icon)).append("\n");
    sb.append("    sortOrder: ").append(toIndentedString(sortOrder)).append("\n");
    sb.append("    movementType: ").append(toIndentedString(movementType)).append("\n");
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

    private CategoryV1Dto instance;

    public Builder() {
      this(new CategoryV1Dto());
    }

    protected Builder(CategoryV1Dto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CategoryV1Dto value) { 
      this.instance.setId(value.id);
      this.instance.setName(value.name);
      this.instance.setColor(value.color);
      this.instance.setIcon(value.icon);
      this.instance.setSortOrder(value.sortOrder);
      this.instance.setMovementType(value.movementType);
      return this;
    }

    public CategoryV1Dto.Builder id(Integer id) {
      this.instance.id(id);
      return this;
    }
    
    public CategoryV1Dto.Builder name(String name) {
      this.instance.name(name);
      return this;
    }
    
    public CategoryV1Dto.Builder color(String color) {
      this.instance.color(color);
      return this;
    }
    
    public CategoryV1Dto.Builder icon(String icon) {
      this.instance.icon(icon);
      return this;
    }
    
    public CategoryV1Dto.Builder icon(JsonNullable<String> icon) {
      this.instance.icon = icon;
      return this;
    }
    
    public CategoryV1Dto.Builder sortOrder(Integer sortOrder) {
      this.instance.sortOrder(sortOrder);
      return this;
    }
    
    public CategoryV1Dto.Builder movementType(MovementTypeV1 movementType) {
      this.instance.movementType(movementType);
      return this;
    }
    
    /**
    * returns a built CategoryV1Dto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CategoryV1Dto build() {
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
  public static CategoryV1Dto.Builder builder() {
    return new CategoryV1Dto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CategoryV1Dto.Builder toBuilder() {
    CategoryV1Dto.Builder builder = new CategoryV1Dto.Builder();
    return builder.copyOf(this);
  }

}

