package br.com.fiap.carbontrace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "TB_RELATORIO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRelatorio;
    private LocalDate dataGeracao;
    private String titulo;
    private LocalDate periodoInicio;
    private LocalDate periodoFim;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @PrePersist
    public void prePersist() {
        this.dataGeracao = LocalDate.now();
    }
}
