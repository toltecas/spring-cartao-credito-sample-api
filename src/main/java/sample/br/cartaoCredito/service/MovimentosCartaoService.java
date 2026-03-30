package sample.br.cartaoCredito.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sample.br.cartaoCredito.model.MembroFamilia;
import sample.br.cartaoCredito.model.MovimentosCartao;
import sample.br.cartaoCredito.model.dto.MembroFamiliaDTO;
import sample.br.cartaoCredito.model.dto.MovimentosCartaoDTO;
import sample.br.cartaoCredito.repository.MembroFamiliaRepository;
import sample.br.cartaoCredito.repository.MovimentosCartaoRepository;

import java.util.List;
import java.util.Optional;

@Service
//Pode-se usar "@Transactional" em nivel de Classe, porem com efeito apenas aos metodos Publicos (Regra IoC que cria um Pattern Proxy para essa anotacao).
//@Transactional
public class MovimentosCartaoService {

   @Autowired
   MembroFamiliaRepository membroRepository;

   @Autowired
   MovimentosCartaoRepository movimentosRepository;

   @Transactional(readOnly = true)
   public ResponseEntity<List<MembroFamilia>> findAll(){

       List<MembroFamilia> listaMembros = membroRepository.findAll();

       if(!listaMembros.isEmpty()) {
           return ResponseEntity.ok(listaMembros);
       }

       return null;
   }

   @Transactional(readOnly = true)
   public ResponseEntity<?> findById(Long id){

       Optional<MembroFamilia> membro = membroRepository.findById(id);

       if(!membro.isEmpty()) {
           return ResponseEntity.ok(membro);
       }

       return null;
   }

   // Pode-se usar apenas "@Transactional" uma vez que sao formas equivalentes, ja que o valor REQUIRED e' o padrao.
   @Transactional(propagation = Propagation.REQUIRED)
   public MembroFamilia save(MembroFamilia membro) {
       return membroRepository.save(membro);
   }

   @Transactional(propagation = Propagation.REQUIRED)
   public void deleteById(Long id) {
       membroRepository.deleteById(id);
   }

   @Transactional(propagation = Propagation.REQUIRED)
   public void atualizarMembroFamilia(Long id, MembroFamiliaDTO membroDTO) {

       MembroFamilia membro = validarMembroFamilia(id, membroDTO);

       membroRepository.save(membro);
   }
/*
   @Transactional(propagation = Propagation.REQUIRED)
    public MovimentosCartao save(MovimentosCartaoDTO movimentosDTO) {
        MovimentosCartao movimentos = new MovimentosCartao(movimentosDTO);
        return movimentosRepository.save(movimentos);
    }
*/
    
   private MembroFamilia validarMembroFamilia(Long id, MembroFamiliaDTO membroDTO){

       /** Validando o DTO (convertido)/JSON de Entrada do Controller. */
       Optional<MembroFamiliaDTO> membroDTOOpt = Optional.ofNullable(membroDTO);

       MembroFamiliaDTO membroDTOAux = membroDTOOpt.orElseThrow(
               ()-> new EntityNotFoundException("Objeto JSON enviado no Body nao pode ser nulo!")
       );

       //Parece redundante com a construcao acima. Aqui pode ser lancada a exception EntityNotFoundException.
       membroDTOOpt.ifPresent(integrante -> {
           membroDTOAux.setNome(integrante.getNome());
           membroDTOAux.setRelacaoFamiliar(integrante.getRelacaoFamiliar());
       });

       /** Validando o Objeto do Banco de Dados. */
       Optional<MembroFamilia> membroOpt = membroRepository.findById(id);

       MembroFamilia membro = membroOpt.orElseThrow(
               ()->new EntityNotFoundException("Membro Familiar com ID " + id + "Nao Encontrado!")
       );

       membro.setNome(membroDTOAux.getNome());
       membro.setRelacaoFamiliar(membroDTOAux.getRelacaoFamiliar());

       return membro;
   }
}