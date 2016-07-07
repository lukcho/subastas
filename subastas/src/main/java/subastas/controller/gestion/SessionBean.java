package subastas.controller.gestion;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import subastas.entidades.help.UsuarioHelp;
import subastas.entidades.help.Utilidades;
import subastas.model.dao.entities.SubPostulante;
import subastas.model.generic.Mensaje;
import subastas.model.manager.ManagerGestion;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@SessionScoped
@ManagedBean
public class SessionBean {
	private UsuarioHelp session;
	// log
	private String nick;
	private String pass;

	@EJB
	ManagerGestion managest;

	// devolver contraseña
	private String correocontra;
	String smscor = "";

	// mostrar
	private String nom;

	@SuppressWarnings("unused")
	private SubPostulante usr;

	/* Perfil de Usuario */
	private String nombre, apellido, password, correo, cedula;

	private String pos_id;
	private Timestamp pos_fecha_reg;
	private String pos_nombre;
	private String pos_apellido;
	private String pos_direccion;
	private String pos_correo;
	private String pos_telefono;
	private String pos_password;
	private String pos_institucion;
	private String pos_gerencia;
	private String pos_area;
	private String pos_estado;
	private Date fecha;
	private boolean edicion;

	@EJB
	private ManagerGestion managergest;
	
	@EJB
	private subastas.model.manager.ManagerBuscar mb;

	public SessionBean() {
		nombre = "";
		apellido = "";
		password = "";
		correo = "";
		cedula = "";
		pos_id = "";
		pos_fecha_reg = null;
		pos_nombre = "";
		pos_apellido = "";
		pos_direccion = "";
		pos_correo = "";
		pos_telefono = "";
		pos_password = "";
		pos_institucion = "";
		pos_gerencia = "";
		pos_area = "";
		pos_estado = "";
		fecha = null;
		edicion = false;
		managest = new ManagerGestion();
		usr = new SubPostulante();
	}

	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public String getPos_id() {
		return pos_id;
	}

	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}

	public Timestamp getPos_fecha_reg() {
		return pos_fecha_reg;
	}

	public void setPos_fecha_reg(Timestamp pos_fecha_reg) {
		this.pos_fecha_reg = pos_fecha_reg;
	}

	public String getPos_nombre() {
		return pos_nombre;
	}

	public void setPos_nombre(String pos_nombre) {
		this.pos_nombre = pos_nombre;
	}

	public String getPos_apellido() {
		return pos_apellido;
	}

	public void setPos_apellido(String pos_apellido) {
		this.pos_apellido = pos_apellido;
	}

	public String getPos_direccion() {
		return pos_direccion;
	}

	public void setPos_direccion(String pos_direccion) {
		this.pos_direccion = pos_direccion;
	}

	public String getPos_correo() {
		return pos_correo;
	}

	public void setPos_correo(String pos_correo) {
		this.pos_correo = pos_correo;
	}

	public String getPos_telefono() {
		return pos_telefono;
	}

	public void setPos_telefono(String pos_telefono) {
		this.pos_telefono = pos_telefono;
	}

	public String getPos_password() {
		return pos_password;
	}

	public void setPos_password(String pos_password) {
		this.pos_password = pos_password;
	}

	public String getPos_institucion() {
		return pos_institucion;
	}

	public void setPos_institucion(String pos_institucion) {
		this.pos_institucion = pos_institucion;
	}

	public String getPos_gerencia() {
		return pos_gerencia;
	}

	public void setPos_gerencia(String pos_gerencia) {
		this.pos_gerencia = pos_gerencia;
	}

	public String getPos_area() {
		return pos_area;
	}

	public void setPos_area(String pos_area) {
		this.pos_area = pos_area;
	}

	public String getPos_estado() {
		return pos_estado;
	}

	public void setPos_estado(String pos_estado) {
		this.pos_estado = pos_estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getPass() {
		return pass;
	}

	public UsuarioHelp getSession() {
		return session;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	/* Perfil Usuario */
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @return the correocontra
	 */
	public String getCorreocontra() {
		return correocontra;
	}

	/**
	 * @param correocontra
	 *            the correocontra to set
	 */
	public void setCorreocontra(String correocontra) {
		this.correocontra = correocontra;
	}

	// login
	public void veri() {
		System.out.println("este es el id: " + nick);
		int t = 0;
		List<SubPostulante> a = managest.findAllpostulantes();
		for (SubPostulante u : a) {
			if ((u.getPosId().equals(nick) || u.getPosCorreo().equals(nick))) {
				t = 100;
			}
		}
		if (t != 100) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Alias Inexistente o Usuario Desactivado", null));
		}
	}

	// metodo para ingresar al sistema
	public String login() {
		String r = "";
		Integer t = 0;
		List<SubPostulante> u = managest.findAllpostulantes();
		try {
			for (SubPostulante y : u) {
				System.out.println("avr "
						+ Utilidades.Encriptar(pass).toString());
				if (y.getPosId().equals(nick)
						&& y.getPosPassword()
								.equals(Utilidades.Encriptar(pass))
						&& y.getPosEstado().equals("A")) {
					session = new UsuarioHelp(y.getPosId(), y.getPosApellido(),
							y.getPosCorreo(), y.getPosNombre());
					nom = y.getPosNombre() + " " + y.getPosApellido();
					usr = y;
					r = "postulante/home?faces-redirect=true";
					t = 1;
				} else if (y.getPosCorreo().equals(nick)
						&& y.getPosPassword()
								.equals(Utilidades.Encriptar(pass))) {
					session = new UsuarioHelp(y.getPosId(), y.getPosApellido(),
							y.getPosCorreo(), y.getPosNombre());
					nom = y.getPosNombre() + " " + y.getPosApellido();
					usr = y;
					r = "postulante/home?faces-redirect=true";
					t = 1;
				}
			}
			if (t == 0) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Error..!!!",
						"Usuario o Contrasena Incorrecta "));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	// metodo para salir de el sistema
	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.invalidate();
		nom = "";
		correo = "";
		pass = "";
		nick = "";
		System.out.println("si salio");
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Salió", null));
		return "/index?faces-redirect=true";
	}

	/**
	 * Método para verifiar la existencia de la sesión
	 * 
	 * @param rol
	 *            de usuario
	 * @return Clase Usuario
	 */
	public static UsuarioHelp verificarSession() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		SessionBean user = (SessionBean) session.getAttribute("sessionBean");
		if (user == null || user.getSession() == null) {
			try {
				System.out.println("me envia a ajuera ");
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("../index.xhtml");
			} catch (IOException ex) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
								.getMessage(), null));
			}
			return null;
		} else {
			return user.getSession();
		}
	}

	/**
	 * Método para validar sesión en el INDEX
	 */
	public void validaIndex() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		SessionBean user = (SessionBean) session.getAttribute("sessionBean");
		if (user == null || user.getSession() == null) {
			try {
				System.out.println("me envia a ajuefa ");
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("../index.xhtml");
			} catch (IOException ex) {
				Mensaje.crearMensajeERROR(ex.getMessage());
			}
		}
	}

	public String cargarDatosPerfil() {
		String pag = "";
		if (session != null) {
			try {
				SubPostulante usr = managest.postulanteByID(session.getIdUsr());
				setApellido(usr.getPosApellido());
				setNombre(usr.getPosNombre());
				setCorreo(usr.getPosCorreo());
				setCedula(usr.getPosId());
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error al  cargar sus datos personales", null));
			}
		}
		return pag;
	}

	public String regresarHomeUser() {
		return "index?faces-redirect=true";
	}

	// metodo para enviar el correo
	public String devolvercontra() {
		String r = "";
		Integer t = 0;
		List<SubPostulante> u = managest.findAllpostulantes();
		for (SubPostulante y : u) {
			if (y.getPosCorreo().equals(correocontra)) {
				System.out.println("si entra1");
				enviarmensajerecuperarcontra(y);
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_INFO,
										"Enviado correctamente revise su correo",
										null));
				correocontra = "";
				t = 1;
			}
		}
		if (t == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Error..!!! su correo no existe o es incorrecto", null));
		}
		return r;
	}

	String correoveri = "";

	// Tomar el id de estado general id_estadoSolicitud
	public String enviarmensajerecuperarcontra(SubPostulante usr) {
		try {
			String passwordnuevo;
			cedula = usr.getPosId();
			nombre = usr.getPosNombre();
			apellido = usr.getPosApellido();
			correo = usr.getPosCorreo();
			password = usr.getPosPassword();
			System.out.println(password);
			passwordnuevo = Utilidades.Desencriptar(password);
			System.out.println(passwordnuevo);

			smscor = "<!DOCTYPE html><html lang='es'><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
					+ "<meta name='viewport' content='width=device-width'></head><body>"
					+ "Estimado(a) postulant: "+ nombre+ " "+ apellido+ ", <br/>"
					+ "Sus credenciales para ingreso al sistema de subastas son: <br/>"
					+ "Usuario: "+ cedula +" " + "<br/> Contraseña: "
					+ passwordnuevo + " " + "<br/> Correo: " + correo + ""
					+ "<br/> Saludos cordiales, "
					+ "<br/> Sistema de Subastas Yachay EP"
					+ "<br/><em><strong>NOTA:</strong> Este correo es generado automáticamente por el sistema favor no responder al mismo.</em></body></html>";

//			Mail.generateAndSendEmail(correo,
//					"Recuperación de contraseña Subastas", smscor);
			
			mb.envioMailWS(correo, "Recuperación de contraseña Subastas", smscor);
			// limpiamos los datos
			cedula = "";
			nombre = "";
			apellido = "";
			correo = "";
			password = "";
			passwordnuevo = "";
			smscor = "";

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Error al enviar correo", null));
		}
		return "index?faces-redirect=true";
	}

}
