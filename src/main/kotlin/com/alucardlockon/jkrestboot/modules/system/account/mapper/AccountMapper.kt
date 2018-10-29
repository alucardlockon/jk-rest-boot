package com.alucardlockon.jkrestboot.modules.system.account.mapper

import com.alucardlockon.jkrestboot.modules.system.account.entity.Account
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper

@Mapper
interface AccountMapper: BaseMapper<Account> {

}