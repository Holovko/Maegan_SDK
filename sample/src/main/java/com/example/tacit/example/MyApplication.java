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
        new MGNDataExchangeObject("serena_df@test.com", true),
        new MGNDataExchangeObject("Serena", true), new MGNDataExchangeObject("Laurence", true),
        new MGNDataExchangeObject("", false),
        new MGNDataExchangeObject("", false),
        new MGNDataExchangeObject("5555555555", true),
        new ArrayList<MGNAddressInfo>(), false);


    @Override
    public void onCreate() {
        super.onCreate();
        MaeganConfig maeganConfig = new MaeganConfig.Builder(getApplicationContext()).appConfig(
            new CustomerAppConfig("prod", "140CAA902719481C94E0D8ED71A94E8E", "guruseSDK"))
            .additionalCustomerInfo(mAdditionalCustomerInfo)
            .build();
        MaeganSDK.initialize(maeganConfig);
    }
}
