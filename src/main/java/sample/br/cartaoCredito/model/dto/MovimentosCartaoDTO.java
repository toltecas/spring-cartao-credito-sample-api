package sample.br.cartaoCredito.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class MovimentosCartaoDTO {
    private Integer membroId;
    @NotNull(message = "O Valor do Movimento é obrigatório!")
    private Double valorMovimento;
    @NotNull(message = "A Data de Movimento é obrigatória!")
    private Date dataMovimento;
    @NotBlank(message = "A Descrição do Movimento não pode ser em branco!")
    private String descricaoMovimento;

    public Integer getMembroId() {
        return membroId;
    }

    public void setMembroId(Integer membroId) {
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
}
