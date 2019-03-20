let ctxGjennomsnitt = document.getElementById('gjennomsnitt');
let ctxAntall = document.getElementById('antall');


let prosjektid = document.getElementById("prosjektid").value;

$.ajax({
    url: "/api/prosjekt/" + prosjektid + "/stemmer",
    data: {},
    success: visGraf
})

function visGraf(resultat) {
    let gjennomsnitt = new Chart(ctxGjennomsnitt, {
        type: 'scatter',
        data: {
            datasets: [{
                label: 'Gjennomsnittverdi over tid',
                fill: true,
                showLine: true,
                backgroundColor: 'rgba(75,192,192,0.4)',
                pointBorderColor: 'rgba(75,192,192,1)',
                pointBackgroundColor: '#fff',
                pointBorderWidth: 1,
                pointHoverRadius: 5,
                pointHoverBackgroundColor: 'rgba(75,192,192,1)',
                pointHoverBorderColor: 'rgba(220,220,220,1)',
                pointHoverBorderWidth: 2,
                pointRadius: 1,
                pointHitRadius: 10,
                data: createDatasetGjennomsnitt(resultat.slice())
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        max: 5,
                        min: 0
                    }
                }]
            }
        }
    });

    let antall = new Chart(ctxAntall, {
        type: 'scatter',
        data: {
            datasets: [{
                label: 'Antall stemmer over tid',
                fill: true,
                showLine: true,
                backgroundColor: 'rgba(75,192,192,0.4)',
                pointBorderColor: 'rgba(75,192,192,1)',
                pointBackgroundColor: '#fff',
                pointBorderWidth: 1,
                pointHoverRadius: 5,
                pointHoverBackgroundColor: 'rgba(75,192,192,1)',
                pointHoverBorderColor: 'rgba(220,220,220,1)',
                pointHoverBorderWidth: 2,
                pointRadius: 1,
                pointHitRadius: 10,
                data: createDatasetAntall(resultat.slice())
            }]
        }
    });
}

function createDatasetAntall(raw_data) {
    raw_data.forEach(a => a.stemmetidspunkt = new Date(a.stemmetidspunkt));
    let sortert_paa_tid = raw_data.sort((a, b) => a.stemmetidspunkt - b.stemmetidspunkt);

    let start = sortert_paa_tid[0].stemmetidspunkt;
    let slutt = sortert_paa_tid[sortert_paa_tid.length - 1].stemmetidspunkt;

    let start_ms = start.valueOf();
    let slutt_ms = slutt.valueOf();
    let interval = slutt_ms - start_ms;

    let dataset = [];

    for (let i = 0; i < sortert_paa_tid.length; i++) {
        let time = sortert_paa_tid[i].stemmetidspunkt.valueOf();

        dataset.push({
            x: (time-start_ms) / interval,
            y: (i + 1)
        });
    }

    return dataset;
}

function createDatasetGjennomsnitt(raw_data) {
    raw_data.forEach(a => a.stemmetidspunkt = new Date(a.stemmetidspunkt));
    let sortert_paa_tid = raw_data.sort((a, b) => a.stemmetidspunkt - b.stemmetidspunkt);

    let start = sortert_paa_tid[0].stemmetidspunkt;
    let slutt = sortert_paa_tid[sortert_paa_tid.length - 1].stemmetidspunkt;

    let start_ms = start.valueOf();
    let slutt_ms = slutt.valueOf();
    let interval = slutt_ms - start_ms;

    let dataset = [];

    let total = 0;
    for (let i = 0; i < sortert_paa_tid.length; i++) {
        total += sortert_paa_tid[i].stemmeverdi;
        let time = sortert_paa_tid[i].stemmetidspunkt.valueOf();

        dataset.push({
            x: (time-start_ms) / interval,
            y: total / (i+1)
        });
    }

    return dataset;
}
