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
    MongoDatabase db = fongo.getDatabase("app");
	
	public boolean addStudent(Document aluno)
	{
		MongoDatabase db = fongo.getDatabase("antenas");
		MongoCollection<Document> alunos = db.getCollection("aluno");
    	alunos.insertOne(aluno);
    	return true;
	}

	public boolean login(String email, String senha) {
		MongoCollection<Document> alunos = db.getCollection("aluno");
		FindIterable<Document> login = alunos.find(new Document("email", email).append("senha", senha));
		if(login != null) {
			return true;
		}return false;
	}
}
		
		/*	MongoCursor<Document> cursor = alunos.find().iterator();
		try {
			while (cursor.hasNext()) {
			Document achado1 = alunos.find(new Document("email", email)).first();
			if(achado1.equals(alunos.find(new Document("senha", senha)))){
				return 	
								}
							}
		} finally {
		cursor.close();
		}
		

		FindIterable<Document> username = alunos.find(new Document("email", email));
		if(username.equals(alunos.find(new Document("senha", senha)))) {
	
		}*/
		

	
	
	
//TODO	
//	public boolean isUserAvailable(Document aluno){
//			    
//	    for(Document student:allStudents){
//	    	if(student.getUserName().equals(username)) return false;
//	    }
//	    
//	    return true;
//	}
	
/*
	public Student login(String username, String password){
		
		Query query = students.query();
		query.constrain(Student.class);
	    ObjectSet<Student> allStudents = query.execute();
	    
	    for(Student student:allStudents){
	    	if(student.getUserName().equals(username) && student.getPassword().equals(password)){
	    		
	    		return student;
	    	}
	    	
	    }
	    
	    return null;

	}
	
	public Student searchStudentbyRA(int ra){
		
		
		Query query = students.query();
		query.constrain(Student.class);
	    ObjectSet<Student> allStudents = query.execute();
		
	    for(Student student:allStudents){
	    	if(student.getRa()==ra){
	    		return student;
	    	}
	    	
	    }
	    
	    return null;

		
	}
	
	/*public Question searchQuestionByCode(int code){
		
		Query query = questions.query();
		query.constrain(Question.class);
	    ObjectSet<Question> allQuestions = query.execute();
		
	    for(Question question:allQuestions){
	    	if(question.getNumber() == code){
	    		return question;
	    	}
	    	
	    }
	    
	    return null;
		
	}

	public List<Question> getAllQuestions(){
		
		Query query = questions.query();
		//query.constrain(Question.class);
		query.descend("number").orderAscending();
		List<Question> allQuestions = query.execute();

		return allQuestions;
    
	}
	
	public void deleteQuestion(int number){
		Query query = questions.query();
		query.constrain(Question.class);
		List<Question> allQuestions = query.execute();
		
		for(Question question:allQuestions){
			if(question.getNumber()==number){
				questions.delete(question);
				questions.commit();
				for(Question q:allQuestions){
					if(q.getNumber()>=number){
						q.setNumber(q.getNumber()-1);
						questions.store(q);
						questions.commit();
					}
				}
				break;
			}
		}
	}
	*/
