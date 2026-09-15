package com.produtoapi.controller;

import com.produtoapi.model.Produto;
import com.produtoapi.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins= "*")
@RestController // Indica que é uma Classe Controller
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public List<Produto> listarTodos() {
		return produtoService.listarTodos();
	}
	
	@PostMapping
	public Produto salvar(@RequestBody Produto produto) {
		return produtoService.salvar(produto);
	}
	
	@PostMapping("/salvarLista")
	public List<Produto> salvarLista(@RequestBody List<Produto> produtos) {
		return produtoService.salvarLista(produtos);
	}
	
	@PutMapping("/{id}")
	public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
		return produtoService.atualizar(id, produto);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		produtoService.deletar(id);
	}
	
	@GetMapping("/{id}")
	public Optional<Produto> findById(@PathVariable Long id) {
		return produtoService.findById(id);
	}
	
	// Endpoints de busca por nome
	@GetMapping("/buscarPorNome")
	public List<Produto> buscarPorNome(@RequestParam String valor) {
		return produtoService.findByNome(valor);
	}
	
	@GetMapping("/buscarPorNomeContendo")
	public List<Produto> buscaPorNomeContendo(@RequestParam String valor) {
		return produtoService.findByNomeContaining(valor);
	}
	
	@GetMapping("/buscarPorNomeEStatus")
	public List<Produto> buscarPorNomeEStatus(@RequestParam String nome, @RequestParam String status) {
		return produtoService.findByNomeAndStatus(nome, status);
	}
	
	@GetMapping("/buscarPorNomeComecandoCom")
	public List<Produto> buscarPorNomeComecandoCom(@RequestParam String valor) {
		return produtoService.findByNomeStartingWith(valor);
	}
	
	@GetMapping("/buscarPorNomeTerminandoCom")
	public List<Produto> buscarPorNomeTerminandoCom(@RequestParam String valor) {
		return produtoService.findByNomeEndingWith(valor);
	}
	
	// Endpoints busca por preço
	@GetMapping("/buscarPorPreco")
	public List<Produto> buscarPorPreco(@RequestParam Double valor) {
		return produtoService.findByPreco(valor);
	}
	
	@GetMapping("/buscarPorPrecoMaiorQue")
	public List<Produto> buscarPorPrecoMaiorQue(@RequestParam Double valor) {
		return produtoService.findByPrecoGreaterThen(valor);
	}
	
	@GetMapping("/buscarPorPrecoMenorQue")
	public List<Produto> buscarPorPrecoMenorQue(@RequestParam Double valor) {
		return produtoService.findByPrecoLessThen(valor);
	}
	
	@GetMapping("/buscarTotalPreco")
	public Double buscarTotalPreco() {
		return produtoService.findTotalPreco();
	}
	
	// Endpoints busca por quantidade
	@GetMapping("/buscarPorQuantidade")
	public List<Produto> buscarPorQuantidade(@RequestParam Integer valor) {
		return produtoService.findByQuantidade(valor);
	}
	
	@GetMapping("/buscarPorQuantidadeMaiorQue")
	public List<Produto> buscarPorQuantidadeMaiorQue(@RequestParam Integer valor) {
		return produtoService.findByQuantidadeGreaterThan(valor);
	}
	
	@GetMapping("/buscarPorQuantidadeMenorQue")
	public List<Produto> buscarPorQuantidadeMenorQue(@RequestParam Integer valor) {
		return produtoService.findByQuantidadeLessThan(valor);
	}
	
	// Endpoints de buscar Combinadas, Padronizadas e Totalizadas
	@GetMapping("/buscarPorStatus")
	public List<Produto> buscarPorStatus(@RequestParam(required = false) String valor) {
		return produtoService.findByStatus(valor);
	}
	
	// retorna produtos com status Null
	@GetMapping("/buscarPorStatusNulos")
	public List<Produto> buscarPorStatussNulos() {
		return produtoService.findByStatusIsNull();
	}
	
	@GetMapping("/buscarPorPrecoEStatus")
	public List<Produto> buscarPorPrecoEStatus(@RequestParam Double preco, @RequestParam String status) {
		return produtoService.findByPrecoAndStatus(preco, status);
	}
	
	@GetMapping("/contarTotalDeProdutos")
	public Long contarTotalDeProdutos() {
		return produtoService.count();
	}
	
	// Se não passar nada, volta todos os produtos com status padrão, neste case será "Disponível"
	@GetMapping("/buscarPorStatusPadrao")
	public List<Produto> buscarPorStatusPadrao(@RequestParam(defaultValue = "Disponível") String valor) {
		return produtoService.findByStatus(valor);
	}
}
