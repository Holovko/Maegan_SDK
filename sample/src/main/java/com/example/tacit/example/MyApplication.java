package com.example.tacit.example;

import android.app.Application;

import com.tacitinnovations.core.sdk.CustomerAppConfig;
import com.tacitinnovations.core.sdk.MGNAddressInfo;
import com.tacitinnovations.core.sdk.MGNCustomerInfo;
import com.tacitinnovations.core.sdk.MGNDataExchangeObject;
import com.tacitinnovations.core.sdk.MaeganConfig;
import com.tacitinnovations.core.sdk.MaeganSDK;
import java.util.ArrayList;

public class MyApplication extends Application {

    private MGNCustomerInfo mAdditionalCustomerInfo = new MGNCustomerInfo(
        new MGNDataExchangeObject("", true),
        new MGNDataExchangeObject("serena_sym@digicert.com", true),
        new MGNDataExchangeObject("Serena", true), new MGNDataExchangeObject("Laurence", true),
        new MGNDataExchangeObject("", false),
        new MGNDataExchangeObject("", false),
        new MGNDataExchangeObject("9055526614", true),
        new ArrayList<MGNAddressInfo>(), false);


    @Override
    public void onCreate() {
        super.onCreate();
        MaeganConfig maeganConfig = new MaeganConfig.Builder(getApplicationContext()).appConfig(
            new CustomerAppConfig("prod", "9E9F47342E80480DBE6F1EA869B91A40", "guruseSDK"))
            .additionalCustomerInfo(mAdditionalCustomerInfo)
            .build();
        MaeganSDK.initialize(maeganConfig);
    }
}
