/**
 * 
 */
$(document).ready(function() {
	$(".btn-menu").click(function() {
		window.location.href = this.id.replace("btn-", "")+".go";
	});
})