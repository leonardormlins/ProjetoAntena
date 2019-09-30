$(document).ready(function (){
	
	/*let email = sessionStorage.getItem("sess_email_aluno");
	let $projetos = document.querySelector('#projetos');
	let rota = "/dono/" + email
	let retorno = {}
	
	$.get(rota, function(data){
		retorno = data;
	});
	
	telaProjetos = $projetos.innerHTML;
	console.log(telaProjetos);
	console.log(retorno);*/
	
	$('#botao-add').click(function(){
		let codigoProjeto = $("#codigo-projetoLabel").val();
		let email = sessionStorage.getItem("sess_email_aluno");
		let jsonSend = '{"id": "' + codigoProjeto +'","email":"'+email+'"}';
			
		$.post("/add-projeto", jsonSend , function (json){
			alert("Adicionado projeto "+codigoProjeto+" com sucesso!");
		});
});
});