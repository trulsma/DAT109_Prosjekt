let stemmeverdi = document.getElementById('stemmeverdi');
let antall = document.getElementById('antall');
let stemmeverdi_visning = document.getElementById('stemmeverdi_visning');
let antall_visning = document.getElementById('antall_visning');

function visData(prosjektid, arrangementid) {
        $.ajax({
        url: "/api/prosjekt/{0}/arrangement/{1}?stepSize=10".replace("{0}", prosjektid).replace("{1}", arrangementid),
        data: {},
        success: visGrafer
    })
}

function toHHMM(date) {
    return ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2);
}

function visGrafer(data) {
    if (data.length == 0) {
        stemmeverdi_visning.innerText = 0;
        antall_visning.innerText = 0;
    }
    else {
        stemmeverdi_visning.innerText = data[data.length - 1].stemmeverdi;
        antall_visning.innerText = data[data.length - 1].antall;
    }
    visGrafAntall(data);
    visGrafStemmeverdi(data);
}

function visGrafStemmeverdi(data) {
    let stemmeVerdiGraf = new Chart(stemmeverdi, {
        type: 'line',
        data: {
            labels: data.map(a => toHHMM(new Date(a.stemmetidspunkt))),
            datasets: [{
                label: "Gjennomsnittlig stemmeverdi over tid",
                data: data.map(a => a.stemmeverdi),
                borderWidth: 1,borderColor: [
                    'rgba(255, 0, 128, 1)',
                ],
                backgroundColor: [
                    'rgba(255, 0, 128, 0.1)',
                ],
            }]
        },
        options: {
            elements: {
                line: {
                    tension: 0
                }
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        max: 5
                    }
                }]
            }
        }
    });
}


function visGrafAntall(data) {
    let antallGraf = new Chart(antall, {
        type: 'line',
        data: {
            labels: data.map(a => toHHMM(new Date(a.stemmetidspunkt))),
            datasets: [{
                label: "Antall stemmer over tid",
                data: data.map(a => a.antall),
                lineTension: 0,
                borderWidth: 1,borderColor: [
                    'rgba(0, 0, 255, 1)',
                ],
                backgroundColor: [
                    'rgba(0, 0, 255, 0.1)',
                ],
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
}