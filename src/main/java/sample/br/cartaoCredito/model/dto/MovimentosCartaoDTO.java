package sample.br.cartaoCredito.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Date;

//Aplicando o Pattern Layered Architecture (com um pe na Clean Architecture).
public record MovimentosCartaoDTO (

        @NotNull(message = "O Valor para o Codigo de Relacionamento com o Membro Familiar não pode ser nulo!")
        @Positive(message = "O Valor para o Codigo de Relacionamento com o Membro Familiar precisa ser positivo!")
        Integer codigoMembroFamilia,
        @Positive(message = "O Valor do Movimento precisa ser maior que zero!")
        @NotNull(message = "O Valor do Movimento não pode ser nulo!")
        Double valorMovimento,
        Date dataMovimento,
        @NotBlank(message = "A Descrição do Movimento é obrigatória no máximo de 100 Caracteres")
        @Size(max = 100)
        String descricaoMovimento
){}
