function submitForm() {
    // Get form values
    var nom = document.getElementById("nom").value;
    var unite = document.getElementById("unite").value;

    // Create JSON object
    var articleData = {
        "nom": nom,
        "unite": unite
    };

    // Make POST request
    fetch('http://localhost:8080/article/insert', {
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