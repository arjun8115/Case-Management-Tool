package com.lawtendo.cmtool.application.config;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		 RequestMatcher csrfRequestMatcher = new RequestMatcher() {
		      
		      private Pattern allowedMethods = Pattern.compile("^(HEAD|TRACE|OPTIONS)$");
		      
		      private AntPathRequestMatcher[] requestMatchers = {
		    		    new AntPathRequestMatcher("/login"),
		    		    new AntPathRequestMatcher("/logout"),
		    		    new AntPathRequestMatcher("/google-auth"),
		    		    new AntPathRequestMatcher("/webindex"),
		    		    new AntPathRequestMatcher("/user/**"),
		    		    new AntPathRequestMatcher("/case/**"),
		    		    new AntPathRequestMatcher("/contact/**"),
		    		    new AntPathRequestMatcher("/doc/**"),
		    		    new AntPathRequestMatcher("/event/**"),
		    		    new AntPathRequestMatcher("/htmlToPdf/**"),
		    		    new AntPathRequestMatcher("/invoice/**")
		    		  };

		      @Override
		      public boolean matches(HttpServletRequest request) {
		    	  
		    	  if (allowedMethods.matcher(request.getMethod()).matches()) {
		    	      return false;
		    	    }

		    	  for (AntPathRequestMatcher rm : requestMatchers) {
		    	      if (rm.matches(request)) { return false; }
		    	    }
		              
		          
		          return true;
		      }

		    }; 

		    http.csrf()
		        .requireCsrfProtectionMatcher(csrfRequestMatcher)
		        .and()
		      .authorizeRequests()
		        .antMatchers(
		            "/login",
		            "/google-auth",
		            "/webindex",
		            "/logout",
		            "/signup",
		            "/user/**",
		            "/case/**",
		            "/event/**",
		            "/invoice/**",
		            "/contact/**",
		            "/htmlToPdf/**",
		            "/doc/**",
		            "/invoice/**")
		            .permitAll();
		    
		    return;
        
        
    }
}
