package subastas.controller.gestion;

import subastas.model.dao.entities.SubPostulante;
import subastas.model.generic.Funciones;
import subastas.model.manager.ManagerGestion;
import subastas.entidades.help.UsuarioHelp;
import subastas.entidades.help.Utilidades;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean
public class postulantesusuBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerGestion managergest;

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
	private String rcorreo;
	private String rpassword;
	private boolean principal;

	private UsuarioHelp session;

	private SubPostulante postulante;

	private List<SubPostulante> listaPostulante;

	private boolean mostrarpro_id;
	private boolean edicion;
	private boolean ediciontipo;
	private boolean verhorario;
	private boolean guardarin;

	public postulantesusuBean() {
	}

	@PostConstruct
	public void ini() {
		pos_id = "";
		pos_nombre = "";
		pos_apellido = "";
		pos_direccion = "";
		pos_correo = "";
		rcorreo = "";
		pos_password = "";
		rpassword = "";
		pos_telefono = "";
		pos_institucion = "";
		pos_gerencia = "";
		pos_area = "";
		pos_estado = "A";
		edicion = false;
		ediciontipo = false;
		guardarin = false;
		mostrarpro_id = false;
		listaPostulante = managergest.findAllpostulantes();
	}

	public ManagerGestion getManagergest() {
		return managergest;
	}

	public void setManagergest(ManagerGestion managergest) {
		this.managergest = managergest;
	}

	public List<SubPostulante> getListaPostulante() {
		return listaPostulante;
	}

	public void setListaPostulante(List<SubPostulante> listaPostulante) {
		this.listaPostulante = listaPostulante;
	}

	public boolean isMostrarpro_id() {
		return mostrarpro_id;
	}

	public void setMostrarpro_id(boolean mostrarpro_id) {
		this.mostrarpro_id = mostrarpro_id;
	}

	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public boolean isEdiciontipo() {
		return ediciontipo;
	}

	public void setEdiciontipo(boolean ediciontipo) {
		this.ediciontipo = ediciontipo;
	}

	public boolean isVerhorario() {
		return verhorario;
	}

	public void setVerhorario(boolean verhorario) {
		this.verhorario = verhorario;
	}

	public boolean isGuardarin() {
		return guardarin;
	}

	public void setGuardarin(boolean guardarin) {
		this.guardarin = guardarin;
	}

	public Date getFecha() {
		return fecha;
	}

	public SubPostulante getPostulante() {
		return postulante;
	}

	public void setPostulante(SubPostulante postulante) {
		this.postulante = postulante;
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

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public UsuarioHelp getSession() {
		return session;
	}

	public void setSession(UsuarioHelp session) {
		this.session = session;
	}

	public String getRcorreo() {
		return rcorreo;
	}

	public void setRcorreo(String rcorreo) {
		this.rcorreo = rcorreo;
	}

	public String getRpassword() {
		return rpassword;
	}

	public void setRpassword(String rpassword) {
		this.rpassword = rpassword;
	}

	/**
	 * @return the principal
	 */
	public boolean isPrincipal() {
		return principal;
	}

	/**
	 * @param principal
	 *            the principal to set
	 */
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public List<SubPostulante> getListposPostulantes() {
		return managergest.findAllpostulantes();
	}

	// Usuarios
	// metodo para comprobar el alias
	public boolean ccedula(String cedula) {
		List<SubPostulante> u = managergest.findAllpostulantes();
		for (SubPostulante us : u) {
			if (cedula.equals(us.getPosId())) {
				return true;
			}
		}
		return false;
	}

	// metodo para comprobar el correo
	public boolean ccorreo(String correo) {
		List<SubPostulante> u = managergest.findAllpostulantes();
		for (SubPostulante us : u) {
			if (correo.equals(us.getPosCorreo())) {
				return true;
			}
		}
		return false;
	}

	// metodo para comprobar repetidos
	public boolean repetidosc() {
		if (pos_correo.equals(rcorreo)) {
			return true;
		}
		return false;
	}

	// metodo para comprobar repetidos
	public boolean repetidosp() {
		if (pos_password.equals(rpassword)) {
			return true;
		}
		return false;
	}

	// metodo para listar los registros
	public List<SubPostulante> getListRegServi() {
		List<SubPostulante> a = managergest.findAllpostulantes();
		List<SubPostulante> l1 = new ArrayList<SubPostulante>();
		for (SubPostulante t : a) {
			if (!t.getPosNombre().equals("root")
					&& !t.getPosApellido().equals("root")) {
				l1.add(t);
			}
		}
		return l1;
	}

	// accion para invocar el manager y crear evento
	public String crearPostulante() {
		String r = "";
		try {
			// if(valida(cedula)==true)
			// {
			fecha = new Date();
			pos_fecha_reg = new Timestamp(fecha.getTime());
			if (edicion) {
				setPos_password(Utilidades.Encriptar(getPos_password()));// PASS
				managergest.editarPostulante(pos_id.trim(), pos_nombre.trim(), pos_apellido.trim(),
						pos_direccion.trim(), pos_correo.trim(), pos_telefono.trim(), pos_password.trim(),
						pos_institucion.trim(), pos_gerencia.trim(), pos_area.trim(), pos_estado);
				pos_id = "";
				pos_nombre = "";
				pos_apellido = "";
				pos_direccion = "";
				pos_correo = "";
				rcorreo = "";
				pos_password = "";
				rpassword = "";
				pos_telefono = "";
				pos_institucion = "";
				pos_gerencia = "";
				pos_estado = "A";
				pos_area = "";
				getListaPostulante().clear();
				getListaPostulante().addAll(managergest.findAllpostulantes());
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Modificado..!!!",
						"Usuario Modificado "));
				r = "postulantes?faces-redirect=true";
			} else {
				if (this.ccedula(pos_id)) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(
							"Cédula Repetida..!!!",
							"La cédula ya esta siendo utilizada"));
				} else if (this.ccorreo(pos_correo)) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(
							"Correo Repetido..!!!",
							"El correo ya esta siendo utilizado"));
				} else {
					setPos_password(Utilidades.Encriptar(getPos_password()));// PASS
					managergest.insertarPostulante(pos_id.trim(), pos_fecha_reg,
							pos_nombre.trim(), pos_apellido.trim(), pos_direccion.trim(),
							pos_correo.trim(), pos_telefono.trim(), pos_password.trim(),
							pos_institucion.trim(), pos_gerencia.trim(), pos_area.trim());
					pos_id = "";
					pos_nombre = "";
					pos_apellido = "";
					pos_direccion = "";
					pos_correo = "";
					rcorreo = "";
					pos_password = "";
					rpassword = "";
					pos_telefono = "";
					pos_institucion = "";
					pos_gerencia = "";
					pos_area = "";
					pos_estado = "A";
					getListaPostulante().clear();
					getListaPostulante().addAll(
							managergest.findAllpostulantes());
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(
							"Registrado..!!!", "Usuario Creado "));
					r = "postulantes?faces-redirect=true";
				}
			}
			// }
			// else{
			// FacesContext.getCurrentInstance().addMessage(null, new
			// FacesMessage(FacesMessage.SEVERITY_ERROR,"Cédula no valida",null));
			// }
			
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Error..!!!",
					"Usuario no pudo ser Creado "));
			e.printStackTrace();
		}
		return r;
	}

	// validar cedula
	/*
	 * public static boolean valida(String x){ int suma=0; if(x.length()==9){
	 * System.out.println("Ingrese su cedula de 10 digitos"); return false;
	 * }else{ int a[]=new int [x.length()/2]; int b[]=new int [(x.length()/2)];
	 * int c=0; int d=1; for (int i = 0; i < x.length()/2; i++) {
	 * a[i]=Integer.parseInt(String.valueOf(x.charAt(c))); c=c+2; if (i <
	 * (x.length()/2)-1) { b[i]=Integer.parseInt(String.valueOf(x.charAt(d)));
	 * d=d+2; } }
	 * 
	 * for (int i = 0; i < a.length; i++) { a[i]=a[i]*2; if (a[i] >9){
	 * a[i]=a[i]-9; } suma=suma+a[i]+b[i]; } int aux=suma/10; int
	 * dec=(aux+1)*10; if ((dec - suma) ==
	 * Integer.parseInt(String.valueOf(x.charAt(x.length()-1)))) return true;
	 * else if(suma%10==0 && x.charAt(x.length()-1)=='0'){ return true; }else{
	 * return false; } } }
	 */

	public void cambiarEstadoPostulante(SubPostulante pos) {
		setPostulante(pos);
		RequestContext.getCurrentInstance().execute("PF('ce').show();");
	}

	/**
	 * activar y desactivar estado producto
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public String cambiarEstado() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage("INFORMACION", managergest
							.cambioEstadoPostulante(getPostulante().getPosId())));
			getListaPostulante().clear();
			getListaPostulante().addAll(managergest.findAllpostulantes());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	// accion para cargar los datos en el formulario
	public String cargarPostulante(SubPostulante u) {
		try {
			pos_id = u.getPosId();
			pos_nombre = u.getPosNombre();
			pos_fecha_reg = u.getPosFechaRegistro();
			pos_apellido = u.getPosApellido();
			pos_direccion = u.getPosDireccion();
			pos_correo = u.getPosCorreo();
			pos_password = u.getPosPassword();
			pos_telefono = u.getPosTelefono();
			pos_institucion = u.getPosInstitucion();
			pos_gerencia = u.getPosGerencia();
			pos_area = u.getPosArea();
			pos_estado = u.getPosEstado();
			edicion = true;
			mostrarpro_id = true;
			return "npostulante?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "";
	}

	// ESTADOS
	/**
	 * Lista de estados
	 * 
	 * @return lista de items de estados
	 */
	public List<SelectItem> getlistEstados() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem(Funciones.estadoActivo, Funciones.estadoActivo
				+ " : " + Funciones.valorEstadoActivo));
		lista.add(new SelectItem(Funciones.estadoInactivo,
				Funciones.estadoInactivo + " : "
						+ Funciones.valorEstadoInactivo));
		return lista;
	}

	/**
	 * Redirecciona a la pagina de creacion de sitios
	 * 
	 * @return
	 */
	public String nuevoPostulante() {
		pos_id = "";
		pos_nombre = "";
		pos_apellido = "";
		pos_direccion = "";
		pos_correo = "";
		rcorreo = "";
		pos_password = "";
		rpassword = "";
		pos_telefono = "";
		pos_institucion = "";
		pos_gerencia = "";
		pos_area = "";
		pos_estado = "A";
		edicion = false;
		guardarin = false;
		ediciontipo = false;
		mostrarpro_id=false;
		verhorario = false;
		edicion = false;
		guardarin = false;
		return "npostulante?faces-redirect=true";
	}

	/**
	 * limpia la informacion
	 * 
	 * @return
	 */
	public String salir() {
		// limpiar datos
		pos_id = "";
		pos_nombre = "";
		pos_apellido = "";
		pos_direccion = "";
		pos_correo = "";
		rcorreo = "";
		pos_password = "";
		rpassword = "";
		pos_telefono = "";
		pos_institucion = "";
		pos_gerencia = "";
		pos_area = "";
		pos_estado = "A";
		edicion = false;
		guardarin = false;
		ediciontipo = false;
		verhorario = false;
		edicion = false;
		guardarin = false;
		getListaPostulante().clear();
		getListaPostulante().addAll(managergest.findAllpostulantes());
		return "postulantes?faces-redirect=true";
	}

	public void abrirDialog() {
		RequestContext.getCurrentInstance().execute("PF('gu').show();");
	}

}