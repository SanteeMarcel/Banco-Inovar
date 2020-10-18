package com.banco.BancoInovar;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResourceController {

	UsuarioCadastradoSet usuarios = new UsuarioCadastradoSet();

	@GetMapping("/Cadastro")
	public String welcome(Model model) {
		model.addAttribute("clientePessoaFisica", new ClientePessoaFisica());
		return "Cadastro";
	}

	@PostMapping("/Cadastro")
	public ResponseEntity<Object> cadastroPessoaFisica(@ModelAttribute ClientePessoaFisica clientePessoaFisica,
			Model model) {
		model.addAttribute("clientePessoaFisica", clientePessoaFisica);

		boolean cpfInvalido = (clientePessoaFisica.getCpf() == null);
		boolean emailInvalido = (clientePessoaFisica.getEmail() == null);
		boolean nascimentoInvalido = (clientePessoaFisica.getDataDeNascimento() == null);

		JSONObject json = new JSONObject();

		try {

			json.put("nome", clientePessoaFisica.getNome());
			json.put("sobrenome", clientePessoaFisica.getSobrenome());
			json.put("email", emailInvalido ? "EMAIL INVALIDO" : clientePessoaFisica.getEmail());
			json.put("cpf", cpfInvalido ? "CPF INVALIDO" : clientePessoaFisica.getCpf());
			json.put("datadenascimento", nascimentoInvalido ? "DATA DE NASCIMENTO INVALIDA"
					: clientePessoaFisica.getDataDeNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

			if (cpfInvalido || emailInvalido || nascimentoInvalido) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json.toString());
			}

			if (usuarios.listaCpfs().contains(clientePessoaFisica.getCpf())) {
				json.put("cpf", "CPF JÁ CADASTRADO");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json.toString());
			}

			if (usuarios.listaEmails().contains(clientePessoaFisica.getEmail())) {
				json.put("email", "EMAIL JÁ CADASTRADO");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json.toString());
			}

		} catch (JSONException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		// return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION,
		// "Endereco").body(null); // STATUS 201 não concede redirecionamento
		usuarios.addUsuario(clientePessoaFisica);
		return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "Endereco").body(null);
	}

	@GetMapping("/Endereco")
	public String cadastroEndereco(Model model) {
		model.addAttribute("endereco", new Endereco());
		return "Endereco";
	}

	@PostMapping("/Endereco")
	public ResponseEntity<Object> cadastroEndereco(@ModelAttribute Endereco endereco, Model model) {
		model.addAttribute("endereco", endereco);
		
		if (endereco.getCep() == null) {
			JSONObject json = new JSONObject();
			try {
				json.put("cep", "CEP INVALIDO");
				json.put("rua", endereco.getRua());
				json.put("bairro", endereco.getBairro());
				json.put("complemento", endereco.getComplemento());
				json.put("cidade", endereco.getCidade());
			} catch (JSONException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json.toString());
		}
		
		usuarios.addEndereco(endereco);
		
		return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "uploadForm")
				.body(null);
	}
	
	

}