package com.spring.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.AddItem;
import com.spring.dto.ApiResponse;
import com.spring.dto.CourseRequest;
import com.spring.dto.LibraryRequest;
import com.spring.iservice.ILibraryService;
import com.spring.model.BookMark;
import com.spring.model.Collection;
import com.spring.model.CourseCollection;
import com.spring.model.Holiday;
import com.spring.model.ReviewsAndRating;

import com.spring.model.UserSearch;
import com.spring.model.VideoLike;
import com.spring.model.VideoReport;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/library")
public class LibraryController extends BaseController{
	@Autowired
	private ILibraryService libraryService;

	static Logger logger = Logger.getLogger(LibraryController.class);
	@RequestMapping(value = "/createCollection", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<ApiResponse> createCollection(@RequestBody JSONObject request) throws SQLException {
			JSONObject json=new JSONObject();
		     //List<Order> orderList;
		
			try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
		  Collection collection= libraryService.createCollection(request.get("name").toString(),userId);
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	       
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service failed due to some exceptions", 500), HttpStatus.INTERNAL_SERVER_ERROR);
	}
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "collection is added",201), HttpStatus.CREATED);

		}
	@RequestMapping(value = "/addToCollection", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<ApiResponse> addToCollection(@RequestBody CourseCollection course) throws SQLException {
			JSONObject json=new JSONObject();
		     //List<Order> orderList;
		String response=null;
			try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
		response= libraryService.addToCollection(course.getCourseId(),course.getCollectionId(),userId);
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	       
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service failed due to some exceptions",500), HttpStatus.INTERNAL_SERVER_ERROR);
	}
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, response,201), HttpStatus.CREATED);

		}
	@RequestMapping(value = "/getAllPurchasedCourses", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<?> getAllPurchasedCourses(@RequestBody JSONObject req) throws SQLException {
			JSONObject json=new JSONObject();
		     JSONObject cartList;
			try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
		   cartList= libraryService.getAllPurchasedCourses(userId);
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	      
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<>("service failed due to some exceptions", HttpStatus.INTERNAL_SERVER_ERROR);
	}
			return new ResponseEntity<>(cartList, HttpStatus.OK);
	       
		}
	@RequestMapping(value = "/getHolidaysList", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<?> getHolidaysList(@RequestBody JSONObject req) throws SQLException {
			JSONObject json=new JSONObject();
		     List<Holiday> list;
			try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
		   list= libraryService.getHolidaysList(req.get("region").toString());
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	      
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<>("service failed due to some exceptions", HttpStatus.INTERNAL_SERVER_ERROR);
	}
			return new ResponseEntity<>(list, HttpStatus.OK);
	       
		}

	@RequestMapping(value = "/getAllCollectionCourses", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<?> getAllCollectionCourses(@RequestBody JSONObject req) throws SQLException {
			JSONObject json=new JSONObject();
		     List<Map<String, Object>> collectionList=new ArrayList<Map<String, Object>>();
			try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
				collectionList= libraryService.getAllCollectionCourses(userId);
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	      
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<>("service failed due to some exceptions", HttpStatus.INTERNAL_SERVER_ERROR);
	}
			return new ResponseEntity<>(collectionList, HttpStatus.OK);
	       
		}
	@RequestMapping(value = "/getCoursesById", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<?> getCoursesById(@RequestBody CourseRequest reqs) throws SQLException {
			JSONObject json=new JSONObject();
		    Map<String, Object> collectionList=new HashMap<String, Object>();
			try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
				collectionList= libraryService.getCoursesById(reqs,userId);
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	      
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<>("service failed due to some exceptions", HttpStatus.INTERNAL_SERVER_ERROR);
	}
			return new ResponseEntity<>(collectionList, HttpStatus.OK);
	       
		}
	@RequestMapping(value = "/getCoursesActivity", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<?> getCoursesActivity(@RequestBody CourseRequest reqs) throws SQLException {
			JSONObject json=new JSONObject();
		    double duration=0;
			Map<String,Object> data=new HashMap<String,Object>();

			try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				//int userId=1;
				duration= libraryService.getCoursesActivity(reqs.getInstitute(),reqs.getCourseTopic());
				data.put("Completed", duration);
				data.put("Incompleted",100.0-duration);
				data.put("NotYetStarted", 0);
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	      
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<>("service failed due to some exceptions", HttpStatus.INTERNAL_SERVER_ERROR);
	}
			return new ResponseEntity<>(data, HttpStatus.OK);
	       
		}
	@RequestMapping(value = "/createReviews", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<?> createReviews(@RequestBody ReviewsAndRating rating ) throws SQLException {
			JSONObject json=new JSONObject();
		     //List<Order> orderList;
			ReviewsAndRating list;
			try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
				rating.setUserId(userId);
		   list= libraryService.createReviewsAndRatingService(rating);
		   if(list!=null) {
			   json.put("message","saved");
			   json.put("status",200);
		   }
		   else {
			   json.put("message","failed");
			   json.put("status",200);

		   }
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	       
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<>("service failed due to some exceptions", HttpStatus.INTERNAL_SERVER_ERROR);
	}
			return new ResponseEntity<>(json, HttpStatus.CREATED);

		}
	
	@RequestMapping(value = "/getCourseVideoDetails", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<?> getCourseTopicById(@RequestBody CourseRequest reqs) throws SQLException {
			JSONObject json=new JSONObject();
		    Map<String, Object> collectionList=new HashMap<String, Object>();
			try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
				collectionList= libraryService.getCourseTopicById(reqs,userId);
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	      
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<>("service failed due to some exceptions", HttpStatus.INTERNAL_SERVER_ERROR);
	}
			return new ResponseEntity<>(collectionList, HttpStatus.OK);
	       
		}
	@RequestMapping(value = "/getRecommendationCourses", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<?> getRecommendationCourses() throws SQLException {
			JSONObject json=new JSONObject();
		    List<Map<String, Object>> collectionList=new ArrayList<Map<String, Object>>();
			try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
				collectionList= libraryService.getRecommendationCourses(userId);
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	      
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<>("service failed due to some exceptions", HttpStatus.INTERNAL_SERVER_ERROR);
	}
			return new ResponseEntity<>(collectionList, HttpStatus.OK);
	       
		}
	@RequestMapping(value = "/getTopSearches", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<?> getTopSearches() throws SQLException {
			JSONObject json=new JSONObject();
		    List<String> collectionList=new ArrayList<String>();
			try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
				collectionList= libraryService.getTopSearches(userId);
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	      
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<>("service failed due to some exceptions", HttpStatus.INTERNAL_SERVER_ERROR);
	}
			return new ResponseEntity<>(collectionList, HttpStatus.OK);
	       
		}
	
	@RequestMapping(value = "/removeCourseFromCollection", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<ApiResponse> removeCourseFromCollection(@RequestBody JSONObject course) throws SQLException {
			JSONObject json=new JSONObject();
			String response;
		   //  List<AddToCartDto> cartList;
		try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
		   response= libraryService.removeCourseFromCollection(Integer.parseInt(course.get("courseCollectionId").toString()));
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	       
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service failed due to some exceptions", 500), HttpStatus.INTERNAL_SERVER_ERROR);
	}
			 return new ResponseEntity<ApiResponse>(new ApiResponse(true, response, 200), HttpStatus.OK);

		}
	@RequestMapping(value = "/deleteCollection", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<ApiResponse> deleteCollection(@RequestBody JSONObject req) throws SQLException {
			JSONObject json=new JSONObject();
			Collection cart;
		   //  List<AddToCartDto> cartList;
		try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
		    libraryService.deleteCollection(Integer.parseInt(req.get("collectionId").toString()),Integer.parseInt(req.get("userId").toString()));
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	       
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service failed due to some exceptions",500), HttpStatus.INTERNAL_SERVER_ERROR);
	}
			 return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Items has been removed", 200), HttpStatus.OK);

		}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createBookMark", method = RequestMethod.POST, headers = "content-type=application/json")	 
	public ResponseEntity<?> createBookMark(HttpServletRequest request,@ApiParam(value = "json object", required = true, name = "json") @RequestBody LibraryRequest bookMark)throws SQLException {

		String bookMarkSaved=null;
		JSONObject response=new JSONObject();
		try {
			//HttpServletRequest req = (HttpServletRequest) request;
			//String auth = req.getHeader("Token");
			//String validateToken=validateToken(auth,"USER");
			//if(validateToken.equalsIgnoreCase("true")) {
			int userId=1;
				bookMarkSaved =  libraryService.saveBookmark(bookMark,userId);
				
			//}
			//else
			//{
				//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			//}

		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service failed due to some exceptions",500), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		 return new ResponseEntity<ApiResponse>(new ApiResponse(true, bookMarkSaved,200), HttpStatus.OK);


	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/likeVideo", method = RequestMethod.POST, headers = "content-type=application/json")	 
	public ResponseEntity<?> likeVideo(HttpServletRequest request,@ApiParam(value = "json object", required = true, name = "json") @RequestBody LibraryRequest like)throws SQLException {

		ApiResponse likeSaved=null;
		JSONObject response=new JSONObject();
		try {
			//HttpServletRequest req = (HttpServletRequest) request;
			//String auth = req.getHeader("Token");
			//String validateToken=validateToken(auth,"USER");
			//if(validateToken.equalsIgnoreCase("true")) {
			int userId=1;
				likeSaved =  libraryService.likeVideo(like,userId);
				
			//}
			//else
			//{
				//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			//}

		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service failed due to some exceptions",500), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		 return new ResponseEntity<>(likeSaved, HttpStatus.OK);


	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveEbook", method = RequestMethod.POST, headers = "content-type=application/json")	 
	public ResponseEntity<?> saveEbook(HttpServletRequest request,@ApiParam(value = "json object", required = true, name = "json") @RequestBody LibraryRequest book)throws SQLException {

		ApiResponse ebookSaved=null;
		JSONObject response=new JSONObject();
		try {
			//HttpServletRequest req = (HttpServletRequest) request;
			//String auth = req.getHeader("Token");
			//String validateToken=validateToken(auth,"USER");
			//if(validateToken.equalsIgnoreCase("true")) {
			int userId=1;
			ebookSaved=  libraryService.saveEbook(book,userId);
				
			//}
			//else
			//{
				//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			//}

		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service failed due to some exceptions",500), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		 return new ResponseEntity<>(ebookSaved, HttpStatus.OK);


	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reportVideo", method = RequestMethod.POST, headers = "content-type=application/json")	 
	public ResponseEntity<?> reportVideo(HttpServletRequest request,@ApiParam(value = "json object", required = true, name = "json") @RequestBody LibraryRequest report)throws SQLException {

		ApiResponse reportSaved=null;
		JSONObject response=new JSONObject();
		try {
			//HttpServletRequest req = (HttpServletRequest) request;
			//String auth = req.getHeader("Token");
			//String validateToken=validateToken(auth,"USER");
			//if(validateToken.equalsIgnoreCase("true")) {
			int userId=1;
			reportSaved =  libraryService.reportVideo(report,userId);
				
			//}
			//else
			//{
				//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			//}

		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service failed due to some exceptions",500), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		 return new ResponseEntity<>(reportSaved, HttpStatus.OK);


	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deleteBookMark", method = RequestMethod.POST,headers = "content-type=application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteBookMark(HttpServletRequest request,@RequestBody BookMark bookmark)throws SQLException  {

		BookMark result =new BookMark();
		JSONObject response=new JSONObject();
		try {
		//	HttpServletRequest req = (HttpServletRequest) request;
			//String auth = req.getHeader("Token");
			//String validateToken=validateToken(auth,"USER");
			//int userId=1;
			//if(validateToken.equalsIgnoreCase("true")) {
				result = libraryService.deleteBookMark(bookmark);
				if(result!=null) {
					response.put("message",result.getTitle()+" "+"deleted successfully");
					response.put("status",200);
				}
				else {
					response.put("message", "failed");
				}

			//}
			//else
			//{
			//	return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			//}
		}
		catch (Exception e) {

			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);
			return new ResponseEntity<>("service failed due to some exceptions", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getBookMarkList", method = RequestMethod.POST, headers = "content-type=application/json")	 
	public ResponseEntity<?> getBookMarkList(HttpServletRequest request,
			@ApiParam(value = "json object", required = true, name = "json") @RequestBody LibraryRequest json)throws SQLException  {

		Map<String, Object> list =null;
		try {
		//	HttpServletRequest req = (HttpServletRequest) request;
			//String auth = req.getHeader("Token");
			//String validateToken=validateToken(auth,"USER");
		//	if(validateToken.equalsIgnoreCase("true")) {
			int userId=1;
				list = libraryService.getBookMarkList(
						userId,
						json);
			//}
			//else
			//{
				//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			//}
		}
		catch (Exception e) {

			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);
			return new ResponseEntity<>("service failed due to some exceptions", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveUserProgress", method = RequestMethod.POST, headers = "content-type=application/json")	 
	public ResponseEntity<?> userProgress(HttpServletRequest request,@ApiParam(value = "json object", required = true, name = "json") @RequestBody LibraryRequest userProgress)throws SQLException {

		ApiResponse reportSaved=null;
		JSONObject response=new JSONObject();
		try {
			//HttpServletRequest req = (HttpServletRequest) request;
			//String auth = req.getHeader("Token");
			//String validateToken=validateToken(auth,"USER");
			//if(validateToken.equalsIgnoreCase("true")) {
			int userId=1;
			reportSaved =  libraryService.userProgress(userProgress,userId);	
			//}
			//else
			//{
				//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			//}

		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service failed due to some exceptions",500), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		 return new ResponseEntity<>(reportSaved, HttpStatus.OK);


	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUserProgress", method = RequestMethod.POST, headers = "content-type=application/json")	 
	public ResponseEntity<?> getUserProgress(HttpServletRequest request,@ApiParam(value = "json object", required = true, name = "json") @RequestBody LibraryRequest userProgress)throws SQLException {

		ApiResponse reportSaved=null;
		JSONObject response=new JSONObject();
		try {
			//HttpServletRequest req = (HttpServletRequest) request;
			//String auth = req.getHeader("Token");
			//String validateToken=validateToken(auth,"USER");
			//if(validateToken.equalsIgnoreCase("true")) {
			int userId=1;
			reportSaved =  libraryService.getUserProgress(userId);	
			//}
			//else
			//{
				//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			//}

		}

		catch (Exception e) {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			logger.error(stackTrace);
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service failed due to some exceptions", 500), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		 return new ResponseEntity<>(reportSaved, HttpStatus.OK);


	}
	@RequestMapping(value = "/createSearch", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<ApiResponse> createSearch(@RequestBody JSONObject request) throws SQLException {
			JSONObject json=new JSONObject();
		     //List<Order> orderList;
		
			try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
		  UserSearch collection= libraryService.createSearch(request.get("name").toString(),userId);
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	       
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service failed due to some exceptions", 500), HttpStatus.INTERNAL_SERVER_ERROR);
	}
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "search is added",201), HttpStatus.CREATED);

		}
	@RequestMapping(value = "/getAllReviews", method = RequestMethod.POST, headers = "content-type=application/json")	 
	 public ResponseEntity<?> getAllReviews(@ApiParam(value = "json object", required = true, name = "json") @RequestBody LibraryRequest req) throws SQLException {
			JSONObject json=new JSONObject();
		     //List<Order> orderList;
			List<ReviewsAndRating>list;
			try {
			//	HttpServletRequest req = (HttpServletRequest) request;
				//String auth = req.getHeader("Token");
				//String validateToken=validateToken(auth,"USER");
				//if(validateToken.equalsIgnoreCase("true")) {
				int userId=1;
		   list= libraryService.getAllReviewsAndRatingService(req.getCourseId(),userId);
		   if(list!=null) {
			   json.put("list", list);
			   json.put("status", 200);
		   }
		   else  {
			   json.put("list", list);
			   json.put("status", 204);
		   }
	      //  StripeResponse stripeResponse = new StripeResponse(session.getId());
	        // send the stripe session id in response
	       
			
		}
		//else
		//{
			//return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		//}


	catch (Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.error(stackTrace);
		return new ResponseEntity<>("service failed due to some exceptions", HttpStatus.INTERNAL_SERVER_ERROR);
	}
			return new ResponseEntity<>(json, HttpStatus.CREATED);

		}
}
