package muralis.desafio.Enderecos.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import muralis.desafio.Enderecos.model.*;
import muralis.desafio.Enderecos.dto.*;

@Repository
public class JdbcClienteRepository implements ClienteRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public int salvar(Cliente cliente ) {
		String data = tratarData(cliente.getDataCadastro());
		  
		// o injector do jdbc não reconhece o tipo Timestamp do postgres, 
		// portanto é necessário adicionar a data diretamente no query sql.
		return jdbcTemplate.update("INSERT INTO clientes (nome, data_cadastro) VALUES(?, '"+data+"')",
				new Object[] { cliente.getNome() });
	}

	@Override
	public int atualizar(Cliente cliente) {
	
//		if(cliente.getDataCadastro() == null)
			return jdbcTemplate.update("UPDATE clientes SET nome=? WHERE id=?",
					new Object[] { cliente.getNome(),  cliente.getId() });
//		//else {
//			String data = tratarData(cliente.getDataCadastro());
//			return jdbcTemplate.update("UPDATE clientes SET nome=?, data_cadastro='"+data+"' WHERE id=?",
//					new Object[] { cliente.getNome(),  cliente.getId() });
//		}
	    
	}

	@Override
	public Cliente encontrarPorId(Long id) {
		try {
			Cliente cliente = jdbcTemplate.queryForObject("SELECT * FROM clientes WHERE id=?",
	        BeanPropertyRowMapper.newInstance(Cliente.class), id);
			return cliente;
		} 
		catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public int deletarPorId(Long id) {
		int codigo = jdbcTemplate.update("DELETE FROM contatos WHERE idcliente = ?", id);
		codigo = jdbcTemplate.update("DELETE FROM enderecos WHERE idcliente = ?", id); 
		
		codigo = jdbcTemplate.update("DELETE FROM clientes WHERE id=?", id);
		
		return codigo;
	}

	@Override
	public List<Cliente> todosOsClientes() {
		List<Cliente> clientes = jdbcTemplate.query("SELECT * FROM clientes", BeanPropertyRowMapper.newInstance(Cliente.class));
		
		return clientes;
	}


	@Override
	public List<Cliente> encontrarPorNome(String nome) {
		String SqlQuery = "SELECT * FROM clientes WHERE nome ILIKE '%" + nome + "%'";
		
		List<Cliente> clientes = jdbcTemplate.query(SqlQuery, BeanPropertyRowMapper.newInstance(Cliente.class));

		return clientes;
	}

	@Override
	public int deletarTodos() {
		int codigo = jdbcTemplate.update("DELETE FROM contatos");
		codigo = jdbcTemplate.update("DELETE FROM enderecos");
		codigo = jdbcTemplate.update("DELETE FROM clientes");
		return 	codigo;
	}
  
	private String tratarData(LocalDateTime dataCliente) {
		String data = dataCliente.toString();
			
		//quando as horas terminam em 00 segundos, a classe LocalDateTime retira os 2 ultimos digitos,
		//e aqui é necessário adicionar de volta
		if(data.length() == 16)
			data = data+":00";
		  
		// tratamento da data para ficar compativel com timestamp de postgres
		if(data.indexOf('.') == -1)
			data = data.replace('T', ' ');
		else 
			data = data.substring(0, data.indexOf('.')).replace('T', ' ');
		
		return data;
	}
}
