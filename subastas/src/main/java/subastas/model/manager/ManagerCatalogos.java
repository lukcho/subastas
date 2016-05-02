package subastas.model.manager;

import subastas.model.dao.entities.*;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerCatalogos {

	@EJB
	private ManagerDAO mDAO;

	private static SubCatCab fab_cat;

	private static SubCatDet fab_cati;

	public String tipo;
	String h = "";

	public ManagerCatalogos() {
	}

	// CATALOGO

	/**
	 * buscar todos catalogos
	 * 
	 * @param cat_id
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public List<SubCatCab> findCatalogo() {
		return mDAO.findWhere(SubCatCab.class, "1=1", null);
	}
	
	/**
	 * buscar todos catalogos
	 * 
	 * @param catId
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public List<SubCatCab> findCatalogosByCatalogo(Integer catId) {
		return mDAO.findWhere(SubCatCab.class, "o.catcId= '"+catId+"'", null);
	}

	/**
	 * listar todos los catalogos
	 * 
	 * @param id_cat
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SubCatCab> findAllCatalogos() {
		return mDAO.findAll(SubCatCab.class);
	}

	/**
	 * buscar Catalogos por ID
	 * 
	 * @param id_cat
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public SubCatCab CatalogoByID(Integer cat_id) throws Exception {
		return (SubCatCab) mDAO.findById(SubCatCab.class, cat_id);
	}

	/**
	 * Agrega Catalogo
	 * 
	 * @param cat_id
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public void insertarCatalogos(String nombre, String valor) throws Exception {
		SubCatCab cat = new SubCatCab();
		cat.setCatcNombre(nombre);
		cat.setCatcValor(valor);
		mDAO.insertar(cat);
	}

	/**
	 * Cambiar datos de Catalogo
	 * 
	 * @param id_cat
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public void editarCatalogo(Integer cat_id, String nombre, String valor)
			throws Exception {
		SubCatCab cat = this.CatalogoByID(cat_id);
		cat.setCatcNombre(nombre);
		cat.setCatcValor(valor);
		mDAO.actualizar(cat);
	}

	/**
	 * Cambiar estado Catalogo
	 * 
	 * @param id_cat
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */
	public String cambioEstadocat(Integer cat_id) throws Exception {
		String h = "";
		SubCatCab cat = CatalogoByID(cat_id);

		if (cat.getCatcValor().equals("A")) {
			cat.setCatcValor("I");
			h = "Estado del Catalogo Modificado";
		} else if (cat.getCatcValor().equals("I")) {
			cat.setCatcValor("A");
			h = "Estado del Registro Modificado";
		}
		mDAO.actualizar(cat);
		return h;
	}

	/**
	 * Verifica si el Catalogo esta activado
	 * 
	 * @param u
	 *            Catalogo a analizar
	 * @return true o false
	 */
	public boolean escatActivo(SubCatCab u) {
		boolean resp = false;
		if (u.getCatcValor().equals("A")) {
			resp = true;
		}
		return resp;
	}

	// CATALOGOITEMS
	/**
	 * buscar todos catalogosItems
	 * 
	 * @param catId
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public List<SubCatDet> findCatalogoItemsByCatalogo(Integer catId) {
		return mDAO.findWhere(SubCatDet.class, "o.transCatCab.catcId= "
				+ catId + " and catdIdPadre= 0", null);
	}

	/**
	 * buscar todos catalogosItemsitems
	 * 
	 * @param catId
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public List<SubCatDet> findCatalogoItemshijosByCatalogo(Integer cati_Id) {
		return mDAO.findWhere(SubCatDet.class, "o.catdIdPadre = " + cati_Id,
				null);
	}

	/**
	 * listar todos los catalogositems
	 * 
	 * @param id_cat
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SubCatDet> findAllCatalogoItems() {
		return mDAO.findAll(SubCatDet.class);
	}

	/**
	 * buscar Catalogositems por ID
	 * 
	 * @param id_cat
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public SubCatDet CatalogoItemsByID(Integer cati_id) throws Exception {
		return (SubCatDet) mDAO.findById(SubCatDet.class, cati_id);
	}

	/**
	 * Agrega Catalogoitems
	 * 
	 * @param cat_id
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public void insertarCatalogoItems(String nombre, Integer id_padre)
			throws Exception {
		SubCatDet cati = new SubCatDet();
		if (id_padre == null) {
			cati.setCatdNombre(nombre);
			cati.setCatdIdPadre(0);
			cati.setSubCatCab(fab_cat);
			cati.setCatdEstado("A");
			mDAO.insertar(cati);
		} else
			cati.setCatdNombre(nombre);
		cati.setCatdIdPadre(id_padre);
		cati.setSubCatCab(fab_cat);
		cati.setCatdEstado("A");
		mDAO.insertar(cati);
	}

	/**
	 * Cambiar datos de CatalogoItems
	 * 
	 * @param id_cat
	 * @param nombre
	 * @param valor
	 * @throws Exception
	 */
	public void editarCatalogoItems(Integer cati_id, String nombre,
			String estado, Integer id_padre) throws Exception {
		SubCatDet cati = this.CatalogoItemsByID(cati_id);
		if (id_padre == null || id_padre == 0) {
			cati.setCatdNombre(nombre);
			cati.setCatdIdPadre(0);
			cati.setSubCatCab(fab_cat);
			cati.setCatdEstado(estado);
		} else
			cati.setCatdNombre(nombre);
		cati.setCatdIdPadre(id_padre);
		cati.setSubCatCab(fab_cat);
		cati.setCatdEstado(estado);
		mDAO.actualizar(cati);
	}

	/**
	 * Cambiar estado CatalogoItems
	 * 
	 * @param id_cat
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @throws Exception
	 */
	public String cambioEstadocati(Integer cati_id) throws Exception {
		String h = "";
		SubCatDet cati = CatalogoItemsByID(cati_id);

		if (cati.getCatdEstado().equals("A")) {
			cati.setCatdEstado("I");
			h = "Estado del Catálogo Modificado";
		} else if (cati.getCatdEstado().equals("I")) {
			cati.setCatdEstado("A");
			h = "Estado del Catálogo Modificado";
		}
		mDAO.actualizar(cati);
		return h;
	}

	/**
	 * Verifica si el CatalogoItems esta activado
	 * 
	 * @param u
	 *            Catalogo a analizar
	 * @return true o false
	 */
	public boolean escatiActivo(SubCatDet u) {
		boolean resp = false;
		if (u.getCatdEstado().equals("A")) {
			resp = true;
		}
		return resp;
	}

	/**
	 * metodo para asignar el catalogo al un item
	 * 
	 * @param u
	 *            Catalogo a analizar
	 * @return true o false
	 */
	public SubCatCab asignarcatalogo(Integer cat_id) {
		try {
			fab_cat = CatalogoByID(cat_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fab_cat;
	}

	// CATALOGOITEM

	/**
	 * buscar catalogo_item por ID
	 * 
	 * @param prod_id
	 * @throws Exception
	 */
	public SubCatDet CatalogoItemByID(Integer cati_id) throws Exception {
		return (SubCatDet) mDAO.findById(SubCatDet.class, cati_id);
	}

	/**
	 * metodo para asignar el producto a un item
	 * 
	 * @param u
	 *            prodalogo a analizar
	 * @return true o false
	 */
	public SubCatDet asignarcati(Integer cati_id) {
		try {
			fab_cati = CatalogoItemByID(cati_id);
		} catch (Exception e) {
			// TODO Auto-generated prodch block
			e.printStackTrace();
		}
		return fab_cati;
	}
}
