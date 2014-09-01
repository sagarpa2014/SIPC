$(document).ready(function(){
	$.ajaxSetup({
		'beforeSend' : function(xhr) {
			try{
				xhr.overrideMimeType('text/html; charset=iso-8859-1');
			}catch(e){}
		}});
});

function recuperaMunicipioByEstado(){
	var idEstado = $('#idEstado').val();
	if(idEstado == -1){
		$("#recuperaMunicipioByEstado").fadeOut('slow');
		return false;
	}
	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "recuperaMunicipioByEstado",
		   data: "idEstado="+idEstado, 
		   success: function(data){
				$('#recuperaMunicipioByEstado').html(data).ready(function () {
					$("#recuperaMunicipioByEstado").fadeIn('slow');
					
				});
		   }
		});//fin ajax
}
	function recuperaLocalidadesByMunicipio(){
		var idEstado = $('#idEstado').val();
		var claveMunicipio = $('#claveMunicipio').val();
		if(idEstado == -1){
			$("#recuperaLocalidadesByMunicipio").fadeOut('slow');
			return false;
		}
		
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "recuperaLocalidadesByMunicipio",
			   data: "idEstado="+idEstado + "&claveMunicipio="+claveMunicipio, 
			   success: function(data){
					$('#recuperaLocalidadesByMunicipio').html(data).ready(function () {
						$("#recuperaLocalidadesByMunicipio").fadeIn('slow');
						
					});
			   }
			});//fin ajax
		
		
}
	
function validarNumeroAuditor(){
	numeroAuditor = $('#numeroAuditor').val();
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "validarNumeroAuditor",
		   data: "numeroAuditor="+numeroAuditor,
		   success: function(data){
				$('#validarNumeroAuditor').html(data).ready(function () {
					$("#validarNumeroAuditor").fadeIn('slow');
					
				});
		   }
		});//fin ajax
}


	
	function chkCamposAuditor(){
		//Validando los campos del formulario
		
/*		var rfc = $('#rfcc').val();
		console.log($('#rfcc').val());
		patron = /^([A-Z]{3,4})([0-9]{6})([A-Z,0-9]{3})$/;
		if(rfc == "" || rfc == null){
			$('#dialogo_1').html('Escriba el RFC del Auditor');
		   	abrirDialogo();
		   	return false;
		}else if (!rfc.match(patron)){
			$('#dialogo_1').html('Escriba corectamente el RFC del Auditor');
		   	abrirDialogo();
		   	return false;
		}
		
		var curp = $('#curpc').val();
		console.log($('#curpc').val());
		patron = /^([A-Z]{3,4})([0-9]{6})(H|M)(AS|BC|BS|CC|CL|CM|CS|CH|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)([A-Z]{3})([A-Z,0-9]{3})$/;
		if(curp == "" || curp == null){
			$('#dialogo_1').html('Escriba el RFC del Auditor');
		   	abrirDialogo();
		   	return false;
		}else if (!curp.match(patron)){
			$('#dialogo_1').html('Escriba corectamente el RFC del Auditor');
		   	abrirDialogo();
		   	return false;
		}
	*/	
		
		var numeroAuditor = $('#numeroAuditor').val();
		if((numeroAuditor == null || numeroAuditor == "")){
			$('#dialogo_1').html('Escriba el n&uacute;mero del Auditor');
			abrirDialogo();
		 	return false;
		}else{
			if ($('#errorNumeroAuditor').length){
				if(errorNumeroAuditor!=0){
					$('#dialogo_1').html('El n&uacute;mero del Auditor ya se encuentra registrado, por favor verifique');
			   		abrirDialogo();
			 		return false;	
				}
			}
		}
	
		var nombre = $('#nombre').val();
		if((nombre == null || nombre == "")){
			$('#dialogo_1').html('Escriba el nombre del Auditor');
			abrirDialogo();
			return false;
		}
		
		
		var numeroRegistroDespacho = $('#numeroRegistroDespacho').val();
		console.log($('#numeroRegistroDespacho').val());
		patron = /^(.(0,10))$/;
		if (numeroRegistroDespacho != null && numeroRegistroDespacho.isEmpty){
			if (!numeroRegistroDespacho.match(patron)){
				$('#dialogo_1').html('Únicamente se aceptan valores numericos');
				abrirDialogo();
			}
			return false;
		}
		var codigoPostal = $('#codigoPostal').val();
		patron = /^([0-9]{1,9})$/;
		if (codigoPostal != null && codigoPostal != ""){
			if(!codigoPostal.match(patron)){
				$('#dialogo_1').html('El Código Postal acepta únicamente valores numéricos');
				abrirDialogo();
				return false;
			}
		}	
		var editar = $('#editar').val();
		if (editar==3){
			$("#numeroAuditor").removeAttr('disabled');
		}
		
	
	}
	
			
			
		
		/*	
		var despacho = $('#despacho').val();
		if((despacho == null || despacho == "")){
			$('#dialogo_1').html('Escriba el despacho del Auditor');
			abrirDialogo();
			return false;
		}
		
		var idEstado = $('#idEstado').val();
		if((idEstado == null || idEstado == "" || idEstado ==-1)){
			$('#dialogo_1').html('Seleccione el estado del Auditor');
			abrirDialogo();
		 	return false;
		}
		
		var claveMunicipio = $('#claveMunicipio').val();
		if((claveMunicipio == null || claveMunicipio == ""|| claveMunicipio ==-1)){
			$('#dialogo_1').html('Seleccione el municipio del Auditor');
			abrirDialogo();
		 	return false;
		}
		
		var calle = $('#calle').val();
		if((calle == null || calle == "")){
			$('#dialogo_1').html('Escriba la calle del Auditor');
			abrirDialogo();
		 	return false;
		}
		
*/			
	