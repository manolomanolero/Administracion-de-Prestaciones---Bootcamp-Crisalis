package com.mpautasso.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioRegisterResponse {
    private String username;
    private String email;
    private String token;
}
