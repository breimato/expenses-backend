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
 * Patch Expense V1 Request
 */

@Schema(name = "PatchExpenseV1Request", description = "Patch Expense V1 Request")
@JsonTypeName("PatchExpenseV1Request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class PatchExpenseV1RequestDto {

  private Integer categoryId;

  private String amount;

  private String description;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate expenseDate;

  private MovementTypeV1 movementType;

  private Boolean offsetsSpendingAverage;

  public PatchExpenseV1RequestDto categoryId(Integer categoryId) {
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

  public PatchExpenseV1RequestDto amount(String amount) {
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

  public PatchExpenseV1RequestDto description(String description) {
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

  public PatchExpenseV1RequestDto expenseDate(LocalDate expenseDate) {
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

  public PatchExpenseV1RequestDto movementType(MovementTypeV1 movementType) {
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

  public PatchExpenseV1RequestDto offsetsSpendingAverage(Boolean offsetsSpendingAverage) {
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
    PatchExpenseV1RequestDto patchExpenseV1Request = (PatchExpenseV1RequestDto) o;
    return Objects.equals(this.categoryId, patchExpenseV1Request.categoryId) &&
        Objects.equals(this.amount, patchExpenseV1Request.amount) &&
        Objects.equals(this.description, patchExpenseV1Request.description) &&
        Objects.equals(this.expenseDate, patchExpenseV1Request.expenseDate) &&
        Objects.equals(this.movementType, patchExpenseV1Request.movementType) &&
        Objects.equals(this.offsetsSpendingAverage, patchExpenseV1Request.offsetsSpendingAverage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(categoryId, amount, description, expenseDate, movementType, offsetsSpendingAverage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PatchExpenseV1RequestDto {\n");
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

    private PatchExpenseV1RequestDto instance;

    public Builder() {
      this(new PatchExpenseV1RequestDto());
    }

    protected Builder(PatchExpenseV1RequestDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PatchExpenseV1RequestDto value) { 
      this.instance.setCategoryId(value.categoryId);
      this.instance.setAmount(value.amount);
      this.instance.setDescription(value.description);
      this.instance.setExpenseDate(value.expenseDate);
      this.instance.setMovementType(value.movementType);
      this.instance.setOffsetsSpendingAverage(value.offsetsSpendingAverage);
      return this;
    }

    public PatchExpenseV1RequestDto.Builder categoryId(Integer categoryId) {
      this.instance.categoryId(categoryId);
      return this;
    }
    
    public PatchExpenseV1RequestDto.Builder amount(String amount) {
      this.instance.amount(amount);
      return this;
    }
    
    public PatchExpenseV1RequestDto.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    public PatchExpenseV1RequestDto.Builder expenseDate(LocalDate expenseDate) {
      this.instance.expenseDate(expenseDate);
      return this;
    }
    
    public PatchExpenseV1RequestDto.Builder movementType(MovementTypeV1 movementType) {
      this.instance.movementType(movementType);
      return this;
    }
    
    public PatchExpenseV1RequestDto.Builder offsetsSpendingAverage(Boolean offsetsSpendingAverage) {
      this.instance.offsetsSpendingAverage(offsetsSpendingAverage);
      return this;
    }
    
    /**
    * returns a built PatchExpenseV1RequestDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PatchExpenseV1RequestDto build() {
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
  public static PatchExpenseV1RequestDto.Builder builder() {
    return new PatchExpenseV1RequestDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PatchExpenseV1RequestDto.Builder toBuilder() {
    PatchExpenseV1RequestDto.Builder builder = new PatchExpenseV1RequestDto.Builder();
    return builder.copyOf(this);
  }

}

