package sample.br.cartaoCredito.model;

import jakarta.persistence.*;
import sample.br.cartaoCredito.model.dto.MovimentosCartaoDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "movimentacao")
public class MovimentosCartao implements Serializable {
    @Serial
    private static final long serialVersionUID = 920835965172954074L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigomovimento")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigomembro", referencedColumnName = "codigomembro", nullable=false)
    private MembrosFamilia membro;
    @Column(name = "valormovimento")
    private Double valorMovimento;
    @Column(name = "datamovimento")
    private Date dataMovimento;
    @Column(name = "descricaomovimento")
    private String descricaoMovimento;

    public MovimentosCartao(){}

    public MovimentosCartao(MovimentosCartaoDTO movimentosDTO) {
        this.membro.setId(movimentosDTO.getMembroId());
        this.valorMovimento = movimentosDTO.getValorMovimento();
        this.dataMovimento = movimentosDTO.getDataMovimento();
        this.descricaoMovimento = movimentosDTO.getDescricaoMovimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MembrosFamilia getMembro() {
        return membro;
    }

    public void setMembro(MembrosFamilia membro) {
        this.membro = membro;
    }

    public Double getValorMovimento() {
        return valorMovimento;
    }

    public void setValorMovimento(Double valorMovimento) {
        this.valorMovimento = valorMovimento;
    }

    public Date getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public String getDescricaoMovimento() {
        return descricaoMovimento;
    }

    public void setDescricaoMovimento(String descricaoMovimento) {
        this.descricaoMovimento = descricaoMovimento;
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
