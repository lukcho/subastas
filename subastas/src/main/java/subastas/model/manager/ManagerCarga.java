package subastas.model.manager;

import java.sql.ResultSet;

import subastas.general.connection.SingletonJDBC;
import subastas.model.dao.entities.Persona;

/**
 * Contiene la lógica de negocio para realizar el proceso de reserva de sitios
 * 
 * @author
 * 
 */
public class ManagerCarga {

	private SingletonJDBC conDao;

	public ManagerCarga() {
		conDao = SingletonJDBC.getInstance();
	}

	/**
	 * Devuelve un funcionario por dni
	 * 
	 * @param dni
	 * @return Funcionario
	 * @throws Exception
	 */
	public Persona funcionarioByDNI(String usr_login) throws Exception {
		Persona f = null;
		ResultSet consulta = conDao
				.consultaSQL("SELECT p.per_id as dni , p.per_nombres as nombres, "
						+ " p.per_apellidos as apellidos, p.per_correo as correo, "
						+ " f.fun_cargo as cargo, f.fun_jefe_inmediato as jefe, f.fun_gerencia as gerencia, f.fun_direccion as direccion, "
						+ " (select pe.per_nombres||' '||pe.per_apellidos "
						+ " from gen_persona pe where pe.per_id=f.fun_jefe_inmediato) as nombreJefe "
						+ " FROM gen_persona p INNER JOIN gen_usuario u ON p.per_id = u.per_id "
						+ " INNER JOIN gen_funcionario f  on f.per_id = u.per_id "
						+ " WHERE u.usu_login = '" + usr_login + "'");
		if (consulta != null) {
			consulta.next();
			f = new Persona();
			f.setPerDNI(consulta.getString("dni"));
			f.setPerNombres(consulta.getString("nombres"));
			f.setPerApellidos(consulta.getString("apellidos"));
			f.setPerCorreo(consulta.getString("correo"));
			f.setCargo(consulta.getString("cargo"));
			f.setJefeInmediato(consulta.getString("jefe"));
			f.setPerGerencia(consulta.getString("gerencia"));
			f.setPerDireccion(consulta.getString("direccion"));
		}
		return f;
	}
	
	
	/**
	 * Devuelve un funcionario por dni
	 * 
	 * @param dni
	 * @return Funcionario
	 * @throws Exception
	 */
	public Persona personasolicitudByDNI(String per_id) throws Exception {
		Persona f = null;
		ResultSet consulta = conDao
				.consultaSQL("SELECT p.per_id as dni , p.per_nombres as nombres, "
						+ " p.per_apellidos as apellidos, p.per_correo as correo, "
						+ " f.fun_cargo as cargo, f.fun_jefe_inmediato as jefe, f.fun_gerencia as gerencia, f.fun_direccion as direccion, "
						+ " (select pe.per_nombres||' '||pe.per_apellidos "
						+ " from gen_persona pe where pe.per_id=f.fun_jefe_inmediato) as nombreJefe, "
						+ " (select pe.per_correo from gen_persona pe where pe.per_id=f.fun_jefe_inmediato) as correojefeinmediato"
						+ " FROM gen_persona p INNER JOIN gen_usuario u ON p.per_id = u.per_id "
						+ " INNER JOIN gen_funcionario f  on f.per_id = u.per_id "
						+ " WHERE p.per_id = '" + per_id + "'");
		if (consulta != null) {
			consulta.next();
			f = new Persona();
			f.setPerDNI(consulta.getString("dni"));
			f.setPerNombres(consulta.getString("nombres"));
			f.setPerApellidos(consulta.getString("apellidos"));
			f.setPerCorreo(consulta.getString("correo"));
			f.setCargo(consulta.getString("cargo"));
			f.setJefeInmediato(consulta.getString("jefe"));
			f.setPerGerencia(consulta.getString("gerencia"));
			f.setPerDireccion(consulta.getString("direccion"));
			f.setCorreoJefeInmediato(consulta.getString("correojefeinmediato"));
		}
		return f;
	}
}
