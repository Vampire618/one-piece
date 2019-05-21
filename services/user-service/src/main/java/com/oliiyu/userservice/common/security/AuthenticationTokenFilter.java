package com.oliiyu.userservice.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: oliiyu
 * Date: 2019/5/6 15:23
 * Description:
 * 集成JWT在Spring中工作，新建一个filter，并把它配置在 WebSecurityConfig 中。
 * <p>
 * 如果足够相信token中的数据，也就是足够相信签名token的secret的机制足够好，这种情况下，可不用再查询数据库，而直接采用token中的数据。
 * 本例，还是通过Spring Security的 @UserDetailsService 进行了数据查询，但简单验证的话，可采用直接验证token是否合法来避免昂贵的数据查询。
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * Spring Security 的核心操作服务类
     * 在当前类中将使用 UserDetailsService 来获取 userDetails 对象
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 尝试获取请求头的 token
        String authHeader = request.getHeader(this.tokenHeader);

        // 尝试拿 token 中的 username
        // 若是没有 token 或者拿 username 时出现异常，那么 username 为 null
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            // The part after "Bearer "
            final String authToken = authHeader.substring(tokenHead.length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);

            logger.info("checking authentication " + username);

            // 如果上面解析 token 成功并且拿到了 username 并且本次会话的权限还未被写入
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // 用 UserDetailsService 从数据库中拿到用户的 UserDetails 类
                // UserDetails 类是 Spring Security 用于保存用户权限的实体类
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

                // 检查用户带来的 token 是否有效
                // 包括 token 和 userDetails 中用户名是否一样，token 是否过期，token 生成时间是否在最后一次密码修改时间之前
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {

                    // 若是检查通过, 生成通过认证
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.info("authenticated user " + username + ", setting security context");

                    // 将权限写入本次会话
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        chain.doFilter(request, response);
    }

 /*   @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 将 ServletRequest 转换为 HttpServletRequest 才能拿到请求头中的 token
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 尝试获取请求头的 token
        String authToken = httpRequest.getHeader(this.tokenHeader);
        // 尝试拿 token 中的 username
        // 若是没有 token 或者拿 username 时出现异常，那么 username 为 null
        String username = this.tokenUtils.getUsernameFromToken(authToken);

        // 如果上面解析 token 成功并且拿到了 username 并且本次会话的权限还未被写入
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 用 UserDetailsService 从数据库中拿到用户的 UserDetails 类
            // UserDetails 类是 Spring Security 用于保存用户权限的实体类
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            // 检查用户带来的 token 是否有效
            // 包括 token 和 userDetails 中用户名是否一样， token 是否过期， token 生成时间是否在最后一次密码修改时间之前
            // 若是检查通过
            if (this.tokenUtils.validateToken(authToken, userDetails)) {
                // 生成通过认证
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                // 将权限写入本次会话
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            if (!userDetails.isEnabled()){
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print("{\"code\":\"452\",\"data\":\"\",\"message\":\"账号处于黑名单\"}");
                return;
            }
        }
        chain.doFilter(request, response);
    }
    */
}
