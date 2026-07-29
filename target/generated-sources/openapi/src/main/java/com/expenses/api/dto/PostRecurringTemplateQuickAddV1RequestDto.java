package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * Post Recurring Template Quick Add V1 Request
 */

@Schema(name = "PostRecurringTemplateQuickAddV1Request", description = "Post Recurring Template Quick Add V1 Request")
@JsonTypeName("PostRecurringTemplateQuickAddV1Request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class PostRecurringTemplateQuickAddV1RequestDto {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate expenseDate;

  private String amount;

  public PostRecurringTemplateQuickAddV1RequestDto expenseDate(LocalDate expenseDate) {
    this.expenseDate = expenseDate;
    return this;
  }

  /**
   * Override expense date
   * @return expenseDate
   */
  @Valid 
  @Schema(name = "expenseDate", description = "Override expense date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expenseDate")
  public LocalDate getExpenseDate() {
    return expenseDate;
  }

  public void setExpenseDate(LocalDate expenseDate) {
    this.expenseDate = expenseDate;
  }

  public PostRecurringTemplateQuickAddV1RequestDto amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Override expense amount as decimal string
   * @return amount
   */
  
  @Schema(name = "amount", description = "Override expense amount as decimal string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amount")
  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostRecurringTemplateQuickAddV1RequestDto postRecurringTemplateQuickAddV1Request = (PostRecurringTemplateQuickAddV1RequestDto) o;
    return Objects.equals(this.expenseDate, postRecurringTemplateQuickAddV1Request.expenseDate) &&
        Objects.equals(this.amount, postRecurringTemplateQuickAddV1Request.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expenseDate, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostRecurringTemplateQuickAddV1RequestDto {\n");
    sb.append("    expenseDate: ").append(toIndentedString(expenseDate)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

    private PostRecurringTemplateQuickAddV1RequestDto instance;

    public Builder() {
      this(new PostRecurringTemplateQuickAddV1RequestDto());
    }

    protected Builder(PostRecurringTemplateQuickAddV1RequestDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PostRecurringTemplateQuickAddV1RequestDto value) { 
      this.instance.setExpenseDate(value.expenseDate);
      this.instance.setAmount(value.amount);
      return this;
    }

    public PostRecurringTemplateQuickAddV1RequestDto.Builder expenseDate(LocalDate expenseDate) {
      this.instance.expenseDate(expenseDate);
      return this;
    }
    
    public PostRecurringTemplateQuickAddV1RequestDto.Builder amount(String amount) {
      this.instance.amount(amount);
      return this;
    }
    
    /**
    * returns a built PostRecurringTemplateQuickAddV1RequestDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PostRecurringTemplateQuickAddV1RequestDto build() {
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
  public static PostRecurringTemplateQuickAddV1RequestDto.Builder builder() {
    return new PostRecurringTemplateQuickAddV1RequestDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PostRecurringTemplateQuickAddV1RequestDto.Builder toBuilder() {
    PostRecurringTemplateQuickAddV1RequestDto.Builder builder = new PostRecurringTemplateQuickAddV1RequestDto.Builder();
    return builder.copyOf(this);
  }

}

