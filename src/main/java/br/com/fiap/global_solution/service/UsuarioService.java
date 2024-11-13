package br.com.fiap.global_solution.service;

import br.com.fiap.global_solution.dto.UsuarioRequest;
import br.com.fiap.global_solution.dto.UsuarioResponse;
import br.com.fiap.global_solution.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    //Metodos Mapper

    public Usuario requestToUsuario(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.getNome());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(usuarioRequest.getSenha());
        return usuario;
    }

    public UsuarioResponse usuarioToResponse(Usuario usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setIdUsuario(usuario.getIdUsuario());
        usuarioResponse.setNome(usuario.getNome());
        usuarioResponse.setEmail(usuario.getEmail());
        usuarioResponse.setSenha(usuario.getSenha());
        return usuarioResponse;
    }

    public UsuarioRequest usuarioToRequest(Usuario usuario){
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setNome(usuario.getNome());
        usuarioRequest.setEmail(usuario.getEmail());
        usuarioRequest.setSenha(usuario.getSenha());
        return usuarioRequest;
    }
}
