package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.MovementTypeV1;
import com.expenses.api.dto.RecurringFrequencyV1;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Patch Recurring Template V1 Request
 */

@Schema(name = "PatchRecurringTemplateV1Request", description = "Patch Recurring Template V1 Request")
@JsonTypeName("PatchRecurringTemplateV1Request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class PatchRecurringTemplateV1RequestDto {

  private String label;

  private String amount;

  private Integer categoryId;

  private Integer sortOrder;

  private MovementTypeV1 movementType;

  private Boolean offsetsSpendingAverage;

  private RecurringFrequencyV1 frequency;

  private Integer dayOfMonth;

  private Boolean autoApply;

  private Boolean enabled;

  public PatchRecurringTemplateV1RequestDto label(String label) {
    this.label = label;
    return this;
  }

  /**
   * Template label
   * @return label
   */
  
  @Schema(name = "label", description = "Template label", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("label")
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public PatchRecurringTemplateV1RequestDto amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Template amount as decimal string
   * @return amount
   */
  
  @Schema(name = "amount", description = "Template amount as decimal string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amount")
  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public PatchRecurringTemplateV1RequestDto categoryId(Integer categoryId) {
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

  public PatchRecurringTemplateV1RequestDto sortOrder(Integer sortOrder) {
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

  public PatchRecurringTemplateV1RequestDto movementType(MovementTypeV1 movementType) {
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

  public PatchRecurringTemplateV1RequestDto offsetsSpendingAverage(Boolean offsetsSpendingAverage) {
    this.offsetsSpendingAverage = offsetsSpendingAverage;
    return this;
  }

  /**
   * For income templates: reduces net spending in analytics averages
   * @return offsetsSpendingAverage
   */
  
  @Schema(name = "offsetsSpendingAverage", description = "For income templates: reduces net spending in analytics averages", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("offsetsSpendingAverage")
  public Boolean getOffsetsSpendingAverage() {
    return offsetsSpendingAverage;
  }

  public void setOffsetsSpendingAverage(Boolean offsetsSpendingAverage) {
    this.offsetsSpendingAverage = offsetsSpendingAverage;
  }

  public PatchRecurringTemplateV1RequestDto frequency(RecurringFrequencyV1 frequency) {
    this.frequency = frequency;
    return this;
  }

  /**
   * Get frequency
   * @return frequency
   */
  @Valid 
  @Schema(name = "frequency", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("frequency")
  public RecurringFrequencyV1 getFrequency() {
    return frequency;
  }

  public void setFrequency(RecurringFrequencyV1 frequency) {
    this.frequency = frequency;
  }

  public PatchRecurringTemplateV1RequestDto dayOfMonth(Integer dayOfMonth) {
    this.dayOfMonth = dayOfMonth;
    return this;
  }

  /**
   * Day of month for monthly auto-apply (1-31)
   * @return dayOfMonth
   */
  
  @Schema(name = "dayOfMonth", description = "Day of month for monthly auto-apply (1-31)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dayOfMonth")
  public Integer getDayOfMonth() {
    return dayOfMonth;
  }

  public void setDayOfMonth(Integer dayOfMonth) {
    this.dayOfMonth = dayOfMonth;
  }

  public PatchRecurringTemplateV1RequestDto autoApply(Boolean autoApply) {
    this.autoApply = autoApply;
    return this;
  }

  /**
   * Whether to auto-apply on schedule
   * @return autoApply
   */
  
  @Schema(name = "autoApply", description = "Whether to auto-apply on schedule", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("autoApply")
  public Boolean getAutoApply() {
    return autoApply;
  }

  public void setAutoApply(Boolean autoApply) {
    this.autoApply = autoApply;
  }

  public PatchRecurringTemplateV1RequestDto enabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  /**
   * Whether the template is active
   * @return enabled
   */
  
  @Schema(name = "enabled", description = "Whether the template is active", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("enabled")
  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatchRecurringTemplateV1RequestDto patchRecurringTemplateV1Request = (PatchRecurringTemplateV1RequestDto) o;
    return Objects.equals(this.label, patchRecurringTemplateV1Request.label) &&
        Objects.equals(this.amount, patchRecurringTemplateV1Request.amount) &&
        Objects.equals(this.categoryId, patchRecurringTemplateV1Request.categoryId) &&
        Objects.equals(this.sortOrder, patchRecurringTemplateV1Request.sortOrder) &&
        Objects.equals(this.movementType, patchRecurringTemplateV1Request.movementType) &&
        Objects.equals(this.offsetsSpendingAverage, patchRecurringTemplateV1Request.offsetsSpendingAverage) &&
        Objects.equals(this.frequency, patchRecurringTemplateV1Request.frequency) &&
        Objects.equals(this.dayOfMonth, patchRecurringTemplateV1Request.dayOfMonth) &&
        Objects.equals(this.autoApply, patchRecurringTemplateV1Request.autoApply) &&
        Objects.equals(this.enabled, patchRecurringTemplateV1Request.enabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, amount, categoryId, sortOrder, movementType, offsetsSpendingAverage, frequency, dayOfMonth, autoApply, enabled);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PatchRecurringTemplateV1RequestDto {\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
    sb.append("    sortOrder: ").append(toIndentedString(sortOrder)).append("\n");
    sb.append("    movementType: ").append(toIndentedString(movementType)).append("\n");
    sb.append("    offsetsSpendingAverage: ").append(toIndentedString(offsetsSpendingAverage)).append("\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
    sb.append("    dayOfMonth: ").append(toIndentedString(dayOfMonth)).append("\n");
    sb.append("    autoApply: ").append(toIndentedString(autoApply)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
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

    private PatchRecurringTemplateV1RequestDto instance;

    public Builder() {
      this(new PatchRecurringTemplateV1RequestDto());
    }

    protected Builder(PatchRecurringTemplateV1RequestDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PatchRecurringTemplateV1RequestDto value) { 
      this.instance.setLabel(value.label);
      this.instance.setAmount(value.amount);
      this.instance.setCategoryId(value.categoryId);
      this.instance.setSortOrder(value.sortOrder);
      this.instance.setMovementType(value.movementType);
      this.instance.setOffsetsSpendingAverage(value.offsetsSpendingAverage);
      this.instance.setFrequency(value.frequency);
      this.instance.setDayOfMonth(value.dayOfMonth);
      this.instance.setAutoApply(value.autoApply);
      this.instance.setEnabled(value.enabled);
      return this;
    }

    public PatchRecurringTemplateV1RequestDto.Builder label(String label) {
      this.instance.label(label);
      return this;
    }
    
    public PatchRecurringTemplateV1RequestDto.Builder amount(String amount) {
      this.instance.amount(amount);
      return this;
    }
    
    public PatchRecurringTemplateV1RequestDto.Builder categoryId(Integer categoryId) {
      this.instance.categoryId(categoryId);
      return this;
    }
    
    public PatchRecurringTemplateV1RequestDto.Builder sortOrder(Integer sortOrder) {
      this.instance.sortOrder(sortOrder);
      return this;
    }
    
    public PatchRecurringTemplateV1RequestDto.Builder movementType(MovementTypeV1 movementType) {
      this.instance.movementType(movementType);
      return this;
    }
    
    public PatchRecurringTemplateV1RequestDto.Builder offsetsSpendingAverage(Boolean offsetsSpendingAverage) {
      this.instance.offsetsSpendingAverage(offsetsSpendingAverage);
      return this;
    }
    
    public PatchRecurringTemplateV1RequestDto.Builder frequency(RecurringFrequencyV1 frequency) {
      this.instance.frequency(frequency);
      return this;
    }
    
    public PatchRecurringTemplateV1RequestDto.Builder dayOfMonth(Integer dayOfMonth) {
      this.instance.dayOfMonth(dayOfMonth);
      return this;
    }
    
    public PatchRecurringTemplateV1RequestDto.Builder autoApply(Boolean autoApply) {
      this.instance.autoApply(autoApply);
      return this;
    }
    
    public PatchRecurringTemplateV1RequestDto.Builder enabled(Boolean enabled) {
      this.instance.enabled(enabled);
      return this;
    }
    
    /**
    * returns a built PatchRecurringTemplateV1RequestDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PatchRecurringTemplateV1RequestDto build() {
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
  public static PatchRecurringTemplateV1RequestDto.Builder builder() {
    return new PatchRecurringTemplateV1RequestDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PatchRecurringTemplateV1RequestDto.Builder toBuilder() {
    PatchRecurringTemplateV1RequestDto.Builder builder = new PatchRecurringTemplateV1RequestDto.Builder();
    return builder.copyOf(this);
  }

}

