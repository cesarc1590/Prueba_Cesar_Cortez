package com.sorteo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;



import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name ="Persona")
public class Persona  implements Serializable {

	
	private static final long serialVersionUID = -3168081865086024085L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	private Long id;
	
	@Column 
	@NotBlank
    private String tipo_documento;
	@Column 
	private int n_documento;
	
	@Column 
	@NotBlank
	private String nombres;
	
	@Column 
	@NotBlank
	private String apellidos;
	
	@Column
	@NotBlank
	private String  fecha_nacimiento;
	
	@Column 
	private String  gano;
	
	@Column 
	private String  premio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public int getN_documento() {
		return n_documento;
	}

	public void setN_documento(int n_documento) {
		this.n_documento = n_documento;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getGano() {
		return gano;
	}

	public void setGano(String gano) {
		this.gano = gano;
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", tipo_documento=" + tipo_documento + ", n_documento=" + n_documento
				+ ", nombres=" + nombres + ", apellidos=" + apellidos + ", fecha_nacimiento=" + fecha_nacimiento
				+ ", gano=" + gano + ", premio=" + premio + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((fecha_nacimiento == null) ? 0 : fecha_nacimiento.hashCode());
		result = prime * result + ((gano == null) ? 0 : gano.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + n_documento;
		result = prime * result + ((nombres == null) ? 0 : nombres.hashCode());
		result = prime * result + ((premio == null) ? 0 : premio.hashCode());
		result = prime * result + ((tipo_documento == null) ? 0 : tipo_documento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (fecha_nacimiento == null) {
			if (other.fecha_nacimiento != null)
				return false;
		} else if (!fecha_nacimiento.equals(other.fecha_nacimiento))
			return false;
		if (gano == null) {
			if (other.gano != null)
				return false;
		} else if (!gano.equals(other.gano))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (n_documento != other.n_documento)
			return false;
		if (nombres == null) {
			if (other.nombres != null)
				return false;
		} else if (!nombres.equals(other.nombres))
			return false;
		if (premio == null) {
			if (other.premio != null)
				return false;
		} else if (!premio.equals(other.premio))
			return false;
		if (tipo_documento == null) {
			if (other.tipo_documento != null)
				return false;
		} else if (!tipo_documento.equals(other.tipo_documento))
			return false;
		return true;
	}

	

	


	
}
