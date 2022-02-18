package com.learning.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;



import com.learning.Exception.InvalidPasswordException;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name="reg",uniqueConstraints = { @UniqueConstraint(columnNames="username"),@UniqueConstraint(columnNames="email")})
public class Register implements Comparable<Register>{
	
	@Id
	@Column(name="regId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@NotBlank
	
	private String username;
	
	@NotBlank
	@Email
	private String email;
	

	@NotBlank
	private String address;
	
	
	@NotBlank
	
	private String password;
	
	
	
	
	
	


	public Register(String username,String email, String address,
			String password) {
		this.username = username;
		this.email = email;
		this.address = address;
		this.password = password;
	}
	@Override
	public int compareTo(Register o) {
		// TODO Auto-generated method stub
		return o.id.compareTo(this.getId());
	}

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="user_roles",joinColumns = @JoinColumn(name="regId"),inverseJoinColumns = @JoinColumn(name="roleId"))
	private Set<Role> roles=new HashSet<>();
	
	
	
	
		
	
}
