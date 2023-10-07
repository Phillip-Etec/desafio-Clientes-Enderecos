package muralis.desafio.Enderecos.model;
/*
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Cliente {


	private long id;
	
	private String nome;
	
	private String dataCadastro;
	
	public Cliente() {
		
	}
	
	public Cliente(long id, String nome) {
		setId(id);
		setNome(nome);
		setDataCadastro(LocalDate.now().toString());
	}
	
	public Cliente(String nome) {
		setNome(nome);
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate agora = LocalDate.now();
		setDataCadastro(agora.format(pattern));
	}
	
	public Cliente(String nome, String data) {
		setNome(nome);
		//LocalDate momento = LocalDate.parse(data);
		setDataCadastro(data);
	}
	
	public Cliente(long id, String nome,String dataCadastro) {
		setId(id);
		setNome(nome);
		//LocalDate momento = LocalDate.parse(data);
		setDataCadastro(dataCadastro);
	}
	
	public long getId() {
		return id;
	}

	public String getNome() {
		return this.nome;
	}

	
	public String getDataCadastro() {
		return this.dataCadastro;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "Cliente [ id: " + String.valueOf(id) + "; nome: " + nome + "; dataCadastro: " + dataCadastro.toString();
	}
}*/

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Cliente {


	private long id;
	
	private String nome;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataCadastro;
	
	public Cliente() {
		
	}
	
	public Cliente(long id, String nome) {
		setId(id);
		setNome(nome);
		setDataCadastro(LocalDateTime.now());
	}
	
	public Cliente(String nome) {
		setNome(nome);
		setDataCadastro(LocalDateTime.now());
	}
	
	public Cliente(long id, String nome,LocalDateTime dataCadastro) {
		setId(id);
		setNome(nome);
		setDataCadastro(dataCadastro);
	}
	
	public Cliente(long id, String nome, String dataCadastro) {
		setId(id);
		setNome(nome);
		dataCadastro = dataCadastro.replace(' ', 'T');
		setDataCadastro(LocalDateTime.parse(dataCadastro));
	}
	
	public Cliente(String nome,LocalDateTime dataCadastro) {
		setNome(nome);
		setDataCadastro(dataCadastro);
	}
	
	public Cliente(String nome,String dataCadastro) {
		setId(id);
		setNome(nome);
		if(dataCadastro.indexOf(' ') != -1)
			dataCadastro = dataCadastro.replace(' ', 'T');
		
		setDataCadastro(LocalDateTime.parse(dataCadastro));
	}
	
	
	public long getId() {
		return id;
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

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "Cliente [ id: " + String.valueOf(id) + "; nome: " + nome + "; dataCadastro: " + dataCadastro.toString();
	}
}