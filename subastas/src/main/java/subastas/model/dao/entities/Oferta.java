package subastas.model.dao.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Oferta {

	private Integer oferId;
	private Date oferFechaOferta;
	private BigDecimal oferValorOferta;
	private Integer subItem;
	private String subPostulante;
	
	public Oferta() {}
	
	public Oferta(Integer oferId){
		this.oferId = oferId;
	}

	public Oferta(Integer oferId, Date oferFechaOferta, BigDecimal oferValorOferta, Integer subItem, String subPostulante){
		this.oferId=oferId;
		this.oferFechaOferta=oferFechaOferta;
		this.oferValorOferta=oferValorOferta;
		this.subItem=subItem;
		this.subPostulante=subPostulante;
	}

	public Integer getOferId() {
		return oferId;
	}

	public void setOferId(Integer oferId) {
		this.oferId = oferId;
	}

	public Date getOferFechaOferta() {
		return oferFechaOferta;
	}

	public void setOferFechaOferta(Date oferFechaOferta) {
		this.oferFechaOferta = oferFechaOferta;
	}

	public BigDecimal getOferValorOferta() {
		return oferValorOferta;
	}

	public void setOferValorOferta(BigDecimal oferValorOferta) {
		this.oferValorOferta = oferValorOferta;
	}

	public Integer getSubItem() {
		return subItem;
	}

	public void setSubItem(Integer subItem) {
		this.subItem = subItem;
	}

	public String getSubPostulante() {
		return subPostulante;
	}

	public void setSubPostulante(String subPostulante) {
		this.subPostulante = subPostulante;
	}
}
