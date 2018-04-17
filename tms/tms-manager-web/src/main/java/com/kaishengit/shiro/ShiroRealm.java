package com.kaishengit.shiro;

import com.kaishengit.entitys.Account;
import com.kaishengit.entitys.AccountLoginLog;
import com.kaishengit.mapper.AccountLoginLogMapper;
import com.kaishengit.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ShiroRealm extends AuthorizingRealm{

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        String mobile = usernamePasswordToken.getUsername();
        System.out.println("mobile-----"+mobile);
        if (mobile != null) {
            Account account = accountService.findByMobile(mobile);
            if (account == null) {
                throw new UnknownAccountException("账户或者密码错误" + mobile);
            }else{
                if (account.getStatus().equals(Account.Status_Normal)){
                    logger.info("{}登录",account);
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setLoginTime(new Date());
                    accountLoginLog.setLoginIp(usernamePasswordToken.getHost());
                    accountLoginLog.setAccountId(account.getId());
                    accountLoginLogMapper.insertSelective(accountLoginLog);
                    return new SimpleAuthenticationInfo(account,account.getPassword(),getName());
                }else{
                    throw new LockedAccountException("账户异常");
                }
            }
        }
        return null;
    }
}