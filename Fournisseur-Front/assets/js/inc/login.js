var alert = document.getElementById("notification");

document.getElementById("loginButton").addEventListener("click", function () {
    var form = document.getElementById("loginForm");
    var formData = new FormData(form);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/auth/login", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let value = JSON.parse(xhr.responseText)
            if(value.erreur == false){
                localStorage.setItem('id',value.donner.idMagasin)
                window.location.href = "dashbord.html";
            }else{
                alert.innerHTML = alertNotification(value.information);
            }
            
        }
    };

    xhr.send(new URLSearchParams(formData));
});

function alertNotification(value) {
    return`<div class="alert alert-danger"role="alert">`+value+`</div>`
}