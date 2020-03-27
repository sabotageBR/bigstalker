package com.bigstalker.servlet;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigstalker.session.CustomIdentity;

@WebServlet({"/redirecionar"})
public class Redirecionar extends HttpServlet {
     
    private static final long serialVersionUID = 1L;

    private @Inject CustomIdentity customIdentity;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(customIdentity.getUrlChallenge() != null) {
    		String url = customIdentity.getUrlChallenge();
    		customIdentity.setUrlChallenge(null);
    		System.out.println("******Redirecionado para: "+url);
    		response.sendRedirect(url);
    	}
    }
 
}