<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/inscripcion.js" />"></script>
<s:select id="c%{count}" cssClass="a" name="capCultivo" list="lstCultivo" listKey="idCultivo" listValue="%{cultivo}" headerKey="-1" headerValue="-- Seleccione una opción --" tabindex="0" value="%{idCultivo}" onchange="ok(this.value, %{count});" />										
<script>					

function ok(idCultivo, count){
	if(idCultivo == -1){
		return false;
	}
	var idInicializacionEsquema = $('#idInicializacionEsquema').val();	
	var idEstado = $('#e'+count).val();

	
	$.ajax({
		   async: false,
		   type: "POST",
		   url: "recuperaVariedadByCultivoEdoAsigCA",
		   data: "idEstado="+idEstado+
		   		"&count="+count+
		   		"&idInicializacionEsquema="+idInicializacionEsquema,	
		   success: function(data){
				$("#variedad"+count).html(data).ready(function () {
					
				});
		   }
		});//fin ajax
	
	//$.ajax({
		   //async: false,
		  // type: "POST",
		   //url: "recuperaVariedadByCultivoEdoAsigCA",
		   //data: "idCultivo="+idCultivo+
		   	//	"&idEstado="+idEstado+
		   	//	"&count="+count+
		   	//	"&idInicializacionEsquema="+idInicializacionEsquema,	
		   //success: function(data){
			//	$("#variedad"+count).html(data).ready(function () {
					
				//});
		   //}
		//});//fin ajax
}
</script>					