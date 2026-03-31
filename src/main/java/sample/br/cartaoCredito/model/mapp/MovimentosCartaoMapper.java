package sample.br.cartaoCredito.model.mapp;

import org.mapstruct.Mapper;
import sample.br.cartaoCredito.model.MovimentosCartao;
import sample.br.cartaoCredito.model.dto.MovimentosCartaoDTO;

@Mapper(componentModel = "spring")
public interface MovimentosCartaoMapper {
    MovimentosCartaoDTO toDto(MovimentosCartao movimentos);
    MovimentosCartao toEntity(MovimentosCartaoDTO movimentosDTO);
}
