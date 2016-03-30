package com.skimmdit.myproject;

import org.skife.jdbi.v2.DBI;

import com.google.common.base.Optional;
import com.skimmdit.dao.UserDao;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class AppAuthenticator implements Authenticator<BasicCredentials,String>{

	private final UserDao userDao;
	public AppAuthenticator(DBI jdbi)
	{
		userDao=jdbi.onDemand(UserDao.class);
	}
	
	
	@Override
	public Optional<String> authenticate(BasicCredentials arg0)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
		if(userDao.authenticateUser(arg0.getUsername(),arg0.getPassword())==1)
		{
			return Optional.of(arg0.getUsername());
		}
		return Optional.absent();
	}

}
