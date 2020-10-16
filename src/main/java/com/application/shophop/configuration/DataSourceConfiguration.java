package com.application.shophop.configuration;


import com.application.shophop.constants.Branch;
import com.application.shophop.datasources.DefaultDbDetails;
import com.application.shophop.datasources.DemoSchoolDbDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.application.shophop.repository",
        transactionManagerRef = "transcationManager",
        entityManagerFactoryRef = "entityManager")
@EnableTransactionManagement
public class DataSourceConfiguration {

    @Autowired
    private DemoSchoolDbDetails demoSchoolDbDetails;

    @Autowired
    private DefaultDbDetails defaultDbDetails;

    @Bean
    @Primary
    public DataSource dataSource() {
        DataSourceRouting routingDataSource = new DataSourceRouting();

        HashMap<Enum,DataSource> sourceHashMap = new HashMap<Enum,DataSource>();

        sourceHashMap.put(Branch.demoschool,demoSchoolDatasource());
        sourceHashMap.put(Branch.defaults,defaultDatasource());

        routingDataSource.initDatasource(sourceHashMap);
        return routingDataSource;
    }

    public DataSource defaultDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(defaultDbDetails.getUrl());
        dataSource.setUsername(defaultDbDetails.getUsername());
        dataSource.setPassword(defaultDbDetails.getPassword());
        return dataSource;
    }

    public DataSource demoSchoolDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(demoSchoolDbDetails.getUrl());
        dataSource.setUsername(demoSchoolDbDetails.getUsername());
        dataSource.setPassword(demoSchoolDbDetails.getPassword());
        return dataSource;
    }

    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource()).packages("com.application.shophop.model","com.application.shophop.repository")
                .build();
    }

    @Bean(name = "transcationManager")
    public JpaTransactionManager transactionManager(
            @Autowired @Qualifier("entityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }

}
