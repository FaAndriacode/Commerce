window.addEventListener("load",function(){
    //prendre l'element de la form
    var form = this.document.getElementById("formD");

    // Récupération de l'URL actuelle
    var urlParams = new URLSearchParams(window.location.search);

    // Récupération des valeurs des paramètres
    var etat = urlParams.get('etat');
    var error = this.document.getElementById('notification')
    
    //prise en charge du button submit
    form.addEventListener("submit",function(event) {
        event.preventDefault();//bloque le submit par defaut
        sendData(form); 
    })

     //send Data element
    function sendData() {
        var xhr = new XMLHttpRequest();
    
        // Préparation de la requête POST vers l'URL spécifiée
        var req = "http://localhost:8080/auth/login";
        xhr.open("POST", req);

    
        // Gestion de l'événement de chargement
        xhr.onload = function () {
            if (xhr.status === 200) {
                var value = JSON.parse(xhr.responseText);
                if(value.erreur == false){
                    console.log("Success ",value);
                    console.log("etat ",etat);
                    if(etat == 1){//chef de departement
                        window.location.href = "dashbord.html";
                    }else if (etat == 0) {//chef de services
                        window.location.href = "creationBesoin.html";
                    }
                }else if(value.erreur == true){
                    error.innerHTML=`<div class="alert alert-danger" role="alert">veuillez verifier votre insertion</div>`
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
        
        // Ajout des champs etat à FormData
        formData.append('etat', etat);

        xhr.send(formData);
    }
    
 });