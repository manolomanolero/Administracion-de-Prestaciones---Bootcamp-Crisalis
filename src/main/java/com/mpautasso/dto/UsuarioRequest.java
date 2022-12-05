package com.mpautasso.dto;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String username;
    private String password;
    private String email;
}
