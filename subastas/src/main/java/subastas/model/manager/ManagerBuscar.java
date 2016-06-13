package subastas.model.manager;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.json.simple.JSONObject;

import subastas.model.dao.entities.Persona;
import subastas.model.dao.entities.SubParametro;
import subastas.model.generic.ConsumeREST;
import subastas.model.generic.Funciones;

@Stateless
public class ManagerBuscar {

	@EJB
	private ManagerDAO mDAO;

	public ManagerBuscar() {
		mDAO = new ManagerDAO();
	}

	public Persona buscarPersonaWSReg(String dni) throws Exception {
		SubParametro param = parametroByID("find_personas");
		System.out.println(param.getParValor());
		System.out.println("este es el dni:" + dni);
		JSONObject respuesta = ConsumeREST.consumeGetRestEasyObject(param
				.getParValor() + dni);
		if (respuesta.isEmpty())
			return null;
		else {
			System.out.println("Respuesta FindPersonas ---> " + respuesta);
			Persona p = new Persona();
			p.setPerDNI(Funciones.evaluarDatoWS(respuesta.get("perDni")));
			p.setPerNombres(Funciones.evaluarDatoWS(respuesta.get("perNombres")));
			p.setPerApellidos(Funciones.evaluarDatoWS(respuesta
					.get("perApellidos")));
			p.setPerCorreo(Funciones.evaluarDatoWS(respuesta.get("perCorreo")));
			p.setPerCelular(Funciones.evaluarDatoWS(respuesta.get("perCelular")));
			p.setPerTelefono(Funciones.evaluarDatoWS(respuesta
					.get("perTelefono")));
			return p;
		}
	}

	/**
	 * buscar los vehuculos por ID
	 * 
	 * @param vehi_id
	 * @throws Exception
	 */
	public SubParametro parametroByID(String parametro) throws Exception {
		return (SubParametro) mDAO.findById(SubParametro.class, parametro);
	}

}
