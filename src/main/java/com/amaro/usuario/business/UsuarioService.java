package com.amaro.usuario.business;


import com.amaro.usuario.business.converter.UsuarioConverter;
import com.amaro.usuario.business.dto.UsuarioDTO;
import com.amaro.usuario.infrastruture.entity.Usuario;
import com.amaro.usuario.infrastruture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }
}
