package hello;

import static spark.Spark.get;
import static spark.Spark.post;


import java.io.UnsupportedEncodingException;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;


public class REST{
	
	private Model model;
	
	
	public REST(Model store){
		this.model = store;
	}
	
	public void getLogin(){
		
		get("/login/:username/:password", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	     	 
	        	response.header("Access-Control-Allow-Origin", "*");
	            try {
	            	Student student = model.login(request.params(":username"), request.params(":password"));
	            	
	            	if(student != null){
	            		
	            		JSONArray jsonResult = new JSONArray();
		         	    JSONObject jsonObj = new JSONObject();

		        		jsonObj.put("ra", student.getRa());
		        		jsonObj.put("completed", student.getCompleted());
		        		jsonObj.put("question", student.getQuestion());
		        		
		             	jsonResult.put(jsonObj);
		             	
		             	return jsonResult;
	            		
	            	} else {
	            		
	            		
	            		
	            	}
	            	
	            	
	             	
	        		} catch (JSONException e) {
	        				
	        			//e.printStackTrace();

	        		}
	         	    	
	
	            JSONArray jsonResult = new JSONArray();
         	    JSONObject jsonObj = new JSONObject();

        		jsonObj.put("ra", 0);
        		
        		
             	jsonResult.put(jsonObj);
             	
             	return jsonResult;
	            
	     	     
	         }
	         
	      });
			

	}
	
	//@Review
	public void getQuestionByNumber(){
		
		get("/questions/:number", new Route() {
			@Override
            public Object handle(final Request request, final Response response) throws UnsupportedEncodingException{
	        	
	        	response.header("Access-Control-Allow-Origin", "*");
	        	 
	            Integer number = Integer.parseInt(request.params(":number"));
	        	
	            
	            try {
	            	Question question = model.searchQuestionByCode(number);
	            	
	            	JSONArray jsonResult = new JSONArray();
	         	    JSONObject jsonObjQuestion = new JSONObject();
	         	    
	         	    jsonObjQuestion.put("number", question.getNumber());
	        		jsonObjQuestion.put("introduction", java.net.URLDecoder.decode(question.getIntroduction(), "UTF-8"));
	        		jsonObjQuestion.put("introductionMediaType", question.getIntroductionMediaType());
	        		jsonObjQuestion.put("question", question.getQuestion());
	        		jsonObjQuestion.put("answer", question.getAnswers());
	        		
	        		jsonResult.put(jsonObjQuestion);

	        		
	        		
	             	return jsonResult;
	             	
	        		} catch (JSONException e) {
	        				
	        			e.printStackTrace();
	        		}
	         	    	
	
	     	    return null;
	     	     
	         }
	         
	      });
		
	}	
	
	public void setAnswerbyCode(){
		
		get("/answer/:ra/:questionNumber/:answerCode", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	        	response.header("Access-Control-Allow-Origin", "*");
	        	 
	        	
	        	
	        	Integer ra = Integer.parseInt(request.params(":ra"));
	        	Integer questionNumber = Integer.parseInt(request.params(":questionNumber"));
	        	Integer answerCode = Integer.parseInt(request.params(":answerCode"));
	            
	            
	            
	            try {
	            	
	            	int status = model.recordAnswer(ra, questionNumber, answerCode);
	            	
	            	if(status == 0){
	            		JSONArray jsonResult = new JSONArray();
		         	    
		         	    JSONObject jsonObj = new JSONObject();
		         	    	
		         	    jsonObj.put("status", "ok");
		         	    jsonResult.put(jsonObj);
		
		             	return jsonResult;
		             	
	            	} else {
	            		
	            		JSONArray jsonResult = new JSONArray();
		         	    
		         	    JSONObject jsonObj = new JSONObject();
		         	    	
		         	    jsonObj.put("status", "fim");
		         	    jsonResult.put(jsonObj);
		
		             	return jsonResult;
	            		
	            	}
	            	
	            	
	             	
	        		} catch (JSONException e) {
	        				
	        			e.printStackTrace();
	        		}
	         	    	
	
	     	    return null;
	     	     
	         }
	         
	      });

	         
	}
	
	
	public void setComments(){
			
			post("/comment/", new Route() {
				@Override
	            public Object handle(final Request request, final Response response){
		        	
		           response.header("Access-Control-Allow-Origin", "*");

		        	
		        	
		        	
		           JSONObject json = new JSONObject(request.body());
		        	
		           String comment = json.getString("comment");
		        	
		           int ra = Integer.parseInt(json.getString("ra"));
		        	
		           
	         	    
	         	   try {
		            	
		            	boolean status = model.setComment(ra, comment);;
		            	
		            	
		            	
		            	if(status==true){
		            		
		            		
		            		
		            		JSONArray jsonResult = new JSONArray();
		 	         	    JSONObject jsonObj = new JSONObject();
		     
			        		jsonObj.put("status", 1);
			        		
			        		
			             	jsonResult.put(jsonObj);
			             	
			             	
			             	
			             	return jsonResult;
		            		
		            	}
		            	
		            	
		             	
		        		} catch (JSONException e) {
		        				
		        			e.printStackTrace();
		        		}
	         	    
	         	    JSONArray jsonResult = new JSONArray();
	         	    JSONObject jsonObj = new JSONObject();
	         	   	
	         	    jsonObj.put("status", 0);
	        		
	        		
	             	jsonResult.put(jsonObj);
	             	
	             	return jsonResult;
	         	   
	         	   
		        	
			   }
			});     

	         
	}
	
	public void getAllQuestions(){
		
		
		
		get("/questions", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	        	response.header("Access-Control-Allow-Origin", "*");

	        	JSONArray jsonResult = new JSONArray();
         	    
         	    
	        	
	            try {
	            	
	            	List<Question> questions = model.getAllQuestions();
	            	
	            	for(Question question:questions){
	            		
	            		JSONObject jsonObj = new JSONObject();
	            		jsonObj.put("number", question.getNumber());
	            		jsonObj.put("question", question.getQuestion());
	            		jsonResult.put(jsonObj);
	            		
	            	}
	            	
	            	return jsonResult;
	            	
	            	
	             	
	        		} catch (JSONException e) {
	        				
	        			e.printStackTrace();
	        		}
	         	    	
	
	     	    return null;
	     	     
	         }
	         
	      });
		
		
	}
	
	public void setStudent(){
		
		post("/student", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	           response.header("Access-Control-Allow-Origin", "*");

	           Gson gson = new Gson();
	           
	           String json = request.body();
	           
	           
	           
	           Student student = gson.fromJson(json, Student.class);
	           
	           
	           
         	    
         	   try {
	            	
	            		boolean status = model.addStudent(student);
	            		
	            		if(status){
	            			
	            			JSONArray jsonResult = new JSONArray();
		 	         	    JSONObject jsonObj = new JSONObject();
		     
			        		jsonObj.put("status", 1);
			        		
			        		
			             	jsonResult.put(jsonObj);
			             	
			             	
			             	
			             	return jsonResult;
	            		}
	            		
	            		
	            		
	            	
	            	
	            	
	             	
	        		} catch (JSONException e) {
	        				
	        			e.printStackTrace();
	        		}
         	    
         	    JSONArray jsonResult = new JSONArray();
         	    JSONObject jsonObj = new JSONObject();
         	   	
         	    jsonObj.put("status", 0);
        		
        		
             	jsonResult.put(jsonObj);
             	
             	return jsonResult;
         	   
         	   
	        	
		   }
		});     
	}
	
	