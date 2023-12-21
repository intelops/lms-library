package com.spring.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="holiday")
public class Holiday implements Serializable{
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	  @Column
	  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	    private LocalDateTime date=LocalDateTime.now(ZoneOffset.UTC);
		

	  //  @ManyToOne
	    //@JoinColumn(name = "product_id", referencedColumnName = "id")
	   

	    public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
		@Column 
	    private String region;
	    @Column
	    private String name;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	  
	
}
