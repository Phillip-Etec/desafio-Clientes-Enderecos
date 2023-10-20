package muralis.desafio.Enderecos.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import muralis.desafio.Enderecos.model.Contato;
import muralis.desafio.Enderecos.model.TipoContato;

@Repository
public class JdbcContatoRepository implements ContatoRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TipoContatoRepository repositorioDeTiposDeContatos;

	@Override
	public int salvar(Contato contato ) {
		return jdbcTemplate.update("INSERT INTO contatos (idtipocontato, texto, idcliente) VALUES(?, ?, ?)",
				new Object[] { contato.getTipo().getId(), contato.getTexto(), contato.getIdCliente()  });
	}

	@Override
	public int atualizar(Contato contato) {
	
			return jdbcTemplate.update("UPDATE contatos SET texto=?, idtipocontato=?  WHERE id=?",
					new Object[] { contato.getTexto(), contato.getTipo().getId(), contato.getId() });
	    
	}
	
	@Override
	public List<Contato> encontrarPorCliente(long idCliente) {
		String SqlQuery = "SELECT * FROM contatos JOIN clientes ON idcliente = clientes.id WHERE idcliente="+idCliente+";";
		
		List<Contato> contatos = jdbcTemplate.query(SqlQuery, BeanPropertyRowMapper.newInstance(Contato.class));
		injetarTiposDeContatos(contatos);
		
		return contatos;
	}

	@Override
	public Contato encontrarPorId(Long id) {
		try {
			Long idTipoDeContato;
			Contato contato = jdbcTemplate.queryForObject("SELECT id, idtipocontato, texto, idcliente FROM contatos WHERE id=?",
	        BeanPropertyRowMapper.newInstance(Contato.class), id);
			idTipoDeContato = contato.getIdTipoContato();
			
			contato.setTipo(repositorioDeTiposDeContatos.encontrarPorId(idTipoDeContato));
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
		List<Contato> contatos = jdbcTemplate.query("SELECT * FROM contatos", BeanPropertyRowMapper.newInstance(Contato.class));
		injetarTiposDeContatos(contatos);
		
		return contatos;
	}


	@Override
	public List<Contato> encontrarPorTexto(String texto) {
		String SqlQuery = "SELECT * FROM contatos WHERE texto ILIKE '%" + texto + "%'";
		List<Contato> contatos = jdbcTemplate.query(SqlQuery, BeanPropertyRowMapper.newInstance(Contato.class));
		injetarTiposDeContatos(contatos);

		return contatos;
	}

	@Override
	public int deletarTodos() {
		return jdbcTemplate.update("DELETE FROM contatos");
	}
	
	private Map<Long, TipoContato> consultarTiposDeContatos() {
		return repositorioDeTiposDeContatos.tiposDeContatosCadastrados();
	}
	
	private void injetarTiposDeContatos(List<Contato> contatos) {
		Map<Long, TipoContato> tiposDeContatos = consultarTiposDeContatos();
		
		for(Contato contatoAtual: contatos) {
			TipoContato tipoDeContatoParaOContatoAtual = tiposDeContatos.get(contatoAtual.getId());
			contatoAtual.setTipo(tipoDeContatoParaOContatoAtual);
		}
	}

}