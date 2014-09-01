<%@taglib uri="/struts-tags" prefix="s"%>
<s:hidden id="fechaFirmaCA%{count}" name="" value="%{getText('fecha1',{fechaFirmaCartaAdhesion})}"/>
<s:textfield id="" value="%{getText('fecha',{fechaFirmaCartaAdhesion})}" name="" maxlength="30" size="30" readonly="true"  cssClass="textCentrado"/>
					