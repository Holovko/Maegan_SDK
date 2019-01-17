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
                new CustomerAppConfig("dev", "eyJ4NXQiOiJaV0ptTVdJeVlqUTFZVE00WldRM05XRTROMkl5WWpjeU5UZzRabVV6TXpBek9HVXlPRGxtTUEiLCJraWQiOiJkMGVjNTE0YTMyYjZmODhjMGFiZDEyYTI4NDA2OTliZGQzZGViYTlkIiwiYWxnIjoiUlMyNTYifQ.eyJhdF9oYXNoIjoiLUZRV3YxSGl5Q3RnMHhFUy1NNEkzZyIsInN1YiI6ImIwYzJjZjY1LTdkZDQtNGU4Ny1iYzllLTdhZWEzMzg5ZjE4NSIsImlzcyI6Imh0dHBzOlwvXC9pZG0tc3RnLnZlbnVldGl6ZS5jb21cL29hdXRoMlwvdG9rZW4iLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ0YWNpdHVzZXIxQGdtYWlsLmNvbSIsImdpdmVuX25hbWUiOiJUZXN0IiwidXVpZCI6ImIwYzJjZjY1LTdkZDQtNGU4Ny1iYzllLTdhZWEzMzg5ZjE4NSIsImFjciI6InVybjptYWNlOmluY29tbW9uOmlhcDpzaWx2ZXIiLCJhdWQiOlsiQVJiWTVyRzk2WlBQWnhxV3l5MmZRd0htQ3VjYSJdLCJhenAiOiJBUmJZNXJHOTZaUFBaeHFXeXkyZlF3SG1DdWNhIiwiZXhwIjoxNTQ1NzgxOTcxLCJmYW1pbHlfbmFtZSI6IlVzZXIiLCJpYXQiOjE1NDUxNzcxNzEsImVtYWlsIjoidGFjaXR1c2VyMUBnbWFpbC5jb20ifQ.JLIvAr_FCCGK0IbMO2RoBb8CI3h4x0yLaFsrTbpMkBP-XvOs3EbUR2lIqZMCuMRv2z9lNAZGfpg5Rf60Eta0u0FKlq0bPOeT2WwaosJ-MpT1zK2CD5lmHnfIzHyPoFXwIke8CasEWPqAp-5-PUYXtdEIRyoCEVd_EZPywX8yCIA", "venutizeSDK"))
            .additionalCustomerInfo(mAdditionalCustomerInfo)
            .build();
        MaeganSDK.initialize(maeganConfig);
    }
}
