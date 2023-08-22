console.log("teste aliquota")

let valorMinimo = document.getElementById("valorMinimo");
let valorMaximo = document.getElementById("valorMaximo");
let aliquota = document.getElementById("aliquota");
let anoMes = document.getElementById("anoMes");
let faixaAliquota = document.getElementById("faixaAliquota");
listener(aliquota);
let form = document.getElementById("form");

function listener(html) {

	html.addEventListener('keypress', (event) => {

		let keyCode = event.key;
		addFaixasHtml(keyCode);


});
	
} 
function addFaixasHtml(keyCode){
	
	if (keyCode === "Enter") {
		form.appendChild(faixaAliquota.cloneNode(true))

		listener( form.lastElementChild.lastElementChild)


	}
}

function enviar() {
	
	let faixas = [];
	let f = form.childNodes;

	for(i in f){
	console.log(f[i].value)
	faixas += {
//	valorMinimo: valorMinimo.value, valorMaximo: valorMaximo.value, aliquota: aliquota.value
	}
	}
	let json = JSON.stringify({
		anoMes: anoMes.value, faixasAliquotas: [faixas]
	});
	console.log(JSON.parse(json))
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/aliquota", true)
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhr.onload = function() {
		let resp = JSON.parse(this.response);
		console.log(JSON.parse(this.response).nomeCompleto);
		window.alert(`Aliquota do mes ${resp.anoMes}, percentual ${resp.aliquota}, 
			valor mínimo ${resp.valorMinimo} e valor máximo ${resp.valorMaximo}, salva com sucesso.`)
	};
	xhr.send(json);
	//location.reload();

}