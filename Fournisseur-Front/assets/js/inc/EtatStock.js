var liste
var idMagasin
window.addEventListener("load", function () {
    // Récupération des valeurs des paramètres
    liste = this.document.getElementById('listeStock')
    idMagasin = this.localStorage.getItem("id")

    // Envoi de la requête GET avec les paramètres dans l'URL
    sendData(idMagasin);

    // Fonction d'envoi de la requête
    function sendData(idMagasin) {
        var xhr = new XMLHttpRequest();
    
        // Préparation de la requête GET vers l'URL spécifiée avec les paramètres dans l'URL
        var req = "http://localhost:8080/etat/stock?idMagasin=" + idMagasin;
        xhr.open("GET", req);
    
        // Gestion de l'événement de chargement
        xhr.onload = function () {
            if (xhr.status === 200) {
                var value = JSON.parse(xhr.responseText);
                if (value.erreur == false) {
                    console.log("Success ", value);
                    create_table(value.donner);
                } else if (value.erreur == true) {
                    alert("Une erreur s'est produite. Veuillez contacter le chef.");
                }
            } else {
                console.error('Erreur lors de la requête. Statut:', xhr.status);
            }
        };
    
        // Gestion de l'événement d'erreur
        xhr.onerror = function () {
            console.error('Erreur réseau lors de la requête.');
        };
    
        // Envoi de la requête GET sans utiliser FormData
        xhr.send();
    }
    
});

function var_table(donner) {
    return `
        <tr>
            <td>${donner.nomArticle}</td>
            <td>${donner.nomMagasin}</td>
            <td>${donner.stockInitial}</td>
            <td>${donner.stockFinal}</td>
            <td>${donner.montantT}</td>
            <td>${donner.prixMoyenne}</td>
        </tr>
    `;
}

function create_table(donner) {
    if (!Array.isArray(donner)) {
        //console.error('Données invalides. Un tableau est attendu.');
        return;
    }
    const stringTable = donner.map(var_table).join('');
    liste.innerHTML = stringTable;
}