$(document).ready(function(){
	$.ajaxSetup({
		'beforeSend' : function(xhr) {
			try{
				xhr.overrideMimeType('text/html; charset=iso-8859-1');
			}catch(e){}
		}});
});



function chkArchivoAvancePagos(){

	var patron = /^(xlsx)$/;
	var doc = $('#doc').val();
	if(doc == null || doc == ""){
		$('#dialogo_1').html('Seleccione el archivo de avance de pagos');
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

function chkCampoBuscarAvisoDof(){
	var fechaPublicacion = $('#fechaPublicacion').val();
	
	if(fechaPublicacion == "" || fechaPublicacion == null){
		$('#dialogo_1').html('Capture la fecha de la publicación para realizar la consulta');
   		abrirDialogo();
 		return false;
	}
	
}


$(function() {	
	function validaCamposAvisoDof( ) {
			
		var leyenda = $('#leyenda').val();	
		var fechaPublicacion = $('#fechaPublicacion').val();	
		var idPgrAv = $('#idPgrAv').val();
		var idApoyo = $('#idApoyo').val();
		var ciclo = $('#ciclo').val();
		var ejercicio = $('#ejercicio').val();
		var idEstado = $('#idEstado').val();
		var volumenC = $('#volumenC').val();
		var importeC = $('#importeC').val();
				
		
		if(leyenda== null || leyenda==""){
			$('#dialogo_1').html('Debe capturar la leyenda del aviso');			
			abrirDialogo();
			return false;
		}
		
		if(fechaPublicacion== null || fechaPublicacion==""){
			$('#dialogo_1').html('Debe capturar la fecha de publicación del aviso');			
			abrirDialogo();
			return false;
		}		
		
		if(idApoyo == -1){
			$('#dialogo_1').html('Debe seleccionar el apoyo del aviso');			
			abrirDialogo();
			return false;
		}
		
		if(idPgrAv == -1){
			$('#dialogo_1').html('Debe seleccionar el programa');			
			abrirDialogo();
			return false;
		}
		
		if(ciclo == -1){
			$('#dialogo_1').html('Debe seleccionar el ciclo');			
			abrirDialogo();
			return false;
		}
		if(ejercicio == -1){
			$('#dialogo_1').html('Debe seleccionar el año');			
			abrirDialogo();
			return false;
		}
		if(idEstado == -1){
			$('#dialogo_1').html('Debe seleccionar el estado');			
			abrirDialogo();
			return false;
		}
		
		if(volumenC== null || volumenC==""){
			$('#dialogo_1').html('Debe capturar el volumen');			
			abrirDialogo();
			return false;
		}
		
		if(importeC== null || importeC==""){
			$('#dialogo_1').html('Debe capturar el importe');			
			abrirDialogo();
			return false;
		}
		
		return true;
	}
	
	function addUser() {      
		var valid = true;     
		var registrar = $('#registrar').val();
		var count = $('#count').val();
		var idAvisoDof = $('#idAvisoDof').val();
		var leyenda = $('#leyenda').val();
		var id = $('#id').val();
		
		var fechaPublicacion = $('#fechaPublicacion').val();
		var idApoyo = $('#idApoyo').val();
		var idPgrAv = $('#idPgrAv').val();
		var ciclo = $('#ciclo').val();
		var idCultivo = $('#idCultivo').val();
		var ejercicio = $('#ejercicio').val();
		var idEstado = $('#idEstado').val();
		var volumenC = $('#volumenC').val();
		var importeC = $('#importeC').val();
		var volumenRegional = $('#volumenRegional').val();
		var importeRegional = $('#importeRegional').val();
		if(!validaCamposAvisoDof()){
			return false;
		}
		var error = 1, programa="", estado ="", cultivo ="", apoyo = "", cicloAgricola = "", errorDetalleRepetido =1, errorAvisoYaCapturado = 1;
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "registrarAvisosDof",
			   data: "registrar="+registrar+
			   		"&av.id="+idAvisoDof		   			   
			   		+"&id="+id		   			   
			   		+"&leyenda="+leyenda		   			   
			   		+"&av.fechaPublicacion="+fechaPublicacion	   			   
			   		+"&avd.idApoyo="+idApoyo		   			   
			   		+"&avd.idPrograma="+idPgrAv		   			   
			   		+"&avd.ciclo="+ciclo		   			   
			   		+"&avd.idCultivo="+idCultivo		   			   
			   		+"&avd.anio="+ejercicio		   			   
			   		+"&avd.idEstado="+idEstado		   			   
			   		+"&avd.volumen="+volumenC		   			   
			   		+"&avd.importe="+importeC
			   		+"&avd.volumenRegional="+volumenRegional		   			   
			   		+"&avd.importeRegional="+importeRegional,
			   success: function(data){
				   var $response=$(data);
				   error = $response.filter('#error').text();	
				   errorDetalleRepetido = $response.filter('#errorDetalleRepetido').text();
				   errorAvisoYaCapturado = $response.filter('#errorAvisoYaCapturado').text();
				   programa = $response.filter('#programa').text();
				   estado = $response.filter('#estado').text();	
				   cultivo = $response.filter('#cultivo').text();
				   apoyo = $response.filter('#apoyo').text();
				   cicloAgricola = $response.filter('#cicloAgricola').text();
				   registrar = $response.filter('#registrar').text();
				   $('#registrar').val(registrar);			   
					$('#repuestaRegistroAvisosDof').html(data).ready(function () {
						$("#repuestaRegistroAvisosDof").fadeIn('slow');
					});					
			   }
			});//fin ajax
		
		if ( error == 0 && errorDetalleRepetido == 0 && errorAvisoYaCapturado == 0) {
			var tds = "";
			if(registrar == 0 || registrar == 1){
				tds += '<tr>';
				tds += '<td >'+programa+'</td>';
				tds += '<td >'+apoyo+'</td>';
				tds += '<td >'+cicloAgricola+'</td>';
				tds += '<td >'+cultivo+'</td>';			
				tds += '<td>'+estado+'</td>';
				tds += '<td>'+volumenC+'</td>';
				tds += '<td >'+importeC+'</td>';
				tds += '<td>'+volumenRegional+'</td>';
				tds += '<td >'+importeRegional+'</td>';
				tds += '<td ></td>';
				tds += '<td ></td>';
				tds += '</tr>';			
				$( "#tabAvisosDof tbody" )
				.append(tds);
			}else{
				$('#pgr'+count).html(programa);
				$('#apoy'+count).html(apoyo);
				$('#cicloA'+count).html(cicloAgricola);
				$('#cult'+count).html(cultivo);
				$('#est'+count).html(estado);
				$('#vol'+count).html(volumenC);
				$('#imp'+count).html(importeC);
				$('#volReg'+count).html(volumenRegional);
				$('#impReg'+count).html(importeRegional);
				$('#registrar').val(1);	
			}
			        
			$( "#dialog-form" ).dialog( "close" );      
		}  else{
			if(errorDetalleRepetido != 0){
				$('#dialogo_1').html('Ya se han capturado estos datos en otro registro, por favor verifique');			
				abrirDialogo();
			}else if( errorAvisoYaCapturado !=0){
				$('#dialogo_1').html('La fecha de publicacion ya se ha capturado, por favor verifique');
				abrirDialogo();
			}else if( error !=0){
				$('#dialogo_1').html('Ocurrio un error inesperado, favor de reportar al administrador');
				abrirDialogo();
			}
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
		}    
	});     
	
	$( "#dialog-form" ).find( "form" ).on( "submit", function( event ) {      
		event.preventDefault(); 		
		addUser();    });  
	
	$( "#add-reg")
		.button()
		.on( "click", function() {
			var registrar = $('#registrar').val();
			getAjaxTablaAvisosDof(0, registrar,-1);
			$('#registrar').val(registrar);	 
			$( "#dialog-form" ).dialog( "open" );
	}); 
	$(".editar").on( "click", function() {	
		var cadena = $(this).attr('id');
		
		var id = cadena.substring(0, cadena.indexOf("-")); 
		var count = cadena.substring((cadena.indexOf("-")+1), cadena.length);
		getAjaxTablaAvisosDof(id, 2, count);
		$('#registrar').val(2);	 
		$('#count').val(count);	 
		$( "#dialog-form" ).dialog( "open" ); 
		
	}); 
	function getAjaxTablaAvisosDof(id, registrar, count) {				
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "getTablaAvisosDof",
			   data: "registrar="+registrar
						+"&id="+id
			   			+"&count="+count,			   
			   success: function(data){
					$('#dialog-form').html(data).ready(function () {
						$("#dialog-form").fadeIn('slow');
					});
			   }
			});//fin ajax
	}
}); //end funcion principal	



function eliminarArchivo(){	
	var claveAviso =  $('#claveAviso').val();
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "eliminarArchivoAvance",
		   data: "claveAviso="+claveAviso,
		   success: function(data){
				$('#eliminarArchivoAvance').html(data).ready(function () {
					$("#eliminarArchivoAvance").fadeIn('slow');
				});
		   }
		});
}

function eliminarDetalleAviso(id, count){	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "eliminarDetalleAviso",
		   data: "id="+id,
		   success: function(data){
				$('#eliminarDetalleAviso'+count).html(data).ready(function () {
					$("#eliminarDetalleAviso"+count).fadeIn('slow');
				});
		   }
		});
}


function recuperaAnio(){	
	var ciclo =  $('#ciclo').val();
	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "recuperaAnio",
		   data: "avd.ciclo="+ciclo,
		   success: function(data){
				$('#ejercicio').html(data).ready(function () {
					$("#ejercicio").fadeIn('slow');
				});
		   }
		});
}


function chkCamposConsultaAvance(){
	var idProgAviso = $('#idProgAviso').val();
	var cicloAgricola = $('#cicloAgricola').val();
	var idCultivo = $('#idCultivo').val();
	var idEstado = $('#idEstado').val();
//	var fechaAvance = $('#fechaAvance').val();
	var fechaRegistro = $('#fechaRegistro').val();	
	var fechaRegistro1 = $('#fechaRegistro1').val();	
	if((idProgAviso == -1) &&
			(cicloAgricola == "" || cicloAgricola == null) &&
			(idCultivo == -1) &&
			(idEstado == -1) &&
//			(fechaAvance == "" || fechaAvance == null) &&
		(fechaRegistro == "" || fechaRegistro == null) 
		&&(fechaRegistro1 == "" || fechaRegistro1 == -1)){
		$('#dialogo_1').html('Debe seleccionar al menos un criterio de búsqueda');
   		abrirDialogo();
 		return false;
	}
	
	
	if(fechaRegistro != null && fechaRegistro != '' && fechaRegistro1 != -1){
		$('#dialogo_1').html('Seleccione solo una fecha de registro');
   		abrirDialogo();
 		return false;
	}
	
}


function ajustaCantidad(cantidad, tipo){
	var total = 0;
	if(tipo == 1){ //volumen
		if(cantidad!=null){
			var volumenC = $('#volumenC').val();	
			//Valida el volumen
			var valida = validarVolumenRegional(cantidad, 'volumenRegional', 1);
			if(valida==0){
				return false;
			}
			total = parseFloat(cantidad) + parseFloat(volumenC); 
			if(total < 0){
				$('#dialogo_1').html('El valor capturado es incorrecto, rebasa el volumen total');
				abrirDialogo();
				$('#volumenRegional').val(null);
				return false;
			}
			$('#volumenC').val(total.toFixed(3));
		}
		
	}else{//importe		
		if(cantidad!=null){
			var importeC = $('#importeC').val();	
			//Valida el volumen
			var valida = validarImporteRegional(cantidad, 'importeRegional', 1);
			if(valida==0){
				return false;
			}
			total = parseFloat(cantidad) + parseFloat(importeC); 
			if(total < 0){
				$('#dialogo_1').html('El valor capturado es incorrecto, rebasa el importe total');
				abrirDialogo();
				$('#importeRegional').val(null);
				return false;
			}
			$('#importeC').val(total.toFixed(2));
		}
	}
	
}

function validarVolumenRegional(volumen, id, msj){	
	if(volumen!=null && volumen !=''){		
		var patron =/^-\d|\d{1,7}((\.\d{1,3})|(\.))?$/;
		if (!volumen.match(patron)){	
			if(id!=0){
				$('#'+id).val(null);
			}
			if(msj==1){
				$('#dialogo_1').html('El valor capturado es incorrecto, se deben capturar decimales y aceptan hasta 7 digitos a la izquierda y 3 máximo a la derecha');
				abrirDialogo();
			}
			return 0;
		}else{
			return 1;
		}
	}
}


function validarImporteRegional(importe, id, msj){	
	if(importe!=null && importe !=''){		
		var patron =/^-\d|\d{1,10}((\.\d{1,2})|(\.))?$/;
		if (!importe.match(patron)){	
			if(id!=0){
				$('#'+id).val(null);
			}
			if(msj==1){
				$('#dialogo_1').html('El valor capturado es incorrecto, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 2 máximo a la derecha');
				abrirDialogo();
			}
			return 0;
		}else{
			return 1;
		}
	}
}
