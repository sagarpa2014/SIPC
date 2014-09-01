$(document).ready(function(){
	$.ajaxSetup({
		'beforeSend' : function(xhr) {
			try{
				xhr.overrideMimeType('text/html; charset=iso-8859-1');
			}catch(e){}
		}});
});

function recuperaEdoCompradoresByPrograma(){
	var idPrograma = $('#idPrograma').val();
	if(idPrograma == -1){
		$("#recuperaEdoCompradoresByPrograma").fadeOut('slow');
		return false;
	}
	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "recuperaEdoCompradoresByPrograma",
		   data: "idPrograma="+idPrograma, 
		   success: function(data){
				$('#recuperaEdoCompradoresByPrograma').html(data).ready(function () {
					$("#recuperaEdoCompradoresByPrograma").fadeIn('slow');
					
				});
		   }
		});//fin ajax
}
function datosComprador(){
	var idComprador = $('#idComprador').val();
	var idPrograma = $('#idPrograma').val();
	
	if(idComprador == -1 || idPrograma == -1){
		$("#datosComprador").fadeOut('slow');
		return false;
	}
	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "datosComprador",
		   data: "idComprador="+idComprador+
		   		 "&idPrograma="+idPrograma, 
		   success: function(data){
				$('#datosComprador').html(data).ready(function () {
					$("#datosComprador").fadeIn('slow');
				});
		   }
		});//fin ajax
}

function recuperaDatosPlaza(){
	$("#recuperaDatosPlaza").css("display", "none");
	var clabe = $('#clabe').val();
	
	if(clabe==-1){
		return false;
	}
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "recuperaDatosPlaza",
		   data: "clabe="+clabe, 
		   success: function(data){
				$('#recuperaDatosPlaza').html(data).ready(function () {
					$("#recuperaDatosPlaza").css("display", "inline");

					
				});
		   }
		});//fin ajax
}

function validaClabe(){
	var cuenta = $('#cuenta').val();
	if(cuenta == null || cuenta == ""){
		return false;
	}
	//var errorClabeInvalida = $('#errorClabeInvalida').val();
	//alert("errorCla"+errorClabeInvalida);
	if ($('#errorClabeInvalida').length){
		var errorClabeInvalida = $('#errorClabeInvalida').val();
		//alert("errorClabeInvalida "+errorClabeInvalida);
		if(errorClabeInvalida==0){
			return false;
		}
	}
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "validarCuentaBancaria",
		   data: "clabe="+cuenta,
		   success: function(data){
				$('#msjCuenta').html(data).ready(function () {
				});
		   }
		}); //termina ajax
}
function validaConfClabe(){
	var confirmaCuenta = $('#confirmaCuenta').val();
	var cuenta = $('#tmpCuenta').val();
	
	if(confirmaCuenta == null || confirmaCuenta == ""){
		return false;
	}
	//if(confirmaCuenta.length == 3){
		if(cuenta!=null && cuenta!=""){
			/*if(cuenta!=confirmaCuenta){
				$('#msjConfirmacionCta').html('Las cuentas no coinciden');
				$("#msjConfirmacionCta").css("display", "inline");
				$("#errorClabeInvalida").val(1);
			}else{*/
				$.ajax({
					   async: false,
					   type: "POST",
					   url: "validarConfirmacionCtaBancaria",
					   data: "clabe="+confirmaCuenta+
					   		 "&cuenta="+cuenta,
					   success: function(data){
							$('#msjConfCta').html(data).ready(function () {
							});
					   }
					}); //termina ajax
				
			//}
		}
	//}

	
}
function capturarOtraCuenta(){
	//1:habilita captura de nueva cuenta
	//0:Deshabilita captura de nueva cuenta
	var otrac = $('input:radio[name=otrac]:checked').val();
	
	if(parseInt(otrac)==0){
		$("#otraCuenta").css("display", "none");
		$("#clabe").removeAttr('disabled');
		$("#recuperaDatosPlaza").css("display", "inline");
		return false;
	}else{
		$("#recuperaDatosPlaza").css("display", "none");
	}
	$("#clabe").attr('disabled','disabled');
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "capturarOtraCuenta",
		   success: function(data){
				$('#otraCuenta').html(data).ready(function () {
					$("#otraCuenta").css("display", "inline");
				});
		   }
		});//fin ajax	
}
function capturarRFC(){
	// 1:Fisica, 0:Moral
	var personaFiscal = $('input:radio[name=personaFiscal]:checked').val();
	if(personaFiscal == 1){
		$("#rfc").css("display", "inline");
		$("#curp").css("display", "inline");
	}else{
		$("#rfc").css("display", "inline");
		$("#curp").css("display", "none");
	}
		
}


function chkCamposActualizaPagos(){
	//Validando los campos de la edición del pago
	var otrac = $('input:radio[name=otrac]:checked').val();
	var clabe = $('#clabe').val();
	var numCampos = $('#numCampos').val();
	
	if(parseInt(otrac)==1){//si captura nueva cuenta
		var confirmaCuenta = $('#confirmaCuenta').val();
		var cuenta = $('#cuenta').val();
		//valida que los campos de clabe interbancaria hayan sido capurados
		if((cuenta==null||cuenta =="")||(confirmaCuenta==null||confirmaCuenta =="") ){
			$('#dialogo_1').html('Debe capturar las clabes interbancarias');
			abrirDialogo();
			return false;
		}
	}else{//valida que se haya seleccionado una clabe interbancaria
		if(clabe==-1){
			$('#dialogo_1').html('Seleccione la clabe interbancaria');
			abrirDialogo();
			return false;
		}
	}
	
	/** Valida si hubo un error en validacion de clabe interbancaria**/
	if ($('#errorClabeInvalida').length){
		var errorClabeInvalida = $('#errorClabeInvalida').val();
		if(errorClabeInvalida!=0){
			$('#dialogo_1').html('La clabe interbancaria es incorrecta, favor de verificar');
	   		abrirDialogo();
	 		return false;	
		}
	}	
	/** Valida si hubo un error en validacion de confirmacion de clabe interbancaria**/
	if ($('#errorConfClabeInvalida').length){
		var errorConfClabeInvalida = $('#errorConfClabeInvalida').val();
		if(errorConfClabeInvalida!=0){
			$('#dialogo_1').html('La clabe interbancaria es incorrecta, favor de verificar');
	   		abrirDialogo();
	 		return false;
		}
	}	
	
	/*Valida que la sucursal sea capturada */
	if($('#sucursal').length){
		var sucursal = $('#sucursal').val();
		if(sucursal == "" || sucursal == null ){
			$('#dialogo_1').html('Capture el nombre de la sucursal');
	   		abrirDialogo();
	   		return false;
		}
	}
	
	if(numCampos==-1){
		$('#dialogo_1').html('Seleccione el número de estados a apoyar');
		abrirDialogo();
	 	return false;
	}else{
		var edo = -1;
		var volumen ="";
		var importe ="";
		var tempEdo ="";
		
		for (i=1;i<parseInt(numCampos)+1;i++){
			edo = $('#e'+i).val();
			volumen = $('#v'+i).val();
			importe = $('#i'+i).val();
			if(edo==-1 || (volumen == "" || volumen == null) || (importe == "" || importe == null) ){
	   			$('#dialogo_1').html('Capture los valores de estados a apoyar en el registro '+i);
		   		abrirDialogo();
		 		return false;
	   		 }else{
	   			 /*Valida que el estado no se encuentre repetido*/
	   			for (j=1;j<parseInt(numCampos)+1;j++){
	   				if(i!=j){
	   					tempEdo = $('#e'+j).val();
	   					if(edo == tempEdo){
	   						$('#dialogo_1').html('El estado del registro '+i+", se encuentra repetido, favor de verificar");
	   				   		abrirDialogo();
	   				 		return false;
	   					}
	   				}
	   				
	   			}
	   			patron =/^\d{1,10}((\.\d{1,3})|(\.))?$/;
	   			 if(!volumen.match(patron)){
		   			$('#dialogo_1').html('El valor de volumen "'+volumen+'" del registro '+i+' es inválido, se aceptan hasta 10 digitos a la izquierda y 3 digitos máximo a la derecha');
			   		abrirDialogo();
			 		return false;
	   			 }else{
	   				patron =/^\d{1,13}((\.\d{1,2})|(\.))?$/;
	   				if(!importe.match(patron)){
	   					$('#dialogo_1').html('El valor del importe "'+importe+'" del registro '+i+' es inválido, se aceptan hasta 13 digitos a la izquierda con 2 máximo a la derecha');
	   			   		abrirDialogo();
	   			 		return false;
	   				}
	   			 }
	   			
	   		 }
			
		}// end for
	}// end validacion de los campos de estados a apoyar
	
}

function chkCamposPagos(){
	//Validando los campos del formulario
	var idPrograma = $('#idPrograma').val();
	var patron ="";
	if((idPrograma ==-1)){
		$('#dialogo_1').html('Seleccione el programa');
		abrirDialogo();
	 	return false;
	}
	var idComprador = $('#idComprador').val();
	var numCampos = $('#numCampos').val();
	
	if(idComprador ==-1){
		$('#dialogo_1').html('Seleccione el nombre del comprador');
		abrirDialogo();
	 	return false;
	}

	if($('#noCarta').length){
		var noCarta = $('#noCarta').val();
		if(noCarta == -1 ){
			$('#dialogo_1').html('Seleccione el número de carta');
	   		abrirDialogo();
	   		return false;
		}
	}
	if(idComprador !=-1){
		var tieneCuenta = $('#tieneCuenta').val();
		var tieneRfc = $('#tieneRfc').val();
		var cuenta = $('#cuenta').val();
		var confirmaCuenta = $('#confirmaCuenta').val();
		/* ***Valida información de las cuentas bancarias*** */
		if(parseInt(tieneCuenta)==1){//si el comprador tiene cuentas asociadas
			var otrac = $('input:radio[name=otrac]:checked').val();
			var clabe = $('#clabe').val();
			var confirmaCuenta = $('#confirmaCuenta').val();
			
			if(parseInt(otrac)==1){//si captura nueva cuenta
				//valida que los campos de clabe interbancaria hayan sido capurados
				if((cuenta==null||cuenta =="")||(confirmaCuenta==null||confirmaCuenta =="") ){
					$('#dialogo_1').html('Debe capturar las clabes interbancarias');
					abrirDialogo();
					return false;
				}
			}else{//valida que se haya seleccionado una clabe interbancaria
				if(clabe==-1){
					$('#dialogo_1').html('Seleccione la clabe interbancaria');
					abrirDialogo();
					return false;
				}
			}
		}else{//el comprador no tiene cuentas asociadas tieneCuenta == 0
			if((cuenta==null||cuenta =="")||(confirmaCuenta==null||confirmaCuenta =="") ){
				$('#dialogo_1').html('Debe capturar las clabes interbancarias');
				abrirDialogo();
				return false;
			}
		}
		/* ***Valida información de RFC*** */
		if(parseInt(tieneRfc)==0){//si el comprador no tiene rfc, debe capturar el campo de rfc
			var rfc = $('#rfcc').val();
			if((rfc==null||rfc =="")){
				$('#dialogo_1').html('Debe capturar el RFC');
				abrirDialogo();
				return false;
			}else{
				patron =/^[A-Z]{4}\\d{6}([A-Z0-9 ]{3}){0,1}":"[A-Z]{3}\\d{6}[A-Z0-9]{3}$/;
				/*if(!rfc.match(patron)){
					$('#dialogo_1').html('El RFC es inválido, favor de verificar');
			   		abrirDialogo();
			 		return false;	
				}*/
			}
			
			if(curp != "" && curp!=null ){
				patron = /^[A-Z]{4}\\d{6}(H|M)(AS|BC|BS|CC|CL|CM|CS|CH|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[A-Z]{3}\\d{2}$/;
				/*if(!curp.match(patron)){
					$('#dialogo_1').html('El CURP es inválido, favor de verificar');
			   		abrirDialogo();
			 		return false;
				}*/
			}
		}
		//}
		/** Valida si hubo un error en validacion de clabe interbancaria**/
		if ($('#errorClabeInvalida').length){
			var errorClabeInvalida = $('#errorClabeInvalida').val();
			if(errorClabeInvalida!=0){
				$('#dialogo_1').html('La clabe interbancaria es incorrecta, favor de verificar');
		   		abrirDialogo();
		 		return false;	
			}
		}	
		/** Valida si hubo un error en validacion de confirmacion de clabe interbancaria**/
		if ($('#errorConfClabeInvalida').length){
			var errorConfClabeInvalida = $('#errorConfClabeInvalida').val();
			if(errorConfClabeInvalida!=0){
				$('#dialogo_1').html('La clabe interbancaria es incorrecta, favor de verificar');
		   		abrirDialogo();
		 		return false;
			}
		}	
		
		/*Valida que la sucursal sea capturada */
		if($('#sucursal').length){
			var sucursal = $('#sucursal').val();
			if(sucursal == "" || sucursal == null ){
				$('#dialogo_1').html('Capture el nombre de la sucursal');
		   		abrirDialogo();
		   		return false;
			}
			
		}
		
	}// termina comprador != -1
	
	/*Valida que la etapa sea capturada en caso de que haya*/
	if($('#etapa').length){
		var etapa = $('#etapa').val();
		if(etapa==-1){
			$('#dialogo_1').html('Capture el valor de la etapa');
	   		abrirDialogo();
	   		return false;
		}
	}
	//validaCamposEdoApoyar(numCampos);
	if(numCampos==-1){
		$('#dialogo_1').html('Seleccione el número de estados a apoyar');
		abrirDialogo();
	 	return false;
	}else{
		var edo = -1;
		var volumen ="";
		var importe ="";
		var tempEdo ="";
		var etapa ="";
		var criterioPago = $('#criterioPago').val();
		
		for (i=1;i<parseInt(numCampos)+1;i++){
			edo = $('#e'+i).val();
			if(criterioPago == 1 || criterioPago == 3){
				volumen = $('#v'+i).val();
			}
			
			if(criterioPago == 2 || criterioPago == 3){
				etapa = $('#etapa'+i).val();
			}
			
			importe = $('#i'+i).val();
			if(criterioPago ==1){
				if(edo==-1 || (volumen == "" || volumen == null) || (importe == "" || importe == null) ){
		   			$('#dialogo_1').html('Capture los valores de estados a apoyar en el registro '+i);
			   		abrirDialogo();
			 		return false;
		   		 }	
			}else if(criterioPago == 2){
				if(edo==-1 || (etapa == "" || etapa == null) || (importe == "" || importe == null) ){
		   			$('#dialogo_1').html('Capture los valores de estados a apoyar en el registro '+i);
			   		abrirDialogo();
			 		return false;
		   		 }
			}else{
				if(edo==-1 || (etapa == "" || etapa == null) ||(volumen == "" || volumen == null)|| (importe == "" || importe == null) ){
		   			$('#dialogo_1').html('Capture los valores de estados a apoyar en el registro '+i);
			   		abrirDialogo();
			 		return false;
		   		 }
			}
			
   			 /*Valida que el estado no se encuentre repetido*/
   			for (j=1;j<parseInt(numCampos)+1;j++){
   				if(i!=j){
   					tempEdo = $('#e'+j).val();
   					if(edo == tempEdo){
   						$('#dialogo_1').html('El estado del registro '+i+", se encuentra repetido, favor de verificar");
   				   		abrirDialogo();
   				 		return false;
   					}
   				}
   				
   			}
   			
   			if(criterioPago==1||criterioPago==3){
   				patron =/^\d{1,10}((\.\d{1,3})|(\.))?$/;
      			 if(!volumen.match(patron)){
   	   			$('#dialogo_1').html('El valor de volumen "'+volumen+'" del registro '+i+' es inválido, se aceptan hasta 10 digitos a la izquierda y 3 digitos máximo a la derecha');
   		   		abrirDialogo();
   		 		return false;
      			}
   			}
   			
   			if(criterioPago==2||criterioPago==3){
   				if(etapa == -1){
   					$('#dialogo_1').html('El valor de etapa es inválido, por favor verifique');
   	   		   		abrirDialogo();
   	   		 		return false;
   				}
   			}
   			 
			patron =/^\d{1,13}((\.\d{1,2})|(\.))?$/;
			if(!importe.match(patron)){
				$('#dialogo_1').html('El valor del importe "'+importe+'" del registro '+i+' es inválido, se aceptan hasta 13 digitos a la izquierda con 2 máximo a la derecha');
		   		abrirDialogo();
		 		return false;
			}
   			 
	   			
	   		 //}
			
		}// end for
	}// end validacion de los campos de estados a apoyar

	
		
}

/*function errorCapturaSucursal(){
	var sucursal = $('#sucursal').val();
	if(sucursal == "" || sucursal == null ){
		$('#dialogo_1').html('Capture el nombre de la sucursal');
   		abrirDialogo();
   		return false;
	}
}*/
/*function errorClabeInvalida(){
	var errorClabeInvalida = $('#errorClabeInvalida').val();
	if(errorClabeInvalida!=0){
		$('#dialogo_1').html('La clabe interbancaria es incorrecta, favor de verificar');
   		abrirDialogo();
 		return false;	
	}
}*/

/*function errorConfClabeInvalida(){
	var errorConfClabeInvalida = $('#errorConfClabeInvalida').val();
	if(errorConfClabeInvalida!=0){
		$('#dialogo_1').html('La clabe interbancaria es incorrecta, favor de verificar');
   		abrirDialogo();
 		return false;
	}
	
}*/
function validaCamposEdoApoyar(numCampos){
	
}
function agregaEdoPorVolumen(editar){ 
	var numCampos = $('#numCampos').val();
	var idPrograma = $('#idPrograma').val();
	var criterioPago = $('#criterioPago').val();
	var idPago = 0;

	
	if(editar == 1){
		idPago = $('#idPago').val();
	}
	/*if ($('#editar').length){
		idPago = $('#idPago').val();	
		editar=1;
	}*/
	if(numCampos==-1){
		$("#agregaEdoPorVolumen").fadeOut('slow');
		return false;	
	}

   $.ajax({
	   async: false,
	   type: "POST",
	   url: "agregaEdoPorVolumen",
	   data: "numCampos="+numCampos+
	   		 "&idPrograma="+idPrograma+
	   		 "&idPago="+idPago+
	   		 "&editar="+editar+
	   		 "&criterioPago="+criterioPago,
	   success: function(data){
			$('#agregaEdoPorVolumen').html(data).ready(function () {
				$("#agregaEdoPorVolumen").fadeIn('slow');
			});
	   }
	}); //termina ajax
}

function calculaTotalImporte(imp){
	var numCampos = $('#numCampos').val();
	var totalImporte = 0;
	var importe = null;
	for (i=1;i<parseInt(numCampos)+1;i++){
		importe = $('#i'+i).val();
		if(importe!="" && importe !=null){
			totalImporte = (totalImporte+parseFloat(importe));
		}

	}	
	$("#totalesImporte").val("$ "+totalImporte.toFixed(2));	
	
}


/*
function calculaTotalVolumen(v){
	var numCampos = $('#numCampos').val();
	var totalVolumen = null;
	var volumen = null;
	
	/*Calcula el valor total de volumen*/
	/*for (i=1;i<parseInt(numCampos)+1;i++){
		volumen = $('#v'+i).val();
		if(volumen!="" && volumen !=null){
				totalVolumen = (totalVolumen+parseFloat(volumen));	
		}
	}
	
	$("#totalesVolumen").val(totalVolumen.toFixed(3));	
	
}*/
function calculaCuota(volumen, importe, index){
	var cuota = parseFloat(importe)/parseFloat(volumen);
	$("#cuota"+index).val(parseFloat(cuota.toFixed(2)));	
	
}


