package subastas.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sub_item_fotos database table.
 * 
 */
@Entity
@Table(name="sub_item_fotos")
@NamedQuery(name="SubItemFoto.findAll", query="SELECT s FROM SubItemFoto s")
public class SubItemFoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SUB_ITEM_FOTOS_ITEMFID_GENERATOR", sequenceName="SEQ_SUB_ITEMS_FOTOS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUB_ITEM_FOTOS_ITEMFID_GENERATOR")
	@Column(name="itemf_id")
	private Integer itemfId;

	@Column(name="itemf_direccion", length=200)
	private String itemfDireccion;

	@Column(name="itemf_estado", columnDefinition="bpchar", length=1)
	private String itemfEstado;

	@Column(name="itemf_mostrar")
	private Boolean itemfMostrar;

	@Column(name="itemf_nombre", length=200)
	private String itemfNombre;

	//bi-directional many-to-one association to SubItem
	@ManyToOne
	@JoinColumn(name="item_id")
	private SubItem subItem;

	public SubItemFoto() {
	}

	public Integer getItemfId() {
		return this.itemfId;
	}

	public void setItemfId(Integer itemfId) {
		this.itemfId = itemfId;
	}

	public String getItemfDireccion() {
		return this.itemfDireccion;
	}

	public void setItemfDireccion(String itemfDireccion) {
		this.itemfDireccion = itemfDireccion;
	}

	public String getItemfEstado() {
		return this.itemfEstado;
	}

	public void setItemfEstado(String itemfEstado) {
		this.itemfEstado = itemfEstado;
	}

	public Boolean getItemfMostrar() {
		return this.itemfMostrar;
	}

	public void setItemfMostrar(Boolean itemfMostrar) {
		this.itemfMostrar = itemfMostrar;
	}

	public String getItemfNombre() {
		return this.itemfNombre;
	}

	public void setItemfNombre(String itemfNombre) {
		this.itemfNombre = itemfNombre;
	}

	public SubItem getSubItem() {
		return this.subItem;
	}

	public void setSubItem(SubItem subItem) {
		this.subItem = subItem;
	}

}