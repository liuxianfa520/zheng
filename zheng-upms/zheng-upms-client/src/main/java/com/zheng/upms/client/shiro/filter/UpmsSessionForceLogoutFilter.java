package com.zheng.upms.client.shiro.filter;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 强制退出会话过滤器
 *
 * @author shuzheng
 * @date 2017/3/1
 */
public class UpmsSessionForceLogoutFilter extends AccessControlFilter {

    /**
     * <b>访问是否允许</b><br>
     * 如果session中有'FORCE_LOGOUT',则允许访问(此方法返回true);<br>
     * 如果session中没有'FORCE_LOGOUT',则说明已被管理员强制退出(此方法返回false);
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return true:允许访问,false:不允许访问
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response,
                                      Object mappedValue) throws Exception {

        Session session = getSubject(request, response).getSession(false);
        if (session == null) {
            return true;
        }
        return session.getAttribute("FORCE_LOGOUT") == null;
    }

    /**
     * 当访问被拒绝
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {
        getSubject(request, response).logout();
        String loginUrl = getLoginUrl() + (getLoginUrl().contains("?") ? "&" : "?") + "forceLogout=1";
        WebUtils.issueRedirect(request, response, loginUrl);
        return false;
    }

}
