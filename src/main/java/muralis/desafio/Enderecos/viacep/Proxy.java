package muralis.desafio.Enderecos.viacep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="quote-service", url = "https://viacep.com.br")
public interface Proxy {

	@GetMapping("/ws")
	EnderecoViacep getEndereco();
	
}
