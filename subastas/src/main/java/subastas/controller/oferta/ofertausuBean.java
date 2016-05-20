package subastas.controller.oferta;

import subastas.entidades.help.UsuarioHelp;
import subastas.entidades.help.Utilidades;

import java.io.IOException;
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
import subastas.controller.gestion.SessionBean;
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
	private String valorMaximo;
	private String ganandoperdiendo;
	private String ganandoperdiendo1;
	private String valorUltimoPostulante;

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
	private Integer faltatiempo;
	private String colorgana;
	private String colorgana1;
	// postulante
	private String pos_id;
	private String pos_nombre;
	private String pos_apellido;
	private String pos_correo;
	private String pos_telefono;
	private String pos_celular;
	private String pos_institucion;
	private String pos_gerencia;
	private String pos_area;
	private String pos_direccion;
	private String pos_password;
	private boolean edicion;
	private SubOferta ofertadelsita;

	private SubItem itemdelsita;
	private Integer itemganador;
	private Integer automatico;
	private SubOferta ofertavalor;

	private List<SubItem> listaItem;

	private List<SubOferta> listaOferta;

	private String tiempo;

	private boolean ocultarColorGana;
	private boolean ocultarColorGana1;

	// horario
	private Date fi;
	private Date ff;
	private Date date;
	private Date fecha;

	private UsuarioHelp session;

	public ofertausuBean() {
	}

	@PostConstruct
	public void ini() {
		edicion = true;
		tiempo = "00 : 00 : 00";
		valorUltimoPostulante = "0.00";
		ganandoperdiendo = "";
		ganandoperdiendo1 = "";
		colorgana = "colorBlack";
		colorgana1 = "colorBlack";
		session = SessionBean.verificarSession();
		cargarDatosLogeado();
		listaItem = managergest.findAllItems();
	}

	public String getGanandoperdiendo1() {
		return ganandoperdiendo1;
	}
	
	public void setGanandoperdiendo1(String ganandoperdiendo1) {
		this.ganandoperdiendo1 = ganandoperdiendo1;
	}
	
	public String getGanandoperdiendo() {
		return ganandoperdiendo;
	}

	public void setGanandoperdiendo(String ganandoperdiendo) {
		this.ganandoperdiendo = ganandoperdiendo;
	}

	public boolean isOcultarColorGana1() {
		return ocultarColorGana1;
	}
	
	public void setOcultarColorGana1(boolean ocultarColorGana1) {
		this.ocultarColorGana1 = ocultarColorGana1;
	}
	
	public boolean isOcultarColorGana() {
		return ocultarColorGana;
	}

	public void setOcultarColorGana(boolean ocultarColorGana) {
		this.ocultarColorGana = ocultarColorGana;
	}

	public String getValorUltimoPostulante() {
		return valorUltimoPostulante;
	}

	public void setValorUltimoPostulante(String valorUltimoPostulante) {
		this.valorUltimoPostulante = valorUltimoPostulante;
	}
	
	public String getColorgana1() {
		return colorgana1;
	}
	
	public void setColorgana1(String colorgana1) {
		this.colorgana1 = colorgana1;
	}

	public String getColorgana() {
		return colorgana;
	}

	public void setColorgana(String colorgana) {
		this.colorgana = colorgana;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public Integer getItemganador() {
		return itemganador;
	}

	public void setItemganador(Integer itemganador) {
		this.itemganador = itemganador;
	}

	public Integer getAutomatico() {
		return automatico;
	}

	public void setAutomatico(Integer automatico) {
		this.automatico = automatico;
	}

	public SubItem getItemdelsita() {
		return itemdelsita;
	}

	public void setItemdelsita(SubItem itemdelsita) {
		this.itemdelsita = itemdelsita;
	}

	public Integer getFaltatiempo() {
		return faltatiempo;
	}

	public void setFaltatiempo(Integer faltatiempo) {
		this.faltatiempo = faltatiempo;
	}

	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public String getPos_password() {
		return pos_password;
	}

	public void setPos_password(String pos_password) {
		this.pos_password = pos_password;
	}

	public String getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(String valorMaximo) {
		this.valorMaximo = valorMaximo;
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

	public String getPos_celular() {
		return pos_celular;
	}

	public void setPos_celular(String pos_celular) {
		this.pos_celular = pos_celular;
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
	 * Metodo de cálculo de tiempo
	 * 
	 * @param eva_est
	 */
	public void totalTiempo(SubItem item) {
		String tiempo_eva = "";
		fecha = new Date();
		Timestamp fecha_ahora = new Timestamp(fecha.getTime());
		long time = fecha_ahora.getTime()
				- item.getItemFechaSubastaInicio().getTime();

		long minutos = 0;
		long segundos = 0;

		minutos = time / (60 * 1000);
		while (minutos >= 60) {
			minutos = minutos - 60;
		}

		segundos = time / 1000;
		while (segundos >= 60) {
			segundos = segundos - 60;
		}

		tiempo_eva = minutos + ":" + segundos;
		System.out.println(tiempo_eva);
	}

	/**
	 * metodo para listar las ofertas x usuario
	 * 
	 * @return
	 */
	public List<SubOferta> getListaOfertaXUsuario() {
		session = SessionBean.verificarSession();
		System.out.println(session.getIdUsr());
		List<SubOferta> a = managergest.findAllofertasOrdenadasXUsuario(session
				.getIdUsr());
		List<SubOferta> l1 = new ArrayList<SubOferta>();
		for (SubOferta t : a) {
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
			pos_id = session.getIdUsr();
			System.out.println("iddelitem " + item_id);
			managergest.asignarPostulante(pos_id);
			managergest.asignarItem(item_id);
			ofer_fecha_oferta = new Timestamp(fecha.getTime());
			managergest.insertarOferta(valoroferta, ofer_fecha_oferta);
			ofer_valor_oferta = "";
			valoroferta = new BigDecimal(0.00);
			getListaItem().clear();
			getListaItem().addAll(managergest.findAllItems());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Registrado- Se envio la oferta", null));
			r = "";

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al crear oferta", null));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}
		return r;
	}

	// accion para invocar el manager y crear evento
	public String editarPostulante() {
		String r = "";
		try {
			setPos_password(Utilidades.Encriptar(getPos_password()));// PASS
			managergest.editarPostulanteedicion(pos_id.trim(),
					pos_nombre.trim(), pos_apellido.trim(),
					pos_direccion.trim(), pos_correo.trim(),
					pos_telefono.trim(), pos_celular.trim(),
					pos_password.trim());
			Mensaje.crearMensajeINFO("Actualizado - Modificado");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado - Editado", null));
			r = "home?faces-redirect=true";

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al  crear usuario postulante", null));
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
			ofer_id = ofer.getOferId();
			ofer_valor_oferta = ofer.getOferValorOferta().toString();
			ofer_fecha_oferta = ofer.getOferFechaOferta();
			item = ofer.getSubItem();
			postulante = ofer.getSubPostulante();

			item_id = ofer.getSubItem().getItemId();
			System.out.println("este es el id: " + item_id);
			item_nombre = ofer.getSubItem().getItemNombre();
			item_caracteristicas = ofer.getSubItem().getItemCaracteristicas();
			item_imagen = ofer.getSubItem().getItemImagen();
			item_descripcion = ofer.getSubItem().getItemDescripcion();
			item_valorbase = ofer.getSubItem().getItemValorBase().toString();

			if (managergest.ValorMaximoXItem(item_id) == null) {
				valorMaximo = "No se Oferta aún";
			} else {
				valorMaximo = managergest.ValorMaximoXItem(item_id).toString();
			}

			pollMethod();
			pos_nombre = ofer.getSubPostulante().getPosNombre();
			pos_apellido = ofer.getSubPostulante().getPosApellido();
			pos_direccion = ofer.getSubPostulante().getPosDireccion();
			pos_correo = ofer.getSubPostulante().getPosCorreo();
			pos_telefono = ofer.getSubPostulante().getPosTelefono();
			pos_celular = ofer.getSubPostulante().getPosCelular();
			pos_institucion = ofer.getSubPostulante().getPosInstitucion();
			pos_gerencia = ofer.getSubPostulante().getPosGerencia();
			pos_area = ofer.getSubPostulante().getPosArea();

			return "uoferta?faces-redirect=true";
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
	public String cargarOfertaPas(SubOferta ofer) {
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
			pos_celular = ofer.getSubPostulante().getPosCelular();
			pos_institucion = ofer.getSubPostulante().getPosInstitucion();
			pos_gerencia = ofer.getSubPostulante().getPosGerencia();
			pos_area = ofer.getSubPostulante().getPosArea();
			return "uoferta?faces-redirect=true";
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
			if (managergest.ValorMaximoXItem(item_id) == null) {
				valorMaximo = "No se Oferta aún";
			} else {

				if (managergest.ofertaXPost(item_id, pos_id) == 0) {
					ofertavalor = null;
				} else {
					ofertavalor = managergest.ofertaByID(managergest
							.ofertaXPost(item_id, pos_id));
					BigDecimal valoroferta = ofertavalor.getOferValorOferta();
					BigDecimal valormaximo1 = new BigDecimal(
							managergest.ValorMaximoXItem(item_id));

					valorMaximo = managergest.ValorMaximoXItem(item_id)
							.toString();
					valorUltimoPostulante = valoroferta.toString();

					if (valormaximo1.compareTo(valoroferta) == 1) {
						colorgana = "colorRed";
						ganandoperdiendo = "  Tu oferta  "+valorUltimoPostulante;
						colorgana1 = "colorGreen";
						ganandoperdiendo1 = "  Ultima Mejor Oferta:  $"+valorMaximo;
						setOcultarColorGana1(true);
					} else if (valormaximo1.compareTo(valoroferta) == -1) {
						colorgana = "colorGreen";
						ganandoperdiendo = " Estas Ganando: $"+valorUltimoPostulante;
						setOcultarColorGana1(false);
					} else if (valormaximo1.compareTo(valoroferta) == 0) {
						colorgana = "colorGreen";
						ganandoperdiendo = " Estas Ganando: $"+valorUltimoPostulante;
						setOcultarColorGana1(false);
					}
					setOcultarColorGana(true);
					
				}
			}
			return "";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	// postulante edicion perfil
	/**
	 * metodo para listar los registros
	 * 
	 * @return
	 */
	public List<SubItem> getListaItems() {
		fecha = new Date();
		cargarDatosLogeado();
		Timestamp fecha_ahora = new Timestamp(fecha.getTime());
		List<SubItem> a = managergest.findItems();
		List<SubItem> l1 = new ArrayList<SubItem>();
		for (SubItem t : a) {
			if (t.getItemGanadorDni() == null && t.getItemEstado().equals("A")
					&& t.getItemFechaSubastaFin().after((fecha_ahora)))
				l1.add(t);
		}
		return l1;
	}

	// accion para invocar el manager y crear evento
	public String crearPostulante() {
		String r = "";
		try {
			if (edicion) {
				setPos_password(Utilidades.Encriptar(getPos_password()));// PASS
				managergest.editarPostulante(pos_id.trim(), pos_nombre.trim(),
						pos_apellido.trim(), pos_direccion.trim(),
						pos_correo.trim(), pos_telefono.trim(),
						pos_celular.trim(), pos_password.trim(), "A");
				pos_id = "";
				pos_nombre = "";
				pos_apellido = "";
				pos_direccion = "";
				pos_correo = "";
				pos_password = "";
				pos_telefono = "";
				pos_celular = "";
				pos_institucion = "";
				pos_gerencia = "";
				pos_area = "";
				Mensaje.crearMensajeINFO("");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Modificado - Editado", null));
				r = "home?faces-redirect=true";
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						"Error..!!! Usuario no pudo ser Creado "));
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Error..!!!",
					"Usuario no pudo ser Creado "));
			e.printStackTrace();
		}
		return r;
	}

	public String cargarDatosLogeado() {
		if (session != null) {
			try {
				SubPostulante usr = managergest.postulanteByID(session
						.getIdUsr());
				pos_id = usr.getPosId();
				pos_nombre = usr.getPosNombre();
				pos_apellido = usr.getPosApellido();
				pos_direccion = usr.getPosDireccion();
				pos_correo = usr.getPosCorreo();
				pos_password = Utilidades.Desencriptar(usr.getPosPassword());
				pos_telefono = usr.getPosTelefono();
				pos_celular = usr.getPosCelular();
				pos_institucion = usr.getPosInstitucion();
				pos_gerencia = usr.getPosGerencia();
				pos_area = usr.getPosArea();
				edicion = true;
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error al  cargar sus datos personales", null));
			}
		} else {
			System.out.println("no carga nada");
		}
		return "uperfil?faces-redirect=true";
	}

	/**
	 * limpia la informacion
	 * 
	 * @return
	 */
	public String salir() {
		// limpiar datos
		ofertavalor = null;
		ofer_valor_oferta = null;
		ganandoperdiendo = "";
		valorUltimoPostulante = "0.00";
		getListaItems().clear();
		getListaItems().addAll(managergest.findAllItems());
		return "home?faces-redirect=true";
	}

	// traslados

	/**
	 * Cancela la accion de irHorario
	 * 
	 * @return
	 */
	public String irComprar() {
		String r = "";
		try {
			if (item == null) {
				System.out.println("holi");
			} else {
				setOcultarColorGana(false);
				fecha = new Date();
				ofer_fecha_oferta = new Timestamp(System.currentTimeMillis());
				item_fecha_subasta_inicio = item.getItemFechaSubastaInicio();
				item_fecha_subasta_fin = item.getItemFechaSubastaFin();
				System.out.println(item_fecha_subasta_fin);
				Date fechaactual = new Date(ofer_fecha_oferta.getTime());
				Date fechasubini = new Date(item_fecha_subasta_inicio.getTime());
				Date fechasubfin = new Date(item_fecha_subasta_fin.getTime());

				miTiempo();

				System.out.println("midia en date" + fechaactual
						+ "    mi diainicio" + fechasubini + "     mi diafin"
						+ fechasubfin);

				if (ofer_fecha_oferta.before(item_fecha_subasta_inicio)
						&& ofer_fecha_oferta.getTime() < item_fecha_subasta_inicio
								.getTime()) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Aun no se puede ofertar", null));
				} else if (ofer_fecha_oferta.after((item_fecha_subasta_fin))
						&& ofer_fecha_oferta.getTime() > item_fecha_subasta_fin
								.getTime()) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Ya se ha terminado la oferta", null));
				} else if (fechaactual.getTime() > fechasubini.getTime()
						&& fechaactual.getTime() < fechasubfin.getTime()) {
					item_id = item.getItemId();
					item_nombre = item.getItemNombre();
					item_caracteristicas = item.getItemCaracteristicas();
					item_descripcion = item.getItemDescripcion();
					item_imagen = item.getItemImagen();
					item_valorbase = item.getItemValorBase().toString();
					item_valorventa = item.getItemValorVenta().toString();

					if (managergest.ValorMaximoXItem(item_id) == null) {
						valorMaximo = "No se Oferta aún";
					} else {
						valorMaximo = managergest.ValorMaximoXItem(item_id)
								.toString();
					}
					item_fecha_subasta_inicio = item
							.getItemFechaSubastaInicio();
					item_fecha_subasta_fin = item.getItemFechaSubastaFin();
					fi = item.getItemFechaSubastaInicio();
					ff = item.getItemFechaSubastaFin();
					r = "noferta?faces-redirect=true";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public void pollMethod() {
		miTiempo();
		conocerGanador();
	}

	/**
	 * 
	 * @param fFin
	 * @return
	 */
	public Integer tiempoRestante(Timestamp fFin) {
		return ((Long) ((fFin.getTime() - (new Timestamp(new Date().getTime()))
				.getTime()) / 1000)).intValue();
	}

	/**
	 * 
	 */
	public void miTiempo() {
		setFaltatiempo(this.tiempoRestante(item_fecha_subasta_fin));
		setTiempo(this.tiempoRestanteHMS(getFaltatiempo()));
		System.out.println("---------------------> FALTA TIEMPO "
				+ getFaltatiempo());
		if (getFaltatiempo() <= 0)
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/home.xhtml");
			} catch (IOException ex) {
				Mensaje.crearMensajeERROR(ex.getMessage());
			}
	}

	public String tiempoRestanteHMS(Integer segundos) {
		int hor = segundos / 3600;
		int min = (segundos - (3600 * hor)) / 60;
		int seg = segundos - ((hor * 3600) + (min * 60));
		return String.format("%02d", hor) + " : " + String.format("%02d", min)
				+ " : " + String.format("%02d", seg);
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
		pos_celular = "";
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
	public String salirEdPerfil() {
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
		pos_celular = "";
		pos_institucion = "";
		pos_gerencia = "";
		pos_area = "";
		getListaItems().clear();
		getListaItems().addAll(managergest.findAllItems());
		return "home?faces-redirect=true";
	}

	/**
	 * limpia la informacion
	 * 
	 * @return
	 */
	public String salirOfertas() {
		// limpiar datos
		ofer_id = null;
		ofer_valor_oferta = null;
		ofer_fecha_oferta = null;
		item = null;

		item_nombre = "";
		item_caracteristicas = "";
		item_imagen = "";
		getListaOfertaXUsuario().clear();
		getListaOfertaXUsuario()
				.addAll(managergest.findAllofertasOrdenadasXUsuario(session
						.getIdUsr()));
		return "ofertas?faces-redirect=true";
	}

	/**
	 * 
	 */
	public void abrirDialog() {
		if (!verificarValor()){
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_INFO,
									"El valor es menor que el de base o de tu última oferta",
									null));
		RequestContext.getCurrentInstance().execute("PF('vm').show();");
		}else {
			RequestContext.getCurrentInstance().execute("PF('gu').show();");
		}
	}

	/**
	 * 
	 */
	public void abrirDialogEdPerfil() {
		RequestContext.getCurrentInstance().execute("PF('gu').show();");
	}

	/**
	 * 
	 * @return
	 */
	public boolean verificarValor() {
		boolean r;

		BigDecimal valoroferta1 = new BigDecimal(ofer_valor_oferta.replace(",",
				"."));
		BigDecimal valorbase = new BigDecimal(item_valorbase.replace(",", "."));

		BigDecimal valorultimopostulante = new BigDecimal(
				valorUltimoPostulante.replace(",", "."));

		System.out.println("el valor que se ingresa: " + valoroferta1);
		System.out.println("el valor del item: " + valorbase);
		System.out.println("el ultimo valor postu: " + valorultimopostulante);
		if (valoroferta1 == valorultimopostulante
				|| (valoroferta1.compareTo(valorbase) == 1 && valoroferta1
						.compareTo(valorultimopostulante) == 1)) {
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