// Variabili globali per tenere traccia dell'annuncio corrente
var advertisements = [];
var palinsestoXML = null;
var currentIndex = 0;
var idPalinsesto = 0;
var idCartellone = 0;
var duration = 0;

document.addEventListener("DOMContentLoaded", function () {
    if (stato === 1) {
        loadPalinsesto()
        // Mando una segnalazione ogni 30 secondi
        setInterval(sendSegnalazione, 30000);
    }
    else {
        console.log("Stato disattivo");
        window.location.href = "/inattivo?id=" + id;
    }
});

function loadPalinsesto() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPalinsesto(this);
        }
    };
    xhttp.open("GET", "/xml" + pathPalinsesto, true);
    xhttp.send();
}

function displayPalinsesto(xml) {
    var xmlDoc = xml.responseXML;
    advertisements = xmlDoc.getElementsByTagName("advertisement");
    palinsestoXML = xmlDoc.getElementsByTagName("palinsesto")[0]
    //console.log("Palinsesto XML:", palinsestoXML);
    var ledWall = document.getElementById("ledWall");

    var totalDuration = 0;
    displayAdvertisement(advertisements, ledWall, totalDuration);

}

function displayAdvertisement(advertisements, ledWall, totalDuration) {
    var file = advertisements[currentIndex].getElementsByTagName("file")[0].childNodes[0].nodeValue;
    duration = parseInt(advertisements[currentIndex].getElementsByTagName("duration")[0].childNodes[0].nodeValue);
    idPalinsesto = parseInt(palinsestoXML.getAttribute("idPalinsesto"));
    idCartellone = parseInt(advertisements[currentIndex].getAttribute("idCartellone"));

    var iframe = document.createElement("iframe");
    iframe.src = file;
    iframe.className = "show";
    ledWall.innerHTML = '';
    ledWall.appendChild(iframe);
    currentIndex = (currentIndex + 1) % advertisements.length;
    totalDuration += duration;


    setTimeout(function () {
        displayAdvertisement(advertisements, ledWall, totalDuration, currentIndex)
    }, duration * 1000);
}

function sendSegnalazione() {
    var idImpianto = id;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/segnalazioni", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log("Segnalazione inviata con successo");
            } else {
                console.error("Errore durante l'invio della segnalazione:", xhr.statusText);
            }
        }
    };
    var data = {
        idImpianto: idImpianto,
        idPalinsesto: idPalinsesto,
        idCartellone: idCartellone,
        durata: duration
    };
    data = JSON.stringify(data)
    xhr.send(data);
    console.log("Dati inviati:", data);
}




// Funzione per aggiornare l'ora attuale
function updateDateTime() {
    var now = new Date();
    var day = now.getDate()
    var month = now.getMonth() + 1
    var year = now.getFullYear()
    var DateString = day + '/' + month + '/' + year
    var hours = now.getHours().toString().padStart(2, '0');
    var minutes = now.getMinutes().toString().padStart(2, '0');
    var seconds = now.getSeconds().toString().padStart(2, '0');
    var TimeString = hours + ':' + minutes + ':' + seconds;
    document.getElementById("currentDate").textContent = DateString
    document.getElementById("currentTime").textContent = TimeString;
}

// Funzione per aggiornare le informazioni meteo
function updateWeatherInfo() {
    var apiKey = typeof openWeatherApiKey !== 'undefined' ? openWeatherApiKey : '';
    if (!apiKey) {
        console.warn("OpenWeather API key is missing. Skipping weather update.");
        return;
    }
    var city = 'Palermo';
    var apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric&lang=it`;

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            var weatherIconCode = data.weather[0].icon;
            var weatherIconUrl = 'http://openweathermap.org/img/wn/' + weatherIconCode + '.png';
            var weatherDescription = data.weather[0].description;
            var weatherDescriptionUpper = weatherDescription[0].toUpperCase() + weatherDescription.slice(1)
            var temperature = data.main.temp;
            var cityName = data.name;
            document.getElementById("weatherInfo").textContent = weatherDescriptionUpper;
            document.getElementById("cityInfo").textContent = `${cityName}`;
            document.getElementById("temperatureInfo").textContent = `${temperature.toFixed(0)}°C`;
            var weatherIconElement = document.getElementById('weatherIcon');
            weatherIconElement.src = weatherIconUrl;
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}


updateWeatherInfo()
setInterval(updateDateTime, 1000);
//Aggiorniamo il meteo ogni ora
setInterval(updateWeatherInfo, 3600000);