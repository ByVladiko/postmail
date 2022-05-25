package com.vldby.postmail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vldby.postmail.entity.PostType;

public class PostalDeliveryRegistrationDto {

    @JsonProperty(required = true)
    private PostType postType;
    @JsonProperty(required = true)
    private String index;
    @JsonProperty(required = true)
    private String address;
    @JsonProperty(required = true)
    private String receiverName;
    @JsonProperty(required = true)
    private String indexOffice;

    public PostalDeliveryRegistrationDto(PostType postType, String index, String address, String receiverName,
                                         String indexOffice) {
        this.postType = postType;
        this.index = index;
        this.address = address;
        this.receiverName = receiverName;
        this.indexOffice = indexOffice;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getIndexOffice() {
        return indexOffice;
    }

    public void setIndexOffice(String indexOffice) {
        this.indexOffice = indexOffice;
    }
}
