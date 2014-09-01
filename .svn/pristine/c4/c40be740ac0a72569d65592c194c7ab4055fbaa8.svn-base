$(document).ready(function(){
	$.ajaxSetup({
		'beforeSend' : function(xhr) {
			try{
				xhr.overrideMimeType('text/html; charset=iso-8859-1');
			}catch(e){}
		}});
});


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

function chkCamposCuentasBancarias(){	
	console.log("chkCamposCuentasBancarias");
	var tieneCuenta = $('#tieneCuenta').val();
	var cuenta = $('#cuenta').val();
	var confirmaCuenta = $('#confirmaCuenta').val();
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
				$('#dialogo_1').html('Seleccione la clabe interbancaria, para verificar los datos');
				abrirDialogo();
				return false;
			}
			if($('#sucursal').length){
				var sucursal = $('#sucursal').val();
				//$("#accion").css("display", "inline");
				if(sucursal == "" || sucursal == null ){
					$('#dialogo_1').html('Capture el nombre de la sucursal');
			   		abrirDialogo();
			   		return false;
				}
				
			}
		}
	}else{//el comprador no tiene cuentas asociadas tieneCuenta == 0
		if((cuenta==null||cuenta =="")||(confirmaCuenta==null||confirmaCuenta =="") ){
			$('#dialogo_1').html('Debe capturar las clabes interbancarias');
			abrirDialogo();
			return false;
		}
	}
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
	
	
}