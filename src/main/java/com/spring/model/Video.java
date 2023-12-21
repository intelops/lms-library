package com.spring.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@Table(name = "video")
public class Video implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
	private int userId;
	@Column
	private int courseId;
	@Column
	private int courseDuration;
	
    @OneToMany(fetch =FetchType.EAGER,mappedBy="video",cascade = CascadeType.ALL) 
	private Set<VideoReport> videoreport;
    @OneToOne(fetch = FetchType.EAGER,mappedBy="video",cascade = CascadeType.ALL) 
   	private VideoLike videolike;
    @OneToMany(fetch =FetchType.EAGER,mappedBy="video",cascade = CascadeType.ALL) 
   	private Set<BookMark> bookmark;
   // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(fetch =FetchType.EAGER,mappedBy="video",cascade = CascadeType.ALL) 
   	private Set<UserProgress> userprogress;
   
	public int getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(int courseDuration) {
		this.courseDuration = courseDuration;
	}
	public Set<UserProgress> getUserprogress() {
		return userprogress;
	}
	public void setUserprogress(Set<UserProgress> userprogress) {
		this.userprogress = userprogress;
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

	public Set<VideoReport> getVideoreport() {
		return videoreport;
	}
	public void setVideoreport(Set<VideoReport> videoreport) {
		this.videoreport = videoreport;
	}
	public VideoLike getVideolike() {
		return videolike;
	}
	public void setVideolike(VideoLike videolike) {
		this.videolike = videolike;
	}
	public Set<BookMark> getBookmark() {
		return bookmark;
	}
	public void setBookmark(Set<BookMark> bookmark) {
		this.bookmark = bookmark;
	}
    
    // Add other properties like title, description, etc.

    // Add getters and setters
}