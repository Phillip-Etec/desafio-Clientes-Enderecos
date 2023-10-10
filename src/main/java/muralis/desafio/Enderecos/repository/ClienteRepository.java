package muralis.desafio.Enderecos.repository;

import java.util.List;

import muralis.desafio.Enderecos.model.*;

public interface ClienteRepository {
	int salvar(Cliente cliente);
	
	int atualizar(Cliente cliente);
	
	Cliente encontrarPorId(Long id);
	
	int deletarPorId(Long id);
	
	List<Cliente> todosOsClientes();
	
	List<Cliente> encontrarPorNome(String nome);
	
	int deletarTodos();
}
