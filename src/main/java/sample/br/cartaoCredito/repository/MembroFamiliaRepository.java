package sample.br.cartaoCredito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.br.cartaoCredito.model.MembroFamilia;

public interface MembroFamiliaRepository extends JpaRepository<MembroFamilia, Long> {

}
