package net.diegoqueres.crud.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import net.diegoqueres.crud.entity.Produto;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

@JsonPropertyOrder({"id","nome","estoque","preco"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = -6971061102210858989L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("estoque")
    private Integer estoque;

    @JsonProperty("preco")
    private Double preco;

    public static ProdutoDTO create(Produto produto) {
        return new ModelMapper().map(produto, ProdutoDTO.class);
    }
}
