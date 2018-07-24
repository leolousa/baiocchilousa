package br.com.baiocchilousa.algamoney.api.token;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * Classe filtro para ler o refresh_token do cookie
 * e mandar na requisição
 * @author leolo
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)//Prioridade alta para analisar a requisição antes de todos
public class RefreshTokenCookiePreProcessorFilter implements Filter{


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        
        if ("/oauth/token".equalsIgnoreCase(req.getRequestURI())
                && "refresh_token".equals(req.getParameter("grant_type"))
                && req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if(cookie.getName().equals("refreshToken")) {
                    String refreshToken = cookie.getValue();
                    req = new MyServletRequestWrapper(req, refreshToken);
                }
            }
        }
        chain.doFilter(req, response);
    }

    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void destroy() {
    }
    
    
    
    /**
     * Classe extra para empacotar e aterar o request
     * @author leolo
     *
     */
    static class MyServletRequestWrapper extends HttpServletRequestWrapper {

        private String refreshToken;
        
        public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
            super(request);
            this.refreshToken = refreshToken;
        }
        
        //Cria o novo mapa na requisição
        @Override
        public Map<String, String[]> getParameterMap() {
            ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
            map.put("refresh_token", new String[] { refreshToken });
            map.setLocked(true);
            return map;
        }
        
    }

    /* EXPLICAÇÃO:
     * 
     * Então, primeiro fazemos uma requisição para http://localhost:8080/oauth/token,
     * com isso obtemos o Access Token, é ele que vai nos permitir usar a API.
     * Então iremos conseguir utilizar a API com ele até o tempo em que ele expirar.
     * Quando isso acontecer, iremos buscar um novo Access Token.
     * 
     * Até então, quando buscamos nosso primeiro Access Token, recebemos o Refresh
     * Token no cookie, então estávamos fazendo uma requisição para http://localhost:8080/oauth/token
     * adicionando no body: grant_type:refresh_token <REFRESH_TOKEN>, onde em <REFRESH_TOKEN> colocávamos
     * o valor do refresh token que está no cookie.
     * 
     * Após a alteração desta aula, não precisamos mais passar o Refresh Token no body, pois a API já irá pegar
     * o Refresh Token no Cookie. Não iremos passar o Refresh Token na requisição mais.
     * Isso por questões de segurança, pois este token no Body pode ser acessível na aplicação cliente.
     * Deixando o Refresh Token apenas no Cookie, acessível apenas via HTTP (quando for para produção será HTTPS, ou seja,
     * realmente seguro) o que impossibilitaria alguém interceptar e ter o Refresh Token.
     * 
     * Veja que desmarcamos grant_type do body no Postman, ou seja, ele não é enviado na requisição.
     * O Access Token fica na requisição no Header Authorization, o Refresh Token irá ficar apenas no Cookie.
     * 
     * */
    
}
