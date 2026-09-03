package com.produtoapi.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.produtoapi.model.Produto;

import java.util.Arrays;
import java.util.List;

public class CRUDJavaClient {
	private static final String BASE_URL = "http://localhost:8080/produtos";
	private RestTemplate restTemplate;
	
	public CRUDJavaClient() {
		this.restTemplate = new RestTemplate();
	}
	
	// Método para listar todos os produtos
	public void listarTodos() {
		ResponseEntity<Produto[]> response = restTemplate.getForEntity(BASE_URL, Produto[].class);
		List<Produto> produtos = Arrays.asList(response.getBody());
		produtos.forEach(produto -> {
			System.out.println("ID: " + produto.getId());
			System.out.println("Nome: " + produto.getNome());
			System.out.println("Preço: " + produto.getPreco());
			System.out.println("Quantidade: " + produto.getQuantidade());
			System.out.println("Status: " + produto.getStatus());
			System.out.println("------------------------");
		});
	}
	
	// Método para salvar um novo produto
	public Produto salvar(Produto produto) {
		HttpEntity<Produto> request = new HttpEntity<>(produto);
		return restTemplate.postForObject(BASE_URL, request, Produto.class);
	}
			
	// Método para deletar um produto pelo ID
	public void deletar(Long id) {
		restTemplate.delete(BASE_URL + "/" + id);
	}
	
	// Método para atualizar o produto existente
	public Produto atualizar(Long id, Produto produto) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Produto> request = new HttpEntity<>(produto, headers);
		ResponseEntity<Produto> response = restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.PUT, request, Produto.class);
		return response.getBody();
	}
	
	// Método para buscar produto pelo ID
	public Produto findById(Long id) {
		ResponseEntity<Produto> response = restTemplate.getForEntity(BASE_URL + "/" + id, Produto.class);
		return response.getBody()	;
	}
	
}
