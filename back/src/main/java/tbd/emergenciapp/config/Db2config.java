package tbd.emergenciapp.config;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement

public class Db2config {

    @Bean(name = "dataSourceProperties")
    @ConfigurationProperties("db2.datasource")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }



    @Bean(name = "dataSource")
    @ConfigurationProperties("db2.datasource.configuration")
    public DataSource dataSource(@Qualifier("dataSourceProperties") DataSourceProperties dataSourceProperties){
        return  dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }



}
