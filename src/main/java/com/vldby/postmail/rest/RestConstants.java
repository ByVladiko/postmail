package com.vldby.postmail.rest;

public interface RestConstants {

    String REQUEST_LOG_FORMAT = "{} request to endpoint '{}'";

    interface Endpoints {
        String POSTAL = "postal";
        String REGISTRATION = "registration";
        String ACCEPTED = "accepted";
        String SENT = "sent";
        String ARRIVED = "arrived";
    }
}