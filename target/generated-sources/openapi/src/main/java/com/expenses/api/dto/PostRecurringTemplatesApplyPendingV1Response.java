package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Post Recurring Templates Apply Pending V1 Response
 */

@Schema(name = "PostRecurringTemplatesApplyPendingV1Response", description = "Post Recurring Templates Apply Pending V1 Response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class PostRecurringTemplatesApplyPendingV1Response {

  private Integer appliedCount;

  public PostRecurringTemplatesApplyPendingV1Response() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PostRecurringTemplatesApplyPendingV1Response(Integer appliedCount) {
    this.appliedCount = appliedCount;
  }

  public PostRecurringTemplatesApplyPendingV1Response appliedCount(Integer appliedCount) {
    this.appliedCount = appliedCount;
    return this;
  }

  /**
   * Number of recurring templates applied
   * @return appliedCount
   */
  @NotNull 
  @Schema(name = "appliedCount", description = "Number of recurring templates applied", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("appliedCount")
  public Integer getAppliedCount() {
    return appliedCount;
  }

  public void setAppliedCount(Integer appliedCount) {
    this.appliedCount = appliedCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostRecurringTemplatesApplyPendingV1Response postRecurringTemplatesApplyPendingV1Response = (PostRecurringTemplatesApplyPendingV1Response) o;
    return Objects.equals(this.appliedCount, postRecurringTemplatesApplyPendingV1Response.appliedCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appliedCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostRecurringTemplatesApplyPendingV1Response {\n");
    sb.append("    appliedCount: ").append(toIndentedString(appliedCount)).append("\n");
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

    private PostRecurringTemplatesApplyPendingV1Response instance;

    public Builder() {
      this(new PostRecurringTemplatesApplyPendingV1Response());
    }

    protected Builder(PostRecurringTemplatesApplyPendingV1Response instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PostRecurringTemplatesApplyPendingV1Response value) { 
      this.instance.setAppliedCount(value.appliedCount);
      return this;
    }

    public PostRecurringTemplatesApplyPendingV1Response.Builder appliedCount(Integer appliedCount) {
      this.instance.appliedCount(appliedCount);
      return this;
    }
    
    /**
    * returns a built PostRecurringTemplatesApplyPendingV1Response instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PostRecurringTemplatesApplyPendingV1Response build() {
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
  public static PostRecurringTemplatesApplyPendingV1Response.Builder builder() {
    return new PostRecurringTemplatesApplyPendingV1Response.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PostRecurringTemplatesApplyPendingV1Response.Builder toBuilder() {
    PostRecurringTemplatesApplyPendingV1Response.Builder builder = new PostRecurringTemplatesApplyPendingV1Response.Builder();
    return builder.copyOf(this);
  }

}

