package sample.br.cartaoCredito.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sample.br.cartaoCredito.model.MembroFamilia;
import sample.br.cartaoCredito.model.dto.MembroFamiliaDTO;
import sample.br.cartaoCredito.repository.MembroFamiliaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentosCartaoService {

   @Autowired
   MembroFamiliaRepository repository;

   @Transactional(readOnly = true)
   public ResponseEntity<List<MembroFamilia>> findAll(){

       List<MembroFamilia> listaMembros = repository.findAll();

       if(!listaMembros.isEmpty()) {
           return ResponseEntity.ok(listaMembros);
       }

       return null;
   }

   @Transactional(readOnly = true)
   public ResponseEntity<?> findById(Long id){

       Optional<MembroFamilia> membro = repository.findById(id);

       if(!membro.isEmpty()) {
           return ResponseEntity.ok(membro);
       }

       return null;
   }

   // Pode-se usar apenas "@Transactional" uma vez que sao formas equivalentes, ja que o valor REQUIRED e' o padrao.
   @Transactional(propagation = Propagation.REQUIRED)
   public MembroFamilia save(MembroFamilia membro) {
       return repository.save(membro);
   }

   @Transactional(propagation = Propagation.REQUIRED)
   public MembroFamilia save(MembroFamiliaDTO membroDTO) {
       MembroFamilia membro = new MembroFamilia(membroDTO);
       return repository.save(membro);
   }

   @Transactional(propagation = Propagation.REQUIRED)
   public void deleteById(Long id) {
       repository.deleteById(id);
   }

   @Transactional(propagation = Propagation.REQUIRED)
   public void atualizarMembroFamilia(Long id, MembroFamiliaDTO membroDTO) {

       MembroFamilia membro = validarMembroFamilia(id, membroDTO);

       repository.save(membro);
   }

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
       Optional<MembroFamilia> membroOpt = repository.findById(id);

       MembroFamilia membro = membroOpt.orElseThrow(
               ()->new EntityNotFoundException("Membro Familiar com ID " + id + "Nao Encontrado!")
       );

       membro.setNome(membroDTOAux.getNome());
       membro.setRelacaoFamiliar(membroDTOAux.getRelacaoFamiliar());

       return membro;
   }
}