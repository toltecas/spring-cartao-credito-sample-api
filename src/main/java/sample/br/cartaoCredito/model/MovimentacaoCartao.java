package sample.br.cartaoCredito.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "movimentacao")
public class MovimentacaoCartao implements Serializable {
    @Serial
    private static final long serialVersionUID = 920835965172954074L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigomovimento")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigomembro", referencedColumnName = "codigomembro", nullable=false)
    private MembroFamilia membroId;
    @Column(name = "valormovimento")
    private Double valorMovimento;
    @Column(name = "datamovimento")
    private Date dataMovimento;
    @Column(name = "descricaomovimento")
    private String descricaoMovimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MembroFamilia getMembroId() {
        return membroId;
    }

    public void setMembroId(MembroFamilia membroId) {
        this.membroId = membroId;
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
