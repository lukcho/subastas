package subastas.controller.gestion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import subastas.model.dao.entities.SubItem;
import subastas.model.dao.entities.SubOferta;
import subastas.model.generic.Funciones;
import subastas.model.generic.Mensaje;
import subastas.model.manager.ManagerGestion;
import subastas.model.dao.entities.Persona;
import subastas.model.manager.ManagerCarga;
import subastas.controller.access.SesionBean;

@SessionScoped
@ManagedBean
public class itemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerGestion managergest;

	@Inject
	SesionBean ms;

	// PRODUCTOS Y SERVICIOS
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
	private Integer item_ganador_dni;
	private String item_usuario_registro;
	private String item_estado;
	private boolean mostrarfoto;
	private String valorMaximo;
	
	private boolean mostrarpro_id;
	private boolean edicion;
	private boolean ediciontipo;
	private boolean verhorario;
	private boolean guardarin;
	private Integer automatico;

	// fabprodfoto imagenes
	private UploadedFile file;
	private String g;
	private boolean imagenprod;

	private SubItem itemdelsita;

	private List<SubItem> listaItem;

	private SubOferta ofertadelsita;
	
	// horario
	private Date fi;
	private Date ff;
	private Date date;
	private Date fecha;

	private String usuario;
	private String nombre_usuario;
	private Persona per;
	private Persona per1;
	private ManagerCarga mc;

	public itemBean() {
	}

	@PostConstruct
	public void ini() {
		mc = new ManagerCarga();
		item_imagen = "300.jpg";
		item_caracteristicas = "";
		item_descripcion = "";
		item_ganador_dni = null;
		item_usuario_registro = "";
		item_estado = "A";
		item_nombre = "";
		item_valorbase = null;
		automatico=0;
		item_valorventa = "0.00";
		edicion = false;
		ediciontipo = false;
		verhorario = false;
		guardarin = false;
		mostrarpro_id = false;
		mostrarfoto = false;
		listaItem = managergest.findAllItems();
		usuario = ms.validarSesion("items.xhtml");
	}
	
	public Integer getAutomatico() {
		return automatico;
	}
	
	public void setAutomatico(Integer automatico) {
		this.automatico = automatico;
	}
	
	public SubOferta getOfertadelsita() {
		return ofertadelsita;
	}

	public void setOfertadelsita(SubOferta ofertadelsita) {
		this.ofertadelsita = ofertadelsita;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	
	public String getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(String valorMaximo) {
		this.valorMaximo = valorMaximo;
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

	public String getItem_descripcion() {
		return item_descripcion;
	}

	public void setItem_descripcion(String item_descripcion) {
		this.item_descripcion = item_descripcion;
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

	public Integer getItem_ganador_dni() {
		return item_ganador_dni;
	}

	public void setItem_ganador_dni(Integer item_ganador_dni) {
		this.item_ganador_dni = item_ganador_dni;
	}

	public String getItem_usuario_registro() {
		return item_usuario_registro;
	}

	public void setItem_usuario_registro(String item_usuario_registro) {
		this.item_usuario_registro = item_usuario_registro;
	}

	public String getItem_estado() {
		return item_estado;
	}

	public void setItem_estado(String item_estado) {
		this.item_estado = item_estado;
	}

	public SubItem getItemdelsita() {
		return itemdelsita;
	}

	public void setItemdelsita(SubItem itemdelsita) {
		this.itemdelsita = itemdelsita;
	}

	public List<SubItem> getListaItem() {
		return listaItem;
	}

	public void setListaItem(List<SubItem> listaItem) {
		this.listaItem = listaItem;
	}

	public boolean isMostrarfoto() {
		return mostrarfoto;
	}

	public void setMostrarfoto(boolean mostrarfoto) {
		this.mostrarfoto = mostrarfoto;
	}

	public boolean isGuardarin() {
		return guardarin;
	}

	public void setGuardarin(boolean guardarin) {
		this.guardarin = guardarin;
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

	public boolean isMostrarpro_id() {
		return mostrarpro_id;
	}

	public void setMostrarpro_id(boolean mostrarpro_id) {
		this.mostrarpro_id = mostrarpro_id;
	}

	public boolean isVerhorario() {
		return verhorario;
	}

	public void setVerhorario(boolean verhorario) {
		this.verhorario = verhorario;
	}

	public boolean isImagenprod() {
		return imagenprod;
	}

	public void setImagenprod(boolean imagenprod) {
		this.imagenprod = imagenprod;
	}

	public boolean isEdiciontipo() {
		return ediciontipo;
	}

	public void setEdiciontipo(boolean ediciontipo) {
		this.ediciontipo = ediciontipo;
	}

	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * metodo para listar los registros
	 * 
	 * @return
	 */
	public List<SubItem> getListaItems() {
		List<SubItem> a = managergest.findAllItemsOrdenadasad();
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
	public String conocerGanador() {
		try {
			item_id = itemdelsita.getItemId();
			automatico = managergest.ofertaXItem(item_id);
			System.out.println("coge el id del mayor: "+automatico);
			managergest.ganadorItemAutom(item_id, "I",automatico);
			item_id=null;
			getListaItem().clear();
			getListaItem().addAll(managergest.findAllItems());
			Mensaje.crearMensajeINFO("Actualizado - Modificado");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Ganador - Establecido", null));
			return "items?faces-redirect=true";
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
	public void conocerGanadorItem(SubItem item) {
		setItemdelsita(item);
		RequestContext.getCurrentInstance().execute("PF('cg').show();");
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
	public String crearItem() {
		String r = "";
		try {
			BigDecimal valorbase = new BigDecimal(item_valorbase.replace(",",
					"."));
			BigDecimal valorventa = new BigDecimal(item_valorventa.replace(",",
					"."));
			item_fecha_subasta_inicio = new Timestamp(fi.getTime());
			item_fecha_subasta_fin = new Timestamp(ff.getTime());
			if (edicion) {
				managergest.editarItem(item_id, item_nombre.trim(),
						item_descripcion.trim(), item_caracteristicas.trim(),
						item_imagen.trim(), valorbase, valorventa,
						item_fecha_subasta_inicio, item_fecha_subasta_fin,
						item_ganador_dni, item_estado);

				getListaItem().clear();
				getListaItem().addAll(managergest.findAllItems());
				item_id = null;
				item_nombre = "";
				item_caracteristicas = "";
				item_descripcion = "";
				item_imagen = "300.jpg";
				item_valorbase = "";
				item_valorventa = "0.00";
				item_fecha_subasta_inicio = null;
				item_fecha_subasta_fin = null;
				item_ganador_dni = null;
				item_estado = "A";
				edicion = false;
				guardarin = false;
				ediciontipo = false;
				verhorario = false;
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Editado - Modificado", null));
			} else {
				managergest.insertarItem(item_nombre.trim(),
						item_descripcion.trim(), item_caracteristicas.trim(),
						item_imagen, valorbase, valorventa,
						item_fecha_subasta_inicio, item_fecha_subasta_fin,
						item_ganador_dni, item_usuario_registro.trim());
				Mensaje.crearMensajeINFO("Registrado - Creado");
				item_id = null;
				item_nombre = "";
				item_caracteristicas = "";
				item_descripcion = "";
				item_imagen = "300.jpg";
				item_valorbase = "";
				item_valorventa = "0.00";
				item_fecha_subasta_inicio = null;
				item_fecha_subasta_fin = null;
				item_ganador_dni = null;
				item_estado = "A";
				edicion = false;
				guardarin = false;
				ediciontipo = false;
				verhorario = false;
				imagenprod = false;
				mostrarpro_id = true;
				guardarin = true;
				getListaItem().clear();
				getListaItem().addAll(managergest.findAllItems());
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Registrado - Creado", null));
			}
			r = "items?faces-redirect=true";
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
	public String cargarItem(SubItem item) {
		try {
			item_id = item.getItemId();
			item_nombre = item.getItemNombre();
			asignarNombreImagen();
			item_caracteristicas = item.getItemCaracteristicas();
			item_descripcion = item.getItemDescripcion();
			item_imagen = item.getItemImagen();
			item_valorbase = item.getItemValorBase().toString();
			item_valorventa = item.getItemValorVenta().toString();
			item_fecha_subasta_inicio = item.getItemFechaSubastaInicio();
			item_fecha_subasta_fin = item.getItemFechaSubastaFin();
			fi = item.getItemFechaSubastaInicio();
			ff = item.getItemFechaSubastaFin();
			
			if (managergest.ValorMaximoXItem(item_id) == null) {
				valorMaximo = "";
			} else {
				valorMaximo = managergest.ValorMaximoXItem(item_id).toString();
			}
			
			item_ganador_dni = item.getItemGanadorDni();
			item_usuario_registro = item.getItemUsuarioRegistro();
			System.out.println("usuario que hizo: " + item_usuario_registro);
			item_estado = item.getItemEstado();
			BuscarPersonaitem(item_usuario_registro);
			edicion = true;
			imagenprod = false;
			mostrarpro_id = true;
			guardarin = false;
			return "nitem?faces-redirect=true";
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
	public String cambiarEstado() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Estado Modificado",
					managergest.cambioEstadoItem(getItemdelsita().getItemId())));
			getListaItem().clear();
			getListaItem().addAll(managergest.findAllItems());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "items?faces-redirect=true";
	}

	public void cambiarEstadoItem(SubItem item) {
		setItemdelsita(item);
		RequestContext.getCurrentInstance().execute("PF('ce').show();");
	}

	// metodo para guardar la imagen en el servidor
	public void ImagenServ(FileUploadEvent event) throws IOException {
		file = event.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		if (file != null) {
			try {
				// Tomar PAD REAL
				ServletContext servletContext = (ServletContext) FacesContext
						.getCurrentInstance().getExternalContext().getContext();
				String carpetaImagenes = (String) servletContext
						.getRealPath(File.separatorChar + "resources" + File.separatorChar + "img" + File.separatorChar + "items");
				setItem_imagen(g);
				System.out.println("PAD------> " + carpetaImagenes);
				System.out.println("name------> " + getItem_imagen());
				outputStream = new FileOutputStream(new File(carpetaImagenes
						+ File.separatorChar + getItem_imagen()));
				inputStream = file.getInputstream();

				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Correcto: Carga Correcta", null));

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: no se pudo subir la imagen",null));
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}

				if (outputStream != null) {
					outputStream.close();
				}
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:",
							"no se pudo seleccionar la imagen"));
		}
	}

	// metodo para poner el nombre a la imagen
	public void asignarNombreImagen() {
		if (getItem_nombre().trim().isEmpty()) {
			System.out.println("Vacio");
		}else {
			DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyy");
			g = "img_" + getItem_nombre() + dateFormat.format(new Date())
					+ ".jpg";
			System.out.println(g);
		}
	}

	// metodo para poner el nombre a la imagen
	public void nombreImagen(String n) {
		List<SubItem> li = managergest.findAllItems();
		for (SubItem e : li) {
			if (e.getItemImagen().contains(n)) {
				g = e.getItemImagen();
			}
		}
		System.out.println(g);
	}

	/**
	 * Redirecciona a la pagina de creacion de sitios
	 * 
	 * @return
	 */
	public String nuevoItem() {
		BuscarPersona();
		item_id = null;
		item_nombre = "";
		item_caracteristicas = "";
		item_descripcion = "";
		item_imagen = "300.jpg";
		System.out.println(item_imagen);
		item_valorbase = "";
		item_valorventa = "0.00";
		fi = new Date();
		ff = new Date();
		item_ganador_dni = null;
		item_estado = "A";
		edicion = false;
		guardarin = false;
		ediciontipo = false;
		verhorario = false;
		edicion = false;
		imagenprod = true;
		guardarin = false;
		mostrarfoto = false;
		return "nitem?faces-redirect=true";
	}

	public void BuscarPersona() {
		try {
			per = mc.funcionarioByDNI(usuario);
			item_usuario_registro = per.getPerDNI();
			nombre_usuario = per.getPerNombres() + " " + per.getPerApellidos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void BuscarPersonaitem(String per_id) {
		try {
			per1 = mc.personasolicitudByDNI(per_id);
			nombre_usuario = per1.getPerNombres() + " "
					+ per1.getPerApellidos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		item_id = null;
		item_nombre = "";
		item_caracteristicas = "";
		item_descripcion = "";
		item_imagen = "300.jpg";
		item_valorbase = "";
		item_valorventa = "0.00";
		item_fecha_subasta_inicio = null;
		item_fecha_subasta_fin = null;
		item_ganador_dni = null;
		item_estado = "A";
		edicion = false;
		guardarin = false;
		ediciontipo = false;
		verhorario = false;
		getListaItem().clear();
		getListaItem().addAll(managergest.findAllItems());
		return "items?faces-redirect=true";
	}

	public void abrirDialog() {
		if (fi.after(ff)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Inicio Subasta debe ser menor que la Fin Subasta",
							null));
		} else {
			RequestContext.getCurrentInstance().execute("PF('gu').show();");
		}
	}
}
