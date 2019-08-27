package hello;

import static spark.Spark.get;
import static spark.Spark.post;


import java.io.UnsupportedEncodingException;
import java.util.List;

import org.bson.Document;

import spark.Route;


import spark.Request;
import spark.Response;
import spark.Route;


public class REST{
	
	private Model model;
	
	
	public REST(Model store){
		this.model = store;
	}
//	public void cadastroAluno(){
//		post("",(req,res) -> {
//			
//		});
//	}

	public static void cadastroAluno(){
		
		post("/cadastro.html", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	Document student = new Document("aluno",request.body());
	        	student.append("aluno", student);
	        	return student;
			}
		});     
		
	}
}

