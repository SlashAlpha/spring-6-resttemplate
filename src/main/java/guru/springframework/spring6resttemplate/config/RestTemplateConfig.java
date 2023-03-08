package guru.springframework.spring6resttemplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfig {
    @Value("${rest.template.rootUrl}")
    public  String rootUrl;


    @Value("${rest.template.username}")
    String username;

    @Value("${rest.template.password}")
    String password;

    @Bean
    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer){

        assert rootUrl!=null;

//        RestTemplateBuilder builder= configurer.configure(new RestTemplateBuilder());
//        DefaultUriBuilderFactory defaultUriBuilderFactory=new DefaultUriBuilderFactory(rootUrl);
//
//      RestTemplateBuilder restTemplateBuilderWithAuth=  builder.basicAuthentication(username,password);

      return configurer
              .configure(new RestTemplateBuilder())
              .uriTemplateHandler(new DefaultUriBuilderFactory(rootUrl))
              .basicAuthentication(username,password);
//        return  restTemplateBuilderWithAuth.uriTemplateHandler(defaultUriBuilderFactory);
    }
}
