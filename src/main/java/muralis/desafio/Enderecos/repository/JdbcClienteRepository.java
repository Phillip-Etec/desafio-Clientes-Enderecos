package muralis.desafio.Enderecos.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import muralis.desafio.Enderecos.model.*;

@Repository
public class JdbcClienteRepository implements ClienteRepository {
	@Autowired
	  private JdbcTemplate jdbcTemplate;

	  @Override
	  public int salvar(Cliente cliente ) {
		  //DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		  String data = cliente.getDataCadastro().toString();
		  
		  if(data.length() == "2023-01-01T00:00".length())
			  data = data+":00";
		  
		  if(data.indexOf('.') == -1)
			  data = data.replace('T', ' ');
		  else 
			  data = data.substring(0, data.indexOf('.')).replace('T', ' ');
		  
		  
		  int criacao = jdbcTemplate.update("INSERT INTO clientes (nome, data_cadastro) VALUES(?, '"+data+"')",
			        new Object[] { 	cliente.getNome()});
		  //System.out.println("CODIGO:" + criacao);
	    return criacao;
	  }

	  @Override
	  public int atualizar(Cliente cliente) {
	    return jdbcTemplate.update("UPDATE clientes SET nome=? WHERE id=?",
	        new Object[] { cliente.getNome(),  cliente.getId() });
	  }

	  @Override
	  public Cliente encontrarPorId(Long id) {
	    try {
	    	Cliente cliente = jdbcTemplate.queryForObject("SELECT * FROM clientes WHERE id=?",
	          BeanPropertyRowMapper.newInstance(Cliente.class), id);

	      return cliente;
	    } catch (IncorrectResultSizeDataAccessException e) {
	      return null;
	    }
	  }

	  @Override
	  public int deletarPorId(Long id) {
	    return jdbcTemplate.update("DELETE FROM clientes WHERE id=?", id);
	  }

	  @Override
	  public List<Cliente> todosOsClientes() {
	    return jdbcTemplate.query("SELECT * FROM clientes", BeanPropertyRowMapper.newInstance(Cliente.class));
	  }


	  @Override
	  public List<Cliente> encontrarPorNome(String nome) {
	    String SqlQuery = "SELECT * FROM clientes WHERE nome ILIKE '%" + nome + "%'";

	    return jdbcTemplate.query(SqlQuery, BeanPropertyRowMapper.newInstance(Cliente.class));
	  }

	  @Override
	  public int deletarTodos() {
	    return jdbcTemplate.update("DELETE FROM clientes");
	  }
}
