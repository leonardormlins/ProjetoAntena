
package hello;

import static spark.Spark.*;

import org.bson.Document;

import com.mongodb.client.FindIterable;
//import org.json.me;


public class MainServer {
	
	final static Model model = new Model();
	
    public static void main(String[] args) {

		// Get port config of heroku on environment variable
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 9090;
        }
        port(port);

		//Servir conteudo html, css e javascript
		staticFileLocation("/static");

		inicializarPesquisa();
 
		REST controller = new REST(model);
		
		controller.inserirAluno();
	    controller.search();
	    controller.loginAluno();
	    controller.projetos();
	    controller.atribuirProjeto();
	    model.addAluno(Document.parse("{'email':'leo@antenas.com','name':'Leo', 'senha':'12345', 'nivel':'1'}"));
			
		
    }
    
    
    public static void inicializarPesquisa(){
    	
    	
    	model.addProjeto(Document.parse("{'_id': '1', 'nome':'projetox', 'fase':'3', 'responsavel-cadi':'jesuka@aaa.com', 'responsavel-prof': ''}"));
    	model.addProjeto(Document.parse("{'nome':'projetoq', 'fase':'2', 'responsavel-cadi':'rone@email.com'}"));
		model.addProjeto(Document.parse("{'_id': '2', 'nome':'projectyas', 'fase':'1', 'responsavel-cadi':''}"));
    
    }
}
