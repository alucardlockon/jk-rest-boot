package com.alucardlockon.jkrestboot.base.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod


@EnableWebSecurity
class WebSecurityConfig: WebSecurityConfigurerAdapter() {

    private var anyUserDetailsService: AnyUserDetailsService? = null

    @Autowired
    fun setAnyUserDetailsService(anyUserDetailsService: AnyUserDetailsService) {
        this.anyUserDetailsService = anyUserDetailsService
    }

    @Autowired
    lateinit var myCustomLoginAuthenticationSuccessHandler: CustomLoginAuthenticationSuccessHandler
    @Autowired
    lateinit var myCustomLoginAuthenticationFailureHandler: CustomLoginAuthenticationFailureHandler

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // 无需使用csrf
        http.csrf().disable()
        // spring security跨域设置
        http.cors().configurationSource(corsConfigurationSource())
        // 其他安全设置
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/account/").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").successHandler(myCustomLoginAuthenticationSuccessHandler).failureHandler(myCustomLoginAuthenticationFailureHandler).permitAll()
                .and()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
        // auth.inMemoryAuthentication().passwordEncoder(BCryptPasswordEncoder())
        auth!!.userDetailsService<AnyUserDetailsService>(anyUserDetailsService)
                .passwordEncoder(BCryptPasswordEncoder())
    }

    /**
     * 跨域设置
     */
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL)
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL)
        corsConfiguration.addExposedHeader("Authorization")
        corsConfiguration.addAllowedOrigin("*")
        corsConfiguration.allowCredentials = true

        val corsSource = UrlBasedCorsConfigurationSource()
        corsSource.registerCorsConfiguration("/**", corsConfiguration)

        return corsSource
    }

}