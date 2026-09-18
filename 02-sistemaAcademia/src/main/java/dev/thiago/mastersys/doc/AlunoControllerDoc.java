package dev.thiago.mastersys.doc;

import dev.thiago.mastersys.domain.Aluno;
import dev.thiago.mastersys.dto.AlunoFiltroRequest;
import dev.thiago.mastersys.dto.AlunoRequest;
import dev.thiago.mastersys.dto.AlunoResponse;
import dev.thiago.mastersys.exception.ErroResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ProblemDetail; // Substituído para documentação OpenAPI adequada
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Alunos",
        description = "Operações para cadastro, consulta, atualização, exclusão"
)
public interface AlunoControllerDoc {

    @Operation(
            summary = "Cadastrar aluno",
            description = "Cria um novo aluno no sistema de academia",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Aluno cadastrado com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação ou regra de negócio",
                            content = @Content(schema = @Schema(implementation = ProblemDetail.class))
                    )
            }
    )
    default AlunoResponse cadastrar(
            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados necessários para cadastrar um aluno",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = AlunoRequest.class),
                            examples = @ExampleObject(
                                    name = "Aluno válido",
                                    value = """
                                            {
                                                  "nome": "João da Silva atualizado",
                                                  "dataNascimento": "1998-05-02",
                                                  "sexo": "M",
                                                  "telefone": "32482348",
                                                  "celular": "232342341",
                                                  "observacao":"Aluno intermediario",
                                                  "endereco": "rua das orquideas",
                                                  "numero": "333",
                                                  "complemento": "casa",
                                                  "cidade": "centro",
                                                  "estado": "SC",
                                                  "cep": "238129238"
                                            }
                                            """
                            )
                    )
            )
            AlunoRequest alunoRequest
    ) {
        return null; // Corpo necessário para métodos 'default' em interfaces
    }
    @Operation(
            summary = "Listar alunos",
            description = "Listar alunos de forma paginada, permitindo filtros opcionais por "+
                    "nome, e-mail, celular, cidade e estado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de alunos retornada com sucesso")
            }
    )
    Page<AlunoResponse> listar(
            @Parameter(description = "Filtros opcionais para busca de alunos")
            AlunoFiltroRequest filtro,

            @Parameter(description = "Informações de paginação e ordenação")
            Pageable pageable
    );

    @Operation(
            summary = "Buscar aluno por id",
            description = "Retorna os dados resumidos de um aluino espésifico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Aluno encontrado"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Aluno não encontrado",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    AlunoResponse buscarPorId(
            @Parameter(description = "ID do aluno", example = "2", required = true)
            Long id
    );

    @Operation(
            summary = "Atualizar aluno",
            description = "Atualiza os dados de um aluno existente no sistema",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Aluno atualizado com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação ou aluno não encontrado",
                            content = @Content(
                                    schema = @Schema(implementation = ErroResponse.class)
                            )
                    )
            }
    )
    default AlunoResponse atualizar(
            @Parameter(
                    description = "ID do aluno",
                    example = "2",
                    required = true
            )
            Long id,

            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para atualização do aluno",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = AlunoRequest.class),
                            examples = @ExampleObject(
                                    name = "Aluno atualizado",
                                    value = """
                                        {
                                              "nome": "João da Silva atualizado",
                                              "dataNascimento": "1998-05-02",
                                              "sexo": "M",
                                              "telefone": "32482348",
                                              "celular": "232342341",
                                              "email": "joao@email.com",
                                              "observacao": "Aluno intermediario",
                                              "endereco": "rua das orquideas",
                                              "numero": "333",
                                              "complemento": "casa",
                                              "bairro": "Centro",
                                              "cidade": "Santa Luzia",
                                              "estado": "MG",
                                              "cep": "23812923"
                                        }
                                        """
                            )
                    )
            )
            AlunoRequest alunoRequest
    ) {
        return null;
    }


    @Operation(
            summary = "Excluir aluno",
            description = "Exclui um aluno existente do sistema",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Aluno excluído com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Aluno não encontrado",
                            content = @Content(
                                    schema = @Schema(implementation = ErroResponse.class)
                            )
                    )
            }
    )
    default void excluir(
            @Parameter(
                    description = "ID do aluno",
                    example = "2",
                    required = true
            )
            Long id
    ) {
    }
}