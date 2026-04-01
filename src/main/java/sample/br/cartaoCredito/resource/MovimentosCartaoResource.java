/*
NOTA: O ideal é que seus Controllers sejam organizados com base em Recursos (Business Resources) e não na estrutura das suas classes (sejam elas Entities ou DTOs).
*/

package sample.br.cartaoCredito.resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sample.br.cartaoCredito.model.MembrosFamilia;
import sample.br.cartaoCredito.model.dto.MembrosFamiliaDTO;
import sample.br.cartaoCredito.model.dto.MovimentosCartaoDTO;
import sample.br.cartaoCredito.service.MovimentosCartaoService;

import java.net.URI;
import java.util.List;

//@Tag(value = "Controle dos Membros de uma Família que realiza Movimentações no Cartão de Crédito")
@RestController
@RequestMapping("/api/cartaoCredito")
public class MovimentosCartaoResource {

   @Autowired
   private MovimentosCartaoService service;

   @GetMapping("/membros-familia")
   public ResponseEntity<List<MembrosFamilia>> listarMembrosFamilia() {
       return service.findAll();
   }

   @GetMapping("/membros-familia/{requestedId}")
   public ResponseEntity<?> listarMembroFamilia(@PathVariable Long requestedId) {
       return service.findById(requestedId);
   }

   @Deprecated
   @PostMapping("/familiares/v1")
   public MembrosFamilia criarMembroFamilia(@RequestBody @Valid MembrosFamilia membro) {
       return service.save(membro);
   }

   @PostMapping("/familiares")
   public MembrosFamilia criarMembroFamilia(@RequestBody @Valid MembrosFamiliaDTO membroDTO) {
        MembrosFamilia membro = new MembrosFamilia(membroDTO);
        return service.save(membro);
    }

   @DeleteMapping("/familiares/{requestedId}")
   public void apagarMembroFamilia(@PathVariable Long requestedId) {
       service.deleteById(requestedId);
   }

   /** Aqui passou-se a utilizar o Pattern DTO. Ele foi estendido para alguns Endpoints que utilizam Entity Classes. */
   @PutMapping("/membros-familia/{requestedId}/dados")
   public void atualizarMembroFamilia(@PathVariable(required = true) Long requestedId, @RequestBody @Valid MembrosFamiliaDTO membroDTO) {
       service.atualizarMembroFamilia(requestedId, membroDTO);
   }

    @Deprecated
    @PostMapping("/movimentos/v1")
    public MovimentosCartaoDTO criarMovimento(@RequestBody @Valid MovimentosCartaoDTO movimentosDTO) {
        return service.salvarMovimento(movimentosDTO);
    }

    @PostMapping("/movimentos")
    public ResponseEntity<MovimentosCartaoDTO>  criarMovimentoCartao(@RequestBody @Valid MovimentosCartaoDTO movimentosDTO) {

        MovimentosCartaoDTO movimentosDTOAux = service.salvarMovimento(movimentosDTO);

        //Monta a URI para o novo recurso (ex: /movimentos)
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/movimentos")
                .buildAndExpand(movimentosDTOAux.codigoMovimento())
                .toUri();

        //Permite receber: Status Code 201, o header Location, alem do objeto do body.
        return ResponseEntity.created(uri).body(movimentosDTOAux);
    }

    @GetMapping("/movimentos-cartao")
    public ResponseEntity<List<MovimentosCartaoDTO>> listarMovimentos() {
        return service.listarMovimentos();
    }

/*
   --Precisara do @RequestBody
   @PatchMapping("/familiares/nome")
   public void atualizarNomeMembroFamilia(@RequestParam(required = true) Long id, @RequestParam(required = true) String nome) {
       service.atualizarNomeMembroFamilia(id, nome);
   }
*/

}
