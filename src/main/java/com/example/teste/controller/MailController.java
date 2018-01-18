package com.example.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teste.mail.EmailServiceImpl;

@RestController
@RequestMapping("/email")
public class MailController {
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@GetMapping("/{emailEnviar}")
	public String enviar(@PathVariable("emailEnviar") String email) {
		String to = "celiohauck@gmail.com";

		try {
			SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
			simpleMailMessage.setText("Olá %s bom dia, você foi premiado com R$: %s reais");
			emailServiceImpl.sendSimpleMessageUsingTemplate(to, "Premiação para um grande funcionário", simpleMailMessage, "Ramozinho","10,0000");
//			emailServiceImpl.sendSimpleMessage(to, "testando","testando");
			return "Email enviado com sucesso;";
		} 
		catch (Exception ex) {
			return String.format("Falha ao enviar email %s", ex.getMessage());
		}
	}
}
