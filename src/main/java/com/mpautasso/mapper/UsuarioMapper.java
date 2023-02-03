package com.mpautasso.mapper;

import com.mpautasso.dto.authentication.AuthRequest;
import com.mpautasso.dto.authentication.UsuarioRegisterResponse;
import com.mpautasso.dto.authentication.UsuarioRequest;
import com.mpautasso.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario authRequestToEntity(AuthRequest authRequest){
        return new Usuario(authRequest.getUsername(), authRequest.getPassword(), authRequest.getUsername());
    }

    public Usuario usuarioRequestToEntity(UsuarioRequest usuarioRequest){
        return new Usuario(usuarioRequest.getUsername(), usuarioRequest.getPassword(), usuarioRequest.getEmail());
    }

    public UsuarioRegisterResponse entityToUsuarioRegisterResponse(Usuario usuario, String token){
        return new UsuarioRegisterResponse(usuario.getUsername(), usuario.getEmail(), token);
    }
}
