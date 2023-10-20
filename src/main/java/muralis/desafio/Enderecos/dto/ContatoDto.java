package muralis.desafio.Enderecos.dto;

public class ContatoDto {
	
	private String tipo;
	
	private String texto;
	
	private long idCliente;
	
	public ContatoDto() {
		
	}

	public ContatoDto(String tipo, String texto, long idCliente) {
		super();
		this.tipo = tipo;
		this.texto = texto;
		this.idCliente = idCliente;
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
		return "{ tipo: "+tipo+"; texto: "+texto+"; idCliente: "+idCliente+" }";
	}
}