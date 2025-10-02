package pdev.com.agenda.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pdev.com.agenda.domain.entities.Usuario;
import pdev.com.agenda.domain.repositories.UsuarioRepository;
import pdev.com.agenda.exceptions.BusinessException;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return new User(usuario.getUsuario(), usuario.getSenha(), new ArrayList<>());
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuario) {
        var result = findById(id);

        if (result.isEmpty())
            throw new BusinessException("Usuario não encontrado");

        usuario.setId(id);

        return save(usuario);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}