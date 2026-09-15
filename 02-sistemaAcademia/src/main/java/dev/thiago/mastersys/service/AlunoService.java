package dev.thiago.mastersys.service;

import dev.thiago.mastersys.domain.Aluno;
import dev.thiago.mastersys.dto.AlunoRequest;
import dev.thiago.mastersys.dto.AlunoResponse;
import dev.thiago.mastersys.repository.AlunoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    public final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponse cadastrar(AlunoRequest request) {
        if (request.email() != null && alunoRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Já existe um aluno cadastrado com este email");
        }

        Aluno aluno = request.toEntity();
        Aluno alunoSalvo = alunoRepository.save(aluno);
        return AlunoResponse.fromEntity(alunoSalvo);
    }

    public Page<AlunoResponse> listar(Pageable pageable) {
        return alunoRepository.findAll(pageable).map(AlunoResponse::fromEntity);
    }

    public AlunoResponse buscarPorId(Long id) {
        Aluno aluno = buscarEntidadePorId(id);
        return AlunoResponse.fromEntity(aluno);
    }

    public AlunoResponse atualizar(Long id, AlunoRequest request) {
        Aluno aluno = buscarEntidadePorId(id);
        request.preencher(aluno);
        Aluno alunoAtualizado = alunoRepository.save(aluno);
        return AlunoResponse.fromEntity(alunoAtualizado);
    }

    public void excluir(Long id) {
        Aluno aluno = buscarEntidadePorId(id);
        alunoRepository.delete(aluno);
    }

    public Aluno buscarEntidadePorId(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }
}
