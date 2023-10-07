package muralis.desafio.Enderecos.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import muralis.desafio.Enderecos.model.Endereco;

@Repository
public class JdbcEnderecoRepository implements EnderecoRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int salvar(Endereco endereco) {
		return jdbcTemplate.update("INSERT INTO enderecos (cep, logradouro, cidade, numero, complemento, idcliente) VALUES(?, ?, ?, ?, ?, ?)",
				new Object[] { endereco.getCep(),
						endereco.getLogradouro(),
						endereco.getCidade(),
						endereco.getNumero(),
						endereco.getComplemento(),
						endereco.getIdCliente()
				});
	}
	
	
	@Override
	public int atualizar(Endereco endereco) {
		return jdbcTemplate.update("UPDATE enderecos SET cep = ?, logradouro = ? , cidade = ?, numero = ?, complemento = ? WHERE id=?",
				new Object[] { endereco.getCep(),
								endereco.getLogradouro(),
								endereco.getCidade(),
								endereco.getNumero(),
								endereco.getComplemento(),
								endereco.getId()
				});
	}
	
	
	@Override
	public Endereco encontrarPorId(Long id) {
		try {
			Endereco endereco = jdbcTemplate.queryForObject("SELECT id, cep, logradouro, cidade, numero, complemento, idcliente FROM enderecos WHERE id=?",
	        BeanPropertyRowMapper.newInstance(Endereco.class), id);
			return endereco;
		} 
		catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}
	
	
	@Override
	public int deletarPorId(Long id) {
		return jdbcTemplate.update("DELETE FROM enderecos WHERE id=?", id);
	}
	
	
	@Override
	public List<Endereco> todosOsEnderecos() {
		String SqlQuery = "SELECT * FROM enderecos";
		
		List<Endereco> enderecos = jdbcTemplate.query(SqlQuery, BeanPropertyRowMapper.newInstance(Endereco.class));

		return enderecos;
	}
	
	
	@Override
	public List<Endereco> encontrarPorCliente(long idCliente) {
		String SqlQuery = "SELECT id, cep, logradouro, numero, complemento, cliente_id FROM enderecos JOIN clientes ON idcliente = clientes(id) WHERE cliente_id="+idCliente+";";
		
		List<Endereco> enderecos = jdbcTemplate.query(SqlQuery, BeanPropertyRowMapper.newInstance(Endereco.class));

		return enderecos;
	}
	
	
	@Override
	public int deletarTodos() {
		return jdbcTemplate.update("DELETE FROM enderecos");
	}
}
