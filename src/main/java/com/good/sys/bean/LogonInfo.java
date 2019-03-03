package com.good.sys.bean;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * 登录信息汇总
 * 
 * @author wuwei
 */
public class LogonInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Operator oper;
    private Collection<? extends GrantedAuthority> grantedAuth;

    public Operator getOperator() {
        return oper;
    }

    public void setOperator(Operator oper) {
        this.oper = oper;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuth;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> granted) {
        grantedAuth = granted;
    }

}
