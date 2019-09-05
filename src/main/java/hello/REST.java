package hello;
import static spark.Spark.post;

import org.json.*;
import org.bson.Document;


import spark.Request;
import spark.Response;
import spark.Route;



public class REST{

	private Model model;


	public REST(Model model){
		super();
		this.model = model;
	}
//	public void cadastroAluno(){
//		post("",(req,res) -> {
//
//		});
//	}

	public static void cadastroAluno(){

		post("/aluno/cadastro", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	Document student = new Document("aluno",request.body());
	        	student.append("aluno", student);
	        	return student;
			}
		});

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

