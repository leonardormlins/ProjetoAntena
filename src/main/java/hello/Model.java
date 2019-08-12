package hello;

import java.util.LinkedList;
import java.util.List;

/*import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;*/

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Model{
	
	ObjectContainer students = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/students.db4o");
	ObjectContainer questions = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/questions.db4o");
	ObjectContainer competencies = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/competencies.db4o");
	ObjectContainer institutions = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/institutions.db4o");
	ObjectContainer psychologists = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/psychologists.db4o");
	ObjectContainer adms = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/adms.db4o");
	

	
	public boolean addStudent(Student student){
		
		if(isUserAvailable(student.getUserName())){
			List<Competency> studentsCompetencies = new LinkedList<Competency>();
			
			Query query = competencies.query();
			query.constrain(Competency.class);
		    ObjectSet<Competency> allCompetencies = query.execute();
		    
		    for(Competency competency:allCompetencies){
		    	studentsCompetencies.add(competency);
		    }
			
		    student.setCompetencies(studentsCompetencies);
		    
		    
			students.store(student);
			students.commit();
			
			return true;
		}
		
		return false;
		
	}
	
	
	public boolean isUserAvailable(String username){
		Query query = students.query();
		query.constrain(Student.class);
	    ObjectSet<Student> allStudents = query.execute();
	    
	    for(Student student:allStudents){
	    	if(student.getUserName().equals(username)) return false;
	    }
	    
	    return true;
	}
	

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
		
	}*/

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