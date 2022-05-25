package com.vldby.postmail.entity;

import javax.persistence.*;

@Entity(name = "PostalJournal")
@Table(name = "POSTAL_JOURNAL")
public class PostalLog extends BaseUuidEntity {

    @OneToOne
    @JoinColumn(name = "POSTAL_DELIVERY_ID")
    private PostalDelivery postalDelivery;

    @OneToOne
    @JoinColumn(name = "POSTAL_OFFICE_ID")
    private PostalOffice postalOffice;

    @Enumerated(EnumType.STRING)
    @Column(name = "POSTAL_DELIVERY_STATUS")
    private PostalDeliveryStatus postalDeliveryStatus;

    public PostalDeliveryStatus getPostalDeliveryStatus() {
        return postalDeliveryStatus;
    }

    public void setPostalDeliveryStatus(PostalDeliveryStatus postalDeliveryStatus) {
        this.postalDeliveryStatus = postalDeliveryStatus;
    }

    public PostalOffice getPostalOffice() {
        return postalOffice;
    }

    public void setPostalOffice(PostalOffice postalOffice) {
        this.postalOffice = postalOffice;
    }

    public PostalDelivery getPostalDelivery() {
        return postalDelivery;
    }

    public void setPostalDelivery(PostalDelivery postalDelivery) {
        this.postalDelivery = postalDelivery;
    }
}
