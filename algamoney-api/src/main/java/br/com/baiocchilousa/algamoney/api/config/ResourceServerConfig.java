package br.com.baiocchilousa.algamoney.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Classe de configuração de segurança com OAUTH2 da API
 * 
 * Oauth2 - fluxo Password Flow:
 * Autenticação e autorização utilizando o framework OAuth2 
 * no fluxo Password Flow
 * 
 * @author leolo
 *
 */
@EnableWebSecurity
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    
    //Método para criar um usuário em memória para testes
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            //.passwordEncoder(NoOpPasswordEncoder.getInstance())
            .withUser("admin").password("{noop}admin").roles("ROLE");
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/categorias").permitAll() //Libera a url para qualquer um que usar a API
                .anyRequest().authenticated() //Qualquer request deverá ser autenticado
                .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //Desabilita controle de sessão
            .csrf().disable(); //Desabilita o Cross-site request forgery
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.stateless(true);
    }
}
