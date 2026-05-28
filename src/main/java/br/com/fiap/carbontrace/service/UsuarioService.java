package br.com.fiap.carbontrace.service;

import br.com.fiap.carbontrace.dto.request.UsuarioRequest;
import br.com.fiap.carbontrace.dto.response.UsuarioResponse;
import br.com.fiap.carbontrace.model.Usuario;
import br.com.fiap.carbontrace.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponse cadastrar(UsuarioRequest request) {
        Usuario usuario = request.toEntity();
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return UsuarioResponse.fromEntity(usuarioSalvo);
    }

    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponse::fromEntity)
                .toList();
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

        return UsuarioResponse.fromEntity(usuario);
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        usuario.setTipoUsuario(request.tipoUsuario());

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return UsuarioResponse.fromEntity(usuarioAtualizado);
    }

    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Usuário não encontrado"
            );
        }

        usuarioRepository.deleteById(id);
    }
}