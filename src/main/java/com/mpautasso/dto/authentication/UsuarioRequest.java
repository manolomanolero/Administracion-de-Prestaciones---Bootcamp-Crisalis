package com.mpautasso.dto.authentication;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String username;
    private String password;
    private String email;
}
