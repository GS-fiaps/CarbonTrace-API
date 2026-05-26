package br.com.fiap.carbontrace.model;

import br.com.fiap.carbontrace.enums.TipoOrgao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "TB_ORGAO_AMBIENTAL")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrgaoAmbiental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrgao;
    private String nome;
    @Enumerated
    private TipoOrgao tipo;
    private String emailContato;

    @OneToMany
    private List<AlertaOrgao> alertasOrgaos;

    @ManyToOne
    @JoinColumn(name = "fk_orgao_estado")
    private Estado estado;
}
