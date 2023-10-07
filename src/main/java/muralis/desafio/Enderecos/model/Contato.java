package muralis.desafio.Enderecos.model;

public class Contato {
	
	private long id;
	
	private String tipo;
	
	private String texto;
	
	private long idCliente;

	public Contato(long id, String tipo, String texto, long idCliente) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.texto = texto;
		this.idCliente = idCliente;
	}
	
	public Contato(String tipo, String texto, long idCliente) {
		super();
		this.tipo = tipo;
		this.texto = texto;
		this.idCliente = idCliente;
	}
	

	public Contato() {
	
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	
	public String toString() {
		return "{ id: +"+id+"; tipo: "+tipo+"; texto: "+texto+"; idCliente: "+idCliente+" }";
	}
	
}
