package com.example.user.lecturesample

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by hato on 2018/09/21.
 */
class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //Realmの初期設定
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }
}