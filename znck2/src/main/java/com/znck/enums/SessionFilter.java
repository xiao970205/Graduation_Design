package com.znck.enums;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "sessionFilter")
public class SessionFilter implements Filter {

    String NO_LOGIN = "您还未登陆";

    String[] includerUrls = new String[] {"templates/index.html","js/publicFunction.js","js/jquery.js","landing","registPhone"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        
        String url = request.getRequestURI();
        
        System.out.println("filter url:"+url);
        boolean needFilter = isNeedFilter(url,request.getContextPath());
        
        if(url.equals("/znck/jumpToUrl")){
            if("phone".equals(request.getParameter("url"))){
                needFilter = false;
            }
        }
        if(!needFilter){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            if(session!=null&&session.getAttribute("user")!=null){
                filterChain.doFilter(request, response);
            }else{
                String requestType = request.getHeader("X-Requested-With");
                
                if(requestType != null && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(this.NO_LOGIN);
                }else{
                    response.sendRedirect(request.getContextPath()+"/templates/index.html");
                }
                return;
            }
        }
    }

    public boolean isNeedFilter(String url,String projectName) {
        for (String includerUrl : includerUrls) {
            if ((projectName+"/"+includerUrl).equals(url)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void destroy() {

    }
}
