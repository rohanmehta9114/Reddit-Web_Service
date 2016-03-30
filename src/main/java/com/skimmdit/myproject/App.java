package com.skimmdit.myproject;

import org.skife.jdbi.v2.DBI;






import com.skimmdit.resources.ArticleResource;
import com.skimmdit.resources.UserResource;
import com.skimmdit.resources.VoteResource;

import io.dropwizard.Application;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

class App extends Application<AppConfiguration>
{
	public static void main(String arg[]) throws Exception
	{
		
		new App().run(new String[] { "server","skimmdit.yml" });
		
	}

	@Override
	public void initialize(Bootstrap<AppConfiguration> arg0) {
		// TODO Auto-generated method stub
		//System.out.println("Hello");
	}

	@Override
	public void run(AppConfiguration arg0, Environment arg1) throws Exception {
		
		// TODO Auto-generated method stub
		 final DBIFactory factory = new DBIFactory();
		 final DBI jdbi = factory.build(arg1, arg0.getDataSourceFactory(), "hsql");
		 arg1.jersey().register(new UserResource(jdbi));
		 arg1.jersey().register(new ArticleResource(jdbi));
		 arg1.jersey().register(new VoteResource(jdbi));
		 arg1.jersey().register(new BasicAuthProvider<String>(new AppAuthenticator(jdbi), "Web Service Realm"));
		// arg1.jersey().register(new BasicAuthProvider<Boolean>(new AppAuthenticator(jdbi), "Web Service Realm"));
		//arg1.jersey().register(AuthFactory.binder(new BasicAuthFactory<String>(new AppAuthenticator(jdbi),"SUPER SECRET STUFF",String.class)));
	}
	
	
}
