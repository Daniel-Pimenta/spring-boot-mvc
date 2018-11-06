package br.com.dotcom.sbmvc;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataConfigurationPrd {

	/*
  @Bean
  public BasicDataSource basicDataSource() throws URISyntaxException {
      URI dbUri = new URI(System.getenv("DATABASE_URL"));

      String username = dbUri.getUserInfo().split(":")[0];
      String password = dbUri.getUserInfo().split(":")[1];
      String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

      System.out.println("******************************************************");
      System.out.println("****************** POSTGRES HEROKU *******************");
      System.out.println("URL :"+dbUrl);
      System.out.println("USER:"+username);
      System.out.println("PASS:"+password);
      System.out.println("******************************************************");
      

      //URL :jdbc:postgresql://ec2-174-129-18-98.compute-1.amazonaws.com:5432/d9snnj9ndabmuj
      //USER:pwslbgzqqvdstp
      //PASS:00603f5a23afcccb71b230d39e2f378c787cc6101b415c826ea6b3e955503f80

      
      BasicDataSource basicDataSource = new BasicDataSource();
      basicDataSource.setUrl(dbUrl);
      basicDataSource.setUsername(username);
      basicDataSource.setPassword(password);

      return basicDataSource;
  }
	*/

	@Bean
	public DataSource dataSource() throws URISyntaxException {
    URI dbUri = new URI(System.getenv("DATABASE_URL"));

    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		
    System.out.println("******************************************************");
    System.out.println("****************** POSTGRES HEROKU *******************");
    System.out.println("URL :"+dbUrl);
    System.out.println("USER:"+username);
    System.out.println("PASS:"+password);
    System.out.println("******************************************************");
    
    
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.postgresql.jdbc.driver");
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.POSTGRESQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
		adapter.setPrepareConnection(true);
		return adapter;
	}

}
