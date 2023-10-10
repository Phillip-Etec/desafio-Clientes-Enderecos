package muralis.desafio.Enderecos.model;

import org.springframework.data.relational.core.mapping.Table;

@Table(name = "tipo_contato")
public class TipoContato {
	
	private long id;
	
	private String tipo;
	
	public TipoContato() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public TipoContato(long id, String tipo) {
		setId(id);
		setTipo(tipo);
	}
	
	public TipoContato(String tipo) {
		setTipo(tipo);
	}
	
	@Override
	public String toString() {
		return "{ id: "+id+"; tipo: "+tipo+" }";
	}
}
