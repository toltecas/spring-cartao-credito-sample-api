package sample.br.cartaoCredito.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
//@Table(name = "public.\"Membro\"")
@Table(name = "membro")
public class MembroFamilia implements Serializable {

    @Serial
    private static final long serialVersionUID = -4967189648512386057L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigomembro")
    private Integer id;
    @Column(name = "nomemembro")
    private String nome;
    @Column(name = "relacaofamiliar")
    private String relacaoFamiliar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRelacaoFamiliar() {
        return relacaoFamiliar;
    }

    public void setRelacaoFamiliar(String relacaoFamiliar) {
        this.relacaoFamiliar = relacaoFamiliar;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
