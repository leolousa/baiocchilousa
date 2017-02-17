package br.com.baiocchilousa.wg.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Tomcat desligado
		HibernateUtil.getFabricaDeSessoes().close();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//Tomcat ligado
		HibernateUtil.getFabricaDeSessoes();
		
	}

}
