/*<![CDATA[*/
var bar = document.getElementById('myBar');
var ctx = document.getElementById('myLine')

function randomRGBA() {
    var o = Math.round, r = Math.random, m = 255;

    return 'rgba(' + o(r() * m) + ',' + o(r() * m) + ',' + o(r() * m) + ',';
}

/*
$.ajax({
    url: "/api/prosjekter/stemmer",
    data: {},
    success: visGraf
})
*/
let random1 = randomRGBA();
let random2 = randomRGBA();
let random3 = randomRGBA();


function visGraf(resultat) {
    let prosjekt = [];

    console.log("#reee");


//console.log(resultat[0])

    let label = resultat.map(a => a.prosjektnavn);
    let data = resultat.map(a => a.gjennomsnittVerdi);
    var myBar = new Chart(bar, {
        type: 'bar',
        data: {
            labels: label,
            datasets: [{
                label: 'Prosjekter',
                data: data,
                borderColor: [
                    random1 + '1)',
                    random2 + '1)',
                    random3 + '1)'
                ],
                backgroundColor: [
                    random1 + '0.1)',
                    random2 + '0.1)',
                    random3 + '0.1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
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


let myLine = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['prosjekt1', 'prosjekt2', 'prosjekt3'],
        datasets: [{
            label: ['prosjekt1', 'prosjekt2', 'prosjekt3'],
            data: [1, 2, 3],
            borderColor: [
                random1 + '1)',
                random2 + '1)',
                random3 + '1)'
            ],
            backgroundColor: [
                random1 + '0.1)',
                random2 + '0.1)',
                random3 + '0.1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
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


function visGraf(resultat) {
    let prosjekt = [];

//console.log(resultat[0])


}

function triggerSelect() {
    $('#fuck').selectpicker();
    $('#picker').selectpicker();

}

$(document).ready(function () {
    $('#addProsjektModal').on('show', function () {
        triggerSelect();

    });
});

/*]]>*/