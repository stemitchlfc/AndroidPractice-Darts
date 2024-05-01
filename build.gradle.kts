buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
    id("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false
}