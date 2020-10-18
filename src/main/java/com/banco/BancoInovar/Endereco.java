package com.banco.BancoInovar;

public class Endereco {
	
	private String cep;
	private String rua;
	private String bairro;
	private String complemento;
	private String cidade;
	private String estado;
	
	public String getCep() {
		return cep;
	}
	public boolean setCep(String cep) {
		if (cep.length() == 8) {
			this.cep = cep;
			return true;
		}
		else return false;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
