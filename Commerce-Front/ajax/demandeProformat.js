var liste

window.addEventListener("load", function () {
    // Récupération de l'URL actuelle

    liste = this.document.getElementById('listeFournisseur')

    // Envoi de la requête GET avec les paramètres dans l'URL
    sendData();

    // Fonction d'envoi de la requête
    function sendData() {
        var xhr = new XMLHttpRequest();

        // Préparation de la requête GET vers l'URL spécifiée avec les paramètres dans l'URL
        var req = "http://localhost:8080/proformat/fournisseur";
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
            <td>${donner.nom}</td>
            <td>${donner.adresse}</td>
            <td>${donner.email}</td>
            <td><button type="button" onclick="demande('${donner.nom}')"class="btn btn-outline-primary">Envoie</button></td>
        </tr>
    `;
}

function create_table(donner) {
    var listStringyFy = ''
    for (let i = 0; i < donner.length; i++) {
        listStringyFy += var_table(donner[i]);
    }
    liste.innerHTML = listStringyFy;
}



async function demande(nomForunisseur) {
    console.log("Demande proforma");

    (function () {
        emailjs.init("BwMQK5KQ6W5Z9EIU5");
    })();

    var params = {
        sendername: "Blink enterprise",
        to_name: "ramangasonialisoa@gmail.com",
        subject: "Demande de proforma",
        message: "Liste de vous produits, Quantites, Condition de payement",
    };

    var serviceID = "service_guqkq9r";
    var templateID = "template_2u7u3vm";

    try {
        const response = await emailjs.send(serviceID, templateID, params);
        console.log(response);
        alert("E-mail envoyé avec succès");
    } catch (error) {
        console.error("Erreur lors de l'envoi de l'e-mail", error);
        // Gérez l'erreur de manière appropriée (affichage d'une alerte, journalisation, etc.)
    }
}


