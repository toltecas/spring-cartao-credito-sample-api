package sample.br.cartaoCredito.resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.br.cartaoCredito.model.MembroFamilia;
import sample.br.cartaoCredito.repository.MembroFamiliaRepository;
import sample.br.cartaoCredito.service.MembroFamiliaService;

@RestController
@RequestMapping("/api/cartaoCredito")
public class MembroFamiliaResource {

    @Autowired
    private MembroFamiliaService service;

    @GetMapping("/membros")
    public ResponseEntity<?> listarMembrosFamilia() {
        return service.findAll();
    }

    @GetMapping("/membros/{requestedId}")
    public ResponseEntity<?> listarMembrosFamilia(@PathVariable Long requestedId) {
        return service.findById(requestedId);
    }

    /*
    @PostMapping
    public MembroFamilia criarMembroFamilia(@RequestBody MembroFamilia membro) {
        return service.save(membro);
    }

    @DeleteMapping("/{id}")
    public void apagarMembroFamilia(@PathVariable Long id) {
        service.deleteById(id);
    }

 */
}