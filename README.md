# Installation

1. Add dependency Maegan SDK to build.gradle

```

compile ‘com.tacitinnovations.maegansdk:maegan-sdk:0.9'

```

2. Add repository for ZenDesk dependency 

```

repositories{

    maven { url 'https://zendesk.artifactoryonline.com/zendesk/repo'}

}

```

3. Local Properties file stored assets/settings.properties

4.  AndroidManifest.xml in the <application> add

```

<application …

tools:replace="android:icon,android:label, android:theme />

```

5. If targetSdkVersion in gradle "22" or lower just add required permissions to AndroidManifest, If higher - you need request dangerous permissions before use SDK. See [https://developer.android.com/training/permissions/requesting.html](https://developer.android.com/training/permissions/requesting.html) 

Permissions for SDK required:

    //Normal permissions	

    <uses-permission android:name="android.permission.INTERNET" />

    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

    <uses-permission android:name="android.permission.ACCESS_MOCK_LOCATION" />

    <uses-permission android:name="android.permission.FLASHLIGHT" />

    <uses-permission android:name="android.permission.VIBRATE" />

    <uses-permission android:name="android.permission.BROADCAST_STICKY" /> 

     //Dangerous permissions   

    <uses-permission android:name="android.permission.CAMERA" />

    <uses-permission android:name="android.permission.READ_PHONE_STATE" />

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

# Using

### Example of using

1. Initialize in application class:    

```

 MaeganConfig maeganConfig = new MaeganConfig.Builder(getApplicationContext())

     	    .appConfig(new CustomerAppConfig("dev","1","application ID"))

                .additionalCustomerInfo(mAdditionalCustomerInfo)

                .build();

 MaeganSDK.initialize(maeganConfig);

```

2. Request:

```

MaeganSDK.getInstance().presentRestaurantList(this, CALL_BACK_CODE);

```

  3. Response:

```

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...
        Bundle extras = getIntent().getExtras();
        if(extras !=null && extras.containsKey(MaeganSDK.RESULT_CODE)){
            int callBackCode = extras.getInt(MaeganSDK.REQUEST_CALLBACK);
            int resultCode = extras.getInt(MaeganSDK.RESULT_CODE);
            switch (resultCode){
                case RESULT_OK: {
                    if(callBackCode == CALL_BACK_CODE) {
                        TableOrder order = getIntent().getParcelableExtra(MaeganSDK.RESULT_DATA);
                        Log.d(TAG, "onCreate: "+order);
                    }
                    break;
                }
            }
        }
    }

```

3. See more example in sample folder.

