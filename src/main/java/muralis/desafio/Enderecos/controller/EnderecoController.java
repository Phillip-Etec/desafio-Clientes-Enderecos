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
import org.springframework.web.client.RestTemplate;

import muralis.desafio.Enderecos.model.*;
import muralis.desafio.Enderecos.repository.*;
import muralis.desafio.Enderecos.viacep.EnderecoViacep;
import muralis.desafio.Enderecos.dto.*;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class EnderecoController {
	
	@Autowired
  	EnderecoRepository repositorioDeEndereco;
	
	@GetMapping("/enderecos")
	public ResponseEntity<List<Endereco>> getTodosOsEnderecos(@RequestParam(required = false) String cep) {
  		try {
  			List<Endereco> enderecos = new ArrayList<Endereco>();
  			
  			repositorioDeEndereco.todosOsEnderecos().forEach(enderecos::add);

  			if (enderecos.isEmpty()) {
  				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  			}
  			
  			return new ResponseEntity<>(enderecos, HttpStatus.OK);
  		} catch (Exception e) {
  			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  		}
  	}
	
	@GetMapping("/enderecos/{id}")
	public ResponseEntity<Endereco> getEnderecoPorId(@PathVariable("id") long id) {
		Endereco endereco = repositorioDeEndereco.encontrarPorId(id);
	
  		if (endereco != null) {
  			return new ResponseEntity<>(endereco, HttpStatus.OK);
  		} 
  		else {
  			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  		}
  	}
	
	@GetMapping("/enderecoviacep")
	private EnderecoViacep getCep(@RequestParam(required = true) String cep) {
		String url = "https://viacep.com.br/ws/"+cep+"/json/";
		
		RestTemplate restTemplate = new RestTemplate();
		
		EnderecoViacep resultado = restTemplate.getForObject(url, EnderecoViacep.class);
		return resultado;
	}
	
	@PostMapping("/enderecos")
	public ResponseEntity<String> createEndereco(@RequestBody EnderecoDto enderecoDto) {
		try {
			EnderecoViacep enderecoCompleto = getCep(enderecoDto.getCep().replace("-", ""));
			
			Endereco enderecoParaSalvar = 
					new Endereco ( enderecoCompleto.getCep(),
							enderecoCompleto.getLogradouro(),
							enderecoCompleto.getLocalidade(),
							enderecoDto.getNumero(),
							enderecoDto.getComplemento(),
							enderecoDto.getIdCliente()
					);
			
	
			repositorioDeEndereco.salvar(enderecoParaSalvar);
			return new ResponseEntity<>("Endereço cadastrado com sucesso.", HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/enderecos/{id}")
	public ResponseEntity<String> deleteEndereco(@PathVariable("id") long id) {
		try {
			int resultado = repositorioDeEndereco.deletarPorId(id);
			if (resultado == 0) {
				return new ResponseEntity<>("Não foi possível encontrar Endereco com id=" + id, HttpStatus.OK);
			}
			
			return new ResponseEntity<>("Endereco deletado com sucesso.", HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>("Não foi possível deletar o Endereco.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/enderecos")
	public ResponseEntity<String> deletarTodosOsEnderecos() {
		try {
			int quantidadeDeletada = repositorioDeEndereco.deletarTodos();
			return new ResponseEntity<>(quantidadeDeletada + " Cliente(s) deletados com sucesso.", HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("Não foi possível deletar nenhum Cliente.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}

}
