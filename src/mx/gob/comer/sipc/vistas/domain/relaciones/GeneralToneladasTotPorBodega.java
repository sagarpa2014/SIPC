package mx.gob.comer.sipc.vistas.domain.relaciones;

@SuppressWarnings("rawtypes")
public class GeneralToneladasTotPorBodega implements Comparable{	
	
	private String claveBodega;
	private String folioContrato;	
	private String nombreBodega;	
	private String nombreEstado;
	private Double pesoNetoAnaBol;
	private Double pesoNetoAnaFac;
	private Integer totalBoletas;
	private Integer totalFacturas;
	private Integer totalRegistros;
	private Integer numeroProdBenef;
	

	public String getClaveBodega() {
		return claveBodega;
	}
	
	public void setClaveBodega(String claveBodega) {
		this.claveBodega = claveBodega;
	}
	
	public String getFolioContrato() {
		return folioContrato;
	}
	
	public void setFolioContrato(String folioContrato) {
		this.folioContrato = folioContrato;
	}
		
	public String getNombreBodega() {
		return nombreBodega;
	}

	public void setNombreBodega(String nombreBodega) {
		this.nombreBodega = nombreBodega;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public Double getPesoNetoAnaBol() {
		return pesoNetoAnaBol;
	}

	public void setPesoNetoAnaBol(Double pesoNetoAnaBol) {
		this.pesoNetoAnaBol = pesoNetoAnaBol;
	}

	public Double getPesoNetoAnaFac() {
		return pesoNetoAnaFac;
	}

	public void setPesoNetoAnaFac(Double pesoNetoAnaFac) {
		this.pesoNetoAnaFac = pesoNetoAnaFac;
	}

	public Integer getTotalBoletas() {
		return totalBoletas;
	}

	public void setTotalBoletas(Integer totalBoletas) {
		this.totalBoletas = totalBoletas;
	}

	public Integer getTotalFacturas() {
		return totalFacturas;
	}

	public void setTotalFacturas(Integer totalFacturas) {
		this.totalFacturas = totalFacturas;
	}

	public Integer getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public Integer getNumeroProdBenef() {
		return numeroProdBenef;
	}

	public void setNumeroProdBenef(Integer numeroProdBenef) {
		this.numeroProdBenef = numeroProdBenef;
	}
	
	@Override
	public int compareTo(Object o) {
		GeneralToneladasTotPorBodega obj= (GeneralToneladasTotPorBodega) o;     
		if((this.claveBodega==null?"":this.claveBodega).compareToIgnoreCase(obj.claveBodega==null?"":obj.claveBodega) == 0){
	    	if(this.folioContrato.compareToIgnoreCase(obj.folioContrato) == 0){
	    		return this.nombreEstado.compareToIgnoreCase(obj.nombreEstado);
	    	}else{
	    		return this.folioContrato.compareToIgnoreCase(obj.folioContrato);
	    	}
	    } else {
	    	return this.claveBodega.compareToIgnoreCase(obj.claveBodega); 
	    }    		
	}
}
