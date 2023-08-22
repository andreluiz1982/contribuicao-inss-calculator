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
        let faixa =  form.appendChild(faixaAliquota.cloneNode(true))
        let inputs = faixa.getElementsByTagName('input');
          for (index = 0; index < inputs.length; ++index) {
          inputs[index].id = "faixaAliquota"+index;
            	    if(inputs[index].type =="text")
                    inputs[index].value = '';
                }
		listener( form.lastElementChild.lastElementChild)


	}
}

function enviar() {
	
	let faixas = [];
	let f = document.getElementsByClassName("faixa m-3 p-3 border border-info rounded");
	console.log('size  '+ f.length)
	for(let i in f){
	console.log('i ' + i)
	if(i ==1 ){
	    let min = f[i].querySelector('#valorMinimo').value;
	    let max = f[i].querySelector('#valorMaximo').value;
	    let aliq = f[i].querySelector('#aliquota').value;

	    console.log(min + ' '+ max + ' ' + aliq)
	    faixas += {
	        'valorMinimo': min,
	        'valorMaximo':max,
	        'aliquota': aliq
	    }
	    console.log(faixas)
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