package com.skimmdit.resources;

import io.dropwizard.auth.Auth;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;

import com.codahale.metrics.annotation.Timed;
import com.skimmdit.dao.ArticleDao;
import com.skimmdit.representation.ArticleRepresentation;


@Path("/skimmdit/articles/")
@Produces(value = MediaType.APPLICATION_JSON)
public class ArticleResource {

	
	private final ArticleDao articleDao;
	
	public ArticleResource(DBI jdbi)
	{
		articleDao=jdbi.onDemand(ArticleDao.class);
	}
	
	
	@Path("/create/articletable")
	@GET
	@Timed
	@Produces(value = MediaType.TEXT_PLAIN)
	public String createArticleTable()
	{
		articleDao.createArticleTable();
		return "Article Table Succesfully Created";
	}
	
	

	@Path("/createArticle")
	@POST
	@Produces(value = MediaType.TEXT_PLAIN)
	public String createArticle(@QueryParam("articlename") String articlename, @QueryParam("link") String link,@Auth String username) throws SQLException
	{ 
	int rs1=articleDao.getUserId(username);
	articleDao.addArticle(articlename,link,rs1);
	int rs=articleDao.getArticleId();
	articleDao.defaultVote(rs1, rs, 1,0);
	return "Article Successfully Created";
	}
	
	@Path("/getArticleById/{id}")
	@GET
	public Response getArticleById(@PathParam("id") int id) 
	{ 
		List<ArticleRepresentation> art=articleDao.getArticleById(id);
		return Response.ok(art).build();
	}
	
	
	@Path("/showAllArticle")
	@GET
	@Timed
	public Response showAllArticle()
	{ 
		List<ArticleRepresentation> art=articleDao.showAllArticle();
		return Response.ok(art).build();
	}
}
