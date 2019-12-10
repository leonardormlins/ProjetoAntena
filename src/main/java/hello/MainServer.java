
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
            port = 8080;
        }
        port(port);

		//Servir conteudo html, css e javascript
		staticFileLocation("/static");

		inicializarPesquisa();
 
		REST controller = new REST(model);

		controller.cadastroAluno();
	    controller.search();
	    controller.projetos();
	    controller.atribuirProjeto();
	    controller.ativarUsuario();
	    
	    //validacao alunos
	    controller.validaAluno();
	    controller.loginAluno();
	    
	    model.addAluno(Document.parse(
	    		"{"
	    		+ "'email':'leonardo.lins@antenas.com',"
	    		+ "'name':'Leo', "
	    		+ "'senha':'12345', "
	    		+ "'nivel':'1'"
	    		+ "}"));
    }
    
    
    public static void inicializarPesquisa(){

    	model.addProjeto(Document.parse(
        		"{"
        			+ "'_id':'1234',"
        			+ "'chave':'ha86sh1'"
        			+ "'titulo':'Programa sempre teste!',"
        			+ "'descricao-breve':'Aplicacao que verifica qualitativamente os testes de um time',"
        			+ "'link-externo-1':'',"
        			+ "'link-externo-2':'',"
        			+ "'descricao-completa':'Precisa-se de uma aplicacao que analise todos os testes de um time de desenvolvimento e apresente em um Dashboard',"
        			+ "'descricao-tecnologias':'Analisa submiss�es de teste JUnit',"
        			+ "'fase': '4',"
        			+ "'reuniao': {"
        				+ "'data': '15/03/2020',"
        				+ "'horario':'15:00',"
        				+ "'local': 'S�o Jos� dos Campos',"
        				+ "'datas-possiveis': ["
        					+ "{"
        						+ "'data': '02/03/2020',"
        						+ "'hora': '13:50'"
        					+ "}"
        				+ "]"
        			+ "}"
        			+ "'status': {"
        				+ "'negado': false,"
        				+ "'motivo': ''"
        			+ "}"
        			+ "'entregas': ["
        				+ "{"
        					+ "'aluno-responsavel': '',"
        					+ "'alunos': [],"
        					+ "'link-repositorio':'',"
        					+ "'link-cloud':'',"
        					+ "'comentario':''"
        				+ "}"
        			+ "],"
        			+ "'responsavel-cadi': 'arakaki@fatec.sp.gov.br',"
        			+ "'responsavel-professor': ["
        				+ "'giuliano@fatec.sp.gov.br'"
        			+ "],"
        			+ "'responsavel-empresario': 'Bill Clever',"
        			+ "'alunos': ["
        				+ "'leonardo.lins@fatec.sp.gov.br'"
        			+ "]"
        		+ "}"));
    	model.addProjeto(Document.parse(
        		"{"
        			+ "'_id':'1234567',"
        			+ "'chave':'ahfaasd'"
        			+ "'titulo':'No rage!',"
        			+ "'descricao-breve':'Aplicacao que verifica qualitativamente os testes de um time',"
        			+ "'link-externo-1':'',"
        			+ "'link-externo-2':'',"
        			+ "'descricao-completa':'Precisa-se de uma aplicacao que analise todos os testes de um time de desenvolvimento e apresente em um Dashboard',"
        			+ "'descricao-tecnologias':'Analisa submiss�es de teste JUnit',"
        			+ "'fase': '4',"
        			+ "'reuniao': {"
        				+ "'data': '15/03/2020',"
        				+ "'horario':'15:00',"
        				+ "'local': 'S�o Jos� dos Campos',"
        				+ "'datas-possiveis': ["
        					+ "{"
        						+ "'data': '02/03/2020',"
        						+ "'hora': '13:50'"
        					+ "}"
        				+ "]"
        			+ "}"
        			+ "'status': {"
        				+ "'negado': false,"
        				+ "'motivo': ''"
        			+ "}"
        			+ "'entregas': ["
        				+ "{"
        					+ "'aluno-responsavel': '',"
        					+ "'alunos': [],"
        					+ "'link-repositorio':'',"
        					+ "'link-cloud':'',"
        					+ "'comentario':''"
        				+ "}"
        			+ "],"
        			+ "'responsavel-cadi': 'arakaki@fatec.sp.gov.br',"
        			+ "'responsavel-professor': ["
        				+ "'giuliano@fatec.sp.gov.br'"
        			+ "],"
        			+ "'responsavel-empresario': 'Bill Clever',"
        			+ "'alunos': ["
        				+ "'leonardo.lins@fatec.sp.gov.br'"
        			+ "]"
        		+ "}"));
    	model.addProjeto(Document.parse(
        		"{"
        			+ "'_id':'678910',"
        			+ "'chave':'pjefba'"
        			+ "'titulo':'Lets Encode',"
        			+ "'descricao-breve':'Aplicacao que verifica qualitativamente os testes de um time',"
        			+ "'link-externo-1':'',"
        			+ "'link-externo-2':'',"
        			+ "'descricao-completa':'Precisa-se de uma aplicacao que analise todos os testes de um time de desenvolvimento e apresente em um Dashboard',"
        			+ "'descricao-tecnologias':'Analisa submiss�es de teste JUnit',"
        			+ "'fase': '4',"
        			+ "'reuniao': {"
        				+ "'data': '15/03/2020',"
        				+ "'horario':'15:00',"
        				+ "'local': 'S�o Jos� dos Campos',"
        				+ "'datas-possiveis': ["
        					+ "{"
        						+ "'data': '02/03/2020',"
        						+ "'hora': '13:50'"
        					+ "}"
        				+ "]"
        			+ "}"
        			+ "'status': {"
        				+ "'negado': false,"
        				+ "'motivo': ''"
        			+ "}"
        			+ "'entregas': ["
        				+ "{"
        					+ "'aluno-responsavel': '',"
        					+ "'alunos': [],"
        					+ "'link-repositorio':'',"
        					+ "'link-cloud':'',"
        					+ "'comentario':''"
        				+ "}"
        			+ "],"
        			+ "'responsavel-cadi': 'arakaki@fatec.sp.gov.br',"
        			+ "'responsavel-professor': ["
        				+ "'giuliano@fatec.sp.gov.br'"
        			+ "],"
        			+ "'responsavel-empresario': 'Bill Clever',"
        			+ "'alunos': ["
        				+ "'leonardo.lins@antenas.com'"
        			+ "]"
        		+ "}"));
    }
}
