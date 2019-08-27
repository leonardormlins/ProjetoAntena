package hello;
import static spark.Spark.post;

import org.bson.Document;
import org.json.JSONObject;

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

		post("/aluno/cadastro", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	Document student = new Document("aluno",request.body());
	        	student.append("aluno", student);
	        	return student;
			}
		});

	}
	public static void loginAluno(){

		post("/login", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
				  response.header("Access-Control-Allow-Origin", "*");
				  
			}
		});

	}
}

