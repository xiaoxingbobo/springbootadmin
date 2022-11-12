package com.xxbb.springbootapi.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xxbb.springbootapi.entity.User;
import com.xxbb.springbootapi.utils.IpUtil;
import com.xxbb.springbootapi.utils.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("Authorization");
        //token不为空
        if (StringUtils.hasText(token)) {
            try {
                DecodedJWT jwt = JwtUtil.verifyToken(token);
                Integer userId = jwt.getClaim("id").asInt();
                String username = jwt.getClaim("username").asString();
                Integer roleId = jwt.getClaim("roleId").asInt();
                List<String> authority = jwt.getClaim("authority").asList(String.class);
                String name = jwt.getClaim("name").asString();
                if(roleId==1){
                    authority.add("ROLE_ADMIN");
                }
                //把token中的信息存入SecurityContext
                User user = new User().setId(userId).setUsername(username).setRoleId(roleId).setName(name);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authority.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            } catch (SignatureVerificationException exception) {
                throw new LegalException("无效签名！");
            } catch (TokenExpiredException exception) {
                //判断是否是登录请求
                if (!httpServletRequest.getRequestURI().contains("login")) {
                    //移除权限
                    httpServletRequest.removeAttribute("Authorization");
                    throw new LegalException("token已过期！请重新登录");
                }
            } catch (AlgorithmMismatchException exception) {
                //记录ip
                log.info("违规操作：" + IpUtil.getIp(httpServletRequest));
                throw new LegalException("token算法不一致！请勿违规操作，否则后果自负！");
            } catch (InvalidClaimException | IllegalAccessError exception) {
                throw new LegalException("token无效！");
            } catch (Exception e) {
                throw new LegalException(e.getMessage());
            }
        }
        //放行
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
