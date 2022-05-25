package com.vldby.postmail.service;

import com.vldby.postmail.entity.PostalDelivery;
import com.vldby.postmail.entity.PostalDeliveryStatus;
import com.vldby.postmail.entity.PostalLog;
import com.vldby.postmail.entity.PostalOffice;

import java.util.List;

public interface PostalService {
    PostalLog postageRegister(PostalDelivery postalDelivery, PostalOffice postalOffice);
    PostalLog savePostalLog(PostalDelivery postalDelivery, PostalOffice postalOffice, PostalDeliveryStatus postalDeliveryStatus);
    List<PostalLog> getMovementHistoryPostalDelivery(PostalDelivery postalDelivery);
}
