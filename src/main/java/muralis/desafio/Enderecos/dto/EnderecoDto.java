package muralis.desafio.Enderecos.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import muralis.desafio.Enderecos.model.Endereco;

public class EnderecoDto {
	
	@Autowired
	ModelMapper mapper = new ModelMapper();
	
	private String cep;
	
	private String numero;
	
	private String complemento;
	
	private long idCliente;
	
	public EnderecoDto() {
		
	}
	
	public EnderecoDto(String cep, String complemento) {
		setCep(cep);
		setComplemento(complemento);
	}
	
	public EnderecoDto(long id, String cep, String complemento, long idCliente) {
		setId(id);
		setCep(cep);
		setComplemento(complemento);
		setIdCliente(idCliente);
	}
	
	public EnderecoDto(String cep, String numero, String complemento, long idCliente) {
		setCep(cep);
		setComplemento(complemento);
		setNumero(numero);
		setIdCliente(idCliente);
	}
	
	public EnderecoDto(String cep, String logradouro, String cidade, String numero, String complemento, long idCliente) {
		setCep(cep);
		setLogradouro(logradouro);
		setCidade(cidade);
		setNumero(numero);
		setComplemento(complemento);
		setIdCliente(idCliente);
	}
	
	public EnderecoDto(long id, String cep, String logradouro, String cidade, String numero, String complemento, long idCliente) {
		setId(idCliente);
		setCep(cep);
		setLogradouro(logradouro);
		setCidade(cidade);
		setNumero(numero);
		setComplemento(complemento);
		setIdCliente(idCliente);
	}
	
	public void setId(long id) {
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

	public void setLogradouro(String logradouro) {
	}

	public void setCidade(String cidade) {
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
		return "Endereço { cep: "+cep+"; numero"+numero+"; complemento: "+complemento+"; idCliente: "+idCliente+" }";
	}
	
	public Endereco converterParaEndereco() {
		Endereco endereco = this.mapper.map(this, Endereco.class);
		
		return endereco;
	}
	
}
