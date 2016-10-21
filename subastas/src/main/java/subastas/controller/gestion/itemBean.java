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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import subastas.model.dao.entities.SubItem;
import subastas.model.dao.entities.SubItemFoto;
import subastas.model.dao.entities.SubOferta;
import subastas.model.generic.ConsumeREST;
import subastas.model.generic.Funciones;
import subastas.model.generic.Mensaje;
import subastas.model.manager.ManagerGestion;
import subastas.model.dao.entities.Persona;
import subastas.model.manager.ManagerCarga;
import subastas.controller.access.SesionBean;
import subastas.model.manager.ManagerBuscar;

@SessionScoped
@ManagedBean
public class itemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerGestion managergest;

	@EJB
	private ManagerBuscar mb;

	@Inject
	SesionBean ms;

	// PRODUCTOS Y SERVICIOS
	// Items
	private Integer item_id;
	private Integer item_id1;

	private String item_nombre;
	private String cedula;
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
	private SubItemFoto itemdelsitafot;

	private List<SubItem> listaItem;
	private List<SubItemFoto> listaItemfoto;

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
//	private ManagerCarga mc;
	private String img = "";

	public itemBean() {
	}

	@PostConstruct
	public void ini() {
//		mc = new ManagerCarga();
		item_id1 = 0;
		item_imagen = "300.jpg";
		item_caracteristicas = "";
		item_descripcion = "";
		item_ganador_dni = null;
		item_usuario_registro = "";
		item_estado = "A";
		item_nombre = "";
		item_valorbase = null;
		automatico = 0;
		item_valorventa = "0.00";
		edicion = false;
		img = "";
		ediciontipo = false;
		verhorario = false;
		guardarin = false;
		mostrarpro_id = false;
		mostrarfoto = false;
		listaItem = managergest.findAllItemsOrdenadasad();
		listaItemfoto = new ArrayList<SubItemFoto>();
		usuario = ms.validarSesion();
		consumirURL();
	}

	private void consumirURL() {
		try {
			img = ConsumeREST
					.consumeGetRestEasyObject(
							"http://yachay-ws.yachay.gob.ec/data/WSParametrosEntity/SRV_IMG_SYS_SUBASTAS")
					.get("parValor").toString();
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("ERROR AL CONSUMIR EL WS");
			e.printStackTrace();
		}
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getItem_id1() {
		return item_id1;
	}

	public void setItem_id1(Integer item_id1) {
		this.item_id1 = item_id1;
	}

	public SubItemFoto getItemdelsitafot() {
		return itemdelsitafot;
	}

	public void setItemdelsitafot(SubItemFoto itemdelsitafot) {
		this.itemdelsitafot = itemdelsitafot;
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

	public List<SubItemFoto> getListaItemfoto() {
		return listaItemfoto;
	}

	/**
	 * Método para listar los Items
	 * 
	 * @return
	 */
	public List<SubItemFoto> getListaItems() {
		List<SubItemFoto> a = managergest.findAllItemsOrdenadaspara();
		List<SubItemFoto> l1 = new ArrayList<SubItemFoto>();
		for (SubItemFoto t : a) {
			l1.add(t);
		}
		return l1;
	}

	/**
	 * Método para conocer el ganador
	 * 
	 * @throws Exception
	 */
	public String conocerGanador() {
		try {
			item_id = itemdelsita.getItemId();
			automatico = managergest.ofertaXItem(item_id);
			managergest.ganadorItemAutom(item_id, "I", automatico);
			item_id = null;
			getListaItem().clear();
			getListaItem().addAll(managergest.findAllItemsOrdenadasad());
			Mensaje.crearMensajeINFO("Ganador establecido, dirígase a la tabla de ganadores");
			return "items?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Método abrir dialogo para conocer ganador
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public void conocerGanadorItem(SubItem item) {
		if (item.getItemEstado().equals("A")) {
			setItemdelsita(item);
			RequestContext.getCurrentInstance().execute("PF('cg').show();");
		} else {
			Mensaje.crearMensajeINFO("Yá se estableció ganador");
		}
	}

	/**
	 * Método para invocar el manager y crear producto o editar el Item
	 * 
	 * @param item_id
	 * @param item_nombre
	 * @param item_descripcion
	 * @param item_caracteristicas
	 * @param item_imagen
	 * @param valorbase
	 * @param valorventa
	 * @param item_fecha_subasta_inicio
	 * @param item_fecha_subasta_fin
	 * @param item_ganador_dni
	 * @param item_estado
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
				item_id1 = managergest.itemByNombre(item_nombre);
				managergest.asignarItem(item_id1);
				guardarin = true;
				Mensaje.crearMensajeINFO("Actualizado - Modificado");
			} else {
				if (!averiguarItemNombre(item_nombre)) {
					managergest.insertarItem(item_nombre.trim(),
							item_descripcion.trim(),
							item_caracteristicas.trim(), item_imagen,
							valorbase, valorventa, item_fecha_subasta_inicio,
							item_fecha_subasta_fin, item_ganador_dni,
							item_usuario_registro.trim());
					Mensaje.crearMensajeINFO("Registrado - Creado Por favor seleccione una imágen principal");
					item_id1 = managergest.itemByNombre(item_nombre);
					managergest.asignarItem(item_id1);
				} else {
					Mensaje.crearMensajeWARN("Error - No se creó el registro");
				}
			}
			r = "";
		} catch (Exception e) {
			Mensaje.crearMensajeWARN("Error al crear producto");
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * Método para cargar los datos en el Item
	 * 
	 * @param item_id
	 * @param item_nombre
	 * @param item_descripcion
	 * @param item_caracteristicas
	 * @param item_imagen
	 * @param valorbase
	 * @param valorventa
	 * @param item_fecha_subasta_inicio
	 * @param item_fecha_subasta_fin
	 * @param item_ganador_dni
	 * @param item_estado
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
			item_estado = item.getItemEstado();
			BuscarPersonaitem(item_usuario_registro);
			edicion = true;
			imagenprod = false;
			mostrarpro_id = true;
			guardarin = false;
			item_id1 = managergest.itemByID(item_id).getItemId();
			getListaItemfoto().clear();
			getListaItemfoto().addAll(managergest.ItemFotoById1(item_id));
			return "nitem?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Método para activar y desactivar estado del item
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public String cambiarEstado() {
		try {
			Mensaje.crearMensajeINFO(managergest
					.cambioEstadoItem(getItemdelsita().getItemId()));
			getListaItem().clear();
			getListaItem().addAll(managergest.findAllItemsOrdenadasad());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "items?faces-redirect=true";
	}

	/**
	 * Metodo para cambiar el estado del Item
	 * 
	 * @param item
	 */
	public void cambiarEstadoItem(SubItem item) {
		setItemdelsita(item);
		RequestContext.getCurrentInstance().execute("PF('ce').show();");
	}

	/**
	 * Método para cargar la imagen del item
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void ImagenServ(FileUploadEvent event) throws IOException {
		try {
			file = event.getFile();
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (item_nombre != null && item_nombre != "") {
				item_id1 = managergest.itemByID(item_id).getItemId();
				asignarNombreImagencargado(item_id1);
				int i = listaItemfoto.size() + 1;
				if (i < 6) {
					if (file != null) {
						try {
							// Tomar PAD REAL
							// ServletContext servletContext = (ServletContext)
							// FacesContext
							// .getCurrentInstance().getExternalContext()
							// .getContext();
							// esto borrar
							// String carpetaImagenes = (String) servletContext
							// .getRealPath(File.separatorChar + "imgevent");

							// String carpetaImagenes =
							// "C:/Users/lcorrea/Desktop/wildfly-8.2.1.Final/standalone/img/img_subastas/items";
							String carpetaImagenes = "/opt/wildfly/standalone/img/img_subastas/items/";
							setItem_imagen(g);
							System.out.println("PAD------> " + carpetaImagenes);
							System.out.println("name------> "
									+ getItem_imagen());
							outputStream = new FileOutputStream(new File(
									carpetaImagenes + File.separatorChar
											+ getItem_imagen()));
							inputStream = file.getInputstream();
							int read = 0;
							byte[] bytes = new byte[1024];

							while ((read = inputStream.read(bytes)) != -1) {
								outputStream.write(bytes, 0, read);
							}
							guardarimagen();
							getListaItemfoto().clear();
							getListaItemfoto().addAll(
									managergest.ItemFotoById1(item_id1));
							setItem_imagen("");
							g = "";
							Mensaje.crearMensajeINFO("Correcto: Carga correcta, Por favor seleccione una imágen principal");
						} catch (Exception e) {
							Mensaje.crearMensajeWARN("Error: no se pudo subir la imagen");
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
						Mensaje.crearMensajeWARN("No se pudo seleccionar la imagen");
					}
				} else
					Mensaje.crearMensajeWARN("Error: Sólo puede subir 5 imagenes");
			} else
				Mensaje.crearMensajeWARN("Error: Debe guardar el ítem primero");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// metodo para poner el nombre a la imagen
	public void asignarNombreImagencargado(Integer item_nombre1) {
		try {
			if (getNombre_usuario().trim().isEmpty()) {
				Mensaje.crearMensajeWARN("Error: debe guardar un item primero");
			} else {
				DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyyHHmmss");
				g = "img_" + managergest.itemByID(item_nombre1).getItemNombre()
						+ dateFormat.format(new Date()) + ".jpg";
				System.out.println(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para poner el nombre a la imagen
	 * 
	 */
	public void asignarNombreImagen() {
		if (getItem_nombre().trim().isEmpty()) {
			Mensaje.crearMensajeWARN("Debe crear o guardar primero un producto o servicio");
		} else {
			DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyyHHmmss");
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
		mostrarfoto = true;
		listaItemfoto.clear();
		return "nitem?faces-redirect=true";
	}

	public void BuscarPersona() {
		try {
			cedula = ManagerCarga.consultaSQL(usuario);
			per = mb.buscarPersonaWSReg(cedula);
			if (per != null) {
				item_usuario_registro = per.getPerDNI();
				nombre_usuario = per.getPerNombres() + " "
						+ per.getPerApellidos();
			} else {
				throw new Exception("PERSONA NULA");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//
	public void BuscarPersonaitem(String per_id) {
		try {
			per1 = mb.buscarPersonaWSReg(per_id);
			nombre_usuario = per1.getPerNombres() + " "
					+ per1.getPerApellidos();
		} catch (Exception e) {
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
		String r = "";
		boolean a = false;
		try {
			if (item_id1 == 0) {
				r = "items?faces-redirect=true";
			} else {
				List<SubItemFoto> cond;
				cond = managergest.ItemFotoById1(item_id1);
				for (SubItemFoto y : cond) {
					if (y.getItemfMostrar() == true) {
						a = true;
						break;
					} else {
						a = false;
					}
				}
				if (a == true) {
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
					mostrarpro_id = false;
					getListaItemfoto().clear();
					getListaItem().clear();
					getListaItem()
							.addAll(managergest.findAllItemsOrdenadasad());
					r = "items?faces-redirect=true";
				} else {
					Mensaje.crearMensajeWARN("Debe seleccionar por lo menos una imagen");
					r = "nitems?faces-redirect=true";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	public void abrirDialog() {
		if (fi.after(ff)) {
			Mensaje.crearMensajeWARN("Inicio subasta debe ser menor que la fin subasta");
		} else
			RequestContext.getCurrentInstance().execute("PF('gu').show();");
	}

	/**
	 * metodo para conocer el prodid si esta usado
	 * 
	 */
	public boolean averiguarItemNombre(String item_nombre) {
		Integer t = 0;
		boolean r = false;
		List<SubItem> pro = managergest.findAllItems();
		for (SubItem y : pro) {
			if (y.getItemNombre().equals(item_nombre)) {
				t = 1;
				r = true;
				Mensaje.crearMensajeWARN("El nombre del item existe");
			}
		}
		if (t == 0) {
			r = false;
		}
		return r;
	}

	/**
	 * guardar una imagen fotoproducto
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public Integer guardarimagen() {
		try {
			managergest.asignarItem(item_id1);
			managergest.insertarItemFoto(item_nombre, item_imagen);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item_id1;
	}

	// itemfotos
	/**
	 * eliminar un fotoproducto
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public String eliminarfoto() {
		try {
			managergest.eliminarItemFoto(getItemdelsitafot().getItemfId());
			getListaItemfoto().clear();
			getListaItemfoto().addAll(
					managergest.itemFotoByNombre(getItemdelsitafot()
							.getItemfNombre()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * activar y desactivar estado fotoproducto
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public String cambiarEstadofoto() {
		try {
			if (getItemdelsitafot().getItemfMostrar() == true) {
				Mensaje.crearMensajeINFO("INFORMACIÓN: La imágen se muestra como principal");
			} else {
				managergest.cambioEstadoItemFoto(getItemdelsitafot()
						.getItemfId());
				Mensaje.crearMensajeINFO("INFORMACIÓN: Cambio modificado ");
				getListaItemfoto().clear();
				getListaItemfoto().addAll(
						managergest.itemFotoByNombre(item_nombre));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * activar y desactivar estado fotoproducto
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public String cambiarMostrarfoto() {
		try {
			Mensaje.crearMensajeINFO(managergest.cambioMostrarItemFoto(getItemdelsitafot().getItemfId(), itemdelsitafot));
			getListaItemfoto().clear();
			getListaItemfoto().addAll(
					managergest.ItemFotoById1(getItemdelsitafot().getSubItem()
							.getItemId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * eliminar un fotoproducto abriendo el dialogo
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public void cambiarEstadoprodfot(SubItemFoto item) {
		setItemdelsitafot(item);
		RequestContext.getCurrentInstance().execute("PF('ce').show();");

	}

	/**
	 * eliminar un fotoproducto abriendo el dialogo
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public void cambiarMostrarfotos(SubItemFoto item) {
		if (item.getItemfEstado().equals("I")) {
			Mensaje.crearMensajeINFO("La imágen está inactiva, seleccione una activa");
		} else {
			setItemdelsitafot(item);
			RequestContext.getCurrentInstance().execute("PF('mf').show();");
		}
	}

	/**
	 * eliminar un fotoproducto abriendo el dialogo
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public void eliminarfotocon(SubItemFoto item) {
		setItemdelsitafot(item);
		RequestContext.getCurrentInstance().execute("PF('ef').show();");
	}

}
