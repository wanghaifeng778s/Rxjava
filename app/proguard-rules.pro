# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Develops\Android\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html
#
# Add any project specific keep options here:
#
# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}



#  指定代码的压缩级别
  -optimizationpasses 10
  #混淆时不使用大小写混合类名
  -dontusemixedcaseclassnames
  #不跳过library中的非public的类
  -dontskipnonpubliclibraryclasses
   #表示不进行优化，建议使用此选项，因为根据proguard-android-optimize.txt中的描述，
   #优化可能会造成一些潜在风险，不能保证在所有版本的Dalvik上都正常运行
  -dontoptimize
   #表示不进行预校验。这个预校验是作用在Java平台上的，Android平台上不需要这项功能，去掉之后还可以加快混淆速度
  -dontpreverify
   #混淆时是否记录日志
  -verbose
   #表示对注解中的参数进行保留
  -keepattributes *Annotation*
   # 混淆时所采用的算法
  -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
   #表示不混淆任何包含native方法的类的类名以及native方法名
  -keepclasseswithmembernames class * {
      native <methods>;
  }
   #表示不混淆任何一个View中的setXxx()和getXxx()方法，因为属性动画需要有相应的setter和getter的方法实现，混淆了就无法工作了
  -keepclassmembers public class * extends android.view.View {
     void set*(***);
     *** get*();
  }
  # 保持哪些类不被混淆
  -keep public class * extends android.app.Fragment
  -keep public class * extends android.app.Activity
  -keep public class * extends android.app.Application
  -keep public class * extends android.app.Service
  -keep public class * extends android.content.BroadcastReceiver
  -keep public class * extends android.content.ContentProvider
  -keep public class * extends android.app.backup.BackupAgentHelper
  -keep public class * extends android.preference.Preference
  -keep public class com.android.vending.licensing.ILicensingService

  #如果有引用v4包可以添加下面这行
  -keep public class * extends android.support.v4.app.Fragment
  #忽略警告
  -ignorewarning
  #####################记录生成的日志数据,gradle build时在本项目根目录输出################
  #apk 包内所有 class 的内部结构
  -dump class_files.txt
  #未混淆的类和成员
  -printseeds seeds.txt
  #列出从 apk 中删除的代码
  -printusage unused.txt
  #混淆前后的映射
  -printmapping mapping.txt
  #####################记录生成的日志数据，gradle build时 在本项目根目录输出-end################
  ################混淆保护自己项目的部分代码以及引用的第三方jar包library#########################
#  三星应用市场需要添加:sdk
#  -v1.0.0.jar,look-v1.0.1.jar
#  -libraryjars libs/sdk-v1.0.0.jar
#  -libraryjars libs/look-v1.0.1.jar

  #表示不混淆枚举中的values()和valueOf()方法
  -keepclassmembers enum * {
      public static **[] values();
      public static ** valueOf(java.lang.String);
  }

  #如果引用了v4或者v7包
  -dontwarn android.support.**
  ############混淆保护自己项目的部分代码以及引用的第三方jar包library-end##################
  -keep public class * extends android.view.View {
      public <init>(android.content.Context);
      public <init>(android.content.Context, android.util.AttributeSet);
      public <init>(android.content.Context, android.util.AttributeSet, int);
      public void set*(...);
  }
  #保持自定义控件类不被混淆
  -keepclasseswithmembers class * {
      public <init>(android.content.Context, android.util.AttributeSet);
  }
  #保持自定义控件类不被混淆
  -keepclasseswithmembers class * {
      public <init>(android.content.Context, android.util.AttributeSet, int);
  }
  #保持 Parcelable 不被混淆
  -keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
  }
  #保持 Serializable 不被混淆
  -keepnames class * implements java.io.Serializable
  #保持 Serializable 不被混淆并且enum 类也不被混淆
  -keepclassmembers class * implements java.io.Serializable {
      static final long serialVersionUID;
      private static final java.io.ObjectStreamField[] serialPersistentFields;
      !static !transient <fields>;
      !private <fields>;
      !private <methods>;
      private void writeObject(java.io.ObjectOutputStream);
      private void readObject(java.io.ObjectInputStream);
      java.lang.Object writeReplace();
      java.lang.Object readResolve();
  }
  -keepclassmembers class * {
      public void *ButtonClicked(android.view.View);
  }
  #不混淆资源类,文件中的所有静态字段
  -keepclassmembers class **.R$* {
      public static <fields>;
  }
  #避免混淆泛型 如果混淆报错建议关掉
  -keepattributes Signature


# --------------------js混淆---------------------------
# -keep class cn.jianguo.qinzi.web.**{*;}
-keepattributes *JavascriptInterface*
-keep class android.webkit.JavascriptInterface {*;}

#-----------fresco-------------------
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**
-dontwarn com.facebook.infer.**

##-------------------------volley-----------------
#-keep class com.android.volley.** {*;}
#-keep class com.android.volley.toolbox.** {*;}
#-keep class com.android.volley.Response$* { *; }
#-keep class com.android.volley.Request$* { *; }
#-keep class com.android.volley.RequestQueue$* { *; }
#-keep class com.android.volley.toolbox.HurlStack$* { *; }
#-keep class com.android.volley.toolbox.ImageLoader$* { *; }

#greendao
-keep class de.greenrobot.dao.** {*;}
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static Java.lang.String TABLENAME;
}
-keep class **$Properties

#EventBus
-keepclassmembers class ** {
    public void onEvent*(**);
}
-keepclassmembers class ** {
public void xxxxxx(**); #所有监听的方法都要列在这里
}
#gson
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.examples.Android.model.** { *; }

#fastJson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }

#Rxjava RxAndroid
-dontwarn rx.*
-dontwarn sun.misc.**

#retrofit2.x
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

################Rxjava and RxAndroid###############
-dontwarn org.mockito.**
-dontwarn org.junit.**
-dontwarn org.robolectric.**

-keep class rx.** { *; }
-keep interface rx.** { *; }

-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-dontwarn okio.**
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn rx.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-keep class sun.misc.Unsafe { *; }

-dontwarn java.lang.invoke.*

-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-dontwarn rx.internal.util.unsafe.**

#实体类
#-----------------------------------------------------------------------------
-keep class com.feng.com.rxjavade.app.bean.** {*;}

#-----------------------------------------------------------------------------

 #自己项目特殊处理代码

#-dontwarn shanggame.news.greendao.**
 #保留一个完整的包
#-keep com.marswin89.marsdaemon.** {*;}