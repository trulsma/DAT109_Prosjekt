/*<![CDATA[*/
var ctx = document.getElementById('myBar');

function randomRGBA() {
    var o = Math.round, r = Math.random, m = 255;

    return 'rgba(' + o(r() * m) + ',' + o(r() * m) + ',' + o(r() * m) + ',';
}

$.ajax({
    url: "/api/prosjekter/stemmer",
    data: {},
    success: visGraf
})

function visGraf(resultat) {
    let prosjekt = [];

    console.log("#reee");


    var random1 = randomRGBA();
    var random2 = randomRGBA();
    var random3 = randomRGBA();
//console.log(resultat[0])

    let label = resultat.map(a => a.prosjektnavn);
    let data = resultat.map(a => a.gjennomsnittVerdi);
    var myBar = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['prosjekter'],
            datasets: [{
                label: label,
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
                        //max: 5
                    }
                }]
            }
        }
    });
}

/*]]>*/