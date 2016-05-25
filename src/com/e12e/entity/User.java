package com.e12e.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;



public class User {
	
	@NotEmpty(message="{username.not.empty}")
	@Length(min=6,message="{username.length}")
	private String username;
	
	@NotEmpty(message="{password.not.empty}")
	@Length(min=6,message="{password.length}")
	private String password;
	
	@Email(message="{email.format}")
	private String email;
	
	@Range(min=0,max=120,message="{age.range}")
	private Integer age;
			
	@Pattern(regexp="[男女]",message="{sex.pattern}")
	private String sex;

	public User() {
		
	}

	public User(String username, String password, String email, Integer age, String sex) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.sex = sex;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
