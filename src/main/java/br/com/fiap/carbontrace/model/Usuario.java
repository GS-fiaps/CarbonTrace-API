package br.com.fiap.carbontrace.model;

import br.com.fiap.carbontrace.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "TB_USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;
    private LocalDate dataCadastro;

    @OneToMany
    private List<Ocorrencia> ocorrencias;

    @OneToMany
    private List<Relatorio> relatorios;
}
