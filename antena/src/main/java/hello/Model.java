package hello;

//import java.util.LinkedList;
//import java.util.List;

import org.bson.Document;

/*import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;*/

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.github.fakemongo.Fongo;

	public class Model{

//	MongoClientURI uri  = new MongoClientURI("mongodb://localhost:27017/telegram"); 
//    MongoClient client = new MongoClient();	
	
  Fongo fongo = new Fongo("Mongo");
  MongoDatabase db = fongo.getDatabase("antenas");
	MongoCollection<Document> alunos = db.getCollection("alunos");    
    
	public boolean addStudent(Document aluno)
	{
    	alunos.insertOne(aluno);
    	return true;
	}

	public  boolean loginAluno(String email, String senha) {
		
		MongoCollection<Document> alunos = db.getCollection("aluno");
		FindIterable<Document> login = alunos.find(new Document("email", email).append("senha", senha));
		if(login != null) {
			return true;
		}return false;
	}
 }