package br.com.fiap.global_solution.service;

import br.com.fiap.global_solution.dto.AparelhoRequest;
import br.com.fiap.global_solution.dto.AparelhoResponse;
import br.com.fiap.global_solution.model.Aparelho;
import br.com.fiap.global_solution.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AparelhoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Aparelho requestToAparelho(AparelhoRequest aparelhoRequest) {
        Aparelho aparelho = new Aparelho();
        aparelho.setNome(aparelhoRequest.getNome());
        aparelho.setPotencia(aparelhoRequest.getPotencia());
        aparelho.setTipo(aparelhoRequest.getTipo());
        aparelho.setUsuario(aparelhoRequest.getUsuario());
        return aparelho;
    }

    public AparelhoResponse aparelhoToResponse(Aparelho aparelho) {
        AparelhoResponse aparelhoResponse = new AparelhoResponse();
        aparelhoResponse.setIdAparelho(aparelho.getIdAparelho());
        aparelhoResponse.setNome(aparelho.getNome());
        aparelhoResponse.setPotencia(aparelho.getPotencia());
        aparelhoResponse.setTipo(aparelho.getTipo());
        aparelhoResponse.setUsuario(aparelho.getUsuario());
        return aparelhoResponse;
    }

    public AparelhoRequest aparelhoToRequest(Aparelho aparelho) {
        AparelhoRequest aparelhoRequest = new AparelhoRequest();
        aparelhoRequest.setNome(aparelho.getNome());
        aparelhoRequest.setPotencia(aparelho.getPotencia());
        aparelhoRequest.setTipo(aparelho.getTipo());
        aparelhoRequest.setUsuario(aparelho.getUsuario());
        return aparelhoRequest;
    }
}
