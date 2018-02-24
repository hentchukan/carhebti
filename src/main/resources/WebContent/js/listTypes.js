/**
 * 
 */
$(document).ready(function() {
	
	$('#btn-add').on('click touchstart', function(event) {
		initFormType();
	});
	
	$('#btn-save').on('click touchstart', function(event) {
		var id = $("#formType-id").val();
        var name = $("#formType-name").val();
        var provider = $("#formType-provider").val();
        var odometer = $('[name="formType-odometer"]').prop("checked");
        var quantity = $('[name="formType-quantity"]').prop("checked");
        
        submitForm('save');
	});
	
	$('#btn-delete').on('click touchstart', function(event) {
		if ($('#btn-delete').hasClass('disabled'))
			return;
		var id = $("#formType-id").val();
		if (!id) 
			alert("We can't delete a new record");
		else submitForm('delete');
	});
	
	$('.listType tr[data-href]').each(function(){
        $(this).css('cursor','pointer').hover(
            function(){ 
                $(this).addClass('active'); 
            },  
            function(){ 
                $(this).removeClass('active'); 
            }).on('click tap', function() {
            	var id = $(":hidden:eq(0)", this.cells[0]).val();
                var name = $(this)[0].cells[1].innerHTML;
                var provider = $(this)[0].cells[2].innerHTML;
                var odometer = $(":checkbox:eq(0)", this.cells[3])[0].checked;
                var quantity = $(":checkbox:eq(0)", this.cells[4])[0].checked;
                
                loadFormType(id, name, provider, odometer, quantity);
            });
    });
	
	function initFormType() {
		loadFormType("", "", "", false, false);
	}
	
	function loadFormType(id, name, provider, odometer, quantity) {
		$("#formType-div").show();
		$("#formType-id").val(id);
		$("#formType-name").val(name);
		$("#formType-provider").val(provider);
		$('[name="formType-odometer"]').prop("checked", odometer);
		$('[name="formType-quantity"]').prop("checked", quantity);
		
		$("#btn-save").removeClass('disabled');
		if (id)
			$("#btn-delete").removeClass('disabled');
		else $("#btn-delete").addClass('disabled');
		
	}
	
	var submitForm = function(method){
	    //set form action based on the method passed in the click handler, update/delete
		var baseUrl = "/" + window.location.pathname.split("/")[1] + "/";
	    var formAction =  'type/' + method + '.do';
	    //set form action
	    $('#formType').attr('action', baseUrl+formAction);
	    //submit form
	    $('#formType').submit();
	};
});