package com.banco.BancoInovar;

import java.util.HashSet;
import java.util.Set;

public class UsuarioCadastradoSet {
	
	private Set<ClientePessoaFisica> clientes = new HashSet<ClientePessoaFisica>();
	private Set<Endereco> enderecos = new HashSet<Endereco>();
	
	public Set<ClientePessoaFisica> getClientes() {
		return clientes;
	}
	public void setClientes(Set<ClientePessoaFisica> clientes) {
		this.clientes = clientes;
	}
	public Set<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public void addUsuario(ClientePessoaFisica cliente) {
		this.clientes.add(cliente);
	}
	
	public void addEndereco(Endereco endereco) {
		this.enderecos.add(endereco);
	}
	
	public Set<String> listaCpfs() {
		Set<String> cpfs =  new HashSet<String>();
		if (clientes == null) {
			return cpfs; 
		}
		for (ClientePessoaFisica cliente : clientes) {
			cpfs.add(cliente.getCpf());
		}
		return cpfs;
	}
	
	public Set<String> listaEmails () {
		Set<String> emails =  new HashSet<String>();
		if (clientes == null) {
			return emails; 
		}
		for (ClientePessoaFisica cliente : clientes) {
			emails.add(cliente.getCpf());
		}
		return emails;
	}

}
