package com.vldby.postmail.entity;

import com.vldby.postmail.entity.listener.PostalDeliveryListener;

import javax.persistence.*;

@Entity(name = "PostalDelivery")
@Table(name = "POSTAL_DELIVERY")
@EntityListeners(PostalDeliveryListener.class)
public class PostalDelivery extends BaseUuidEntity {

    @Column(name = "IDENTIFIER")
    private String identifier;

    @Enumerated(EnumType.STRING)
    @Column(name = "POST_TYPE")
    private PostType postType;

    @Column(name = "INDEX")
    private String index;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "RECEIVER_NAME")
    private String receiverName;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
}
