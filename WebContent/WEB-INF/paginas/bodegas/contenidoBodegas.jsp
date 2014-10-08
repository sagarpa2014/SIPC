<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value="/js/bodegas.js" />"></script>
<div id="error"></div>
<script>
$(document).ready(function(){  
	     $('form').ajaxForm({
	    	 beforeSubmit: function() {			   
			    	$("#espera").fadeIn('slow', function() {
			   		 	$("#img-espera").fadeIn('slow');
			    	});
			     },
			     success: function(data) {
			    	var $response=$(data);
			    	 var errorSistema = $response.filter('#errorSistema').text();
			    	 if(errorSistema != 0){//si hubo error
			    		 $('#error').html(data).ready(function () {		    		
								$("#img-espera").fadeOut("slow", function() {
							   		  $("#espera").fadeOut("slow");
							   	});   		
					    	});   
			    	 }else{
			    		 $('#detalleBodegas').html(data).ready(function () {		    		
								$("#img-espera").fadeOut("slow", function() {
							   		  $("#espera").fadeOut("slow");
							   	});  
					    	}); 
			    	 }
			    	$("#errorSistema").fadeOut("slow");
			     }
		     });
	}); 
</script>
<div id="detalleBodegas">
	<s:include value="detalleBodegas.jsp"/>
</div>

