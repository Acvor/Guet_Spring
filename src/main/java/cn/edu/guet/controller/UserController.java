package cn.edu.guet.controller;

import cn.edu.guet.bean.RelayDTO;
import cn.edu.guet.bean.User;
import cn.edu.guet.bean.UserExample;
import cn.edu.guet.common.ResponseData;
import cn.edu.guet.mapper.UserMapper;
import cn.edu.guet.mvc.annotation.Controller;
import cn.edu.guet.mvc.annotation.RequestMapping;
import cn.edu.guet.util.DBConnection;
import org.apache.ibatis.session.SqlSession;

@Controller
public class UserController {
    /*根据用户名获取权限*/
    @RequestMapping("/getPermissionsByUsername")
    public ResponseData getPermissionsByUsername(String username){
        SqlSession sqlSession = DBConnection.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return ResponseData.ok(userMapper.getPermissionsByUsername(username));
    }
    /*登录*/
    @RequestMapping("/login")
    public ResponseData login(RelayDTO relayDTO){
        try {
            String username = relayDTO.getUsername();
            String password = relayDTO.getPassword();
            SqlSession sqlSession = DBConnection.getSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUsernameEqualTo(username);
            User user = userMapper.selectByExample(userExample).get(0);
            if (user.getPassword().equals(password)) return ResponseData.ok("true");
            else return ResponseData.ok("false");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseData.ok("用户名不存在.");
        }
    }
}
