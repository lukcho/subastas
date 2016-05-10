package subastas.controller.gestion;

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
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import subastas.controller.access.SesionBean;
import subastas.model.dao.entities.SubItem;
import subastas.model.dao.entities.SubOferta;
import subastas.model.dao.entities.SubPostulante;
import subastas.model.generic.Funciones;
import subastas.model.generic.Mensaje;
import subastas.model.manager.ManagerGestion;

@SessionScoped
@ManagedBean
public class ofertasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerGestion managergest;

	@Inject
	SesionBean ms;

	// ofertas
	private Integer ofer_id;
	private String ofer_valor_oferta;
	private Timestamp ofer_fecha_oferta;
	private SubItem item;
	private SubPostulante postulante;

	// Items
	private Integer item_id;
	private String item_nombre;
	private String item_caracteristicas;
	private String item_descripcion;
	private String item_imagen;
	private Integer item_ganador;
	private String item_valorbase;
	private String item_valorventa;
	private String valorMaximo;

	// postulante
	private String pos_nombre;
	private String pos_apellido;
	private String pos_correo;
	private String pos_telefono;
	private String pos_institucion;
	private String pos_gerencia;
	private String pos_area;
	private String pos_direccion;

	private SubOferta ofertadelsita;

	private List<SubItem> listaItem;

	private List<SubOferta> listaOferta;

	// horario
	private Date fi;
	private Date ff;
	private Date date;
	private Date fecha;

	private String usuario;

	public ofertasBean() {
	}

	@PostConstruct
	public void ini() {
		listaOferta = managergest.findAllofertasOrdenadas();
		usuario = ms.validarSesion("ofertas.xhtml");
	}

	public String getItem_valorventa() {
		return item_valorventa;
	}

	public void setItem_valorventa(String item_valorventa) {
		this.item_valorventa = item_valorventa;
	}

	public String getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(String valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public String getItem_valorbase() {
		return item_valorbase;
	}

	public void setItem_valorbase(String item_valorbase) {
		this.item_valorbase = item_valorbase;
	}

	public Integer getItem_ganador() {
		return item_ganador;
	}

	public void setItem_ganador(Integer item_ganador) {
		this.item_ganador = item_ganador;
	}

	public String getPos_direccion() {
		return pos_direccion;
	}

	public void setPos_direccion(String pos_direccion) {
		this.pos_direccion = pos_direccion;
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

	public Integer getOfer_id() {
		return ofer_id;
	}

	public void setOfer_id(Integer ofer_id) {
		this.ofer_id = ofer_id;
	}

	public String getOfer_valor_oferta() {
		return ofer_valor_oferta;
	}

	public void setOfer_valor_oferta(String ofer_valor_oferta) {
		this.ofer_valor_oferta = ofer_valor_oferta;
	}

	public Timestamp getOfer_fecha_oferta() {
		return ofer_fecha_oferta;
	}

	public void setOfer_fecha_oferta(Timestamp ofer_fecha_oferta) {
		this.ofer_fecha_oferta = ofer_fecha_oferta;
	}

	public SubItem getItem() {
		return item;
	}

	public void setItem(SubItem item) {
		this.item = item;
	}

	public SubPostulante getPostulante() {
		return postulante;
	}

	public void setPostulante(SubPostulante postulante) {
		this.postulante = postulante;
	}

	public Date getFi() {
		return fi;
	}

	public void setFi(Date fi) {
		this.fi = fi;
	}

	public Date getFf() {
		return ff;
	}

	public void setFf(Date ff) {
		this.ff = ff;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String getItem_nombre() {
		return item_nombre;
	}

	public void setItem_nombre(String item_nombre) {
		this.item_nombre = item_nombre;
	}

	public String getItem_caracteristicas() {
		return item_caracteristicas;
	}

	public void setItem_caracteristicas(String item_caracteristicas) {
		this.item_caracteristicas = item_caracteristicas;
	}

	public String getItem_descripcion() {
		return item_descripcion;
	}

	public void setItem_descripcion(String item_descripcion) {
		this.item_descripcion = item_descripcion;
	}

	public String getItem_imagen() {
		return item_imagen;
	}

	public void setItem_imagen(String item_imagen) {
		this.item_imagen = item_imagen;
	}

	public SubOferta getOfertadelsita() {
		return ofertadelsita;
	}

	public void setOfertadelsita(SubOferta ofertadelsita) {
		this.ofertadelsita = ofertadelsita;
	}

	public List<SubItem> getListaItem() {
		return listaItem;
	}

	public void setListaItem(List<SubItem> listaItem) {
		this.listaItem = listaItem;
	}

	public List<SubOferta> getListaOferta() {
		return listaOferta;
	}

	public void setListaOferta(List<SubOferta> listaOferta) {
		this.listaOferta = listaOferta;
	}

	public Date getDate() {
		date = new Date();
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * metodo para listar los registros
	 * 
	 * @return
	 */
	public List<SubOferta> getListaOfertasNoGanadores() {
		List<SubOferta> a = managergest.listadoNoGanadores();
		List<SubOferta> l1 = new ArrayList<SubOferta>();
		for (SubOferta t : a) {
			l1.add(t);
		}
		return l1;
	}

	/**
	 * metodo para listar los registros
	 * 
	 * @return
	 */
	public List<SubOferta> getListaOfertasGanadores() {
		return managergest.listadoGanadores();
	}

	/**
	 * metodo para listar las ofertas
	 * 
	 * @return
	 */
	public List<SubItem> getListaItems() {
		List<SubItem> a = managergest.findAllItemsOrdenadas();
		List<SubItem> l1 = new ArrayList<SubItem>();
		for (SubItem t : a) {
			l1.add(t);

		}
		return l1;
	}

	/**
	 * accion para cargar los datos en el formulario
	 * 
	 * @param pro_id
	 * @param prodfoto_id
	 * @param pro_nombre
	 * @param pro_descripcion
	 * @param pro_costo
	 * @param pro_precio
	 * @param pro_stock
	 * @param pro_estado
	 * @param pro_estado_fun
	 * @throws Exception
	 */
	public String cargarOferta(SubOferta ofer) {
		try {
			ofer_id = ofer.getOferId();
			ofer_valor_oferta = ofer.getOferValorOferta().toString();
			ofer_fecha_oferta = ofer.getOferFechaOferta();
			item = ofer.getSubItem();
			postulante = ofer.getSubPostulante();

			item_id = ofer.getSubItem().getItemId();
			item_nombre = ofer.getSubItem().getItemNombre();
			item_caracteristicas = ofer.getSubItem().getItemCaracteristicas();
			item_descripcion = ofer.getSubItem().getItemDescripcion();
			item_imagen = ofer.getSubItem().getItemImagen();
			item_valorbase = ofer.getSubItem().getItemValorBase().toString();
			item_valorventa = ofer.getSubItem().getItemValorVenta().toString();

			if (managergest.ValorMaximoXItem(item_id) == null) {
				valorMaximo = "";
			} else {
				valorMaximo = managergest.ValorMaximoXItem(item_id).toString();
			}

			pos_nombre = ofer.getSubPostulante().getPosNombre();
			pos_apellido = ofer.getSubPostulante().getPosApellido();
			pos_direccion = ofer.getSubPostulante().getPosDireccion();
			pos_correo = ofer.getSubPostulante().getPosCorreo();
			pos_telefono = ofer.getSubPostulante().getPosTelefono();
			pos_institucion = ofer.getSubPostulante().getPosInstitucion();
			pos_gerencia = ofer.getSubPostulante().getPosGerencia();
			pos_area = ofer.getSubPostulante().getPosArea();
			Mensaje.crearMensajeINFO("Actualizado - Modificado");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Editado - Modificado", null));
			return "noferta?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * accion para cargar los datos en el formulario
	 * 
	 * @param pro_id
	 * @param prodfoto_id
	 * @param pro_nombre
	 * @param pro_descripcion
	 * @param pro_costo
	 * @param pro_precio
	 * @param pro_stock
	 * @param pro_estado
	 * @param pro_estado_fun
	 * @throws Exception
	 */
	public String conocerGanador() {
		try {
			ofer_id = ofertadelsita.getOferId();
			ofer_valor_oferta = ofertadelsita.getOferValorOferta().toString();
			ofer_fecha_oferta = ofertadelsita.getOferFechaOferta();
			item = ofertadelsita.getSubItem();
			postulante = ofertadelsita.getSubPostulante();

			item_id = ofertadelsita.getSubItem().getItemId();
			item_nombre = ofertadelsita.getSubItem().getItemNombre();
			item_caracteristicas = ofertadelsita.getSubItem()
					.getItemCaracteristicas();
			item_descripcion = ofertadelsita.getSubItem().getItemDescripcion();
			item_imagen = ofertadelsita.getSubItem().getItemImagen();
			item_ganador = ofertadelsita.getSubItem().getItemGanadorDni();

			pos_nombre = ofertadelsita.getSubPostulante().getPosNombre();
			pos_apellido = ofertadelsita.getSubPostulante().getPosApellido();
			pos_direccion = ofertadelsita.getSubPostulante().getPosDireccion();
			pos_correo = ofertadelsita.getSubPostulante().getPosCorreo();
			pos_telefono = ofertadelsita.getSubPostulante().getPosTelefono();
			pos_institucion = ofertadelsita.getSubPostulante()
					.getPosInstitucion();
			pos_gerencia = ofertadelsita.getSubPostulante().getPosGerencia();
			pos_area = ofertadelsita.getSubPostulante().getPosArea();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage("INFORMACION", managergest.ganadorItem(
							item_id, "I", managergest.itemByID(item_id),
							ofer_id)));

			getListaOferta().clear();
			getListaOferta().addAll(managergest.findAllofertasOrdenadas());
			Mensaje.crearMensajeINFO("Actualizado - Modificado");
			return "ofertas?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * activar y desactivar estado producto
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public void conocerGanadorItem(SubOferta ofer) {
		setOfertadelsita(ofer);
		RequestContext.getCurrentInstance().execute("PF('cg').show();");
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
							.cambioEstadoItem(getOfertadelsita().getOferId())));
			getListaOferta().clear();
			getListaOferta().addAll(managergest.findAllofertasOrdenadas());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public void cambiarEstadoOferta(SubOferta ofer) {
		setOfertadelsita(ofer);
		RequestContext.getCurrentInstance().execute("PF('ce').show();");
	}

	/**
	 * Redirecciona a la pagina de creacion de sitios
	 * 
	 * @return
	 */
	public String nuevaSubasta() {
		ofer_id = null;
		ofer_valor_oferta = null;
		ofer_fecha_oferta = null;
		item = null;
		postulante = null;

		item_nombre = "";
		item_caracteristicas = "";
		item_descripcion = "";
		item_imagen = "";

		pos_nombre = "";
		pos_apellido = "";
		pos_direccion = "";
		pos_correo = "";
		pos_telefono = "";
		pos_institucion = "";
		pos_gerencia = "";
		pos_area = "";
		return "nitem?faces-redirect=true";
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

	// ------ traslados--------

	/**
	 * limpia la informacion
	 * 
	 * @return
	 */
	public String salir() {
		// limpiar datos
		ofer_id = null;
		ofer_valor_oferta = null;
		ofer_fecha_oferta = null;
		item = null;
		postulante = null;

		item_nombre = "";
		item_caracteristicas = "";
		item_descripcion = "";
		item_imagen = "";
		item_valorbase = "";

		pos_nombre = "";
		pos_apellido = "";
		pos_direccion = "";
		pos_correo = "";
		pos_telefono = "";
		pos_institucion = "";
		pos_gerencia = "";
		pos_area = "";
		getListaOferta().clear();
		getListaOferta().addAll(managergest.findAllofertasOrdenadas());
		return "ofertas?faces-redirect=true";
	}

	public void abrirDialog() {
		RequestContext.getCurrentInstance().execute("PF('gu').show();");
	}
}
