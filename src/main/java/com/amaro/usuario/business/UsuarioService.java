package com.amaro.usuario.business;


import com.amaro.usuario.business.converter.UsuarioConverter;
import com.amaro.usuario.business.dto.UsuarioDTO;
import com.amaro.usuario.infrastruture.entity.Usuario;
import com.amaro.usuario.infrastruture.exceptions.ConflictException;
import com.amaro.usuario.infrastruture.exceptions.ResourceNotFoundException;
import com.amaro.usuario.infrastruture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }

    public void emailExiste(String email){
        try{
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw  new ConflictException("Email já cadastrado " + email);
            }
        } catch (ConflictException e) {
            throw new RuntimeException("Email já cadastrado ", e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException(
                        String.format("Email [%s] não encontrado", email)
                )
        );
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }
}

