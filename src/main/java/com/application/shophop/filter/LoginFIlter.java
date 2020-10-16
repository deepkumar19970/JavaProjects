package com.application.shophop.filter;

import com.application.shophop.DataTypeUtility;
import com.application.shophop.model.AppUser;
import com.application.shophop.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Configurable
@RequestMapping("/erp/*")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class LoginFIlter implements Filter {



    @Autowired
    AppUserService appUserService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      try{

          HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

          response.addHeader("Access-Control-Allow-Origin'", "http://localhost:4200");
          response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
//        response.addHeader("Access-Control-Allow-Credentials", "true");
          response.addHeader("Access-Control-Allow-Headers"  , "X-Requested-With,content-type");
//        response.addHeader("Access-Control-Allow-Headers"  , request.getHeader("Access-Control-Request-Headers"));


          Cookie[] cookies = request.getCookies();
        String sessionid=null;
        if(cookies!=null){
            for(Cookie c :cookies){
                if(c.getName()!=null && c.getName().length()>0 && c.getName().equals("SESSION")){
                    sessionid=c.getValue();
                }
                System.out.println("values>>"+ c.getValue()+"  >>"+c.getName());
            }
        }

        if(!request.getServletPath().contains("/authenticate")){
            HttpSession session = request.getSession(false);
            if(session==null){
                response.sendError(response.SC_UNAUTHORIZED);
            }
        }

        filterChain.doFilter(request,response);



//          if(sessionid==null){
//            String username = DataTypeUtility.stringValue(request.getParameter("username"));
//            String password = DataTypeUtility.stringValue(request.getParameter("password"));
//            if(username.length()>0 && password.length()>0){
//                AppUser appUser = appUserService.getAppUserByUseridAndAndPassword(username,password);
//                if(appUser==null){
//                   response.sendError(response.SC_UNAUTHORIZED);
//                }else{
//                    filterChain.doFilter(request,response);
//                }
//            }else{
//                response.sendError(response.SC_UNAUTHORIZED);
//            }
//        }else{
//           if( request.getSession().equals(sessionid)){
//               System.out.println("Matched");
//                filterChain.doFilter(request,response);
//            }
//        }

      }catch (Exception e){
          e.printStackTrace();
      }
    }
}
