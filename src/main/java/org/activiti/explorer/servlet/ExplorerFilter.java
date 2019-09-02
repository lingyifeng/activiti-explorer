package org.activiti.explorer.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lingzong on 2019/9/2.
 */
public class ExplorerFilter implements Filter {

    private List<String> ignoreList = new ArrayList();

    public ExplorerFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.ignoreList.add("/ui");
        this.ignoreList.add("/VAADIN");
        this.ignoreList.add("/modeler.html");
        this.ignoreList.add("/editor-app");
        this.ignoreList.add("/service");
        this.ignoreList.add("/diagram-viewer");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        String path = req.getRequestURI().substring(req.getContextPath().length());
        int indexSlash = path.indexOf("/", 1);
        String firstPart = null;
        if(indexSlash > 0) {
            firstPart = path.substring(0, indexSlash);
        } else {
            firstPart = path;
        }

        if(this.ignoreList.contains(firstPart)) {
//            if("/service".equals(firstPart) && req.getRemoteUser() == null && (req.getSession(false) == null || req.getSession().getAttribute("_currentUser") == null)) {
//                ((HttpServletResponse)response).sendError(403);
//                return;
//            }

            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/ui" + path).forward(request, response);
        }

    }

    public void destroy() {
    }
}
