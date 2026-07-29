package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.MovementTypeV1;
import com.expenses.api.dto.RecurringFrequencyV1;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Recurring Template V1
 */

@Schema(name = "RecurringTemplateV1", description = "Recurring Template V1")
@JsonTypeName("RecurringTemplateV1")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class RecurringTemplateV1Dto {

  private Integer id;

  private String label;

  private String amount;

  private Integer categoryId;

  private Integer sortOrder;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private JsonNullable<OffsetDateTime> lastUsedAt = JsonNullable.<OffsetDateTime>undefined();

  private MovementTypeV1 movementType;

  private Boolean offsetsSpendingAverage;

  private RecurringFrequencyV1 frequency;

  private JsonNullable<Integer> dayOfMonth = JsonNullable.<Integer>undefined();

  private Boolean autoApply;

  private Boolean enabled;

  public RecurringTemplateV1Dto id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Recurring template identifier
   * @return id
   */
  
  @Schema(name = "id", description = "Recurring template identifier", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public RecurringTemplateV1Dto label(String label) {
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

  public RecurringTemplateV1Dto amount(String amount) {
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

  public RecurringTemplateV1Dto categoryId(Integer categoryId) {
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

  public RecurringTemplateV1Dto sortOrder(Integer sortOrder) {
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

  public RecurringTemplateV1Dto lastUsedAt(OffsetDateTime lastUsedAt) {
    this.lastUsedAt = JsonNullable.of(lastUsedAt);
    return this;
  }

  /**
   * Last used timestamp
   * @return lastUsedAt
   */
  @Valid 
  @Schema(name = "lastUsedAt", description = "Last used timestamp", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastUsedAt")
  public JsonNullable<OffsetDateTime> getLastUsedAt() {
    return lastUsedAt;
  }

  public void setLastUsedAt(JsonNullable<OffsetDateTime> lastUsedAt) {
    this.lastUsedAt = lastUsedAt;
  }

  public RecurringTemplateV1Dto movementType(MovementTypeV1 movementType) {
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

  public RecurringTemplateV1Dto offsetsSpendingAverage(Boolean offsetsSpendingAverage) {
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

  public RecurringTemplateV1Dto frequency(RecurringFrequencyV1 frequency) {
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

  public RecurringTemplateV1Dto dayOfMonth(Integer dayOfMonth) {
    this.dayOfMonth = JsonNullable.of(dayOfMonth);
    return this;
  }

  /**
   * Day of month for monthly auto-apply (1-31)
   * @return dayOfMonth
   */
  
  @Schema(name = "dayOfMonth", description = "Day of month for monthly auto-apply (1-31)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dayOfMonth")
  public JsonNullable<Integer> getDayOfMonth() {
    return dayOfMonth;
  }

  public void setDayOfMonth(JsonNullable<Integer> dayOfMonth) {
    this.dayOfMonth = dayOfMonth;
  }

  public RecurringTemplateV1Dto autoApply(Boolean autoApply) {
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

  public RecurringTemplateV1Dto enabled(Boolean enabled) {
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
    RecurringTemplateV1Dto recurringTemplateV1 = (RecurringTemplateV1Dto) o;
    return Objects.equals(this.id, recurringTemplateV1.id) &&
        Objects.equals(this.label, recurringTemplateV1.label) &&
        Objects.equals(this.amount, recurringTemplateV1.amount) &&
        Objects.equals(this.categoryId, recurringTemplateV1.categoryId) &&
        Objects.equals(this.sortOrder, recurringTemplateV1.sortOrder) &&
        equalsNullable(this.lastUsedAt, recurringTemplateV1.lastUsedAt) &&
        Objects.equals(this.movementType, recurringTemplateV1.movementType) &&
        Objects.equals(this.offsetsSpendingAverage, recurringTemplateV1.offsetsSpendingAverage) &&
        Objects.equals(this.frequency, recurringTemplateV1.frequency) &&
        equalsNullable(this.dayOfMonth, recurringTemplateV1.dayOfMonth) &&
        Objects.equals(this.autoApply, recurringTemplateV1.autoApply) &&
        Objects.equals(this.enabled, recurringTemplateV1.enabled);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, label, amount, categoryId, sortOrder, hashCodeNullable(lastUsedAt), movementType, offsetsSpendingAverage, frequency, hashCodeNullable(dayOfMonth), autoApply, enabled);
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
    sb.append("class RecurringTemplateV1Dto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
    sb.append("    sortOrder: ").append(toIndentedString(sortOrder)).append("\n");
    sb.append("    lastUsedAt: ").append(toIndentedString(lastUsedAt)).append("\n");
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

    private RecurringTemplateV1Dto instance;

    public Builder() {
      this(new RecurringTemplateV1Dto());
    }

    protected Builder(RecurringTemplateV1Dto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(RecurringTemplateV1Dto value) { 
      this.instance.setId(value.id);
      this.instance.setLabel(value.label);
      this.instance.setAmount(value.amount);
      this.instance.setCategoryId(value.categoryId);
      this.instance.setSortOrder(value.sortOrder);
      this.instance.setLastUsedAt(value.lastUsedAt);
      this.instance.setMovementType(value.movementType);
      this.instance.setOffsetsSpendingAverage(value.offsetsSpendingAverage);
      this.instance.setFrequency(value.frequency);
      this.instance.setDayOfMonth(value.dayOfMonth);
      this.instance.setAutoApply(value.autoApply);
      this.instance.setEnabled(value.enabled);
      return this;
    }

    public RecurringTemplateV1Dto.Builder id(Integer id) {
      this.instance.id(id);
      return this;
    }
    
    public RecurringTemplateV1Dto.Builder label(String label) {
      this.instance.label(label);
      return this;
    }
    
    public RecurringTemplateV1Dto.Builder amount(String amount) {
      this.instance.amount(amount);
      return this;
    }
    
    public RecurringTemplateV1Dto.Builder categoryId(Integer categoryId) {
      this.instance.categoryId(categoryId);
      return this;
    }
    
    public RecurringTemplateV1Dto.Builder sortOrder(Integer sortOrder) {
      this.instance.sortOrder(sortOrder);
      return this;
    }
    
    public RecurringTemplateV1Dto.Builder lastUsedAt(OffsetDateTime lastUsedAt) {
      this.instance.lastUsedAt(lastUsedAt);
      return this;
    }
    
    public RecurringTemplateV1Dto.Builder lastUsedAt(JsonNullable<OffsetDateTime> lastUsedAt) {
      this.instance.lastUsedAt = lastUsedAt;
      return this;
    }
    
    public RecurringTemplateV1Dto.Builder movementType(MovementTypeV1 movementType) {
      this.instance.movementType(movementType);
      return this;
    }
    
    public RecurringTemplateV1Dto.Builder offsetsSpendingAverage(Boolean offsetsSpendingAverage) {
      this.instance.offsetsSpendingAverage(offsetsSpendingAverage);
      return this;
    }
    
    public RecurringTemplateV1Dto.Builder frequency(RecurringFrequencyV1 frequency) {
      this.instance.frequency(frequency);
      return this;
    }
    
    public RecurringTemplateV1Dto.Builder dayOfMonth(Integer dayOfMonth) {
      this.instance.dayOfMonth(dayOfMonth);
      return this;
    }
    
    public RecurringTemplateV1Dto.Builder dayOfMonth(JsonNullable<Integer> dayOfMonth) {
      this.instance.dayOfMonth = dayOfMonth;
      return this;
    }
    
    public RecurringTemplateV1Dto.Builder autoApply(Boolean autoApply) {
      this.instance.autoApply(autoApply);
      return this;
    }
    
    public RecurringTemplateV1Dto.Builder enabled(Boolean enabled) {
      this.instance.enabled(enabled);
      return this;
    }
    
    /**
    * returns a built RecurringTemplateV1Dto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public RecurringTemplateV1Dto build() {
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
  public static RecurringTemplateV1Dto.Builder builder() {
    return new RecurringTemplateV1Dto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public RecurringTemplateV1Dto.Builder toBuilder() {
    RecurringTemplateV1Dto.Builder builder = new RecurringTemplateV1Dto.Builder();
    return builder.copyOf(this);
  }

}

