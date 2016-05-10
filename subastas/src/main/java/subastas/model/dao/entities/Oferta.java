package subastas.model.dao.entities;

import java.sql.Timestamp;
import java.math.BigDecimal;


public class Oferta {

	private Integer oferId;
		private Timestamp oferFechaOferta;
		private BigDecimal oferValorOferta;
		private SubItem subItem;
		private SubPostulante subPostulante;

		public Oferta(BigDecimal oferValorOferta) {
			this.oferValorOferta = oferValorOferta;
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
