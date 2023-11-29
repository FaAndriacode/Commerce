// Function to populate the Article dropdown
function populateArticleDropdown() {
    var articleSelect = document.getElementById("articleSelect");

    // Make an API call to get the article data
    fetch('http://localhost:8080/article/all') // Assuming you have an endpoint to get all articles
        .then(response => response.json())
        .then(data => {
            console.log('Response Data:', data);
            if (Array.isArray(data.donner)) {
                // Populate the dropdown with article options
                data.donner.forEach(article => {
                    var option = document.createElement("option");
                    option.value = article.idArticle; // Assuming idArticle is the ID property
                    option.text = article.nom; // Assuming nom is the property for the display text
                    articleSelect.add(option);
                });
            } else {
                console.error('Invalid data format:', data);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


    function populateFournisseurDropdown() {
        var fournisseurSelect = document.getElementById("fournisseurSelect");

        // Make an API call to get the article data
        fetch('http://localhost:8080/proformat/fournisseur') // Assuming you have an endpoint to get all articles
            .then(response => response.json())
            .then(data => {
                // Check if data.donner is an array
                if (Array.isArray(data.donner)) {
                    // Populate the dropdown with fournisseur options
                    data.donner.forEach(fournisseur => {
                        var option = document.createElement("option");
                        option.value = fournisseur.idFournisseur;
                        option.text = fournisseur.nom;
                        fournisseurSelect.add(option);
                    });
                } else {
                    console.error('Invalid data format:', data);
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
        

    window.onload = function () {
        populateArticleDropdown();
	    populateFournisseurDropdown();
    };

    function submitForm() {
        // Get form values
        var fournisseur = document.getElementById("fournisseurSelect").value;
        var article = document.getElementById("articleSelect").value;
        var quantite = document.getElementById("quantite").value;
        var prixunitaire = document.getElementById("prixunitaire").value; // Corrected ID
        var date = document.getElementById("date").value;

        // Create JSON object
        var articleData = {
            "idfournisseur": fournisseur,
            "idarticle": article,
            "prixunitaire": prixunitaire, // Corrected field name
            "quantite": quantite,
            "date": date
        };
    
        // Make POST request
        fetch('http://localhost:8080/fournarticle/insert', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(articleData)
        })
        .then(response => response.text())
        .then(data => {
            console.log('Success:', data);
            // Optionally, you can handle the success response here
        })
        .catch((error) => {
            console.error('Error:', error);
            // Optionally, you can handle errors here
        });
    }