package com.example.teste.rotina;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.teste.controller.MailController;
import com.example.teste.model.Cliente;
import com.example.teste.repository.ClienteRepository;
import com.example.teste.services.ClienteRepositoryService;


@EnableScheduling
@Component
public class RotinaEnviarMail {
	@Autowired
	private MailController mail;
	
	@Autowired
	private ClienteRepositoryService clienteRepositoryService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	//@Scheduled(cron = "0 06 14 ? * *" )
	@Scheduled(fixedRate = 10000)
	public void EnviarMail() {
		
		//List<Cliente> lista = clienteRepository.findAll();
		//if(lista.size()!=0)
		//List<Cliente> clientes = clienteRepositoryService.EnviaEmailNome("Jõao");
		//if(!clientes.isEmpty()) {
		
		List<Cliente> lista = clienteRepositoryService.VerificaValidade();
		if(!lista.isEmpty()) {
		mail.enviar(lista);
		}
		

	}
}
