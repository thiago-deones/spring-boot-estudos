package dev.thiago.mastersys.repository;

import dev.thiago.mastersys.domain.FaturaMatricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
FaturaMatriculaRepository extends JpaRepository<FaturaMatricula, Long> {
}
