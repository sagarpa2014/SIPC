package mx.gob.comer.sipc.exceptions;

/**
 * Error con parametro esperado.
 * @author Francisco Daniel Perez Morales.
 */
@SuppressWarnings("serial")
public class ParameterException extends Exception{
  public ParameterException(String msg){
    super(msg);
  }
  public ParameterException(){
    super("Error en parametros");
  }
}