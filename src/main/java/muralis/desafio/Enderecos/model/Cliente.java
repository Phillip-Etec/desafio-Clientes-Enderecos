package muralis.desafio.Enderecos.model;

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
}
/*
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Cliente {


	private long id;
	
	private String nome;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dataCadastro;
	
	public Cliente() {
		
	}
	
	public Cliente(long id, String nome) {
		setId(id);
		setNome(nome);
		setDataCadastro(LocalDate.now());
	}
	
	public Cliente(String nome) {
		setNome(nome);
		setDataCadastro(LocalDate.now());
	}
	
	public Cliente(long id, String nome,LocalDate dataCadastro) {
		setId(id);
		setNome(nome);
		setDataCadastro(dataCadastro);
	}
	
	public Cliente(long id, String nome,String dataCadastro) {
		setId(id);
		setNome(nome);
		setDataCadastro(LocalDate.parse(dataCadastro));
	}
	
	public Cliente(String nome,LocalDate dataCadastro) {
		setNome(nome);
		setDataCadastro(dataCadastro);
	}
	
	public Cliente(String nome,String dataCadastro) {
		setId(id);
		setNome(nome);
		setDataCadastro(LocalDate.parse(dataCadastro));
	}
	
	
	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "Cliente [ id: " + String.valueOf(id) + "; nome: " + nome + "; dataCadastro: " + dataCadastro.toString();
	}
}*/