package mx.gob.comer.sipc.log;

import org.apache.log4j.Logger;

/**
 * Bitacoras de la aplicacion.
 * 
 */
public class AppLogger{

  private static Logger app      = null;
  private static Logger errores      = null;
  private static Logger ate      = null;
  private static Logger atr  = null;
  
  private AppLogger(){}
  
  public static void setLogger(String loggerType,Logger log){
    if(loggerType.equalsIgnoreCase("app")){
        if( app == null){
          app = log;
        }
    }else if(loggerType.equalsIgnoreCase("errores")){
        if( errores == null){
        	errores = log;
          }
    }else if(loggerType.equalsIgnoreCase("ate")){
        if( ate == null){
        	ate = log;
          }
    }else if(loggerType.equalsIgnoreCase("atr")){
        if( atr == null){
        	atr = log;
          }
    }

  }//end setLogger
  
  public static void info(Object msg){
      info("app",msg);
  }
  
  public static void info(String logger,Object msg){
      Logger log = app;
      if(logger.equalsIgnoreCase("errores") || logger.equalsIgnoreCase("errores") ){
          log = errores;
      }else if(logger.equalsIgnoreCase("ate") || logger.equalsIgnoreCase("ate") ){
        log = atr;
	  }else if(logger.equalsIgnoreCase("atr") || logger.equalsIgnoreCase("atr") ){
        log = atr;
	  }
      
      if(log!=null){
        log.info(msg);
      }else{
        System.out.println("[INFO] - " + msg.toString());
      }
  }  
  
  public static void error(Object msg){
    error("app",msg);
  }
  
  public static void error(String logger,Object msg){
      Logger log = app;
      if(logger.equalsIgnoreCase("errores") || logger.equalsIgnoreCase("errores") ){
          log = errores; 
      }else if(logger.equalsIgnoreCase("ate") || logger.equalsIgnoreCase("ate") ){
        log = ate;
	  }else if(logger.equalsIgnoreCase("atr") || logger.equalsIgnoreCase("atr") ){
        log = atr;
	  }
      
      if(log!=null){
        log.error(msg);
      }else{
        System.out.println("[ERROR] - " + msg.toString());
      }
  }

  public static void warn(Object msg){
      warn("app",msg);
  }
  
  public static void warn(String logger,Object msg){
      Logger log = app;
      if(logger.equalsIgnoreCase("errores") || logger.equalsIgnoreCase("errores") ){
          log = errores;
      }else if(logger.equalsIgnoreCase("ate") || logger.equalsIgnoreCase("ate") ){
        log = atr;
	  }else if(logger.equalsIgnoreCase("atr") || logger.equalsIgnoreCase("atr") ){
        log = atr;
	  }
      if(log!=null){
        log.warn(msg);
      }else{
        System.out.println("[WARN] - " + msg.toString());
      }
  }
  public static void debug(Object msg){
    debug("app",msg);
  }
  
  public static void debug(String logger,Object msg){
      Logger log = app;
      if(logger.equalsIgnoreCase("errores") || logger.equalsIgnoreCase("errores") ){
          log = ate;
      }else if(logger.equalsIgnoreCase("atr") || logger.equalsIgnoreCase("atr") ){
        log = atr;
	  }else if(logger.equalsIgnoreCase("atr") || logger.equalsIgnoreCase("atr") ){
        log = atr;
	  }
      if(log!=null){
        log.debug(msg);
      }else{
        System.out.println("[DEBUG] - " + msg.toString());
      }
  }
  public static void fatal(Object msg){
      fatal("app",msg);
  }  
  public static void fatal(String logger,Object msg){
      Logger log = app;
      if(logger.equalsIgnoreCase("ate") || logger.equalsIgnoreCase("ate") ){
          log = ate;
      }else if(logger.equalsIgnoreCase("atr") || logger.equalsIgnoreCase("atr") ){
        log = atr;
	  }
      
      if(log!=null){
        log.fatal(msg);
      }else{
        System.out.println("[FATAL] - " + msg.toString());
      }
  }
}