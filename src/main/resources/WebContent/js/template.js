/**
 * 
 */
$(document).ready(function() {
	$(".btn-menu").click(function() {
		window.location.href =  "/" + window.location.pathname.split("/")[1] + "/" + this.id.replace("btn-", "")+".go";
	});
	
	$(".btn-menu-right").click(function() {
		window.location.href =  "/" + window.location.pathname.split("/")[1] + "/" + this.id.replace("btn-", "")+".go";
	});
	
	$(".flag-icon-gb").click(function() {
		pickLanguage('en');
	});
	
	$(".flag-icon-fr").click(function() {
		pickLanguage('fr');
	});
	
	$(".flag-icon-tn").click(function() {
		pickLanguage('ar');
	});
})

function pickLanguage(language) {
	window.location.search =  '?language='+language;
}