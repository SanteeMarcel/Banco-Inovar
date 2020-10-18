package com.banco.BancoInovar;

import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ClientePessoaFisica {
	
	private String cpf;
	private String nome;
	private String sobrenome;
	private String email;
	private LocalDate dataDeNascimento;
	
	public String getCpf() {
		return cpf;
	}
	
	public boolean setCpf(String cpf) { //Apenas aceita numeros ex. 99999999999 e nao 999.999.999-99
		cpf = cpf.replaceAll("[^0-9]","");
		if (ValidadorDeCPF.verificaCPF(cpf)) {
			this.cpf = cpf;
			return true;
		}
		return false;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean setEmail(String email) {
		boolean result = true;
		try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		    } catch (AddressException ex) {
		    	System.out.println(ex);
		      result = false;
		    }
		    if (result == true) {
			   this.email = email;
		    }
		return result;
	}
	
	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}
	
	public boolean setDataDeNascimento(String dataDeNascimento) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		try {
			LocalDate birthDate = LocalDate.parse(dataDeNascimento, formatter);
			Period age = Period.between(birthDate, LocalDate.now());
			if (age.getYears() >= 18) { // Verifica se a data tem mais de 18 anos atrás no passado (valores futuros são
										// negativos)
				this.dataDeNascimento = birthDate;
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
