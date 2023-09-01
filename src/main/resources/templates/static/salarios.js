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


addListeners();



loadContribuintes();



function addListeners() {
	let inputDescricao = document.getElementsByClassName("inputDescricao form-control");
	let adicionarOutro = document.getElementsByClassName("adicionarOutro btn btn-lg btn-light mt-1");
    
    
    for (let i = 0; i < adicionarOutro.length; i++) {

        adicionarOutro.item(i).addEventListener("click", addComponentes());
    }
    for (let i = 0; i < inputDescricao.length; i++) {
        inputDescricao.item(i).addEventListener("keydown", (e) => {
            if (e.key === 'Enter') {

                console.log("key");
                console.log(inputDescricao.length);
                addComponentes();
            }

        });
    }
}

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
			contribuinte: {'id': contribuinteSelecionado.id,
							'nomeCompleto': contribuinteSelecionado.nomeCompleto,
							'cpf': contribuinteSelecionado.cpf
						
						}
	});
	
	console.log(contribuinteSelecionado.id)
	console.log(JSON.parse(json))
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/salario-base", true)
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhr.onload = function() {
		let resp = JSON.parse(this.response);
		console.log(JSON.parse(this.response));
		window.alert(`salario ${resp} salvo com sucesso.`)
	    //location.reload();
	};
	xhr.send(json);


}



function addComponentes() {

    var div = document.createElement('div');
    div.setAttribute('class', 'componentes m-1 border border-info rounded');
    div.innerHTML = `
       				<label class="form-label">Componente salário - INSS</label>
       				<input class="inputSalario form-control"  accept="text" required="true"  placeholder="Digite o valor" id="componenteSalario" >
       				<label class="form-label">Descrição (opcional) - Enter para outro componente </label>
       				<input class="inputDescricao form-control"  accept="text" required="true" placeholder="Descrição (opcional) Ex. Horas Extraordinárias" id="descricao">
       				<button class="adicionarOutro btn btn-lg btn-light mt-1">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
							fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
  					<path
								d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
  					<path
								d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
					</svg>Clique para adicionar outro item</button>
    `;
    
    form.appendChild(div);
};

function loadDataContribuinte(nome)  {


    let john = allContribuintes.find((obj) => obj.nomeCompleto == nome);
   let table = document.createElement('table');
   table.setAttribute('class', 'table');
   let thead = document.createElement('thead');
   let tr = document.createElement('tr');
   let th1 = document.createElement('th');
   let th2 = document.createElement('th');

   th1.innerHTML = 'Ano-Mês';
   th2.innerHTML = 'Salários Contribuição';

   tr.appendChild(th1);
   tr.appendChild(th2);
   thead.appendChild(tr);
   table.appendChild(thead);

   let tbody = document.createElement('tbody');

    john.salariosContribuicao.forEach((sl) => {
     let trBody = document.createElement('tr');
     let td = document.createElement('td');
     td.innerHTML = sl.anoMes;
        trBody.appendChild(td);
        let td2 = document.createElement('td');
        let ol = document.createElement('ol');
        sl.componentesIncidencia.forEach((comp) => {
            let li = document.createElement('li');
            li.innerHTML = comp.descricao +" valor: R$" + comp.valorComponente;
            ol.appendChild(li);
        });

            td2.appendChild(ol);
            trBody.appendChild(td2);
            trBody.onclick = editSalario(sl);
            tbody.appendChild(trBody);
    });
  contribuinteSelecionado = john;
  table.appendChild(tbody);
  response.appendChild(table);
	

};

function editSalario(sal){
    console.log(sal.anoMes)

}

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