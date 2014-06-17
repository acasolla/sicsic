package it.abc.sicsic.security;


import it.abc.sicsic.Constants;
import it.abc.sicsic.view.backing.tree.TreeCore;

import it.abc.sicsic.view.backing.tree.TreeItem;
import it.abc.sicsic.view.backing.tree.TreeNode;

import it.soulsoftware.utils.FacesUtils;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FactoryFinder;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.event.ValueChangeEvent;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class ShiroSecurityFilter implements Filter {
    private static Logger log = Logger.getLogger(ShiroSecurityFilter.class);



    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        log.debug("doFilter!");
        Configuration config = Configuration.getInstance();
        getFacesContext(servletRequest, servletResponse);

        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
        String context = httpRequest.getContextPath();
        String uri = httpRequest.getRequestURI().split(context)[1];

        User us = (User)FacesUtils.getSessionMapValue(Constants.Key.USER);

    
        log.debug("user=" + us + " requested uri=" + uri);

        if (uri.endsWith(config.getLoginPage()) || us != null) {
            log.debug("REDIRECTING");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {

            httpResponse.sendRedirect(httpRequest.getContextPath() + config.getLoginPage());
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //super();
    }

    public void destroy() {
    }

    private FacesContext getFacesContext(ServletRequest request, ServletResponse response) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            return facesContext;
        }

        FacesContextFactory contextFactory =
            (FacesContextFactory)FactoryFinder.getFactory("javax.faces.context.FacesContextFactory");

        LifecycleFactory lifecycleFactory =
            (LifecycleFactory)FactoryFinder.getFactory("javax.faces.lifecycle.LifecycleFactory");

        Lifecycle lifecycle = lifecycleFactory.getLifecycle("DEFAULT");

        ServletContext servletContext = ((HttpServletRequest)request).getSession().getServletContext();
        facesContext = contextFactory.getFacesContext(servletContext, request, response, lifecycle);
        InnerFacesContext.setFacesContextAsCurrentInstance(facesContext);
        if (null == facesContext.getViewRoot()) {
            facesContext.setViewRoot(new UIViewRoot());
        }

        return facesContext;
    }


    private static abstract class InnerFacesContext extends FacesContext {
        protected static void setFacesContextAsCurrentInstance(FacesContext facesContext) {
            FacesContext.setCurrentInstance(facesContext);
        }
    }


    public void changeValue(ValueChangeEvent valueChangeEvent) {

        log.debug("valueChangeEvent=" + valueChangeEvent);

        log.debug("oldValue=" + valueChangeEvent.getOldValue());

        log.debug("newValue=" + valueChangeEvent.getNewValue());
    }
    



}
