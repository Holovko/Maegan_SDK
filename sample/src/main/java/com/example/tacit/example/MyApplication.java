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
        new MGNDataExchangeObject("brenda@maegan.co", true),
        new MGNDataExchangeObject("Brenda", true), new MGNDataExchangeObject("Crainic", true),
        new MGNDataExchangeObject("", false),
        new MGNDataExchangeObject("", false),
        new MGNDataExchangeObject("6478967107", true),
        new ArrayList<MGNAddressInfo>(), false);


    @Override
    public void onCreate() {
        super.onCreate();
        MaeganConfig maeganConfig = new MaeganConfig.Builder(getApplicationContext()).appConfig(
            new CustomerAppConfig("prod", "51EDEFC552D14427B0D344A461C834CD", "aramarkCuSDK"))
            .additionalCustomerInfo(mAdditionalCustomerInfo)
            .build();
        MaeganSDK.initialize(maeganConfig);
    }
}
