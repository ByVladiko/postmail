package com.vldby.postmail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryRequestDto {

    @JsonProperty(required = true)
    private String postalDeliveryIdentifier;
    @JsonProperty(required = true)
    private String postalOfficeIndex;

    public String getPostalDeliveryIdentifier() {
        return postalDeliveryIdentifier;
    }

    public void setPostalDeliveryIdentifier(String postalDeliveryIdentifier) {
        this.postalDeliveryIdentifier = postalDeliveryIdentifier;
    }

    public String getPostalOfficeIndex() {
        return postalOfficeIndex;
    }

    public void setPostalOfficeIndex(String postalOfficeIndex) {
        this.postalOfficeIndex = postalOfficeIndex;
    }
}
