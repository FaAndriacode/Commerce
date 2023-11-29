var liste
window.addEventListener("load", function () {
    // Récupération des valeurs des paramètres
    liste = this.document.getElementById('unite')

    // Envoi de la requête GET avec les paramètres dans l'URL
    sendData();

    // Fonction d'envoi de la requête
    function sendData() {
        var xhr = new XMLHttpRequest();
    
        // Préparation de la requête GET vers l'URL spécifiée avec les paramètres dans l'URL
        var req = "http://localhost:8080/init/unite";
        xhr.open("GET", req);
    
        // Gestion de l'événement de chargement
        xhr.onload = function () {
            if (xhr.status === 200) {
                var value = JSON.parse(xhr.responseText);
                if (value.erreur == false) {
                    console.log("unite ", value);
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

function var_option(donner) {
    return `
        <option value="${donner.idUnite}">${donner.nom}</option>
    `;
}

function create_table(donner) {
    if (!Array.isArray(donner)) {
        //console.error('Données invalides. Un tableau est attendu.');
        return;
    }
    const stringOption = donner.map(var_option).join('');
    liste.innerHTML = stringOption;
}