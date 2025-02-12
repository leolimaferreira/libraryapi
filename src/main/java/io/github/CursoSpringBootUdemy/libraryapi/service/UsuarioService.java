package io.github.CursoSpringBootUdemy.libraryapi.service;

import io.github.CursoSpringBootUdemy.libraryapi.model.Usuario;
import io.github.CursoSpringBootUdemy.libraryapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public void salvar(Usuario usuario) {
        var senha = usuario.getSenha();
        usuario.setSenha(encoder.encode(senha));
        usuarioRepository.save(usuario);
    }

    public Usuario obterPorLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public Usuario obterPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
