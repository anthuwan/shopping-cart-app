package com.capco.pricing.generated.model;

import java.net.URI;
import java.util.Objects;
import com.capco.pricing.generated.model.ProductType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CartItem
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-21T03:06:14.351091+05:30[Asia/Kolkata]")
public class CartItem implements Serializable {

  private static final long serialVersionUID = 1L;

  private ProductType productType;

  private Integer quantity;

  public CartItem() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CartItem(ProductType productType, Integer quantity) {
    this.productType = productType;
    this.quantity = quantity;
  }

  public CartItem productType(ProductType productType) {
    this.productType = productType;
    return this;
  }

  /**
   * Get productType
   * @return productType
  */
  @NotNull @Valid 
  @Schema(name = "productType", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("productType")
  public ProductType getProductType() {
    return productType;
  }

  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

  public CartItem quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * minimum: 1
   * @return quantity
  */
  @NotNull @Min(1) 
  @Schema(name = "quantity", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("quantity")
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartItem cartItem = (CartItem) o;
    return Objects.equals(this.productType, cartItem.productType) &&
        Objects.equals(this.quantity, cartItem.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productType, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CartItem {\n");
    sb.append("    productType: ").append(toIndentedString(productType)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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
}

