package com.application.shophop.configuration;

import com.application.shophop.constants.Branch;
import com.application.shophop.constants.BranchContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DataSourceInterceptor  extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String scheme = request.getScheme();             // http
        String serverName = request.getServerName();     // hostname.com
        int serverPort = request.getServerPort();        // 80
        String contextPath = request.getContextPath();   // /mywebapp
        String servletPath = request.getServletPath();   // /servlet/MyServlet
        String pathInfo = request.getPathInfo();         // /a/b;c=123
        String queryString = request.getQueryString();          // d=789

        if(serverName.equals(Branch.defaults)){
            BranchContextHolder.setBranchContext(Branch.defaults);
        }else if(serverName.equals(Branch.demoschool)){
            BranchContextHolder.setBranchContext(Branch.demoschool);
        }else{
            BranchContextHolder.setBranchContext(Branch.defaults);
        }
        return super.preHandle(request, response, handler);
    }
}
