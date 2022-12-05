package com.mpautasso.security;

import java.util.Optional;

import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.model.Usuario;
import com.mpautasso.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MyAuthenticationManager implements AuthenticationManager {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(authentication.getName());
        if(usuario.isEmpty()) {
            usuario = usuarioRepository.findByEmail(authentication.getName());
        }

        if (usuario.isEmpty()) {
            throw new InvalidArgumentException("El usuario no fue encontrado");
        }

        if (!passwordEncoder.matches(authentication.getCredentials().toString(), usuario.get().getPassword())) {
            throw new InvalidArgumentException("El usuario o la contraseña es inválida");
        }

        Authentication authResponse = new UsernamePasswordAuthenticationToken(usuario.get(), null, usuario.get().getAuthorities());

        return authResponse;
    }

}