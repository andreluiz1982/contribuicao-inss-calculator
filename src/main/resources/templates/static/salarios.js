let contribuintes = document.getElementById("contribuintes");
let valorMinimo = document.getElementById("valorMinimo");
let valorMaximo = document.getElementById("valorMaximo");
let aliquota = document.getElementById("aliquota");
let anoMes = document.getElementById("anoMes");
let faixaAliquota = document.getElementById("faixaAliquota");
let form = document.getElementById("form");
let response = document.getElementById("response");
let componentes = document.getElementsByClassName("componentes");
let componente = document.getElementById("componente");

loadContribuintes();
addComponentes();
let allContribuintes = [];
function enviar() {
	
	let faixas = [];
	for(let i = 1; i <= 4; i++){
	    let f = document.getElementById('faixaAliquota'+i);

	    let min = document.getElementById('valorMinimo'+i).value;
	    let max = document.getElementById('valorMaximo'+i).value;
	    let aliq = document.getElementById('aliquota'+i).value;

	    if(min != '' && max != '' && aliq != ''){

	    faixas.push( {
	        'valorMinimo': min,
	        'valorMaximo':max,
	        'aliquota': aliq
	    });
	    }


	}


	let json = JSON.stringify({
		anoMes: anoMes.value, faixasAliquotas: faixas
	});
	console.log(JSON.parse(json))
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/aliquota", true)
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhr.onload = function() {
		let resp = JSON.parse(this.response);
		console.log(JSON.parse(this.response).nomeCompleto);
		window.alert(`Aliquotas do mes ${resp.anoMes} salva com sucesso.`)
	    location.reload();
	};
	xhr.send(json);


}

function addComponentes() {
    if(event.key === 'Enter') {

    var div = document.createElement('div');
    div.setAttribute('class', 'componentes m-1 border border-secondary rounded');
    div.innerHTML = `
       <label class="form-label">Componente salário - INSS</label>
       				<input class="inputSalario form-control" onkeydown='addComponentes(this)' accept="text" required="true"  placeholder="Digite o valor" id="componenteSalario" >
       				<label class="form-label">Descrição (opcional) - Enter para outro componente </label>
       				<input class="inputDescricao form-control" onkeydown='addComponentes(this)' accept="text" required="true" placeholder="Descrição (opcional) Ex. Horas Extraordinárias" id="descricao">
    `;
    form.appendChild(div);
    }
};

function loadDataContribuinte(nome)  {


    let john = allContribuintes.find((obj) => obj.nomeCompleto == nome);
    let resp = `<table class='table'><thead><tr><th>Ano-Mês</th><th>Salários Contribuição</th></tr></thead><tbody>`;
    john.salariosContribuicao.forEach((sl) => {
        let compIncidencia = '';

        sl.componentesIncidencia.forEach((comp) => {
        compIncidencia += comp + " ";
    });
        resp += `<tr>
                    <td class="align-middle">${sl.anoMes}</td>
                    <td class="align-middle">${compIncidencia}</td>
                 </tr>
        `;
    });
    resp += `</tbody></table>`
    response.innerHTML = resp;


};
function limpar(){
console.log('limpar')
    let inputs = document.getElementsByTagName('input');
    inputs.forEach((p) => {
        p.innerHTML = '';
    })

}
function loadContribuintes(){
    let htmlResp =  '';
    var xhr = new XMLHttpRequest();
	xhr.open("GET", "/contribuinte/all", true)
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhr.onload = function() {
		let resp = JSON.parse(this.response);
		for(let i in resp){
			console.log(resp[i])
			allContribuintes.push(resp[i]);
			htmlResp +=  `<button class='btn btn-lg btn-warning m-1'
			onclick='loadDataContribuinte("${resp[i].nomeCompleto}")'>${resp[i].nomeCompleto}</button>`;
		

		};
	contribuintes.innerHTML = htmlResp;
	}
	xhr.send();
}