package net.therap.ebazar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

/**
 * @author hasin.raiyan
 * @since 3/23/21
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"net.therap.ebazar"})
public class JPAConfig {

    @Bean
    public LocalEntityManagerFactoryBean emfBean() {
        LocalEntityManagerFactoryBean localEmfBean = new LocalEntityManagerFactoryBean();
        localEmfBean.setPersistenceUnitName("JPA");

        return localEmfBean;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(emf);
        jpaTransactionManager.setEntityManagerFactory(emf);

        return jpaTransactionManager;
    }
}