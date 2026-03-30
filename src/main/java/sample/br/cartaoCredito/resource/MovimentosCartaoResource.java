/*
NOTA: O ideal é que seus Controllers sejam organizados com base em Recursos (Business Resources) e não na estrutura das suas classes (sejam elas Entities ou DTOs).
Logo, MembroFamiliaResource deve virar MovimentoCartaoCreditoResource, assim como seu Service.
*/

package sample.br.cartaoCredito.resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.br.cartaoCredito.model.MembroFamilia;
import sample.br.cartaoCredito.model.dto.MembroFamiliaDTO;
import sample.br.cartaoCredito.model.dto.MovimentosCartaoDTO;
import sample.br.cartaoCredito.service.MovimentosCartaoService;
import java.util.List;

//@Tag(value = "Controle dos Membros de uma Família que realiza Movimentações no Cartão de Crédito")
@RestController
@RequestMapping("/api/cartaoCredito")
public class MovimentosCartaoResource {

   @Autowired
   private MovimentosCartaoService service;

   @GetMapping("/membros-familia")
   public ResponseEntity<List<MembroFamilia>> listarMembrosFamilia() {
       return service.findAll();
   }

   @GetMapping("/membros-familia/{requestedId}")
   public ResponseEntity<?> listarMembroFamilia(@PathVariable Long requestedId) {
       return service.findById(requestedId);
   }

   @PostMapping("/familiares/v1")
   public MembroFamilia criarMembroFamilia(@RequestBody @Valid MembroFamilia membro) {
       return service.save(membro);
   }

   @DeleteMapping("/familiares/{requestedId}")
   public void apagarMembroFamilia(@PathVariable Long requestedId) {
       service.deleteById(requestedId);
   }

   /** Aqui passou-se a utilizar o Pattern DTO. Ele sera estendido para os Endpoints que utilizam Entity Classes. */
   @PutMapping("/membros-familia/{requestedId}/dados")
   public void atualizarMembroFamilia(@PathVariable(required = true) Long requestedId, @RequestBody @Valid MembroFamiliaDTO membroDTO) {
       service.atualizarMembroFamilia(requestedId, membroDTO);
   }

   @PostMapping("/familiares")
    public MembroFamilia criarMembroFamilia(@RequestBody @Valid MembroFamiliaDTO membroDTO) {
       MembroFamilia membro = new MembroFamilia(membroDTO);
        return service.save(membro);
    }
   /*
    @PostMapping("/movimentos")
    public MovimentosCartaoDTO criarMembroFamilia(@RequestBody @Valid MembroFamiliaDTO membro) {
        return service.save(membro);
    }
*/
/*
   --Precisara do @RequestBody
   @PatchMapping("/familiares/nome")
   public void atualizarNomeMembroFamilia(@RequestParam(required = true) Long id, @RequestParam(required = true) String nome) {
       service.atualizarNomeMembroFamilia(id, nome);
   }
*/

}
