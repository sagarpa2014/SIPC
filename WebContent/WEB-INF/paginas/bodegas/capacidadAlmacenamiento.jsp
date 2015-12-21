<%@taglib uri="/struts-tags" prefix="s"%>
<fieldset>	
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>Capacidad bodega:</label>
		<s:if test="%{bodegasV.capacidadBodega!=null}">
			<s:textfield id="capacidadBodega" name="capacidadBodega" maxlength="11" size="30" value="%{getText('volumenSinComas',{bodegasV.capacidadBodega})}"  onchange="getTotalAlmacenamiento(this.value,'capacidadBodega', 1 )"/>
		</s:if>
		<s:else>
			<s:textfield id="capacidadBodega" name="capacidadBodega" maxlength="11" size="30" value="%{}" onchange="getTotalAlmacenamiento(this.value,'capacidadBodega', 1 )"/>
		</s:else>		
	</div>
	<div class="izquierda">&nbsp;Ton&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left1"><span class="requerido">*</span>Tejaban:</label>
		<s:if test="%{bodegasV.capacidadTechada!=null}">
			<s:textfield id="capacidadTechada" name="capacidadTechada" maxlength="11" size="30" value="%{getText('volumenSinComas',{bodegasV.capacidadTechada})}"  onchange="getTotalAlmacenamiento(this.value,'capacidadTechada', 1 )"/>
		</s:if>
		<s:else>
			<s:textfield id="capacidadTechada" name="capacidadTechada" maxlength="11" size="30" value="%{}" onchange="getTotalAlmacenamiento(this.value,'capacidadTechada', 1 )"/>
		</s:else>		
	</div>
	<div class="izquierda">&nbsp;Ton</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>Capacidad Silos:</label>
		<s:if test="%{bodegasV.capacidadSilos!=null}">
			<s:textfield id="capacidadSilos" name="capacidadSilos" maxlength="11" size="30" value="%{getText('volumenSinComas',{bodegasV.capacidadSilos})}"  onchange="getTotalAlmacenamiento(this.value,'capacidadSilos', 1 )"/>
		</s:if>
		<s:else>
			<s:textfield id="capacidadSilos" name="capacidadSilos" maxlength="11" size="30" value="%{}" onchange="getTotalAlmacenamiento(this.value,'capacidadSilos', 1 )"/>
		</s:else>
	</div>
	<div class="izquierda">&nbsp;Ton&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left1"><span class="requerido">*</span>Intemperie:</label>
		<s:if test="%{bodegasV.capacidadIntemperie!=null}">
			<s:textfield  id="intemperie"  name="intemperie" maxlength="11" size="30" value="%{getText('volumenSinComas',{bodegasV.capacidadIntemperie})}" onchange="getTotalAlmacenamiento(this.value,'intemperie', 1 )"/>
		</s:if>
		<s:else>
			<s:textfield  id="intemperie"  name="intemperie" maxlength="11" size="30" value="%{}" onchange="getTotalAlmacenamiento(this.value,'intemperie', 1 )"/>
		</s:else>
	</div>
	<div class="izquierda">&nbsp;Ton</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>No. Silos:</label>
		<s:textfield  id="noSilos" name="noSilos" maxlength="4" size="30"  value="%{getText('contador',{bodegasV.noSilos})}" onchange="validarDigitos(this.value,'noSilos', '1' )"/>
	</div>
	<div class="izquierda">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left1"><span class="requerido">*</span><font class="arial12bold">TOTAL ALMACENAMIENTO:</font></label>
		<s:if test="%{bodegasV.totalAlmacenamiento!=null}">
			<s:textfield id="totalAlmacenamiento" name="totalAlmacenamiento" maxlength="11" size="30" value="%{getText('volumenSinComas',{bodegasV.totalAlmacenamiento})}" disabled="true"/>
		</s:if>
		<s:else>
			<s:textfield id="totalAlmacenamiento" name="totalAlmacenamiento" maxlength="11" size="30" value="%{bodegasV.totalAlmacenamiento}" disabled="true"/>
		</s:else>
		
	</div>
	<div class="izquierda">&nbsp;Ton</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2">RECEPCION:</label>
	</div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>No. de rampas:</label>
		<s:textfield id="numRampas" name="numRampas" maxlength="4" size="10"  value="%{getText('contador',{bodegasV.numRampas})}" onchange="validarDigitos(this.value,'numRampas', '1' )"/>
	</div>
	<div class="izquierda">&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>Capacidad:</label>
		<s:if test="%{bodegasV.capacidadRampas!=null}">
			<s:textfield id="capacidadRampas" name="capacidadRampas" maxlength="11" size="30" value="%{getText('volumenSinComas',{bodegasV.capacidadRampas})}"  onchange="validarVolumen(this.value,'capacidadRampas', '1' )"/>
		</s:if>
		<s:else>
			<s:textfield id="capacidadRampas" name="capacidadRampas" maxlength="11" size="30" value="%{}" onchange="validarVolumen(this.value,'capacidadRampas', '1' )"/>
		</s:else>
	  		
	</div>	
	<div class="izquierda">&nbsp;Ton.24/Hrs.</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2">SECADO:</label>
	</div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>No. de secadoras:</label>
		<s:textfield id="numSecadoras"  name="numSecadoras" maxlength="4" size="10"  value="%{getText('contador',{bodegasV.numSecadoras})}" onchange="validarDigitos(this.value,'numSecadoras', '1' )"/>
	</div>
	<div class="izquierda">&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>Capacidad:</label>
		<s:if test="%{bodegasV.capacidadSecado!=null}">
			<s:textfield  id="capacidadSecado" name="capacidadSecado" maxlength="11" size="30" value="%{getText('volumenSinComas',{bodegasV.capacidadSecado})}"  onchange="validarVolumen(this.value,'capacidadSecado', '1' )"/>
		</s:if>
		<s:else>
			<s:textfield  id="capacidadSecado" name="capacidadSecado" maxlength="11" size="30" value="%{bodegasV.capacidadSecado}" onchange="validarVolumen(this.value,'capacidadSecado', '1' )"/>
		</s:else>		
	</div>
	<div class="izquierda">&nbsp;Ton.24/Hrs.</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2">EMBARQUE:</label>
	</div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>No. de cargas:</label>
		<s:textfield id="numCargas"  name="numCargas" maxlength="4" size="10" value="%{getText('contador',{bodegasV.numCargas})}" onchange="validarDigitos(this.value,'numCargas', '1' )"/>
	</div>
	<div class="izquierda">&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>Capacidad:</label>
		<s:if test="%{bodegasV.capacidadCargas!=null}">
			<s:textfield id="capacidadCarga" name="capacidadCarga" maxlength="30" size="30" value="%{getText('volumenSinComas',{bodegasV.capacidadCargas})}" onchange="validarVolumen(this.value,'capacidadCarga', '1' )"/>
		</s:if>
		<s:else>
			<s:textfield id="capacidadCarga" name="capacidadCarga" maxlength="30" size="30" value="%{}" onchange="validarVolumen(this.value,'capacidadCarga', '1' )"/>
		</s:else>		
	</div>
	<div class="izquierda">&nbsp;Ton.24/Hrs.</div>
	<div class="clear"></div>	
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>B&Aacute;SCULA DE PISO:</label>
		<s:select id="piso" name="piso" headerKey="-1" headerValue="-- Seleccione una opción --"	list="#{'S':'SI', 'N':'NO'}"  onchange="" value="%{bodegasV.piso}"/>
	</div>
	<div class="izquierda">&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>Capacidad:</label>
		<s:if test="%{bodegasV.capacidadPiso!=null}">
			<s:textfield  id="capacidadPiso" name="capacidadPiso" maxlength="11" size="30" value="%{getText('volumenSinComas',{bodegasV.capacidadPiso})}" onchange="validarVolumen(this.value,'capacidadPiso', '1')"/>
		</s:if>
		<s:else>
			<s:textfield  id="capacidadPiso" name="capacidadPiso" maxlength="11" size="30"  onchange="validarVolumen(this.value,'capacidadPiso', '1')"/>
		</s:else>
	</div>
	<div class="izquierda">&nbsp;Ton.</div>
	<div class="clear"></div>	
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>B&Aacute;SCULA CAMIONERA:</label>
		<s:select id="camionera" name="camionera" headerKey="-1" headerValue="-- Seleccione una opción --"	list="#{'S':'SI', 'N':'NO'}"  onchange="" value="%{bodegasV.camionera}"/>
	</div>  	
	<div class="izquierda">&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>Capacidad:</label>
		<s:if test="%{bodegasV.capacidadCamionera!=null}">
			<s:textfield  id="capacidadCamionera" name="capacidadCamionera" maxlength="11" size="30"  value="%{getText('volumenSinComas',{bodegasV.capacidadCamionera})}"  onchange="validarVolumen(this.value,'capacidadCamionera', '1')"/>
		</s:if>
		<s:else>
			<s:textfield  id="capacidadCamionera" name="capacidadCamionera" maxlength="11" size="30" value="%{}" onchange="validarVolumen(this.value,'capacidadCamionera', '1')"/>
		</s:else>		
	</div>
	<div class="izquierda">&nbsp;Ton.</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>B&Aacute;SCULA FFCC:</label>
		<s:select id="ffcc" name="ffcc" headerKey="-1" headerValue="-- Seleccione una opción --"	list="#{'S':'SI', 'N':'NO'}"  onchange="" value="%{bodegasV.ffcc}"/>
	</div>
	<div class="izquierda">&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>Capacidad</label>
		<s:if test="%{bodegasV.capacidadFfcc!=null}">
			<s:textfield id="capacidadFfcc" name="capacidadFfcc"  maxlength="11" size="30" value="%{getText('volumenSinComas',{bodegasV.capacidadFfcc})}" onchange="validarVolumen(this.value,'capacidadFfcc', '1')"/>
		</s:if>
		<s:else>
			<s:textfield id="capacidadFfcc" name="capacidadFfcc"  maxlength="11" size="30" value="%{}" onchange="validarVolumen(this.value,'capacidadFfcc', '1')"/>
		</s:else>
		
	</div>
	<div class="izquierda">&nbsp;Ton.</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>ESPUELA DE FFCC:</label>
		<s:select id="espuelaFFCC" name="espuelaFFCC" headerKey="-1" headerValue="-- Seleccione una opción --"	list="#{'S':'SI', 'N':'NO'}"  onchange="" value="%{bodegasV.espuelaFfcc}"/>
	</div>
	<div class="izquierda">&nbsp;&nbsp;&nbsp;</div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>Capacidad:</label>
		<s:if test="%{bodegasV.capacidadEspuela!=null}">
			<s:textfield id="capacidadEspuela" name="capacidadEspuela" maxlength="11" size="30" value="%{getText('volumenSinComas',{bodegasV.capacidadEspuela})}"  onchange="validarVolumen(this.value,'capacidadEspuela', '1')"/>
		</s:if>
		<s:else>
			<s:textfield id="capacidadEspuela" name="capacidadEspuela" maxlength="11" size="30" value="%{bodegasV.capacidadEspuela}" onchange="validarVolumen(this.value,'capacidadEspuela', '1')"/>
		</s:else>
		
	</div>
	<div class="izquierda">&nbsp;Furgones y/o tolvas/24 hrs.</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left2"><span class="requerido">*</span>Fecha de Calibraci&oacute;n:</label>
		<s:textfield id="fechaCalibracion" name="fechaCalibracion" maxlength="10" size="10" cssClass="fecha" value="%{getText('fecha1',{bodegasV.fechaCalibracion})}"/>  
		<s:if test="registrar!=2">
			<img width="16px" src="../images/calendar.gif" id="trgFechaCalibracion" style="cursor: pointer;" alt="Seleccione la fecha" border="0" class="dtalign" title="Seleccione la fecha"/>
			<script type="text/javascript">
				Calendar.setup({
					inputField     :    'fechaCalibracion',     
					ifFormat       :    "%d/%m/%Y",     
					button         :    'trgFechaCalibracion',  
					align          :    "Br",           
					singleClick    :    true
					});
			</script>
		</s:if>
	</div>		  	
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left1"><span class="requerido">*</span>Existe equipo de an&aacute;lisis (S/N):</label>
		<s:select id="equipoAnalisis" name="equipoAnalisis" headerKey="-1" headerValue="-- Seleccione una opción --"	list="#{'S':'SI', 'N':'NO'}"  onchange="" value="%{bodegasV.equipoAnalisis}"/>
	</div>  
	<div class="izquierda">&nbsp;&nbsp;&nbsp;</div>	
	<div class="izquierda">
		<label class="left1"><span class="requerido">*</span>Aplica normas de calidad (S/N):</label>
		<s:select id="aplicaNormasCalidad" name="aplicaNormasCalidad" headerKey="-1" headerValue="-- Seleccione una opción --"	list="#{'S':'SI', 'N':'NO'}"  onchange="" value="%{bodegasV.aplicaNormasCalidad}"/>
	</div>
	<div class="clear"></div>
	<div class="izquierda">
		<label class="left1">Observaciones:</label>
  		<s:textfield id="observacionCapBodega" name="observacionCapacidadBodega"  maxlength="120" size="120" value="%{bodegasV.observaciones}"/>
	</div>	  			
</fieldset>	