
let nome = document.getElementById("nome");
let cpf = document.getElementById("cpf");

function enviar() {

	let json = JSON.stringify({
		nomeCompleto: nome.value, cpf: cpf.value
	});

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/contribuinte", true)
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhr.onload = function() {
		let resp =  JSON.parse(this.response);
		console.log(JSON.parse(this.response).nomeCompleto);
		window.alert(`Contribuinte ${resp.nomeCompleto}, CPF ${resp.cpf}, salvo com sucesso.`)
	};
	xhr.send(json);

}

