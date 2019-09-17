package hello;
import static spark.Spark.post;

import org.json.*;
import org.bson.Document;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.mongodb.client.FindIterable;

import spark.Route;

import spark.Request;
import spark.Response;

public class REST{

	private Model model;


	public REST(Model model){
		super();
		this.model = model;
	}

	public void cadastroAluno(){
				
		post("/cadastro", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	System.out.println(request.body());
				Document student = new Document("alunos",request.body());
				model.addStudent(student);
				return student;
			}
		});
	/*	get("/cadastro", new Route() {

			@Override
            public Object handle(final Request request, final Response response){
				FindIterable<Document> resultado = model.getStudents();
				return StreamSupport.stream(resultado.spliterator(), false)
				        .map(Document::toJson)
				        .collect(Collectors.joining(", ", "[", "]"));
			}
		});*/

  }
  
	public  void login(){

		post("/login", new Route() {
			@Override
			public Object handle(final Request request, final Response response){
				response.header("Access-Control-Allow-Origin", "*");
				JSONObject json = new JSONObject(request.body());
				String email = json.getString("userName");
				 String password = json.getString("password");
				if(model.loginAluno(email,password) == true);{
					return "Login realizado.";
				} 		 
				
			} 
		});
	
	}
}

