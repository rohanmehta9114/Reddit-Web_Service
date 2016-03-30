package com.skimmdit.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface VoteDao {

	
	@SqlUpdate("CREATE TABLE PUBLIC.votes(userid Integer not null,articleid Integer NOT NULL,votelike tinyint,votedislike tinyint)")
	void createVotesTable();
	
	
	@SqlQuery("insert into votes values(:userid,:articleid,:like,:dislike)")
	int voteArticle(@Bind("userid") int userid,@Bind("articleid") int articleid,@Bind("like") int like,@Bind("dislike") int dislike);
	
	@SqlQuery("select userid from usertable where username= :username")
	int getUserId(@Bind("username") String username);

	//Like
	@SqlQuery("select count(*) from votes where votelike=1 and articleid= :articleid and userid=:userid")
	int checkUserLike(@Bind("userid") int userid,@Bind("articleid") int articleid);
	
	
	@SqlUpdate("delete from votes where votelike=1 and articleid= :articleid and userid=:userid ")
	void removeUserLike(@Bind("userid") int userid,@Bind("articleid") int articleid);
	

	@SqlUpdate("insert into votes values(:userid,:articleid,1,0)")
	void addUserLike(@Bind("userid") int userid,@Bind("articleid") int articleid);
	
	@SqlQuery("select count(*) from votes where articleid=:articleid and votelike=1")
	int getLikeSize(@Bind("userid") int userid,@Bind("articleid") int articleid);
	
	
	//Dislike
	@SqlQuery("select count(*) from votes where votedislike=1 and articleid= :articleid and userid=:userid")
	int checkUserDisLike(@Bind("userid") int userid,@Bind("articleid") int articleid);

	@SqlUpdate("Delete from votes where votedislike=1 and articleid= :articleid and userid=:userid ")
	void removeUserDisLike(@Bind("userid") int userid,@Bind("articleid") int articleid);
	
	@SqlUpdate("insert into votes values(:userid,:articleid,0,1)")
	void addUserDisLike(@Bind("userid") int userid,@Bind("articleid") int articleid);
	
	@SqlQuery("select count(*) from votes where articleid=:articleid and votedislike=1")
	int getDisLikeSize(@Bind("userid") int userid,@Bind("articleid") int articleid);
	
}
