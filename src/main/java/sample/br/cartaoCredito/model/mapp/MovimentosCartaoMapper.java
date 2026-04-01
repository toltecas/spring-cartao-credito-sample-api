package sample.br.cartaoCredito.model.mapp;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sample.br.cartaoCredito.model.MovimentosCartao;
import sample.br.cartaoCredito.model.dto.MovimentosCartaoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimentosCartaoMapper {

    @Mapping(source = "membro.id", target = "codigoMembroFamilia")
    MovimentosCartaoDTO toDto(MovimentosCartao movimento);

    List<MovimentosCartaoDTO> toDto(List<MovimentosCartao> movimentos);

    @Mapping(source = "codigoMembroFamilia", target = "membro.id")
    MovimentosCartao toEntity(MovimentosCartaoDTO movimentosDTO);
}
