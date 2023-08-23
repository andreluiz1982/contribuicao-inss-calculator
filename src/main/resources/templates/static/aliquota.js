let valorMinimo = document.getElementById("valorMinimo");
let valorMaximo = document.getElementById("valorMaximo");
let aliquota = document.getElementById("aliquota");
let anoMes = document.getElementById("anoMes");
let faixaAliquota = document.getElementById("faixaAliquota");
let form = document.getElementById("form");
let response = document.getElementById("response");
loadAliquotas();

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
		window.alert(`Aliquota do mes ${resp.anoMes}, percentual ${resp.aliquota},
			valor mínimo ${resp.valorMinimo} e valor máximo ${resp.valorMaximo}, salva com sucesso.`)
	};
	xhr.send(json);
	loadAliquotas();
	location.reload();

}

function loadAliquotas(){
    let htmlResp =  `<table><thead><tr><th>Ano-Mês</th><th>Mín.</th><th>Máx</th><th>Aliq.</th></tr></thead><tbody>`;
    var xhr = new XMLHttpRequest();
	xhr.open("GET", "/aliquota/all", true)
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhr.onload = function() {
		let resp = JSON.parse(this.response);
		for(let i in resp){
		    let anoMes = resp[i].anoMes;
		    let fx =  resp[i].faixasAliquotas;
        for(let f in fx){
            htmlResp += `
                <tr>
                    <td>${anoMes}</td>
                    <td>${fx[f].valorMinimo}</td>
                    <td>${fx[f].valorMaximo}</td>
                    <td>${fx[f].aliquota}</td>
                </tr>

            `;
        }


		}
		htmlResp += `</tbody></table>`;
		response.innerHTML = htmlResp;
	};
	xhr.send();

}