package br.com.baiocchilousa.wg.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;


/*
 * http:localhost:8080/wg/[nomeRest]
 */

@ApplicationPath("rest")
public class WgResourceConfig extends ResourceConfig {
	public WgResourceConfig(){
		packages("br.com.baiocchilousa.wg.service");
	}
}
