/*
package br.com.dotcom.sbmvc.data;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("prd")
public class DataConfigurationPrd {

  @Bean
  public BasicDataSource prdDataSource() throws URISyntaxException {
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


}
*/