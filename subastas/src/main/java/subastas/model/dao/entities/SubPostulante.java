package subastas.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the sub_postulantes database table.
 * 
 */
@Entity
@Table(name="sub_postulantes")
@NamedQuery(name="SubPostulante.findAll", query="SELECT s FROM SubPostulante s")
public class SubPostulante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SUB_POSTULANTES_POSID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUB_POSTULANTES_POSID_GENERATOR")
	@Column(name="pos_id", length=200)
	private String posId;

	@Column(name="pos_apellido", length=200)
	private String posApellido;

	@Column(name="pos_area", length=200)
	private String posArea;

	@Column(name="pos_correo", length=200)
	private String posCorreo;

	@Column(name="pos_direccion", length=200)
	private String posDireccion;

	@Column(name="pos_estado", columnDefinition="bpchar", length=1)
	private String posEstado;

	@Column(name="pos_fecha_registro")
	private Timestamp posFechaRegistro;

	@Column(name="pos_gerencia", length=200)
	private String posGerencia;

	@Column(name="pos_institucion", length=200)
	private String posInstitucion;

	@Column(name="pos_nombre", length=200)
	private String posNombre;

	@Column(name="pos_password", length=200)
	private String posPassword;

	@Column(name="pos_telefono", length=20)
	private String posTelefono;

	//bi-directional many-to-one association to SubOferta
	@OneToMany(mappedBy="subPostulante")
	private List<SubOferta> subOfertas;

	public SubPostulante() {
	}

	public String getPosId() {
		return this.posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public String getPosApellido() {
		return this.posApellido;
	}

	public void setPosApellido(String posApellido) {
		this.posApellido = posApellido;
	}

	public String getPosArea() {
		return this.posArea;
	}

	public void setPosArea(String posArea) {
		this.posArea = posArea;
	}

	public String getPosCorreo() {
		return this.posCorreo;
	}

	public void setPosCorreo(String posCorreo) {
		this.posCorreo = posCorreo;
	}

	public String getPosDireccion() {
		return this.posDireccion;
	}

	public void setPosDireccion(String posDireccion) {
		this.posDireccion = posDireccion;
	}

	public String getPosEstado() {
		return this.posEstado;
	}

	public void setPosEstado(String posEstado) {
		this.posEstado = posEstado;
	}

	public Timestamp getPosFechaRegistro() {
		return this.posFechaRegistro;
	}

	public void setPosFechaRegistro(Timestamp posFechaRegistro) {
		this.posFechaRegistro = posFechaRegistro;
	}

	public String getPosGerencia() {
		return this.posGerencia;
	}

	public void setPosGerencia(String posGerencia) {
		this.posGerencia = posGerencia;
	}

	public String getPosInstitucion() {
		return this.posInstitucion;
	}

	public void setPosInstitucion(String posInstitucion) {
		this.posInstitucion = posInstitucion;
	}

	public String getPosNombre() {
		return this.posNombre;
	}

	public void setPosNombre(String posNombre) {
		this.posNombre = posNombre;
	}

	public String getPosPassword() {
		return this.posPassword;
	}

	public void setPosPassword(String posPassword) {
		this.posPassword = posPassword;
	}

	public String getPosTelefono() {
		return this.posTelefono;
	}

	public void setPosTelefono(String posTelefono) {
		this.posTelefono = posTelefono;
	}

	public List<SubOferta> getSubOfertas() {
		return this.subOfertas;
	}

	public void setSubOfertas(List<SubOferta> subOfertas) {
		this.subOfertas = subOfertas;
	}

	public SubOferta addSubOferta(SubOferta subOferta) {
		getSubOfertas().add(subOferta);
		subOferta.setSubPostulante(this);

		return subOferta;
	}

	public SubOferta removeSubOferta(SubOferta subOferta) {
		getSubOfertas().remove(subOferta);
		subOferta.setSubPostulante(null);

		return subOferta;
	}

}