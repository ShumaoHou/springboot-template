package com.st.shiro.realm;

import com.st.bean.Permission;
import com.st.bean.User;
import com.st.mysql.dto.UserDTO;
import com.st.service.SecurityService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Shumao
 * @description 自定义Realm
 * @date 2019/12/6 15:35
 */
public class SecurityRealm extends AuthorizingRealm {

    @Autowired
    private SecurityService securityService;

    /**
     * 授权验证
     *
     * @param principalCollection 主要集合
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        if (principalCollection.getPrimaryPrincipal() == null) {
            return null;
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        String username = (String) principalCollection.getPrimaryPrincipal();
        User user = securityService.getUserByName(username).getData();
        user.getRoles().forEach(role -> {
            // 添加角色
            simpleAuthorizationInfo.addRole(role.getName());
            for (Permission permission : role.getPermissions()) {
                // 添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getName());
            }
        });

        return simpleAuthorizationInfo;
    }

    /**
     * 登陆验证
     *
     * @param authenticationToken 认证令牌
     * @return 身份验证信息
     * @throws AuthenticationException 登陆异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        if (authenticationToken.getPrincipal() == null) {
            return null;
        }

        String username = authenticationToken.getPrincipal().toString();
        UserDTO userDTO = securityService.loginUserByName(username).getData();

        if (userDTO == null) {
            //这里返回后会报出对应异常
            return null;
        }

        // 验证信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, userDTO.getPassword(), getName());
        // 加密
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        return authenticationInfo;
    }
}
