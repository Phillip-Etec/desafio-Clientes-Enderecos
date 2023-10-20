package muralis.desafio.Enderecos.repository;

import java.util.List;
import java.util.Map;

import muralis.desafio.Enderecos.model.*;

public interface TipoContatoRepository {
	int salvar(TipoContato tipoContato);
	
	int atualizar(TipoContato tipoContato);
	
	TipoContato encontrarPorId(Long id);
	
	int deletarPorId(Long id);
	
	List<TipoContato> todosOsTiposDeContato();
	
	List<TipoContato> encontrarPorNome(String nome);
	
	Map<Long, TipoContato> tiposDeContatosCadastrados();
	
	int deletarTodos();
}
