package mx.gob.comer.sipc.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cuentas_bancarias")
public class CuentaBancaria{
	/**
	 * 
	 */
	private int idCuentaBancaria;
	private int idComprador;
	private String clabe;
	private int estatus;
	private String noPlaza;
	private int bancoId;
	private String numeroCuenta;
	private String sucursal;
	
	
	
	
	public CuentaBancaria(String clabe) {
		super();
		this.clabe = clabe;
	}
	
	public CuentaBancaria() {
		super();
	}
	@Id
	@GeneratedValue(generator = "cuentas_bancarias_id_cuenta_bancaria_seq")
	@SequenceGenerator(name = "cuentas_bancarias_id_cuenta_bancaria_seq", sequenceName = "cuentas_bancarias_id_cuenta_bancaria_seq")
	@Column(name =  "id_cuenta_bancaria")
	public int getIdCuentaBancaria() {
		return idCuentaBancaria;
	}
	public void setIdCuentaBancaria(int idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}
	
	@Column(name =  "id_comprador")
	public int getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
	}
	
	@Column(name =  "clabe")
	public String getClabe() {
		return clabe;
	}
	public void setClabe(String clabe) {
		this.clabe = clabe;
	}
	
	@Column(name =  "estatus")
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	
	@Column(name =  "no_plaza")
	public String getNoPlaza() {
		return noPlaza;
	}
	public void setNoPlaza(String noPlaza) {
		this.noPlaza = noPlaza;
	}
	
	@Column(name =  "banco_id")
	public int getBancoId() {
		return bancoId;
	}
	public void setBancoId(int bancoId) {
		this.bancoId = bancoId;
	}
	
	@Column(name =  "numero_cuenta")
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	@Column(name =  "sucursal")
	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}	
	
}
	
