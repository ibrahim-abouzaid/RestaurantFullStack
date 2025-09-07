package com.restaurant.restaurant.Service.messageHandler;

import com.restaurant.restaurant.setting.BundleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class BundleMessageService {
    private ResourceBundleMessageSource messageSource;

    @Autowired
    public BundleMessageService(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessageAr(String key){
        return messageSource.getMessage(key,null,new Locale("ar"));
    }
    public String getMessageEn(String key){
        return messageSource.getMessage(key,null,new Locale("En"));
    }

    public BundleMessage getMessage(String key){

        return new BundleMessage( getMessageEn(key),getMessageAr(key));
    }
    public BundleMessage getMessageInLanguage(String key,Locale  locale){

        return new BundleMessage( getMessageEn(key),getMessageAr(key));
    }
}
