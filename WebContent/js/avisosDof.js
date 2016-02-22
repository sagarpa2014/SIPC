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
	var claveAviso = $('#claveAviso').val();
	
	if(claveAviso == "" || claveAviso == null){
		$('#dialogo_1').html('Capture la clave del aviso para realizar la consulta');
   		abrirDialogo();
 		return false;
	}
	
}


$(function() {	
	function validaCamposAvisoDof( ) {
		
		var claveAviso = $('#claveAviso').val();	
		var leyenda = $('#leyenda').val();	
		var fechaPublicacion = $('#fechaPublicacion').val();	
		var idApoyo = $('#idApoyo').val();
		var idPgrAv = $('#idPgrAv').val();
		var ciclo = $('#ciclo').val();
		var ejercicio = $('#ejercicio').val();
		var idEstado = $('#idEstado').val();
		var volumenC = $('#volumenC').val();
		var importeC = $('#importeC').val();
		
		if(claveAviso== null || claveAviso==""){
			$('#dialogo_1').html('Debe capturar la clave del aviso');			
			abrirDialogo();
			return false;
		}				
		
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
			$('#dialogo_1').html('Debe seleccionar el ejercicio');			
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
		var claveAviso = $('#claveAviso').val();
		var registrar = $('#registrar').val();
		$("#claveAviso").removeAttr('disabled');
		var leyenda = $('#leyenda').val();
		var fechaPublicacion = $('#fechaPublicacion').val();
		var idApoyo = $('#idApoyo').val();
		var idPgrAv = $('#idPgrAv').val();
		var ciclo = $('#ciclo').val();
		var idCultivo = $('#idCultivo').val();
		var ejercicio = $('#ejercicio').val();
		var idEstado = $('#idEstado').val();
		var volumenC = $('#volumenC').val();
		var importeC = $('#importeC').val();
		if(!validaCamposAvisoDof()){
			return false;
		}
		var error = 1, programa="", estado ="", cultivo ="", errorDetalleRepetido =1;
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "registrarAvisosDof",
			   data: "registrar="+registrar+
			   		"&av.claveAviso="+claveAviso		   			   
			   		+"&av.leyenda="+leyenda		   			   
			   		+"&av.fechaPublicacion="+fechaPublicacion	   			   
			   		+"&av.idApoyo="+idApoyo		   			   
			   		+"&avd.programa="+idPgrAv		   			   
			   		+"&avd.ciclo="+ciclo		   			   
			   		+"&avd.idCultivo="+idCultivo		   			   
			   		+"&avd.ejercicio="+ejercicio		   			   
			   		+"&avd.idEstado="+idEstado		   			   
			   		+"&avd.volumen="+volumenC		   			   
			   		+"&avd.importe="+importeC,   			   		   			   	   
			   success: function(data){
				   var $response=$(data);
				   error = $response.filter('#error').text();	
				   errorDetalleRepetido = $response.filter('#errorDetalleRepetido').text();
				   programa = $response.filter('#programa').text();
				   estado = $response.filter('#estado').text();	
				   cultivo = $response.filter('#cultivo').text();

					$('#repuestaRegistroAvisosDof').html(data).ready(function () {
						$("#repuestaRegistroAvisosDof").fadeIn('slow');
					});					
			   }
			});//fin ajax
		if ( error == 0 && errorDetalleRepetido == 0) {
			var tds = "";
			tds += '<tr>';
			tds += '<td >'+programa+'</td>';
			tds += '<td >'+cultivo+'</td>';
			tds += '<td >'+ciclo+'</td>';
			tds += '<td>'+ejercicio+'</td>';
			tds += '<td>'+estado+'</td>';
			tds += '<td>'+volumenC+'</td>';
			tds += '<td >'+importeC+'</td>';
//			tds += '<td><a href="#"  id="4" class="botonVerDetalles" title=""></a></td>';
			tds += '</tr>';			
			$( "#tabAvisosDof tbody" )
			.append(tds);        
			$( "#dialog-form" ).dialog( "close" );      
		}  else{
			if(errorDetalleRepetido != 0){
				$('#dialogo_1').html('Ya se han capturado estos datos, por favor verifique');			
				abrirDialogo();
			}else if( error !=0){
				
			}
			
			//$( "#dialog-form" ).dialog( "close" );    
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
	
	
	$( "#add-reg" )
		.button()
		.on( "click", function() {
			getAjaxTablaAvisosDof(0, 0);
			$( "#dialog-form" ).dialog( "open" );
	}); 
	$(".editar").on( "click", function() {	
		var id = $(this).attr('id');
		getAjaxTablaAvisosDof(1, 1);
		$( "#dialog-form" ).dialog( "open" ); 
		
	}); 
	function getAjaxTablaAvisosDof(id, registrar) {				
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "getTablaAvisosDof",
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