package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import org.hibernate.validator.constraints.URL;

import lombok.Data;

@Data
@Table(name = "food")
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String foodName;
    private Double foodCost;
    private String foodType;
    private String description;
    
    @URL
    private String foodPic;
}
