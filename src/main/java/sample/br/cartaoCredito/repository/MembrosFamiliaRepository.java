package sample.br.cartaoCredito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.br.cartaoCredito.model.MembrosFamilia;

public interface MembrosFamiliaRepository extends JpaRepository<MembrosFamilia, Long> {
}
