$(document).ready(function (){
	
	let email = sessionStorage.getItem("sess_email_aluno");
	let tela = document.querySelector('#projetos');
	let rota = "/dono/" + email
	let retorno = {}
	
	$.get(rota, function(data, err){
		let projetos = JSON.parse(data);
		for(let i=0; i<len(projetos);i++){
			projetos[i].push("<tr> <td>" + this.nome + "</td><td>" + this.fase + "</td><td>" + this.responsavel-cadi + "</td></tr>");
		}
		$(tela).append(projetos);
	});
	
	$('#botao-add').click(function(){
		let codigoProjeto = $("#codigo-projetoLabel").val();
		let email = sessionStorage.getItem("sess_email_aluno");
		let jsonSend = '{"id": "' + codigoProjeto +'","email":"'+email+'"}';
			
		$.post("/add-projeto", jsonSend , function (json){
			alert("Adicionado projeto "+codigoProjeto+" com sucesso!");
		});
});
});