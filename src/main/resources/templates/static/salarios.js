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
		window.alert(`Aliquotas do mes ${resp.anoMes} salva com sucesso.`)
	    location.reload();
	};
	xhr.send(json);


}

function loadAliquotas(){
    let htmlResp =  `<table class='table'><thead><tr><th scope='col'>Ano-Mês</th><th scope='col'>Mín.</th>
                        <th scope='col'>Máx</th><th scope='col'>Aliq.</th></tr></thead><tbody>`;
    var xhr = new XMLHttpRequest();
	xhr.open("GET", "/aliquota/all", true)
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhr.onload = function() {
		let resp = JSON.parse(this.response);
		for(let i in resp){
		console.log(resp[i])
		    let anoMes = resp[i].anoMes;
		    let fx =  resp[i].faixasAliquotas;
	    	let cols = "'"+(fx.length+1).toString()+"'";
		    console.log(cols)
            htmlResp += `<tr>
                             <td class='align-middle' rowspan=${cols}>${anoMes}</td>
                          </tr>`;
        for(let f in fx){
            htmlResp += `
                <tr>
                    <td class="align-middle">${fx[f].valorMinimo}</td>
                    <td class="align-middle">${fx[f].valorMaximo}</td>
                    <td class="align-middle">${fx[f].aliquota}</td>
                </tr>

            `;
        }


		}
		htmlResp += `</tbody></table>`;
		response.innerHTML = htmlResp;
	};
	xhr.send();

}