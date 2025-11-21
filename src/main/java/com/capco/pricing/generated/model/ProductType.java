package com.capco.pricing.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets ProductType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-21T03:06:14.351091+05:30[Asia/Kolkata]")
public enum ProductType {
  
  HIGH_END_PHONE("HIGH_END_PHONE"),
  
  MID_RANGE_PHONE("MID_RANGE_PHONE"),
  
  LAPTOP("LAPTOP");

  private String value;

  ProductType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ProductType fromValue(String value) {
    for (ProductType b : ProductType.values()) {
      if (b.value.equalsIgnoreCase(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

