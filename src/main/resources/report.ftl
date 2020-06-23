<html>

<head>
	<title></title>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
	<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
	<style>
		body {
	    	font: normal 10px Verdana, Arial, sans-serif;
		}
	</style> 
</head>
	
	<body>
		<h2 style="text-align: center" > Relatório Geral </h1>
		<div style="width:75%; margin:auto;">
	      <p> <strong> Retorno (R$): </strong> ${retorno?c} </p>
	      <canvas id="myChart"></canvas>
	      <div style="text-align:right; margin-top:10px">
	      	Relatório gerado em ${diahora}
	      </div>
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
            label: 'Total Líquido da Nota',
            borderColor: 'rgb(255, 0, 0)',
            data: [
              <#list notas as nota >
                ${ nota.totalLiquidoDaNota?c },
              </#list >
            ]
				  }, {
            label: 'Acumulado',
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

