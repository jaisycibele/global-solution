package br.com.fiap.global_solution.repository;

import br.com.fiap.global_solution.model.Aparelho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AparelhoRepository extends JpaRepository<Aparelho, Long> {
}
