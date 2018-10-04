-keepattributes *Annotation*,Signature
-keepattributes SourceFile,LineNumberTable
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-dontwarn retrofit.**
-dontwarn com.zendesk.util.**
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8
-keep class com.tacitinnovations.tacitlinkx.app.MaeganApp
-keep class com.softjourn.asyncrequest.** { *; }
-keep class com.tacitinnovations.core.app.TacitApp


# Gson specific classes
-keep class com.tacitinnovations.core.api.model.** { *; }
-keep class com.tacitinnovations.core.api.requests.** { *; }
-keep class com.tacitinnovations.core.utils.persistence.** { *; }
-keep class com.tacitinnovations.core.sdk.** { *; }


-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer