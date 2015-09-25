package mx.gob.comer.sipc.domain.transaccionales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "relacion_compras_tmp")
public class RelacionComprasTMP  implements Comparable{
	@Id
	@GeneratedValue(generator = "relacion_compras_tmp_id_relacion_compras_tmp_seq")
	@SequenceGenerator(name = "relacion_compras_tmp_id_relacion_compras_tmp_seq", sequenceName = "relacion_compras_tmp_id_relacion_compras_tmp_seq")
	@Column(name =  "id_relacion_compras_tmp")
	private Long idRelacionComprasTmp;
	@Column(name =  "id_per_rel")
	private Long idPerRel;
	@Column(name =  "id_comprador")
	private Integer idComprador;
	@Column(name =  "folio_carta_adhesion")
	private String folioCartaAdhesion;
	@Column(name =  "clave_bodega")
	private String claveBodega;
	@Column(name =  "estado_acopio")
	private Integer estadoAcopio;
	@Column(name =  "nombre_estado")
	private String nombreEstado;
	@Column(name =  "folio_predio")
	private String folioPredio;
	@Column(name =  "folio_predio_sec")
	private Integer folioPredioSec;
	@Column(name =  "folio_predio_alterno")
	private String folioPredioAlterno;
	@Column(name =  "productor")
	private Long folioProductor;
	@Column(name =  "nombre_productor")
	private String nombreProductor;
	@Column(name =  "paterno_productor")
	private String paternoProductor;
	@Column(name =  "materno_productor")
	private String maternoProductor;
	@Column(name =  "curp_productor")
	private String curpProductor;
	@Column(name =  "rfc_productor")
	private String rfcProductor;
	@Column(name =  "boleta_ticket_bascula")
	private String boletaTicketBascula;
	@Column(name =  "fecha_entrada_boleta")
	private Date fechaEntradaBoleta;
	@Column(name =  "vol_bol_ticket")
	private Double volBolTicket;
	@Column(name =  "persona_factura_global")
	private String personaFacturaGlobal;
	@Column(name =  "numero_fac_global")
	private String numeroFacGlobal;
	@Column(name =  "fecha_fac_global")
	private Date fechaFacGlobal;
	@Column(name =  "rfc_fac_global")
	private String rfcFacGlobal;
	@Column(name =  "vol_pna_fac_global")
	private Double volPnaFacGlobal;
	@Column(name =  "imp_fac_global")
	private Double impFacGlobal;
	@Column(name =  "folio_factura_venta")
	private String folioFacturaVenta;  
	@Column(name =  "fecha_emision_fac")
	private Date fechaEmisionFac;  
	@Column(name =  "rfc_fac_venta")
	private String rfcFacVenta;
	@Column(name =  "vol_total_fac_venta")
	private Double volTotalFacVenta;
	@Column(name =  "vol_sol_fac_venta")
	private Double volSolFacVenta;
	@Column(name =  "imp_sol_fac_venta")
	private Double impSolFacVenta;
	@Column(name =  "folio_contrato")
	private String folioContrato; 
	@Column(name =  "imp_pactado_compra_venta")
	private Double impPactadoCompraVenta;
	@Column(name =  "imp_pac_axc")
	private Double impPacAXC;
	@Column(name =  "imp_pac_axc_prod")
	private Double impPacAXCProd;
	@Column(name =  "imp_comp_ton_axc")
	private Double impCompTonAXC;
	@Column(name =  "imp_comp_ton_total_axc")
	private Double impCompTonTotalAXC;
	@Column(name =  "id_tipo_doc_pago")
	private Long idTipoDocPago;
	@Column(name =  "tipo_doc_pago")
	private String tipoDocPago; 
	@Column(name =  "folio_doc_pago")
	private String folioDocPago; 
	@Column(name =  "imp_doc_pago_sinaxc")
	private Double impDocPagoSinaxc;
	@Column(name =  "fecha_doc_pago_sinaxc")
	private Date fechaDocPagoSinaxc;
	@Column(name =  "banco_id")
	private Integer bancoId;
	@Column(name =  "banco_sinaxc")
	private String bancoSinaxc;
	@Column(name =  "imp_total_pago_sinaxc")
	private Double impTotalPagoSinaxc;
	@Column(name =  "imp_precio_ton_pago_sinaxc")
	private Double impPrecioTonPagiSinaxc;
	@Column(name =  "numero_prod_benef")
	private Integer numeroProdBenef;
	@Column(name =  "fecha_tipo_cambio")
	private Date fechaTipoCambio;	
	@Column(name =  "id_variedad")
	private Integer idVariedad;
	@Column(name =  "id_cultivo")
	private Integer idCultivo;
	@Column(name =  "rfc_comprador")
	private String rfcComprador;
	@Column(name =  "id_programa")
	private Integer idPrograma;
	@Column(name =  "productor_incorrecto")
	private String productorIncorrecto;
	@Column(name =  "predio_incorrecto")
	private String predioIncorrecto;
	@Column(name =  "boleta_incosistente")
	private Boolean boletaIncosistente;
	@Column(name =  "factura_inconsistente")
	private Boolean facturaInconsistente;
	@Column(name =  "pago_inconsistente")
	private Boolean pagoInconsistente;
	@Column(name =  "predio_inconsistente")
	private Boolean predio_inconsistente;
	@Column(name =  "volumen_no_procedente")
	private Double volumenNoProcedente;
	@Column(name =  "rfc_inconsistente")
	private Boolean rfcInconsistente;	
	@Column(name =  "dif_volumen_fac_mayor")
	private Double difVolumenFacMayor;
	@Column(name =  "variedad")
	private String variedad;
	@Column(name =  "dif_volumen_fglobal_vs_find")
	private Double difVolumenFglobalVSFind;
		
	public RelacionComprasTMP() {
		super();
	}

	//Resultado para boletas duplicadas
	public RelacionComprasTMP(String claveBodega, String boletaTicketBascula) {
		super();
		this.claveBodega = claveBodega;
		this.boletaTicketBascula = boletaTicketBascula;
	}
	
	public Long getIdRelacionComprasTmp() {
		return idRelacionComprasTmp;
	}
	
	public void setIdRelacionComprasTmp(Long idRelacionComprasTmp) {
		this.idRelacionComprasTmp = idRelacionComprasTmp;
	}
	public Long getIdPerRel() {
		return idPerRel;
	}
	public void setIdPerRel(Long idPerRel) {
		this.idPerRel = idPerRel;
	}		
	
	public Integer getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}

	public String getFolioCartaAdhesion() {
		return folioCartaAdhesion;
	}
	public void setFolioCartaAdhesion(String folioCartaAdhesion) {
		this.folioCartaAdhesion = folioCartaAdhesion;
	}
	public String getClaveBodega() {
		return claveBodega;
	}
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	public Integer getEstadoAcopio() {
		return estadoAcopio;
	}
	public void setEstadoAcopio(Integer estadoAcopio) {
		this.estadoAcopio = estadoAcopio;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public String getFolioPredio() {
		return folioPredio;
	}
	public void setFolioPredio(String folioPredio) {
		this.folioPredio = folioPredio;
	}
	public Integer getFolioPredioSec() {
		return folioPredioSec;
	}
	public void setFolioPredioSec(Integer folioPredioSec) {
		this.folioPredioSec = folioPredioSec;
	}	
	
	public String getFolioPredioAlterno() {
		return folioPredioAlterno;
	}

	public void setFolioPredioAlterno(String folioPredioAlterno) {
		this.folioPredioAlterno = folioPredioAlterno;
	}

	public Long getFolioProductor() {
		return folioProductor;
	}
	public void setFolioProductor(Long folioProductor) {
		this.folioProductor = folioProductor;
	}
	public String getNombreProductor() {
		return nombreProductor;
	}
	public void setNombreProductor(String nombreProductor) {
		this.nombreProductor = nombreProductor;
	}
	public String getPaternoProductor() {
		return paternoProductor;
	}
	public void setPaternoProductor(String paternoProductor) {
		this.paternoProductor = paternoProductor;
	}
	public String getMaternoProductor() {
		return maternoProductor;
	}
	public void setMaternoProductor(String maternoProductor) {
		this.maternoProductor = maternoProductor;
	}
	public String getCurpProductor() {
		return curpProductor;
	}
	public void setCurpProductor(String curpProductor) {
		this.curpProductor = curpProductor;
	}
	public String getRfcProductor() {
		return rfcProductor;
	}
	public void setRfcProductor(String rfcProductor) {
		this.rfcProductor = rfcProductor;
	}
	public String getBoletaTicketBascula() {
		return boletaTicketBascula;
	}
	public void setBoletaTicketBascula(String boletaTicketBascula) {
		this.boletaTicketBascula = boletaTicketBascula;
	}
	public Date getFechaEntradaBoleta() {
		return fechaEntradaBoleta;
	}
	public void setFechaEntradaBoleta(Date fechaEntradaBoleta) {
		this.fechaEntradaBoleta = fechaEntradaBoleta;
	}
	public Double getVolBolTicket() {
		return volBolTicket;
	}
	public void setVolBolTicket(Double volBolTicket) {
		this.volBolTicket = volBolTicket;
	}
	public String getPersonaFacturaGlobal() {
		return personaFacturaGlobal;
	}
	public void setPersonaFacturaGlobal(String personaFacturaGlobal) {
		this.personaFacturaGlobal = personaFacturaGlobal;
	}	
	
	public String getNumeroFacGlobal() {
		return numeroFacGlobal;
	}

	public void setNumeroFacGlobal(String numeroFacGlobal) {
		this.numeroFacGlobal = numeroFacGlobal;
	}

	public Date getFechaFacGlobal() {
		return fechaFacGlobal;
	}
	public void setFechaFacGlobal(Date fechaFacGlobal) {
		this.fechaFacGlobal = fechaFacGlobal;
	}
	public String getRfcFacGlobal() {
		return rfcFacGlobal;
	}
	public void setRfcFacGlobal(String rfcFacGlobal) {
		this.rfcFacGlobal = rfcFacGlobal;
	}
	public Double getVolPnaFacGlobal() {
		return volPnaFacGlobal;
	}
	public void setVolPnaFacGlobal(Double volPnaFacGlobal) {
		this.volPnaFacGlobal = volPnaFacGlobal;
	}
	public Double getImpFacGlobal() {
		return impFacGlobal;
	}
	public void setImpFacGlobal(Double impFacGlobal) {
		this.impFacGlobal = impFacGlobal;
	}
	public String getFolioFacturaVenta() {
		return folioFacturaVenta;
	}
	public void setFolioFacturaVenta(String folioFacturaVenta) {
		this.folioFacturaVenta = folioFacturaVenta;
	}
	public Date getFechaEmisionFac() {
		return fechaEmisionFac;
	}
	public void setFechaEmisionFac(Date fechaEmisionFac) {
		this.fechaEmisionFac = fechaEmisionFac;
	}
	public String getRfcFacVenta() {
		return rfcFacVenta;
	}
	public void setRfcFacVenta(String rfcFacVenta) {
		this.rfcFacVenta = rfcFacVenta;
	}
	public Double getVolTotalFacVenta() {
		return volTotalFacVenta;
	}
	public void setVolTotalFacVenta(Double volTotalFacVenta) {
		this.volTotalFacVenta = volTotalFacVenta;
	}
	public Double getVolSolFacVenta() {
		return volSolFacVenta;
	}
	public void setVolSolFacVenta(Double volSolFacVenta) {
		this.volSolFacVenta = volSolFacVenta;
	}
	public Double getImpSolFacVenta() {
		return impSolFacVenta;
	}
	public void setImpSolFacVenta(Double impSolFacVenta) {
		this.impSolFacVenta = impSolFacVenta;
	}
	public String getFolioContrato() {
		return folioContrato;
	}
	public void setFolioContrato(String folioContrato) {
		this.folioContrato = folioContrato;
	}
	public Double getImpPactadoCompraVenta() {
		return impPactadoCompraVenta;
	}
	public void setImpPactadoCompraVenta(Double impPactadoCompraVenta) {
		this.impPactadoCompraVenta = impPactadoCompraVenta;
	}
	public Double getImpPacAXC() {
		return impPacAXC;
	}
	public void setImpPacAXC(Double impPacAXC) {
		this.impPacAXC = impPacAXC;
	}
	public Double getImpPacAXCProd() {
		return impPacAXCProd;
	}
	public void setImpPacAXCProd(Double impPacAXCProd) {
		this.impPacAXCProd = impPacAXCProd;
	}
	public Double getImpCompTonAXC() {
		return impCompTonAXC;
	}
	public void setImpCompTonAXC(Double impCompTonAXC) {
		this.impCompTonAXC = impCompTonAXC;
	}
	public Double getImpCompTonTotalAXC() {
		return impCompTonTotalAXC;
	}
	public void setImpCompTonTotalAXC(Double impCompTonTotalAXC) {
		this.impCompTonTotalAXC = impCompTonTotalAXC;
	}
	public Long getIdTipoDocPago() {
		return idTipoDocPago;
	}
	public void setIdTipoDocPago(Long idTipoDocPago) {
		this.idTipoDocPago = idTipoDocPago;
	}
	public String getTipoDocPago() {
		return tipoDocPago;
	}
	public void setTipoDocPago(String tipoDocPago) {
		this.tipoDocPago = tipoDocPago;
	}
	public String getFolioDocPago() {
		return folioDocPago;
	}
	public void setFolioDocPago(String folioDocPago) {
		this.folioDocPago = folioDocPago;
	}
	public Double getImpDocPagoSinaxc() {
		return impDocPagoSinaxc;
	}
	public void setImpDocPagoSinaxc(Double impDocPagoSinaxc) {
		this.impDocPagoSinaxc = impDocPagoSinaxc;
	}
	public Date getFechaDocPagoSinaxc() {
		return fechaDocPagoSinaxc;
	}
	public void setFechaDocPagoSinaxc(Date fechaDocPagoSinaxc) {
		this.fechaDocPagoSinaxc = fechaDocPagoSinaxc;
	}
	public Integer getBancoId() {
		return bancoId;
	}
	public void setBancoId(Integer bancoId) {
		this.bancoId = bancoId;
	}
	public String getBancoSinaxc() {
		return bancoSinaxc;
	}
	public void setBancoSinaxc(String bancoSinaxc) {
		this.bancoSinaxc = bancoSinaxc;
	}
	public Double getImpTotalPagoSinaxc() {
		return impTotalPagoSinaxc;
	}
	public void setImpTotalPagoSinaxc(Double impTotalPagoSinaxc) {
		this.impTotalPagoSinaxc = impTotalPagoSinaxc;
	}
	public Double getImpPrecioTonPagiSinaxc() {
		return impPrecioTonPagiSinaxc;
	}
	public void setImpPrecioTonPagiSinaxc(Double impPrecioTonPagiSinaxc) {
		this.impPrecioTonPagiSinaxc = impPrecioTonPagiSinaxc;
	}
	public Integer getNumeroProdBenef() {
		return numeroProdBenef;
	}
	public void setNumeroProdBenef(Integer numeroProdBenef) {
		this.numeroProdBenef = numeroProdBenef;
	}

	public Date getFechaTipoCambio() {
		return fechaTipoCambio;
	}

	public void setFechaTipoCambio(Date fechaTipoCambio) {
		this.fechaTipoCambio = fechaTipoCambio;
	}

	public Integer getIdVariedad() {
		return idVariedad;
	}

	public void setIdVariedad(Integer idVariedad) {
		this.idVariedad = idVariedad;
	}

	public Integer getIdCultivo() {
		return idCultivo;
	}

	public void setIdCultivo(Integer idCultivo) {
		this.idCultivo = idCultivo;
	}

	public String getRfcComprador() {
		return rfcComprador;
	}

	public void setRfcComprador(String rfcComprador) {
		this.rfcComprador = rfcComprador;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public String getProductorIncorrecto() {
		return productorIncorrecto;
	}

	public void setProductorIncorrecto(String productorIncorrecto) {
		this.productorIncorrecto = productorIncorrecto;
	}

	public String getPredioIncorrecto() {
		return predioIncorrecto;
	}

	public void setPredioIncorrecto(String predioIncorrecto) {
		this.predioIncorrecto = predioIncorrecto;
	}

	public Boolean getBoletaIncosistente() {
		return boletaIncosistente;
	}

	public void setBoletaIncosistente(Boolean boletaIncosistente) {
		this.boletaIncosistente = boletaIncosistente;
	}

	public Boolean getFacturaInconsistente() {
		return facturaInconsistente;
	}

	public void setFacturaInconsistente(Boolean facturaInconsistente) {
		this.facturaInconsistente = facturaInconsistente;
	}

	public Boolean getPagoInconsistente() {
		return pagoInconsistente;
	}

	public void setPagoInconsistente(Boolean pagoInconsistente) {
		this.pagoInconsistente = pagoInconsistente;
	}

	public Boolean getPredio_inconsistente() {
		return predio_inconsistente;
	}

	public void setPredio_inconsistente(Boolean predio_inconsistente) {
		this.predio_inconsistente = predio_inconsistente;
	}

	public Double getVolumenNoProcedente() {
		return volumenNoProcedente;
	}

	public void setVolumenNoProcedente(Double volumenNoProcedente) {
		this.volumenNoProcedente = volumenNoProcedente;
	}		
		
	public Boolean getRfcInconsistente() {
		return rfcInconsistente;
	}

	public void setRfcInconsistente(Boolean rfcInconsistente) {
		this.rfcInconsistente = rfcInconsistente;
	}

	public Double getDifVolumenFacMayor() {
		return difVolumenFacMayor;
	}

	public void setDifVolumenFacMayor(Double difVolumenFacMayor) {
		this.difVolumenFacMayor = difVolumenFacMayor;
	}
	
	public String getVariedad() {
		return variedad;
	}

	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}
	

	public Double getDifVolumenFglobalVSFind() {
		return difVolumenFglobalVSFind;
	}

	public void setDifVolumenFglobalVSFind(Double difVolumenFglobalVSFind) {
		this.difVolumenFglobalVSFind = difVolumenFglobalVSFind;
	}

	@Override
	public int compareTo(Object o) {
		RelacionComprasTMP obj= (RelacionComprasTMP) o;     
	    if(this.claveBodega.compareToIgnoreCase(obj.claveBodega) == 0) { 
	    	if(this.nombreEstado.compareToIgnoreCase(obj.nombreEstado) == 0){
	    		if((this.folioContrato==null?"":this.folioContrato).compareToIgnoreCase(obj.folioContrato==null?"":obj.folioContrato) == 0){	    			
	    			if((this.numeroFacGlobal==null?"":this.numeroFacGlobal).compareToIgnoreCase(obj.numeroFacGlobal==null?"":obj.numeroFacGlobal) == 0){
	    				if(this.paternoProductor.compareToIgnoreCase(obj.paternoProductor) == 0) {
			            	if(this.maternoProductor.compareToIgnoreCase(obj.maternoProductor) == 0) {
			            		  return this.nombreProductor.compareToIgnoreCase(obj.nombreProductor); 
			            	}else{
			            		return this.maternoProductor.compareTo(obj.maternoProductor);
			            	}	                 
			            } else { 
			                return this.paternoProductor.compareToIgnoreCase(obj.paternoProductor); 
			            }
	    			}else{
	    				return this.numeroFacGlobal.compareToIgnoreCase(obj.folioContrato);
	    			}		    		
		    	}else{
		    		return this.folioContrato.compareToIgnoreCase(obj.folioContrato);
		    	} 
	    	}else{
	    		return this.nombreEstado.compareToIgnoreCase(obj.nombreEstado);
	    	}
	    } else {
	            return this.claveBodega.compareToIgnoreCase(obj.claveBodega); 
	    }    		
	}

	
}
