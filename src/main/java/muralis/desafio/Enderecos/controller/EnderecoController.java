package muralis.desafio.Enderecos.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
	ModelMapper mapper;
	
	
	@Autowired
  	EnderecoRepository repositorioDeEndereco;
	
	@GetMapping("/enderecos")
	public ResponseEntity<List<EnderecoDto>> getTodosOsEnderecos(@RequestParam(required = false) String idcliente) {
  		try {
  			List<Endereco> enderecos = new ArrayList<Endereco>();
  			
  			if(idcliente == null)
  				repositorioDeEndereco.todosOsEnderecos().forEach(enderecos::add);
  			else 
  				repositorioDeEndereco.encontrarPorCliente(Long.parseLong(idcliente)).forEach(enderecos::add);
  			
  			if (enderecos.isEmpty()) {
  				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  			}
  			
  			List<EnderecoDto> respostaEnderecos = 	enderecos.stream()
													.map(endereco -> mapper.map(endereco, EnderecoDto.class))
													.collect(Collectors.toList());
  			
  			return new ResponseEntity<>(respostaEnderecos, HttpStatus.OK);
  		} 
  		catch (Exception e) {
  			System.out.println(e.getMessage());
  			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  		}
  	}
	
	@GetMapping("/enderecos/{id}")
	public ResponseEntity<EnderecoDto> getEnderecoPorId(@PathVariable("id") long id) {
		try {
			Endereco endereco = repositorioDeEndereco.encontrarPorId(id);
		
			if (endereco != null) {
				EnderecoDto respostaEndereco = mapper.map(endereco, EnderecoDto.class);
				return new ResponseEntity<>(respostaEndereco, HttpStatus.OK);
			} 
			
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
  	}
	
	@GetMapping("/enderecoviacep")
	private EnderecoViacep getCep(@RequestParam(required = true) String cep) {
		try {
			String url = "https://viacep.com.br/ws/"+cep+"/json/";
			
			RestTemplate restTemplate = new RestTemplate();
			
			EnderecoViacep resultado = restTemplate.getForObject(url, EnderecoViacep.class);
			return resultado;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@PostMapping("/enderecos")
	public ResponseEntity<String> createEndereco(@RequestBody EnderecoDto enderecoDto) {
		try {
			EnderecoViacep enderecoCompleto = getCep(enderecoDto.getCep().replace("-", ""));
			
			if(enderecoCompleto == null) {
				return new ResponseEntity<>("Erro na API viacep.", HttpStatus.SERVICE_UNAVAILABLE);
			}
			
			else if(enderecoCompleto.getError()) {
				return new ResponseEntity<>("CEP inválido.", HttpStatus.NOT_FOUND);
			}
			
			
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
			return new ResponseEntity<>("Não foi possivel cadastrar o endereço. Erro:\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/enderecos/{id}")
	public ResponseEntity<String> updateEndereco(@PathVariable("id") long id, @RequestBody EnderecoDto enderecoDto) {
		try {
			Endereco endereco = repositorioDeEndereco.encontrarPorId(id);
			EnderecoViacep enderecoCompleto = getCep(enderecoDto.getCep().replace("-", ""));
			
			if(enderecoCompleto == null) {
				return new ResponseEntity<>("Erro na API viacep.", HttpStatus.SERVICE_UNAVAILABLE);
			}
			
			if(enderecoCompleto.getErro()) {
				return new ResponseEntity<>("CEP inválido.", HttpStatus.NOT_FOUND);
			}
			
			endereco.setCep(enderecoCompleto.getCep());
			endereco.setLogradouro(enderecoCompleto.getLogradouro());
			endereco.setCidade(enderecoCompleto.getLocalidade());
			endereco.setNumero(enderecoDto.getNumero());
			endereco.setComplemento(enderecoDto.getComplemento());
			endereco.setIdCliente(enderecoDto.getIdCliente());
	    	
			repositorioDeEndereco.atualizar(endereco);
			return new ResponseEntity<>("Endereço atualizado com sucesso!.", HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("Não foi possível atualizar o endereço. Erro:\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/enderecos/{id}")
	public ResponseEntity<String> deleteEndereco(@PathVariable("id") long id) {
		try {
			int resultado = repositorioDeEndereco.deletarPorId(id);
			if (resultado == 0) {
				return new ResponseEntity<>("Não foi possível encontrar Endereco com id=" + id, HttpStatus.OK);
			}
			
			return new ResponseEntity<>("Endereço deletado com sucesso.", HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>("Não foi possível deletar o Endereço. Erro:\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/enderecos")
	public ResponseEntity<String> deletarTodosOsEnderecos() {
		try {
			int quantidadeDeletada = repositorioDeEndereco.deletarTodos();
			return new ResponseEntity<>(quantidadeDeletada + " Endereço(s) deletados com sucesso.", HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("Não foi possível deletar nenhum endereço. Erro:\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}

}
