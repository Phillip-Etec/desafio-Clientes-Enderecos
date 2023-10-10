package muralis.desafio.Enderecos.model;

public class Contato {
	
	private long id;
	
	private TipoContato tipo;
	
	private long idTipoContato;
	
	private String texto;
	
	private long idCliente;

	public Contato(long id, TipoContato tipo, String texto, long idCliente) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.texto = texto;
		this.idCliente = idCliente;
	}
	
	public Contato(TipoContato tipo, String texto, long idCliente) {
		super();
		this.tipo = tipo;
		this.texto = texto;
		this.idCliente = idCliente;
	}
	
	public Contato(String tipo, String texto, long idCliente) {
		super();
		TipoContato tipoContato = new TipoContato(tipo);
		this.tipo = tipoContato;
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

	public TipoContato getTipo() {
		return tipo;
	}

	public void setTipo(TipoContato tipo) {
		this.tipo = tipo;
	}
	
	public void setIdTipoContato(long idTipoContato) {
		this.idTipoContato = idTipoContato;
	}
	
	public long getIdTipoContato() {
		return idTipoContato;
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
		return "{ id: +"+id+"; tipo: "+tipo.getTipo()+"; texto: "+texto+"; idCliente: "+idCliente+" }";
	}
	
}
