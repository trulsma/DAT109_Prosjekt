var ctx = document.getElementById('myLines');
var myLines = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['verdi_1', 'verdi_2', 'verdi_3'],
        datasets: [{
            label: 'fin test',
            data: [20, 10, 30],
            backgroundColor: [
                'rgba(255, 0, 255, 0.3)',
                'rgba(128,0,0, 0.3)',
                'rgba(0,0,128, 0.3)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)'
            ],
            borderWidth: 1
        }]
    }
});