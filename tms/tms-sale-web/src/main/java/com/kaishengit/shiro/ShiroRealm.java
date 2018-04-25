package com.kaishengit.shiro;

import com.kaishengit.entitys.SaleAccount;
import com.kaishengit.entitys.Ticketoffice;
import com.kaishengit.service.SaleAccountService;
import com.kaishengit.service.TicketofficeService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm{

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private TicketofficeService ticketofficeService;
    @Autowired
    private SaleAccountService saleAccountService;


    /*
    * 判断角色和权限
    * */
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
            SaleAccount saleAccount  = saleAccountService.findByMobile(mobile);
            if (saleAccount == null) {
                throw new UnknownAccountException("账户或者密码错误" + mobile);
            }else{
                Ticketoffice ticketoffice = ticketofficeService.findBySaleAccountId(saleAccount.getId());
                return new SimpleAuthenticationInfo(ticketoffice,saleAccount.getPassword(),getName());
            }
        }
        return null;
    }
}