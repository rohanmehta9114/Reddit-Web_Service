package com.skimmdit.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;

import com.codahale.metrics.annotation.Timed;
import com.skimmdit.dao.UserDao;
import com.skimmdit.representation.UserRepresentation;
	
	@Path("/skimmdit/users")
	@Produces(value = MediaType.APPLICATION_JSON)
	public class UserResource {

		private final UserDao userDao;
		
		public UserResource(DBI jdbi)
		{
			userDao=jdbi.onDemand(UserDao.class);
		}
		
		@Path("/create/usertable")
		@GET
		@Timed
		@Produces(value = MediaType.TEXT_PLAIN)
		public String createUserTable()
		{
			userDao.createUserTable();
			return "Usertable Created";
		}
		
		@Path("/register")
		@POST
		@Timed
		@Produces(value = MediaType.TEXT_PLAIN)
		public String useradd(@QueryParam("username") String name,@QueryParam("email") String email,@QueryParam("password") String password)
		{       
		if(userDao.checkUser(name)>0)
		{
			return "Username Already Exist";
		}
		else
		{
		userDao.addUser(name, email, password);
		return "useradded";
		}
		}
		
		
		@Path("/showAllUser")
		@GET
		public Response showUsers()
		{ 
		List<UserRepresentation> usr=userDao.showAllUser();
		return Response.ok(usr).build();
		}
		
		
}
