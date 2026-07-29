package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.MovementTypeV1;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * Post Expense V1 Request
 */

@Schema(name = "PostExpenseV1Request", description = "Post Expense V1 Request")
@JsonTypeName("PostExpenseV1Request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class PostExpenseV1RequestDto {

  private Integer categoryId;

  private String amount;

  private String description;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate expenseDate;

  private MovementTypeV1 movementType;

  private Boolean offsetsSpendingAverage;

  public PostExpenseV1RequestDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PostExpenseV1RequestDto(Integer categoryId, String amount, String description, LocalDate expenseDate) {
    this.categoryId = categoryId;
    this.amount = amount;
    this.description = description;
    this.expenseDate = expenseDate;
  }

  public PostExpenseV1RequestDto categoryId(Integer categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  /**
   * Category identifier
   * @return categoryId
   */
  @NotNull 
  @Schema(name = "categoryId", description = "Category identifier", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("categoryId")
  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public PostExpenseV1RequestDto amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Expense amount as decimal string
   * @return amount
   */
  @NotNull 
  @Schema(name = "amount", description = "Expense amount as decimal string", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public PostExpenseV1RequestDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Expense description
   * @return description
   */
  @NotNull 
  @Schema(name = "description", description = "Expense description", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PostExpenseV1RequestDto expenseDate(LocalDate expenseDate) {
    this.expenseDate = expenseDate;
    return this;
  }

  /**
   * Expense date
   * @return expenseDate
   */
  @NotNull @Valid 
  @Schema(name = "expenseDate", description = "Expense date", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("expenseDate")
  public LocalDate getExpenseDate() {
    return expenseDate;
  }

  public void setExpenseDate(LocalDate expenseDate) {
    this.expenseDate = expenseDate;
  }

  public PostExpenseV1RequestDto movementType(MovementTypeV1 movementType) {
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

  public PostExpenseV1RequestDto offsetsSpendingAverage(Boolean offsetsSpendingAverage) {
    this.offsetsSpendingAverage = offsetsSpendingAverage;
    return this;
  }

  /**
   * For incomes: reduces net spending in analytics averages
   * @return offsetsSpendingAverage
   */
  
  @Schema(name = "offsetsSpendingAverage", description = "For incomes: reduces net spending in analytics averages", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("offsetsSpendingAverage")
  public Boolean getOffsetsSpendingAverage() {
    return offsetsSpendingAverage;
  }

  public void setOffsetsSpendingAverage(Boolean offsetsSpendingAverage) {
    this.offsetsSpendingAverage = offsetsSpendingAverage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostExpenseV1RequestDto postExpenseV1Request = (PostExpenseV1RequestDto) o;
    return Objects.equals(this.categoryId, postExpenseV1Request.categoryId) &&
        Objects.equals(this.amount, postExpenseV1Request.amount) &&
        Objects.equals(this.description, postExpenseV1Request.description) &&
        Objects.equals(this.expenseDate, postExpenseV1Request.expenseDate) &&
        Objects.equals(this.movementType, postExpenseV1Request.movementType) &&
        Objects.equals(this.offsetsSpendingAverage, postExpenseV1Request.offsetsSpendingAverage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(categoryId, amount, description, expenseDate, movementType, offsetsSpendingAverage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostExpenseV1RequestDto {\n");
    sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    expenseDate: ").append(toIndentedString(expenseDate)).append("\n");
    sb.append("    movementType: ").append(toIndentedString(movementType)).append("\n");
    sb.append("    offsetsSpendingAverage: ").append(toIndentedString(offsetsSpendingAverage)).append("\n");
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

    private PostExpenseV1RequestDto instance;

    public Builder() {
      this(new PostExpenseV1RequestDto());
    }

    protected Builder(PostExpenseV1RequestDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PostExpenseV1RequestDto value) { 
      this.instance.setCategoryId(value.categoryId);
      this.instance.setAmount(value.amount);
      this.instance.setDescription(value.description);
      this.instance.setExpenseDate(value.expenseDate);
      this.instance.setMovementType(value.movementType);
      this.instance.setOffsetsSpendingAverage(value.offsetsSpendingAverage);
      return this;
    }

    public PostExpenseV1RequestDto.Builder categoryId(Integer categoryId) {
      this.instance.categoryId(categoryId);
      return this;
    }
    
    public PostExpenseV1RequestDto.Builder amount(String amount) {
      this.instance.amount(amount);
      return this;
    }
    
    public PostExpenseV1RequestDto.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    public PostExpenseV1RequestDto.Builder expenseDate(LocalDate expenseDate) {
      this.instance.expenseDate(expenseDate);
      return this;
    }
    
    public PostExpenseV1RequestDto.Builder movementType(MovementTypeV1 movementType) {
      this.instance.movementType(movementType);
      return this;
    }
    
    public PostExpenseV1RequestDto.Builder offsetsSpendingAverage(Boolean offsetsSpendingAverage) {
      this.instance.offsetsSpendingAverage(offsetsSpendingAverage);
      return this;
    }
    
    /**
    * returns a built PostExpenseV1RequestDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PostExpenseV1RequestDto build() {
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
  public static PostExpenseV1RequestDto.Builder builder() {
    return new PostExpenseV1RequestDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PostExpenseV1RequestDto.Builder toBuilder() {
    PostExpenseV1RequestDto.Builder builder = new PostExpenseV1RequestDto.Builder();
    return builder.copyOf(this);
  }

}

