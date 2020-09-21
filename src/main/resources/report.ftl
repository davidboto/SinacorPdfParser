<!DOCTYPE HTML>    
<html>    
<head>
	<title></title>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.stock.min.js"></script>
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
          <div id="chartContainer" style="height: 450px; width: 100%;"></div>
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

<script type="text/javascript">
window.onload = function () {  
  var stockChart = new CanvasJS.StockChart("chartContainer",{
    theme: "light2",
    animationEnabled: true,
    exportEnabled: true,
    charts: [{
      title :{
          text: "Total Líquido Diário",
          fontSize: 15
      },
      axisX: {
        valueFormatString: "DD-MM-YYYY",
        crosshair: {
          enabled: true,
          snapToDataPoint: true
        }
      },
      axisY: {
        crosshair: {
          enabled: true,
          //snapToDataPoint: true
        }
      },
      data: chartsData
      
    }],
    rangeSelector: {
      valueFormatString: "DD-MM-YYYY",
      inputFields: {
        startValue: new Date('${inicioPeriodo}'), // new Date(yyyy,mm,dd)
        endValue: new Date('${fimPeriodo}'),
      },
    }
  });

  stockChart.render();    
}

var chartsData = []; 

var dataSeries = { type: "line", showInLegend: true, name: "Total Líquido Diário (R$)" };
var dataPoints = [];

// var dataSeries2 = { type: "spline" };
// var dataPoints2 = [];

// dataPoints.push({ x: i, y: y });

<#list notasFormatacaoData as nota >

    dataPoints.push({ x: new Date('${nota.dataPregao}'), y: ${nota.totalLiquidoDaNota?c} }); 

</#list >
dataSeries.dataPoints = dataPoints;

// dataPoints2.push({ x: i, y: y });
// dataSeries2.dataPoints = dataPoints2;

chartsData.push(dataSeries);
// chartsData.push(dataSeries2);
</script>
</html>

