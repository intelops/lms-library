 package com.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="collection")
public class Collection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdDate;

  //  @ManyToOne
    //@JoinColumn(name = "product_id", referencedColumnName = "id")
   

    @Column
    private int userId;
    @Column
    private String name;
  
@Column
private Boolean isActive;

@OneToMany(fetch = FetchType.EAGER,mappedBy="collection",cascade = CascadeType.ALL)

private List<CourseCollection> coursecollection;

	


	public List<CourseCollection> getCoursecollection() {
	return coursecollection;
}



public void setCoursecollection(List<CourseCollection> coursecollection) {
	this.coursecollection = coursecollection;
}



	public Boolean getIsActive() {
	return isActive;
}



public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
}



	
 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

  
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	


	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}
    
}
