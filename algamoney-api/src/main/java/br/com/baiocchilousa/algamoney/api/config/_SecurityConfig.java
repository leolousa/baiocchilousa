package br.com.baiocchilousa.algamoney.api.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Classe de configuração de segurança BÁSICA da API
 * 
 * Basic Auth:
 * Autenticação básica onde sempre devemos enviar o usuário e a senha
 * no header da requisição
 * 
 * @author leolo
 *
 */
/*@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    
    //Método para criar um usuário em memória para testes
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
            .withUser("admin").password("admin").roles("ROLE");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/categorias").permitAll() //Libera a url para qualquer um que usar a API
                .anyRequest().authenticated() //Qualquer request deverá ser autenticado
                .and()
            .httpBasic().and()//Autenticação básica
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //Desabilita controle de sessão
            .csrf().disable(); //Desabilita o Cross-site request forgery
    }
}*/
