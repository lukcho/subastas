package subastas.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sub_cat_cab database table.
 * 
 */
@Entity
@Table(name="sub_cat_cab")
@NamedQuery(name="SubCatCab.findAll", query="SELECT s FROM SubCatCab s")
public class SubCatCab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SUB_CAT_CAB_CATCID_GENERATOR", sequenceName="SEQ_SUB_CAT_CAB", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUB_CAT_CAB_CATCID_GENERATOR")
	@Column(name="catc_id")
	private Integer catcId;

	@Column(name="catc_nombre", length=200)
	private String catcNombre;

	@Column(name="catc_valor", columnDefinition="bpchar", length=4)
	private String catcValor;

	//bi-directional many-to-one association to SubCatDet
	@OneToMany(mappedBy="subCatCab")
	private List<SubCatDet> subCatDets;

	public SubCatCab() {
	}

	public Integer getCatcId() {
		return this.catcId;
	}

	public void setCatcId(Integer catcId) {
		this.catcId = catcId;
	}

	public String getCatcNombre() {
		return this.catcNombre;
	}

	public void setCatcNombre(String catcNombre) {
		this.catcNombre = catcNombre;
	}

	public String getCatcValor() {
		return this.catcValor;
	}

	public void setCatcValor(String catcValor) {
		this.catcValor = catcValor;
	}

	public List<SubCatDet> getSubCatDets() {
		return this.subCatDets;
	}

	public void setSubCatDets(List<SubCatDet> subCatDets) {
		this.subCatDets = subCatDets;
	}

	public SubCatDet addSubCatDet(SubCatDet subCatDet) {
		getSubCatDets().add(subCatDet);
		subCatDet.setSubCatCab(this);

		return subCatDet;
	}

	public SubCatDet removeSubCatDet(SubCatDet subCatDet) {
		getSubCatDets().remove(subCatDet);
		subCatDet.setSubCatCab(null);

		return subCatDet;
	}

}