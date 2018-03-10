/**
 * 
 */
$(document).ready(function() {
	
//	(function() {
//		$('#formSettings-language').val("${requestScope.settings.language}");
//		$('#formSettings-car').val("${requestScope.settings.car.id}");
//	})();
	
	$.validate();

	$('#btn-save').on('click touchstart', function(event) {
		
		var language = $('#formSettings-language option:selected').val();
		var car = $('#formSettings-car option:selected').val();
		
		// TODO validation
		submitForm('save');
	});
	
	var submitForm = function(method) {
		// set form action based on the method passed in the
		// click handler, update/delete
		var baseUrl = "/" + window.location.pathname.split("/")[1] + "/";
		var formAction = 'settings/' + method + '.do';
		// set form action
		$('#formSettings').attr('action', baseUrl+formAction);
		// submit form
		$('#formSettings').submit();
	};
});