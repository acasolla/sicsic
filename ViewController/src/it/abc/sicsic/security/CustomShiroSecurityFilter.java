package it.abc.sicsic.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import javax.faces.FactoryFinder;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
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

public class CustomShiroSecurityFilter
  implements Filter
{
      private static Logger log = Logger.getLogger(CustomShiroSecurityFilter.class);
  protected static final String ACCESSOR_NAME = "profileAccessor";
  

 
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
    throws IOException, ServletException
  {
      log.debug("doFilter!");
     Configuration config = Configuration.getInstance();
        
    getFacesContext(servletRequest, servletResponse);
     
     SecurityService ss = new SecurityService();
     boolean  logged = ss.login("f", "f");

    if ((!(servletRequest instanceof HttpServletRequest))) {
      filterChain.doFilter(servletRequest, servletResponse);
    } else {
      HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
      HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
      String context = httpRequest.getContextPath();
      String uri = httpRequest.getRequestURI().split(context)[1];
        
      if ( logged )
        if ((!(uri.endsWith(config.getLoginPage()))) && (!(uri.endsWith(config.getProfilePage()))))
          filterChain.doFilter(httpRequest, httpResponse);
        else
          httpResponse.sendRedirect(httpRequest.getContextPath() + config.getHomePage());

//      else if (accessor.isAuthenticated()) {
//        if (uri.endsWith(config.getProfilePage()))
//          filterChain.doFilter(httpRequest, httpResponse);
//        else
//          httpResponse.sendRedirect(httpRequest.getContextPath() + config.getProfilePage());
//
//      }
      else if (uri.endsWith(config.getLoginPage()))
        filterChain.doFilter(httpRequest, httpResponse);
      else
        httpResponse.sendRedirect(httpRequest.getContextPath() + config.getLoginPage());

    }

//  //  ProfileContext.getInstance().setCurrentProfileAccessor(null);
  }

@Override
  public void init(FilterConfig filterConfig)
    throws ServletException
  {
    Configuration conf = Configuration.getInstance();
    if (filterConfig != null) {
      Enumeration paramNames = filterConfig.getInitParameterNames();
      while (paramNames.hasMoreElements()) {
        String paramName = (String)paramNames.nextElement();
        String param = filterConfig.getInitParameter(paramName);
        String methodName = "set" + Character.toUpperCase(paramName.charAt(0)) + paramName.substring(1);
        Method method = null;
        Object arg = null;
        try {
          method = conf.getClass().getMethod(methodName, new Class[] { String.class });
          arg = param;
        } catch (NoSuchMethodException e) {
          try {
            method = conf.getClass().getMethod(methodName, new Class[] { Class.class });
            arg = Class.forName(param);
          } catch (NoSuchMethodException ex) {
            log.debug("parameter [" + paramName + "] not present in configuration", ex);
          } catch (ClassNotFoundException ex) {
            String message = "for parameter " + paramName + " class [" + param + "] not present";
            log.fatal(message, ex);
            throw new RuntimeException(message, ex);
          }
        }
        if ((method != null) && (arg != null))
          try {
            method.invoke(conf, new Object[] { arg });
          } catch (Exception e) {
            String message = "Error while invoka method [" + methodName + "] with agument [" + arg + "]";
            log.fatal(message, e);
            throw new RuntimeException(message, e);
          }
      }
    }
  }

  public void destroy()
  {
  }

  private FacesContext getFacesContext(ServletRequest request, ServletResponse response)
  {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    if (facesContext != null)
    {
      return facesContext;
    }

    FacesContextFactory contextFactory = (FacesContextFactory)FactoryFinder.getFactory("javax.faces.context.FacesContextFactory");

    LifecycleFactory lifecycleFactory = (LifecycleFactory)FactoryFinder.getFactory("javax.faces.lifecycle.LifecycleFactory");

    Lifecycle lifecycle = lifecycleFactory.getLifecycle("DEFAULT");

    ServletContext servletContext = ((HttpServletRequest)request).getSession().getServletContext();
    facesContext = contextFactory.getFacesContext(servletContext, request, response, lifecycle);
        InnerFacesContext.setFacesContextAsCurrentInstance(facesContext);
    if (null == facesContext.getViewRoot())
    {
      facesContext.setViewRoot(new UIViewRoot());
    }

    return facesContext;
  }


    private static abstract class InnerFacesContext extends FacesContext
  {
    protected static void setFacesContextAsCurrentInstance(FacesContext facesContext)
    {
      FacesContext.setCurrentInstance(facesContext);
    }
  }
}