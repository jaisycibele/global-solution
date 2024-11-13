package br.com.fiap.global_solution.service;

import br.com.fiap.global_solution.dto.PrecoEnergiaRequest;
import br.com.fiap.global_solution.dto.PrecoEnergiaResponse;
import br.com.fiap.global_solution.model.PrecoEnergia;
import org.springframework.stereotype.Service;

@Service
public class PrecoEnergiaService {

    public PrecoEnergia requestToPrecoEnergia(PrecoEnergiaRequest precoEnergiaRequest) {
        PrecoEnergia precoEnergia = new PrecoEnergia();
        precoEnergia.setData(precoEnergiaRequest.getData());
        precoEnergia.setPrecoKwh(precoEnergiaRequest.getPrecoKwh());
        return precoEnergia;
    }

    public PrecoEnergiaResponse precoEnergiaToResponse(PrecoEnergia precoEnergia) {
        PrecoEnergiaResponse precoEnergiaResponse = new PrecoEnergiaResponse();
        precoEnergiaResponse.setIdPrecoEnergia(precoEnergia.getIdPrecoEnergia());
        precoEnergiaResponse.setData(precoEnergia.getData());
        precoEnergiaResponse.setPrecoKwh(precoEnergia.getPrecoKwh());
        return precoEnergiaResponse;
    }

    public PrecoEnergiaRequest precoEnergiaToRequest(PrecoEnergia precoEnergia) {
        PrecoEnergiaRequest precoEnergiaRequest = new PrecoEnergiaRequest();
        precoEnergiaRequest.setData(precoEnergia.getData());
        precoEnergiaRequest.setPrecoKwh(precoEnergia.getPrecoKwh());
        return precoEnergiaRequest;
    }
}
