package com.refactor.demo.auth;

import com.refactor.demo.Dao.UserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserDetailsMapper userDetailsMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MyUserDetails myUserDetails = userDetailsMapper.getUserDetailsByUserName(s);
        List<String> roles = userDetailsMapper.getRolesByUserName(s);
        System.out.println(myUserDetails);
        List<GrantedAuthority> roleAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", roles));
        myUserDetails.setAuthorities(roleAuthorities);

        return myUserDetails;
    }
}
