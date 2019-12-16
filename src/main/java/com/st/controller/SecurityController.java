package com.st.controller;

import com.st.bean.User;
import com.st.mysql.dto.UserDTO;
import com.st.result.Result;
import com.st.result.UserResult;
import com.st.service.SecurityService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Shumao
 * @description
 * @date 2019/12/6 15:34
 */
@CrossOrigin
@RestController
@RequestMapping("security")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    // Auth

    /**
     * 登录
     * @param userDTO 用户名密码
     * @return Token
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                userDTO.getUsername(),
                userDTO.getPassword());
        Result result = new Result();
        try {
            subject.login(usernamePasswordToken);

            Session session = subject.getSession();
            session.setAttribute("username", userDTO.getUsername());

            result.setToken(session.getId().toString());
            result.setMessage("登录成功");
        } catch (AuthenticationException e) {
            result.fail("账号或密码错误！" + e.getMessage());
            result.setCode(Result.PASSWORD_FAIL);
            return result;
        } catch (AuthorizationException e) {
            result.fail("没有权限！" + e.getMessage());
            result.setCode(Result.PERMISSION_FAIL);
            return result;
        }
        return result;
    }

    /**
     * 根据token获取用户信息
     * @param token token
     * @return User
     */
    @RequestMapping("/token")
    public UserResult token(String token) {
        UserResult result = new UserResult();
        result.setToken(token);
        try {
            Session session = SecurityUtils.getSecurityManager().getSession(() -> token);
            String username = (String) session.getAttribute("username");
            User user = securityService.getUserByName(username).getData();
            result.setData(user);
            result.setMessage("Token获取用户信息成功！");
        } catch (Exception e) {
            result.fail("登录失效，请重新登录！" + e.getMessage());
            result.setCode(Result.TOKEN_ILLEGAL);
            return result;
        }
        return result;
    }

    /**
     * 登出
     * @return result
     */
    @PostMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        Result result = new Result();
        try {
            subject.logout();
            result.setMessage("登出成功！");
        } catch (Exception e) {
            result.fail(e.getMessage());
            result.setCode(Result.LOGOUT_FAIL);
            return result;
        }
        return result;
    }

}
