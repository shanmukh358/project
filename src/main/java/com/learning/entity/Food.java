package com.learning.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="food",uniqueConstraints = { @UniqueConstraint(columnNames="foodname")})
public class Food implements Comparable<Food>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long foodId;
	
	@Size(max=50)
	@NotBlank
	private String foodname;
	
	private int foodcost;
	private String description;
	private String foodPic;

	@Override
	public int compareTo(Food o) {
		// TODO Auto-generated method stub
		return o.foodId.compareTo(this.getFoodId());
	}
	
	
	
}
