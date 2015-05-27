$(document).ready(function(){
	$.ajaxSetup({
		'beforeSend' : function(xhr) {
			try{
				xhr.overrideMimeType('text/html; charset=iso-8859-1');
			}catch(e){}
		}});
});

/*Indican que se deben validar los apartados correspondientes y posteriormente se guardaran*/
function setValidarCamposByApartado(apartado){	
	console.log("apartado "+apartado);
	if(apartado == 6){
		$( "#validaApartado").val("6");//Validar predios del productor
	}else if(apartado == 7){
		$( "#validaApartado").val("7");//Entradas a la Bodega
	}else if(apartado == 9){
		$( "#validaApartado").val("9");//Factura de Venta del Grano
	}else if(apartado == 12){
		$( "#validaApartado").val("12");//Pago al Productor sin AXC
	}else if(apartado == 100){
		$( "#validaApartado").val("100");//Indica que se validaran todos los campos 
	}
	 
	$("#target" ).submit();
}

function recuperaConfRelacionD(){
	var idRelacion = $('#idRelacion').val();
	var tipoCaptura = $('#tipoCaptura').val();
	var idIniEsquemaRelacion = 0, idPrograma=0;
	if(tipoCaptura == 0){
		idIniEsquemaRelacion = $('#idIniEsquemaRelacion').val();
	}else{
		idPrograma = $('#idPrograma').val();
	}
	
	if(idRelacion!=-1){
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "recuperaConfRelacionD",
			   data: "idRelacion="+idRelacion
			   		+"&idIniEsquemaRelacion="+idIniEsquemaRelacion
			   		+"&idPrograma="+idPrograma
			   		+"&tipoCaptura="+tipoCaptura,
			   success: function(data){
					$('#recuperaConfRelacionD').html(data).ready(function () {
						$("#recuperaConfRelacionD").fadeIn('slow');
					});
			   }
			});
	}else{
		$("#recuperaConfRelacionD").fadeOut('slow');
	}
	
}

function chkCamposCapturaPersonalizacionRel(){
	var selectPGrupoEnc = new Array();
	var selectPGrupoEncAll = new Array();
	var selectPGrupoDetalle = new Array();
	var selectPGrupoDetalleHelp = new Array();
	var selectNombreGrupoDetalleHelp = new Array();
	var i=0, temp=0, j=0, k=0; 
	var registrar = $('#registrar').val();
	var idRelacion = $('#idRelacion').val();
	
	if(registrar==0){
		var numCiclos = $('#numCiclos').val();
		if(numCiclos == -1){
			$('#dialogo_1').html('Seleccione el número de ciclos a capturar');
			abrirDialogo();
		    return false;
		}else{
			var ciclo ="", anio ="",  tempCiclo ="", tempAnio ="";
			for (i=1;i<parseInt(numCiclos)+1;i++){
				ciclo = $('#ci'+i).val();
				anio = $('#a'+i).val();
				if(ciclo==-1 || anio==-1 ){
		   			$('#dialogo_1').html('Capture los valores del registro '+i+' de la captura de ciclos');
			   		abrirDialogo();
			 		return false;
		   		 }else{
		   			/*Valida que el ciclo seleccionado por el usuario no se encuentre repetido*/
			   			for (j=1;j<parseInt(numCiclos)+1;j++){
			   				if(i!=j){
			   					tempCiclo = $('#ci'+j).val();
			   					tempAnio = $('#a'+j).val();
			   					if(ciclo == tempCiclo && anio == tempAnio ){
			   						$('#dialogo_1').html('El ciclo del registro '+i+", se encuentra repetido, favor de verificar");
			   				   		abrirDialogo();
			   				 		return false;
			   					}
			   				}	   				
			   			} 
		   		 }
			}
		}	// end else ciclos
		//VALIDACION DE CULTIVOS
		var numCultivos = $('#numCultivos').val();
		if(numCultivos == -1){
			$('#dialogo_1').html('Seleccione el número de cultivos a capturar');
			abrirDialogo();
		    return false;
		}else{
			var cultivo ="",   tempCultivo ="";
			for (i=1;i<parseInt(numCultivos)+1;i++){
				cultivo = $('#cultivo'+i).val();
				
				if(cultivo==-1){
		   			$('#dialogo_1').html('Capture los valores del registro '+i+' de la captura de cultivos');
			   		abrirDialogo();
			 		return false;
		   		 }else{
		   			/*Valida que el ciclo seleccionado por el usuario no se encuentre repetido*/
			   			for (j=1;j<parseInt(numCultivos)+1;j++){
			   				if(i!=j){
			   					tempCultivo = $('#cultivo'+j).val();
			   					
			   					if(cultivo == tempCultivo){
			   						$('#dialogo_1').html('El cultivo del registro '+i+", se encuentra repetido, favor de verificar");
			   				   		abrirDialogo();
			   				 		return false;
			   					}
			   				}	   				
			   			} 
		   		 }
			}
		}	// end else cultivos

	}//End registrar = 0
	
	var msj ="";
	/*****************VALIDACION DE CAMPOS DEL ENCABEZADO****************/
	$('.classPEnc').each(function(){
		tmp=$(this).val();
		if(tmp != null && tmp!= "" ){			
			selectPGrupoEnc.push(tmp);			
		}
		selectPGrupoEncAll.push(tmp);
	});
	
	//Valida que el grupo 1 (NOMBRE DEL PARTICIPANTE) y 2 (CLAVE DE LA CARTA DE ADHESIÓN) sean capturados
	for(i=0; i<selectPGrupoEncAll.length; i++){
		var grEnc = $('#grEnc'+i).val();
		var grEncValor = $('#grEncValor'+i).val();
		if(grEnc==1){	
			if(idRelacion==1 || idRelacion==2 || idRelacion==4 || idRelacion==5 ){
				if(selectPGrupoEncAll[i] == null || selectPGrupoEncAll[i]== ""){
					msj = grEncValor;
					$('#dialogo_1').html('El campo del encabezado "'+msj+'"  es requerido, favor de capturar su posición');
					abrirDialogo();
				    return false;
				}
				
			}
		}
		if(grEnc==2){	
			if(idRelacion==1 || idRelacion==2 || idRelacion==3 ||  idRelacion==4 || idRelacion==5 ){
				if(selectPGrupoEncAll[i] == null || selectPGrupoEncAll[i]== ""){
					msj = grEncValor;
					$('#dialogo_1').html('El campo del encabezado "'+msj+'"  es requerido, favor de capturar su posición');
					abrirDialogo();
				    return false;
				}
			}
		}
		if(grEnc==3){	
			if(idRelacion==1 || idRelacion==2){
				if(selectPGrupoEncAll[i] == null || selectPGrupoEncAll[i]== ""){
					msj = grEncValor;
					$('#dialogo_1').html('El campo del encabezado "'+msj+'"  es requerido, favor de capturar su posición');
					abrirDialogo();
				    return false;
				}
			}
		}
		if(grEnc==4){	
			if(idRelacion==1 || idRelacion==2 || idRelacion==4){
				if(selectPGrupoEncAll[i] == null || selectPGrupoEncAll[i]== ""){
					msj = grEncValor;
					$('#dialogo_1').html('El campo del encabezado "'+msj+'"  es requerido, favor de capturar su posición');
					abrirDialogo();
				    return false;
				}
			}
		}
		if(grEnc==5){	
			if(idRelacion==1){
				if(selectPGrupoEncAll[i] == null || selectPGrupoEncAll[i]== ""){
					msj = grEncValor;
					$('#dialogo_1').html('El campo del encabezado "'+msj+'"  es requerido, favor de capturar su posición');
					abrirDialogo();
				    return false;
				}
			}
		}
		if(grEnc==14){	
			if(idRelacion==2 || idRelacion==3){
				if(selectPGrupoEncAll[i] == null || selectPGrupoEncAll[i]== ""){
					msj = grEncValor;
					$('#dialogo_1').html('El campo del encabezado "'+msj+'"  es requerido, favor de capturar su posición');
					abrirDialogo();
				    return false;
				}
			}
		}
		if(grEnc==17){	
			if(idRelacion==3){
				if(selectPGrupoEncAll[i] == null || selectPGrupoEncAll[i]== ""){
					msj = grEncValor;
					$('#dialogo_1').html('El campo del encabezado "'+msj+'"  es requerido, favor de capturar su posición');
					abrirDialogo();
				    return false;
				}
			}
		}
		if(grEnc==18){	
			if(idRelacion==3){
				if(selectPGrupoEncAll[i] == null || selectPGrupoEncAll[i]== ""){
					msj = grEncValor;
					$('#dialogo_1').html('El campo del encabezado "'+msj+'"  es requerido, favor de capturar su posición');
					abrirDialogo();
				    return false;
				}
			}
		}
	}
	
	if(selectPGrupoEnc.length==0){
		$('#dialogo_1').html('Debe capturar al menos un grupo del encabezado en la configuración de la relación');
		abrirDialogo();
	    return false;
	}	else if(selectPGrupoEnc.length==1){
		if(selectPGrupoEnc[0]!=1){
			$('#dialogo_1').html('El número de la posición en el grupo  del Encabezado, debe comenzar desde uno.');
			abrirDialogo();
		    return false;
			
		}
	}
		
	//Verifica
	/*Ordenando los elementos de la posicion del grupo detalle que capturo el usuario*/
	for(i=0; i<selectPGrupoEnc.length; i++){
		for(j=i; j<selectPGrupoEnc.length-1; j++){
			if(selectPGrupoEnc[i]>selectPGrupoEnc[j+1]){
				temp = selectPGrupoEnc[i];
				selectPGrupoEnc[i] = selectPGrupoEnc[j+1];
				selectPGrupoEnc[j+1] = temp;
			}
		}
	}
	/*Verificando que los elementos capturados empiecen con 1 y sean secuenciales*/
	for(i=0; i<selectPGrupoEnc.length-1; i++){
		if(i==0){
			if(selectPGrupoEnc[i]!=1){
				$('#dialogo_1').html('El número de la posición, debe comenzar desde uno.');
				abrirDialogo();
			    return false;
				
			}
		}
		temp = (parseInt(selectPGrupoEnc[i])+1);
		if(selectPGrupoEnc[i+1]!=temp){
			$('#dialogo_1').html('Los valores en la posición del grupo encabezado deben ser secuenciales, por favor verifique');
			abrirDialogo();
		    return false;
		}
	}//end Verificando que los elementos capturados empiecen con 1 y sean secuenciales*/

	/*****************VALIDACION DE GRUPO DEL DETALLE****************/
	$('.pgd').each(function(){
		tmp=$(this).val();
		if(tmp != null && tmp!= "" ){			
			selectPGrupoDetalle.push(tmp);			
		}	   
	});
	
	if(selectPGrupoDetalle.length==0){
		$('#dialogo_1').html('Debe capturar al menos un grupo en el detalle de la relación');
		abrirDialogo();
	    return false;
	}
	$("input[name$='idGrupos']" ).each(function(){
		tmp=$(this).val();
		if(tmp != null && tmp!= "" ){
			selectPGrupoDetalleHelp.push(tmp);			
		}	   
	});
	
	$("input[name$='nombreGrupos']" ).each(function(){  
		tmp=$(this).val();
		if(tmp != null && tmp!= "" ){
			selectNombreGrupoDetalleHelp.push(tmp);			
		}	   
	});
	
	/*****************VALIDACION DE CAMPO DEL DETALLE****************/
	var contCampos = 0;
	for(i=0; i< selectPGrupoDetalleHelp.length; i++){
		//Valida que la posicion de los campos en bloques de los grupos se reinice la numeracion
		var selectPCampoDetalle = new Array();
		$('.g'+selectPGrupoDetalleHelp[i]).each(function(){
			tmp=$(this).val();
			if(tmp != null && tmp!= "" ){
				selectPCampoDetalle.push(tmp);
				contCampos = contCampos+1;
			}
			
		});		
		
		//Ordenando elementos
		for(k=0; k<selectPCampoDetalle.length; k++){
			for(j=k; j<selectPCampoDetalle.length-1; j++){
				if(selectPCampoDetalle[k]>selectPCampoDetalle[j+1]){
					temp = selectPCampoDetalle[k];
					selectPCampoDetalle[k] = selectPCampoDetalle[j+1];
					selectPCampoDetalle[j+1] = temp;
				}
			}
		}
		
		
		//Si hay un solo elemento en el arreglo verificar que empiece en uno
		if(selectPCampoDetalle.length==1){
			if(selectPCampoDetalle[0]!=1){
				$('#dialogo_1').html('El número de la posición en el grupo '+selectNombreGrupoDetalleHelp[i]+', debe comenzar desde uno.');
				abrirDialogo();
			    return false;
				
			}
		}
		/*Verificando que los elementos capturados empiecen con 1 y sean secuenciales*/
		for(k=0; k<selectPCampoDetalle.length-1; k++){
			if(k==0){
				if(selectPCampoDetalle[k]!=1){
					$('#dialogo_1').html('El número de la posición en el grupo '+selectNombreGrupoDetalleHelp[i]+', debe comenzar desde uno.');
					abrirDialogo();
				    return false;
					
				}
			}
			temp = (parseInt(selectPCampoDetalle[k])+1);
			if(selectPCampoDetalle[k+1]!=temp){
				$('#dialogo_1').html('Los valores de las posiciones del grupo "'+ selectNombreGrupoDetalleHelp[i]+'" deben ser secuenciales y comenzar desde uno, por favor verifique');
				abrirDialogo();
			    return false;
			}
		}		
	}//End verifica que los campos por grupo comienzen con 1 y sean secuenciales
	
	if(contCampos==0){
		$('#dialogo_1').html('Debe capturar por lo menos un dato en la columna de campos');
		abrirDialogo();
	    return false;
	}
		
	/*Ordenando los elementos de la posicion del grupo detalle que capturo el usuario*/
	for(i=0; i<selectPGrupoDetalle.length; i++){
		for(j=i; j<selectPGrupoDetalle.length-1; j++){
			if(selectPGrupoDetalle[i]>selectPGrupoDetalle[j+1]){
				temp = selectPGrupoDetalle[i];
				selectPGrupoDetalle[i] = selectPGrupoDetalle[j+1];
				selectPGrupoDetalle[j+1] = temp;
			}
		}
	}
	/*Verificando que los elementos capturados empiecen con 1 y sean secuenciales*/
	for(i=0; i<selectPGrupoDetalle.length-1; i++){
		if(i==0){
			if(selectPGrupoDetalle[i]!=1){
				$('#dialogo_1').html('El número de la posición, debe comenzar desde uno.');
				abrirDialogo();
			    return false;
				
			}
		}
		temp = (parseInt(selectPGrupoDetalle[i])+1);
		if(selectPGrupoDetalle[i+1]!=temp){
			$('#dialogo_1').html('Los valores en la posición del grupo detalle deben ser secuenciales, por favor verifique');
			abrirDialogo();
		    return false;
		}
	}//end Verificando que los elementos capturados empiecen con 1 y sean secuenciales*/
	
}//chkCamposCapturaPersonalizacionRel

function ordenarElementosArray(){
	var elementosArray = new Array();
	var k=0, j=0;
	for(k=0; k<elementosArray.length; k++){
		for(j=k; j<elementosArray.length-1; j++){
			if(elementosArray[k]>elementosArray[j+1]){
				temp = elementosArray[k];
				elementosArray[k] = elementosArray[j+1];
				elementosArray[j+1] = temp;
			}
		}
	}
	return elementosArray;
}

function chkCamposBusquedaPersonalizacionRelaciones(){
	var idRelacion = $('#idRelacion').val();
	//var idCultivo = $('#idCultivo').val();
	var idCiclo = $('#idCiclo').val();
	var idEjercicio = $('#idEjercicio').val();
	
	if(idRelacion== -1  && idCiclo == -1 && idEjercicio ==-1){
		$('#dialogo_1').html('Debe seleccionar al menos un dato para realizar la consulta');
		abrirDialogo();
		return false;
	}
	
}

function consigueNumCiclos(numCiclo){
	var registrar = $('#registrar').val();
	var idIniEsquemaRelacion = $('#idIniEsquemaRelacion').val();
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "consigueNumCiclos",
		   data: "numCiclos="+numCiclo
		   		+"&registrar="+registrar
		   		+"&idIniEsquemaRelacion="+idIniEsquemaRelacion,
		   success: function(data){
				$('#consigueNumCiclos').html(data).ready(function () {
					$("#consigueNumCiclos").fadeIn('slow');
					
				});
		   }
		});//fin ajax
}

function consigueNumCultivos(numCultivos){
	var registrar = $('#registrar').val();
	var idIniEsquemaRelacion = $('#idIniEsquemaRelacion').val();
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "consigueNumCultivos",
		   data: "numCultivos="+numCultivos
		   		+"&registrar="+registrar
		   		+"&idIniEsquemaRelacion="+idIniEsquemaRelacion,
		   success: function(data){
				$('#consigueNumCultivos').html(data).ready(function () {
					$("#consigueNumCultivos").fadeIn('slow');
					
				});
		   }
		});//fin ajax
}

function validaGrupoEnc(valor, count){
	var patron =/^\d{1}$/;
	if(valor!=null && valor!=""){
		if(!valor.match(patron)){
			$('#dialogo_1').html('La posición del grupo encabezado que capturó es incorrecto, se debe capturar 1 digito ');			
			abrirDialogo();
			$('#posicionEnc'+count).val(null);
			return false;
		}
	}
}

function setValHiddenGrupo(valor, count, idGrupo, grupo){
	var patron =/^\d{1}$/;
	if(valor!=null && valor!=""){
		if(!valor.match(patron)){
			$('#dialogo_1').html('La posición del grupo que capturó es incorrecto, se debe capturar 1 digito ');			
			abrirDialogo();
			$('#posicionGrupoDetalle'+count).val(null);
			return false;
		}
		$('#idGrupo'+count).val(idGrupo);
		$('#nombreGrupos'+count).val(grupo);
	}else{
		$('.g'+idGrupo).val(null);
		//$("#visible"+idGrupo).removeAttr("checked");
		$('#idGrupo'+count).val(null);
		$('#nombreGrupos'+count).val(null); 
	}
	
}

function setValHiddenCampo(valor, count, idGrupo, grupo){
	var selectIdGrupoEnCampo = new Array();
	var patron =/^\d{1}$/;
	var i=0, band="false";
	if(valor!=null && valor!=""){
		if(!valor.match(patron)){
			$('#dialogo_1').html('La posición del campo que capturó es incorrecto, se debe capturar 1 digito ');			
			abrirDialogo();
			$('#posicionDet'+count).val(null);
			return false;
		}
		$('#idGrupoEnCampo'+count).val(idGrupo);
		$("input[name$='idGrupos']" ).each(function(){
			tmp=$(this).val();
			if(tmp != null && tmp!= "" ){
				selectIdGrupoEnCampo.push(tmp);			
			}	   
		});		
		//Verifica que la posicion en grupo haya sido capturado, si se ha capturado posicion de campo
		for(i=0; i<selectIdGrupoEnCampo.length; i++){
			if(idGrupo == selectIdGrupoEnCampo[i]){
				band="true";
				break;
			}
		}	
		if(band=="false"){
			$('#posicionDet'+count).val(null);
			$('#dialogo_1').html('Debe capturar la posición del grupo "'+grupo+"' si requiere la configuración del campo");
			abrirDialogo();
			return false;
		}	
	}else{
		$('#idGrupoEnCampo'+count).val(null); 
	}

}//end setValHiddenCampo

function poneValoresDefault(){	
	var sizeArrayEnc  = $('#sizeArrayEnc').val();
	var contadorGrupoDetalle = new Array();
	var i=0, j=0;
	for(i=0; i<sizeArrayEnc; i++){
		$('#posicionEnc'+(i+1)).val(i+1);
	}
		
	i=0;
	$("input[name$='count']" ).each(function(){
		var count = $(this).val();
		$('#posicionGrupoDetalle'+(count)).val(i+1);
		contadorGrupoDetalle.push(count);
		i++;
	});
	i=0;
	$("input[name$='idGrupoTotalName']" ).each(function(){
		var idGrupoTotalName = $(this).val();
		$('#idGrupo'+contadorGrupoDetalle[i]).val(idGrupoTotalName);  
		j=1;
		$('.g'+idGrupoTotalName).each(function(){
			$(this).val(j);
			j++;
		});			
		i++;		
	});
	
	$(".ck:checkbox:not(:checked)").attr("checked", "checked");
	
	return false;
}

function quitarValoresDefault(){
	 var i=0;
	 var sizeArrayEnc  = $('#sizeArrayEnc').val();
	 var sizeArrayCampoDet  = $('#sizeArrayCampoDet').val();
	 $(".ck:checkbox:checked").removeAttr("checked");
	for(i=0; i<sizeArrayEnc; i++){
		$('#posicionEnc'+(i+1)).val(null);
	}
	
	for(i=0; i<sizeArrayCampoDet; i++){
		$('#posicionGrupoDetalle'+(i+1)).val(null);
		$('#posicionDet'+(i+1)).val(null);
		$('#idGrupo'+(i+1)).val(null);
	}
	
}

function exportarRelacion(opcion, claveBodega, idCompPer, idRelacion,
		idIniEsquemaRelacion, idPerRel, cultivoRelacion, idPgrCiclo,
		razonSocialAlmacen, estadoBodega, nombreBarco, lugarDestino) {
	var folioCartaAdhesion = "";
	var idRelacionTemp="";
	if(opcion == 1){
		idRelacionTemp = idRelacion;
	}else{
		idRelacionTemp = $('#idRelacion').val();
		if(idRelacion == -1){
			$('#dialogo_1').html('Seleccione la relación');
			abrirDialogo();
			return false;
		}
	
		folioCartaAdhesion = $('#folioCartaAdhesion').val();
		if(folioCartaAdhesion == null || folioCartaAdhesion==""){
			$('#dialogo_1').html('Seleccione la Carta de Adhesión');
			abrirDialogo();
			return false;
		}
	}

	 $("#espera").fadeIn('slow', function() {
		  $("#img-espera").fadeIn('slow', function(){
			  	$("#pContenido").slideUp('slow', function() {
					  $.ajax({
						   async: false,
						   type: "POST",
						   url: "exportarRelacion",
						   data: "idRelacion="+idRelacionTemp
					   		+"&folioCartaAdhesion="+folioCartaAdhesion
					   		+"&opcion="+opcion
					   		+"&claveBodega="+claveBodega
					   		+"&idCompPer="+idCompPer
					   		+"&idIniEsquemaRelacion="+idIniEsquemaRelacion
					   		+"&idPerRel="+idPerRel
					   		+"&cultivoRelacion="+cultivoRelacion
					   		+"&idPgrCiclo="+idPgrCiclo
					   		+"&razonSocialAlmacen="+razonSocialAlmacen
					   		+"&estadoBodega="+estadoBodega
					   		+"&nombreBarco="+nombreBarco
					   		+"&lugarDestino="+lugarDestino,
						   success: function(data){
								$('#pContenido').html(data).ready(function () {
									hideLoading();									
								});
						   }
						});//fin ajax	
					  $("#pContenido").fadeIn("slow");
			  	 });
		  });
	  });
}

function recuperaDatosProductor(tipoOpcion){
	var claveBodega = $('#claveBodega').val();	
	var estadoBodega = $('#estadoBodega').val();	
	var registrar = $('#registrar').val();	
	var folioProductor = $('#folioProductor').val();
		
	if(tipoOpcion == 1 ){//Seleccionan por primera vez el estado de acopio
		if((claveBodega== null || claveBodega=="")
			&& (folioProductor == null || folioProductor =="")){
			return false;
		}
	}
	
	if(folioProductor == null || folioProductor == ""){
		$('#dialogo_1').html('Capture el Folio del Productor ');
		abrirDialogo();
		return false;
	}
	
	if(folioProductor.indexOf("-") != -1){
		folioProductor = folioProductor.substring(0, folioProductor.lastIndexOf("-"));   
	}
		
	patron = /^([0-9]{1,15})$/;
	if (!folioProductor.match(patron)){
		$('#dialogo_1').html('El Folio del Productor es inválido, solo se aceptan valores numéricos');
		abrirDialogo();
		return false;
	}
	/**Estado de Acopio **/
	if(estadoBodega == -1){
		$('#dialogo_1').html('Seleccione el estado de origen de acopio');
		abrirDialogo();
		return false;
	}
	
	if(claveBodega== null || claveBodega==""){
		$('#dialogo_1').html('Capture primero los datos de la bodega');
		$('#folioProductor').val(null);
		abrirDialogo();
		return false;
	}	
	

	if(registrar == 2){
		if($('#folioProductorAnt').length){
			var folioProductorAnt = $.trim($('#folioProductorAnt'+count).val());
			//Si el folio capturado es igual al de un inicio, no valida la factura
			folioProductor = $.trim(folioProductor);
			if(folioProductor == folioProductorAnt){
				return false; //No valida folio y regresa a la pantalla
			}
			
		}
	}		
	//Recupera los datos del productor
	if((folioProductor == "" || folioProductor == null)){
		error = 1;
		return false;
	}
	

	$.ajax({
		async: false,
		type: "POST",
		url: "recuperaDatosProductor", 
		data: "folioProductor="+folioProductor+
			"&validaParBodProd="+1+
			"&claveBodega="+claveBodega+
			"&estadoBodega="+estadoBodega,
		success: function(data){
			var $response=$(data);
			var errorProductores = $response.filter('#errorProductoresR').text();
			var nombreProductor = $response.filter('#nombreProductor').text();
			var tipoPersona = $response.filter('#tipoPersona').text();
			var errorNoExistenPredios = $response.filter('#errorNoExistenPrediosR').text();
			$('#nomProductor').val(nombreProductor);
			var paterno = $response.filter('#paterno').text();
			$('#pat').val(paterno);
			var materno = $response.filter('#materno').text();
			$('#mat').val(materno);
			var rfc = $response.filter('#rfc').text();
			$('#rfcR').val(rfc);
			var curp = $response.filter('#curp').text();
			$('#curpR').val(curp);
			if(tipoPersona == 'F'){
				$('#numProdBen').val(1);
				$("#numProdBen").attr('disabled','disabled');
			}else{
				$('#numProdBen').val(null);
				$("#numProdBen").removeAttr('disabled');
			}			
			if(errorNoExistenPredios != 0){
				$('#errorNoExistenPredios').val(1);
			}else{
				$('#errorNoExistenPredios').val(0);
			}
			if(errorProductores == 1 || errorProductores == 2){
				$('#errorProductores').val(1);
			}else{
				$('#errorProductores').val(0);
				cambiaRfcFacturas();
			}
			$("#foliosPredios").html(data).ready(function () {
			});
			$("#errorProductores").fadeOut("slow");
		}
	});//fin ajax
	
	var idCompPer = $('#idCompPer').val();
	if(idCompPer ==0){
		validaClaveBodega();
	}
	
	
}

function validaClaveBodega(){
	var idPerRel = $('#idPerRel').val();
	var idRelacion = $('#idRelacion').val();
	var cultivoRelacion = $('#cultivo').val();
	var idPgrCiclo = $('#idPgrCiclo').val();
	var estadoBodega =  -1;
	var razonSocialAlmacen = -1;
	var tipoFormatoTerrestre  = -1;
	var tipoFormatoMaritimo  = -1;
	var nombreBarco  = -1;
	var lugarDestino  = -1;
	if($('#estadoBodega').length){
		estadoBodega = $('#estadoBodega').val();
	}	
	var claveBodega = $.trim($('#claveBodega').val()).toUpperCase();
	$('#claveBodega').val(claveBodega);		
	//Valida que la bodega exista en la base de datos
	if(idRelacion == 1){
		if((claveBodega == "" || claveBodega == null) || cultivoRelacion == -1 || idPgrCiclo == -1 ||  estadoBodega == -1 ){
			return false;
		}
	}else if(idRelacion == 4){
		razonSocialAlmacen = $('#razonSocialAlmacen').val();
		if((claveBodega == "" || claveBodega == null) || cultivoRelacion == -1 || idPgrCiclo == -1 || razonSocialAlmacen == -1 ){
			return false;
		}
	}else if(idRelacion == 2){
		tipoFormatoTerrestre = $('input:radio[name=tipoFormatoTerrestre]:checked').val();
		if((claveBodega == "" || claveBodega == null) || cultivoRelacion == -1 || idPgrCiclo == -1 ||  estadoBodega == -1 ){
			return false;
		}
		
	}else if(idRelacion == 3){
		tipoFormatoMaritimo = $('input:radio[name=tipoFormatoMaritimo]:checked').val();
		nombreBarco = $.trim($('#nombreBarco').val()).toUpperCase(); 
		$('#nombreBarco').val(nombreBarco);
		lugarDestino = $.trim($('#lugarDestino').val()).toUpperCase();
		$('#lugarDestino').val(lugarDestino);
		
		if((claveBodega == "" || claveBodega == null) || cultivoRelacion == -1 || idPgrCiclo == -1 || (nombreBarco == "" || nombreBarco == null) || (lugarDestino == "" || lugarDestino == null) ){
			return false;
		}
	}
	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "validaClaveBodega", 
		   data: "claveBodega="+claveBodega+
		   "&idPerRel="+idPerRel+
		   "&idRelacion="+idRelacion+
		   "&cultivoRelacion="+cultivoRelacion+
		   "&idPgrCiclo="+idPgrCiclo+
		   "&estadoBodega="+estadoBodega+
		   "&nombreBarco="+nombreBarco+
		   "&lugarDestino="+lugarDestino+
		   "&tipoFormatoTerrestre="+tipoFormatoTerrestre+
		   "&tipoFormatoMaritimo="+tipoFormatoMaritimo+
		   "&razonSocialAlmacen="+razonSocialAlmacen,	   
		   success: function(data){
			   var $response=$(data);
			   var errorClaveBodega = $response.filter('#errorCB').text();
			   var errorClaveBodegaExiste = $response.filter('#errorCBExiste').text();
			   console.log("errorClaveBodega "+errorClaveBodega);
			   console.log("errorClaveBodegaExiste "+errorClaveBodegaExiste);
	           var domicilio = $response.filter('#domicilio').text();
	           $('#domicilioBodega').val(domicilio);           
			   if(errorClaveBodega == 1){
				   $('#errorClaveBodega').val(1);
			   }else{
				   $('#errorClaveBodega').val(0);
			   }
			   if(errorClaveBodegaExiste == 1){
				   console.log("errorClaveBodegaExiste");
				   $('#errorClaveBodegaExiste').val(1);
			   }else{
				   $('#errorClaveBodegaExiste').val(0);
			   }
			   $("#validaClaveBodega").html(data).ready(function () {
				});
			   $("#errorClaveBodega").fadeOut("slow");
		   }
		});//fin ajax	
}

function chkCamposCapturaRelaciones(){
	
	var idRelacion = $('#idRelacion').val();
	$("#idRelacion").removeAttr('disabled');
	var i = 0;	
	/**Estado de Acopio **/
	if($('#estadoBodega').length){
		if($('#estadoBodega').val() == -1){
			$('#dialogo_1').html('Seleccione el estado de origen de acopio');
			abrirDialogo();
			return false;
		}
		$("#estadoBodega").removeAttr('disabled');
	}
	/**Clabe de la bodega**/
	if($('#claveBodega').length){
		if($('#claveBodega').val() == "" || $('#claveBodega').val() == null){
			$('#dialogo_1').html('Capture la clave de la bodega');			
			abrirDialogo();
			return false;
		}
		$("#claveBodega").removeAttr('disabled');
	}
	if( $('#errorClaveBodega').val() ==1 || $('#errorClaveBodegaExiste').val() ==1){
		$('#dialogo_1').html('Capture correctamente la clave de la bodega');
	   	abrirDialogo();
		error = 1;
		return false;
	}
	/**Producto a Apoyar**/
	if($('#cultivo').length){
		if($('#cultivo').val() == -1){
			$('#dialogo_1').html('Seleccione el cultivo');
			abrirDialogo();
			return false;
		}
		$("#cultivo").removeAttr('disabled');
	}
		
	/**Ciclo Agricola**/	
	if($('#idPgrCiclo').length){
		if($('#idPgrCiclo').val() == -1){
			$('#dialogo_1').html('Seleccione el Ciclo Agricola');
			abrirDialogo();
			return false;
		}
		$("#idPgrCiclo").removeAttr('disabled');
	}
		
	if(idRelacion == 1){
		var validaApartado = parseInt($('#validaApartado').val());
		var numBolTicket = 0, numFacVenta = 0, numPagosSinAXC = 0;
		/**Datos del productor**/
		if($('#folioProductor').length){
			if($('#folioProductor').val()  == "" || $('#folioProductor').val() == null){
				$('#dialogo_1').html('Capture el folio del productor');
				abrirDialogo();
				return false;
			}
		}
		if( $('#errorProductores').val() ==1){
			$('#dialogo_1').html('El folio del productor no se encuentra registrado o ya ha sido capturado para la bodega, favor de verificar');
			abrirDialogo();
			return false;
		}
		$('#rfc').removeAttr('disabled');
		if($('#rfcR').length){
			/*rfcProductor = $('#rfcR').val();
			patron = /^([A-Z, a-z]{3,4})([0-9]{6})([A-Z,a-z,0-9]{3})$/;
			rfcProductor.toUpperCase();
			if (!rfcProductor.match(patron)){
				$('#dialogo_1').html('El RFC del Productor es inválido, capture correctamente el RFC con homoclave, ejemplo (ABCD123456R12)');
				//$('#rfcR').val(null);
				abrirDialogo();
				return false;
			}*/
		}	
	
		if($('#numProdBen').length){
			var numProdBen = $('#numProdBen').val();  
			if($('#numProdBen').val()  == "" || $('#numProdBen').val() == null){
				$('#dialogo_1').html('Capture el número de productores beneficiados');
				abrirDialogo();
				return false;
			}else{
				var patron =/^\d$/;
				if (!numProdBen.match(patron)){
					$('#numProdBen').val(null);
				}
			}	
			$("#numProdBen").removeAttr('disabled');
		}		
		if(validaApartado == 6 || validaApartado == 100){//Validar Predios del productor
			if( $('#errorNoExistenPredios').val() ==1){
				$('#dialogo_1').html('No existen predios asociados al productor/estado capturados, favor de verificar');
				abrirDialogo();
				return false;
			}			
			var numPredios = $('#numPredios').val();
			var contadorSuperficie = 0;
			for (i=1; i<parseInt(numPredios)+1;i++){
				if(($('#superficiePredio'+i).val()!=null && $('#superficiePredio'+i).val()!= "" )){
					contadorSuperficie = contadorSuperficie +1;
					break;
				}			
			}
			if(contadorSuperficie == 0){
				$('#dialogo_1').html('Debe incluir por lo menos un predio en el apartado "Predios del Productor" capturando la superficie a apoyar');
				abrirDialogo();
				return false;
			}
			
			for(i=1;i<=numPredios;i++){
				if ($('#errorValidacion'+i).length){
					console.log("$('#errorValidacion'+i) "+$('#errorValidacion'+i).val());
					if($('#errorValidacion'+i).val() !=0){
						$('#dialogo_1').html('Existen errores en la captura de predios, por favor verifique');
						abrirDialogo();
						return false;
					}
				}
			}
			
		}// end validaApartado == 6 || validaApartado == 100 
		
		if(validaApartado == 7 || validaApartado == 100){//Validar Entradas a la bodega
			/**Validando Entrada(s) a la Bodega**/
			if($('#numBolTicket').length){
				numBolTicket = $('#numBolTicket').val();				
				if(numBolTicket == 0 ){
					$('#dialogo_1').html('Debe capturar la información de "Entrada(s) a la Bodega"');
					abrirDialogo();
					return false;
				}else{			
					for (i=1; i<parseInt(numBolTicket)+1;i++){
						if( ($('#capBolTicket'+i).val()==null || $('#capBolTicket'+i).val()== "" ) 
							|| ($('#capFechaEntrada'+i).val()==null || $('#capFechaEntrada'+i).val()== "")
							|| ($('#capVolBolTicket'+i).val()==null || $('#capVolBolTicket'+i).val()== "")){
							$('#dialogo_1').html('Capture los datos del apartado "Entrada(s) a la Bodega" en el registro ['+i+']');
							abrirDialogo();
							return false;
						}else{
							var patron = /^([A-Z, a-z,0-9,-]{1,10})$/;
							if(!$('#capBolTicket'+i).val().match(patron)){
								$('#dialogo_1').html('El valor de la boleta en el registro ['+i+'] es inválido, solo se aceptan letras, números y guión ejemplo (AB-123456)');
								abrirDialogo();
								return false;
							}
						}
						
					}		
					for(i=1;i<=numBolTicket;i++){
						if($('#errorBoletaRepetida'+i).val() !=0){
							$('#dialogo_1').html('Existen errores en la captura de boletas, por favor verifique');
							abrirDialogo();
							return false;
						}else if($('#errorBodegaTicket'+i).length){
							if($('#errorBodegaTicket'+i).val() !=0){
								$('#dialogo_1').html('Existen errores en la captura de boletas, por favor verifique');
								abrirDialogo();
								return false;
							}
						}		
					}
				}		
			}/** End Validando Entrada(s) a la Bodega**/	
		}// end validaApartado == 7 || validaApartado == 100*/
		
		if(validaApartado == 9 || validaApartado == 100){//Validar Factura de Venta del Grano
			/**Factura de Venta del Grano**/
			if($('#numFacVenta').length){
				numFacVenta = $('#numFacVenta').val();
				if(numFacVenta ==0){
					$('#dialogo_1').html('Debe capturar las "Facturas de Venta de Grano"');
					abrirDialogo();
					return false;
				}else{			
					for (i=1; i<parseInt(numFacVenta)+1;i++){
						if($('#capFolioFac'+i).length){
							if($('#capFolioFac'+i).val()==null || $('#capFolioFac'+i).val()== "" ){
								$('#dialogo_1').html('Capture los datos del apartado "Factura de Venta del Grano" en el registro ['+i+']');
								abrirDialogo();
								return false;
							} 
						}
						if($('#capFechaEmisionFac'+i).length){
							if($('#capFechaEmisionFac'+i).val()==null || $('#capFechaEmisionFac'+i).val()== ""){
								$('#dialogo_1').html('Capture los datos del apartado "Factura de Venta del Grano" en el registro ['+i+']');
								abrirDialogo();
								return false;
							}
						}
						if($('#capVolSolFac'+i).length){
							if($('#capVolSolFac'+i).val()==null || $('#capVolSolFac'+i).val()== ""){
								$('#dialogo_1').html('Capture los datos del apartado "Factura de Venta del Grano" en el registro ['+i+']');
								abrirDialogo();
								return false;
							}
						}
						if($('#capVolTotalFac'+i).length){
							if($('#capVolTotalFac'+i).val()==null || $('#capVolTotalFac'+i).val()== ""){
								$('#dialogo_1').html('Capture los datos del apartado "Factura de Venta del Grano" en el registro ['+i+']');
								abrirDialogo();
								return false;
							}
						}
						
						if($('#capImpSolFac'+i).length){
							if(($('#capImpSolFac'+i).val()==null || $('#capImpSolFac'+i).val()== "")){
								$('#dialogo_1').html('Capture los datos del apartado "Factura de Venta del Grano" en el registro ['+i+']');
								abrirDialogo();
								return false;
							}
						}	
					}
					/*if( $('#errorFacturaByProductor').val() ==1){
						$('#dialogo_1').html('Existe un error en la captura de facturas, por favor de verifique');
						abrirDialogo();
						return false;
					}*/
					
				}	
				for(i=1;i<=numFacVenta;i++){
					if( $('#errorFacturasRepetidos'+i).val() !=0){
						$('#dialogo_1').html('Existen errores en la captura de facturas, por favor verifique');
						abrirDialogo();
						return false;
					}else if ($('#errorFacturaByProductor'+i).length){
						console.log("$('#errorFacturaByProductor'+i) "+$('#errorFacturaByProductor'+i).val());
						if($('#errorFacturaByProductor'+i).val() !=0){
							$('#dialogo_1').html('Existen errores en la captura de facturas, por favor verifique');
							abrirDialogo();
							return false;
						}
					}
							
				}
			}/**End Factura de Venta del Grano**/
		}//End (validaApartado == 9 || validaApartado == 100)
		
		if(validaApartado == 12 || validaApartado == 100){ //Pagos al Productor sin AXC
			//Pago al Productor sin AXC	
			if($('#numPagosSinAXC').length){
				numPagosSinAXC = $('#numPagosSinAXC').val();
				if(numPagosSinAXC ==0){
					$('#dialogo_1').html('Debe capturar los "Pagos al Productor sin AXC"');
					abrirDialogo();
					return false;
				}else{	
					for (i=1; i<parseInt(numPagosSinAXC)+1;i++){
						if( ($('#capIdTipoDocPago'+i).val()==null || $('#capIdTipoDocPago'+i).val()== "" ) 
								|| ($('#capFolioDocPago'+i).val()==null || $('#capFolioDocPago'+i).val()== "")
								|| ($('#capFechaDocPago'+i).val()==null || $('#capFechaDocPago'+i).val()== "")){
							$('#dialogo_1').html('Capture los datos del apartado "Pago al Productor sin AXC" en el registro ['+i+']');
							abrirDialogo();
							return false;
						}
						var capIdTipoDocPago =  $('#capIdTipoDocPago'+i).val();
						if(capIdTipoDocPago==-1){
							$('#dialogo_1').html('Seleccione el Tipo de Documento en el registro ['+i+'] del apartado de "Pago al Productor sin AXC"');
							abrirDialogo();
						   	return false;
						}else{
							if(capIdTipoDocPago != 3){
								var capBancoIdSin = $('#capBancoIdSin'+i).val();
								if(capBancoIdSin == -1){
									$('#dialogo_1').html('Seleccione el banco en el registro ['+i+'] del apartado de "Pago al Productor sin AXC"');
									abrirDialogo();
								   	return false;
								}
							}
						}
						
						for (i=1; i<parseInt(numPagosSinAXC)+1;i++){
							if($("#capBancoIdSin"+i).length){
								$("#capBancoIdSin"+i).removeAttr("disabled");
							}
						}
						
					}
				}
				
			}// End Pago al Productor sin AXC
		}//End (validaApartado == 12 || validaApartado == 100)
		
		if(validaApartado == 100){
			if($('#numBolTicket').length && $('#numFacVenta').length){
				numBolTicket = $('#numBolTicket').val(); 
				numFacVenta = $('#numFacVenta').val(); 
				if(numBolTicket > 0  && numFacVenta > 0){
					if($('#totalesVolumenBolTicket').length && $('#totalesVolumenSolicitado').length){
						var totalesVolumenBolTicket = parseFloat($('#totalesVolumenBolTicket').val().replace(',',''));
						var totalesVolumenSolicitado = parseFloat($('#totalesVolumenSolicitado').val().replace(',',''));
						if(totalesVolumenBolTicket >= totalesVolumenSolicitado){
							console.log("Total Volumen Boleta Ticket Vs Total Volumen Solcitado -correcto");
						}else{
							$('#dialogo_1').html('El volumen total de boletas debe ser mayor igual al volumen total solicitado, favor de verificar');
							abrirDialogo();
							return false;
						}
					}
				}
			}
			if($('#numFacVenta').length && $('#numPagosSinAXC').length){
				numFacVenta = $('#numFacVenta').val(); 
				numPagosSinAXC = $('#numPagosSinAXC').val(); 
				if(numFacVenta > 0  && numPagosSinAXC > 0){
					if($('#totalesImporteFacturas').length && $('#totalesImportePagos').length){
						var totalesImporteFacturas = parseFloat($('#totalesImporteFacturas').val().replace(',',''));
						var totalesImportePagos =  parseFloat($('#totalesImportePagos').val().replace(',',''));
						if(totalesImportePagos >= totalesImporteFacturas){
							console.log("Total Importe Pago Vs Total Importe Facturas -correcto");
						}else{
							$('#dialogo_1').html('El importe total de pagos debe ser mayor igual al importe total facturado, favor de verificar');
							abrirDialogo();
							return false;
						}
					}
				}
			}
		}// end validaApartado == 100 para validar los totales
	}else if(idRelacion == 2){//Relacion = TERRESTRE
		var tipoFormatoTerrestre = $('input:radio[name=tipoFormatoTerrestre]:checked').val();
		if($('#selectedFechaEmbarque').length){
			if($('#selectedFechaEmbarque').val()==null || $('#selectedFechaEmbarque').val()== ""){
				$('#dialogo_1').html('Capture la fecha de embarque');
				abrirDialogo();
				return false;
			}
		}
		if($('#folioTalonTerrestreTemp').length){
			if($('#folioTalonTerrestreTemp').val()==null || $('#folioTalonTerrestreTemp').val()== ""){
				$('#dialogo_1').html('Capture el folio del talón de embarque por autotransporte');
				abrirDialogo();
				return false;
			}
			
			if( $('#errorFolioTalonTerrestre').val() ==1 || $('#errorFolioTalonTerrestre').val() ==1){
				$('#dialogo_1').html('Existe un error en la captura del folio');
			   	abrirDialogo();
				error = 1;
				return false;
			}
		}
		
		if($('#volumenEmbarcado').length){
			if($('#volumenEmbarcado').val()==null || $('#volumenEmbarcado').val()== ""){
				$('#dialogo_1').html('Capture el volumen embarcado');
				abrirDialogo();
				return false;
			}
		} 
		
		if($('#idEstadoBodegaFinal').length){
			if($('#idEstadoBodegaFinal').val()==null || $('#idEstadoBodegaFinal').val()== ""){
				$('#dialogo_1').html('Capture la bodega final');
				abrirDialogo();
				return false;
			}
		}
		
		if(tipoFormatoTerrestre == 0){//BODEGA DE ORIGEN - PLANTA PROCESADORA O FRONTERA
			var siFrontera= 0;
			var siPP= 0;
			if($('#frontera').length){
				siFrontera = 1;
			}
			if($('#plantaProcesadora').length){
				siPP = 1;
			}			
			if(siFrontera == 1 && siPP==1){
				if(($('#frontera').val()==null || $('#frontera').val()== "") && ($('#plantaProcesadora').val()==null || $('#plantaProcesadora').val()== "")){
					$('#dialogo_1').html('Debe capturar la frontera o planta procesadora ');
					abrirDialogo();
					return false;
				}else if(($('#frontera').val()!=null && $('#frontera').val()!= "") && ($('#plantaProcesadora').val()!=null && $('#plantaProcesadora').val()!= "")){
					$('#dialogo_1').html('Capture solo frontera o planta procesadora, pero no ambas ');
					abrirDialogo();
					return false;
				}
			}else if(siFrontera == 1){
				if($('#frontera').val()==null || $('#frontera').val()== ""){
					$('#dialogo_1').html('Debe capturar la frontera');
					abrirDialogo();
					return false;
				}
			}else{
				if($('#plantaProcesadora').val()==null || $('#plantaProcesadora').val()== ""){
					$('#dialogo_1').html('Debe capturar la planta procesadora');
					abrirDialogo();
					return false;
				}
			}
		}else {//PLANTA PROCESADORA - FRONTERA
			var siFrontera= 0;
			var siPP= 0;
			if($('#frontera').length){
				siFrontera = 1;
			}
			if($('#plantaProcesadora').length){
				siPP = 1;
			}
			
			if(siFrontera == 1 && siPP==1){
				if(($('#frontera').val()==null || $('#frontera').val()== "")){
					$('#dialogo_1').html('Debe capturar la frontera');
					abrirDialogo();
					return false;
				}
				if(($('#plantaProcesadora').val()!=null && $('#plantaProcesadora').val()!= "")){
					$('#dialogo_1').html('No debe capturar la planta procesadora');
					$('#plantaProcesadora').val(null);
					abrirDialogo();
					return false;
				}
			}else if(siFrontera == 1){
				if(($('#frontera').val()==null || $('#frontera').val()== "")){
					$('#dialogo_1').html('Debe capturar la frontera');
					abrirDialogo();
					return false;
				}
			}else{
				if(($('#plantaProcesadora').val()!=null && $('#plantaProcesadora').val()!= "")){
					$('#dialogo_1').html('No debe capturar la planta procesadora');
					abrirDialogo();
					return false;
				}
			}	
		}//End tipoFormatoTerrestre		
		if($('#selectedFechaRecepcion').length){
			if($('#selectedFechaRecepcion').val()==null || $('#selectedFechaRecepcion').val()== ""){
				$('#dialogo_1').html('Seleccione la fecha de recepción');
				abrirDialogo();
				return false;
			}
		}
		if($('#noTickets').length){
			if($('#noTickets').val()==null || $('#noTickets').val()== ""){
				$('#dialogo_1').html('Capture el número de tickets de bascula de entrada en almacén');
				abrirDialogo();
				return false;
			}
		}
		
		if($('#volumenRecibido').length){
			if($('#volumenRecibido').val()==null || $('#volumenRecibido').val()== ""){
				$('#dialogo_1').html('Capture el volumen recibido');
				abrirDialogo();
				return false;
			}
		}
		//Remover el tipo de formato terrestre
		$("#tipoFormatoTerrestre").removeAttr('disabled');
		
	}else if(idRelacion == 3){//Relacion =  MOVILIZACION MARITIMA
		if($('#nombreBarco').length){
			if($('#nombreBarco').val()==null || $('#nombreBarco').val()== ""){
				$('#dialogo_1').html('Capture el nombre del barco');
				abrirDialogo();
				return false;
			}
			$("#nombreBarco").removeAttr('disabled');
		}
		if($('#lugarDestino').length){
			if($('#lugarDestino').val()==null || $('#lugarDestino').val()== ""){
				$('#dialogo_1').html('Capture el lugar de destino');
				abrirDialogo();
				return false;
			}
			$("#lugarDestino").removeAttr('disabled');
		}
		if($('#edoOrigen').length){
			if($('#edoOrigen').val() == -1){
				$('#dialogo_1').html('Seleccione el Estado del Origen del Grano');
				abrirDialogo();
				return false;
			}
		}
		if($('#selectedFechaEmbarque').length){
			if($('#selectedFechaEmbarque').val()==null || $('#selectedFechaEmbarque').val()== ""){
				$('#dialogo_1').html('Capture la fecha de embarque');
				abrirDialogo();
				return false;
			}
		}
		if($('#folioTalonMaritimoTemp').length){
			if($('#folioTalonMaritimoTemp').val()==null || $('#folioTalonMaritimoTemp').val()== ""){
				$('#dialogo_1').html('Capture el folio del talón de embarque');
				abrirDialogo();
				return false;
			}
			if( $('#errorFolioTalonMaritimo').val() ==1 || $('#errorFolioTalonMaritimo').val() ==1){
				$('#dialogo_1').html('Existe un error en la captura del folio');
			   	abrirDialogo();
				return false;
			}
		}
		if($('#volumenEmbarcado').length){
			if($('#volumenEmbarcado').val()==null || $('#volumenEmbarcado').val()== ""){
				$('#dialogo_1').html('Capture el volumen embarcado');
				abrirDialogo();
				return false;
			}
		} 
		if($('#noConocimiento').length){
			if($('#noConocimiento').val()==null || $('#noConocimiento').val()== ""){
				$('#dialogo_1').html('Capture el No. de conocimiento de embarque');
				abrirDialogo();
				return false;
			}
		}
		if($('#puertoEmbarque').length){
			if($('#puertoEmbarque').val()==null || $('#puertoEmbarque').val()== ""){
				$('#dialogo_1').html('Capture el puerto de embarque');
				abrirDialogo();
				return false;
			}
		}
		if($('#selectedFechaRec').length){
			if($('#selectedFechaRec').val()==null || $('#selectedFechaRec').val()== ""){
				$('#dialogo_1').html('Seleccione la fecha de recepción');
				abrirDialogo();
				return false;
			}
		}
		
		if($('#selectedFechaEmi').length){
			if($('#selectedFechaEmi').val()==null || $('#selectedFechaEmi').val()== ""){
				$('#dialogo_1').html('Seleccione la fecha de emisión del documento');
				abrirDialogo();
				return false;
			}
		}
		if($('#noTickets').length){
			if($('#noTickets').val()==null || $('#noTickets').val()== ""){
				$('#dialogo_1').html('Capture el número de tickets de bascula de entrada en almacén');
				abrirDialogo();
				return false;
			}
		}
		if($('#selectedFechaSal').length){
			if($('#selectedFechaSal').val()==null || $('#selectedFechaSal').val()== ""){
				$('#dialogo_1').html('Seleccione la fecha de salida del barco');
				abrirDialogo();
				return false;
			}
		}
		if($('#puertoDescarga').length){
			if($('#puertoDescarga').val()==null || $('#puertoDescarga').val()== ""){
				$('#dialogo_1').html('Capture el puerto de descarga');
				abrirDialogo();
				return false;
			}
		}
		if($('#volumenRecibido').length){
			if($('#volumenRecibido').val()==null || $('#volumenRecibido').val()== ""){
				$('#dialogo_1').html('Capture el volumen recibido');
				abrirDialogo();
				return false;
			}
		}
		
		$("#tipoFormatoTerrestre").removeAttr('disabled');
		//end tipo relacion = 3 maritima
	}else if(idRelacion == 4){
		if($('#razonSocialAlmacen').length){
			if($('#razonSocialAlmacen').val() == -1){
				$('#dialogo_1').html('Seleccione la Razón Social del Almacen General de Depósito');
				abrirDialogo();
				return false;
			}
			$("#razonSocialAlmacen").removeAttr('disabled');
		}
		if($('#folioCertificado').length){
			if($('#folioCertificado').val()==null || $('#folioCertificado').val()== ""){
				$('#dialogo_1').html('Capture el folio de Certificado');
				abrirDialogo();
				return false;
			}
			
			$("#folioCertificado").removeAttr('disabled');
			if( $('#errorFolioCertificado').val() ==1 || $('#errorFolioCertificadoAlmacen').val() ==1){
				$('#dialogo_1').html('Existe un error en la captura del folio');
			   	abrirDialogo();
				error = 1;
				return false;
			}
			
			
		}
		if($('#selectedFechaExp').length){
			if($('#selectedFechaExp').val()==null || $('#selectedFechaExp').val()== ""){
				$('#dialogo_1').html('Capture la fecha de expedición');
				abrirDialogo();
				return false;
			}
		}
		if($('#selectedFechaVig').length){
			if($('#selectedFechaVig').val()==null || $('#selectedFechaVig').val()== ""){
				$('#dialogo_1').html('Capture la fecha de vigencia');
				abrirDialogo();
				return false;
			}
		}
		if($('#volumenAmparado').length){
			if($('#volumenAmparado').val()==null || $('#volumenAmparado').val()== ""){
				$('#dialogo_1').html('Capture el volumen');
				abrirDialogo();
				return false;
			}
		}
		//end tipo relacion = 4 Certificado de deposito
	}else if(idRelacion == 5){ //Relacion de ventas
		if($('#nombreRazonSocial').length){
			if($('#nombreRazonSocial').val()==null || $('#nombreRazonSocial').val()== ""){
				$('#dialogo_1').html('Capture el Nombre o Razón Social');
				abrirDialogo();
				return false;
			}
		}
		if($('#domicilio').length){
			if($('#domicilio').val()==null || $('#domicilio').val()== ""){
				$('#dialogo_1').html('Capture el Domicilio');
				abrirDialogo();
				return false;
			}
		}
		if($('#telefono').length){
			if($('#telefono').val()==null || $('#telefono').val()== ""){
				$('#dialogo_1').html('Capture el Telefono');
				abrirDialogo();
				return false;
			}
		}
		if($('#folioFacVentaTemp').length){
			if($('#folioFacVentaTemp').val()==null || $('#folioFacVentaTemp').val()== ""){
				$('#dialogo_1').html('Capture el folio de la factura');
				abrirDialogo();
				return false;
			}
			
			if( $('#errorFolioFacVenta').val() ==1 || $('#errorFolioFacVenta').val() ==1){
				$('#dialogo_1').html('Existe un error en la captura del folio');
			   	abrirDialogo();
				error = 1;
				return false;
			}
		}
		if($('#selectedFecha').length){
			if($('#selectedFecha').val()==null || $('#selectedFecha').val()== ""){
				$('#dialogo_1').html('Capture la fecha');
				abrirDialogo();
				return false;
			}
		}
		if($('#volumen').length){
			if($('#volumen').val()==null || $('#volumen').val()== ""){
				$('#dialogo_1').html('Capture el volumen');
				abrirDialogo();
				return false;
			}
		}
		if($('#idEstadoDet').length){
			if($('#idEstadoDet').val()==-1){
				$('#dialogo_1').html('Seleccione el estado');
				abrirDialogo();
				return false;
			}
		}
		
		if($('#usoGrano').length){
			if($('#usoGrano').val()==null || $('#usoGrano').val()== ""){
				$('#dialogo_1').html('Capture del uso del grano');
				abrirDialogo();
				return false;
			}
		}
		
	}//End Relacion de ventas
}// end chkCamposCapturaRelaciones


function chkCamposCertificados(){
	
}

function validaRfcProductor(rfcProductor, count){
	patron = /^([A-Z, a-z]{3,4})([0-9]{6})([A-Z,a-z,0-9]{3})$/;
	rfcProductor.toUpperCase();
	if (!rfcProductor.match(patron)){
		$('#dialogo_1').html('El RFC del Productor es inválido, capture correctamente el RFC con homoclave, ejemplo (ABCD123456R12)');
		$('#rfcR'+count).val(null);
		abrirDialogo();
		$('#errorBlur').val(8);
		return false;
	}else{
		var numFacVenta = $('#numFacVenta').val();
		if(numFacVenta != null && numFacVenta != ""){
			var rfcR = $('#rfcR').val().toUpperCase();
			for(var i=1;i<parseInt(numFacVenta)+1;i++){
				$('#capRfcFac'+i).val(rfcR);
			}
		}			
		$('#errorBlur').val(0);
	}
}

function cambiaRfcFacturas(){
	var numFacVenta = $('#numFacVenta').val();
	var rfcR = $('#rfcR').val().toUpperCase();
	if(numFacVenta != "" && rfcR != ""){
		$.ajax({
			async: false,
			type: "POST",
			url: "cambiaRfcFacturas",
			data: "numFacVenta="+numFacVenta+
			"&rfcR="+rfcR,
			success: function(data){
				$('#cambiaRfcFacturas').html(data).ready(function () {
					$("#cambiaRfcFacturas").fadeIn('slow');
				});
			}
		}); //termina ajax
		 for(var i=1;i<parseInt(numFacVenta)+1;i++){
			$('#capRfcFac'+i).val(rfcR);
		}	
	}
}

function validaRfc(rfc, count){
	patron = /^([A-Z, a-z]{3,4})([0-9]{6})([A-Z,0-9]{3})$/;
	if (!rfc.match(patron)){
		$('#dialogo_1').html('El valor del campo '+count+' es inválido, capture correctamente el RFC con homoclave, ejemplo (ABCD123456R12)');
		abrirDialogo();
		$('#errorBlur').val(1);
		$('#rfc'+count).val(null);
		return false;
	}else{
		$('#errorBlur').val(0);
	}
}


function validaVolumenBolTicket(volumen, count){
	var valVol="";
	console.log("arg volumen "+volumen);
	console.log("count "+count);
	valVol = validaVolumen(volumen, count);
	console.log("ValVol "+valVol);	
	if(valVol == 0){
		$('#capVolBolTicket'+count).val(null);
		return false;
	}else if(volumen>200.000){
		$('#dialogo_1').html('El volumen capturado en el campo  '+count+' es inválido, el volumen no puede exceder de 200 toneladas, favor de verificar');
		abrirDialogo();
		$('#capVolBolTicket'+count).val(null);
		return false;
	}else{
		sumarVolumenBolTicket();
	}	 
}


function sumarVolumenBolTicket(){
	//Suma el total del volumen de las boletas capturadas
	var numBolTicket = $('#numBolTicket').val();
	var totalVolumenBolTicket = 0;
	var volumen = 0;
	var i = 0;
	/*Calcula el valor total de volumen*/
	for (i=1;i<parseInt(numBolTicket)+1;i++){
		volumen = $('#capVolBolTicket'+i).val();
		console.log("VOLUMEN "+volumen); 
		if(volumen!="" && volumen !=null){
			totalVolumenBolTicket = (totalVolumenBolTicket+parseFloat(volumen));	
		}
	}
	$("#totalesVolumenBolTicket").val(totalVolumenBolTicket.toFixed(3));	
}

function validaVolumen(volumen, count){
	var patron =/^\d{1,10}((\.\d{1,3})|(\.))?$/;
	if (!volumen.match(patron)){
		$('#dialogo_1').html('El valor del campo '+count+' es inválido, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
		abrirDialogo();
		return 0;
	}else{
		return 1;
	}
}



function validarVolumen(volumen, id, msj){	
	if(volumen!=null && volumen !=''){		
		var patron =/^\d{1,10}((\.\d{1,3})|(\.))?$/;
		if (!volumen.match(patron)){	
			if(id!=0){
				$('#'+id).val(null);
			}
			if(msj==1){
				$('#dialogo_1').html('El valor capturado es incorrecto, se deben capturar decimales y aceptan hasta 10 digitos a la izquierda y 3 máximo a la derecha');
				abrirDialogo();
				return false;
			}
			return 0;
		}else{
			return 1;
		}
	}
}

function validarImporte(importe, id, msj){	
	if(importe!=null && importe !=''){		
		var patron =/^\d{1,10}((\.\d{1,2})|(\.))?$/;
		if (!importe.match(patron)){	
			if(id!=0){
				$('#'+id).val(null);
			}
			if(msj==1){
				$('#dialogo_1').html('El valor capturado es incorrecto, se deben capturar decimales y se aceptan hasta 10 digitos a la izquierda y 2 máximo a la derecha');
				abrirDialogo();
				return false;
			}
			return 0;
		}else{
			return 1;
		}
	}
}
function validaImporte1(importe, count){
	var patron =/^\d{1,10}((\.\d{1,2})|(\.))?$/;	
	if (!importe.match(patron)){
		$('#errorBlur').val(4);
		return 0;
	}else{
		$('#errorBlur').val(0);
		return 1;
	}
}
function validaImporte(importe, count){
	var patron =/^\d{1,10}((\.\d{1,2})|(\.))?$/;	
	if (!importe.match(patron)){
		$('#dialogo_1').html('El valor del campo '+count+' es inválido, se deben capturar decimales y se aceptan hasta 10 digitos a la izquierda y 2 máximo a la derecha');
		abrirDialogo();
		$('#errorBlur').val(4);
		$('#importe'+count).val(null);
		return false;
	}else{
		$('#errorBlur').val(0);
	}
}
function validaCaracter(caracter, count){
	var patron = /^([a-zA-Z0-9]*\(*\)*ñ*\s*á*é*í*ó*ú*Á*É*Í*Ó*Ú*Ñ*´*-*\"*\'*\&*)*$/;
	if (!caracter.match(patron)){
		$('#dialogo_1').html('El valor del campo '+count+' es inválido, solo se aceptan letras, numeros, -, ", &, \' ');
		abrirDialogo();
		$('#errorBlur').val(5);
		$('#caracter'+count).val(null);
		return false;
	}else{
		$('#errorBlur').val(0);
	}
}
/*function validaNumero(numero, id, msj){
	var patron = /^([0-9]{1,10})$/;
	if (!numero.match(patron)){
		$('#dialogo_1').html('El valor del campo '+count+' es inválido, no se aceptan decimales');
		abrirDialogo();
		$('#errorBlur').val(6);
		$('#numero'+count).val(null);
		return false;
	}else{
		$('#errorBlur').val(0);
	}
}*/
function validaNumero(numero, id, msj){
	var patron = /^([0-9]{1,10})$/;
	if(numero!=null && numero !=''){
		if (!numero.match(patron)){	
			if(id!=0){
				$('#'+id).val(null);
			}
			if(msj==1){
				$('#dialogo_1').html('El valor capturado es incorrecto, se deben capturar numeros, por favor verifique');
				abrirDialogo();
				return false;
			}
			return 0;
		}else{
			return 1;
		}
	}
}


function validaTicket(ticket, count){
	var patron = /^([A-Z, a-z,0-9,-]{1,10})$/;
	if (!ticket.match(patron)){
		$('#dialogo_1').html('Capture correctamente el n° de boleta o ticket de bascula, solo se aceptan letras, números y guión ejemplo (AB-123456)');
		abrirDialogo();
		$('#errorBlur').val(7);
		$('#ticket'+count).val(null);
		return false;
	}else{
		$('#errorBlur').val(0);
	}
}

$("#agregarFolioPredio").click(function () {	 
	var numPredios = $('#numPredios').val();
	var idPerRel = $('#idPerRel').val();
	var folioProductor = $('#folioProductor').val();
	var idCompProd = $('#idCompProd').val();
	var registrar = $('#registrar').val();
	if (folioProductor == ""){
		$('#dialogo_1').html('Debe capturar el Folio del Productor');
	   		abrirDialogo();
	   		$('#numPredios').val("");
	 		return false;
	}else{	
		if(numPredios == 1){
			$.ajax({
			   async: false,
			   type: "POST",
			   url: "foliosPredios",
			   data: "numPredios="+numPredios+
			   "&idPerRel="+idPerRel+
			   "&registrar="+registrar+
			   "&idCompProd="+idCompProd,
			   success: function(data){
					$('#foliosPredios').html(data).ready(function () {
						$("#foliosPredios").fadeIn('slow');
					});
			   }
			}); //termina ajax
		}else{
			
			
			
		}
	  
	}
});

/*
function facturaGlobal(){ 
	var numFactGlobal = $('#numFactGlobal').val();
	var idPerRel = $('#idPerRel').val();
	if(numFactGlobal == ""){
		$("#facturaGlobal").fadeOut('slow');
		return false;	
	}
	$.ajax({
		async: false,
		type: "POST",
		url: "facturaGlobal",
	   data: "numFactGlobal="+numFactGlobal+
	   "&idPerRel="+idPerRel,
		success: function(data){
			$('#facturaGlobal').html(data).ready(function () {
				$("#facturaGlobal").fadeIn('slow');
			});
		}
	}); //termina ajax
}
*/

function facturaVenta(count){ 
	var numFacVenta = $('#numFacVenta').val();
	if(numFacVenta == 0 || numFacVenta == "" || numFacVenta == null){
		if(numFacVenta == 0){
			$('#numFacVenta').val(null);
			$("#facturaVenta").fadeOut('slow');
		}
		return false;
	}
	var idPerRel = $('#idPerRel').val();
	var rfcR = $('#rfcR').val().toUpperCase();
	var folioProductor = $('#folioProductor').val();
	var registrar = $('#registrar').val();
	var idCompProd = $('#idCompProd').val();
	if (folioProductor == ""){
		$('#dialogo_1').html('Debe capturar el Folio del Productor');
	   		abrirDialogo();
	   		$('#numFacVenta').val("");
	 		return false;
	}else{
		if(numFacVenta == ""){
			$("#facturaVenta").fadeOut('slow');
			return false;	
		}
	}
	$.ajax({
		async: false,
		type: "POST",
		url: "facturaVenta",
		data: "numFacVenta="+numFacVenta+
		"&idPerRel="+idPerRel+
		"&rfcR="+rfcR+
		"&registrar="+registrar+
		   "&idCompProd="+idCompProd,
		success: function(data){
			$('#facturaVenta').html(data).ready(function () {
				$("#facturaVenta").fadeIn('slow');
			});
		}
	}); //termina ajax
	 for(var i=1;i<parseInt(numFacVenta)+1;i++){
		$('#capRfcFac'+i).val(rfcR);
	}
}


function pagosSinAXC(){ 
	var numPagosSinAXC = $('#numPagosSinAXC').val();
	if(numPagosSinAXC == 0 || numPagosSinAXC == "" || numPagosSinAXC == null){
		if(numPagosSinAXC == 0){
			$('#numPagosSinAXC').val(null);
			$("#pagosSinAXC").fadeOut('slow');
		}
		return false;
	}
	var idPerRel = $('#idPerRel').val();
	var folioProductor = $('#folioProductor').val();
	var idCompProd = $('#idCompProd').val();
	var registrar = $('#registrar').val();
	if (folioProductor == ""){
		$('#dialogo_1').html('Debe capturar el Folio del Productor');
	   		abrirDialogo();
	   		$('#numPagosSinAXC').val("");
	 		return false;
	}else{
		if(numPagosSinAXC == ""){
			$("#pagosSinAXC").fadeOut('slow');
			return false;	
		}
	}
	$.ajax({
		async: false,
		type: "POST",
		url: "pagosSinAXC",
		 data: "numPagosSinAXC="+numPagosSinAXC+
		   "&idPerRel="+idPerRel+
		   "&registrar="+registrar+
		   "&idCompProd="+idCompProd,
		success: function(data){
			$('#pagosSinAXC').html(data).ready(function () {
				$("#pagosSinAXC").fadeIn('slow');
			});
		}
	}); //termina ajax
}


function chkCamposInicializaProgramaRel(){
	var nombreEsquema = $('#nombreEsquema').val();
	if( nombreEsquema == null || nombreEsquema =="" ){
		$('#dialogo_1').html('Debe capturar el nombre corto del esquema');
		abrirDialogo();
		return false;
	}
	//Validando los ciclos
	var ciclo ="", anio ="",  tempCiclo ="", tempAnio ="", i = 0, j = 0;
	var numCiclos = $('#numCiclos').val();
	if(numCiclos == -1){
		$('#dialogo_1').html('Seleccione el número de ciclos a capturar');
		abrirDialogo();
	    return false;
	}else{
		for (i=1;i<parseInt(numCiclos)+1;i++){
			ciclo = $('#ci'+i).val();
			anio = $('#a'+i).val();
			if(ciclo==-1 || anio==-1 ){
	   			$('#dialogo_1').html('Capture los valores del registro '+i+' de la captura de ciclos');
		   		abrirDialogo();
		 		return false;
	   		 }else{
	   			/*Valida que el ciclo seleccionado por el usuario no se encuentre repetido*/
		   			for (j=1;j<parseInt(numCiclos)+1;j++){
		   				if(i!=j){
		   					tempCiclo = $('#ci'+j).val();
		   					tempAnio = $('#a'+j).val();
		   					if(ciclo == tempCiclo && anio == tempAnio ){
		   						$('#dialogo_1').html('El ciclo del registro '+i+", se encuentra repetido, favor de verificar");
		   				   		abrirDialogo();
		   				 		return false;
		   					}
		   				}	   				
		   			} 
	   		 }
		}
	}
	//Validando los cultivos
	var numCultivos = $('#numCultivos').val();
	if(numCultivos == -1){
		$('#dialogo_1').html('Seleccione el número de cultivos a capturar');
		abrirDialogo();
	    return false;
	}else{
		var cultivo ="",   tempCultivo ="";
		for (i=1;i<parseInt(numCultivos)+1;i++){
			cultivo = $('#cultivo'+i).val();
			
			if(cultivo==-1){
	   			$('#dialogo_1').html('Capture los valores del registro '+i+' de la captura de cultivos');
		   		abrirDialogo();
		 		return false;
	   		 }else{
	   			/*Valida que el ciclo seleccionado por el usuario no se encuentre repetido*/
		   			for (j=1;j<parseInt(numCultivos)+1;j++){
		   				if(i!=j){
		   					tempCultivo = $('#cultivo'+j).val();
		   					
		   					if(cultivo == tempCultivo){
		   						$('#dialogo_1').html('El cultivo del registro '+i+", se encuentra repetido, favor de verificar");
		   				   		abrirDialogo();
		   				 		return false;
		   					}
		   				}	   				
		   			} 
	   		 }
		}
	}// end else cultivos	
}


function validaFoliosFactura(count){
	var numFacVenta = parseInt($('#numFacVenta').val());
	var registrar = parseInt($('#registrar').val());
	var capFolioFac = $('#capFolioFac'+count).val();
	var idCompProd =0;
	if(registrar == 2){//Edicion
		idCompProd = $('#idCompProd').val();
	}
	
	var folioProductor = $('#folioProductor').val();
	if((capFolioFac == "" || capFolioFac == null)){
		error = 1;
		return false;
	}else{
		var capFolioFacTmp = null;
		var i=0;
		var j=0;
		for (i=1;i<numFacVenta+1;i++){
			validaFolioFac = $('#capFolioFac'+i).val();
			if(validaFolioFac != ""){
				for (j=1;j<numFacVenta+1;j++){
					if(i!=j){
						capFolioFacTmp = $('#capFolioFac'+j).val();
						if(validaFolioFac == capFolioFacTmp){						
							$('#dialogo_1').html('La Factura que capturó se encuentra repetido en el registro '+i+', favor de verificar');
						   	abrirDialogo();
						   	$('#errorFacturasRepetidos'+j).val(1);
						 	return false;
						}else{
							$('#errorFacturasRepetidos'+j).val(0);
						}
					}
				}
			}
		}
		
		if(registrar == 2){
			if($('#folioFacAnt'+count).length){
				var folioFacAnt = $.trim($('#folioFacAnt'+count).val());
				//Si el folio capturado es igual al de un inicio, no valida la factura
				capFolioFac = $.trim(capFolioFac);
				if(folioFacAnt == capFolioFac){
					console.log("folioFacAnt "+folioFacAnt);
					console.log("capFolioFac "+capFolioFac);
					return false; //No valida folio y regresa a la pantalla
				}
				
			}
		}
		
		
		//Valida que la factura no se encuentre repetida a nivel productor
		$.ajax({
			async: false,
			type: "POST",
			url: "validaFoliosFactura",
			 data: "registrar="+registrar+
			   "&idCompProd="+idCompProd+
			   "&folioProductor="+folioProductor+
			   "&folioFac = "+capFolioFac+
			   "&count="+count,			  
			success: function(data){				
				var $response=$(data);
				   var errorFacturaByProductor = $response.filter('#errorFacturaByProductor'+count).text();
				   console.log("errorFacturaByProductor "+errorFacturaByProductor);		           
				   /*if(errorFacturaByProductor == 1){
					   $('#errorFacturaByProductor').val(1);
				   }else{
					   $('#errorFacturaByProductor').val(0);
				   }*/
				   $("#validaFoliosFactura"+count).html(data).ready(function () {
						$("#validaFoliosFactura"+count).fadeIn('slow');
					});
				   //$("#errorFacturaByProductor").fadeOut("slow");
			}
		}); //termina ajax
		
		
	}//end else
}

function calculaVolumenFacturas(){
	var numFacVenta = $('#numFacVenta').val();
	var totalVolumenSolicitado = 0;
	var volumen = null;
	var i = 0;
	$("#totalesVolumenSolicitado").val(totalVolumenSolicitado.toFixed(3));
	/*Calcula el valor total de volumen*/
	for (i=1;i<parseInt(numFacVenta)+1;i++){
		volumen = $('#capVolSolFac'+i).val();
		var valVol = validarVolumen(volumen, 0, 0);
		if(valVol == 1){
			totalVolumenSolicitado = (totalVolumenSolicitado+parseFloat(volumen));
		}	
	}
	$("#totalesVolumenSolicitado").val(totalVolumenSolicitado.toFixed(3));	
}
function calculaTotalVolumenFacturas(){
	var numFacVenta = $('#numFacVenta').val();
	var totalVolumenFacturado = 0;
	var volumenTmp = null;
	var i = 0;
	$("#totalesVolumenFacturado").val(totalVolumenFacturado.toFixed(3));
	/*Calcula el valor total de volumen*/
	for (i=1;i<parseInt(numFacVenta)+1;i++){
		volumenTmp = $('#capVolTotalFac'+i).val();
		var valVol = validarVolumen(volumenTmp, 0, 0);
		if(valVol == 1){
			totalVolumenFacturado = (totalVolumenFacturado+parseFloat(volumenTmp));
		}
	}
	$("#totalesVolumenFacturado").val(totalVolumenFacturado.toFixed(3));
	/*Calcula el precio x tonelada, siempre y cuando el campo importe total de la factura se encuentre capturada*/
	setValPrecioXTonelada();
	
}
function calculaTotalImporteFacturas(){
	/*Valida que el campo sea valido*/
	var numFacVenta = $('#numFacVenta').val();
	var totalImporteFacturas = 0;
	var importe = null;
	var i = 0;
	$("#totalesImporteFacturas").val(totalImporteFacturas.toFixed(2));
	/*Calcula el valor total del impore*/
	for (i=1;i<parseInt(numFacVenta)+1;i++){
		importe = $('#capImpSolFac'+i).val();
		var valImp = validarImporte(importe, 0, 0);
		if(valImp == 1){
			totalImporteFacturas = (totalImporteFacturas+parseFloat(importe));
		}
	}
	$("#totalesImporteFacturas").val(totalImporteFacturas.toFixed(2));
	setValPrecioXTonelada();
}

function setValPrecioXTonelada(){
	var numFacVenta = $('#numFacVenta').val();
	var i = 0, precioXtonelada = 0;	
	var volumen = 0, importe = 0;
	for (i=1;i<parseInt(numFacVenta)+1;i++){
		volumen = $('#capVolTotalFac'+i).val();
		console.log("v"+volumen);
		importe = $('#capImpSolFac'+i).val();
		console.log("i"+importe);
		if((importe!="" && importe !=null)&&((volumen!="" && volumen !=null))){
			if(volumen != 0){
				precioXtonelada = importe/volumen;
				$("#precioXTonelada"+i).val(precioXtonelada.toFixed(3));
			}			
				
		}
	}
}

function calculaTotalImportePagos(){
	var numPagosSinAXC = $('#numPagosSinAXC').val();
	var totalImportePagos = 0;
	var importe = null;
	var i = 0;
	$("#totalesImportePagos").val(totalImportePagos.toFixed(2));	
	/*Calcula el valor total de volumen*/
	for (i=1;i<parseInt(numPagosSinAXC)+1;i++){
		importe = $('#capImpDocPago'+i).val();
		if(importe!="" && importe !=null){
			var valImp = validarImporte(importe, 0, 0);
			if(valImp == 1){
				totalImportePagos = (totalImportePagos+parseFloat(importe));
			}			
		}
	}
	$("#totalesImportePagos").val(totalImportePagos.toFixed(2));	
}

function validaBodegaTicket(boletaTicket, count){
	var claveBodega = $('#claveBodega').val();
	var idPerRel = $('#idPerRel').val();
	var numBolTicket = parseInt($('#numBolTicket').val());
	var registrar =$('#registrar').val();
	
	//Valida que el ticket o boleta no se encuentre repetido
	if((boletaTicket == "" || boletaTicket == null)){
		return false;
	}else{
		//Valida que la boleta cumpla con las reglas de la estructura
		var patron = /^([A-Z, a-z,0-9,-]{1,10})$/;
		if (!boletaTicket.match(patron)){
			$('#dialogo_1').html('El valor del campo '+count+' es inválido, solo se aceptan letras, números y guión ejemplo (AB-123456)');
			abrirDialogo();
			$('#count').val(count);
			$('#folio'+ count).val(null);
			return false;
		}		var capBolTicketTmp = null;
		var i=0;
		var j=0;
		for (i=1;i<numBolTicket+1;i++){
			capBolTicket = $('#capBolTicket'+i).val();
			if(capBolTicket != ""){
				for (j=1;j<numBolTicket+1;j++){
					if(i!=j){
						capBolTicketTmp = $('#capBolTicket'+j).val();
						if(capBolTicket == capBolTicketTmp){						
							$('#dialogo_1').html('La Boleta/Ticket que capturó se encuentra repetido en el registro '+i+', favor de verificar');
							$("#errorBoletaRepetida"+j).val(1);
						   	abrirDialogo();
						 	return false;
						}else{
							$("#errorBoletaRepetida"+j).val(0);
						}
					}
				}
			}
		}//End for
	}
	
	if(registrar == 2){//Edicion
		if($('#boletaTicketBasculaAnt'+count).length){
			var boletaTicketBasculaAnt = $.trim($('#boletaTicketBasculaAnt'+count).val());			
			//Si la boleta capturada es igual al de un inicio, no valida la edicion
			boletaTicket = $.trim(boletaTicket);		
			if(boletaTicketBasculaAnt == boletaTicket){
				return false; //No valida folio y regresa a la pantalla
			}
			
		}
	}
	$.ajax({
		async: false,
		type: "POST",
		url: "validaBodegaTicket",
		data: "boletaTicket="+boletaTicket+
		"&claveBodega="+claveBodega+
		"&idPerRel="+idPerRel+
		"&count="+count,
		success: function(data){
		   var $response=$(data);
		   var errorBodegaTicket = $response.filter('#errorBodegaTicket'+count).text();
		   console.log("errorBodegaTicket "+errorBodegaTicket);
           /*
		   if(errorBodegaTicket == 1){
			   $('#errorBodegaTicket').val(1);
		   }else{
			   $('#errorBodegaTicket').val(0);
		   }*/
		   $("#validaBodegaTicket"+count).html(data).ready(function () {	
			});
		   //$("#errorBodegaTicket").fadeOut("slow");
	   }
	});//fin ajax
		
	var totalVolumenBolTicket = 0; 
	/*Calcula el valor total de volumen*/
	for (i=1;i<parseInt(numBolTicket)+1;i++){
		 var volumen = $('#capVolBolTicket'+i).val();
		if(volumen!="" && volumen !=null){
			totalVolumenBolTicket = (totalVolumenBolTicket+parseFloat(volumen));	
		}

	}
	$("#totalesVolumenBolTicket").val(totalVolumenBolTicket.toFixed(3));
}



function validaFolioDocPago(folio, id){	
	var patron = /^([A-Z, a-z,0-9,-]{1,10})$/;
	if (!folio.match(patron)){
		$('#dialogo_1').html('El valor del campo es inválido, solo se aceptan letras, números y guión ejemplo (AB-123456)');
		abrirDialogo();
		if(id!=0){
			$('#'+id).val(null);
		}
		return false;
	}
}
function validaPredios(count){	
	var folioPredio = $('#capPredio'+count).val();
	var predioSecuencial = $('#capPredioSec'+count).val();	
	var predioAlterno = $('#capPredioAlterno'+count).val();
	var idPerRel = $('#idPerRel').val();
	var superficiePredio = $('#superficiePredio'+count).val();

	var valVol = validaVolumen(superficiePredio, count);
	if(valVol == 0){
		$('#superficiePredio'+count).val(null);
		return false;
	}	
	var numPredios = $('#numPredios').val();
	var registrar = $('#registrar').val();
	var idCompProd = $('#idCompProd').val();
	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "validarPredio",
		   data: "folioPredio="+folioPredio+
		   		"&predioSecuencial="+predioSecuencial+
		   		"&predioAlterno="+predioAlterno+
		   		"&idPerRel="+idPerRel+
		   		"&superficiePredio="+superficiePredio+
		   		"&count="+count+
		   		"&idCompProd="+idCompProd+
		   		"&registrar="+registrar,
		   success: function(data){
			   var $response=$(data);
			    errorValidacion = $response.filter('#errorValidacion'+count).text();
			    var superficieTotal = $response.filter('#superficieTotal'+count).text();
			    if(superficieTotal != null && superficieTotal != ""){
			    	superficieTotal = parseFloat(superficieTotal).toFixed(2);
			    }else{
			    	superficieTotal = null;
			    }
		    	$('#validaPredio'+count).html(data).ready(function () {
					$("#validaPredio"+count).fadeIn('slow');
					$("#superficieTotalPredio"+count).val(superficieTotal);		
				});				      
		   }
	});//fin ajax
		//Totales de superficieTotal totalesSuperficieApoyar
		//var totalesSuperficieTotal = 0;
		var totalesSuperficieApoyar = 0;
		var i = 0;
		for(i=1; i<= numPredios; i++){
			//var superficieTotalPredio =  $('#superficieTotalPredio'+i).val();
			var superficiePredio =  $('#superficiePredio'+i).val();	
			if(superficiePredio != "" && superficiePredio !=null){
				totalesSuperficieApoyar = (totalesSuperficieApoyar+parseFloat(superficiePredio));
			}
		}
		
		//$("#totalesSuperficieTotal").val(totalesSuperficieTotal.toFixed(2));
		if(totalesSuperficieApoyar > 0){
			$("#totalesSuperficieApoyar").val(totalesSuperficieApoyar.toFixed(2));
		}
			
	
	
	
} //End validaPredios


function validaTipoDocPago(count){
	var capIdTipoDocPago = $('#capIdTipoDocPago'+count).val();
	if(capIdTipoDocPago == -1){
		return false;
	}else if(capIdTipoDocPago == 3){
		$("#capBancoIdSin"+count).val(-1);
		$("#capBancoIdSin"+count).attr('disabled','disabled'); 
	}else{	
		$("#capBancoIdSin"+count).removeAttr("disabled"); 
	}
}

function validaFecha(fecha, count, id, msj){
	console.log("id "+id);
	console.log("count "+count);
	console.log("msj"+msj);
	var fechaActualL = $('#fechaActualL').val();
	var dia = fecha.substring(0,2);
	var mes = fecha.substring(3,5);
	var anio = fecha.substring(6,10);
	var fechaTemp = anio+""+""+mes+""+dia;
	if(parseInt(fechaTemp) > parseInt(fechaActualL)){
		console.log("mayor invalida");
		$('#dialogo_1').html('La fecha no puede ser mayor a la actual en el apartado "'+msj+'" registro ['+count+"]");
		abrirDialogo();		
		$('#'+id+count).val(null);
		return false;
	}
}



function recuperaBodegas(idPerRel, idIniEsquemaRelacion, count, estatus, idRelacion ){	
	if(estatus==1){
		$("#imgBodegaOff"+count).css("display", "none");
		$("#imgBodegaOn"+count).css("display", "inline");
		$.ajax({
			   async: false,
			   type: "POST",
			   url: "busquedaParticipanteBodegas",
			   data: "idPerRel="+idPerRel
			   		+"&idIniEsquemaRelacion="+idIniEsquemaRelacion
			   		+"&idRelacion="+idRelacion,
			   success: function(data){
					$('#recuperaBodegas'+count).html(data).ready(function () {
						$("#recuperaBodegas"+count).fadeIn('slow');
					});
			   }
			});
	}else{
		$("#imgBodegaOff"+count).css("display", "inline");
		$("#imgBodegaOn"+count).css("display", "none");
		$("#recuperaBodegas"+count).fadeOut('slow');
	}
}



function validaFolio(folio, id, msj){
	var patron = /^([A-Z, a-z,0-9,-]{1,10})$/;
	if (!folio.match(patron)){
		if(id!=0){
			$('#'+id).val(null);
		}
		if(msj==1){
			$('#dialogo_1').html('El valor del campo  es inválido, solo se aceptan letras, números y guión ejemplo (AB-123456)');
			abrirDialogo();
			return false;
		}
		return 0;
	}else{
		return 1;
	}
}



function validaFolioCertificadoDeposito(folio){
	
	var claveBodega =  $('#claveBodega').val();
	var cultivoRelacion = $('#cultivo').val();
	var idPgrCiclo = $('#idPgrCiclo').val();
	var idPerRel = $('#idPerRel').val();
	var razonSocialAlmacen = $('#razonSocialAlmacen').val();
	
	if(claveBodega == "" || claveBodega == null){
		$('#dialogo_1').html('Capture primero la Clave de la Bodega');
		abrirDialogo();
		return false;
	}
		
	var patron = /^([A-Z, a-z,0-9,-]{1,10})$/;
	if (!folio.match(patron)){
		$('#dialogo_1').html('El valor del campo es inválido, solo se aceptan letras, números y guión ejemplo (AB-123456)');
		abrirDialogo();
		return false;
	}
	
	$.ajax({
		async: false,
		type: "POST",
		url: "validaFolioCertificadoDeposito",
	   data: "&idPerRel="+idPerRel +
	   		 "&claveBodega="+claveBodega+
	   		 "&cultivoRelacion="+cultivoRelacion+
	   		 "&idPgrCiclo="+idPgrCiclo+
	   		 "&razonSocialAlmacen="+razonSocialAlmacen+
	   		 "&folioCertificado="+folio,
		success: function(data){
			$('#validaFolioCertificadoDeposito').html(data).ready(function () {
				$("#validaFolioCertificadoDeposito").fadeIn('slow');
			});
		}
	}); //termina ajax
}

function validarFolioFacVenta(){
	var folioFacVentaTemp = $('#folioFacVentaTemp').val();
	var cultivoRelacion = $('#cultivo').val();
	var idPgrCiclo = $('#idPgrCiclo').val();
	var idPerRel = $('#idPerRel').val();
	$('#folioFacVenta').val(folioFacVentaTemp);
	
	if(cultivoRelacion != -1 && idPgrCiclo -1 && (folioFacVentaTemp!=null || folioFacVentaTemp !="")){
		$.ajax({
			async: false,
			type: "POST",
			url: "validarFolioFacVenta",
		   data: "&idPerRel="+idPerRel +
		   		 "&cultivoRelacion="+cultivoRelacion+
		   		 "&idPgrCiclo="+idPgrCiclo+
		   		 "&folioFacVenta="+folioFacVentaTemp,
			success: function(data){
				$('#validarFolioFacVenta').html(data).ready(function () {
					$("#validarFolioFacVenta").fadeIn('slow');
				});
			}
		}); //termina ajax
	}
}

function validarFolioTalonTerrestre(){

	var folioTalonTerrestreTemp = $('#folioTalonTerrestreTemp').val();
	var cultivoRelacion = $('#cultivo').val();
	var idPgrCiclo = $('#idPgrCiclo').val();
	var idPerRel = $('#idPerRel').val();
	var claveBodega = $('#claveBodega').val();
	var estadoBodega = $('#estadoBodega').val();
	$('#folioTalonTerrestre').val(folioTalonTerrestreTemp);		
	if (cultivoRelacion != -1
		&& idPgrCiclo != -1
		&& (folioTalonTerrestreTemp != null && folioTalonTerrestreTemp != "")
		&& (claveBodega != null && claveBodega != "") && estadoBodega != -1) {	
		$.ajax({
			async: false,
			type: "POST",
			url: "validarFolioTalonTerrestre",
		   data: "&idPerRel="+idPerRel +
		   		 "&cultivoRelacion="+cultivoRelacion+
		   		 "&idPgrCiclo="+idPgrCiclo+
		   		 "&folioTalonTerrestre="+folioTalonTerrestreTemp+
		   		 "&claveBodega="+claveBodega+
		   		 "&estadoBodega="+estadoBodega,
			success: function(data){
				$('#validarFolioTalonTerrestre').html(data).ready(function (){
					$("#validarFolioTalonTerrestre").fadeIn('slow');
				});
			}
		}); //termina ajax
	}
}

function validarFolioTalonMaritima(){
	var folioTalonMaritimoTemp = $('#folioTalonMaritimoTemp').val();
	var cultivoRelacion = $('#cultivo').val();
	var idPgrCiclo = $('#idPgrCiclo').val();
	var idPerRel = $('#idPerRel').val();
	var claveBodega = $('#claveBodega').val();
	var nombreBarco = $('#nombreBarco').val();
	var lugarDestino = $('#lugarDestino').val();
	$('#folioTalonMaritimo').val(folioTalonMaritimoTemp);
	if (cultivoRelacion != -1 && idPgrCiclo - 1
		&& (folioTalonMaritimoTemp != null && folioTalonMaritimoTemp != "")
		&& (claveBodega != null && claveBodega != "")
		&& (nombreBarco != null && nombreBarco != "")
		&& (lugarDestino != null && lugarDestino != "")){
		$.ajax({
			async: false,
			type: "POST",
			url: "validarFolioTalonMaritima",
		   data: "&idPerRel="+idPerRel +
		   		 "&cultivoRelacion="+cultivoRelacion+
		   		 "&idPgrCiclo="+idPgrCiclo+
		   		 "&folioTalonMaritimo="+folioTalonMaritimoTemp+
		   		 "&claveBodega="+claveBodega+
		   		 "&nombreBarco="+nombreBarco+
		   		 "&lugarDestino="+lugarDestino,
			success: function(data){
				$('#validarFolioTalonMaritima').html(data).ready(function () {
					$("#validarFolioTalonMaritima").fadeIn('slow');
				});
			}
		}); //termina ajax
	}
}

function agregarEntradaBodega () {
	var numBolTicket = $('#numBolTicket').val();
	var idPerRel = $('#idPerRel').val();
	var folioProductor = $('#folioProductor').val();
	var registrar = $('#registrar').val();
	var idCompProd = $('#idCompProd').val();
	if (folioProductor == ""){
		$('#dialogo_1').html('Debe capturar el Folio del Productor');
	   		abrirDialogo();
	   		$('#numBolTicket').val("");
	 		return false;
	}else{
		numBolTicket = parseInt(numBolTicket) + 1;	
		$('#numBolTicket').val(numBolTicket);
		$("#removerEntradaBodega").fadeIn('slow');
		$("#guardarEntradaBodega").fadeIn('slow');
		if(numBolTicket == 1){
			$.ajax({
				async: false,
				type: "POST",
				url: "bolTicket",
			   data: "numBolTicket="+numBolTicket+
			   "&idPerRel="+idPerRel +
			   "&registrar="+registrar+
			   "&idCompProd="+idCompProd,
				success: function(data){
					$('#bolTicket').html(data).ready(function () {
						$("#bolTicket").fadeIn('slow');
					});
				}
			}); //termina ajax
		}else if(numBolTicket != 1 && numBolTicket > 0){
			var code = "";
			var tds = '<tr>';
				tds  += '<td style="text-align: center;">'
						+'<input type="text" maxlength="3" size="3" name="" value="'+numBolTicket+'" disabled="disabled">'	
					+'</td>';
				tds +='</tr>';
				$("#tCountBoletas").append(tds);
			if($('#tNoBoletas').length){
				var tds1='<tr>';
				tds1 +='<td style="text-align: center;">'
						+'<input type="text" id="capBolTicket'+numBolTicket+'" maxlength="50" size="14" name="capBolTicket" onchange="validaBodegaTicket(this.value,'+numBolTicket+');">'
						+'<input type="hidden" id="boletaTicketBasculaAnt'+numBolTicket+'" name="" value="">'
						+'<input type="hidden" id="errorBoletaRepetida'+numBolTicket+'" name="" value="0">'
						+'<div id="validaBodegaTicket'+numBolTicket+'"></div>'
					 +'</td>';
				tds1+='</tr>';
				$("#tNoBoletas").append(tds1);
			}
			code = '<script type="text/javascript" src="../js/relaciones.js"></' + 'script>';
			$('body').append(code);
			
			if($('#tFechaEntrada').length){
				msj1 = "capFechaEntrada";
				msj2 = "Entrada(s)";
				var tds2 = '<tr>';
				tds2 +='<td style="text-align: center;"><input type="text" id="capFechaEntrada'+numBolTicket+'" maxlength="10" size="10"" name="capFechaEntrada" readonly="true" onchange="validaFecha(this.value, '+numBolTicket+', &#039;capFechaEntrada&#039;,  &quot;Entrada(s) a la Bodega &quot;);" >'
						+'<img width="16px" src="../images/calendar.gif" id="trgA'+numBolTicket+'" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />'
					+'</td>';
				tds2 += '</tr>';
				$("#tFechaEntrada").append(tds2);	
				code ='<script>	Calendar.setup({inputField: "capFechaEntrada'+numBolTicket+'",' +    
					'ifFormat : "%d/%m/%Y", button: "trgA'+numBolTicket+'", align:"Br",  singleClick : true});</' + 'script>';
				$('body').append(code);
			}
				
			
			if($('#tVolBolTicket').length){
				var tds3 = '<tr>';
				tds3 +='<td style="text-align: center;">'
					  	+'<input type="text" class="cantidad" id="capVolBolTicket'+numBolTicket+'" maxlength="15" size="20" name="capVolBolTicket" onchange="validaVolumenBolTicket(this.value, '+numBolTicket+');" >'
					  +'</td>';
				tds3 +='</tr>';
				$("#tVolBolTicket").append(tds3);
			}
		}
	}
}

function removerEntradaBodega (){
	var numBolTicket = $('#numBolTicket').val();
	if(numBolTicket > 0){
		if($('#tNoBoletas').length){
			$('#tNoBoletas').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}
		if($('#tFechaEntrada').length){
			$('#tFechaEntrada').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}
		
		if($('#tVolBolTicket').length){
			$('#tVolBolTicket').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}
	
		if($('#tCountBoletas').length){
			$('#tCountBoletas').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}		
		numBolTicket = parseInt(numBolTicket) -1;	
		$('#numBolTicket').val(numBolTicket);
		sumarVolumenBolTicket();
	} //numBolTicket > 0
	
	if(numBolTicket == 0){
		$("#removerEntradaBodega").fadeOut('slow');
		$("#guardarEntradaBodega").fadeOut('slow');
	}
}
function agregarFacturaVenta (){	
	var numFacVenta = $('#numFacVenta').val();
	var idPerRel = $('#idPerRel').val();
	var folioProductor = $('#folioProductor').val();
	var registrar = $('#registrar').val();
	var idCompProd = $('#idCompProd').val();
	var rfcR = $('#rfcR').val().toUpperCase();
	if (folioProductor == ""){
		$('#dialogo_1').html('Debe capturar el Folio del Productor');
	   		abrirDialogo();
	   		$('#numFacVenta').val("");
	 		return false;
	}else{		
		numFacVenta = parseInt(numFacVenta) + 1;	
		$('#numFacVenta').val(numFacVenta);
		$("#removerFacturaVenta").fadeIn('slow');
		$("#guardarFacturaVenta").fadeIn('slow');
		if(numFacVenta == 1){
			$.ajax({
				async: false,
				type: "POST",
				url: "facturaVenta",
			   data: "numFacVenta="+numFacVenta+
			   "&idPerRel="+idPerRel +
			   "&registrar="+registrar+
			   "&idCompProd="+idCompProd,
				success: function(data){
					$('#facturaVenta').html(data).ready(function () {
						$("#facturaVenta").fadeIn('slow');
					});
				}
			}); //termina ajax
			$("#capRfcFac1").val(rfcR);
		}if(numFacVenta != 1 && numFacVenta > 0){
			var code = "";
			/*var tds = '<tr>';
				tds  += '<td style="text-align: center;">'
					+'<input type="text" maxlength="3" size="3" name="" value="'+numBolTicket+'" disabled="disabled">'	
					+'</td>';
				tds +='</tr>';
				$("#tCountBoletas").append(tds);*/
			if($('#tNumFacVenta').length){
				var tds1='<tr>';
				tds1 +='<td style="text-align: center;">'
						+'<input type="text" id="capFolioFac'+numFacVenta+'" maxlength="32" size="20" name="capFolioFac" onchange="validaFoliosFactura('+numFacVenta+');">'
						+'<input type="hidden" id="errorFacturasRepetidos'+numFacVenta+'" name="" value="0">'
						+'<input type="hidden" id="errorFacturaByProductor'+numFacVenta+'" name="" value="0">'
						+'<div id="validaFoliosFactura'+numFacVenta+'"></div>'
					 +'</td>';
				tds1+='</tr>';
				$("#tNumFacVenta").append(tds1);
			}
			code = '<script type="text/javascript" src="../js/relaciones.js"></' + 'script>';
			$('body').append(code);
			
			if($('#tFechaEmisionFac').length){
				var tds2 = '<tr>';
				tds2 +='<td style="text-align: center;"><input type="text" id="capFechaEmisionFac'+numFacVenta+'" maxlength="10" size="10"" name="capFechaEmisionFac" readonly="true" onchange="validaFecha(this.value, '+numFacVenta+', &#039;capFechaEmisionFac&#039;,  &quot;Factura de Venta del Grano&quot;);" >'
						+'<img width="16px" src="../images/calendar.gif" id="trgC'+numFacVenta+'" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />'
					+'</td>';
				tds2 += '</tr>';
				$("#tFechaEmisionFac").append(tds2);	
				code ='<script>	Calendar.setup({inputField: "capFechaEmisionFac'+numFacVenta+'",' +    
					'ifFormat : "%d/%m/%Y", button: "trgC'+numFacVenta+'", align:"Br",  singleClick : true});</' + 'script>';
				$('body').append(code);
			}
				
			
			if($('#tRfcFacVenta').length){
				var tds3 = '<tr>';
				var rfcProductor = $('#rfcR').val();
				tds3 +='<td style="text-align: center;">'
					  	+'<input type="text" id="capRfcFac'+numFacVenta+'" maxlength="15" size="15" name="capRfcFac" value="'+rfcProductor+'" disabled="disabled">'
					  +'</td>';
				tds3 +='</tr>';
				$("#tRfcFacVenta").append(tds3);
			}
			
			if($('#tCapVolSolFac').length){
				var tds4 = '<tr>';
				tds4 +='<td style="text-align: center;">'
					  	+'<input type="text" class="volumen" id="capVolSolFac'+numFacVenta+'" maxlength="14" size="20" name="capVolSolFac" onblur="validarVolumen(this.value, &#039;capVolSolFac'+numFacVenta+'&#039;,1);" onchange="calculaVolumenFacturas();" >'
					  +'</td>';
				tds4 +='</tr>';
				$("#tCapVolSolFac").append(tds4);
			}
			
			if($('#tCapVolTotalFac').length){
				var tds4 = '<tr>';
				tds4 +='<td style="text-align: center;">'
					  	+'<input type="text" class="volumen" id="capVolTotalFac'+numFacVenta+'" maxlength="14" size="20" name="capVolTotalFac" onkeyup="calculaTotalVolumenFacturas();" >'
					  +'</td>';
				tds4 +='</tr>';
				$("#tCapVolTotalFac").append(tds4);
			}
			
			if($('#tCapImpSolFac').length){
				var tds5 = '<tr>';
				tds5 +='<td style="text-align: center;">'
					  	+'<input type="text" class="importe" id="capImpSolFac'+numFacVenta+'" maxlength="14" size="20" name="capImpSolFac" onkeyup="calculaTotalImporteFacturas(this.value, '+numFacVenta+');" >'
					  +'</td>';
				tds5 +='</tr>';
				$("#tCapImpSolFac").append(tds5);
			}
			
			if($('#tprecioXTonelada').length){
				var tds6 = '<tr>';
				tds6 +='<td style="text-align: center;">'
					  	+'<input type="text" class="importe" id="precioXTonelada'+numFacVenta+'" maxlength="14" size="20" disabled="disabled" >'
					  +'</td>';
				tds6 +='</tr>';
				$("#tprecioXTonelada").append(tds6);
			}
		}
	}
}

function removerFacturaVenta(){
	var numFacVenta = $('#numFacVenta').val();
	if(numFacVenta > 0){
		if($('#tNumFacVenta').length){
			$('#tNumFacVenta').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}
		if($('#tFechaEmisionFac').length){
			$('#tFechaEmisionFac').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}
		
		if($('#tRfcFacVenta').length){
			$('#tRfcFacVenta').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}
	
		if($('#tCapVolSolFac').length){
			$('#tCapVolSolFac').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}
		if($('#tCapVolTotalFac').length){
			$('#tCapVolTotalFac').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}	
	
		if($('#tCapImpSolFac').length){
			$('#tCapImpSolFac').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}	
		
		if($('#tprecioXTonelada').length){
			$('#tprecioXTonelada').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}	
		numFacVenta = parseInt(numFacVenta) -1;	
		$('#numFacVenta').val(numFacVenta);
		calculaVolumenFacturas();
		calculaTotalVolumenFacturas();
		calculaTotalImporteFacturas();
	} //numFacVenta > 0
	
	if(numFacVenta == 0){
		$("#removerFacturaVenta").fadeOut('slow');
		$("#guardarFacturaVenta").fadeOut('slow');
	}
}



function agregarPagosSinAXC () {
	var numPagosSinAXC = $('#numPagosSinAXC').val();
	var idPerRel = $('#idPerRel').val();
	var folioProductor = $('#folioProductor').val();
	var registrar = $('#registrar').val();
	var idCompProd = $('#idCompProd').val();
	if (folioProductor == ""){
		$('#dialogo_1').html('Debe capturar el Folio del Productor');
	   		abrirDialogo();
	   		$('#numPagosSinAXC').val("");
	 		return false;
	}else{
		numPagosSinAXC = parseInt(numPagosSinAXC) + 1;	
		$('#numPagosSinAXC').val(numPagosSinAXC);
		$("#removerPagosSinAXC").fadeIn('slow');
		$("#guardarPagosSinAXC").fadeIn('slow');
		if(numPagosSinAXC == 1){
			$.ajax({
				async: false,
				type: "POST",
				url: "pagosSinAXC",
				 data: "numPagosSinAXC="+numPagosSinAXC+
				   "&idPerRel="+idPerRel+
				   "&registrar="+registrar+
				   "&idCompProd="+idCompProd,
				success: function(data){
					$('#pagosSinAXC').html(data).ready(function () {
						$("#pagosSinAXC").fadeIn('slow');
					});
				}
			}); //termina ajax
		}else if(numPagosSinAXC != 1 && numPagosSinAXC > 0){
			var code = "";
			/*var tds = '<tr>';
				tds  += '<td style="text-align: center;">'
						+'<input type="text" maxlength="3" size="3" name="" value="'+numPagosSinAXC+'" disabled="disabled">'	
					+'</td>';
				tds +='</tr>';
				$("#tCountBoletas").append(tds);*/
			if($('#tCapIdTipoDocPago').length){
				var tds1='<tr>';
				tds1 +='<td style="text-align: center;" id="tdCapIdTipoDocPago'+numPagosSinAXC+'">'
					 +''
					 +'</td>';
				tds1+='</tr>';
				$("#tCapIdTipoDocPago").append(tds1);
				$('#capIdTipoDocPago1').clone().attr('id', 'capIdTipoDocPago'+numPagosSinAXC).attr('onchange', 'validaTipoDocPago('+numPagosSinAXC+')').appendTo('#tdCapIdTipoDocPago'+numPagosSinAXC);
				$('#capIdTipoDocPago'+numPagosSinAXC).val(-1);
			}
			code = '<script type="text/javascript" src="../js/relaciones.js"></' + 'script>';
			$('body').append(code);
		
			if($('#tCapBancoIdSin').length){
				var tds2 = '<tr>';
				tds2 +='<td style="text-align: center;" id="tdCapBancoIdSin'+numPagosSinAXC+'">'
					  	+''
					  +'</td>';
				tds2	 +='</tr>';
				$("#tCapBancoIdSin").append(tds2);
				$('#capBancoIdSin1').clone().attr('id', 'capBancoIdSin'+numPagosSinAXC).appendTo('#tdCapBancoIdSin'+numPagosSinAXC);
				$('#capBancoIdSin'+numPagosSinAXC).val(-1);
			}
			
			if($('#tCapFechaDocPago').length){
				var tds3 = '<tr>';
				tds3 +='<td style="text-align: center;"><input type="text" id="capFechaDocPago'+numPagosSinAXC+'" maxlength="10" size="10"" name="capFechaDocPago" readonly="true" onchange="validaFecha(this.value, '+numPagosSinAXC+', &#039;capFechaDocPago&#039;,  &quot;Pago al Productor sin AXCo&quot;);" >'
						+'<img width="16px" src="../images/calendar.gif" id="trgD'+numPagosSinAXC+'" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha" />'
					+'</td>';
				tds3 += '</tr>';
				$("#tCapFechaDocPago").append(tds3);	
				code ='<script>	Calendar.setup({inputField: "capFechaDocPago'+numPagosSinAXC+'",' +    
					'ifFormat : "%d/%m/%Y", button: "trgD'+numPagosSinAXC+'", align:"Br",  singleClick : true});</' + 'script>';
				$('body').append(code);
			}
			
			if($('#tCapFolioDocPago').length){
				var tds4 = '<tr>';
				tds4 +='<td style="text-align: center;">'
					  	+'<input type="text"  id="capFolioDocPago'+numPagosSinAXC+'" maxlength="15" size="14" name="capFolioDocPago" onblur="validaFolioDocPago(this.value, &#039;capFolioDocPago'+numPagosSinAXC+'&#039;);" >'
					  +'</td>';
				tds4 +='</tr>';
				$("#tCapFolioDocPago").append(tds4);
			}
			
			if($('#tCapImpDocPago').length){
				var tds5 = '<tr>';
				tds5 +='<td style="text-align: center;">'
					  	+'<input type="text" class="cantidad" id="capImpDocPago'+numPagosSinAXC+'" maxlength="15" size="20" name="capImpDocPago" onblur="validarImporte(this.value,  &#039;capImpDocPago'+numPagosSinAXC+'&#039;, 1);" onchange="calculaTotalImportePagos();" >'
					  +'</td>';
				tds5 +='</tr>';
				$("#tCapImpDocPago").append(tds5);
			}
		}
	}
}

function removerPagosSinAXC(){
	var numPagosSinAXC = $('#numPagosSinAXC').val();
	if(numPagosSinAXC > 0){
		if($('#tCapIdTipoDocPago').length){
			$('#tCapIdTipoDocPago').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}
		if($('#tCapBancoIdSin').length){
			$('#tCapBancoIdSin').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}
		
		if($('#tCapFechaDocPago').length){
			$('#tCapFechaDocPago').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}
	
		if($('#tCapFolioDocPago').length){
			$('#tCapFolioDocPago').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}
		if($('#tCapImpDocPago').length){
			$('#tCapImpDocPago').each(function(){
			    if($('tbody', this).length > 0){
			        $('tbody tr:last', this).remove();
			    }else {
			        $('tr:last', this).remove();
			    }
			});
		}
		numPagosSinAXC = parseInt(numPagosSinAXC) -1;	
		$('#numPagosSinAXC').val(numPagosSinAXC);
		
	} //numFacVenta > 0
	
	if(numPagosSinAXC == 0){
		$("#removerPagosSinAXC").fadeOut('slow');
		$("#guardarPagosSinAXC").fadeOut('slow');
	}
}

function chkCamposRegistroCartaAdhesion(){

	var folioCartaAdhesion = $('#folioCartaAdhesion').val();
	if(folioCartaAdhesion == null || folioCartaAdhesion == ""){
		$('#dialogo_1').html('Capture el Folio de la Carta de Adhesión');
		abrirDialogo();
	    return false;
	}
	
	var selectedItems = new Array();
	$(".ck:checkbox:checked").each(function() {selectedItems.push($(this).val());});
	if (selectedItems.length == 0){ 
		$('#dialogo_1').html('Por favor seleccione los registros para la asignación de carta.');
		abrirDialogo();
	    return false;
	}
}

function chkCamposRegistroRelCompras(){
	var patron = /^(xlsx|xls)$/;
	var doc = $('#doc').val();
	if(doc == null || doc == ""){
		$('#dialogo_1').html('Seleccione el archivo de la relación de compras');
		abrirDialogo();
		return false;
	}else{
		ext = doc.toLowerCase().substring(doc.lastIndexOf(".")+1);
		if(!ext.match(patron)){
			$('#dialogo_1').html('Archivo no permitido, solo puede cargar archivos con extensiones xlsx y xls');
	   		abrirDialogo();
			return false;
		}
	}//End doc

}


function verificaSiRecuperaPeriodo (idCriterio){
	var aplicaAdendum = "";
	if(idCriterio != -1){
		var folioCartaAdhesion = $('#folioCartaAdhesion').val();
		
		if(idCriterio == 20 || idCriterio == 21 || idCriterio == 15){
			$("#pideAdendum").css("display", "inline");
		}else{
			$("#pideAdendum").css("display", "none");
		}
		if(idCriterio != 20 && idCriterio != 21 && idCriterio != 15 && idCriterio != 14 ){
			ajaxVerificaSiRecuperaPeriodo(idCriterio, folioCartaAdhesion, false);
			$("#recuperaContrato").fadeOut('slow');
			$("#recuperaCamposFactura").fadeOut('slow');
		}
		if(idCriterio == 14){
			//Recupera los campos para validacion de la factura
			ajaxRecuperaCamposFactura(idCriterio, folioCartaAdhesion);
			$("#verificaSiRecuperaPeriodo").fadeOut('slow');
		}		
		//$("#logPrevalidacion").fadeOut('slow');
	}else{
		$("#verificaSiRecuperaPeriodo").fadeOut('slow');
		$("#recuperaContrato").fadeOut('slow');
		$("#recuperaCamposFactura").fadeOut('slow');
	}
}

function ajaxVerificaSiRecuperaPeriodo (idCriterio, folioCartaAdhesion, aplicaAdendum){
	$.ajax({
		async: false,
		type: "POST",
		url: "verificaSiRecuperaPeriodo",
	   data: "idCriterio="+idCriterio+
	   		"&folioCartaAdhesion="+folioCartaAdhesion+
	   		"&aplicaAdendum="+aplicaAdendum,
		success: function(data){
			$('#verificaSiRecuperaPeriodo').html(data).ready(function () {
				$("#verificaSiRecuperaPeriodo").fadeIn('slow');
			});
		}
	}); //termina ajax
}
function ajaxRecuperaCamposFactura(idCriterio, folioCartaAdhesion){
	$.ajax({
		async: false,
		type: "POST",
		url: "recuperaCamposFactura",
	   data: "idCriterio="+idCriterio+
	   		"&folioCartaAdhesion="+folioCartaAdhesion,
		success: function(data){
			$('#recuperaCamposFactura').html(data).ready(function () {
				$("#recuperaCamposFactura").fadeIn('slow');
			});
		}
	}); //termina ajax
}

function activaPeriodoPorAdendun(){	
	
//	if($('#aplicaAdendum').is(":checked")){
	if($('input:radio[name=aplicaAdendum]').is(":checked")){
		var aplicaAdendum = $("input:radio[name=aplicaAdendum]:checked").val();
		var folioCartaAdhesion = $('#folioCartaAdhesion').val();
		if(aplicaAdendum == 1){		
			ajaxRecuperaContrato(folioCartaAdhesion);
			$("#respuestaPeriodoContratoComplemento").fadeOut('slow');
		}else{			
			ajaxRecuperaPeriodoComplemento();
			$("#respuestaPeriodoContratoAdendum").fadeOut('slow');
		}		
	}else{
		$("#recuperaPeriodoAuditor").fadeOut('slow');
		$("#respuestaPeriodoContratoAdendum").fadeOut('slow');
	}
}

function ajaxRecuperaContrato(folioCartaAdhesion){
	$.ajax({
		async: false,
		type: "POST",
		url: "respuestaPeriodoContratoAdendum",
	   data: "folioCartaAdhesion="+folioCartaAdhesion,
		success: function(data){
			$('#respuestaPeriodoContratoAdendum').html(data).ready(function () {
				$("#respuestaPeriodoContratoAdendum").fadeIn('slow');
			});
		}
	}); //termina ajax
}


function ajaxRecuperaPeriodoComplemento(){
	$.ajax({
		async: false,
		type: "POST",
		url: "respuestaPeriodoContratoComplemento",
		success: function(data){
			$('#respuestaPeriodoContratoComplemento').html(data).ready(function () {
				$("#respuestaPeriodoContratoComplemento").fadeIn('slow');
			});
		}
	}); //termina ajax
}

function chkCamposPrevalidacion (){	
	var grupoCriterio = $('#grupoCriterio').val();
	if(grupoCriterio == -1){
		$('#dialogo_1').html('Seleccione el criterio a validar');
		abrirDialogo();
	    return false;
	}		
	if(grupoCriterio =="Boletas" || grupoCriterio =="Facturas" || grupoCriterio =="Pagos"){

		if ($('input:radio[name=pideTipoPeriodo]').length){			
			if($('input:radio[name=pideTipoPeriodo]').is(":checked")){
				var pideTipoPeriodo = $('input:radio[name=pideTipoPeriodo]:checked').val();				
				if(pideTipoPeriodo == 1 || pideTipoPeriodo == 2){//PERIODO POR LINEAMIENTO (1) : CONTRATO (2)
					if($('#fechaInicio').length){
						var fechaInicio = $('#fechaInicio').val();
						if(fechaInicio == null || fechaInicio ==""){
							$('#dialogo_1').html('Debe capturar la fecha de inicio del periodo');
							abrirDialogo();
						 	return false;
						}
					}
					if($('#fechaInicioCom').length){
						var fechaInicioCom = $('#fechaInicioCom').val();
						if(fechaInicioCom == null || fechaInicioCom ==""){
							$('#dialogo_1').html('Debe capturar la fecha de inicio del periodo');
							abrirDialogo();
						 	return false;
						}
					}
					
					if($('#fechaFinCom').length) {
						var fechaFinCom = $('#fechaFinCom').val();
						if(fechaFinCom == null || fechaFinCom ==""){
							$('#dialogo_1').html('Debe capturar la fecha de Fin del periodo');
							abrirDialogo();
						 	return false;
						}
					}
					
					if($('#fechaFin').length){
						var fechaFin = $('#fechaFin').val();
						if(fechaFin == null || fechaFin ==""){
							$('#dialogo_1').html('Debe capturar la fecha de inicio del periodo');
							abrirDialogo();
						 	return false;
						}
					}//end pide periodo 1() o 2 ()		
				}else{
					if($('input:radio[name=aplicaAdendum]').is(":checked")){
						if($('#fechaInicio').length){
							var fechaInicio = $('#fechaInicio').val();
							if(fechaInicio == null || fechaInicio ==""){
								$('#dialogo_1').html('Debe capturar la fecha de inicio del periodo');
								abrirDialogo();
							 	return false;
							}
						}
						
						if($('#fechaFin').length) {
							var fechaFin = $('#fechaFin').val();
							if(fechaFin == null || fechaFin ==""){
								$('#dialogo_1').html('Debe capturar la fecha de Fin del periodo');
								abrirDialogo();
							 	return false;
							}
						}
						
					
					}
				}			
			}else{
				
				$('#dialogo_1').html('Por favor seleccione el tipo de periodo');
				abrirDialogo();
			 	return false;
			}
			
			
		}// end if ($('input:radio[name=pideTipoPeriodo]').length){
	}
	
//	if(idCriterio == 15){
//		//Checa que se haya capturado las fechas del contrato si se marco adendum
//		if($('#aplicaAdendum').is(":checked")){
//			var numContratos = $('#numContratos').val();
//			var i = 0;
//			for (i=1; i<parseInt(numContratos)+1;i++){
//				if($('#fechaContrato'+i).val()==null || $('#fechaContrato'+i).val()== ""){
//					$('#dialogo_1').html('Capture la fecha en el registro ['+i+'] del contrato');
//					abrirDialogo();
//					return false;
//				}
//			}
//			
//		}
//	}else if(idCriterio == 21){
//		if($('#aplicaAdendum').is(":checked")){
//			var fechaInicio = $('#fechaInicio').val();
//			var fechaFin = $('#fechaFin').val();
//			if(fechaInicio == null || fechaInicio ==""){
//				$('#dialogo_1').html('Debe capturar la fecha de inicio del periodo');
//				abrirDialogo();
//			 	return false;
//			}
//			if(fechaFin == null || fechaFin ==""){
//				$('#dialogo_1').html('Debe capturar la fecha final del periodo');
//				abrirDialogo();
//			 	return false;
//			}
//		}
//	}
}

function chkCamposBitacoraRelCompras (){
	var fechaDeReporte = $('#fechaDeReporte').val();
	if(fechaDeReporte == null || fechaDeReporte ==""){
		$('#dialogo_1').html('Debe capturar la fecha del reporte');
		abrirDialogo();
	 	return false;
	}
	if (confirm('¿Esta seguro de generar el reporte de los cruces previamente validados?')){}else{return false;}
}
function recuperaVistaValidacion (){
	var grupoCriterio = $('#grupoCriterio').val();
	if(grupoCriterio == "Boletas" || grupoCriterio == "Facturas" || grupoCriterio == "Pagos"){	
		$("#pregSiPidePeriodo").fadeIn('slow');
		$("#recuperaContrato").fadeOut('slow');
		$("#recuperaCamposFactura").fadeOut('slow');
		$("#logPrevalidacion").fadeOut('slow');
	}else{
		$("#pregSiPidePeriodo").fadeOut('slow');
		$("#recuperaContrato").fadeOut('slow');
		$("#recuperaCamposFactura").fadeOut('slow');
		$("#logPrevalidacion").fadeOut('slow');
		
	}
}


function verificarQueCriterioPeriodoAplicar(){
	var grupoCriterio = $('#grupoCriterio').val();	
	var folioCartaAdhesion = $('#folioCartaAdhesion').val();		
	$("#logPrevalidacion").fadeOut('slow');
	$.ajax({
		async: false,
		type: "POST",
		url: "verificarQueCriterioPeriodoAplicar",
	   data: "grupoCriterio="+grupoCriterio+
	   		"&folioCartaAdhesion="+folioCartaAdhesion,	   		  
		success: function(data){
			$('#verificarQueCriterioPeriodoAplicar').html(data).ready(function () {
				$("#verificarQueCriterioPeriodoAplicar").fadeIn('slow');
			});
		}
	}); //termina ajax	
	
	
}
function ocultarVista (){	
	$("#respuestaGeneracionBitacora").fadeOut('slow');	
}


function activaPeriodo (){
	var pideTipoPeriodo = $('input:radio[name=pideTipoPeriodo]:checked').val();
	var folioCartaAdhesion = $('#folioCartaAdhesion').val();
	if(pideTipoPeriodo == 1){//LINEAMIENTO				
	}else if(pideTipoPeriodo == 2){//CONTRATO AXC
		$("#pideAdendum").fadeIn('slow');
		$("#recuperaPeriodoAuditor").fadeOut('slow');	
		$('input[name=aplicaAdendum]').removeAttr('checked');
		//ajaxVerificaSiRecuperaPeriodoPorGrupo(grupoCriterio,folioCartaAdhesion,1);
	}else if(pideTipoPeriodo == 3){//DICTAMEN AUDITOR
		$("#pideAdendum").fadeOut('slow');
		$("#respuestaPeriodoContratoAdendum").fadeOut('slow');
		$("#respuestaPeriodoContratoComplemento").fadeOut('slow');
		//Recuperar fechas del dictamen del auditor
		$.ajax({
			async: false,
			type: "POST",
			url: "recuperaPeriodoAuditor",
		   data: "&folioCartaAdhesion="+folioCartaAdhesion,  
			success: function(data){
				$('#recuperaPeriodoAuditor').html(data).ready(function () {
					$("#recuperaPeriodoAuditor").fadeIn('slow');
				});
			}
		}); //termina ajax
		
 	}
}

function ajaxVerificaSiRecuperaPeriodoPorGrupo(grupoCriterio, folioCartaAdhesion, aplicaPeriodoLineamiento){
	$.ajax({
		async: false,
		type: "POST",
		url: "verificaSiRecuperaPeriodoPorGrupo",
	   data: "grupoCriterio="+grupoCriterio+
	   		"&folioCartaAdhesion="+folioCartaAdhesion+
	   		"&aplicaPeriodoLineamiento="+aplicaPeriodoLineamiento,  
		success: function(data){
			$('#verificaSiRecuperaPeriodo').html(data).ready(function () {
				$("#verificaSiRecuperaPeriodo").fadeIn('slow');
			});
		}
	}); //termina ajax
}
