package com.spring.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="coursecollection")
public class CourseCollection implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdDate;

  //  @ManyToOne
    //@JoinColumn(name = "product_id", referencedColumnName = "id")
    @Column
    private int courseId;
    @Column
    private int collectionId;

   

@Column
private Boolean isActive;

@ManyToOne(cascade= CascadeType.ALL)
@JoinColumn(name = "collectionId", nullable = false, insertable = false, updatable = false)
private Collection collection;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}


public Boolean getIsActive() {
	return isActive;
}

public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
}

public LocalDateTime getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(LocalDateTime createdDate) {
	this.createdDate = createdDate;
}

public int getCourseId() {
	return courseId;
}

public void setCourseId(int courseId) {
	this.courseId = courseId;
}

public int getCollectionId() {
	return collectionId;
}

public void setCollectionId(int collectionId) {
	this.collectionId = collectionId;
}


}
