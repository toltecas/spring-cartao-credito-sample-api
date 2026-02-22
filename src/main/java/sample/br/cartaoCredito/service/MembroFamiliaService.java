package sample.br.cartaoCredito.service;

//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sample.br.cartaoCredito.model.MembroFamilia;
import sample.br.cartaoCredito.repository.MembroFamiliaRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MembroFamiliaService {

    //@Autowired
    //Logger logger;

    @Autowired
    MembroFamiliaRepository repository;

    public ResponseEntity<?> findAll(){

        List<MembroFamilia> listaMembros = repository.findAll();

        if(!listaMembros.isEmpty()) {
            return ResponseEntity.ok(listaMembros);
        }

        return null;
    }

    public ResponseEntity<?> findById(Long id){

        Optional<MembroFamilia> listaMembros = repository.findById(id);

        if(!listaMembros.isEmpty()) {
            return ResponseEntity.ok(listaMembros);
        }

        return null;
    }

/*
    public MembroFamilia save(MembroFamilia membro) {
        return repository.save(membro);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

 */
}