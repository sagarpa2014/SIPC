package mx.gob.comer.sipc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class SqlDAO
{
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	
	protected void agregarCondicionInteger(StringBuilder builder, String nombre, Integer valor)
	{
		if( !(valor==null || valor==0 || valor==-1) )
		{
			if(builder.length() > 0)
				builder.append(" AND ");
			else
				builder.append(" WHERE ");
			builder.append(nombre).append("=").append(valor);
		}
	}
	
	protected void agregarCondicionLong(StringBuilder builder, String nombre, Long valor)
	{
		if( !(valor==null || valor==0 || valor==-1) )
		{
			if(builder.length() > 0)
				builder.append(" AND ");
			else
				builder.append(" WHERE ");
			builder.append(nombre).append("=").append(valor);
		}
	}
	
	protected void agregarCondicionString(StringBuilder builder, String nombre, String valor)
	{
		if( !(valor==null || valor.isEmpty()) )
		{
			if(builder.length() > 0)
				builder.append(" AND ");
			else
				builder.append(" WHERE ");
			builder.append(nombre).append("='").append(valor).append("'");
		}
	}
	
	protected void agregarCondicionAproximadaString(StringBuilder builder, String nombre, String valor)
	{
		if( !(valor==null || valor.isEmpty()) )
		{
			if(builder.length() > 0)
				builder.append(" AND ");
			else
				builder.append(" WHERE ");
			builder.append(nombre).append("LIKE '%").append(valor).append("%'");
		}
	}
	
	protected void agregarCondicionDate(StringBuilder builder, String nombre, Date valor)
	{
		if( !(valor==null) )
		{
			if(builder.length() > 0)
				builder.append(" AND ");
			else
				builder.append(" WHERE ");
			builder.append(nombre).append("=").append(valor);
		}
	}
	
	@SuppressWarnings("rawtypes")
	protected List consultaSql(String query)throws Exception
	{
		List resultado = new ArrayList();
		
		try
		{
			resultado = session.createQuery(query).list();
			if ( resultado != null && resultado.size()>0 )
			{
				return resultado;
			}
		}
		catch(JDBCException e)
		{
			e.printStackTrace();
		}
		catch (HibernateException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void guardar(Object registro)
	{
		try
		{
			session.saveOrUpdate(registro);
			session.flush();
			session.evict(registro);
		}
		catch (org.hibernate.exception.ConstraintViolationException e)
		{
			transaction.rollback();
			e.printStackTrace();
			SQLException s = e.getSQLException().getNextException();
			s.printStackTrace();
		}
		catch (Exception e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
	}
}
