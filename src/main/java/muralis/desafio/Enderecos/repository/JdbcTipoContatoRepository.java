package muralis.desafio.Enderecos.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import muralis.desafio.Enderecos.model.TipoContato;

public class JdbcTipoContatoRepository implements TipoContatoRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int salvar(TipoContato tipoContato) {
		return jdbcTemplate.update("INSERT INTO tipo_contato (tipo) VALUES(?)",
				new Object[] { tipoContato.getTipo() });
	}

	@Override
	public int atualizar(TipoContato tipoContato) {
	
			return jdbcTemplate.update("UPDATE tipo_contato SET texto=? WHERE id=?",
					new Object[] { tipoContato.getTipo(), tipoContato.getId() });
	    
	}
	
	@Override
	public List<TipoContato> encontrarPorNome(String nome) {
		String SqlQuery = "SELECT * FROM tipo_contato WHERE tipo ILIKE '%" +nome+ "%'";
		
		List<TipoContato> tiposDeContato = jdbcTemplate.query(SqlQuery, BeanPropertyRowMapper.newInstance(TipoContato.class));

		return tiposDeContato;
	}

	@Override
	public TipoContato encontrarPorId(Long id) {
		try {
			TipoContato tipoDeContato = jdbcTemplate.queryForObject("SELECT * FROM tipo_contato WHERE id=?",
	        BeanPropertyRowMapper.newInstance(TipoContato.class), id);
			return tipoDeContato;
		} 
		catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public int deletarPorId(Long id) {
		return jdbcTemplate.update("DELETE FROM contatos WHERE id=?", id);
	}

	@Override
	public List<TipoContato> todosOsTiposDeContato() {
		List<TipoContato> tiposDeContato = jdbcTemplate.query("SELECT * FROM tipo_contato", BeanPropertyRowMapper.newInstance(TipoContato.class));
		
		return tiposDeContato;
	}

	@Override
	public int deletarTodos() {
		return jdbcTemplate.update("DELETE FROM contatos");
	}
}
