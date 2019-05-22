package com.oliiyu.userservice.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Author: oliiyu
 * Date: 2019/5/6 15:17
 * Description: 为了让Spring可以知道我们想怎样控制安全性，我们还需要建立一个安全配置类 WebSecurityConfig ：
 */
@Configuration      // 标识配置类
@EnableWebSecurity  // 开启 Security 服务
@EnableGlobalMethodSecurity(prePostEnabled = true)// 开启全局 Securtiy 注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 注册 401 处理器
     */
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPointImpl;

    /**
     * 注册 403 处理器
     */
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandlerImpl;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 注册 token 转换拦截器为 bean
     * 如果客户端传来了 token ，那么通过拦截器解析 token 赋予用户权限
     */
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new AuthenticationTokenFilter();
    }

    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(jwtUserDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    // Spring会自动寻找同样类型的具体类注入，这里就是JwtUserDetailsServiceImpl了
    @Autowired
//    private UserDetailsService userDetailsService;
    private UserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .authenticationProvider(authenticationProvider());
//                // 设置UserDetailsService
//                .userDetailsService(this.jwtUserDetailsServiceImpl)
//                // 使用BCrypt进行密码的hash
//                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf, 禁用 Spring Security 自带的跨域处理
                .csrf().disable()

                // 基于token，所以不需要session, 调整为让 Spring Security 不创建和使用 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // 允许对于网站静态资源的无授权访问
                .antMatchers(HttpMethod.GET, "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()

                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/auth/**", "/register/**").permitAll()

//                .antMatchers("/admin").hasAuthority("admin")    // 需拥有 admin 这个权限
//                .antMatchers("/ADMIN").hasRole("ADMIN")         // 需拥有 ADMIN 这个身份

                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()


                // 配置被拦截时的处理
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPointImpl)    // 添加 token 无效或者没有携带 token 时的处理
                .accessDeniedHandler(this.accessDeniedHandlerImpl);             // 添加无权限时的处理

        /**
         * 本次 json web token 权限控制的核心配置部分
         * 在 Spring Security 开始判断本次会话是否有权限时的前一瞬间
         * 通过添加过滤器将 token 解析，将用户所有的权限写入本次 Spring Security 的会话
         */
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }
}
