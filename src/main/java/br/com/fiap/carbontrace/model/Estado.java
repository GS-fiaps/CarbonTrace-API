package br.com.fiap.carbontrace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Estado")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;
    private String nome;
    private String sigla;
    private List <Regiao>regioes ;
    private List <OrgaoAmbiental> orgaosAmbientais;
}
