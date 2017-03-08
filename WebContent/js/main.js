/**
 * 
 */
$(document).ready(function() {
	initPanel();
	
	$('#btn-addType').click(function() {
		$("#panelBody").load('pages/addType.jsp');
	});
})


function initPanel() {
	$("#panelTitle").html("Add a service");
	$("#panelBody").html("A Form here");
}