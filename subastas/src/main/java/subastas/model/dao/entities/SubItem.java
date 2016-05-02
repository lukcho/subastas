package subastas.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the sub_items database table.
 * 
 */
@Entity
@Table(name="sub_items")
@NamedQuery(name="SubItem.findAll", query="SELECT s FROM SubItem s")
public class SubItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SUB_ITEMS_ITEMID_GENERATOR", sequenceName="SEQ_SUB_ITEMS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUB_ITEMS_ITEMID_GENERATOR")
	@Column(name="item_id")
	private Integer itemId;

	@Column(name="irem_caracteristicas", length=255)
	private String iremCaracteristicas;

	@Column(name="item_descripcion", length=255)
	private String itemDescripcion;

	@Column(name="item_estado", columnDefinition="bpchar", length=1)
	private String itemEstado;

	@Column(name="item_fecha_subasta_fin")
	private Timestamp itemFechaSubastaFin;

	@Column(name="item_fecha_subasta_inicio")
	private Timestamp itemFechaSubastaInicio;

	@Column(name="item_ganador_dni", length=255)
	private String itemGanadorDni;

	@Column(name="item_imagen", length=255)
	private String itemImagen;

	@Column(name="item_nombre", length=255)
	private String itemNombre;

	@Column(name="item_usuario_registro", length=255)
	private String itemUsuarioRegistro;

	@Column(name="item_valor_base")
	private BigDecimal itemValorBase;

	@Column(name="item_valor_venta")
	private BigDecimal itemValorVenta;

	//bi-directional many-to-one association to SubOferta
	@OneToMany(mappedBy="subItem")
	private List<SubOferta> subOfertas;

	public SubItem() {
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getIremCaracteristicas() {
		return this.iremCaracteristicas;
	}

	public void setIremCaracteristicas(String iremCaracteristicas) {
		this.iremCaracteristicas = iremCaracteristicas;
	}

	public String getItemDescripcion() {
		return this.itemDescripcion;
	}

	public void setItemDescripcion(String itemDescripcion) {
		this.itemDescripcion = itemDescripcion;
	}

	public String getItemEstado() {
		return this.itemEstado;
	}

	public void setItemEstado(String itemEstado) {
		this.itemEstado = itemEstado;
	}

	public Timestamp getItemFechaSubastaFin() {
		return this.itemFechaSubastaFin;
	}

	public void setItemFechaSubastaFin(Timestamp itemFechaSubastaFin) {
		this.itemFechaSubastaFin = itemFechaSubastaFin;
	}

	public Timestamp getItemFechaSubastaInicio() {
		return this.itemFechaSubastaInicio;
	}

	public void setItemFechaSubastaInicio(Timestamp itemFechaSubastaInicio) {
		this.itemFechaSubastaInicio = itemFechaSubastaInicio;
	}

	public String getItemGanadorDni() {
		return this.itemGanadorDni;
	}

	public void setItemGanadorDni(String itemGanadorDni) {
		this.itemGanadorDni = itemGanadorDni;
	}

	public String getItemImagen() {
		return this.itemImagen;
	}

	public void setItemImagen(String itemImagen) {
		this.itemImagen = itemImagen;
	}

	public String getItemNombre() {
		return this.itemNombre;
	}

	public void setItemNombre(String itemNombre) {
		this.itemNombre = itemNombre;
	}

	public String getItemUsuarioRegistro() {
		return this.itemUsuarioRegistro;
	}

	public void setItemUsuarioRegistro(String itemUsuarioRegistro) {
		this.itemUsuarioRegistro = itemUsuarioRegistro;
	}

	public BigDecimal getItemValorBase() {
		return this.itemValorBase;
	}

	public void setItemValorBase(BigDecimal itemValorBase) {
		this.itemValorBase = itemValorBase;
	}

	public BigDecimal getItemValorVenta() {
		return this.itemValorVenta;
	}

	public void setItemValorVenta(BigDecimal itemValorVenta) {
		this.itemValorVenta = itemValorVenta;
	}

	public List<SubOferta> getSubOfertas() {
		return this.subOfertas;
	}

	public void setSubOfertas(List<SubOferta> subOfertas) {
		this.subOfertas = subOfertas;
	}

	public SubOferta addSubOferta(SubOferta subOferta) {
		getSubOfertas().add(subOferta);
		subOferta.setSubItem(this);

		return subOferta;
	}

	public SubOferta removeSubOferta(SubOferta subOferta) {
		getSubOfertas().remove(subOferta);
		subOferta.setSubItem(null);

		return subOferta;
	}

}