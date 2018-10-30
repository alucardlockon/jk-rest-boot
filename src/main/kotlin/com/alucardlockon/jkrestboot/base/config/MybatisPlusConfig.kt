package com.alucardlockon.jkrestboot.base.config

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement


@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@MapperScan("com.baomidou.cloud.service.*.mapper*")
class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    fun paginationInterceptor(): PaginationInterceptor {
        return PaginationInterceptor()
    }
}
