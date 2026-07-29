package com.expenses.api.dto;

import java.net.URI;
import java.util.Objects;
import com.expenses.api.dto.ProfileV1Dto;
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
 * Profile V1 Response
 */

@Schema(name = "ProfileV1Response", description = "Profile V1 Response")
@JsonTypeName("ProfileV1Response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-29T10:58:32.576515200+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class ProfileV1ResponseDto {

  private ProfileV1Dto profile;

  public ProfileV1ResponseDto profile(ProfileV1Dto profile) {
    this.profile = profile;
    return this;
  }

  /**
   * Get profile
   * @return profile
   */
  @Valid 
  @Schema(name = "profile", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("profile")
  public ProfileV1Dto getProfile() {
    return profile;
  }

  public void setProfile(ProfileV1Dto profile) {
    this.profile = profile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfileV1ResponseDto profileV1Response = (ProfileV1ResponseDto) o;
    return Objects.equals(this.profile, profileV1Response.profile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(profile);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProfileV1ResponseDto {\n");
    sb.append("    profile: ").append(toIndentedString(profile)).append("\n");
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

    private ProfileV1ResponseDto instance;

    public Builder() {
      this(new ProfileV1ResponseDto());
    }

    protected Builder(ProfileV1ResponseDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProfileV1ResponseDto value) { 
      this.instance.setProfile(value.profile);
      return this;
    }

    public ProfileV1ResponseDto.Builder profile(ProfileV1Dto profile) {
      this.instance.profile(profile);
      return this;
    }
    
    /**
    * returns a built ProfileV1ResponseDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProfileV1ResponseDto build() {
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
  public static ProfileV1ResponseDto.Builder builder() {
    return new ProfileV1ResponseDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProfileV1ResponseDto.Builder toBuilder() {
    ProfileV1ResponseDto.Builder builder = new ProfileV1ResponseDto.Builder();
    return builder.copyOf(this);
  }

}

