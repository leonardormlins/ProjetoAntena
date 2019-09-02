package hello;

import static spark.Spark.get;
import static spark.Spark.post;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.bson.Document;

import com.mongodb.client.FindIterable;

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

	public void cadastroAluno(){
				
		post("/cadastro", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	Document student = new Document("alunos",request.body());
	        	student.append("alunos", student);
	        	return student;
			}
		});
		get("/cadastro", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
				FindIterable<Document> resultado = model.getStudents();
				return StreamSupport.stream(resultado.spliterator(), false)
				        .map(Document::toJson)
				        .collect(Collectors.joining(", ", "[", "]"));
			}
		});
		
	}
}

