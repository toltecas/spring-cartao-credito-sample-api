package sample.br.cartaoCredito.model.mapp;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sample.br.cartaoCredito.model.MovimentosCartao;
import sample.br.cartaoCredito.model.dto.MovimentosCartaoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimentosCartaoMapper {

    @Mapping(source = "id", target = "codigoMovimento")
    @Mapping(source = "membro.id", target = "codigoMembroFamilia")
    @Mapping(source = "membro.nome", target = "nomeMembroFamilia")
    MovimentosCartaoDTO toDto(MovimentosCartao movimento);

    List<MovimentosCartaoDTO> toDto(List<MovimentosCartao> movimentos);

    @Mapping(source = "codigoMembroFamilia", target = "membro.id")
    MovimentosCartao toEntity(MovimentosCartaoDTO movimentosDTO);
}
