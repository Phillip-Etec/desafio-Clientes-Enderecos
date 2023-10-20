package muralis.desafio.Enderecos.dto;

public class ContatoDto {
	
	private long id;
	
	private String tipo;
	
	private String texto;
	
	private long idCliente;
	
	public ContatoDto() {
		
	}

	public ContatoDto(long id, String tipo, String texto, long idCliente) {
		super();
		setId(id);
		setTipo(tipo);
		setTexto(texto);
		setIdCliente(idCliente);
	}


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	
	public String toString() {
		return "{ id: "+id+"; tipo: "+tipo+"; texto: "+texto+" }";
	}
}
