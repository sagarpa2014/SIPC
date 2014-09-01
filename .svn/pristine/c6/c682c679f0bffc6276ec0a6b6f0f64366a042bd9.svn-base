$(document).ready(function(){
	$.ajaxSetup({
		'beforeSend' : function(xhr) {
			try{
				xhr.overrideMimeType('text/html; charset=iso-8859-1');
			}catch(e){}
		}});
});

function chkFileAtentaNota(){
	var numCampos = $('#numCampos').val();
	var estatusId = -1;
	var val = null;
	var ext = "";
	var patron = /^(jpg|png|pdf)$/;
	for (var i=1;i<=parseInt(numCampos);i++){
		estatusId = $('#estatusId'+i).val();
		val = $('#f'+i).val();
		console.log( "estatusId "+estatusId+" val "+val);
		//if (estatusId == 1 && (val == null || val == "")){
   			//$('#dialogo_1').html('Se intento guardar un pago Autorizado sin Atenta Nota en el registro '+i+ ' Favor de Verificar');
	   		//abrirDialogo();
	   		//return false;
		//}
		if(estatusId == 1 && !(val == null || val == "")){
			ext = val.toLowerCase().substring(val.lastIndexOf(".")+1);
			if(!ext.match(patron)){
				$('#dialogo_1').html('Extensión no permitida en registro número '+ i +', solo puede cargar archivos con extensiones jpg, png, ó pdf');
		   		abrirDialogo();
				return false;
			}
		}
	}
	
}

function selectEstatus(idEstatus, count){
	
	var idPago = $('#v'+count).val();
	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "recuperaFileByEstatus",
		   data: "estatus="+idEstatus+
		   		 "&count="+count+
		   		 "&idPago="+idPago,	
		   success: function(data){
				$("#contenedorFile"+count).html(data).ready(function () {
					//$("#prueba").fadeIn('slow');
					
				});
		   }
		});//fin ajax
}

function cambiaValor(idPago, count){
	
	$('#p'+count).val(idPago);
	
}