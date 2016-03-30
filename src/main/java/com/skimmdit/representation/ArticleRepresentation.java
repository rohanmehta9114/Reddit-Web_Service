package com.skimmdit.representation;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleRepresentation {

	@NotBlank
	private int articleid;
	
	@NotBlank
	private String articlename;
	
	@NotBlank
	private String link;
	
	@NotBlank
	private int vote;
	
	public ArticleRepresentation(int articleid,String articlename,String link,int vote)
	{
		this.articleid=articleid;
		this.articlename=articlename;
		this.link=link;
		this.vote=vote;
	}
	
	@JsonProperty
	public int getArticleid() {
		return articleid;
	}
	
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
	
	@JsonProperty
	public String getArticlename() {
		return articlename;
	}
	
	public void setArticlename(String articlename) {
		this.articlename = articlename;
	}
	
	@JsonProperty
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	@JsonProperty
	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}
	
}
