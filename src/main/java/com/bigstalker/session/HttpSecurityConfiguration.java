
package com.bigstalker.session;

import javax.enterprise.event.Observes;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

public class HttpSecurityConfiguration {

	public void onInit(@Observes SecurityConfigurationEvent event) {

		SecurityConfigurationBuilder builder = event.getBuilder();
//		builder.http().allPaths()
//	    	.authenticateWith().form().authenticationUri("/login").loginPage("/login").errorPage("/login").restoreOriginalRequest()
//	    	.forPath("/javax.faces.resource/*").unprotected()
//			.forPath("/rest/*").unprotected()
//			.forPath("/resources/*").unprotected()
//			.forPath("/img/*").unprotected()
//			.forPath("/favicon.ico").unprotected()
//			.forPath("/pages/public/*").unprotected()
//		    .forPath("/logout").logout().redirectTo("/index");
		
		
		builder.http().allPaths().unprotected();
				
	}
	

}