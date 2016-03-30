package com.skimmdit.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.skimmdit.dao.mapper.UserMapper;
import com.skimmdit.representation.UserRepresentation;

public interface UserDao {
	
	@SqlUpdate("CREATE TABLE PUBLIC.usertable(UserName Varchar(25) not null unique,Email CHAR(25),Password VARCHAR(25),userid integer identity not null primary key)")
	void createUserTable();
	
	@SqlUpdate("insert into PUBLIC.usertable values(:username, :email, :password,null)")
	void addUser(@Bind("username") String name,@Bind("email") String email, @Bind("password") String password);
	
	@Mapper(UserMapper.class)
	@SqlQuery("select * from PUBLIC.usertable")
	List<UserRepresentation> showAllUser();
	
	@SqlQuery("select count(*) from PUBLIC.usertable where username= :username and password= :password")
	int authenticateUser(@Bind("username") String username,@Bind("password") String password);
	
	@SqlQuery("select count(*) from PUBLIC.usertable where username= :username")
	int checkUser(@Bind("username") String username);
	
	
}

