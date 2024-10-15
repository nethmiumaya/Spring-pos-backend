package lk.ijse.spring_pos_system;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import lk.ijse.spring_pos_system.config.WebAppRootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Appinitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {return new Class[]{WebAppRootConfig.class};}

    @Override
    protected Class<?>[] getServletConfigClasses() {return new Class[]{WebAppRootConfig.class};}

    @Override
    protected String[] getServletMappings() {return new String[]{"/"};}

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        registration.setMultipartConfig(new MultipartConfigElement("/tmp/"));
    }
}
