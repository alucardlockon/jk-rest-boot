package com.alucardlockon.jkrestboot.modules.system.account.service.impl

import com.alucardlockon.jkrestboot.modules.system.account.entity.Account
import com.alucardlockon.jkrestboot.modules.system.account.mapper.AccountMapper
import com.alucardlockon.jkrestboot.modules.system.account.service.IAccountService
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class AccountService : ServiceImpl<AccountMapper, Account>(), IAccountService {
    override fun getByUsername(username: String): Account? {
        return baseMapper.selectOne(QueryWrapper<Account>().eq("username",username))
    }
}