package subastas.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sub_cat_det database table.
 * 
 */
@Entity
@Table(name="sub_cat_det")
@NamedQuery(name="SubCatDet.findAll", query="SELECT s FROM SubCatDet s")
public class SubCatDet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SUB_CAT_DET_CATDID_GENERATOR", sequenceName="SEQ_SUB_CAT_DET", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUB_CAT_DET_CATDID_GENERATOR")
	@Column(name="catd_id")
	private Integer catdId;

	@Column(name="catd_estado", columnDefinition="bpchar", length=1)
	private String catdEstado;

	@Column(name="catd_id_padre")
	private Integer catdIdPadre;

	@Column(name="catd_nombre", length=200)
	private String catdNombre;

	//bi-directional many-to-one association to SubCatCab
	@ManyToOne
	@JoinColumn(name="catc_id")
	private SubCatCab subCatCab;

	public SubCatDet() {
	}

	public Integer getCatdId() {
		return this.catdId;
	}

	public void setCatdId(Integer catdId) {
		this.catdId = catdId;
	}

	public String getCatdEstado() {
		return this.catdEstado;
	}

	public void setCatdEstado(String catdEstado) {
		this.catdEstado = catdEstado;
	}

	public Integer getCatdIdPadre() {
		return this.catdIdPadre;
	}

	public void setCatdIdPadre(Integer catdIdPadre) {
		this.catdIdPadre = catdIdPadre;
	}

	public String getCatdNombre() {
		return this.catdNombre;
	}

	public void setCatdNombre(String catdNombre) {
		this.catdNombre = catdNombre;
	}

	public SubCatCab getSubCatCab() {
		return this.subCatCab;
	}

	public void setSubCatCab(SubCatCab subCatCab) {
		this.subCatCab = subCatCab;
	}

}