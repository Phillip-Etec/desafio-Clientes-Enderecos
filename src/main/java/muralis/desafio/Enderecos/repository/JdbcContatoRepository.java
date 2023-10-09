package muralis.desafio.Enderecos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import muralis.desafio.Enderecos.model.Contato;
import muralis.desafio.Enderecos.model.Endereco;

@Repository
public class JdbcContatoRepository implements ContatoRepository { 
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public int salvar(Contato contato ) {
		return jdbcTemplate.update("INSERT INTO contatos (tipo, texto, idcliente) VALUES(?, ?, ?)",
				new Object[] { contato.getTipo(), contato.getTexto(), contato.getIdCliente()  });
	}

	@Override
	public int atualizar(Contato contato) {
	
			return jdbcTemplate.update("UPDATE contatos SET texto=?, tipo=?  WHERE id=?",
					new Object[] { contato.getTexto(),contato.getTipo(), contato.getId() });
	    
	}
	
	@Override
	public List<Contato> encontrarPorCliente(long idCliente) {
		String SqlQuery = "SELECT * FROM contatos JOIN clientes ON idcliente = clientes.id WHERE idcliente="+idCliente+";";
		
		List<Contato> contatos = jdbcTemplate.query(SqlQuery, BeanPropertyRowMapper.newInstance(Contato.class));

		return contatos;
	}

	@Override
	public Contato encontrarPorId(Long id) {
		try {
			Contato contato = jdbcTemplate.queryForObject("SELECT id, tipo, texto, idcliente FROM contatos WHERE id=?",
	        BeanPropertyRowMapper.newInstance(Contato.class), id);
			return contato;
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
	public List<Contato> todosOsContatos() {
		List<Contato> clientes = jdbcTemplate.query("SELECT * FROM contatos", BeanPropertyRowMapper.newInstance(Contato.class));
		
		return clientes;
	}


	@Override
	public List<Contato> encontrarPorTexto(String texto) {
		String SqlQuery = "SELECT * FROM contatos WHERE texto ILIKE '%" + texto + "%'";
		
		List<Contato> clientes = jdbcTemplate.query(SqlQuery, BeanPropertyRowMapper.newInstance(Contato.class));

		return clientes;
	}

	@Override
	public int deletarTodos() {
		return jdbcTemplate.update("DELETE FROM contatos");
	}

}