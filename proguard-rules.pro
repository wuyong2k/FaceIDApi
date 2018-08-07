# 代码混淆压缩比，在0~7之间，默认为5，一般不需要改
-optimizationpasses 5
# 混淆时不使用大小写混合，混淆后的类名为小写
-dontusemixedcaseclassnames
# 指定不去忽略非公共的库的类
-dontskipnonpubliclibraryclasses
# 混淆时生成日志文件，即映射文件
-verbose
# 指定映射文件的名称
-printmapping proguardMapping.txt
# 不优化输入的类文件
-dontoptimize
# 不做预校验，可加快混淆速度
# preverify是proguard的4个步骤之一
# Android不需要preverify，去掉这一步可以加快混淆速度
-dontpreverify
# 指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers
#混淆时所采用的算法
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
# 保护代码中的Annotation不被混淆
-keepattributes *Annotation*
# 忽略警告
# -ignorewarning
# 保护泛型不被混淆
-keepattributes Signature
# 抛出异常时保留代码行号
-keepattributes InnerClasses,Exceptions, Signature, Deprecated, SourceFile, SourceDir, LineNumberTable, LocalVariableTable, LocalVariableTypeTable, Synthetic, EnclosingMethod, RuntimeVisibleAnnotations, RuntimeInvisibleAnnotations, RuntimeVisibleParameterAnnotations, RuntimeInvisibleParameterAnnotations, AnnotationDefault
-renamesourcefileattribute SourceFile

# 保留所有的本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers class ** {
  @com.megvii.api.annotation.* public *;
}

# 保留枚举 enum 类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class com.megvii.api.util.* { public *;}
-keep class com.megvii.api.annotation.* { public *;}
-keep class com.megvii.api.entity.* {
    void set*(***);
    *** get*();
}
-keep class com.megvii.api.FaceID* {
    public *;
}
-keep class com.megvii.api.FaceIDApi$Verify {
    private <init>(***);
    public *;
}

# 不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}

# okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

# okio
-dontwarn okio.**
-keep class okio.**{*;}

-keep class android.support.** { *; }
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v7.** { *; }
-keep public class * extends android.support.v7.**
-keep interface android.support.v7.app.** { *; }
-dontwarn android.support.**

#keep相关注解
-keep class android.support.annotation.Keep

-keep @android.support.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}