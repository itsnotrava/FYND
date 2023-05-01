
function loadResponse(response) {
    let username = response["username"];
    let divResponse = document.getElementById("result");
    divResponse.innerText = "Username: " + username;
}

function requestHome() {
    let request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/FYND_war_exploded/home");
    request.onload = function() {
        let jsResponse = JSON.parse(request.responseText);
        loadResponse(jsResponse);
    }
    request.send();
}

function main() {
    requestHome();
}


document.addEventListener("DOMContentLoaded", main);