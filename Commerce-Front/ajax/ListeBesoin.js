var liste
var etat
window.addEventListener("load", function () {
    // Récupération de l'URL actuelle
    var urlParams = new URLSearchParams(window.location.search);

    // Récupération des valeurs des paramètres
    etat = urlParams.get('etat');
    liste = this.document.getElementById('listeBesoin')

    // Envoi de la requête GET avec les paramètres dans l'URL
    sendData(etat);

    // Fonction d'envoi de la requête
    function sendData(etat) {
        var xhr = new XMLHttpRequest();

        // Préparation de la requête GET vers l'URL spécifiée avec les paramètres dans l'URL
        var req = "http://localhost:8080/list/besoin?etat=" + etat;
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
            <td>${donner.article.nom}</td>
            <td>${donner.quantite}</td>
            <td>${donner.service.nom}</td>
            <td><button type="button" onclick="validateBesoin(${donner.idBesoin})" class="btn btn-outline-success">✔️</button></td>
            <td><button type="button" onclick="refuseBesoin(${donner.idBesoin})" class="btn btn-outline-danger">❌</button></td>
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

    // Ajoutez un gestionnaire d'événements aux boutons
    const buttons = document.querySelectorAll('.btn-outline-success, .btn-outline-danger');
    buttons.forEach(button => {
        button.addEventListener('click', function () {
            // Supprimez la ligne parente (tr) du bouton cliqué
            this.closest('tr').remove();
        });
    });
}



async function validateBesoin(idBesoin) { //update de l'etat du besoin
    var xhr = new XMLHttpRequest();

        // Préparation de la requête GET vers l'URL spécifiée avec les paramètres dans l'URL
        var req = "http://localhost:8080/list/confirmer?idbesoin=" + idBesoin;
        xhr.open("GET", req);

        console.log("validation ",idBesoin);

        // Gestion de l'événement de chargement
        xhr.onload = function () {
            if (xhr.status === 200) {
                var value = JSON.parse(xhr.responseText);
                if (value.erreur == false) {
                    console.log("Update Success ", value);
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

async function refuseBesoin(idBesoin) { //update de l'etat du besoin
    var xhr = new XMLHttpRequest();

        // Préparation de la requête GET vers l'URL spécifiée avec les paramètres dans l'URL
        var req = "http://localhost:8080/list/delete?idbesoin=" + idBesoin;
        xhr.open("GET", req);

        console.log(idBesoin);

        // Gestion de l'événement de chargement
        xhr.onload = function () {
            if (xhr.status === 200) {
                var value = JSON.parse(xhr.responseText);
                if (value.erreur == false) {
                    console.log("Update Success ", value);
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
