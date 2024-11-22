package br.com.fiap.global_solution.controller;

import br.com.fiap.global_solution.dto.AparelhoRequest;
import br.com.fiap.global_solution.dto.AparelhoResponse;
import br.com.fiap.global_solution.model.Aparelho;
import br.com.fiap.global_solution.repository.AparelhoRepository;
import br.com.fiap.global_solution.service.AparelhoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AparelhoControllerView {

    @Autowired
    private AparelhoService aparelhoService;
    @Autowired
    private AparelhoRepository aparelhoRepository;

    @GetMapping("/listaAparelhos")
    public ModelAndView listaAparelhos() {
        List<Aparelho> listaAparelhos = aparelhoRepository.findAll();
        List<AparelhoResponse> listaAparelhosResponse = new ArrayList<>();
        for (Aparelho aparelho : listaAparelhos) {
            listaAparelhosResponse.add(aparelhoService.aparelhoToResponse(aparelho));
        }
        ModelAndView mv = new ModelAndView("aparelhos");
        mv.addObject("listaAparelhos", listaAparelhosResponse);
        return mv;
    }

    @GetMapping("/cadastroAparelho")
    public ModelAndView cadastroAparelho() {
        ModelAndView mv = new ModelAndView("template");
        mv.addObject("page", "aparelhoCadastro");
        mv.addObject("content", "formCadastroAparelho");
        mv.addObject("aparelhoRequest", new AparelhoRequest());
        return mv;
    }

    @PostMapping("/cadastrarAparelho")
    public ModelAndView cadastrarAparelho(@ModelAttribute AparelhoRequest aparelhoRequest) {
        Aparelho aparelho = aparelhoService.requestToAparelho(aparelhoRequest);
        aparelhoRepository.save(aparelho);
        return listaAparelhos();
    }

    @GetMapping("/editaAparelho/{idAparelho}")
    public ModelAndView editaAparelho(@PathVariable Long idAparelho) {
        Optional<Aparelho> aparelho = aparelhoRepository.findById(idAparelho);
        if (aparelho.isEmpty()) {
            return listaAparelhos();
        }
        ModelAndView mv = new ModelAndView("template");
        mv.addObject("page", "aparelhoEdicao");
        mv.addObject("content", "formEdicaoAparelho");
        mv.addObject("aparelhoRequest", aparelhoService.aparelhoToRequest(aparelho.get()));
        mv.addObject("idAparelho", idAparelho);
        return mv;
    }

    @PostMapping("/atualizarAparelho/{idAparelho}")
    public ModelAndView atualizarAparelho(@PathVariable Long idAparelho,
                                          @Valid @ModelAttribute AparelhoRequest aparelhoRequest) {
        if (aparelhoRepository.findById(idAparelho).isEmpty()) {
            // adicionar informação de erro
            return listaAparelhos();
        }
        Aparelho aparelho = aparelhoService.requestToAparelho(aparelhoRequest);
        aparelho.setIdAparelho(idAparelho);
        aparelhoRepository.save(aparelho);
        return listaAparelhos();
    }

    @GetMapping("/deletarAparelho/{idAparelho}")
    public ModelAndView deletarAparelho(@PathVariable Long idAparelho) {
        Optional<Aparelho> aparelho = aparelhoRepository.findById(idAparelho);
        if (aparelho.isEmpty()) {
            // tratar exceção
            return listaAparelhos();
        }
        aparelhoRepository.delete(aparelho.get());
        return listaAparelhos();
    }
}
