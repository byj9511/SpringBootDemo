package com.refactor.demo.auth;

import com.refactor.demo.Dao.UserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
@Service
public class AllRequestAuthenticateService {
    @Autowired
    UserDetailsMapper userDetailsMapper;
    public boolean isPermitted(HttpServletRequest request ,Authentication auth){
        Object principal = auth.getPrincipal();//被验证的主体
        String uri = request.getRequestURI();
        List<String> uris = userDetailsMapper.getUris();
        if (uris.stream().anyMatch(u->u==uri)){
            //确定访问的url是否需要权限
            if (principal instanceof UserDetails){
                String username = ((UserDetails) principal).getUsername();
                List<String> authorities = userDetailsMapper.getAuthoritiesByUserName(username);
                System.out.println(uri);
                System.out.println(authorities);;
                //查看用户主体是否具有权限，此处将uri作为权限标识
                return authorities.stream().anyMatch(authority -> authority == uri);
            }
        }
        return true;
    }
}
