package subastas.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the sub_ofertas database table.
 * 
 */
@Entity
@Table(name="sub_ofertas")
@NamedQuery(name="SubOferta.findAll", query="SELECT s FROM SubOferta s")
public class SubOferta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SUB_OFERTAS_OFERID_GENERATOR", sequenceName="SEQ_SUB_OFERTAS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUB_OFERTAS_OFERID_GENERATOR")
	@Column(name="ofer_id")
	private Integer oferId;

	@Column(name="ofer_fecha_oferta")
	private Timestamp oferFechaOferta;

	@Column(name="ofer_valor_oferta")
	private BigDecimal oferValorOferta;

	//bi-directional many-to-one association to SubItem
	@ManyToOne
	@JoinColumn(name="item_id")
	private SubItem subItem;

	//bi-directional many-to-one association to SubPostulante
	@ManyToOne
	@JoinColumn(name="pos_id")
	private SubPostulante subPostulante;

	public SubOferta() {
	}

	public Integer getOferId() {
		return this.oferId;
	}

	public void setOferId(Integer oferId) {
		this.oferId = oferId;
	}

	public Timestamp getOferFechaOferta() {
		return this.oferFechaOferta;
	}

	public void setOferFechaOferta(Timestamp oferFechaOferta) {
		this.oferFechaOferta = oferFechaOferta;
	}

	public BigDecimal getOferValorOferta() {
		return this.oferValorOferta;
	}

	public void setOferValorOferta(BigDecimal oferValorOferta) {
		this.oferValorOferta = oferValorOferta;
	}

	public SubItem getSubItem() {
		return this.subItem;
	}

	public void setSubItem(SubItem subItem) {
		this.subItem = subItem;
	}

	public SubPostulante getSubPostulante() {
		return this.subPostulante;
	}

	public void setSubPostulante(SubPostulante subPostulante) {
		this.subPostulante = subPostulante;
	}

}