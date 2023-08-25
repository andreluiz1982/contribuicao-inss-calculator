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
let allContribuintes = [];
let contribuinteSelecionado = {};

loadContribuintes();
addComponentes();


function enviar() {
	
	let comp = document.querySelectorAll('#componente') //document.getElementsByClassName("componentes");
	let componentesSalario = [];
	
	for(let i = 0 ; i <  comp.length; i++){
		let compHtml = comp.item(i);
		let descricao = compHtml.getElementsByClassName("inputDescricao").namedItem('descricao').value;
		let salario = compHtml.getElementsByClassName("inputSalario").namedItem('componenteSalario').value;
	
		let componente = {
			'descricao': descricao,
			'valorComponente': salario
		}
		componentesSalario.push(componente);

		}
	console.log(componentesSalario)

	let json = JSON.stringify({
		anoMes: anoMes.value, componentesIncidencia: componentesSalario, 
			contribuinte: {id: contribuinteSelecionado.id,
							nomeCompleto: contribuinteSelecionado.nomeCompleto,
							cpf: contribuinteSelecionado.cpf
						
						}
	});
	
	console.log(contribuinteSelecionado)
	console.log(JSON.parse(json))
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/salario-base", true)
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhr.onload = function() {
		let resp = JSON.parse(this.response);
		console.log(JSON.parse(this.response));
		window.alert(`salario ${resp} salvo com sucesso.`)
	    location.reload();
	};
	xhr.send(json);


}

function addComponentes() {
    if(event.key === 'Enter') {

    var div = document.createElement('div');
    div.setAttribute('class', 'componentes m-1 border border-info rounded');
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
    contribuinteSelecionado = john;
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
		
	//	console.log(resp)

		for(let i in resp){
			//console.log(resp[i])
			allContribuintes.push(resp[i]);
			htmlResp +=  `<button class='btn btn-lg btn-warning m-1'
			onclick='loadDataContribuinte("${resp[i].nomeCompleto}")'>${resp[i].nomeCompleto}</button>`;
		

		};
	contribuintes.innerHTML = htmlResp;
	}
	xhr.send();
}