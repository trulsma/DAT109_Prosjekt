$.ajax({
    url: "/prosjekt/1/stemmer",
    data: {},
    success: function(result) {
        for (let i = 0; i < result.length; i++) {
            console.log(result[i]);
        }
    }
});