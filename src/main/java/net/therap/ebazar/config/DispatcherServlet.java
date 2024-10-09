package net.therap.ebazar.config;

import net.therap.ebazar.web.filter.AuthenticationFilter;
import net.therap.ebazar.web.filter.SiteMeshFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * @author hasin.raiyan
 * @since 3/23/21
 */
public class DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                WebMvcConfig.class,
                JPAConfig.class
        };
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{
                new AuthenticationFilter(),
                new SiteMeshFilter()
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}