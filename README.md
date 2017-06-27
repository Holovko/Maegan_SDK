# Installation

1. Add dependency Maegan SDK to build.gradle

```groovy
dependencies {
    compile ‘com.tacitinnovations.maegansdk:maegan-sdk:0.9'
}
```

2. Add repository for ZenDesk dependency 

```groovy
repositories{

    maven { url 'https://zendesk.artifactoryonline.com/zendesk/repo'}

}
```

3. Add local Properties file stored assets/settings.properties
```
ManageCustomerProfile = NO
ManagePaymentInstrument = NO
ClientAppName = appName
BrandSelector = User
```


4.  AndroidManifest.xml in the <application> add
```xml
<application 
tools:replace="android:icon,android:label, android:theme />

```

5. If targetSdkVersion in gradle "22" or lower just add required permissions to AndroidManifest, If higher - you need request dangerous permissions before use SDK. See [https://developer.android.com/training/permissions/requesting.html](https://developer.android.com/training/permissions/requesting.html) 

Permissions for SDK required:

```xml
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
```

To receive programmatically list of required permissions. You can by this method:
 
 ```java
 
String[] permissions = MaeganSDK.getInstance().getListOfRequiredPermission();
 
 ```

6. The SDK library consist huge quantity so please make sure you do not reach a certain quantity of methods or just turn on MultiDex support. See [https://developer.android.com/studio/build/multidex.html](https://developer.android.com/studio/build/multidex.html)

# Using

### Example of using

1. Initialize in application class:    

```java

 MaeganConfig maeganConfig = new MaeganConfig.Builder(getApplicationContext())
     	        .appConfig(new CustomerAppConfig("dev","1","application ID"))
                .additionalCustomerInfo(mAdditionalCustomerInfo)
                .build();

 MaeganSDK.initialize(maeganConfig);

```

2. Request:

```java
MaeganSDK.getInstance().presentRestaurantList(this, CALL_BACK_CODE);

```

3. Response:

```java
class ExampleActivity extends Activity {
      @Override
      protected void onActivityResult(int requestCode, int resultCode, Intent data) {
          super.onActivityResult(requestCode, resultCode, data);
          if(requestCode == CALL_BACK_CODE) {
              switch (resultCode) {
                  case RESULT_OK: {
                      TableOrder order = data.getParcelableExtra(MaeganSDK.RESULT_TABLE_ORDER);
                      Log.d(TAG, "onActivityResult: order:" + order);
                      break;
                  }
                  case RESULT_CANCELED: {
                      if(data!=null && data.getExtras().containsKey(MaeganSDK.RESULT_ERROR)){
                          SDKError error = data.getExtras().getParcelable(MaeganSDK.RESULT_ERROR);
                          if(error!=null) {
                              Log.d(TAG, "onActivityResult: error code:" + error.getCode());
                              Log.d(TAG, "onActivityResult: error message:" + error.getMessage());
                          }
                      }
                      break;
                  }
              }
          }
      }
    }
```

3. See more examples in sample folder.

