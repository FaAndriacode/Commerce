var liste
var form
window.addEventListener("load", function () {
    //prendre l'element de la form
    form = this.document.getElementById("formD");

    liste = this.document.getElementById('listeArticle')

    // Envoi de la requête GET avec les paramètres dans l'URL
    sendData();

    // Fonction d'envoi de la requête
    function sendData() {
        var xhr = new XMLHttpRequest();

        // Préparation de la requête GET vers l'URL spécifiée avec les paramètres dans l'URL
        var req = "http://localhost:8080/article/all";
        xhr.open("GET", req);

        // Gestion de l'événement de chargement
        xhr.onload = function () {
            if (xhr.status === 200) {
                var value = JSON.parse(xhr.responseText);
                if (value.erreur == false) {
                    console.log("Success ", value);
                    create_select(value.donner);
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

function var_select(donner) {
    return `
        <option value="${donner.idArticle}">${donner.nom}</option>
    `;
}

function create_select(donner) {
    var optionStringify = ""

    for (let i = 0; i < donner.length; i++) {
        optionStringify += var_select(donner[i]);
    }
    liste.innerHTML = optionStringify;
}



async function validateBesoin() { //insertion de besoin du besoin
        var xhr = new XMLHttpRequest();
        // Préparation de la requête GET vers l'URL spécifiée avec les paramètres dans l'URL
        var req = "http://localhost:8080/list/creation";
        xhr.open("POST", req);

        console.log("creation ");

        // Gestion de l'événement de chargement
        xhr.onload = function () {
            if (xhr.status === 200) {
                var value = JSON.parse(xhr.responseText);
                if (value.erreur == false) {
                    console.log("Insertion Success ", value);
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

         // Envoi des données du formulaire sous forme de FormData
        var formData = new FormData(form);
        
        // Envoi de la requête GET sans utiliser FormData
        xhr.send(formData);
}