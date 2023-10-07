package muralis.desafio.Enderecos.repository;

import java.util.List;

import muralis.desafio.Enderecos.model.Contato;

public interface ContatoRepository {
	int salvar(Contato contato);
	
	int atualizar(Contato contatos);
	
	Contato encontrarPorId(Long id);
	
	int deletarPorId(Long id);
	
	List<Contato> todosOsContatos();
	
	//List<Contato> encontrarPorCliente(long idCliente);
	
	List<Contato> encontrarPorTexto(String nome);
	
	int deletarTodos();
}