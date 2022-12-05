package com.mpautasso.service.Implementation;

import com.mpautasso.dto.AuthRequest;
import com.mpautasso.dto.AuthResponse;
import com.mpautasso.dto.UsuarioRegisterResponse;
import com.mpautasso.dto.UsuarioRequest;
import com.mpautasso.exception.EntityNotFoundException;
import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.exception.InvalidPetitionException;
import com.mpautasso.mapper.UsuarioMapper;
import com.mpautasso.model.Rol;
import com.mpautasso.model.Usuario;
import com.mpautasso.repository.RolRepository;
import com.mpautasso.repository.UsuarioRepository;
import com.mpautasso.security.JwtUtils;
import com.mpautasso.security.MyAuthenticationManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UsuarioServiceImpl {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    private MyAuthenticationManager authManager;
    private final JwtUtils jwtUtils;


    public List<Usuario> listarUsuarios() {

        return usuarioRepository.findAll();
    }


    public UsuarioRegisterResponse crearUsuario(UsuarioRequest usuarioRequest) {
        log.info("Metodo crear usuario inicial");
        Usuario usuario = usuarioMapper.usuarioRequestToEntity(usuarioRequest);
        if(existeUsuario(usuario)){
            throw new InvalidArgumentException("El email o username ya pertenece a otro usuario.");
        }

        log.info("Luego de Existe usuario ? ");
        String oldPassword = usuario.getPassword();
        String encodedPassword = passwordEncoder.encode(oldPassword);

        usuario.setPassword(encodedPassword);

        usuario.agregarRol(rolRepository.findByNombre("ROLE_USER"));

        usuario = usuarioRepository.save(usuario);

        String accessToken = jwtUtils.generateAccessToken(usuario);

        return usuarioMapper.entityToUsuarioRegisterResponse(usuario, accessToken);
    }

    public AuthResponse loguearUsuario(AuthRequest authRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername().trim().toLowerCase(),
                        authRequest.getPassword()
                )
        );

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String accessToken = jwtUtils.generateAccessToken(usuario);

        return new AuthResponse(accessToken);
    }


    public Optional<Usuario> buscarUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }


    public long buscarIdUsuarioPorUsername(String username) {
        Optional<Usuario> usuarioOpt = buscarUsuarioPorUsername(username);
        return usuarioOpt.isPresent() ? usuarioOpt.get().getId() : 0;
    }

    public void agregarRolAUsuario(String username, String rolNombre) {
        Optional<Usuario> usuarioBD = usuarioRepository.findByUsername(username);
        if(usuarioBD.isEmpty()) {
            throw new EntityNotFoundException("No se encontro ningún usuario con el username " + username);
        }
        Usuario usuario = usuarioBD.get();

        Rol rolBD = rolRepository.findByNombre(rolNombre);

        if(usuario.getRoles().contains(rolBD)) {
            throw new InvalidPetitionException("El usuario ya tiene el rol que se le quiere asignar.");
        }
        usuario.agregarRol(rolBD);

        usuarioRepository.save(usuario);
    }

    //Busca si existe un usuario en la BD por username o email
    private boolean existeUsuario(Usuario usuario) {
        boolean existe = false;
        if(usuarioRepository.findByUsername(usuario.getUsername()).isPresent() ||
                usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            existe = true;
        }
        return existe;
    }

}
