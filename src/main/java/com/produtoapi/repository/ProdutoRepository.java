package com.produtoapi.repository;

import com.produtoapi.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long>  {
	// Diferentes buscas por NOME
	List<Produto> findByNome(String nome);
	List<Produto> findByNomeContaining(String nome);
	List<Produto> findByNomeAndStatus(String nome, String status);
	List<Produto> findByNomeStartingWith(String prefix);
	List<Produto> findByNomeEndingWith(String suffix);
	
	// Diferentes buscas por PREÇO
	List<Produto> findByPreco(Double preco);
	List<Produto> findByPrecoGreaterThen(Double preco);
	List<Produto> findByPrecoLessThan(Double preco);
	
	// usando Anottation @Query para obter o total de preços
	@Query("SELECT SUM(p.preco) FROM Produto p")
	Double findTotalPreco();
}
