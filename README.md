# Installation

1. Add dependency Maegan SDK to build.gradle

```groovy
dependencies {
    compile 'com.tacitinnovations.maegansdk:maegan-sdk:0.9.7.9'
}
```

2. Add repository for ZenDesk dependency 

```groovy
repositories{
    maven {
        url "https://maven.google.com"
    }
    maven { url 'https://zendesk.artifactoryonline.com/zendesk/repo'}

}
```

3. Add repository for ZenDesk dependency 

```groovy
    dataBinding {
        enabled = true
    }
```

4. Add local Properties file stored assets/settings.properties
```
ManageCustomerProfile = NO
ManagePaymentInstrument = NO
ClientAppName = appName
BrandSelector = User
DisplayOrderTypeBar = NO
```


5.  AndroidManifest.xml in the <application> add
```xml
<application 
tools:replace="android:icon,android:label, android:theme />

```

6. If targetSdkVersion in gradle "22" or lower just add required permissions to AndroidManifest, If higher - you need request dangerous permissions before use SDK. See [https://developer.android.com/training/permissions/requesting.html](https://developer.android.com/training/permissions/requesting.html) 

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
 
String[] permissions = MaeganSDK.getListOfRequiredPermission();
 
 ```

7. The SDK library consist huge quantity so please make sure you do not reach a certain quantity of methods or just turn on MultiDex support. See [https://developer.android.com/studio/build/multidex.html](https://developer.android.com/studio/build/multidex.html)

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

4. See more examples in sample folder.

### Additional customizations

1. To change logo you should override resource in path /res/drawable/mgn_logo_home_small.png

2. To change splash screen you should override resource mgn_splash.png in paths  /res/drawable-mdpi/
/res/drawable-hdpi/, /res/drawable-xhdpi/, /res/drawable-xxhdpi/ 
3. To change colors u should override color identifiers in /res/values:
```xml
<resources>
    <color name="mgn_primary">#3F51B5</color>
    <color name="mgn_primary_dark">#303F9F</color>
    <color name="mgn_accent">#FF4081</color>
    <color name="mgn_tabs">#303F9F</color>
</resources>
```