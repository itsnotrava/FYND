document.addEventListener("DOMContentLoaded", main);


function main() {
    let handler = new UsernameAndPositionHandler();
    handler.requestUsername();
}


class UsernameAndPositionHandler {
    username;
    position;

    constructor() {
        this.username = "";
        this.position = "";
    }

    /**
     * Metodo per la ricezione dello username temporaneo
     */
    requestUsername() {
        let request = new XMLHttpRequest();
        request.open("GET", "http://localhost:8080/FYND_war_exploded/username");

        request.onload = () => {
            let jsResponse = JSON.parse(request.responseText);
            this.loadUsername(jsResponse);
        }

        request.send();
    }

    /**
     * Metodo per l'elaborazione dello username temporaneo
     * ricevuto, invocato da {@link requestUsername}
     * @param response
     */
    loadUsername(response) {
        // Prendi lo username
        this.username = response["username"];

        // Mostra lo username
        let divResponse = document.getElementById("result");
        divResponse.innerText = this.username;

        // Invia la propria posizione
        this.getSelfPosition();
    }

    /**
     * Metodo per la richiesta della posizione geografica invocato
     * da {@link loadUsername}
     */
    getSelfPosition() {
        const id = navigator.geolocation.watchPosition(
            (position) => {
            this.successPosition(position);
        }, (error) => {
            this.errorPosition(error);
        });
    }

    successPosition(positionObject) {
        let position = positionObject["coords"]["latitude"].toString() + " " + positionObject["coords"]["longitude"].toString();
        console.log(position);
        this.position = position;
        this.sendPosition();
    }

    /**
     * Metodo invocato a ogni spostamento da {@link getSelfPosition}
     * in caso di fallimento
     * @param error
     */
    errorPosition(error) {
        console.log(error);
    }

    /**
     * Metodo per l'aggiornamento della propria posizione sul DB,
     * invocato da {@link successPosition} a ogni spostamento
     */
    sendPosition() {
        let body = {
            "username": this.username,
            "position": this.position
        }

        let request = new XMLHttpRequest();
        request.open("POST", "http://localhost:8080/FYND_war_exploded/position");

        request.onload = function() {
            let jsResponse = JSON.parse(request.responseText);
            console.log(jsResponse);
        }

        request.send(JSON.stringify(body));
    }
}









