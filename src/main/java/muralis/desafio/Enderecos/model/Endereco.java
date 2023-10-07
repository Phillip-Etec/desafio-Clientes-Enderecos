package muralis.desafio.Enderecos.model;

public class Endereco {
	
	private long id;
	
	private String cep;
	
	private String logradouro;
	
	private String cidade;
	
	private String numero;
	
	private String complemento;
	
	private long idCliente;
	
	public Endereco() {
		
	}
	
	public Endereco(String cep, String complemento) {
		setCep(cep);
		setComplemento(complemento);
	}
	
	public Endereco(long id, String cep, String complemento, long idCliente) {
		setId(id);
		setCep(cep);
		setComplemento(complemento);
		setIdCliente(idCliente);
	}
	
	public Endereco(String cep, String complemento, String numero, long idCliente) {
		setCep(cep);
		setComplemento(complemento);
		setNumero(numero);
		setIdCliente(idCliente);
	}
	
	public Endereco(String cep, String logradouro, String cidade, String numero, String complemento, long idCliente) {
		setCep(cep);
		setLogradouro(logradouro);
		setCidade(cidade);
		setNumero(numero);
		setComplemento(complemento);
		setIdCliente(idCliente);
	}
	
	public Endereco(String cep, String logradouro, String cidade, String numero, String complemento) {
		setCep(cep);
		setLogradouro(logradouro);
		setCidade(cidade);
		setNumero(numero);
		setComplemento(complemento);
	}
	
	public Endereco(long id, String cep, String logradouro, String cidade, String numero, String complemento, long idCliente) {
		setId(idCliente);
		setCep(cep);
		setLogradouro(logradouro);
		setCidade(cidade);
		setNumero(numero);
		setComplemento(complemento);
		setIdCliente(idCliente);
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	
	public long getIdCliente() {
		return idCliente;
	}
	
	public void AtualizarEndereco(String cep, String logradouro, String cidade) {
		setCep(cep);
		setLogradouro(logradouro);
		setCidade(cidade);
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	@Override
	public String toString() {
		return "Endereço { id: "+id+"; cep: "+cep+"; logradouro: "+logradouro+"; cidade: "+cidade+"; numero"+numero+"; complemento: "+complemento+"; idCliente: "+idCliente+" }";
	}
}
