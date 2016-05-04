package subastas.controller.oferta;

import java.io.Serializable;
import java.math.BigDecimal;
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

import subastas.model.dao.entities.SubItem;
import subastas.model.dao.entities.SubOferta;
import subastas.model.dao.entities.SubPostulante;
import subastas.model.generic.Funciones;
import subastas.model.generic.Mensaje;
import subastas.model.manager.ManagerGestion;

@SessionScoped
@ManagedBean
public class ofertausuBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerGestion managergest;

	// ofertas
	private Integer ofer_id;
	private String ofer_valor_oferta;
	private Timestamp ofer_fecha_oferta;
	private SubItem item;
	private SubPostulante postulante;

	// Items
	private Integer item_id;
	private String item_nombre;
	private String item_descripcion;
	private String item_caracteristicas;
	private String item_imagen;
	private String item_valorbase;
	private String item_valorventa;
	private Timestamp item_fecha_subasta_inicio;
	private Timestamp item_fecha_subasta_fin;

	// postulante
	private String pos_id;
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

	public ofertausuBean() {
	}

	@PostConstruct
	public void ini() {
		listaItem = managergest.findAllItems();
	}

	public String getPos_id() {
		return pos_id;
	}

	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}

	public String getItem_descripcion() {
		return item_descripcion;
	}

	public void setItem_descripcion(String item_descripcion) {
		this.item_descripcion = item_descripcion;
	}

	public String getItem_valorbase() {
		return item_valorbase;
	}

	public void setItem_valorbase(String item_valorbase) {
		this.item_valorbase = item_valorbase;
	}

	public String getItem_valorventa() {
		return item_valorventa;
	}

	public void setItem_valorventa(String item_valorventa) {
		this.item_valorventa = item_valorventa;
	}

	public Timestamp getItem_fecha_subasta_inicio() {
		return item_fecha_subasta_inicio;
	}

	public void setItem_fecha_subasta_inicio(Timestamp item_fecha_subasta_inicio) {
		this.item_fecha_subasta_inicio = item_fecha_subasta_inicio;
	}

	public Timestamp getItem_fecha_subasta_fin() {
		return item_fecha_subasta_fin;
	}

	public void setItem_fecha_subasta_fin(Timestamp item_fecha_subasta_fin) {
		this.item_fecha_subasta_fin = item_fecha_subasta_fin;
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
	public List<SubItem> getListaItems() {
		List<SubItem> a = managergest.findItems();
		List<SubItem> l1 = new ArrayList<SubItem>();
		for (SubItem t : a) {
			l1.add(t);

		}
		return l1;
	}

	/**
	 * accion para invocar el manager y crear producto o editar el producto
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
	public String crearOferta() {
		String r = "";
		try {
			BigDecimal valoroferta = new BigDecimal(ofer_valor_oferta.replace(
					",", "."));
			fecha = new Date();
			pos_id = "1";
			System.out.println("iddelitem "+item_id);
			managergest.asignarPostulante(pos_id);
			managergest.asignarItem(item_id);
			ofer_fecha_oferta = new Timestamp(fecha.getTime());
			managergest.insertarOferta(valoroferta, ofer_fecha_oferta);
			Mensaje.crearMensajeINFO("Registrado - Creado");
			item_id = null;
			item_nombre = "";
			item_caracteristicas = "";
			item_descripcion = "";
			item_imagen = "300.jpg";
			item_valorbase = "";
			item_valorventa = "";
			item_fecha_subasta_inicio = null;
			item_fecha_subasta_fin = null;
			getListaItem().clear();
			getListaItem().addAll(managergest.findAllItems());
			r = "home?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al crear producto", null));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}
		return r;
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
			managergest.asignarItem(item_id);
			ofer_id = ofer.getOferId();
			ofer_valor_oferta = ofer.getOferValorOferta().toString();
			ofer_fecha_oferta = ofer.getOferFechaOferta();
			item = ofer.getSubItem();
			postulante = ofer.getSubPostulante();

			item_nombre = ofer.getSubItem().getItemNombre();
			item_caracteristicas = ofer.getSubItem().getItemCaracteristicas();
			item_imagen = ofer.getSubItem().getItemImagen();

			pos_nombre = ofer.getSubPostulante().getPosNombre();
			pos_apellido = ofer.getSubPostulante().getPosApellido();
			pos_direccion = ofer.getSubPostulante().getPosDireccion();
			pos_correo = ofer.getSubPostulante().getPosCorreo();
			pos_telefono = ofer.getSubPostulante().getPosTelefono();
			pos_institucion = ofer.getSubPostulante().getPosInstitucion();
			pos_gerencia = ofer.getSubPostulante().getPosGerencia();
			pos_area = ofer.getSubPostulante().getPosArea();

			return "noferta?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Cancela la accion de irHorario
	 * 
	 * @return
	 */
	public String irComprar() {
		try {
			item_id = item.getItemId();
			item_nombre = item.getItemNombre();
			item_caracteristicas = item.getItemCaracteristicas();
			item_descripcion = item.getItemDescripcion();
			item_imagen = item.getItemImagen();
			item_valorbase = item.getItemValorBase().toString();
			item_valorventa = item.getItemValorVenta().toString();
			item_fecha_subasta_inicio = item.getItemFechaSubastaInicio();
			item_fecha_subasta_fin = item.getItemFechaSubastaFin();
			fi = item.getItemFechaSubastaInicio();
			ff = item.getItemFechaSubastaFin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "noferta?faces-redirect=true";
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
		postulante = null;

		item_nombre = "";
		item_caracteristicas = "";
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
		item_imagen = "";

		pos_nombre = "";
		pos_apellido = "";
		pos_direccion = "";
		pos_correo = "";
		pos_telefono = "";
		pos_institucion = "";
		pos_gerencia = "";
		pos_area = "";
		getListaItems().clear();
		getListaItems().addAll(managergest.findAllItems());
		return "home?faces-redirect=true";
	}

	/**
	 * 
	 */
	public void abrirDialog() {
		if (!verificarValor())
			RequestContext.getCurrentInstance().execute("PF('gu').show();");
		else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"El valor es menor que el de base", null));
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean verificarValor() {
		boolean r;
		BigDecimal valoroferta1 = new BigDecimal(ofer_valor_oferta.replace(",",
				"."));
		BigDecimal valorventa = new BigDecimal(
				item_valorventa.replace(",", "."));
		if (valoroferta1.compareTo(valorventa) < 1) {
			r = true;
		} else {
			r = false;
		}
		return r;
	}

	public void cerrarDialog() {
		RequestContext.getCurrentInstance().execute("PF('veritem').hide();");
	}
}
