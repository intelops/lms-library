package com.spring.dto;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

public class LibraryRequest {

	private int id;

	private int userId;
	private int courseId;
	private int videoTopicId;
	private String bookMarkDuration;
	private String title;
	private boolean liked;
	private int reportId;
	private int pausedDuration;
	private int totalDuration;

	private String ebook;
	
	public String getEbook() {
		return ebook;
	}
	public void setEbook(String ebook) {
		this.ebook = ebook;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getVideoTopicId() {
		return videoTopicId;
	}
	public void setVideoTopicId(int videoTopicId) {
		this.videoTopicId = videoTopicId;
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
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	
	
}
