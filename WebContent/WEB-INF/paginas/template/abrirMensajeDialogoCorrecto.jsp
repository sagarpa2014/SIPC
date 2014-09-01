<%@taglib uri="/struts-tags" prefix="s"%>
<script>
      // increase the default animation speed to exaggerate the effect
      $.fx.speeds._default = 300;
      $(function() {
          $( "#dialogo" ).dialog({
              autoOpen: true,
              resizable:false,
              show: "slide",
              hide: "fade",
              width:300,
		modal: true,
		buttons: {
			Aceptar: function() {
				$( this ).dialog( "close" );
			}
		}
          });
  
          $( "#open" ).click(function() {
              $( "#dialogo" ).dialog( "open" );
              return false;
          });
	
	$('.ui-widget-overlay').live('click', function() {
           $('#dialogo').dialog( "close" );
          });
      });
</script>
<div id="dialogo" title="" class="cuadroSatisfactorio" style="color: #405E28;font-weight: bold;">
	<s:property escape="" value="%{cuadroSatisfactorio}"/>
</div>

 