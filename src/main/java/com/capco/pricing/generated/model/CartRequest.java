package com.capco.pricing.generated.model;

import java.net.URI;
import java.util.Objects;
import com.capco.pricing.generated.model.CartItem;
import com.capco.pricing.generated.model.Client;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CartRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-21T03:06:14.351091+05:30[Asia/Kolkata]")
public class CartRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private Client client;

  @Valid
  private List<@Valid CartItem> items = new ArrayList<>();

  public CartRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CartRequest(Client client, List<@Valid CartItem> items) {
    this.client = client;
    this.items = items;
  }

  public CartRequest client(Client client) {
    this.client = client;
    return this;
  }

  /**
   * Get client
   * @return client
  */
  @NotNull @Valid 
  @Schema(name = "client", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("client")
  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public CartRequest items(List<@Valid CartItem> items) {
    this.items = items;
    return this;
  }

  public CartRequest addItemsItem(CartItem itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
  */
  @NotNull @Valid 
  @Schema(name = "items", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("items")
  public List<@Valid CartItem> getItems() {
    return items;
  }

  public void setItems(List<@Valid CartItem> items) {
    this.items = items;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartRequest cartRequest = (CartRequest) o;
    return Objects.equals(this.client, cartRequest.client) &&
        Objects.equals(this.items, cartRequest.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(client, items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CartRequest {\n");
    sb.append("    client: ").append(toIndentedString(client)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

