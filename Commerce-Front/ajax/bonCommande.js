function submitForm() {
    // Get the selected date from the input field
    var selectedDate = document.getElementById("date").value;

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
                        </tr>
                    </thead>
                    <tbody id="id${partie[0].idboncommande}">`;
        
            var tbody = document.getElementById("id"+partie[0].idboncommande);
            for (let i = 0; i < partie.length; i++) {
                tbody.innerHTML += `
                    <tr>
                        <td>${partie[i].nom}</td>
                        <td>${partie[i].quantite}</td>
                        <td>${partie[i].prixunitaire}</td>
                    </tr>`;
            }
        
            bonCommandeDiv.innerHTML += `
                    </tbody>
                </table>
                <div id="total">
                    <strong>Sous-total:</strong><br>
                    <strong>Taxes:</strong><br>
                    <strong>Livraison:</strong><br>
                    <strong>TOTAL DE LA COMMANDE:</strong>
                </div>
                <div id="footer">
                    <p>Conditions de paiement: Paiement à réception de la facture.</p>
                    <p>Conditions générales: Les produits seront expédiés dans les 3 jours ouvrables.</p>
                </div>
            `;
        
            containerDiv.appendChild(bonCommandeDiv);
        });
        
        
    })
    .catch(error => {
        // Handle network error
        alert("Network error");
    });

}