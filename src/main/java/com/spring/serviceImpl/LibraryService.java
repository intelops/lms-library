package com.spring.serviceImpl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import com.spring.dto.AllCourseRequest;
import javax.transaction.Transactional;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.dto.AddItem;
import com.spring.dto.ApiResponse;
import com.spring.dto.CourseRequest;
import com.spring.dto.DataDto;
import com.spring.dto.DropDownData;
import com.spring.dto.DropDownDataDto;
import com.spring.dto.LibraryRequest;
import com.spring.dto.LmsResponse;
import com.spring.dto.MyResponse;
import com.spring.exception.CartItemNotExistException;
import com.spring.iservice.ILibraryService;
import com.spring.model.BookMark;
import com.spring.model.Collection;
import com.spring.model.CourseCollection;
import com.spring.model.EBook;
import com.spring.model.Holiday;
import com.spring.model.ReviewsAndRating;
import com.spring.model.UserDto;
import com.spring.model.UserProgress;
import com.spring.model.UserRoleDto;
import com.spring.model.UserSearch;
import com.spring.model.Video;
import com.spring.model.VideoLike;
import com.spring.model.VideoReport;
import com.spring.repository.ILibraryRepository;
import com.spring.service.BaseServiceImpl;
@Service
@Transactional
public class LibraryService extends BaseServiceImpl implements ILibraryService {
	@Autowired
	private ILibraryRepository repository;;


	@Override
	@Transactional
	public Collection createCollection(String name, int userId) {

		Collection collection=new Collection();
		try {

			//	cart.setProductId(addCart.getProductId());
			LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
			collection.setUserId(userId);
			collection.setName(name);
			//cart.setQuantity(addCart.getQuantity());
			collection.setCreatedDate(now);
			genericDao.create(collection);
		}
		catch (Exception e) {
			throw e;
		}
		return collection;
	}

	@Override
	@Transactional
	public List<Map<String, Object>> getAllCollectionCourses(int userId) {
		// TODO Auto-generated method stub
		//	List<Collection> collection=null;
		List<Map<String, Object>> mapList=new ArrayList<Map<String,Object>>();

		try {
			List<Collection> collection=repository.getAllList(userId);

			for(int i=0;i<collection.size();i++) {
				System.out.println(collection.get(i).getId());
				Map<String,Object> courselist=new HashMap<String,Object>();
				List<Object> objectList=new ArrayList<Object>();
				courselist.put("name",collection.get(i).getName());
				courselist.put("createdDate",collection.get(i).getCreatedDate());
				courselist.put("id", collection.get(i).getId());	
				List<CourseCollection> courseCollectionList=collection.get(i).getCoursecollection();
				for(int j=0;j<courseCollectionList.size();j++) {
					//	Map<String,Object> course=getCourseById(orderList.get(i).getProductId()).getBody();
					CourseRequest req=new CourseRequest();
					req.setCourse(courseCollectionList.get(j).getCourseId());
					//List<Integer> list=new ArrayList<Integer>();
					//list.add(courseCollectionList.get(j).getCourseId());
					//req.setCourse(list);
					LmsResponse course=(getAllCourse(req)).getBody();
					//	course
					objectList.add(course.getList());


				}
				courselist.put("courseList", objectList);
				mapList.add(courselist);
			}
			return mapList;
		}
		catch (Exception e) {
			throw e;
		}

	}

	@Override
	@Transactional
	public String removeCourseFromCollection(int id) {
		// TODO Auto-generated method stub
		try {
			CourseCollection collection=(CourseCollection) genericDao.findOne(id);

			repository.removeCourseFromCollection(id);
		}
		catch (Exception e) {
			throw e;
		}
		return "course has been removed ";
	}
	@Override
	@Transactional
	public void deleteCollection(int id,int userId) {
		repository.deleteCollection(id,userId);
	}

	@Override
	public JSONObject getAllPurchasedCourses(int userId) {
		// TODO Auto-generated method stub
		try {
			MyResponse course=getAllPurchased(userId).getBody();
			ResponseEntity<JSONObject> courseList=getAllCoursesBycategory(course.getMySet());

			return courseList.getBody();
			//courseList.add(course.get("course"));
		}
		catch (Exception e) {
			throw e;
		}

	}

	@Override
	public String addToCollection(int courseId, int collectionId,int userId) {
		try {
			List<Integer> courseList=new ArrayList<Integer>();
			genericDao.setClazz(Collection.class);
			Collection collection=(Collection) genericDao.findOne(collectionId);
			if (collection.equals(null)) {
				return "Collection id is invalid ";
			}// TODO Auto-generated method stub
			else {
				List<CourseCollection> list=collection.getCoursecollection();
				System.out.println(list.contains(courseId));
				for(int i=0;i<list.size();i++) {
					courseList.add(list.get(i).getCourseId());
				}

				if(courseList.contains(courseId)) {
					return "course already exist ";       
				}
				else {

					CourseCollection course=new CourseCollection();
					LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
					course.setCollectionId(collectionId);
					course.setCourseId(courseId);
					course.setCreatedDate(now);
					genericDao.create(course);
				}
			}
			//}
			return "course added to collection ";
			//return collection;
		}
		catch (Exception e) {
			throw e;
		}
	}
	@Override
	@Transactional
	public String saveBookmark(LibraryRequest bookMark, int userId) {
		try {
			BookMark result=repository.checkBookmark(bookMark.getCourseId(),bookMark.getBookMarkDuration(),bookMark.getVideoTopicId(),userId);
			Video videoSave=repository.getVideo(userId,bookMark.getCourseId());
			if(result==null) {
				BookMark save=new BookMark();
				if(videoSave==null) {
					Video video=new Video();
					video.setCourseId(bookMark.getCourseId());
					video.setUserId(userId);
					//video.setVideoTopicId(bookMark.getVideoTopicId());
					genericDao.create(video);
					save.setVideoId(video.getId());
				}
				else {
					save.setVideoId(videoSave.getId());
				}
				save.setVideoTopicId(bookMark.getVideoTopicId());
				save.setBookMarkDuration(bookMark.getBookMarkDuration());
				save.setTitle(bookMark.getTitle());
				genericDao.create(save);
				return "bookmark is created";
			}
			else {
				return "bookmark is already created";
			}
		}
		catch (Exception e) {
			throw e;
		}

	}
	@Transactional 
	@Override
	public BookMark deleteBookMark(BookMark bookmark) {
		// TODO Auto-generated method stub
		try {
			genericDao.setClazz(BookMark.class);
			BookMark bookMark=(BookMark)genericDao.findOne(bookmark.getId());
			if(bookMark.isActive()==true) {
				bookMark.setActive(false);
				genericDao.update(bookMark);
				return bookMark;
			}

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return null;

	}
	@Override
	@Transactional
	public Map<String, Object> getBookMarkList(int userId,LibraryRequest req) {
		try {

			UserDto user=null;
			List<BookMark> bookMarkList =  repository.getBookMarkList(userId,req.getCourseId(),req.getVideoTopicId());
			List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
			for(int i=0;i<bookMarkList.size();i++) {
				user=getUserById(userId);
				List<String> result = new ArrayList<String>();
				Map<String, Object> aMap = new HashMap<String, Object>();
				Map<String, Object> userMap = new HashMap<String, Object>();
				aMap.put("id", bookMarkList.get(i).getId());
				aMap.put("videoTopicId",req.getVideoTopicId());
				aMap.put("user", user);
				aMap.put("bookMarkDuration", bookMarkList.get(i).getBookMarkDuration());
				aMap.put("title", bookMarkList.get(i).getTitle());
				//aMap.put("courseId", bookMarkList.get(i).getCourseId());

				//	userMap.put("name",user.getName());
				//	userMap.put("id",user.getId());
				//aMap.put("user",user.getName());
				list.add(aMap);

			}	
			Map<String, Object> resultList = new HashMap<String, Object>();
			resultList.put("list",list);
			return  resultList;	
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public ApiResponse likeVideo(LibraryRequest like, int userId) {
		try {
			VideoLike result=repository.likeVideo(like.getCourseId(),like.getVideoTopicId(),userId);
			Video videoSave=repository.getVideo(userId,like.getCourseId());

			if(result==null) {
				VideoLike save=new VideoLike();
				if(videoSave==null) {

					Video video=new Video();
					video.setCourseId(like.getCourseId());
					video.setUserId(userId);
					//video.setVideoTopicId(like.getVideoTopicId());
					genericDao.create(video);
					save.setVideoId(video.getId());
				}
				else {
					save.setVideoId(videoSave.getId());
				}
				save.setLiked(true);
				save.setVideoTopicId(like.getVideoTopicId());
				genericDao.create(save);
				return new ApiResponse(true,"liked", 200);
			}
			else {
				return new ApiResponse(true,"already liked", 400);
			}
		}
		catch (Exception e) {
			throw e;
		}
	}
	@Override
	public ApiResponse reportVideo(LibraryRequest report, int userId) {
		try {
			//	VideoReport result=repository.reportVideo(report.getCourseId(),report.getVideoTopicId(),report.getReportId(),userId);
			Video videoSave=repository.getVideo(userId,report.getCourseId());
			//if(result==null) {
			VideoReport save=new VideoReport();
			if(videoSave==null) {

				Video video=new Video();
				video.setCourseId(report.getCourseId());
				video.setUserId(userId);
				//		video.setVideoTopicId(report.getVideoTopicId());
				genericDao.create(video);
				save.setVideoId(video.getId());
			}
			else {
				save.setVideoId(videoSave.getId());
			}
			save.setVideoTopicId(report.getVideoTopicId());
			save.setReported(true);
			save.setReportId(report.getReportId());
			genericDao.create(save);
			return new ApiResponse(true,"report is Saved", 200);
			//}
			//else {
			//return new ApiResponse(false,"report is already created");
			//}
		}
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ApiResponse userProgress(LibraryRequest userProgress, int userId) {
		try {
			//VideoReport result=repository.reportVideo(report.getVideoTopicId(),userId);
			Video videoSave=repository.getVideo(userId,userProgress.getCourseId());

			//if(result==null) {
			UserProgress save=new UserProgress();
			if(videoSave==null) {
				Video video=new Video();
				video.setCourseId(userProgress.getCourseId());
				video.setUserId(userId);
				//video.setVideoTopicId(userProgress.getVideoTopicId());
				genericDao.create(video);
				save.setVideoId(video.getId());
			}
			else {
				save.setVideoId(videoSave.getId());
			}
			save.setVideoTopicId(userProgress.getVideoTopicId());
			save.setPausedDuration(userProgress.getPausedDuration());
			save.setTotalDuration(userProgress.getTotalDuration());
			genericDao.create(save);
			return new ApiResponse(true,"progress is Saved",200);


		}
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Map<String, Object> getCoursesById(CourseRequest reqs,int userId) {
		List<Map<String, Object>> mapList=new ArrayList<Map<String,Object>>();
		Map<String,Object> courselist=new HashMap<String,Object>();

		try {
			//List<Collection> collection=repository.getAllList(userId);
			//VideoLike like=repository.getVideoLike(userId,reqs.getCourse());
			//VideoReport report=repository.getVideoReport(userId,reqs.getCourse());
			//BookMark bookmark=repository.getBookmark(userId,reqs.getCourse());
			//UserProgress
			Video videoList=repository.getAllVideoList(userId,reqs.getCourse());
			//	for(int i=0;i<videoList.size();i++) {
			//	System.out.println(collection.get(i).getId());
			//Map<String,Object> courselist=new HashMap<String,Object>();
			//List<Object> objectList=new ArrayList<Object>();
			//courselist.put("name",collection.get(i).getName());
			//courselist.put("createdDate",collection.get(i).getCreatedDate());
			//courselist.put("id", collection.get(i).getId());	
			//List<CourseCollection> courseCollectionList=collection.get(i).getCoursecollection();
			//for(int j=0;j<courseCollectionList.size();j++) {
			//	Map<String,Object> course=getCourseById(orderList.get(i).getProductId()).getBody();
			LmsResponse courseById=getCourseById(reqs.getCourse()).getBody();
			Map<String,Object> course=courseById.getResponse();
			//	course
			//	objectList.add(course.get("course"));


			//}
			course.put("user",videoList.getUserId());
			courselist.putAll(course);
			//courselist.put("bookmark",videoList.getBookmark());
			//	courselist.put("like",videoList.getVideolike());
			//courselist.put("report",videoList.getVideoreport());
			//courselist.put("userprogress",videoList.getUserprogress());
			//courselist.put("user",videoList.getUserId());
			//mapList.add(courselist);
			return courselist;
		}
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ApiResponse saveEbook(LibraryRequest book, int userId) {
		try {
			EBook result=repository.getBook(book.getCourseId(),userId);
			//Video videoSave=repository.getVideo(book.getBookMarkDuration(),book.getVideoTopicId(),userId,book.getCourseId());
			//
			if(result!=null) {
				//EBook bookSave=new EBook();
				//	if(videoSave==null) {
				//Video video=new Video();
				//bookSave.setCourseId(book.getCourseId());
				//bookSave.setUserId(userId);
				result.seteBook(book.getEbook());
				genericDao.update(result);

				return new ApiResponse(true,"updated",200);
			}
			else {
				EBook bookSave=new EBook();
				bookSave.setCourseId(book.getCourseId());
				bookSave.setUserId(userId);
				bookSave.seteBook(book.getEbook());
				genericDao.create(bookSave);
				return new ApiResponse(true,"book saved",200);
			}

		}
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Map<String, Object> getCourseTopicById(CourseRequest reqs, int userId) {
		List<Map<String, Object>> mapList=new ArrayList<Map<String,Object>>();
		Map<String,Object> courselist=new HashMap<String,Object>();

		try {
			//List<Collection> collection=repository.getAllList(userId);
			//VideoLike like=repository.getVideoLike(userId,reqs.getCourse());
			//VideoReport report=repository.getVideoReport(userId,reqs.getCourse());
			//BookMark bookmark=repository.getBookmark(userId,reqs.getCourse());
			//UserProgress
			//	Video videoList=repository.getAllVideoList(userId,reqs.getCourse(),);
			VideoLike likeList=repository.likeVideo(reqs.getCourse(),reqs.getCourseTopic(),userId);
			VideoReport reportList=repository.reportVideo(reqs.getCourse(),reqs.getCourseTopic(),userId);
			List<BookMark> bookmarkList=repository.getBookMarkList(userId, reqs.getCourse(),reqs.getCourseTopic());
			List<UserProgress> userProgress=repository.getUserProgressByTopicId(reqs.getCourse(),reqs.getCourseTopic(),userId);
			//	for(int i=0;i<videoList.size();i++) {
			//	System.out.println(collection.get(i).getId());
			//Map<String,Object> courselist=new HashMap<String,Object>();
			//List<Object> objectList=new ArrayList<Object>();
			//courselist.put("name",collection.get(i).getName());
			//courselist.put("createdDate",collection.get(i).getCreatedDate());
			//courselist.put("id", collection.get(i).getId());	
			//List<CourseCollection> courseCollectionList=collection.get(i).getCoursecollection();
			//for(int j=0;j<courseCollectionList.size();j++) {
			//	Map<String,Object> course=getCourseById(orderList.get(i).getProductId()).getBody();
			//	LmsResponse courseById=getCourseById(reqs.getCourseTopic()).getBody();
			Map<String,Object> course=new HashMap<String,Object>();
			course.put("userprogress",userProgress);
			course.put("like",likeList);
			//	course
			//	objectList.add(course.get("course"));


			//}
			//	course.put("user",videoList.getUserId());
			//courselist.putAll(course);
			course.put("bookmark",bookmarkList);
			//courselist.put("like",videoList.getVideolike());
			course.put("report",reportList);
			//courselist.put("userprogress",videoList.getUserprogress());
			//courselist.put("user",videoList.getUserId());
			//mapList.add(courselist);
			return course;
		}
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	public double getCoursesActivity(int userId,int videoTopicId) {
		// TODO Auto-generated method stub
		try {
			//	LmsResponse courseById=getCourseById(reqs.getCourse()).getBody();
			MyResponse course=getAllPurchased(userId).getBody();
			//LmsResponse courseList=getAllCoursesDurationList(course.getMySet()).getBody();
			//	Set<Integer> list=course.getMySet();
			int totalCompletedDuration = 0;
			double totalCourseDuration = 0.0;
			List<Integer>  list= new ArrayList<>(course.getMySet());
			for(int i=0;i<list.size();i++) {
				int completedDuration = 0;
				Video  video= repository.getUserProgressDuration(userId,list.get(i));
				System.out.println(video.getId());

				if(video!=null){
					List<UserProgress> userProgress=new ArrayList<>(video.getUserprogress());
					for(int j=0;j<userProgress.size();j++) {
						System.out.println(list.get(i));

						int completedTopicDuration = userProgress.get(j).getTotalDuration() - userProgress.get(j).getPausedDuration();
						System.out.println("completedTopicDuration"+completedTopicDuration);

						completedDuration += completedTopicDuration;

					}
					System.out.println("completedDuration"+completedDuration);
					System.out.println("video.getCourseDuration()"+video.getCourseDuration());

					double completionPercentage = ((double) completedDuration / video.getCourseDuration()) * 100;	 System.out.println("completionPercentage"+completionPercentage);
					// Step 6: Accumulate total completed duration and total course duration
					totalCompletedDuration += completionPercentage;
					//  totalCourseDuration += video.getCourseDuration();
					System.out.println("totalCompletedDuration"+totalCompletedDuration);

				}
			}

			double overallCompletionPercentage = (totalCompletedDuration /list.size());
			//Map<String,Object> progress=null;
			//UserProgress  userProgress= repository.getUserProgressDuration(userId,videoTopicId);
			//return userProgress.getPausedDuration();
			return overallCompletionPercentage;
		}
		catch (Exception e) {
			throw e;
		}

	}

	@Override
	public ApiResponse getUserProgress(int userId) {
		// TODO Auto-generated method stub
		//return null;
		int courseCompleted=0;
		int totalDurationOfAllVideos=0;
		double completionPercentage=0.0;
		double averagCompletionPercentage=0.0;
		int count=0;
		try {
			//genericDao.setClazz(UserProgress);
			List<Video> video=repository.getUserProgress(userId);
			//List<U>
			MyResponse course=getAllPurchased(userId).getBody();
			LmsResponse courseList=getAllCoursesDurationList(course.getMySet()).getBody();
			List<Map<String,Object>> list=courseList.getList();
			for(int k=0;k<video.size();k++) {
				List<UserProgress>  userProgress= new ArrayList<>(video.get(k).getUserprogress());

				for(int i=0;i<userProgress.size();i++) {
					//	Set<UserProgress> userProgress=video.get(k).getUserprogress();

					// if(userProgress.get(i).getPausedDuration()>)
					for(int j=0;j<list.size();j++) {
						Map<String,Object> courseProgress=list.get(j);
						//System.out.println(video.get(k).getCourseId());
						System.out.println(course.getMySet().contains(Integer.parseInt(courseProgress.get("courseId").toString())));

						if(course.getMySet().contains(Integer.parseInt(courseProgress.get("courseId").toString()))) {
							System.out.println(video.get(k).getCourseId());
							int completedDuration =Integer.parseInt(courseProgress.get("duration").toString())-userProgress.get(i).getPausedDuration();
							System.out.println("completedDuration"+completedDuration);
							courseCompleted=courseCompleted+completedDuration;
							System.out.println("courseCompleted"+courseCompleted);
							completionPercentage=completionPercentage+(courseCompleted/Integer.parseInt(courseProgress.get("overallDuration").toString()))*100;
							System.out.println("completionPercentage"+completionPercentage);

							//	totalDurationOfAllVideos=totalDurationOfAllVideos+Integer.parseInt(courseProgress.get("duration").toString());
						}

						count++;
					}
					//count++;


				}

			}
			averagCompletionPercentage=completionPercentage/count;
			ApiResponse result=new ApiResponse(true,averagCompletionPercentage+"",200);
			return result;
		}
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> getRecommendationCourses(int userId) {
		// TODO Auto-generated method stub
		try {
			LmsResponse cartResponse= getCartItems(userId).getBody();
			List<Map<String, Object>> cartList=cartResponse.getList();
			List<Integer> cart=new ArrayList<Integer>();
			LmsResponse wishListResponse=getWishlistItems(userId).getBody();
			List<Map<String, Object>> wishList=wishListResponse.getList();
			List<Integer> wish=new ArrayList<Integer>();
			Set<Integer> batchMembersCourses = getAllHighestCourse(userId).getBody().getMySet();

			for(int i=0;i<cartList.size();i++) {
				cart.add(Integer.parseInt(cartList.get(i).get("id").toString()));
			}
			for(int j=0;j<wishList.size();j++) {
				wish.add(Integer.parseInt(wishList.get(j).get("id").toString()));
			}
			Set<Integer> combinedCourses = new HashSet<Integer>();;
			combinedCourses.addAll(cart);
			combinedCourses.addAll(wish);
			Set<Integer> filteredBatchCourses = batchMembersCourses.stream()
					.filter(course -> !combinedCourses.contains(course))
					.collect(Collectors.toSet());
			List<Map<String, Object>> recommendedCourses = getAllapplyRecommendationAlgorithm(filteredBatchCourses).getBody().getList();
			return recommendedCourses;
		}
		catch (Exception e) {
			throw e;
		}
	}
	@Override
	public List<String> getTopSearches(int userId) {
		// TODO Auto-generated method stub
		try {
			List<String> list=null;
			DropDownDataDto list1=getDropDownSetupData().getBody();
			List<DropDownData> response=list1.getData();
			String course=null;
			for(int x=0;x<response.size();x++) {
				if(response.get(x).getName().equalsIgnoreCase("course")) {
					List<DataDto>  courseList= response.get(x).getData();
					for (DataDto data : courseList) {
						course=course+"|"+data.getName();
					}
				}
			}
			System.out.println(course);
			list=repository.getSearchList(course, userId);
			return list;
		}
		catch (Exception e) {
			throw e;
		}

	}

	@Override
	public UserSearch createSearch(String name, int userId) {
		// TODO Auto-generated method stub
		UserSearch search=new UserSearch();
		search.setName(name);
		search.setUserId(userId);
		return search;
	}

	@Override
	public ReviewsAndRating createReviewsAndRatingService(ReviewsAndRating rating) {
		// TODO Auto-generated method stub
		try {
			//ReviewsAndRating ratingSaved=new ReviewsAndRating();
			genericDao.create(rating);

			//	ratingSaved.setUserId(userId);
			//	ratingSaved.setDescription(rating);
			return rating;

		}
		catch (Exception e) {
			throw e;
		}

	}
	@Override
	public List<ReviewsAndRating> getAllReviewsAndRatingService(int courseId,int userId) {
		// TODO Auto-generated method stub
		try {
			//genericDao.setClazz(ReviewsAndRating.class);
			Pageable paging = PageRequest.of(0,10,Sort.by(Direction.DESC,"id"));
			Page<ReviewsAndRating> pagedResult = repository.getAllReviewsAndRating(courseId,paging);
			List<ReviewsAndRating> list=pagedResult.getContent() ;
			UserDto user=getUserById(userId);
			List<UserRoleDto> role=user.getList();
			List<Integer> roleList=new ArrayList<Integer>();
			Map<String, Object> result = new HashMap<String, Object>();

			//			/for(int i=0;i<role.size();i++) {
			//roleList.add(role.get(i).getRoleId());
			//roleList.add(null)
			//}
			for(int j=0;j<list.size();j++) {
				//user=getUserById(list.get(j).getUserId());
				//List<UserRoleDto> roleRating=user.getList();
				//for(int k=0;k<role.size();k++) {
				//if(roleList.contains(role.get(k).getRoleId())) {
				result.put("id",list.get(j).getId());
				result.put("description",list.get(j).getDescription());
				result.put("rating",list.get(j).getRating());
				//result.put("linkedIn",list.get(j).getLinkedInLink());
				//result.put("imageLink",list.get(j).getImageUrl());
				result.put("userName",user.getFullName());



			}

			return list;
		}
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Holiday> getHolidaysList(String region) {
		// TODO Auto-generated method stub
		try {
			List<Holiday> list=repository.getHolidaysList(region);
			return list;
		}
		catch (Exception e) {
			throw e;
		}

	}
}