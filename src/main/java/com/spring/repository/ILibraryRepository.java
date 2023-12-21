package com.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.dto.AddItem;
import com.spring.model.BookMark;
import com.spring.model.Collection;
import com.spring.model.EBook;
import com.spring.model.Holiday;
import com.spring.model.ReviewsAndRating;
import com.spring.model.UserSearch;
import com.spring.model.UserProgress;
import com.spring.model.Video;
import com.spring.model.VideoLike;
import com.spring.model.VideoReport;
//import com.spring.serviceImpl.EBook;


@Repository
public interface ILibraryRepository  extends PagingAndSortingRepository<Collection,Integer>{
	@Query("select a from Collection a where  a.userId=:userId and a.isActive=1") 
	List<Collection> getAllList(@Param("userId")int userId);

	@Modifying
	@Query("UPDATE CourseCollection SET courseId=:courseId   WHERE id=:collectionId") 
	void addToCollection(@Param("courseId")int courseId,@Param("collectionId")int collectionId);
	@Modifying
	@Query("UPDATE CourseCollection SET isActive=0  WHERE id=:id") 
	void removeCourseFromCollection(@Param("id")int id);
	@Modifying
	@Query("UPDATE Collection SET isActive=0  WHERE id=:id and userId=:userId") 
	void deleteCollection(@Param("id")int id,@Param("userId") int userId);
	@Query("select a from BookMark a join Video v on v.id=a.videoId where  v.courseId=:courseId and  v.userId=:userId  and a.bookMarkDuration=:bookMarkDuration and a.videoTopicId=:videoTopicId and  a.isActive=1") 
	BookMark checkBookmark(@Param("courseId") int courseId,@Param("bookMarkDuration")String bookMarkDuration,@Param("videoTopicId") int videoTopicId,@Param("userId") int userId);
	@Query("select b from BookMark b join Video v on v.id=b.videoId where  v.userId=:userId and v.courseId=:courseId and  b.videoTopicId=:videoTopicId and  b.isActive=1") 
	List<BookMark> getBookMarkList(@Param("userId") int userId,@Param("courseId") int courseId,@Param("videoTopicId") int videoTopicId);
	@Query("select a from VideoLike a join Video v on v.id=a.videoId where v.courseId=:courseId and   v.userId=:userId and a.videoTopicId=:videoTopicId and  a.liked=1") 
	VideoLike likeVideo(@Param("courseId") int courseId,@Param("videoTopicId") int videoTopicId,@Param("userId") int userId);
	@Query("select a from VideoReport a join Video v on v.id=a.videoId where v.courseId=:courseId and v.userId=:userId and  a.videoTopicId=:videoTopicId  and  a.reported=1") 
	VideoReport reportVideo(@Param("courseId") int courseId,@Param("videoTopicId") int videoTopicId,@Param("userId") int userId);
	@Query("select a from Video a    where  a.userId=:userId and a.courseId=:course") 
	Video getAllVideoList(@Param("userId")int userId,@Param("course") int course);
	//@Query("select a from EBook a join Video v on v.id=a.videoId where and a.courseId=:courseId") 
	//com.spring.model.EBook getBook(@Param("courseId") int courseId);
	@Query("select a from Video a where  a.userId=:userId   and  a.courseId=:courseId") 
	Video getVideo(@Param("userId")  int userId,@Param("courseId")  int courseId);
	@Query("select a from EBook a where  a.userId=:userId  and  a.courseId=:courseId") 
	EBook getBook(@Param("courseId")int courseId,@Param("userId") int userId);
 @Query("SELECT v FROM Video v join v.userprogress u on v.id=u.videoId  where v.userId=:userId ")
 	List<Video> getUserProgress(@Param("userId") int userId);
// @Query("SELECT v from UserProgress v  where  v.videoTopicId=:videoTopicId ")
//UserProgress getUserProgressDuration(@Param("userId") int userId,@Param("videoTopicId") int videoTopicId);
 @Query("SELECT  v FROM Video v   where v.userId=:userId and v.courseId=:courseId ")
Video getUserProgressDuration(@Param("userId") int userId,@Param("courseId") int courseId);
@Query("select a from UserProgress a join Video v on v.id=a.videoId where v.courseId=:courseId and v.userId=:userId and  a.videoTopicId=:videoTopicId") 
List<UserProgress> getUserProgressByTopicId(@Param("courseId")int courseId,@Param("videoTopicId") int videoTopicId,@Param("userId") int userId);
@Query("select r from ReviewsAndRating r where r.courseId=:courseId")
Page<ReviewsAndRating> getAllReviewsAndRating(@Param("courseId")int courseId, Pageable paging);
@Query(value="select name from lms_library.usersearch where replace(name, '-', '_') RLIKE :course and userId=:userId GROUP BY name\r\n"
		+ " ORDER BY count(*) DESC",nativeQuery=true)
List<String> getSearchList(@Param("course")String course,@Param("userId") int userId);
@Query("select h from Holiday h where h.region like :region")
List<Holiday> getHolidaysList(@Param("region")String region);

}
