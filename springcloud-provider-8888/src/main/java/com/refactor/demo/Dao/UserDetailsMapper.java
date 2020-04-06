package com.refactor.demo.Dao;

import com.refactor.demo.auth.MyUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDetailsMapper {
    @Select("SELECT r.name\n" +
            "FROM roles r LEFT JOIN users_roles u_r on r.id=u_r.rid\n" +
            "LEFT JOIN users u ON u_r.uid=u.id\n" +
            "WHERE u.name=#{userName}")
    public List<String> getRolesByUserName(String userName);

    public List<String> getAuthoritiesByroles(List<String> roles);

    //因为要用username查找，所以
    //@Select("SELECT url\n" +
    //        "FROM menu m RIGHT JOIN roles_menu r_m on m.id=r_m.mid\n" +
    //        "RIGHT JOIN roles r ON r_m.rid=r.id\n" +
    //        "RIGHT JOIN users_roles u_r on r.id=u_r.rid\n" +
    //        "RIGHT JOIN users u ON u_r.uid=u.id\n" +
    //        "WHERE u.name=#{userName}")
    @Select("SELECT url\n" +
            "FROM users u LEFT JOIN users_roles u_r on u.id=u_r.uid\n" +
            "LEFT JOIN roles r ON u_r.rid=r.id\n" +
            "LEFT JOIN roles_menu r_m on r.id=r_m.rid\n" +
            "LEFT JOIN menu m ON r_m.mid=m.id\n" +
            "WHERE u.name=#{userName}")
    public List<String> getAuthoritiesByUserName(String userName);

    @Select("select * from users where name=#{userName}")
    //column代表了字段名,property代表了属性名
    @Results(value =
                @Result(column = "name",property = "username"))
    public MyUserDetails getUserDetailsByUserName(String userName);
    @Select("select url from menu")
    public List<String> getUris();
}
