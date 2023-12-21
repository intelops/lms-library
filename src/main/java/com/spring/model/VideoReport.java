package com.spring.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
@Table(name = "videoreport")
public class VideoReport implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int videoId;
	@Column
	private boolean reported;
	@Column
	private int reportId;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime createdDate=LocalDateTime.now(ZoneOffset.UTC);
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "videoId", nullable = false, insertable = false, updatable = false)
	private Video video;
	@Column
	private int videoTopicId;
	
	public int getVideoTopicId() {
		return videoTopicId;
	}
	public void setVideoTopicId(int videoTopicId) {
		this.videoTopicId = videoTopicId;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public boolean isReported() {
		return reported;
	}
	public void setReported(boolean reported) {
		this.reported = reported;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	
}
