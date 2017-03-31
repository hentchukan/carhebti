/**
 * 
 */
$(document).ready(function() {
	$('#btn-submit').click(function() {
		var name = $("#name").val();
		var provider = $("#provider").val();
		
		if (name == '' || provider == '') {
			$("#msg-error").html('<Strong>Validation failure</Strong> Some fields are not fulfilled');
			$("#msg-error").show();
		} else {
			$("#msg-error").hide();
			
			$("#addTypeForm").submit();
		}
	});
});

function formSuccess() {
	
}