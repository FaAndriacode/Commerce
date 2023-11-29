function submitForm() {
    // Get the selected date from the input field
    var selectedDate = document.getElementById("date").value;
    var livraison = document.getElementById("livraison").value;
    var paiement = document.getElementById("paiement").value;

    // Create a FormData object and append the selected date to it
    var formData = new FormData();
    formData.append("date", selectedDate);

    var containerDiv = document.getElementById("container");
    containerDiv.innerHTML = "";

    // Make an AJAX request to the Spring Boot backend using the fetch API
    fetch('http://localhost:8080/boncommande/calcul', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data.donner);

        data.donner.forEach(partie => {
            var bonCommandeDiv = document.createElement("div");
        
            console.log("idfournisseur" + partie[0].idfournisseur)
            bonCommandeDiv.innerHTML = `
                <h2>Bon de Commande</h2>
                <div>
                    <strong>Fournisseur:</strong> ${partie[0].nomfournisseur}<br>
                    <strong>Date de tirage:</strong> ${partie[0].date}
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>Designation</th>
                            <th>Quantité</th>
                            <th>Prix unitaire</th>
                            <th>Prix HT</th>
                            <th>total</th>
                        </tr>
                    </thead>
                    <tbody id="id${partie[0].idfournisseur}">`;
        
            bonCommandeDiv.innerHTML += `
                    </tbody>
                </table>
                <div id="total">
                    <strong>Taxes:</strong>20%<br>
                    <strong>Livraison:</strong><br>
                    <strong>TOTAL DE LA COMMANDE TTC:</strong>${partie[0].lettre}
                </div>
                <div id="footer">
                    <p>Mode de paiement: ${paiement}</p>
                    <p>Livraison partielle: ${livraison}</p>
                </div>
            `;
            containerDiv.appendChild(bonCommandeDiv);

            var tbody = document.getElementById("id"+partie[0].idfournisseur);
            console.log("ina ito == "+"id"+partie[0].idfournisseur);
            for (let i = 0; i < partie.length; i++) {
                console.log('tonga 1 = ', tbody);
                var tr = document.createElement("tr");
                    var td1 = document.createElement("td");
                    var td2 = document.createElement("td");
                    var td3 = document.createElement("td");
                    var td4 = document.createElement("td");
                    var td5 = document.createElement("td");

                    td1.textContent = partie[i].nomarticle;
                    td2.textContent = partie[i].quantite;
                    td3.textContent = partie[i].prixunitaire;
                    td4.textContent = partie[i].pht;
                    td5.textContent = partie[i].total;

                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    tr.appendChild(td3);
                    tr.appendChild(td4);
                    tr.appendChild(td5);
                tbody.appendChild(tr);
            }

        });
        
        
    })
    .catch(error => {
        // Handle network error
        
        alert(error);
    });

}