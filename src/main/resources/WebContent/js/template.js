/**
 * 
 */
$(document).ready(function() {
	$(".btn-menu").click(function() {
		window.location.href =  "/" + window.location.pathname.split("/")[1] + "/" + this.id.replace("btn-", "")+".go";
	});
})