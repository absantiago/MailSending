package com.example.teste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teste.mail.EmailServiceImpl;
import com.example.teste.model.Cliente;

@RestController
@RequestMapping("/email")
public class MailController {
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	 String nomes = "";
	@GetMapping("/{emailEnviar}")
	public String enviar(List <Cliente> clientes) {
		String to = "testestfpoc@gmail.com";
		 
		try {
			SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
			clientes.forEach(cliente -> {
			 nomes+=cliente.getNome() + "\n";
			});
			simpleMailMessage.setText("Olá "+ nomes+" bom dia, você foi premiado com R$: %s reais");
			emailServiceImpl.sendSimpleMessageUsingTemplate(to, "Premiação para um grande funcionário", simpleMailMessage, "Ramozinho","10,0000");

//			emailServiceImpl.sendSimpleMessage(to, "testando","testando");
			return "Email enviado com sucesso;";
		} 
		catch (Exception ex) {
			return String.format("Falha ao enviar email %s", ex.getMessage());
		}
	}
}
