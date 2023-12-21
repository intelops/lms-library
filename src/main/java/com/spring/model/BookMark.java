package com.spring.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "bookmark")
public class BookMark implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int videoId;
	@Column
	private String bookMarkDuration;        
	@Column
	private String title;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime createdDate=LocalDateTime.now(ZoneOffset.UTC);
	@Column
	private boolean  isActive=true;
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "videoId", nullable = false, insertable = false, updatable = false)
	private Video video=null;
	@Column
	private int videoTopicId;

	public int getVideoTopicId() {
		return videoTopicId;
	}
	public void setVideoTopicId(int videoTopicId) {
		this.videoTopicId = videoTopicId;
	}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getBookMarkDuration() {
		return bookMarkDuration;
	}
	public void setBookMarkDuration(String bookMarkDuration) {
		this.bookMarkDuration = bookMarkDuration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
