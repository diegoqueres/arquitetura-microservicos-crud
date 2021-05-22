package net.diegoqueres.crud.entity;

import lombok.*;
import net.diegoqueres.crud.data.dto.ProdutoDTO;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "produto")
public class Produto implements Serializable {
    private static final long serialVersionUID = -5792895876345379147L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length=255)
    private String nome;

    @Column(name = "estoque", nullable = false, length=10)
    private Integer estoque;

    @Column(name = "preco", nullable = false, length=10)
    private Double preco;

    public static Produto create(ProdutoDTO produtoDTO) {
        return new ModelMapper().map(produtoDTO, Produto.class);
    }
}
