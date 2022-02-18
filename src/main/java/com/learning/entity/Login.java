package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="login")
public class Login {
  @Id
  @Email
  private String username;
  private String password;
  
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="regId")
  //@JsonIgnoreProperties({"hibernateLazyIntializer","handler"})
  @JsonProperty(access = Access.WRITE_ONLY)
  private Register register;
  
}
