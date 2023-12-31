package muralis.desafio.Enderecos.viacep;

public class EnderecoViacep {

	private String cep;
	
	private String logradouro;
	
	private String complemento;
	
	private String bairro;
	
	private String localidade;
	
	private String uf;
	
	private String ibge;
	
	private String gia;
	
	private String ddd;
	
	private String siafi;
	
	private boolean erro;

	public String getCep() {
		return cep;
	}
	
	private void noErro() {
		erro = false;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}
	
	public void setErro(boolean error) {
		this.erro = error;
	}
	
	public boolean getErro() {
		return this.erro;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getSiafi() {
		return siafi;
	}

	public void setSiafi(String siafi) {
		this.siafi = siafi;
	}
	
	public EnderecoViacep() {
		
	}

	public EnderecoViacep(String cep, String logradouro, String complemento, String bairro, String localidade,
			String uf, String ibge, String gia, String ddd, String siafi) {
		super();
		setCep(cep);
		setLogradouro(logradouro);
		setComplemento(complemento);
		setBairro(bairro);
		setLocalidade(localidade);
		setUf(uf);
		setIbge(ibge);
		setGia(gia);
		setDdd(ddd);
		setSiafi(siafi);
		//noError();
	}
	
	@Override
	public String toString() {
		return "{ cep: "+cep+"; logradouro: "+logradouro+"; complemento: "+complemento+"; bairro: "+bairro+"; localidade: "+localidade+"; uf: "+uf+"; Ibge: "+ibge+"; gia: "+gia+"; ddd: "+ddd+"; siafi: "+siafi+"}";
	}
	
}
