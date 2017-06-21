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
            new MGNDataExchangeObject("", true),
            new MGNDataExchangeObject("", true),
            new MGNDataExchangeObject("", true),
            new MGNDataExchangeObject("", false),
            new MGNDataExchangeObject("", false),
            new MGNDataExchangeObject("", true),
            new ArrayList<MGNAddressInfo>(), false);

    @Override
    public void onCreate() {
        super.onCreate();
        MaeganConfig maeganConfig = new MaeganConfig.Builder(getApplicationContext())
                .appConfig(new CustomerAppConfig("dev","1","59E15499-54CC-4AA3-962F-0146F266700F"))
                .additionalCustomerInfo(mAdditionalCustomerInfo)
                .build();
        MaeganSDK.initialize(maeganConfig);
    }
}
