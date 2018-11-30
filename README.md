# Overview

The maeganSDK allows to incorporate ordering with maegan platform in your app. You need a client App Id / client App Name and the external customer Id.

High-level assumptions:
* The wrapper app maintains the customer information and ensures the user is logged in and authenticated when the SDK is invoked.
* The wrapper app may process payments outside of the SDK

The SDK incorporates multiple views:
* Restaurant view – enables user to view one location, browse menus, order and pay
* Restaurants list view – enables users to view multiple locations, browser their menus,
order and pay
* Order history view – enables users to see their order history and reorder
* Credit card view – enables users to setup a credit card on file; please note this view is
not used for wallet integrations
* Pay at the counter view – enables users to present a barcode and pay with the credit
card on file; this view is valid only for locations running on Oracle (all versions) and Volante Systems.

Supports minSdkVersion: 19

# Installation

1. Add plugins and dependency Maegan SDK to build.gradle

```groovy
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: "kotlin-kapt"
apply plugin: 'kotlin-android-extensions'

dependencies {
  compile 'com.tacitinnovations.maegansdk:maegan-sdk:1.0.15'
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

3. Add databinding 

```groovy
    dataBinding {
        enabled = true
    }
```

4. Add local Properties file stored assets/settings.properties (for explanation please see Additional customizations) 
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

7. The SDK library consist huge quantity so please make sure you do not reach a certain quantity of methods or just turn on MultiDex support. See [https://developer.android.com/studio/build/multidex.html](https://developer.android.com/studio/build/multidex.html)

# Using

### Example of using

1. Initialize in application class:    

```java

 MaeganConfig maeganConfig = new MaeganConfig.Builder(getApplicationContext())
     	        .appConfig(new CustomerAppConfig("dev", "1F7D8922E38C4085A48BC6A1D9C3655A", "aramarkCuSDK"))
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

### Additional configuration and customizations
1. Local Properties file stored assets/settings.properties

* **ManageCustomerProfile**: set YES if the customer profile should be managed by the MaeganSDK. Set NO, if the customer profile should be managed by your app. In this case your app must set the DataExchangeDelegate that conform to
* **ClientAppName**: given client app name
* **BrandSelector**: company brand selector type. Depending on the value, “Search”/“View More Locations” buttons on the Landing screen display different screens, logic of presenting the list of the restaurants changes. Possible values are
o None: all restaurants are displayed, Search button allows to search restaurants by name.
o Location: only the restaurants of the selected brands are displayed. If no brand is selected, the nearest restaurant’s brand is selected by default. The search button opens brand selection screen.
o User:onlytherestaurantsoftheselectedbrandsaredisplayed.Searchbutton opens brand selection screen.
* **DisplayOrderTypeBar**: set YES if the order type bar (at the top) should be displayed on the Landing screen. Set NO to permanently hide it

2. To change logo you should override resource in path /res/drawable/mgn_logo_home_small.png
3. To change splash screen you should override resource mgn_splash.png in paths  /res/drawable-mdpi/
/res/drawable-hdpi/, /res/drawable-xhdpi/, /res/drawable-xxhdpi/ 
4. To change colors you should override color identifiers in /res/values:
```xml
<resources>
    <color name="mgn_primary">#3F51B5</color> <!-- // app bar, action bar, additional -->
    <color name="mgn_primary_dark">#303F9F</color> <!-- // status bar version. -->
    <color name="mgn_accent">#FF4081</color> <!-- // progress dialog, floating button and main buttons-->
    <color name="mgn_tabs">#303F9F</color> <!-- // color of tabs -->
</resources>
```
