package muralis.desafio.Enderecos.dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonFormat;

import muralis.desafio.Enderecos.model.Cliente;

public class ClienteDto {
	
	private long id;

	private String nome;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataCadastro;
	
	public ClienteDto() {
		
	}
	
	public ClienteDto(long id, String nome) {
		setId(id);
		setNome(nome);
		setDataCadastro(LocalDateTime.now());
	}
	
	public ClienteDto(String nome) {
		setNome(nome);
		setDataCadastro(LocalDateTime.now());
	}
	
	public ClienteDto(long id, String nome,LocalDateTime dataCadastro) {
		setId(id);
		setNome(nome);
		setDataCadastro(dataCadastro);
	}
	
	public ClienteDto(long id, String nome, String dataCadastro) {
		setId(id);
		setNome(nome);
		dataCadastro = dataCadastro.replace(' ', 'T');
		setDataCadastro(LocalDateTime.parse(dataCadastro));
	}
	
	public ClienteDto(String nome,LocalDateTime dataCadastro) {
		setNome(nome);
		setDataCadastro(dataCadastro);
	}
	
	public ClienteDto(String nome,String dataCadastro) {
		setNome(nome);
		if(dataCadastro.indexOf(' ') != -1)
			dataCadastro = dataCadastro.replace(' ', 'T');
		
		setDataCadastro(LocalDateTime.parse(dataCadastro));
	}
	

	public String getNome() {
		return nome;
	}

	
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public long getId() {
		return this.id;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "Cliente [ nome: " + nome + "; dataCadastro: " + dataCadastro.toString();
	}

}