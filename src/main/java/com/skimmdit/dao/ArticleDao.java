package com.skimmdit.dao;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.skimmdit.dao.mapper.*;
import com.skimmdit.representation.*;

public interface ArticleDao {
	
	
	@SqlUpdate("CREATE TABLE PUBLIC.articletable(articleid Integer Identity primary key,articlename CHAR(25) not null,link VARCHAR(25) not null,userid Integer not null)")
	void createArticleTable();
	
	@SqlUpdate("insert into PUBLIC.articletable(articleid,articlename,link,userid) values(null,:articlename,:link,:userid)")
	void addArticle(@Bind("articlename") String articlename,@Bind("link") String link,@Bind("userid") int userid);
	
	
	@SqlUpdate("insert into PUBLIC.votes(userid,articleid,votelike,votedislike) values(:userid,:articleid,:like,:dislike)")
	void defaultVote(@Bind("userid") int userid,@Bind("articleid") int articleid,@Bind("like") int votelike,@Bind("dislike") int votedislike);
	
	@SqlQuery("select max(articleid) from articletable")
	int getArticleId();
	
	@Mapper(ArticleMapper.class)
	@SqlQuery("select a.*,sum(votelike)-sum(votedislike) as votediff   from articletable a,votes b where a.articleid=b.articleid and a.articleid=:id group by a.articleid,a.articlename,a.link,a.userid")
	List<ArticleRepresentation>  getArticleById(@Bind("id") int id);
	
	
	@SqlQuery("select userid from usertable where username= :username")
	int getUserId(@Bind("username") String username);
	
	@SqlUpdate("insert into PUBLIC.userarticle values(:userid,:artid)")
	void mapArticle(@Bind("userid") int userid,@Bind("artid") int artid);	
	
	@Mapper(ArticleMapper.class)
	@SqlQuery("select a.*,sum(votelike)-sum(votedislike) as votediff   from articletable a,votes b where a.articleid=b.articleid group by a.articleid,a.articlename,a.link,a.userid order by votedif")
	List<ArticleRepresentation> showAllArticle();
	
}
