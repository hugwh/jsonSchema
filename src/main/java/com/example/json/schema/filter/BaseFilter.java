package com.example.json.schema.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 基础过滤类
 *
 * @author: huangwh
 * @mail huangwh@txtws.com
 * @date: 2018-11-23 14:42
 */
public class BaseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }

    void outRespon(ServletResponse servletResponse, String result) throws IOException {
        ServletOutputStream outputStream = servletResponse.getOutputStream();
        outputStream.write(result.getBytes());
        outputStream.flush();
    }
}
