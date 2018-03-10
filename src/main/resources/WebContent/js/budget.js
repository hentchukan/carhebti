/**
 * 
 */
$(document).ready(function() {
	
	$('.datepicker').datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat: 'dd/mm/yy'
	});
	
	loadInitialDates();
	
	$("#budget-btn").on('click touchstart', function(event) {
		var startDate = $("#budgetParameters-startDate").val();
		var endDate = $("#budgetParameters-endDate").val();

		$.ajax({
			type: "GET",
			dataType: "json",
			contentType: 'application/json',
	        mimeType: 'application/json',
			url: "/" + window.location.pathname.split("/")[1] + "/budget/data?start="+parseDate(startDate)+"&end="+parseDate(endDate),
			success: function(data) {
				var chartData = [];
				$.each(data, function(i, item) {
					chartData.push({
						label: data[i].type,
						y: data[i].value
					});
				});
				
				var chart = new CanvasJS.Chart("chartContainer", {
					animationEnabled : true,
					theme : "light2", // "light1", "light2", "dark1", "dark2"
					title : {
						text : "Budget"
					},
					data : [ {
						type : "pie",
						yValueFormatString : "#,##0.0#\"DTN\"",
						dataPoints : chartData
					} ]
				});
				
				chart.render();
			},
			error:function(data,status,er) {
	            alert("error: "+data+" status: "+status+" er:"+er);
	        }
		});
	});
	
	function loadInitialDates() {
		var start = new Date();
		start.setDate(1);
		var end = new Date(start.getFullYear(), start.getMonth()+1, start.getDate(), 0, 0, 0, 0);
		var oneDay = 24 * 60 * 60 * 1000;
		end.setTime(end.getTime() - oneDay);
		
		$("#budgetParameters-startDate").datepicker("setDate",  start);
		$("#budgetParameters-endDate").datepicker("setDate", end);
	}
	
	function parseDate(date) {
		return date.split("/").join("");
	}
});