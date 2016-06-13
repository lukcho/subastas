package subastas.model.manager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import subastas.general.connection.SingletonJDBC;
import subastas.model.dao.entities.Oferta;
import subastas.model.dao.entities.Persona;
import subastas.model.generic.Funciones;

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

	/**
	 * Devuelve un funcionario por dni
	 * 
	 * @param dni
	 * @return Funcionario
	 * @throws Exception
	 */
	public Oferta ValorMaximoXItem1(Integer item_id) throws Exception {
		Oferta o = null;
		System.out.println(item_id);
		ResultSet consulta = conDao
				.consultaSQL("select o.ofer_id as id, o.pos_id as post, o.item_id as item, o.ofer_valor_oferta as valormaximo, "
						+ "o.ofer_fecha_oferta as fechaofer FROM sub_ofertas o WHERE o.ofer_fecha_oferta=(SELECT MIN(p.ofer_fecha_oferta) "
						+ "FROM sub_ofertas p where p.item_id = "
						+ item_id
						+ "  "
						+ "and p.ofer_valor_oferta= (SELECT MAX(q.ofer_valor_oferta) FROM sub_ofertas q))");
		if (consulta != null) {
			consulta.next();
			o = new Oferta();
			System.out.println("entra a la consulta");
			o.setOferId(Integer.parseInt(consulta.getString("id")));
			o.setSubPostulante(consulta.getString("post"));
			o.setSubItem(Integer.parseInt(consulta.getString("item")));
			o.setOferValorOferta(new BigDecimal(consulta
					.getString("valormaximo")));
			o.setOferFechaOferta(new Timestamp(Funciones.stringToDate(
					consulta.getString("fechaofer")).getTime()));
		}
		return o;
	}

	public static String consultaSQL(String usr) throws Exception {
		String cc = "jdbc:postgresql://10.1.0.158:5432/app_permisos?user=adm_svcyachay&password=_50STg-FGh2h";
		Connection conexion = null;
		Statement comando = null;
		ResultSet resultado = null;
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(cc);
			comando = conexion.createStatement();
			resultado = comando
					.executeQuery("SELECT per_id FROM app_usuario WHERE usu_login = '"
							+ usr + "';");
			if (resultado.next()) {
				return resultado.getString("per_id");
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			if (resultado != null)
				resultado.close();
			if (comando != null)
				comando.close();
			if (conexion != null)
				conexion.close();
		}
	}
}
