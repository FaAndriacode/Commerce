function submitForm() {
    // Get the selected date from the input field
    var selectedDate = document.getElementById("date").value;

    // Create a FormData object and append the selected date to it
    var formData = new FormData();
    formData.append("date", selectedDate);

    // Make an AJAX request to the Spring Boot backend using the fetch API
    fetch('http://localhost:8080/boncommande/calcul', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (response.ok) {
            // Handle successful response
            alert("Calculation successful");
        } else {
            // Handle error
            alert("Calculation failed");
        }
    })
    .catch(error => {
        // Handle network error
        alert("Network error");
    });
}