package com.sys.bo.config.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailurHandler implements AuthenticationFailureHandler {
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException exception)
            throws IOException, ServletException {
        req.setAttribute("email", req.getParameter("email"));



		if (exception instanceof AuthenticationServiceException) {
			System.out.println("####1111#######");

		} else if(exception instanceof BadCredentialsException) {
			System.out.println("####22222#######");
		} else if(exception instanceof LockedException) {
			System.out.println("####33333#######");

		} else if(exception instanceof DisabledException) {
			System.out.println("####44444#######");

		}  else if(exception instanceof CredentialsExpiredException) {
			System.out.println("####55555#######");

		}  else if(exception instanceof AuthenticationException) {
			System.out.println("####66666#######");
		}



        //req.getRequestDispatcher("/login_view?error=true").forward(req, res);
    }

}
