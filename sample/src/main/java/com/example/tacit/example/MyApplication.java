package com.example.tacit.example;

import android.app.Application;

import com.tacitinnovations.core.sdk.CustomerAppConfig;
import com.tacitinnovations.core.sdk.MGNCustomerInfo;
import com.tacitinnovations.core.sdk.MGNDataExchangeObject;
import com.tacitinnovations.core.sdk.MaeganConfig;
import com.tacitinnovations.core.sdk.MaeganSDK;

public class MyApplication extends Application {

    private MGNCustomerInfo mAdditionalCustomerInfo = new MGNCustomerInfo(
            new MGNDataExchangeObject("10013418419", true),
            new MGNDataExchangeObject("dev1049+digital6@guruse.com", true),
            new MGNDataExchangeObject("Ivan", true),
            new MGNDataExchangeObject("Drago", true),
            new MGNDataExchangeObject("null", false),
            new MGNDataExchangeObject("null", false),
            new MGNDataExchangeObject("21254556688", true),
            null, false);

    @Override
    public void onCreate() {
        super.onCreate();
        MaeganConfig maeganConfig = new MaeganConfig.Builder(getApplicationContext())
                .appConfig(new CustomerAppConfig("prod","1334139","59E15499-54CC-4AA3-962F-0146F266700F"))
                .additionalCustomerInfo(mAdditionalCustomerInfo)
                .build();
        MaeganSDK.initialize(maeganConfig);
    }
}
