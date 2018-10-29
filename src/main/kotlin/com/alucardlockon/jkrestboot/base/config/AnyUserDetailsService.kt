package com.alucardlockon.jkrestboot.base.config

import com.alucardlockon.jkrestboot.modules.system.account.service.impl.AccountService
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.ArrayList
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder




@Service
class AnyUserDetailsService @Autowired
internal constructor(private val userService: AccountService) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity = userService.getByUsername(username) ?: throw UsernameNotFoundException("用户不存在！")
        val simpleGrantedAuthorities = createAuthorities(userEntity.roles!!)
        return User(userEntity.username, userEntity.password, simpleGrantedAuthorities)
    }

    /**
     * 权限字符串转化
     *
     * 如 "USER,ADMIN" -> SimpleGrantedAuthority("USER") + SimpleGrantedAuthority("ADMIN")
     *
     * @param roleStr 权限字符串
     */
    private fun createAuthorities(roleStr: String): List<SimpleGrantedAuthority> {
        val roles = roleStr.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val simpleGrantedAuthorities = ArrayList<SimpleGrantedAuthority>()
        for (role in roles) {
            simpleGrantedAuthorities.add(SimpleGrantedAuthority(role))
        }
        return simpleGrantedAuthorities
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}