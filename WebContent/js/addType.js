/**
 * 
 */
$(document).ready(function() {
	$('#btn-submit').click(function() {
		var name = $("#name").val();
		var provider = $("#provider").val();
		if (!(name || provider))
			$("#message").html('Cannot add the type. Some fields are not fulfilled');
		else {
			$("#message").html('Valid values');
		}
	});
});