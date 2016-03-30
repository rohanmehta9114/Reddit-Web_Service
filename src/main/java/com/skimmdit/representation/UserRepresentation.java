package com.skimmdit.representation;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRepresentation {
	
	@NotBlank
	@Length(min=2, max=255)
	private String name;
	
	@NotBlank
	@Length(min=2, max=255)
	private int userid;
	
	private String email;
	
	
	public UserRepresentation(int userid, String name,String email)
	{
		
		this.name=name;
		this.email=email;
		this.userid=userid;
	}
	
	@JsonProperty
	public String getName() {
		return name;
	}
	
	@JsonProperty
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
