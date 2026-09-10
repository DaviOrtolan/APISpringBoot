package com.produtoapi.repository;

import com.produtoapi.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>  {
	// Diferentes buscas
	List<Produto> findByNome(String nome);
	List<Produto> findByNomeContaining(String nome);
	List<Produto> findByNomeAndStatus(String nome, String status);
	List<Produto> findByNomeStartingWith(String prefix);
	List<Produto> findByNomeEndingWith(String suffix);
}
