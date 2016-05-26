package com.e12e.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="users")
public class User implements Serializable{
	
	private static final long serialVersionUID = 7901707423259454726L;


	@Id
	@GeneratedValue //配置自动生成
	@Column(name="userId")
	private Integer userId;
	
	
	@Column(name="username",nullable=false,length=32)
	@NotEmpty(message = "{username.not.empty}")
	@Length(min = 6, message = "{username.length}")
	private String username;

	@Column(name="userpass",nullable=false,length=32)
	@NotEmpty(message = "{password.not.empty}")
	@Length(min = 6, message = "{password.length}")
	private String password;

	@Column(name="email",nullable=true,length=32)
	@Email(message = "{email.format}")
	private String email;

	@Column(name="age",nullable=true)
	@Range(min = 0, max = 120, message = "{age.range}")
	private Integer age;

	@Column(name="sex",nullable=true,length=1)
	@Pattern(regexp = "[男女]", message = "{sex.pattern}")
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
