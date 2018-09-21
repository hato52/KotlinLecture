package com.example.user.lecturesample

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by hato on 2018/09/21.
 */
open class Memo (
        @PrimaryKey open var id: Long = 0,
        open var title: String = "",
        open var contents: String = ""
) : RealmObject() {}