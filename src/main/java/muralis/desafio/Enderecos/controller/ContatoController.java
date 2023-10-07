package muralis.desafio.Enderecos.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import muralis.desafio.Enderecos.model.Endereco;
import muralis.desafio.Enderecos.repository.ClienteRepository;
import muralis.desafio.Enderecos.repository.ContatoRepository;
import muralis.desafio.Enderecos.viacep.EnderecoViacep;
import muralis.desafio.Enderecos.dto.ClienteDto;
import muralis.desafio.Enderecos.dto.ContatoDto;
import muralis.desafio.Enderecos.dto.EnderecoDto;
import muralis.desafio.Enderecos.model.Cliente;
import muralis.desafio.Enderecos.model.Contato;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class ContatoController {
	
	@Autowired
  	ContatoRepository repositorioDeContato;
	
	@GetMapping("/contatos")
	public ResponseEntity<List<Contato>> getTodosOsContatos() {
  		try {
  			List<Contato> contatos = new ArrayList<Contato>();
  			
  			repositorioDeContato.todosOsContatos().forEach(contatos::add);

  			if (contatos.isEmpty()) {
  				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  			}
  			
  			return new ResponseEntity<>(contatos, HttpStatus.OK);
  		} catch (Exception e) {
  			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  		}
  	}
	
	@GetMapping("/contatos/{id}")
	public ResponseEntity<Contato> getContatoPorId(@PathVariable("id") long id) {
		Contato contato = repositorioDeContato.encontrarPorId(id);
	
  		if (contato != null) {
  			return new ResponseEntity<>(contato, HttpStatus.OK);
  		} 
  		else {
  			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  		}
  	}
	
	@PostMapping("/contatos")
	public ResponseEntity<String> createContato(@RequestBody ContatoDto contatoDto) {
		try {
			
			Contato contatoParaSalvar = 
					new Contato ( 
						contatoDto.getTipo(),
						contatoDto.getTexto(),
						contatoDto.getIdCliente()
					);
			
	
			repositorioDeContato.salvar(contatoParaSalvar);
			return new ResponseEntity<>("Contato cadastrado com sucesso.", HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/contatos/{id}")
	public ResponseEntity<String> updateContato(@PathVariable("id") long id, @RequestBody ContatoDto contatoDto) {
		Contato _contato = repositorioDeContato.encontrarPorId(id);
		
		
		if (_contato != null) {
			_contato.setId(id);
			_contato.setTexto(contatoDto.getTexto());
			_contato.setTipo(contatoDto.getTipo());
	    	
			repositorioDeContato.atualizar(_contato);
			return new ResponseEntity<>("Contato atualizado com sucesso!.", HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>("Não foi possível encontrar um Contato com id=" + id, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/contatos/{id}")
	public ResponseEntity<String> deleteContato(@PathVariable("id") long id) {
		try {
			int resultado = repositorioDeContato.deletarPorId(id);
			if (resultado == 0) {
				return new ResponseEntity<>("Não foi possível encontrar Contato com id=" + id, HttpStatus.OK);
			}
			
			return new ResponseEntity<>("Contato deletado com sucesso.", HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>("Não foi possível deletar o Contato.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/contatos")
	public ResponseEntity<String> deletarTodosOsClientes() {
		try {
			int quantidadeDeletada = repositorioDeContato.deletarTodos();
			return new ResponseEntity<>(quantidadeDeletada + " Contato(s) deletados com sucesso.", HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("Não foi possível deletar nenhum Contato.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
}
