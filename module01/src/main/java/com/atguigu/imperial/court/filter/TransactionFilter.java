package com.atguigu.imperial.court.filter;

import com.atguigu.imperial.court.util.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lifeifan
 * @Description
 * @create 2022-05-11 20:53
 */
public class TransactionFilter implements Filter {
    // 声明集合保存静态资源扩展名
    private static Set<String> staticResourceExtNameSet;

    static {
        staticResourceExtNameSet = new HashSet<>();
        staticResourceExtNameSet.add(".png");
        staticResourceExtNameSet.add(".jpg");
        staticResourceExtNameSet.add(".css");
        staticResourceExtNameSet.add(".js");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String servletPath = request.getServletPath();

        if (servletPath.contains(".")) {
            String extName = servletPath.substring(servletPath.indexOf("."));
            if(staticResourceExtNameSet.contains(extName)){
                filterChain.doFilter(request,response);
                return ;
            }
        }

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            filterChain.doFilter(request, response);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("回滚失败！");
            }
            String message = e.getMessage();
            request.setAttribute("systemMessage", message);
            request.getRequestDispatcher("/").forward(request, response);
        } finally {
            JDBCUtils.releaseConnection(connection);
        }

    }

    @Override
    public void destroy() {
    }
}
