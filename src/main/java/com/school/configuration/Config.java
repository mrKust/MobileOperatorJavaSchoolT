package com.school.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Class needed to configure web app.
 * Here we set viewResolver bean for mapping views,
 * set connection to MySQL database, crates session factory to work with db,
 * sets up transaction manager, add resource folder
 */
@Configuration
@ComponentScan(basePackages = "com.school")
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
@Import(RabbitConfiguration.class)
public class Config implements WebMvcConfigurer {

    /**
     * Method register viewResolver bean.
     * It helps to write path to view in a short way
     * @return viewResolver
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver =
                new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }

    /**
     * Method registers dataSource.
     * With help of this bean we can connect to database with our settings and credentials
     * @return our database
     */
    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/db_ecare?useSSL=false");
            dataSource.setUser("admin");
            dataSource.setPassword("admin");
        }   catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        return dataSource;
    }

    /**
     * Method register LocalSessionFactoryBean.
     * It sets parameters to our sessions.
     * @return sessionFactory to connect with database
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.school.database.entity");

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect",
                "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "false");

        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    /**
     * Method register transactionManager.
     * This manager control transactions to our database
     * @return transactionManager which uses sessionFactory from sessionFactory bean
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager =
                new HibernateTransactionManager();

        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }

    /**
     * Method setting up resource folder.
     * @param registry - parameter where with add string with source of our resources
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
}
