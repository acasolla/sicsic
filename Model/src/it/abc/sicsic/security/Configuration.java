package it.abc.sicsic.security;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

@XmlRootElement(name="SIC-CFG")
public class Configuration
{
    private static Logger logger = Logger.getLogger(Configuration.class);
  private static Configuration instance;
  private static Configuration defaultvalues = new Configuration();
  private String applicationCode;
  private Class profilerConnectorClass;
  private Class profilerDirectClass;
  private Class profileAccessorClass;
  private Class dbConnectionProviderClass;
  private Class authenticationProviderClass;
  private String jndiDataSource;
  private String userDataSource;
  private String passwordDataSource;
  private String driverDataSource;
  private String urlDataSource;
  private String schemaDataSource;
  private String jndiProfiler;
  private String hostLDAP;
  private String userLDAP;
  private String passwordLDAP;
  private String rootLDAP;
  private String loginPage;
  private String profilePage;
  private String homePage;

  public static synchronized Configuration getInstance()
  {
    if (instance == null)
    {
      URL url = Configuration.class.getResource("/sic-cfg.xml");
      if (url != null) {
        instance = load(url);
        logger.debug("found configuration in: /sic-cfg.xml");
      } else {
        try {
          instance = (Configuration)Class.forName("Configuration").newInstance();
          logger.debug("found configuration in: class Configuration");
        } catch (Exception e) {
          String path = System.getProperty("sic-cfg-path");
          if (path != null) {
            instance = load(new File(path));
            logger.debug("found configuration in: [" + path + "]");
          } else {
            instance = new Configuration();
            logger.warn("Configuration file not found use defaults");
          }
        }
      }
    }
    return instance;
  }

  public static Configuration load(URL url)
  {
    return ((Configuration)JAXB.unmarshal(url, Configuration.class));
  }

  public static Configuration load(File file)
  {
    return ((Configuration)JAXB.unmarshal(file, Configuration.class));
  }

  public static Configuration load(InputStream stream)
  {
    return ((Configuration)JAXB.unmarshal(stream, Configuration.class));
  }

  public void save(File file)
  {
    JAXB.marshal(this, file);
  }

  public void save(OutputStream stream)
  {
    JAXB.marshal(this, stream);
  }

  @XmlElement(required=false)
  public Class getProfilerConnectorClass()
  {
    return ((this.profilerConnectorClass != null) ? this.profilerConnectorClass : defaultvalues.profilerConnectorClass);
  }

  public void setProfilerConnectorClass(Class profilerConnectorClass)
  {
    this.profilerConnectorClass = profilerConnectorClass;
  }

  @XmlElement(required=false)
  public Class getProfileAccessorClass()
  {
    return ((this.profileAccessorClass != null) ? this.profileAccessorClass : defaultvalues.profileAccessorClass);
  }

  public void setProfileAccessorClass(Class profileAccessorClass)
  {
    this.profileAccessorClass = profileAccessorClass;
  }

  @XmlElement(required=false)
  public Class getDbConnectionProviderClass()
  {
    return ((this.dbConnectionProviderClass != null) ? this.dbConnectionProviderClass : defaultvalues.dbConnectionProviderClass);
  }

  public void setDbConnectionProviderClass(Class dbConnectionProviderClass)
  {
    this.dbConnectionProviderClass = dbConnectionProviderClass;
  }

  @XmlElement(required=false)
  public Class getAuthenticationProviderClass()
  {
    return ((this.authenticationProviderClass != null) ? this.authenticationProviderClass : defaultvalues.authenticationProviderClass);
  }

  public void setAuthenticationProviderClass(Class authenticationProviderClass)
  {
    this.authenticationProviderClass = authenticationProviderClass;
  }

  @XmlElement(required=false)
  public String getJndiDataSource()
  {
    return ((this.jndiDataSource != null) ? this.jndiDataSource : defaultvalues.jndiDataSource);
  }

  public void setJndiDataSource(String jndiDataSource)
  {
    this.jndiDataSource = jndiDataSource;
  }

  @XmlElement(required=false)
  public String getUserDataSource()
  {
    return ((this.userDataSource != null) ? this.userDataSource : defaultvalues.userDataSource);
  }

  public void setUserDataSource(String userDataSource)
  {
    this.userDataSource = userDataSource;
  }

  @XmlElement(required=false)
  public String getPasswordDataSource()
  {
    return ((this.passwordDataSource != null) ? this.passwordDataSource : defaultvalues.passwordDataSource);
  }

  public void setPasswordDataSource(String passwordDataSource)
  {
    this.passwordDataSource = passwordDataSource;
  }

  @XmlElement(required=false)
  public String getDriverDataSource()
  {
    return ((this.driverDataSource != null) ? this.driverDataSource : defaultvalues.driverDataSource);
  }

  public void setDriverDataSource(String driverDataSource)
  {
    this.driverDataSource = driverDataSource;
  }

  @XmlElement(required=false)
  public String getUrlDataSource()
  {
    return ((this.urlDataSource != null) ? this.urlDataSource : defaultvalues.urlDataSource);
  }

  public void setUrlDataSource(String urlDataSource)
  {
    this.urlDataSource = urlDataSource;
  }

  @XmlElement(required=false)
  public String getJndiProfiler()
  {
    return ((this.jndiProfiler != null) ? this.jndiProfiler : defaultvalues.jndiProfiler);
  }

  public void setJndiProfiler(String jndiProfiler)
  {
    this.jndiProfiler = jndiProfiler;
  }

  @XmlElement(required=false)
  public String getHostLDAP()
  {
    return ((this.hostLDAP != null) ? this.hostLDAP : defaultvalues.hostLDAP);
  }

  public void setHostLDAP(String hostLDAP)
  {
    this.hostLDAP = hostLDAP;
  }

  @XmlElement(required=false)
  public String getUserLDAP()
  {
    return ((this.userLDAP != null) ? this.userLDAP : defaultvalues.userLDAP);
  }

  public void setUserLDAP(String userLDAP)
  {
    this.userLDAP = userLDAP;
  }

  @XmlElement(required=false)
  public String getPasswordLDAP()
  {
    return ((this.passwordLDAP != null) ? this.passwordLDAP : defaultvalues.passwordLDAP);
  }

  public void setPasswordLDAP(String passwordLDAP)
  {
    this.passwordLDAP = passwordLDAP;
  }

  @XmlElement(required=false)
  public String getRootLDAP()
  {
    return ((this.rootLDAP != null) ? this.rootLDAP : defaultvalues.rootLDAP);
  }

  public void setRootLDAP(String rootLDAP)
  {
    this.rootLDAP = rootLDAP;
  }

  @XmlElement(required=false)
  public String getApplicationCode()
  {
    return ((this.applicationCode != null) ? this.applicationCode : defaultvalues.applicationCode);
  }

  public void setApplicationCode(String applicationCode)
  {
    this.applicationCode = applicationCode;
  }

  @XmlElement(required=false)
  public String getLoginPage()
  {
    return ((this.loginPage != null) ? this.loginPage : defaultvalues.loginPage);
  }

  public void setLoginPage(String loginPage)
  {
    this.loginPage = loginPage;
  }

  @XmlElement(required=false)
  public String getProfilePage()
  {
    return ((this.profilePage != null) ? this.profilePage : defaultvalues.profilePage);
  }

  public void setProfilePage(String profilePage)
  {
    this.profilePage = profilePage;
  }

  @XmlElement(required=false)
  public String getHomePage()
  {
    return ((this.homePage != null) ? this.homePage : defaultvalues.homePage);
  }

  public void setHomePage(String homePage)
  {
    this.homePage = homePage;
  }

  @XmlElement(required=false)
  public Class getProfilerDirectClass()
  {
    return ((this.profilerDirectClass != null) ? this.profilerDirectClass : defaultvalues.profilerDirectClass);
  }

  public void setProfilerDirectClass(Class profilerDirectClass)
  {
    this.profilerDirectClass = profilerDirectClass;
  }

  public String getSchemaDataSource()
  {
    return ((this.schemaDataSource != null) ? this.schemaDataSource : defaultvalues.schemaDataSource);
  }

  public void setSchemaDataSource(String schemaDataSource)
  {
    this.schemaDataSource = schemaDataSource;
  }

  static
  {
    defaultvalues.setSchemaDataSource("");
    defaultvalues.setApplicationCode("test");
    defaultvalues.setLoginPage("/faces/login.jsf");
    defaultvalues.setProfilePage("/faces/private/profile.jsf");
    defaultvalues.setHomePage("/faces/test.jsf");
  }
}