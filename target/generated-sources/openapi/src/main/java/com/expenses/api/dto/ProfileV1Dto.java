package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Profile V1
 */

@Schema(name = "ProfileV1", description = "Profile V1")
@JsonTypeName("ProfileV1")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class ProfileV1Dto {

  private String displayName;

  private String balance;

  public ProfileV1Dto displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * Display name
   * @return displayName
   */
  
  @Schema(name = "displayName", description = "Display name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("displayName")
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public ProfileV1Dto balance(String balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Computed account balance from all recorded movements as decimal string
   * @return balance
   */
  
  @Schema(name = "balance", description = "Computed account balance from all recorded movements as decimal string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("balance")
  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfileV1Dto profileV1 = (ProfileV1Dto) o;
    return Objects.equals(this.displayName, profileV1.displayName) &&
        Objects.equals(this.balance, profileV1.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName, balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProfileV1Dto {\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
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

    private ProfileV1Dto instance;

    public Builder() {
      this(new ProfileV1Dto());
    }

    protected Builder(ProfileV1Dto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProfileV1Dto value) { 
      this.instance.setDisplayName(value.displayName);
      this.instance.setBalance(value.balance);
      return this;
    }

    public ProfileV1Dto.Builder displayName(String displayName) {
      this.instance.displayName(displayName);
      return this;
    }
    
    public ProfileV1Dto.Builder balance(String balance) {
      this.instance.balance(balance);
      return this;
    }
    
    /**
    * returns a built ProfileV1Dto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProfileV1Dto build() {
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
  public static ProfileV1Dto.Builder builder() {
    return new ProfileV1Dto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProfileV1Dto.Builder toBuilder() {
    ProfileV1Dto.Builder builder = new ProfileV1Dto.Builder();
    return builder.copyOf(this);
  }

}

