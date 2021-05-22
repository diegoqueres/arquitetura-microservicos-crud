package net.diegoqueres.crud.services;

import net.diegoqueres.crud.data.dto.ProdutoDTO;
import net.diegoqueres.crud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDTO create(ProdutoDTO produtoDTO) {
        return null;
    }

}
