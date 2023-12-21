package com.spring.iservice;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.spring.dto.AddItem;
import com.spring.dto.ApiResponse;
import com.spring.dto.CourseRequest;
import com.spring.dto.LibraryRequest;
import com.spring.model.BookMark;
import com.spring.model.Collection;
import com.spring.model.Holiday;
import com.spring.model.ReviewsAndRating;
import com.spring.model.UserSearch;
import com.spring.model.VideoLike;
import com.spring.model.VideoReport;

public interface ILibraryService {
	//void removeItemFromCart(int parseInt);
	//void removeItemsFromCart(int parseInt);
//	void deleteCartItems(int parseInt);
	JSONObject getAllPurchasedCourses(int userId);
	Collection createCollection(String string, int userId);
	String addToCollection(int parseInt, int userId, int userId2);
	List<Map<String, Object>> getAllCollectionCourses(int userId);
	String removeCourseFromCollection(int courseId);
	void deleteCollection(int id, int userId);
	//BookMark saveBookmark(BookMark bookMark);
	String saveBookmark(LibraryRequest bookMark, int userId);
	BookMark deleteBookMark(BookMark bookmark);
	Map<String, Object> getBookMarkList(int userId, LibraryRequest json);
	ApiResponse likeVideo(LibraryRequest like, int userId);
	ApiResponse reportVideo(LibraryRequest report, int userId);
	ApiResponse userProgress(LibraryRequest userProgress, int userId);
	Map<String, Object> getCoursesById(CourseRequest reqs,int userId);
	ApiResponse saveEbook(LibraryRequest book, int userId);
	Map<String, Object> getCourseTopicById(CourseRequest reqs, int userId);
	//Map<String, Object> getCoursesActivity(int userId);
	ApiResponse getUserProgress(int userId);
	double getCoursesActivity(int userId, int videoTopicId);
	List<Map<String, Object>> getRecommendationCourses(int userId);
	List<String> getTopSearches(int userId);
	UserSearch createSearch(String string, int userId);
	ReviewsAndRating createReviewsAndRatingService(ReviewsAndRating rating);
	List<ReviewsAndRating> getAllReviewsAndRatingService(int courseId, int userId);
	List<Holiday> getHolidaysList(String string);
	

}
