# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 💀 Make class names unreadable
-obfuscationdictionary obfuscation.txt
-classobfuscationdictionary obfuscation.txt
-packageobfuscationdictionary obfuscation.txt

# 🚫 Prevent decompilers from reconstructing source code
-dontusemixedcaseclassnames
-dontpreverify
-optimizationpasses 5
-allowaccessmodification
-renamesourcefileattribute "Obfuscated"
-adaptclassstrings
-keepattributes *Annotation*

# 🔒 Protect all your classes
-keep class com.yourpackage.** { *; }

# 😵 Keep only essential methods, rest gets messed up
-keepclassmembers class * {
    @androidx.annotation.Keep *;
}

# 🛡️ Hide Log statements (so he can’t see what’s happening)
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}

# 🚀 Remove unused code & resources
-ignorewarnings

# 👁️ Make reflection useless
-flattenpackagehierarchy
-repackageclasses obf

