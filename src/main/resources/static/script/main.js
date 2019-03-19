/*<![CDATA[*/
var ctx = document.getElementById('myBar');

function randomRGBA() {
    var o = Math.round, r = Math.random, m = 255;

    return 'rgba(' + o(r() * m) + ',' + o(r() * m) + ',' + o(r() * m) + ',';
}
/*
let requestValue = [];

<c:forEach items="#{prosjektBean.items}"
*/
console.log("#reee");
var random1 = randomRGBA();
var random2 = randomRGBA();
var random3 = randomRGBA();

var myBar = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['prosjekt 1', 'prosjekt 2', 'prosjekt 3'],
        datasets: [{
            label: 'fin test',
            data: ["#{prosjekt.prosjektid}", 1, 2.5],
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
    options: {scales: {yAxes: [{ticks: {beginAtZero: true}}]}}
});



//brukt til inspirasjon
//TODO fjern før prosjektet er ferdig
new Chart(document.getElementById("chartjs-1"), {
    type: 'bar',
    data: {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [{
            label: 'My First Dataset',
            data: [65, 59, 80, 81, 56, 55, 40],
            fill: false,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 205, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(201, 203, 207, 0.2)'
            ],
            borderColor: [
                'rgb(255, 99, 132)',
                'rgb(255, 159, 64)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)',
                'rgb(54, 162, 235)',
                'rgb(153, 102, 255)',
                'rgb(201, 203, 207)'
            ],
            borderWidth: 1
        }]
    },
    options: {scales: {yAxes: [{ticks: {beginAtZero: true}}]}}
});

/*]]>*/