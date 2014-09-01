$(document).ready(function(){
	$.ajaxSetup({
		'beforeSend' : function(xhr) {
			try{
				xhr.overrideMimeType('text/html; charset=iso-8859-1');
			}catch(e){}
		}});
});

function selectTipoOficio(){
	var tipoOficio = $('input:radio[name=tipoOficio]:checked').val();
	var urlenvio = "oficioEnvio";
	if(tipoOficio==1){
		urlenvio = "archivoEnvio";
	}
	$("#contenidoTipoOficio").fadeOut('slow', function() {
		  	$.ajax({
			   async: false,
			   type: "POST",
			   url: urlenvio,
			   success: function(data){
					$('#contenidoTipoOficio').html(data).ready(function () {
						$("#contenidoTipoOficio").fadeIn('slow');
					});
			   }
			});
	 });
}
function recuperaEdosPorPrograma(){
	var idPrograma = $('#idPrograma').val();
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "recuperaEdosPorPrograma",
		   data: "idPrograma="+idPrograma,
		   success: function(data){
				$('#recuperaEdosPorPrograma').html(data).ready(function () {
					$("#recuperaEdosPorPrograma").fadeIn('slow');
				});
		   }
		});
	
}

function recuperaPagos(){
	var idPrograma = $('#idPrograma').val();
	if(idPrograma == -1){
		$("#recuperaPagos").fadeOut('slow');
		return false;
	}else{
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "recuperaPagos",
			   data: "idPrograma="+idPrograma,
			   success: function(data){
					$('#recuperaPagos').html(data).ready(function () {
						$("#recuperaPagos").fadeIn('slow');
					});
			   }
			});
	}
}

function vistaPreviaOficio(){
	var idPrograma = $('#idPrograma').val();
	var claveOficio = $('#claveOficio').val();
	var noOficio = $('#noOficio').val();
	var anioOficio = $('#anioOficio').val();
	
	
	if(claveOficio==null || claveOficio==""){
		   $('#dialogo_1').html('Capture el Número de clave de oficio.');
			abrirDialogo();
		    return false;
	}
	if(noOficio==null || noOficio==""){
		   $('#dialogo_1').html('Capture el número de Oficio.');
			abrirDialogo();
		    return false;
	}
	if(anioOficio==null || anioOficio==""){
		   $('#dialogo_1').html('Capture el año del oficio.');
			abrirDialogo();
		    return false;
	}
	
	var selectedPagos = new Array();
	$("input[type='checkbox']:checked").each(function() {selectedPagos.push($(this).val());});
	if (selectedPagos.length == 0){ 
	    $('#dialogo_1').html('Por favor seleccione los pagos a generar');
		abrirDialogo();
	    return false;
	}
	
	$("#pagos").fadeOut('5000', function(){
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "vistaPreviaOficio",
			   data: "idPrograma="+idPrograma+
		   		"&claveOficio="+claveOficio+
		   		"&noOficio="+noOficio+
		   		"&anioOficio="+anioOficio+
		   		"&selectedPagos="+selectedPagos,
			   success: function(data){
					$('#vistaPrevia').html(data).ready(function () {
						$("#vistaPrevia").fadeIn('slow');
					});
			   }
			});
		});
}
function cerrarVistaPreviaOficio(){
	
	$("#vistaPrevia").fadeOut('3000', function(){
		$("#pagos").fadeIn('3000');	
	});
}
function cerrarErrorOficio(){
	$("#resultadoGeneracionOficio").fadeOut('3000', function(){
		$("#pagos").fadeIn('3000');	
	});
}

function limpiaPagos(){
	$("#pagos").fadeOut('3000');	
	
}

function generarOficioEnvioCGCDGAF(){
	var idPrograma = $('#idPrograma').val();
	//var idEstado = $('#idEstado').val();
	var claveOficio = $('#claveOficio').val();
	var noOficio = $('#noOficio').val();
	var anioOficio = $('#anioOficio').val();
	
	
	if(claveOficio==null || claveOficio==""){
		   $('#dialogo_1').html('Capture el Número de clave de oficio.');
			abrirDialogo();
		    return false;
	}
	if(noOficio==null || noOficio==""){
		   $('#dialogo_1').html('Capture el número de Oficio.');
			abrirDialogo();
		    return false;
	}
	if(anioOficio==null || anioOficio==""){
		   $('#dialogo_1').html('Capture el año del oficio.');
			abrirDialogo();
		    return false;
	}
	
	
	var selectedPagos = new Array();
	$("input[type='checkbox']:checked").each(function() {selectedPagos.push($(this).val());});
	if (selectedPagos.length == 0){ 
	    $('#dialogo_1').html('Por favor seleccione los pagos a generar');
		abrirDialogo();
	    return false;
	}
	
	$("#pagos").fadeOut('5000', function(){
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "generarOficioEnvioCGCDGAF",
			   data: "idPrograma="+idPrograma+
		   		"&claveOficio="+claveOficio+
		   		"&noOficio="+noOficio+
		   		"&anioOficio="+anioOficio+
		   		"&selectedPagos="+selectedPagos,
			   success: function(data){
					$('#resultadoGeneracionOficio').html(data).ready(function () {
						$("#resultadoGeneracionOficio").fadeIn('slow');
					});
			   }
			});
		});
}

function chkCampoConsultaOficio(){
	var oficioCGC = $('#oficioCGC').val();
	var folioCLC = $('#folioCLC').val();
	
	if((oficioCGC==null || oficioCGC=="")&& (folioCLC==null || folioCLC=="")){
		$('#dialogo_1').html('Debe capturar al menos un dato, para realizar la consulta');
   		abrirDialogo();
   		return false;
	}
}
function chkFile(){
	var val = $('#fileUpload').val();
	if(val==null || val == "")
	{
		$('#dialogo_1').html('Seleccione el archivo por favor.');
		abrirDialogo();
	    return false;
	}else{
		return true;	
	}		
}
function chkFile1(){
	//var val = document.getElementById("f").value;
	var val = $('#fileUpload1').val();
	if(val==null || val == "")
	{
		$('#dialogo_1').html('Seleccione el archivo por favor.');
		abrirDialogo();
		return false;
	}else{
		return true;	
	}		
}

function chkFileEscaneados(){
	var cgcDgaf = $('#cgcDgaf').val();
	var dgafCgc = $('#dgafCgc').val();
	if((cgcDgaf==null || cgcDgaf == "")&&(dgafCgc==null || dgafCgc == "")){
		$('#dialogo_1').html('Seleccione los archivos a cargar.');
		abrirDialogo();
	    return false;
	}	
}
