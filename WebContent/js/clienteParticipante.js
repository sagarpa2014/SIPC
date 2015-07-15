$(document).ready(function(){
	$.ajaxSetup({
		'beforeSend' : function(xhr) {
			try{
				xhr.overrideMimeType('text/html; charset=iso-8859-1');
			}catch(e){}
		}});
});


function chkCamposClienteParticipante(){
	var registrar = $('#registrar').val();
	$("#rfc").removeAttr('disabled');
	if(registrar  == 1){
		$('input[name=tipoPersonaFiscalCliente]').removeAttr('disabled');
		$("#rfc").removeAttr('disabled');
	}
	var rfc = $('#rfc').val();
	var errorRfc = $('#errorRfc').val();
	if(rfc == "" || rfc == null){
		$('#dialogo_1').html('Escriba correctamente el RFC del Cliente');
	   	abrirDialogo();
	   	return false;
	}else{
		patron = /^([A-Z,a-z]{3,4})([0-9]{6})([A-Z,a-z,0-9]{3})$/;
		if (!rfc.match(patron)){
			$('#dialogo_1').html('Escriba correctamente el RFC del Cliente');
		   	abrirDialogo();
		   	return false;
		}
	}
	if ($('#errorRfc').length){
		if(errorRfc!=0){
			$('#dialogo_1').html('El RFC del Cliente ya se encuentra registrado, por favor verifique');
	   		abrirDialogo();
	 		return false;
		}
	}	
	//Validando los campos del formulario
	var nombre = $('#nombre').val();
	if((nombre == null || nombre == "")){
		$('#dialogo_1').html('Escriba el nombre del Cliente');
		abrirDialogo();
	 	return false;
	}
	var tipoPersonaFiscalCliente = $("input:radio[name=tipoPersonaFiscalCliente]:checked").val();
	if(tipoPersonaFiscalCliente == 'F'){
		//Apellido Paterno del Cliente
		if(($('#apellidoPaterno').val()== null || $('#apellidoPaterno').val()=="")){
			$('#dialogo_1').html("Capture el Apellido Paterno del Cliente");
			abrirDialogo();
			return false;
		}	
	}	
	
	var idEstado = $('#idEstado').val();
	if((idEstado == null || idEstado == "" || idEstado ==-1)){
		$('#dialogo_1').html('Seleccione el estado del Cliente');
		abrirDialogo();
	 	return false;
	}

	var claveMunicipio = $('#claveMunicipio').val();
	if((claveMunicipio == null || claveMunicipio == ""|| claveMunicipio ==-1)){
		$('#dialogo_1').html('Seleccione el municipio del Cliente');
		abrirDialogo();
	 	return false;
	}

	var colonia = $('#colonia').val();
	if((colonia == null || colonia == "")){
		$('#dialogo_1').html('Capture la colonia del Cliente');
		abrirDialogo();
	 	return false;
	}
	
	
	var calle = $('#calle').val();
	if((calle == null || calle == "")){
		$('#dialogo_1').html('Escriba la calle del Cliente');
		abrirDialogo();
	 	return false;
	}

	var numExterior = $('#numExterior').val();
	if((numExterior == null || numExterior == "")){
		$('#dialogo_1').html('Escriba el numero exterior del Cliente');
		abrirDialogo();
	 	return false;
	}

	var codigoPostal = $('#codigoPostal').val();
	patron = /^([0-9]{1,9})$/;
	if(codigoPostal == null || codigoPostal == ""){
		$('#dialogo_1').html('Escriba el codigo postal del Cliente');
		abrirDialogo();
		return false;
	}else{
		if(!codigoPostal.match(patron)){
			$('#dialogo_1').html('El Codigo Postal acepta unicamente valores numéricos');
			abrirDialogo();
			return false;
		}
	}
	
	var telefono = $('#telefono').val();
	if((telefono == null || telefono == "")){
		$('#dialogo_1').html('Capture el numero de telefono del Cliente');
		abrirDialogo();
	 	return false;
	}
	
	
}


function recuperaSeleccionTipoPersonaFiscalCliente(){
    // F:Fisica, M:Moral
    var tipoPersonaFiscal = $('input:radio[name=tipoPersonaFiscalCliente]:checked').val();
    
   $.ajax({
	   async: false,
	   type: "POST",
	   url: "recuperaSeleccionTipoPersonaFiscalCliente",
	   data:"&clienteParticipante.tipoPersona="+tipoPersonaFiscal,
	   success: function(data){
           $("#datosClienteFiscales").html(data).ready(function () {
        	   $("#datosClienteFiscales").fadeIn('slow');        
           });
	   }
	});//fin ajax     
	     
}

