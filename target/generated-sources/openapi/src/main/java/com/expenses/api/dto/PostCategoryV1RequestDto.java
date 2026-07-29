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
 * Post Category V1 Request
 */

@Schema(name = "PostCategoryV1Request", description = "Post Category V1 Request")
@JsonTypeName("PostCategoryV1Request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class PostCategoryV1RequestDto {

  private String name;

  private String color;

  private JsonNullable<String> icon = JsonNullable.<String>undefined();

  private Integer sortOrder;

  private MovementTypeV1 movementType;

  public PostCategoryV1RequestDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PostCategoryV1RequestDto(String name, String color) {
    this.name = name;
    this.color = color;
  }

  public PostCategoryV1RequestDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Category name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", description = "Category name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PostCategoryV1RequestDto color(String color) {
    this.color = color;
    return this;
  }

  /**
   * Category color in hex format
   * @return color
   */
  @NotNull 
  @Schema(name = "color", description = "Category color in hex format", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("color")
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public PostCategoryV1RequestDto icon(String icon) {
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

  public PostCategoryV1RequestDto sortOrder(Integer sortOrder) {
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

  public PostCategoryV1RequestDto movementType(MovementTypeV1 movementType) {
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
    PostCategoryV1RequestDto postCategoryV1Request = (PostCategoryV1RequestDto) o;
    return Objects.equals(this.name, postCategoryV1Request.name) &&
        Objects.equals(this.color, postCategoryV1Request.color) &&
        equalsNullable(this.icon, postCategoryV1Request.icon) &&
        Objects.equals(this.sortOrder, postCategoryV1Request.sortOrder) &&
        Objects.equals(this.movementType, postCategoryV1Request.movementType);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, color, hashCodeNullable(icon), sortOrder, movementType);
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
    sb.append("class PostCategoryV1RequestDto {\n");
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

    private PostCategoryV1RequestDto instance;

    public Builder() {
      this(new PostCategoryV1RequestDto());
    }

    protected Builder(PostCategoryV1RequestDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PostCategoryV1RequestDto value) { 
      this.instance.setName(value.name);
      this.instance.setColor(value.color);
      this.instance.setIcon(value.icon);
      this.instance.setSortOrder(value.sortOrder);
      this.instance.setMovementType(value.movementType);
      return this;
    }

    public PostCategoryV1RequestDto.Builder name(String name) {
      this.instance.name(name);
      return this;
    }
    
    public PostCategoryV1RequestDto.Builder color(String color) {
      this.instance.color(color);
      return this;
    }
    
    public PostCategoryV1RequestDto.Builder icon(String icon) {
      this.instance.icon(icon);
      return this;
    }
    
    public PostCategoryV1RequestDto.Builder icon(JsonNullable<String> icon) {
      this.instance.icon = icon;
      return this;
    }
    
    public PostCategoryV1RequestDto.Builder sortOrder(Integer sortOrder) {
      this.instance.sortOrder(sortOrder);
      return this;
    }
    
    public PostCategoryV1RequestDto.Builder movementType(MovementTypeV1 movementType) {
      this.instance.movementType(movementType);
      return this;
    }
    
    /**
    * returns a built PostCategoryV1RequestDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PostCategoryV1RequestDto build() {
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
  public static PostCategoryV1RequestDto.Builder builder() {
    return new PostCategoryV1RequestDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PostCategoryV1RequestDto.Builder toBuilder() {
    PostCategoryV1RequestDto.Builder builder = new PostCategoryV1RequestDto.Builder();
    return builder.copyOf(this);
  }

}

