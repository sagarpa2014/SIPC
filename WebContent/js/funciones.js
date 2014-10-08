$(document).ready(function(){
	$.ajaxSetup({
		'beforeSend' : function(xhr) {
			try{
				xhr.overrideMimeType('text/html; charset=iso-8859-1');
			}catch(e){}
		}});
});


function chkCamposLogin(){ 
	var nombreUsuario = $('#nombreUsuario').val();
	var password = $('#password').val();
	
	if(nombreUsuario == null || nombreUsuario == ""){
 		$('#dialogo_1').html('Capture el usuario');
 		abrirDialogo();
		return false;	
 	}else{
 		
 	}
	if(password == null || password == ""){
 		$('#dialogo_1').html('Capture la contraseña');
 		abrirDialogo();
		return false;	
 	}
	
}

function recuperaCompradoresByPrograma(){
	var idPrograma = $('#idPrograma').val();	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "recuperaCompradoresByPrograma",
		   data: "idPrograma="+idPrograma, 
		   success: function(data){
				$('#compradores').html(data).ready(function () {
					$("#compradores").css("display", "inline");
					
				});
		   }
		});//fin ajax
		
}

function cierraAviso(idAviso){
	$("#espera").fadeIn('slow', function() {
		  $("#img-espera").fadeIn('slow', function(){
			  	$("#pContenido").slideUp('slow', function() {
						$.ajax({
							   async: false,
							   type: "POST",
							   url: "cierraAviso",
							   data: "idAviso="+idAviso, 
							   success: function(data){
									$('#avisos').html(data).ready(function () {
										//$("#withborder").fadeIn('slow');
										hideLoading();
									});
							   }
							});//fin ajax
			  		});
			  		$("#pContenido").fadeIn("slow");
			  	});
		  });
	  
}

function chkCamposBusquedaPagos(){
	var idPrograma = $('#idPrograma').val();
	var idComprador = $('#idComprador').val();
	var fechaInicio = $('#fechaInicio').val();
	var fechaFin = $('#fechaFin').val();
	var estatusId = $('#estatusId').val();
	var noCarta = $('#noCarta').val();
	if((fechaInicio == null || fechaInicio =="") && 
	   (fechaFin == null || fechaFin =="") && 
	   (idPrograma == -1) && (idComprador == -1)&&
	   (estatusId == -1)&& (noCarta == null)){
		$('#dialogo_1').html('Debe capturar al menos un dato');
		abrirDialogo();
	 	return false;
	}
	
}



function chkCamposBusquedaAutorizacionPagos(){
	
	var idEspecialista = $('#idEspecialista').val();
	var idPrograma = $('#idPrograma').val();
	var fechaInicio = $('#fechaInicio').val();
	var fechaFin = $('#fechaFin').val();
	var estatusCve = $('#estatusCve').val();
	
	if((fechaInicio == null || fechaInicio =="") && 
	   (fechaFin == null || fechaFin =="") && 
	   (idPrograma == -1) &&
	   (estatusCve == -1) && (idEspecialista == -1)){
		$('#dialogo_1').html('Debe capturar al menos un dato');
		abrirDialogo();
	 	return false;
	}
	
}



function chkTodos(){ 
	if($(".check_todos").is(":checked")) {
		$(".ck:checkbox:not(:checked)").attr("checked", "checked");
	 }else{
		 $(".ck:checkbox:checked").removeAttr("checked");
	 }
}

function chkTodos1(){ 
	if($(".check_todos1").is(":checked")) {
		$(".ck1:checkbox:not(:checked)").attr("checked", "checked");
	 }else{
		 $(".ck1:checkbox:checked").removeAttr("checked");
	 }
}


function abrirDialogo() {
	$.fx.speeds._default = 300;
	$(function() {
	    $( "#dialogo_1" ).dialog({
	        autoOpen: true,
	        resizable:false,
	        show: "slide",
	        hide: "fade",
	        width:300,
	modal: true,
	buttons: {
		Ok: function() {
			$( this ).dialog( "close" );
		}   
	}
	    });

	    $( "#open_1" ).click(function() {
	        $( "#dialogo_1" ).dialog( "open" );
	        return false;
	    });

	$('.ui-widget-overlay').live('click', function() {
	     $('#dialogo_1').dialog( "close" );
	    });
	});	
}

function abrirDialogo2() {
	$.fx.speeds._default = 300;
	$(function() {
	    $( "#dialogo_2" ).dialog({
	        autoOpen: true,
	        resizable:false,
	        show: "slide",
	        hide: "fade",
	        width:300,
	modal: true,
	buttons: {
		Ok: function() {
			$( this ).dialog( "close" );
		}   
	}
	    });

	    $( "#open_2" ).click(function() {
	        $( "#dialogo_2" ).dialog( "open" );
	        return false;
	    });

	$('.ui-widget-overlay').live('click', function() {
	     $('#dialogo_2').dialog( "close" );
	    });
	});	
}


function showLoading() {
	  $("#espera").fadeIn('slow', function() {
		  $("#img-espera").fadeIn('slow');
	  });
}

function hideLoading() {
	  $("#img-espera").fadeOut("slow", function() {
		  $("#espera").fadeOut("slow");
	  });
}

function limpiarMsg(){
	$('#mensajesApp').html('');
}
function calculaTotalVolumen(v){
	var numCampos = $('#numCampos').val();
	var totalVolumen = null;
	var volumen = null;
	var i = 0;
	/*Calcula el valor total de volumen*/
	for (i=1;i<parseInt(numCampos)+1;i++){
		volumen = $('#v'+i).val();
		if(volumen!="" && volumen !=null){
				totalVolumen = (totalVolumen+parseFloat(volumen));	
		}
	}
	
	$("#totalesVolumen").val(totalVolumen.toFixed(3));	
	
}

function recuperaCartasByComprador(){
	console.log("Entra al metodo recuperaCartasByComprador...");
	var idPrograma = $('#idPrograma').val();	
	var idComprador = $('#idComprador').val();
	console.log("idPrograma: "+idPrograma);
	console.log("idComprador: "+idComprador);
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "recuperaCartasByComprador",
		   data: "idPrograma="+idPrograma+"&idComprador="+idComprador, 
		   success: function(data){
				$('#cartas').html(data).ready(function () {
					$("#cartas").css("display", "inline");
					
				});
		   }
		});//fin ajax
}

function chkCamposBusquedaBodega(){
	var claveBodega = $('#claveBodega').val();
	var nombreBodega = $('#nombreBodega').val();
	var idEstado = $('#idEstado').val();
	if( (nombreBodega == null || nombreBodega=="") 
		&& (claveBodega == null || claveBodega=="" ) && idEstado == -1){
		$('#dialogo_1').html('Debe seleccionar al menos un dato, para realizar la consulta');
		abrirDialogo();
		return false;
	}
}

function chkCamposInterpreteSQL(){
	var consultaSQL = $('#consultaSQL').val();
	if( (consultaSQL == null || consultaSQL=="")){
		$('#dialogo_1').html('Debe ingresar una consulta SQL válida, para realizar la consulta');
		abrirDialogo();
		return false;
	}
}

function cerrarErrorOficio(){
	$("#resultadoGeneracionOficio").fadeOut('3000', function(){
		$("#reporte1").fadeIn('3000');	
		$("#reporte2").fadeIn('3000');	
	});
}

function convierteAmayuscula(id){
	var tmp = $('#'+id).val().toUpperCase(); 
	$('#'+id).val(tmp);
}

function validarVolumen(volumen, id, msj){	
	if(volumen!=null && volumen !=''){		
		var patron =/^\d{1,7}((\.\d{1,3})|(\.))?$/;
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

function validarDigitos(volumen, id, msj){	
	if(volumen!=null && volumen !=''){		
		var patron =/^\d{1,4}$/;
		if (!volumen.match(patron)){	
			if(id!=0){
				$('#'+id).val(null);
			}
			if(msj==1){
				$('#dialogo_1').html('El valor capturado es incorrecto, se deben capturar solo numeros');
				abrirDialogo();
				return false;
			}
			return 0;
		}else{
			return 1;
		}
	}
}

function getTotalAlmacenamiento(volumen, id, msj){
	if(volumen!=null && volumen!=''){
		if(volumen>0){
			var v = validarVolumen(volumen, id, msj);
			if(v==0){
				return false;
			}
		} else {
			$('#'+id).val(0);
		}
	
		var totalVolumenAlmacenamiento = 0;	
		var capacidadBodega =  $('#capacidadBodega').val();
		var capacidadTechada =  $('#capacidadTechada').val();
		var capacidadSilos =  $('#capacidadSilos').val();
		var intemperie =  $('#intemperie').val();
		if(capacidadBodega!=null && capacidadBodega !=''){
			totalVolumenAlmacenamiento = (totalVolumenAlmacenamiento+parseFloat(capacidadBodega));	
		}	
		if(capacidadTechada!=null && capacidadTechada !=''){
			totalVolumenAlmacenamiento = (totalVolumenAlmacenamiento+parseFloat(capacidadTechada));	 
		}	
		if(capacidadSilos!=null && capacidadSilos !=''){
			totalVolumenAlmacenamiento = (totalVolumenAlmacenamiento+parseFloat(capacidadSilos));	
		}
		if(intemperie!=null && intemperie !=''){
			totalVolumenAlmacenamiento = (totalVolumenAlmacenamiento+parseFloat(intemperie));	
		}
		$('#totalAlmacenamiento').val(totalVolumenAlmacenamiento.toFixed(3));
	} else {
		$('#'+id).val(null);
		var totalVolumenAlmacenamiento = 0;	
		var capacidadBodega =  $('#capacidadBodega').val();
		var capacidadTechada =  $('#capacidadTechada').val();
		var capacidadSilos =  $('#capacidadSilos').val();
		var intemperie =  $('#intemperie').val();
		if(capacidadBodega!=null && capacidadBodega !=''){
			totalVolumenAlmacenamiento = (totalVolumenAlmacenamiento+parseFloat(capacidadBodega));	
		}	
		if(capacidadTechada!=null && capacidadTechada !=''){
			totalVolumenAlmacenamiento = (totalVolumenAlmacenamiento+parseFloat(capacidadTechada));	 
		}	
		if(capacidadSilos!=null && capacidadSilos !=''){
			totalVolumenAlmacenamiento = (totalVolumenAlmacenamiento+parseFloat(capacidadSilos));	
		}
		if(intemperie!=null && intemperie !=''){
			totalVolumenAlmacenamiento = (totalVolumenAlmacenamiento+parseFloat(intemperie));	
		}
		$('#totalAlmacenamiento').val(totalVolumenAlmacenamiento.toFixed(3));		
	}
}