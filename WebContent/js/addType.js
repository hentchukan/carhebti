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
			
			var odometer = $("[name='odometer']").prop('checked');
			var quantity = $("[name='quantity']").prop('checked');
			// submit 
			$.ajax({
		        type: "POST",
		        url: "addType.action",
		        data: "name=" + name + "&provider=" + provider + "&odometer=" + odometer+ "&quantity=" + quantity,
		        success : function(text){
		            if (text == "success") {
		                formSuccess();
		            } else {
		            	deliverError(text);
		            }
		        }
		    });
		}
	});
});

function formSuccess() {
	
}