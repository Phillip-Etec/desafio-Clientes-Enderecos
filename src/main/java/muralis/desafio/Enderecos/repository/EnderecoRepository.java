package muralis.desafio.Enderecos.repository;
import java.util.List;

import muralis.desafio.Enderecos.model.*;
import muralis.desafio.Enderecos.dto.*;

public interface EnderecoRepository {
	int salvar(Endereco endereco);
	
	int atualizar(Endereco endereco);
	
	Endereco encontrarPorId(Long id);
	
	int deletarPorId(Long id);
	
	List<Endereco> todosOsEnderecos();
	
	List<Endereco> encontrarPorCliente(long idCliente);
	
	int deletarTodos();
}