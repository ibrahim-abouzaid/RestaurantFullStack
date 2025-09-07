package com.restaurant.restaurant.setting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BundleMessage {
    @JsonProperty("message_en")
    private String messageEn;
    @JsonProperty("message_ar")
    private String messageAr;

    public BundleMessage(String messageEn) {
        this.messageEn = messageEn;
    }

    public BundleMessage(String messageEn, String messageAr) {
        System.out.println("Here is the BundleMessage in the DTO");

        this.messageEn = messageEn;
        this.messageAr = messageAr;

    }
}
