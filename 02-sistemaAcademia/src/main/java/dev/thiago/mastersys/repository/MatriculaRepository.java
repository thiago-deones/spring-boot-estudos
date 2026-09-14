package dev.thiago.mastersys.repository;

import dev.thiago.mastersys.domain.MatriculaModalidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<MatriculaModalidade, Long> {
}
