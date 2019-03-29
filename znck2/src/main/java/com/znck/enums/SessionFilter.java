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

/**
 * 监听器方法 SessionFilter
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@WebFilter(filterName = "sessionFilter")
public class SessionFilter implements Filter {

	static String NO_LOGIN = "您还未登陆";

	String[] includerUrls = new String[] { "templates/index.html", "js/publicFunction.js", "js/jquery.js", "landing",
			"registPhone", "adminLanding" };

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

		boolean needFilter = isNeedFilter(url, request.getContextPath());

		String jumpUrl = "/znck/jumpToUrl";
		String phoneString = "phone";
		String urlString = "url";
		if (url.equals(jumpUrl)) {
			if (phoneString.equals(request.getParameter(urlString))) {
				needFilter = false;
			}
		}
		//测试阶段，直接通过
		needFilter = false;
		
		if (!needFilter) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			String userString = "user";
			String adminString = "admin";
			if (session != null && session.getAttribute(userString) != null) {
				filterChain.doFilter(request, response);
			} else if (session != null && session.getAttribute(adminString) != null) {
				filterChain.doFilter(request, response);
			} else {
				String requestType = request.getHeader("X-Requested-With");
				String xMLHttpRequestString = "XMLHttpRequest";
				if (requestType != null && xMLHttpRequestString.equals(requestType)) {
					response.getWriter().write(NO_LOGIN);
				} else {
					response.sendRedirect(request.getContextPath() + "/templates/index.html");
				}
				return;
			}
		}
	}

	public boolean isAdmin(String url) {
		return true;
	}

	public boolean isNeedFilter(String url, String projectName) {
		String[] urls = url.split("/");
		int sizeThree = 3;
		if (urls.length >= sizeThree) {
			String publicString = "public";
			int sizeTwo = 2;
			if (urls[sizeTwo].equals(publicString)) {
				return false;
			}
		}
		for (String includerUrl : includerUrls) {
			if ((projectName + "/" + includerUrl).equals(url)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void destroy() {

	}
}
