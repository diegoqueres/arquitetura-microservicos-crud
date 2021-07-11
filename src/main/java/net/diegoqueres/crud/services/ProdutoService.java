package net.diegoqueres.crud.services;

import net.diegoqueres.crud.data.dto.ProdutoDTO;
import net.diegoqueres.crud.entity.Produto;
import net.diegoqueres.crud.exception.ResourceNotFoundException;
import net.diegoqueres.crud.message.ProdutoSendMessage;
import net.diegoqueres.crud.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ProdutoSendMessage produtoSendMessage;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, ProdutoSendMessage produtoSendMessage) {
        this.produtoRepository = produtoRepository;
        this.produtoSendMessage = produtoSendMessage;
    }

    public ProdutoDTO create(ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.save(Produto.create(produtoDTO));
        ProdutoDTO produtoDTORetorno = ProdutoDTO.create(produto);
        produtoSendMessage.sendMessage(produtoDTORetorno);
        return produtoDTORetorno;
    }

    public Page<ProdutoDTO> findAll(Pageable pageable) {
        var page = produtoRepository.findAll(pageable);
        return page.map(this::convertToProdutoDTO);
    }

    private ProdutoDTO convertToProdutoDTO(Produto produto) {
        return ProdutoDTO.create(produto);
    }

    public ProdutoDTO findById(Long id) {
        var entity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return ProdutoDTO.create(entity);
    }

    public ProdutoDTO update(ProdutoDTO produtoDTO) {
        final Optional<Produto> optionalProduto = produtoRepository.findById(produtoDTO.getId());
        if (!optionalProduto.isPresent())
            new ResourceNotFoundException("No records found for this ID");

        Produto produto = Produto.create(produtoDTO);
        produto = produtoRepository.save(produto);
        return ProdutoDTO.create(produto);
    }

    public void delete(Long id) {
        var entity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        produtoRepository.delete(entity);
    }
}
