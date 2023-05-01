document.addEventListener("DOMContentLoaded", main);


function main() {
    let handler = new UsernameAndPositionHandler();
    handler.requestUsername();
}


class UsernameAndPositionHandler {
    username;
    position;

    /**
     * Metodo per la ricezione dello username temporaneo
     */
    requestUsername() {
        let request = new XMLHttpRequest();
        request.open("GET", "http://localhost:8080/FYND_war_exploded/username");

        request.onload = function() {
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
        const id = navigator.geolocation.watchPosition(this.successPosition, this.errorPosition)
    }

    /**
     * Metodo invocato a ogni spostamento da {@link getSelfPosition}
     * in caso di successo
     * @param position
     */
    successPosition(position) {
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
     * invocato da {@link successPosition} ad ogni spostamento
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









