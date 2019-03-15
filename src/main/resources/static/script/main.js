var ctx = document.getElementById('myLines');

var myLines = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['verdi_1', 'verdi_2', 'verdi_3'],
        datasets: [{
            label: 'fin test',
            data: [15, 10, 5],
            backgroundColor: [
                'rgba(0, 255, 0, 1)',
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)'
            ],
            borderWidth: 1
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