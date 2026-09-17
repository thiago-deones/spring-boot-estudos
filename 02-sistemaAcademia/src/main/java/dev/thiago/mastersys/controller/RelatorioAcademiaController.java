package dev.thiago.mastersys.controller;

import dev.thiago.mastersys.projection.AlunosPorCidadeProjection;
import dev.thiago.mastersys.projection.FaturamentoMensalProjection;
import dev.thiago.mastersys.projection.FaturasEmAbertoProjection;
import dev.thiago.mastersys.repository.RelatorioAcademiaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/realatorios")
public class RelatorioAcademiaController {

    private final RelatorioAcademiaRepository relatorioAcademiaRepository;

    public RelatorioAcademiaController(RelatorioAcademiaRepository repository) {
        this.relatorioAcademiaRepository = repository;
    }

    @GetMapping("/faturamento-mensal")
    public List<FaturamentoMensalProjection> faturamentoMensal(){
        return relatorioAcademiaRepository.faturamentoMensal();
    }

    @GetMapping("/alunos-por-cidade")
    public List<AlunosPorCidadeProjection> alunosPorCidade(){
        return relatorioAcademiaRepository.alunosPorCidade();
    }

    @GetMapping("/faturas-em-aberto")
    public List<FaturasEmAbertoProjection> faturasEmAberto(){
        return relatorioAcademiaRepository.faturasEmAberto();
    }
}
