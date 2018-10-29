package com.alucardlockon.jkrestboot.modules.system.account.entity

import java.util.*

/**
 * 用户账户
 */
data class Account (
    var id:Int = 0,
    var username:String? = "",
    var password:String? = "",
    var email:String? = "",
    var status:Int? = 0,
    var createIp:String? = "",
    var createDate:Date? = Date(),
    var createUser:Int? = 0,
    var updateDate:Date? = Date(),
    var updateUser:Int? = 0,
    var lastIp:String? = "",
    var roles:String? = ""
)