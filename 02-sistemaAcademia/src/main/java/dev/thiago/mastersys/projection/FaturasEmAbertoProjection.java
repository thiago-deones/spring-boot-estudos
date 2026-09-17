package dev.thiago.mastersys.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface FaturasEmAbertoProjection {

    Long getMatriculaID();

    String getAlunoNome();

    LocalDate getDataVencimento();

    BigDecimal getValor();
}
