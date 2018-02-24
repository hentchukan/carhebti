/**
 * 
 */
$(document).ready(function() {

	$('.datepicker').datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat: 'dd/mm/yy'
	});
					
	$.validate();

	$('#btn-add').on('click touchstart', function(event) {
		initFormService();
	});

	$('#btn-save').on('click touchstart', function(event) {
		
		var type = $('#formService-type option:selected').data('val');
		var id = $("#formService-id").val();
		var date = $("#formService-date").val();
		var provider = $("#formService-provider").val();
		var odometer = (type.odometer) ? $("#formService-odometer").val() : null;
		var quantity = (type.qte) ? $("#formService-quantity").val() : null;
		var comment = $("#formService-comment").val();
		submitForm('save');
	});

	$('#btn-delete').on('click touchstart', function(event) {
		if ($('#btn-delete').hasClass('disabled'))
			return;
		var id = $("#formService-id").val();
		if (!id)
			alert("We can't delete a new record");
		else
			submitForm('delete');
	});

	$('.listService tr[data-href]')
		.each(function() {
			$(this).css('cursor', 'pointer').hover(function() {
				$(this).addClass('active');
			},
			
			function() {
				$(this).removeClass('active');
			})
			.on('click tap', function() {
				var id = $(this)[0].cells[0].innerHTML;
				var date = $(this)[0].cells[1].innerHTML;
				var type = $(this)[0].cells[2].innerHTML;

				var provider = $(":hidden:eq(0)", this.cells[3]).val();
				var comment = $(":hidden:eq(0)", this.cells[4]).val();
				var odometer = $(":hidden:eq(0)", this.cells[5]).val();
				var quantity = $(":hidden:eq(0)", this.cells[6]).val();

				var providerType = $(":hidden:eq(0)", this.cells[7]).val();
				loadFormService(id, date, type, providerType, provider, odometer, quantity, comment);
			});
		});

	function initFormService() {
		var type = $('#formService-type option:selected').data('val');
		loadFormService("", new Date(), type, type.providerName, null, null, null, "");
	}

	function loadFormService(id, date, type, providerType, provider, odometer, quantity, comment) {
		$("#formService-div").show();
		$("#formService-id").val(id);
		$("#formService-date").datepicker("setDate", date );

		$("#formService-provider").val(provider);
		$("#formService-providerType")[0].innerHTML = providerType + " :";

		if (provider && providerType) {
			$("#formService-providerblock").show();
		} else if (type) {
			$("#formService-providerblock").show();
			$("#formService-providerType")[0].innerHTML = type.providerName + ":";
		} else {
			$("#formService-providerblock").hide();
		}

		$("#formService-odometer").val(odometer);
		if (odometer || type.odometer) {
			$("#formService-odometerblock").show();
		} else {
			$("#formService-odometerblock").hide();
			$("#formService-odometer").val(null);
		}

		$("#formService-quantity").val(quantity);
		if (quantity || type.qte) {
			$("#formService-quantityblock").show();
		} else {
			$("#formService-quantityblock").hide();
			$("#formService-quantity").val(null);
		}

		$("#formService-comment").val(comment);
		
		$("#btn-save").removeClass('disabled');
		if (id)
			$("#btn-delete").removeClass('disabled');
		else
			$("#btn-delete").addClass('disabled');
	}

	var submitForm = function(method) {
		// set form action based on the method passed in the
		// click handler, update/delete
		var baseUrl = "/" + window.location.pathname.split("/")[1] + "/";
		var formAction = 'service/' + method + '.do';
		// set form action
		$('#formService').attr('action', baseUrl+formAction);
		// submit form
		$('#formService').submit();
	};

	$("#formService-type").change(function() {
		var type = $('#formService-type option:selected').data('val');
		$("#formService-providerType")[0].innerHTML = type.providerName + " :";
		
		if (type.odometer) {
			$("#formService-odometerblock").show();
		} else {
			$("#formService-odometerblock").hide();
			$("#formService-odometer").val(null);
		}

		if (type.qte) {
			$("#formService-quantityblock").show();
		} else {
			$("#formService-quantityblock").hide();
			$("#formService-quantity").val(null);
		}
		
	});
	
	$('#serviceModal').on( 'show.bs.modal', function(event) {
		var button = $(event.relatedTarget) // Button
		// that
		// triggered
		// the modal
		var recipient = button.data('whatever') // Extract
		// info
		// from
		// data-*
		// attributes
		// If necessary, you could initiate an AJAX
		// request here (and then do the updating in a
		// callback).
		// Update the modal's content. We'll use jQuery
		// here, but you could use a data binding
		// library or other methods instead.
		var modal = $(this)
		modal.find('.modal-title').text('New message to ' + recipient)
		modal.find('.modal-body input').val(recipient)
	})
});