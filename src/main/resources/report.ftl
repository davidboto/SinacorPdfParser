<html>

<head>
	<title></title>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>

	<body>
		<h1> Operações (Day Trade) </h1>
		
		<div style="width:75%;">
	      <canvas id="myChart"></canvas>
	    </div>
	</body>
	
  <script>
		var ctx = document.getElementById('myChart').getContext('2d');
		var chart = new Chart(ctx, {
			type: 'line',
			data: {
				labels: [
					<#list notas as nota >
						'${nota.dataPregao}',
					</#list >
			    ],
				datasets: [
          {
            label: 'Ganho Diário',
            borderColor: 'rgb(255, 0, 0)',
            data: [
              <#list notas as nota >
                ${ nota.totalLiquidoDaNota?c },
              </#list >
            ]
				  }, {
            label: 'Acmuluado',
            borderColor: 'rgb(0, 0, 255)',
            data: [
              <#list sum as s >
                ${ s?c },
              </#list >
            ]
				  }
        ]
		  },
			// Configuration options go here
			options: { }
	    });
	</script>
</html>

