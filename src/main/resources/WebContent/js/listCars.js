/**
 * 
 *//**
 * 
 */
$(document).ready(function() {
	
	$.getScript("js/pagination.js", function() {
		paginate();
	});
	
	$('.datepicker').datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat: 'dd/mm/yy'
	});
					
	$.validate();

	$('#btn-add').on('click touchstart', function(event) {
		initFormCars();
	});

	$('#btn-save').on('click touchstart', function(event) {
		
		var id = $("#formCar-id").val();
		var number = $("#formCar-number").val();
		var trade = $("#formCar-trade").val();
		var manifacturer = $("#formCar-manifacturer").val() ;
		var greyCard = $("#formCar-greyCard").val();
		
		// TODO validation
		submitForm('save');
	});

	$('#btn-delete').on('click touchstart', function(event) {
		if ($('#btn-delete').hasClass('disabled'))
			return;
		var id = $("#formCar-id").val();
		if (!id)
			alert("We can't delete a new record");
		else
			submitForm('delete');
	});

	$('.listCar tr[data-href]')
		.each(function() {
			$(this).css('cursor', 'pointer').hover(function() {
				$(this).addClass('active');
			},
			
			function() {
				$(this).removeClass('active');
			})
			.on('click tap', function() {
				var id = $(this)[0].cells[0].innerHTML;
				var number = $(this)[0].cells[1].innerHTML;
				var trade = $(this)[0].cells[2].innerHTML;
				
				var manifacturer = $(":hidden:eq(0)", this.cells[3]).val();
				var greyCard = $(":hidden:eq(0)", this.cells[4]).val();

				loadFormCar(id, number, trade, manifacturer, greyCard);
			});
		});

	function initFormCars() {
		loadFormCar("", null, null, null, null, null);
	}

	function loadFormCar(id, number, trade, manifacturer, greyCard) {
		$("#formCar-div").show();
		$("#formCar-id").val(id);
		$("#formCar-number").val(number);
		$("#formCar-trade").val(trade);
		$("#formCar-manifacturer").val(manifacturer);
		$("#formCar-greyCard").val(greyCard);

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
		var formAction = 'car/' + method + '.do';
		// set form action
		$('#formCar').attr('action', baseUrl+formAction);
		// submit form
		$('#formCar').submit();
	};

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