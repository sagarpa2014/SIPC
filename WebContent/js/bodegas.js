$(document).ready(function(){
	$.ajaxSetup({
		'beforeSend' : function(xhr) {
			try{
				xhr.overrideMimeType('text/html; charset=iso-8859-1');
			}catch(e){}
		}});
});

function chkCamposBodega(){	
	
	//UBICACION DE LA BODEGA
	var claveBodega =  $('#claveBodega').val();
	var fechaRegistro =  $('#fechaRegistro').val();
	var claveMunicipio =  $('#claveMunicipio').val();
	var ejido =  $('#ejido').val();
	var patron ="";
	//Validacion de la clave de la bodega
	if((claveBodega== null || claveBodega=="")){
		$('#dialogo_1').html('Capture la clave de la bodega');			
		abrirDialogo();
		return false;
	}
	if($('#errorClaveBodega').length){
		if( $('#errorClaveBodega').val() != 0 ){
			$('#dialogo_1').html('Capture correctamente la clave de la bodega');
		   	abrirDialogo();
		}
	}
	//Fecha Registro
	if($('#fechaRegistro').val()==null || $('#fechaRegistro').val()==""){
		$('#dialogo_1').html('Capture la fecha de registro');
		abrirDialogo();
		return false;
	}else{
		var fechaActualL = $('#fechaActualL').val();
		var dia = fechaRegistro.substring(0,2);
		var mes = fechaRegistro.substring(3,5);
		var anio = fechaRegistro.substring(6,10);
		var fechaTemp = anio+""+""+mes+""+dia;
		if(parseInt(fechaTemp) > parseInt(fechaActualL)){
			$('#dialogo_1').html('La fecha de registro no puede ser mayor a la fecha actual, por favor verifique');
			abrirDialogo();		
			return false;
		}
	}
	//municipio
	if(claveMunicipio == -1){
		$('#dialogo_1').html('Seleccione el municipio de la ubicación de la bodega');
		abrirDialogo();		
		return false;
	}
	//ejido
	if(ejido == -1){
		$('#dialogo_1').html('Seleccione el ejido de la ubicación de la bodega');
		abrirDialogo();		
		return false;
	}
	//LATITUD
	if($('#latitudGrados').val()==null || $('#latitudGrados').val()== ""){
		$('#dialogo_1').html('Capture los grados de la latitud');
		abrirDialogo();		
		return false;
	}
	if($('#latitudMinutos').val()==null || $('#latitudMinutos').val()== ""){
		$('#dialogo_1').html('Capture los minutos de la latitud');
		abrirDialogo();		
		return false;
	}
	
	if($('#latitudSegundos').val()==null || $('#latitudSegundos').val()== ""){
		$('#dialogo_1').html('Capture los segundos de la latitud');
		abrirDialogo();		
		return false;
	}
	//LONGITUD
	if($('#longitudGrados').val()==null || $('#longitudGrados').val()== ""){
		$('#dialogo_1').html('Capture los grados de la longitud');
		abrirDialogo();		
		return false;
	}
	
	if($('#longitudMinutos').val()==null || $('#longitudMinutos').val()== ""){
		$('#dialogo_1').html('Capture los minutos de la longitud');
		abrirDialogo();		
		return false;
	}
	
		
	if($('#longitudSegundos').val()==null || $('#longitudSegundos').val()== ""){
		$('#dialogo_1').html('Capture los segundos de la longitud');
		abrirDialogo();		
		return false;
	}	
	
	//DATOS GENERALES
	//Clasificacion	
	if($('#clasificacion').val() == -1){
		$('#dialogo_1').html('Seleccione la clasificación');
		abrirDialogo();		
		return false;
	}
	if($('#claveUso1').val() ==-1){
		$('#dialogo_1').html('Seleccione la clave de uso del centro de acopio');
		abrirDialogo();		
		return false;
	}
	
	if($('#rfcBodega').val()==null || $('#rfcBodega').val()== ""){
		$('#dialogo_1').html('Capture el rfc de la bodega');
		abrirDialogo();		
		return false;
	}else{
		var rfcBodega =  $('#rfcBodega').val();
		patron = /^([A-Z,a-z]{3,4})([0-9]{6})([A-Z,a-z,0-9]{3})$/;
		if(!rfcBodega.match(patron)){
			$('#dialogo_1').html('Escriba correctamente el RFC con homoclave');
			abrirDialogo();
			return false;
		}
	}
	if($('#nombreRazonSocial').val()==null || $('#nombreRazonSocial').val()== ""){
		$('#dialogo_1').html('Capture el nombre o razón social del centro de acopio');
		abrirDialogo();		
		return false;
	}
	if($('#domicilioFiscal').val()==null || $('#domicilioFiscal').val()== ""){
		$('#dialogo_1').html('Capture el domicilio fiscal (como aparece en la Cédula Fiscal) del centro de acopio.');
		abrirDialogo();		
		return false;
	}
	if($('#calleDomFiscal').val()==null || $('#calleDomFiscal').val()== ""){
		$('#dialogo_1').html('Capture el nombre de la calle del centro de acopio.');
		abrirDialogo();		
		return false;
	}
	if($('#numeroDomFiscal').val()==null || $('#numeroDomFiscal').val()== ""){
		$('#dialogo_1').html('Capture el número del domicilio del centro de acopio.');
		abrirDialogo();		
		return false;
	}
	if($('#idEstadoDomFiscal').val()==-1){
		$('#dialogo_1').html('Capture la entidad federativa del centro de acopio.');
		abrirDialogo();		
		return false;
	}
	if($('#claveMunicipioDomFis').val()==-1){
		$('#dialogo_1').html('Capture el municipio del centro de acopio.');
		abrirDialogo();		
		return false;
	}
	if($('#claveLocalidadDomFis').val()==-1){
		$('#dialogo_1').html('Capture la localidad del centro de acopio.');
		abrirDialogo();		
		return false;
	}
	if($('#cpDomFiscal').val()==null || $('#cpDomFiscal').val()== ""){
		$('#dialogo_1').html('Capture el código postal del centro de acopio.');
		abrirDialogo();		
		return false;
	}
	if($('#telefonoDomFis').val()==null || $('#telefonoDomFis').val()== ""){
		$('#dialogo_1').html('Capture el télefono con clave lada.');
		abrirDialogo();		
		return false;
	}
	if($('#faxDomFis').val()==null || $('#faxDomFis').val()== ""){
		$('#dialogo_1').html('Capture el fax del centro de acopio.');
		abrirDialogo();		
		return false;
	}
	if($('#personaAutorizada1').val()==null || $('#personaAutorizada1').val()== ""){
		$('#dialogo_1').html('Capture los datos de la persona 1 autorizada, para proporcionar información.');
		abrirDialogo();		
		return false;
	}
	if($('#puestoPersonaAutorizada1').val()==null || $('#puestoPersonaAutorizada1').val()== ""){
		$('#dialogo_1').html('Capture los datos del puesto de la persona 1 autorizada.');
		abrirDialogo();		
		return false;
	}

	if($('#personaAutorizada2').val()==null || $('#personaAutorizada2').val()== ""){
		$('#dialogo_1').html('Capture los datos de la persona 2 autorizada, para proporcionar información.');
		abrirDialogo();		
		return false;
	}
	if($('#puestoPersonaAutorizada2').val()==null || $('#puestoPersonaAutorizada2').val()== ""){
		$('#dialogo_1').html('Capture los datos del puesto de la persona 2 autorizada.');
		abrirDialogo();		
		return false;
	}
		
	//CAPACIDAD DE ALMACENAMIENTO
	if($('#capacidadBodega').val()==null || $('#capacidadBodega').val()== ""){
		$('#dialogo_1').html('Capture la capacidad de bodega.');
		abrirDialogo();		
		return false;
	}
		
	if($('#capacidadTechada').val()==null || $('#capacidadTechada').val()== ""){
		$('#dialogo_1').html('Capture la capacidad Tejaban.');
		abrirDialogo();		
		return false;
	}
	
	if($('#capacidadSilos').val()==null || $('#capacidadSilos').val()== ""){
		$('#dialogo_1').html('Capture la capacidad Silos.');
		abrirDialogo();		
		return false;
	}
	if($('#intemperie').val()==null || $('#intemperie').val()== ""){
		$('#dialogo_1').html('Capture la capacidad de Intemperie.');
		abrirDialogo();		
		return false;
	}
	if($('#noSilos').val()==null || $('#noSilos').val()== ""){
		$('#dialogo_1').html('Capture el número de silos.');
		abrirDialogo();		
		return false;
	}
	if($('#totalAlmacenamiento').val()==null || $('#totalAlmacenamiento').val()== ""){
		$('#dialogo_1').html('Capture la capacidad de almacenamiento.');
		abrirDialogo();		
		return false;
	}
	if($('#numRampas').val()==null || $('#numRampas').val()== ""){
		$('#dialogo_1').html('Capture el número de rampas en la recepción.');
		abrirDialogo();		
		return false;
	}
	if($('#capacidadRampas').val()==null || $('#capacidadRampas').val()== ""){
		$('#dialogo_1').html('Capture la capacidad en la recepción.');
		abrirDialogo();		
		return false;
	}
	if($('#numSecadoras').val()==null || $('#numSecadoras').val()== ""){
		$('#dialogo_1').html('Capture el número de secadoras en el secado.');
		abrirDialogo();		
		return false;
	}
	if($('#capacidadSecado').val()==null || $('#capacidadSecado').val()== ""){
		$('#dialogo_1').html('Capture la capacidad de secado.');
		abrirDialogo();		
		return false;
	}
	if($('#numCargas').val()==null || $('#numCargas').val()== ""){
		$('#dialogo_1').html('Capture el número de cargas en el embarque.');
		abrirDialogo();		
		return false;
	}
	if($('#capacidadCarga').val()==null || $('#capacidadCarga').val()== ""){
		$('#dialogo_1').html('Capture la capacidad de embarque.');
		abrirDialogo();		
		return false;
	}
	if($('#piso').val()==-1){
		$('#dialogo_1').html('Seleccione si aplica báscula de piso.');
		abrirDialogo();		
		return false;
	}
	
	if($('#piso').val()=='S'){
		if($('#capacidadPiso').val()==null || $('#capacidadPiso').val()== ""){
			$('#dialogo_1').html('Capture la capacidad de báscula de piso.');
			abrirDialogo();		
			return false;
		}
	}
	
	if($('#camionera').val()==-1){
		$('#dialogo_1').html('Seleccione si aplica báscula camionera.');
		abrirDialogo();		
		return false;
	}
	
	if($('#camionera').val()=='S'){
		if($('#capacidadCamionera').val()==null || $('#capacidadCamionera').val()== ""){
			$('#dialogo_1').html('Capture la capacidad de báscula camionera.');
			abrirDialogo();		
			return false;
		}
	}
	
	if($('#ffcc').val()==-1){
		$('#dialogo_1').html('Seleccione si aplica báscula ffcc.');
		abrirDialogo();		
		return false;
	}
	
	if($('#ffcc').val()=='S'){
		if($('#capacidadFfcc').val()==null || $('#capacidadFfcc').val()== ""){
			$('#dialogo_1').html('Capture la capacidad de báscula ffcc.');
			abrirDialogo();		
			return false;
		}
	}
	
	if($('#espuelaFFCC').val()==-1){
		$('#dialogo_1').html('Seleccione si aplica espuela de ffcc.');
		abrirDialogo();		
		return false;
	}
	
	if($('#espuelaFFCC').val()=='S'){
		if($('#capacidadEspuela').val()==null || $('#capacidadEspuela').val()== ""){
			$('#dialogo_1').html('Capture la capacidad de espuela ffcc.');
			abrirDialogo();		
			return false;
		}
	}
	
	if($('#fechaCalibracion').val()==null || $('#fechaCalibracion').val()== ""){
		$('#dialogo_1').html('Capture la fecha de calibración.');
		abrirDialogo();		
		return false;
	}
	if($('#equipoAnalisis').val()==-1){
		$('#dialogo_1').html('Seleccione si existe equipo de análisis.');
		abrirDialogo();		
		return false;
	}
	if($('#aplicaNormasCalidad').val()==-1){
		$('#dialogo_1').html('Seleccione si aplica normas de calidad.');
		abrirDialogo();		
		return false;
	}
	
	//DATOS DEL REPRESENTANTE LEGAL
	if($('#rfcRepresentanteLegal').val()==null || $('#rfcRepresentanteLegal').val()== ""){
		$('#dialogo_1').html('Capture el RFC del Representate');
		abrirDialogo();		
		return false;
	}
	if($('#errorRepresentanteLegal').length){
		if( $('#errorRepresentanteLegal').val() != 0 ){
			$('#dialogo_1').html('Capture correctamente el rfc del representante legal');
		   	abrirDialogo();
		   	return false;
		}
	}
	
	$("#totalAlmacenamiento").removeAttr('disabled');
}


function validaRango(valor, id, tipo, msj){
	
	var patron =/^\d{1,2}$/;
	if (!valor.match(patron)){
		$('#dialogo_1').html('El valor del campo es inválido, se deben capturar de 1 a 2 números, por favor verifique');
		abrirDialogo();
		$('#'+id).val(null);
		return false;
	}
	if(tipo == 1){//Valida Grados
		if(!(valor > 11 && valor < 34)){
			$('#dialogo_1').html(msj+' grados debe ser mayor a 11 y menor a 34 ');
			abrirDialogo();	
			$('#'+id).val(null);
			return false;
		}
	}else if(tipo == 2 || tipo == 3){//valida minutos o segundos
		if(!(valor >= 0 && valor < 60)){
			$('#dialogo_1').html(msj+' debe ser mayor igual a 0 y menor a 60 ');
			$('#'+id).val(null);
			abrirDialogo();		
			return false;
		}
	}
}
function validaComposicionClaveBodega(){
	var claveBodega =  $('#claveBodega').val();
	var registrar =  $('#registrar').val();
	var patron = /^B([0-9]{2})([0-9]{3})([0-9]{2})([0-9]{3})$/;//2 Entidad Federativa //3 DDR //2 CADER //3 CONSECUTIVO
	
	if(claveBodega!=null && claveBodega!=""){
		if (!claveBodega.match(patron)){
			$('#dialogo_1').html('La clave de la bodega es incorrecto, se debe capturar conforme a la siguiente nomenclatura ejemplo: "B0100101002"');
		   	abrirDialogo();
		   	return false;		
		}else{	
			$.ajax({
				   async: false,
				   type: "POST",
				   url: "validaComposicionClaveBodega",
				   data: "claveBodega="+claveBodega
				   		+"&registrar="+registrar,
				   success: function(data){
						$('#validaComposicionClaveBodega').html(data).ready(function () {
							$("#validaComposicionClaveBodega").fadeIn('slow');
						});
				   }
			});//fin ajax
		}
	}else{
		//Reiniciar valores a nulos
		$('#nombreEstado').val(null);
		$('#ddr').val(null);
		$('#cader').val(null);
		$('#claveMunicipio').val(-1);
		$('#ejido').val(-1);
	}
	
}


function recuperaMunicipioByEdoBodegas(idEstado){
	
	if(idEstado == -1){
		$("#recuperaMunicipioByEstado").fadeOut('slow');
		return false;
	}
	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "recuperaMunicipioByEdoBodegas",
		   data: "idEstado="+idEstado, 
		   success: function(data){
				$('#recuperaMunicipioByEdoBodegas').html(data).ready(function () {
					$("#recuperaMunicipioByEdoBodegas").fadeIn('slow');
					
				});
		   }
		});//fin ajax
}


function recuperaLocalidadesByMunicipioBodegas(idEdo, idMpo){
	var idEstado = $('#'+idEdo).val();
	var claveMunicipio = $('#'+idMpo).val();
	if(idEstado == -1){
		$("#recuperaLocalidadesByMunicipioBodegas").fadeOut('slow');
		return false;
	}	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "recuperaLocalidadesByMunicipioBodegas",
		   data: "idEstado="+idEstado + "&claveMunicipio="+claveMunicipio, 
		   success: function(data){
				$('#recuperaLocalidadesByMunicipioBodegas').html(data).ready(function () {
					$("#recuperaLocalidadesByMunicipioBodegas").fadeIn('slow');
					
				});
		   }
		});//fin ajax
}

function recuperaDatosRepresentanteBodega(){
	var rfcRepresentanteLegal = $('#rfcRepresentanteLegal').val();
	
	if($('#rfcRepresentanteLegal').val()==null || $('#rfcRepresentanteLegal').val()== ""){
		$('#dialogo_1').html('Existe un error en el rfc del representante legal, por favor verifique');
	   	abrirDialogo();
	   	return false;
	}
		
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "recuperaDatosRepresentanteBodega",
		   data: "rfcRepresentanteLegal="+rfcRepresentanteLegal, 
		   success: function(data){
				$('#recuperaDatosRepresentanteBodega').html(data).ready(function () {
					$("#recuperaDatosRepresentanteBodega").fadeIn('slow');
					
				});
		   }
		});//fin ajax
}