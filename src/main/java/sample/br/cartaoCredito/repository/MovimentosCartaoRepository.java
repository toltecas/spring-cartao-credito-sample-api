package sample.br.cartaoCredito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.br.cartaoCredito.model.MovimentosCartao;


public interface MovimentosCartaoRepository extends JpaRepository<MovimentosCartao, Long> {
}
