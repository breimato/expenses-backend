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
 * Post Recurring Template V1 Request
 */

@Schema(name = "PostRecurringTemplateV1Request", description = "Post Recurring Template V1 Request")
@JsonTypeName("PostRecurringTemplateV1Request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class PostRecurringTemplateV1RequestDto {

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

  public PostRecurringTemplateV1RequestDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PostRecurringTemplateV1RequestDto(String label, String amount, Integer categoryId) {
    this.label = label;
    this.amount = amount;
    this.categoryId = categoryId;
  }

  public PostRecurringTemplateV1RequestDto label(String label) {
    this.label = label;
    return this;
  }

  /**
   * Template label
   * @return label
   */
  @NotNull 
  @Schema(name = "label", description = "Template label", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("label")
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public PostRecurringTemplateV1RequestDto amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Template amount as decimal string
   * @return amount
   */
  @NotNull 
  @Schema(name = "amount", description = "Template amount as decimal string", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public PostRecurringTemplateV1RequestDto categoryId(Integer categoryId) {
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

  public PostRecurringTemplateV1RequestDto sortOrder(Integer sortOrder) {
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

  public PostRecurringTemplateV1RequestDto movementType(MovementTypeV1 movementType) {
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

  public PostRecurringTemplateV1RequestDto offsetsSpendingAverage(Boolean offsetsSpendingAverage) {
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

  public PostRecurringTemplateV1RequestDto frequency(RecurringFrequencyV1 frequency) {
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

  public PostRecurringTemplateV1RequestDto dayOfMonth(Integer dayOfMonth) {
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

  public PostRecurringTemplateV1RequestDto autoApply(Boolean autoApply) {
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

  public PostRecurringTemplateV1RequestDto enabled(Boolean enabled) {
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
    PostRecurringTemplateV1RequestDto postRecurringTemplateV1Request = (PostRecurringTemplateV1RequestDto) o;
    return Objects.equals(this.label, postRecurringTemplateV1Request.label) &&
        Objects.equals(this.amount, postRecurringTemplateV1Request.amount) &&
        Objects.equals(this.categoryId, postRecurringTemplateV1Request.categoryId) &&
        Objects.equals(this.sortOrder, postRecurringTemplateV1Request.sortOrder) &&
        Objects.equals(this.movementType, postRecurringTemplateV1Request.movementType) &&
        Objects.equals(this.offsetsSpendingAverage, postRecurringTemplateV1Request.offsetsSpendingAverage) &&
        Objects.equals(this.frequency, postRecurringTemplateV1Request.frequency) &&
        Objects.equals(this.dayOfMonth, postRecurringTemplateV1Request.dayOfMonth) &&
        Objects.equals(this.autoApply, postRecurringTemplateV1Request.autoApply) &&
        Objects.equals(this.enabled, postRecurringTemplateV1Request.enabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, amount, categoryId, sortOrder, movementType, offsetsSpendingAverage, frequency, dayOfMonth, autoApply, enabled);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostRecurringTemplateV1RequestDto {\n");
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

    private PostRecurringTemplateV1RequestDto instance;

    public Builder() {
      this(new PostRecurringTemplateV1RequestDto());
    }

    protected Builder(PostRecurringTemplateV1RequestDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PostRecurringTemplateV1RequestDto value) { 
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

    public PostRecurringTemplateV1RequestDto.Builder label(String label) {
      this.instance.label(label);
      return this;
    }
    
    public PostRecurringTemplateV1RequestDto.Builder amount(String amount) {
      this.instance.amount(amount);
      return this;
    }
    
    public PostRecurringTemplateV1RequestDto.Builder categoryId(Integer categoryId) {
      this.instance.categoryId(categoryId);
      return this;
    }
    
    public PostRecurringTemplateV1RequestDto.Builder sortOrder(Integer sortOrder) {
      this.instance.sortOrder(sortOrder);
      return this;
    }
    
    public PostRecurringTemplateV1RequestDto.Builder movementType(MovementTypeV1 movementType) {
      this.instance.movementType(movementType);
      return this;
    }
    
    public PostRecurringTemplateV1RequestDto.Builder offsetsSpendingAverage(Boolean offsetsSpendingAverage) {
      this.instance.offsetsSpendingAverage(offsetsSpendingAverage);
      return this;
    }
    
    public PostRecurringTemplateV1RequestDto.Builder frequency(RecurringFrequencyV1 frequency) {
      this.instance.frequency(frequency);
      return this;
    }
    
    public PostRecurringTemplateV1RequestDto.Builder dayOfMonth(Integer dayOfMonth) {
      this.instance.dayOfMonth(dayOfMonth);
      return this;
    }
    
    public PostRecurringTemplateV1RequestDto.Builder autoApply(Boolean autoApply) {
      this.instance.autoApply(autoApply);
      return this;
    }
    
    public PostRecurringTemplateV1RequestDto.Builder enabled(Boolean enabled) {
      this.instance.enabled(enabled);
      return this;
    }
    
    /**
    * returns a built PostRecurringTemplateV1RequestDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PostRecurringTemplateV1RequestDto build() {
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
  public static PostRecurringTemplateV1RequestDto.Builder builder() {
    return new PostRecurringTemplateV1RequestDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PostRecurringTemplateV1RequestDto.Builder toBuilder() {
    PostRecurringTemplateV1RequestDto.Builder builder = new PostRecurringTemplateV1RequestDto.Builder();
    return builder.copyOf(this);
  }

}

