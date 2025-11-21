package com.capco.pricing.generated.model;

import java.net.URI;
import java.util.Objects;
import com.capco.pricing.generated.model.ClientType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Client information (individual or professional).
 */

@Schema(name = "Client", description = "Client information (individual or professional).")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-21T03:06:14.351091+05:30[Asia/Kolkata]")
public class Client implements Serializable {

  private static final long serialVersionUID = 1L;

  private ClientType type;

  private String clientId;

  private String firstName;

  private String lastName;

  private String companyName;

  private JsonNullable<String> vatNumber = JsonNullable.<String>undefined();

  private String registrationNumber;

  private Double annualRevenue;

  public Client() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Client(ClientType type, String clientId) {
    this.type = type;
    this.clientId = clientId;
  }

  public Client type(ClientType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @NotNull @Valid 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public ClientType getType() {
    return type;
  }

  public void setType(ClientType type) {
    this.type = type;
  }

  public Client clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  /**
   * Get clientId
   * @return clientId
  */
  @NotNull 
  @Schema(name = "clientId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("clientId")
  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public Client firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Only for INDIVIDUAL
   * @return firstName
  */
  
  @Schema(name = "firstName", description = "Only for INDIVIDUAL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firstName")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Client lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Only for INDIVIDUAL
   * @return lastName
  */
  
  @Schema(name = "lastName", description = "Only for INDIVIDUAL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastName")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Client companyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  /**
   * Only for PROFESSIONAL
   * @return companyName
  */
  
  @Schema(name = "companyName", description = "Only for PROFESSIONAL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("companyName")
  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Client vatNumber(String vatNumber) {
    this.vatNumber = JsonNullable.of(vatNumber);
    return this;
  }

  /**
   * Optional, only for PROFESSIONAL
   * @return vatNumber
  */
  
  @Schema(name = "vatNumber", description = "Optional, only for PROFESSIONAL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("vatNumber")
  public JsonNullable<String> getVatNumber() {
    return vatNumber;
  }

  public void setVatNumber(JsonNullable<String> vatNumber) {
    this.vatNumber = vatNumber;
  }

  public Client registrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
    return this;
  }

  /**
   * Only for PROFESSIONAL
   * @return registrationNumber
  */
  
  @Schema(name = "registrationNumber", description = "Only for PROFESSIONAL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("registrationNumber")
  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public Client annualRevenue(Double annualRevenue) {
    this.annualRevenue = annualRevenue;
    return this;
  }

  /**
   * Only for PROFESSIONAL
   * @return annualRevenue
  */
  
  @Schema(name = "annualRevenue", description = "Only for PROFESSIONAL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("annualRevenue")
  public Double getAnnualRevenue() {
    return annualRevenue;
  }

  public void setAnnualRevenue(Double annualRevenue) {
    this.annualRevenue = annualRevenue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Client client = (Client) o;
    return Objects.equals(this.type, client.type) &&
        Objects.equals(this.clientId, client.clientId) &&
        Objects.equals(this.firstName, client.firstName) &&
        Objects.equals(this.lastName, client.lastName) &&
        Objects.equals(this.companyName, client.companyName) &&
        equalsNullable(this.vatNumber, client.vatNumber) &&
        Objects.equals(this.registrationNumber, client.registrationNumber) &&
        Objects.equals(this.annualRevenue, client.annualRevenue);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, clientId, firstName, lastName, companyName, hashCodeNullable(vatNumber), registrationNumber, annualRevenue);
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
    sb.append("class Client {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    companyName: ").append(toIndentedString(companyName)).append("\n");
    sb.append("    vatNumber: ").append(toIndentedString(vatNumber)).append("\n");
    sb.append("    registrationNumber: ").append(toIndentedString(registrationNumber)).append("\n");
    sb.append("    annualRevenue: ").append(toIndentedString(annualRevenue)).append("\n");
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

