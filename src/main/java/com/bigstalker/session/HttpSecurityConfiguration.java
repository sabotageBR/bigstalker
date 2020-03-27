
package com.bigstalker.session;

import javax.enterprise.event.Observes;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

public class HttpSecurityConfiguration {

	public void onInit(@Observes SecurityConfigurationEvent event) {

		SecurityConfigurationBuilder builder = event.getBuilder();
		builder.http().allPaths()
	    	.authenticateWith().form().authenticationUri("/login")
	    		.loginPage("/login").errorPage("/login").restoreOriginalRequest()
	    	.forPath("/javax.faces.resource/*").unprotected()
	    	.forPath("/resources/*").unprotected()
			.forPath("/home").unprotected()
			.forPath("/perfil/*").unprotected()
			.forPath("/limit").unprotected()
			.forPath("/redirecionar").unprotected()
			.forPath("/scripts/*").unprotected()
			.forPath("/images/*").unprotected()
			
			.forPath("/").unprotected()
			.forPath("/index.xhtml").unprotected()
			.forPath("/index").unprotected()
			.forPath("/favicon.ico").unprotected()
			.forPath("/public/*").unprotected()
		    .forPath("/logout").logout().redirectTo("/index");
		
		//builder.http().allPaths().unprotected();
				
	}
	

}