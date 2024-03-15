package com.example.kmldatastructure.device

import android.content.Context
import android.os.Build
import android.provider.Settings
import java.util.*

class DeviceInfoProvider(private val context: Context) {
    fun getDeviceName(): String {
        return Build.MODEL
    }

    fun getDeviceUUID(): String {
        val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return UUID.nameUUIDFromBytes(androidId.toByteArray()).toString()
    }

    // Example of other device information
    fun getManufacturer(): String {
        return Build.MANUFACTURER
    }

    fun getOSVersion(): String {
        return Build.VERSION.RELEASE
    }

    fun getDeviceModel(): String {
        return Build.MODEL
    }
}
