package com.aaron.winkwebapp.restful.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource(value="classpath:application.properties",ignoreResourceNotFound=true)
@ComponentScan(basePackages={"com.aaron.winkwebapp.core"})
public class PersistenceConfig {
	
	@Autowired
	private Environment env;
	
/*	@Value("${init-db:false}")
    private String initDatabase;*/

	
	@Bean
	public DataSourceTransactionManager transactionManager() throws SQLException {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(this.dataSource());
		return transactionManager;
	}

	
	/*@Bean
	public DataSource dataSource()	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}*/
	
	@Bean(initMethod="init",destroyMethod="close")
	public DataSource dataSource() throws SQLException {
		DruidDataSource bean = new DruidDataSource();
		bean.setDriverClassName("com.mysql.jdbc.Driver");
		bean.setUrl("jdbc:mysql://"+env.getProperty("db.ip")+":" + env.getProperty("db.port")+"/" 
				+env.getProperty("db.name")+"?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
		bean.setUsername(env.getProperty("db.user"));
		bean.setPassword(env.getProperty("db.pwd"));
		bean.setInitialSize(10);
		bean.setMinIdle(3);
		bean.setMaxActive(20);
		bean.setMaxWait(60000);
		bean.setTimeBetweenEvictionRunsMillis(60000);
		bean.setMinEvictableIdleTimeMillis(300000);
		bean.setValidationQuery("SELECT 'x'");
		bean.setTestWhileIdle(true);
		bean.setTestOnBorrow(false);
		bean.setTestOnReturn(false);
		bean.setFilters("stat");
		return bean;
	}
	
	/*@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) 
	{
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("db.sql"));
		dataSourceInitializer.setDatabasePopulator(databasePopulator);
		dataSourceInitializer.setEnabled(Boolean.parseBoolean(initDatabase));
		return dataSourceInitializer;
	}	*/
}
