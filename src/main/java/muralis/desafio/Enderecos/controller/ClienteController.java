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


import muralis.desafio.Enderecos.model.*;
import muralis.desafio.Enderecos.repository.*;
import muralis.desafio.Enderecos.dto.*;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
  	ClienteRepository repositorioDeCliente;
  
  	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getTodosOsClientes(@RequestParam(required = false) String nome) {
  		try {
  			List<Cliente> clientes = new ArrayList<Cliente>();
  			
  			if (nome == null)
  				repositorioDeCliente.todosOsClientes().forEach(clientes::add);
  			else
  				repositorioDeCliente.encontrarPorNome(nome).forEach(clientes::add);

  			if (clientes.isEmpty()) {
  				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  			}
  			
  			return new ResponseEntity<>(clientes, HttpStatus.OK);
  		} catch (Exception e) {
  			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  		}
  	}

  	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> getClientePorId(@PathVariable("id") long id) {
  		Cliente cliente = repositorioDeCliente.encontrarPorId(id);
	
  		if (cliente != null) {
  			return new ResponseEntity<>(cliente, HttpStatus.OK);
  		} 
  		else {
  			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  		}
  	}

	@PostMapping("/clientes")
	public ResponseEntity<String> createCliente(@RequestBody ClienteDto clienteDto) {
		try {
				if(clienteDto.getDataCadastro() == null)
					clienteDto.setDataCadastro(LocalDateTime.now());
	
				repositorioDeCliente.salvar(clienteDto.converterParaCliente());
				return new ResponseEntity<>("Cliente cadastrado com sucesso.", HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/clientes/{id}")
		public ResponseEntity<String> updateCliente(@PathVariable("id") long id, @RequestBody ClienteDto clienteDto) {
		Cliente _cliente = repositorioDeCliente.encontrarPorId(id);
		
		
		if (_cliente != null) {
			_cliente.setId(id);
			_cliente.setNome(clienteDto.getNome());
			_cliente.setDataCadastro(clienteDto.getDataCadastro());
	    	
			repositorioDeCliente.atualizar(_cliente);
			return new ResponseEntity<>("Cliente atualizado com sucesso!.", HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>("Não foi possível encontrar um cliente com id=" + id, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<String> deleteCliente(@PathVariable("id") long id) {
		try {
			int resultado = repositorioDeCliente.deletarPorId(id);
			if (resultado == 0) {
				return new ResponseEntity<>("Não foi possível encontrar Cliente com id=" + id, HttpStatus.OK);
			}
			
			return new ResponseEntity<>("Cliente deletado com sucesso.", HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>("Não foi possível deletar o Cliente.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/clientes")
	public ResponseEntity<String> deletarTodosOsClientes() {
		try {
			int quantidadeDeletada = repositorioDeCliente.deletarTodos();
			return new ResponseEntity<>(quantidadeDeletada + " Cliente(s) deletados com sucesso.", HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("Não foi possível deletar nenhum Cliente.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}

}
