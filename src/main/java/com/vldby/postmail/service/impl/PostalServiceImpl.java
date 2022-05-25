package com.vldby.postmail.service.impl;

import com.vldby.postmail.entity.PostalDelivery;
import com.vldby.postmail.entity.PostalDeliveryStatus;
import com.vldby.postmail.entity.PostalLog;
import com.vldby.postmail.entity.PostalOffice;
import com.vldby.postmail.repository.PostalDeliveryRepo;
import com.vldby.postmail.repository.PostalLogRepo;
import com.vldby.postmail.service.PostalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PostalServiceImpl implements PostalService {

    private final PostalLogRepo postalLogRepo;
    private final PostalDeliveryRepo postalDeliveryRepo;

    public PostalServiceImpl(PostalLogRepo postalLogRepo, PostalDeliveryRepo postalDeliveryRepo) {
        this.postalLogRepo = postalLogRepo;
        this.postalDeliveryRepo = postalDeliveryRepo;
    }

    @Override
    @Transactional
    public PostalLog postageRegister(PostalDelivery postalDelivery, PostalOffice postalOffice) {
        postalDelivery = postalDeliveryRepo.save(postalDelivery);
        PostalLog postalLog = createPostalLog(postalDelivery, postalOffice, PostalDeliveryStatus.REGISTERED);
        return postalLogRepo.save(postalLog);
    }

    @Override
    public List<PostalLog> getMovementHistoryPostalDelivery(PostalDelivery postalDelivery) {
        return postalLogRepo.getPostalLogByPostalDeliveryOrderByCreateTs(postalDelivery);
    }

    @Override
    public PostalLog savePostalLog(PostalDelivery postalDelivery,
                                   PostalOffice postalOffice,
                                   PostalDeliveryStatus postalDeliveryStatus) {
        PostalLog postalLog = createPostalLog(postalDelivery, postalOffice, postalDeliveryStatus);
        return postalLogRepo.save(postalLog);
    }

    private PostalLog createPostalLog(PostalDelivery postalDelivery,
                                      PostalOffice postalOffice,
                                      PostalDeliveryStatus postalDeliveryStatus) {
        PostalLog postalLog = new PostalLog();
        postalLog.setPostalDelivery(postalDelivery);
        postalLog.setPostalOffice(postalOffice);
        postalLog.setPostalDeliveryStatus(postalDeliveryStatus);
        return postalLog;
    }

}
