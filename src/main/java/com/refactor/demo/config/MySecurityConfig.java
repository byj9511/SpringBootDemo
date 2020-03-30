package com.refactor.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.refactor.demo.auth.*;
import net.sf.jsqlparser.schema.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.restart.FailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    MySuccessHandler mySuccessHandler;

    @Autowired
    MyFailureHandler myFailureHandler;

    @Autowired
    MyExpiredSeeionStrategy myExpiredSeeionStrategy;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    AllRequestAuthenticateService allRequestAuthenticateService;

    @Autowired
    private DataSource dataSource;//参见DataSourceConfiguration
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        //1登陆控制
        http.formLogin()
                //访问逻辑
                .loginPage("/")
                .loginProcessingUrl("/userlogin")
                .usernameParameter("un")
                .passwordParameter("pd")
                .successHandler(mySuccessHandler)
                .failureHandler(myFailureHandler);

        //1.2访问权限控制
        http.authorizeRequests()

                .antMatchers("/","/kaptcha").permitAll()  //任何人都能访问该路径,主页、登陆请求、验证码等请求应该让任何人都能访问
                .antMatchers("/security/admin").hasRole("admin") //具有admin角色权限才能访问该路径
                .antMatchers("/security/user").hasAuthority("/security/user") //让该路径拥有名为/security/user的资源权限id
                .anyRequest().access("@allRequestAuthenticateService.isPermitted(request,authentication)");//权限表达式，如果是自己定义的函数要加上@
        //.anyRequest().authenticated(); //任何访问都需要进行验证（检查登陆）

                //.defaultSuccessUrl("/main");
    //   3 session管理
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/") //session超时失效后返回主页，其失效时间在application中配置
                .sessionFixation().migrateSession()
                //    一个用户最大访问数量
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(myExpiredSeeionStrategy);//多余用户登陆了之后给出一定的响应
        //4remberme的token
        http.rememberMe()//设置rememberme的令牌
            .rememberMeCookieName("dsadsad")
            .tokenValiditySeconds(2*60)
            .rememberMeParameter("rm")
            .tokenRepository(persistentTokenRepository());//对数据标的名称、字段名都有要求
            //insert into persistent_logins (username, series, token, last_used) values(?,?,?,?)
            //数据库的表名和字段名都已经写死了
        http.logout();
        //5额外的访问认证
        http.addFilterBefore(new ImageAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    用户权限设置

        //auth.inMemoryAuthentication()
        //        .passwordEncoder(passwordEncoder())
        //        .withUser("123").password(passwordEncoder().encode("123")).roles("admin","user");
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }//重要！！要选择加密器

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        //token持久化
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}

