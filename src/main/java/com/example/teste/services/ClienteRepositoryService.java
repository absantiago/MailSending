package com.example.teste.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.teste.model.Cliente;
import com.example.teste.repository.*;

@Service
public class ClienteRepositoryService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> EnviaEmailNome(String nome) {
		List<Cliente> clientes = clienteRepository.findAll();

		for (int cont = 0; cont < clientes.size(); cont++) {
			if (!clientes.get(cont).getNome().equals(nome))
				clientes.remove(cont);
		}

		return clientes;

	}

	public List<Cliente> VerificaValidade(){
		List<Cliente> clientes = clienteRepository.findAll();
		Date atual = new Date();
		atual.getTime();
		List<Cliente> aniversariantesDoMes = new ArrayList<Cliente>();
		Date aux ;
		long dif;
		float dias;
		for(int cont=0;cont<clientes.size();cont++) {
		  aux = clientes.get(cont).getDataNascimento();
		  dif = aux.getTime()-atual.getTime();
		  dias = (dif / (1000*60*60*24));
		  System.out.println(dias);
		  
		  if(dias==30) {
			  aniversariantesDoMes.add(clientes.get(cont));
		  }
		}
		
		

		return aniversariantesDoMes;
	}
	
	
}
