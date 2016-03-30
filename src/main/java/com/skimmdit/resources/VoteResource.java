package com.skimmdit.resources;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.skife.jdbi.v2.DBI;

import com.codahale.metrics.annotation.Timed;
import com.skimmdit.dao.VoteDao;


@Path("/skimmdit/votes")
public class VoteResource {

	private final VoteDao voteDao;
	public VoteResource(DBI jdbi)
	{
		voteDao=jdbi.onDemand(VoteDao.class);
		
	}
	
	@Path("/create/votestable")
	@GET
	@Timed
	@Produces(value = MediaType.TEXT_PLAIN)
	public String createVotesTable()
	{
		voteDao.createVotesTable();
		return "Votes Table Created";
	}
	
	@Path("/{articleid}/like")
	@GET
	@Timed
	@Produces(value = MediaType.TEXT_PLAIN)
	public String likeArticle(@PathParam("articleid") int articleid,@Auth String username)
	{
		int userid=voteDao.getUserId(username);
		
	
		if(voteDao.checkUserLike(userid, articleid)>0)
		{
			voteDao.removeUserLike(userid, articleid);
			if(voteDao.checkUserDisLike(userid, articleid)>0)
			{
				voteDao.removeUserDisLike(userid, articleid);
				voteDao.addUserLike(userid, articleid);
			}
		}
		else if(voteDao.checkUserDisLike(userid, articleid)>0)
		{
			voteDao.removeUserDisLike(userid, articleid);
			voteDao.addUserLike(userid, articleid);
		}
		else{
			voteDao.addUserLike(userid, articleid);
		}
		
		return "Operation Successfully Executed";
	}
	
	@Path("/{articleid}/dislike")
	@GET
	@Timed
	@Produces(value = MediaType.TEXT_PLAIN)
	public String dislikeArticle(@PathParam("articleid") int articleid,@Auth String username)
	{
		int userid=voteDao.getUserId(username);
		
		if(voteDao.checkUserLike(userid, articleid)>0)
		{
			
			if((voteDao.getLikeSize(userid, articleid)-voteDao.getDisLikeSize(userid, articleid))>1)
			{
				voteDao.removeUserLike(userid, articleid);
				voteDao.addUserDisLike(userid, articleid);	
			}
			else if((voteDao.getLikeSize(userid, articleid)-voteDao.getDisLikeSize(userid, articleid))==1)
			{
				voteDao.addUserDisLike(userid, articleid);
			}
			else
			{
				voteDao.removeUserDisLike(userid, articleid);
				voteDao.removeUserLike(userid, articleid);
				voteDao.addUserLike(userid, articleid);
			}
		}
		else if(voteDao.checkUserDisLike(userid, articleid)>0)
		{
			voteDao.removeUserDisLike(userid, articleid);	
		}
		else
		{
			if(voteDao.getLikeSize(userid, articleid)>0)
			{
				voteDao.addUserDisLike(userid, articleid);
			}
		}
		return "Operation Successfully Executed";
	}


	
	
}
