package subastas.model.manager;

import subastas.model.dao.entities.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerGestion {

	@EJB
	private ManagerDAO mDAO;

	private static SubItem sub_item;
	private static SubPostulante sub_pos;

	String h = "";

	public ManagerGestion() {
	}

	// ITEMS

	/**
	 * buscar todos vehiculos
	 * 
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public List<SubItem> findItems() {
		return mDAO.findWhere(SubItem.class, "1=1", null);
	}

	/**
	 * listar todos los vehiculos
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SubItem> findAllItems() {
		return mDAO.findAll(SubItem.class);
	}

	/**
	 * buscar vehiculos por ID
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	public SubItem itemByID(Integer item_id) throws Exception {
		return (SubItem) mDAO.findById(SubItem.class, item_id);
	}

	/**
	 * listar todos los items por ganador
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SubItem> findAllItemsOrdenadas() {
		return mDAO.findWhere(SubItem.class, " 1=1 ",
				" o.itemGanadorDni not like '' desc");
	}

	/**
	 * listar todos los items por ganador
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SubItem> findAllItemsOrdenadasad() {
		return mDAO.findWhere(SubItem.class, " 1=1 ",
				" o.itemFechaSubastaInicio desc");
	}

	/**
	 * listar todos los items por fechas pasadas
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SubItem> findAllItemsOrdenadasPasadas() {
		return mDAO
				.findWhere(
						SubItem.class,
						" o.itemFechaSubastaFin < now() or o.itemGanadorDni is not null or o.itemEstado = 'I' ",
						" o.itemFechaSubastaFin desc");
	}

	/**
	 * Agrega vehiculo
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
	public void insertarItem(String item_nombre, String item_descripcion,
			String item_caracteristicas, String item_imagen,
			BigDecimal item_valorbase, BigDecimal item_valorventa,
			Timestamp item_fecha_subasta_inicio,
			Timestamp item_fecha_subasta_fin, Integer item_ganador_dni,
			String item_usuario_registro) throws Exception {
		SubItem item = new SubItem();
		item.setItemNombre(item_nombre);
		item.setItemDescripcion(item_descripcion);
		item.setItemCaracteristicas(item_caracteristicas);
		item.setItemImagen(item_imagen);
		item.setItemValorBase(item_valorbase);
		item.setItemValorVenta(item_valorventa);
		item.setItemFechaSubastaInicio(item_fecha_subasta_inicio);
		item.setItemFechaSubastaFin(item_fecha_subasta_fin);
		item.setItemGanadorDni(item_ganador_dni);
		item.setItemUsuarioRegistro(item_usuario_registro);
		item.setItemEstado("A");
		mDAO.insertar(item);
	}

	/**
	 * Cambiar datos de Item
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
	public void editarItem(Integer item_id, String item_nombre,
			String item_descripcion, String item_caracteristicas,
			String item_imagen, BigDecimal item_valorbase,
			BigDecimal item_valorventa, Timestamp item_fecha_subasta_inicio,
			Timestamp item_fecha_subasta_fin, Integer item_ganador_dni,
			String item_estado) throws Exception {
		SubItem item = this.itemByID(item_id);
		item.setItemNombre(item_nombre);
		item.setItemDescripcion(item_descripcion);
		item.setItemCaracteristicas(item_caracteristicas);
		item.setItemImagen(item_imagen);
		item.setItemValorBase(item_valorbase);
		item.setItemValorVenta(item_valorventa);
		item.setItemFechaSubastaInicio(item_fecha_subasta_inicio);
		item.setItemFechaSubastaFin(item_fecha_subasta_fin);
		item.setItemGanadorDni(item_ganador_dni);
		item.setItemEstado(item_estado);
		mDAO.actualizar(item);
	}
	
	
	/**
	 * Cambiar datos de item notificacion
	 * 
	 * @param pro_id
	 * @throws Exception
	 */
	public void notificadoItem(Integer item_id) throws Exception {
		SubItem item = this.itemByID(item_id);
		item.setItemSms("Notificado");
		mDAO.actualizar(item);
	}
	
	

	/**
	 * Concoer gaandor del Item
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
	public String ganadorItem(Integer item_id, String item_estado,
			SubItem sitem, Integer ofer_id) throws Exception {
		String h = "";
		SubItem item = this.itemByID(item_id);

		List<SubItem> cond;
		cond = listitemById(sitem.getItemId());
		for (SubItem y : cond) {
			if (!y.getItemGanadorDni().equals("''")) {
				y.setItemGanadorDni(null);
				y.setItemEstado("A");
			}
		}
		if (item.getItemGanadorDni().equals("''")) {
			h = "Aún no hay Ofertas";
		} else {
			item.setItemGanadorDni(ofer_id);
			item.setItemEstado(item_estado);
			mDAO.actualizar(item);
			h = "Ganador establecido, dirígase a la lista de ganadores";
		}
		return h;
	}

	/**
	 * Cambiar datos de vehiculo
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
	public void ganadorItemAutom(Integer item_id, String item_estado,
			Integer automatico) throws Exception {
		SubItem item = this.itemByID(item_id);
		SubOferta ofer = this.ofertaByID(automatico);
		System.out.println("valor id oferta: " + automatico.toString());
		item.setItemGanadorDni(ofer.getOferId());
		item.setItemValorVenta(ofer.getOferValorOferta());
		item.setItemEstado(item_estado);
		item.setItemSms("No Notificado");
		mDAO.actualizar(item);
		h = "Ganador establecido, dirígase a la lista de ganadores";
	}

	@SuppressWarnings("unchecked")
	public List<SubItem> listitemById(Integer item_id) throws Exception {
		return mDAO
				.findWhere(SubItem.class, "o.itemId='" + item_id + "'", null);
	}

	/**
	 * Cambiar estado item
	 * 
	 * @param id_prod
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */
	public String cambioEstadoItem(Integer item_id) throws Exception {
		String h = "";
		SubItem item = itemByID(item_id);

		if (item.getItemEstado().equals("A")) {
			item.setItemEstado("I");
			h = "Estado Modificado";
		} else if (item.getItemEstado().equals("I")) {
			item.setItemEstado("A");
			h = "Estado Modificado";
		}
		mDAO.actualizar(item);
		return h;
	}

	// POSTULANTES
	/**
	 * listar todos los postulantes
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SubPostulante> findAllpostulantes() {
		return mDAO.findAll(SubPostulante.class);
	}

	/**
	 * buscar conductores por ID
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	public SubPostulante postulanteByID(String pos_id) throws Exception {
		return (SubPostulante) mDAO.findById(SubPostulante.class, pos_id);
	}

	/**
	 * Agrega conductores
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
	public void insertarPostulante(String pos_id, Timestamp pos_fecha_reg,
			String pos_nombre, String pos_apellido, String pos_direccion,
			String pos_correo, String pos_telefono, String pos_celular, String pos_password)
			throws Exception {
		SubPostulante pos = new SubPostulante();
		pos.setPosId(pos_id);
		pos.setPosFechaRegistro(pos_fecha_reg);
		pos.setPosNombre(pos_nombre);
		pos.setPosApellido(pos_apellido);
		pos.setPosDireccion(pos_direccion);
		pos.setPosCorreo(pos_correo);
		pos.setPosTelefono(pos_telefono);
		pos.setPosCelular(pos_celular);
		pos.setPosPassword(pos_password);
		pos.setPosEstado("A");
		mDAO.insertar(pos);
	}

	/**
	 * Cambiar datos de conductores
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
	public void editarPostulante(String pos_id, String pos_nombre,
			String pos_apellido, String pos_direccion, String pos_correo,
			String pos_telefono, String pos_celular, String pos_password, String pos_estado)
			throws Exception {
		System.out.println(pos_id);
		SubPostulante pos = this.postulanteByID(pos_id);
		pos.setPosNombre(pos_nombre);
		pos.setPosApellido(pos_apellido);
		pos.setPosDireccion(pos_direccion);
		pos.setPosCorreo(pos_correo);
		pos.setPosTelefono(pos_telefono);
		pos.setPosCelular(pos_celular);
		pos.setPosPassword(pos_password);
		pos.setPosEstado(pos_estado);
		mDAO.actualizar(pos);
	}

	/**
	 * Cambiar datos de conductores
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
	public void editarPostulanteedicion(String pos_id, String pos_nombre,
			String pos_apellido, String pos_direccion, String pos_correo,
			String pos_telefono, String pos_celular, String pos_password) throws Exception {
		System.out.println(pos_id);
		SubPostulante pos = this.postulanteByID(pos_id);
		pos.setPosNombre(pos_nombre);
		pos.setPosApellido(pos_apellido);
		pos.setPosDireccion(pos_direccion);
		pos.setPosCorreo(pos_correo);
		pos.setPosTelefono(pos_telefono);
		pos.setPosPassword(pos_password);
		pos.setPosCelular(pos_celular);
		mDAO.actualizar(pos);
	}

	/**
	 * Cambiar estado conductores
	 * 
	 * @param id_prod
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */
	public String cambioEstadoPostulante(String pos_id) throws Exception {
		String h = "";
		SubPostulante pos = postulanteByID(pos_id);

		if (pos.getPosEstado().equals("A")) {
			pos.setPosEstado("I");
			h = "Estado Modificado";
		} else if (pos.getPosEstado().equals("I")) {
			pos.setPosEstado("A");
			h = "Estado Modificado";
		}
		mDAO.actualizar(pos);
		return h;
	}

	// OFERTAS
	/**
	 * listar todos los postulantes
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SubOferta> findAllofertas() {
		return mDAO.findAll(SubOferta.class);
	}

	/**
	 * listar todos los postulantes
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SubOferta> findAllofertasOrdenadas() {
		return mDAO.findWhere(SubOferta.class, " 1=1 ",
				" o.oferFechaOferta desc");
	}

	/**
	 * listar todos los postulantes
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SubOferta> findAllofertasOrdenadasXUsuario(String usu_id) {
		return mDAO.findWhere(SubOferta.class, " o.subPostulante.posId = '"
				+ usu_id + "' ", " o.oferFechaOferta desc");
	}

	/**
	 * buscar conductores por ID
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	public SubOferta ofertaByID(Integer con_id) throws Exception {
		return (SubOferta) mDAO.findById(SubOferta.class, con_id);
	}

	/**
	 * listar todos las ofertas x item
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SubOferta> findAllofertasOrdenadasXItem(Integer item_id) {
		return mDAO.findWhere(SubOferta.class, " o.subItem.itemId = '"
				+ item_id + "' ", " o.oferFechaOferta desc");
	}

	/**
	 * listar todos las ofertas x item
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String ValorMaximoXItem(Integer item_id) {
		List<BigDecimal> list = mDAO
				.findJPQL("SELECT MAX(oferValorOferta) as valor FROM SubOferta o where o.subItem.itemId = "
						+ item_id + " ");
		System.out.println(list.size());
		System.out.println(list.get(0));
		if (list.get(0) == null) {
			return null;
		} else
			return list.get(0).toString();
	}

	/**
	 * listar todos las ofertas x item
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Integer ofertaXItem(Integer item_id) {
		System.out.println("entraaaaa");
		List<Integer> list = mDAO
				.findJPQL("SELECT o.oferId FROM SubOferta o"
						+ " WHERE o.oferValorOferta = (SELECT MAX(p.oferValorOferta) FROM SubOferta p where p.subItem.itemId = "
						+ item_id + " ) ");
		System.out.println(list.size());
		System.out.println(list.get(0));
		if (list.get(0) == null) {
			return 0;
		} else
			return list.get(0);
	}

	/**
	 * listar todos las ofertas x item
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Integer ofertaXPost(Integer item_id,String pos_id) {
		//System.out.println("entraaaaa");
		List<Integer> list = mDAO
				.findJPQL("SELECT o.oferId FROM SubOferta o WHERE o.oferValorOferta = (SELECT MAX(p.oferValorOferta) FROM SubOferta p where p.subItem.itemId = "
						+ item_id + " and p.subPostulante.posId = '"+pos_id+"' ) ");

		//System.out.println(list.size());
		//System.out.println(list.get(0));
		
		if (list.size() == 0) {
			return 0;
		} else
			return list.get(0);
	}

	/**
	 * Agrega conductores
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
	public void insertarOferta(BigDecimal ofer_valor_oferta,
			Timestamp ofer_fecha_oferta) throws Exception {
		SubOferta ofer = new SubOferta();
		ofer.setSubItem(sub_item);
		ofer.setSubPostulante(sub_pos);
		ofer.setOferValorOferta(ofer_valor_oferta);
		ofer.setOferFechaOferta(ofer_fecha_oferta);
		mDAO.insertar(ofer);
	}

	/**
	 * metodo para asignar el item
	 * 
	 * @param u
	 *            item a analizar
	 * @return true o false
	 */
	public SubItem asignarItem(Integer item_id) {
		try {
			sub_item = itemByID(item_id);
		} catch (Exception e) {
			// TODO Auto-generated prodch block
			e.printStackTrace();
		}
		return sub_item;
	}

	/**
	 * metodo para asignar el postulante
	 * 
	 * @param u
	 *            item a analizar
	 * @return true o false
	 */
	public SubPostulante asignarPostulante(String pos_id) {
		try {
			sub_pos = postulanteByID(pos_id);
		} catch (Exception e) {
			// TODO Auto-generated prodch block
			e.printStackTrace();
		}
		return sub_pos;
	}

	/**
	 * Cambiar datos de conductores
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
	public void editarOferta(Integer ofer_id, BigDecimal ofer_valor_oferta,
			Timestamp ofer_fecha_oferta) throws Exception {
		SubOferta ofer = this.ofertaByID(ofer_id);
		ofer.setSubItem(sub_item);
		ofer.setSubPostulante(sub_pos);
		ofer.setOferValorOferta(ofer_valor_oferta);
		ofer.setOferFechaOferta(ofer_fecha_oferta);
		mDAO.actualizar(ofer);
	}

	@SuppressWarnings("unchecked")
	public List<SubOferta> listadoGanadores() {
		return mDAO
				.findWhere(
						SubOferta.class,
						"o.oferId IN (SELECT i.itemGanadorDni FROM SubItem i WHERE i.itemEstado='I')",
						null);
	}

	@SuppressWarnings("unchecked")
	public List<SubOferta> listadoNoGanadores() {
		return mDAO
				.findWhere(
						SubOferta.class,
						"o.oferId NOT IN (SELECT i.itemGanadorDni FROM SubItem i WHERE i.itemEstado='I')",
						null);
	}
}
