package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.MovementTypeV1;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Expense V1
 */

@Schema(name = "ExpenseV1", description = "Expense V1")
@JsonTypeName("ExpenseV1")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class ExpenseV1Dto {

  private Integer id;

  private Integer categoryId;

  private String amount;

  private String description;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate expenseDate;

  private MovementTypeV1 movementType;

  private Boolean offsetsSpendingAverage;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updatedAt;

  public ExpenseV1Dto id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Expense identifier
   * @return id
   */
  
  @Schema(name = "id", description = "Expense identifier", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ExpenseV1Dto categoryId(Integer categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  /**
   * Category identifier
   * @return categoryId
   */
  
  @Schema(name = "categoryId", description = "Category identifier", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("categoryId")
  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public ExpenseV1Dto amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Expense amount as decimal string
   * @return amount
   */
  
  @Schema(name = "amount", description = "Expense amount as decimal string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amount")
  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public ExpenseV1Dto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Expense description
   * @return description
   */
  
  @Schema(name = "description", description = "Expense description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ExpenseV1Dto expenseDate(LocalDate expenseDate) {
    this.expenseDate = expenseDate;
    return this;
  }

  /**
   * Expense date
   * @return expenseDate
   */
  @Valid 
  @Schema(name = "expenseDate", description = "Expense date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expenseDate")
  public LocalDate getExpenseDate() {
    return expenseDate;
  }

  public void setExpenseDate(LocalDate expenseDate) {
    this.expenseDate = expenseDate;
  }

  public ExpenseV1Dto movementType(MovementTypeV1 movementType) {
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

  public ExpenseV1Dto offsetsSpendingAverage(Boolean offsetsSpendingAverage) {
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

  public ExpenseV1Dto createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Creation timestamp
   * @return createdAt
   */
  @Valid 
  @Schema(name = "createdAt", description = "Creation timestamp", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public ExpenseV1Dto updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Last update timestamp
   * @return updatedAt
   */
  @Valid 
  @Schema(name = "updatedAt", description = "Last update timestamp", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("updatedAt")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExpenseV1Dto expenseV1 = (ExpenseV1Dto) o;
    return Objects.equals(this.id, expenseV1.id) &&
        Objects.equals(this.categoryId, expenseV1.categoryId) &&
        Objects.equals(this.amount, expenseV1.amount) &&
        Objects.equals(this.description, expenseV1.description) &&
        Objects.equals(this.expenseDate, expenseV1.expenseDate) &&
        Objects.equals(this.movementType, expenseV1.movementType) &&
        Objects.equals(this.offsetsSpendingAverage, expenseV1.offsetsSpendingAverage) &&
        Objects.equals(this.createdAt, expenseV1.createdAt) &&
        Objects.equals(this.updatedAt, expenseV1.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, categoryId, amount, description, expenseDate, movementType, offsetsSpendingAverage, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExpenseV1Dto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    expenseDate: ").append(toIndentedString(expenseDate)).append("\n");
    sb.append("    movementType: ").append(toIndentedString(movementType)).append("\n");
    sb.append("    offsetsSpendingAverage: ").append(toIndentedString(offsetsSpendingAverage)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
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

    private ExpenseV1Dto instance;

    public Builder() {
      this(new ExpenseV1Dto());
    }

    protected Builder(ExpenseV1Dto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ExpenseV1Dto value) { 
      this.instance.setId(value.id);
      this.instance.setCategoryId(value.categoryId);
      this.instance.setAmount(value.amount);
      this.instance.setDescription(value.description);
      this.instance.setExpenseDate(value.expenseDate);
      this.instance.setMovementType(value.movementType);
      this.instance.setOffsetsSpendingAverage(value.offsetsSpendingAverage);
      this.instance.setCreatedAt(value.createdAt);
      this.instance.setUpdatedAt(value.updatedAt);
      return this;
    }

    public ExpenseV1Dto.Builder id(Integer id) {
      this.instance.id(id);
      return this;
    }
    
    public ExpenseV1Dto.Builder categoryId(Integer categoryId) {
      this.instance.categoryId(categoryId);
      return this;
    }
    
    public ExpenseV1Dto.Builder amount(String amount) {
      this.instance.amount(amount);
      return this;
    }
    
    public ExpenseV1Dto.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    public ExpenseV1Dto.Builder expenseDate(LocalDate expenseDate) {
      this.instance.expenseDate(expenseDate);
      return this;
    }
    
    public ExpenseV1Dto.Builder movementType(MovementTypeV1 movementType) {
      this.instance.movementType(movementType);
      return this;
    }
    
    public ExpenseV1Dto.Builder offsetsSpendingAverage(Boolean offsetsSpendingAverage) {
      this.instance.offsetsSpendingAverage(offsetsSpendingAverage);
      return this;
    }
    
    public ExpenseV1Dto.Builder createdAt(OffsetDateTime createdAt) {
      this.instance.createdAt(createdAt);
      return this;
    }
    
    public ExpenseV1Dto.Builder updatedAt(OffsetDateTime updatedAt) {
      this.instance.updatedAt(updatedAt);
      return this;
    }
    
    /**
    * returns a built ExpenseV1Dto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ExpenseV1Dto build() {
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
  public static ExpenseV1Dto.Builder builder() {
    return new ExpenseV1Dto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ExpenseV1Dto.Builder toBuilder() {
    ExpenseV1Dto.Builder builder = new ExpenseV1Dto.Builder();
    return builder.copyOf(this);
  }

}

