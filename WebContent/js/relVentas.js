$(document).ready(function(){
	$.ajaxSetup({
		'beforeSend' : function(xhr) {
			try{
				xhr.overrideMimeType('text/html; charset=iso-8859-1');
			}catch(e){}
		}});
});

$(function() {
	
	function validaCamposRelVentas( ) {
		var idClienteC = $('#idClienteC').val();
		var folioFacturaC = $('#folioFacturaC').val();
		var fechaFacturaC = $('#fechaFacturaC').val();
		var volumenC = $('#volumenC').val();
		var idDestinoC = $('#idDestinoC').val();
		var idUsoGranoC = $('#idUsoGranoC').val();
		if(idClienteC == -1){
			$('#dialogo_1').html('Debe seleccionar el cliente');
			abrirDialogo();
			return false;
		}		
		if(folioFacturaC== null || folioFacturaC==""){
			$('#dialogo_1').html('Debe capturar el folio de la factura');			
			abrirDialogo();
			return false;
		}	
		if(fechaFacturaC== null || fechaFacturaC==""){
			$('#dialogo_1').html('Debe capturar la fecha de la factura');			
			abrirDialogo();
			return false;
		}	
		if(volumenC== null || volumenC==""){
			$('#dialogo_1').html('Debe capturar el volumen de la factura');			
			abrirDialogo();
			return false;
		}		
		if(idDestinoC == -1){
			$('#dialogo_1').html('Debe seleccionar el destino');
			abrirDialogo();
			return false;
		}		
		if(idUsoGranoC == -1){
			$('#dialogo_1').html('Debe seleccionar el tipo de uso de grano');
			abrirDialogo();
			return false;
		}
		
		return true;
	}
	
	function addUser() {      
		var valid = true;      
		var registrar = $('#registrar').val();
		var folioCartaAdhesion = $('#folioCartaAdhesion').val();
		var idClienteC = $('#idClienteC').val();
		var idRelVentas = $('#idRelVentas').val();
		var folioFacturaC = $('#folioFacturaC').val();
		var fechaFacturaC = $('#fechaFacturaC').val();
		var volumenC = $('#volumenC').val();
		var idDestinoC = $('#idDestinoC').val();
		var idUsoGranoC = $('#idUsoGranoC').val();
		if($("#idChkDestino").is(":checked")) {
			aplicaInternacional = true;
		}else{
			aplicaInternacional = false;
		}	
		
		if(!validaCamposRelVentas()){
			return false;
		}
		var error = 0, cliente="", usoGrano ="", destino= "", direccionCliente ="";
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "registrarRelVentas",
			   data: "registrar="+registrar+
			   "&rv.idCliente="+idClienteC+
			   "&rv.id="+idRelVentas+
			   "&rv.folioFactura="+folioFacturaC+
			   "&folioCartaAdhesion="+folioCartaAdhesion+
			   "&rv.fecha="+fechaFacturaC+
			   "&rv.volumen="+volumenC+
			   "&rv.idPais="+idDestinoC+
			   "&rv.idUsoGrano="+idUsoGranoC+
			   "&rv.internacional="+aplicaInternacional,			   
			   success: function(data){
				   var $response=$(data);
				   error = $response.filter('#error').text();	
				   cliente = $response.filter('#cliente').text();
				   direccionCliente = $response.filter('#direccionCliente').text();
				   usoGrano = $response.filter('#usoGrano').text();
				   destino = $response.filter('#destino').text();
				   idRelVentas = $response.filter('#idRelVentas').text();
					$('#repuestaRegistroRelVentas').html(data).ready(function () {
						$("#repuestaRegistroRelVentas").fadeIn('slow');
					});
			   }
			});//fin ajax
		if ( error == 0 ) {
			var tds = "";
			tds += '<tr>';
			tds += '<td >'+cliente+'</td>';
			tds += '<td >'+direccionCliente+'</td>';
			tds += '<td>'+folioFacturaC+'</td>';
			tds += '<td>'+fechaFacturaC+'</td>';
			tds += '<td>'+volumenC+'</td>';
			tds += '<td >'+destino+'</td>';
			tds += '<td >'+usoGrano+'</td>';
//			tds += '<td><a href="#"  id="4" class="botonVerDetalles" title=""></a></td>';
			tds += '</tr>';			
			$( "#tabRelVentas tbody" )
			.append(tds);        
			$( "#dialog-form" ).dialog( "close" );      
		}      
		return valid;    
	}     
	 $( "#dialog-form" ).dialog({      
		autoOpen: false,      
		height: 350,      
		width: 380,      
		modal: true,      
		buttons: {        
			"Guardar": addUser,        
			Cancel: function() {          
				$( "#dialog-form" ).dialog( "close" );       
			}      
		},      
		close: function() {        
			//form[ 0 ].reset();        
			//allFields.removeClass( "ui-state-error" );      
		}    
	});     
	
	$( "#dialog-form" ).find( "form" ).on( "submit", function( event ) {      
		event.preventDefault(); 		
		addUser();    });  
	
	
	$( "#add-reg" )
		.button()
		.on( "click", function() {
			getAjaxTablaRelVentas(0, 0);
			//dialog.dialog( "open" ); 	
			$( "#dialog-form" ).dialog( "open" );
	}); 
	$(".editar").on( "click", function() {	
		var id = $(this).attr('id');
		getAjaxTablaRelVentas(id, 1);
		$( "#dialog-form" ).dialog( "open" ); 
		
	}); 
	
//	$( ".botonVerDetalles").on( "click", function() {	
//		alert("ok 1");	 	
//	}); 
	function getAjaxTablaRelVentas(id, registrar) {				
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "getTablaRelVentas",
			   data: "registrar="+registrar+
			   "&idRelVentas="+id,
			   success: function(data){
					$('#dialog-form').html(data).ready(function () {
						$("#dialog-form").fadeIn('slow');
					});
			   }
			});//fin ajax
	}
}); //end funcion principal	



function consigueDestinoRelVentas (){	
	if($("#idChkDestino").is(":checked")) {
		aplicaInternacional = true;
	}else{
		aplicaInternacional = false;
	}
	$.ajax({
		async: false,
		type: "POST",
		url: "consigueDestinoRelVentas",
	   data: "aplicaInternacional ="+aplicaInternacional,
		success: function(data){
			$('#contenedorDestino').html(data).ready(function () {
				$("#contenedorDestino").fadeIn('slow');
			});
		}
	}); //termina ajax
	
}


function seleccionaTipoCargaRelVentas(){
	var tipoCargaRelVentas = $('input:radio[name=tipoCargaRelVentas]:checked').val();
	var urlenvio = "";
	var folioCartaAdhesion = $('#folioCartaAdhesion').val();
	if(tipoCargaRelVentas==1){
		urlenvio = "cargaIndRelVentas";
	}else{
		urlenvio = "cargaMasivaRelVentas";	
	}
	$("#tipoCargaRelVentas").fadeOut('slow', function() {
		$.ajax({
			   async: false,
			   type: "POST",
			   url: urlenvio,
			   data:"folioCartaAdhesion="+folioCartaAdhesion,
			   success: function(data){
					$('#tipoCargaRelVentas').html(data).ready(function () {
						$("#tipoCargaRelVentas").fadeIn('slow');
						
					});
			   }
			});//fin ajax
	 });
}

function chkArchivoRelVentas(){
	var patron = /^(xlsx)$/;
	var doc = $('#doc').val();
	if(doc == null || doc == ""){
		$('#dialogo_1').html('Seleccione el archivo de la relación de ventas');
		abrirDialogo();
		return false;
	}else{
		var ext = doc.toLowerCase().substring(doc.lastIndexOf(".")+1);
		if(!ext.match(patron)){
			$('#dialogo_1').html('Extensión no permitida, solo puede cargar archivos con extensiones xlsx');
	   		abrirDialogo();
			return false;
		}
	}
	
}