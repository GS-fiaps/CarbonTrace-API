package br.com.fiap.carbontrace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "TB_ESTADO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;
    private String nome;
    private String sigla;

    @OneToMany
    private List <OrgaoAmbiental> orgaosAmbientais;

    @OneToMany
    private List<Regiao> regioes;
}
