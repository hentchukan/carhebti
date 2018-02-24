/**
 * 
 */
$(document).ready(function() {
	$(".btn-menu").click(function() {
<<<<<<< HEAD
		window.location.href =  "/" + window.location.pathname.split("/")[1] + "/" + this.id.replace("btn-", "")+".go";
=======
		window.location.href = this.id.replace("btn-", "")+".go";
>>>>>>> refs/remotes/origin/master
	});
})