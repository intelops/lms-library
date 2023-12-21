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
@Table(name = "userprogress")
public class UserProgress implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userprogressId;
	@Column
	private int videoId;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime pausedAt=LocalDateTime.now(ZoneOffset.UTC);
	@Column
	private int pausedDuration;
	@Column
	private int totalDuration;
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

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	
	
	public int getUserprogressId() {
		return userprogressId;
	}

	public void setUserprogressId(int userprogressId) {
		this.userprogressId = userprogressId;
	}

	public LocalDateTime getPausedAt() {
		return pausedAt;
	}

	public void setPausedAt(LocalDateTime pausedAt) {
		this.pausedAt = pausedAt;
	}

	public int getPausedDuration() {
		return pausedDuration;
	}

	public void setPausedDuration(int pausedDuration) {
		this.pausedDuration = pausedDuration;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}

	
	
}
