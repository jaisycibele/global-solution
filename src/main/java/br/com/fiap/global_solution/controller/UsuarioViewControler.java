package br.com.fiap.global_solution.controller;

import br.com.fiap.global_solution.dto.UsuarioRequest;
import br.com.fiap.global_solution.dto.UsuarioResponse;
import br.com.fiap.global_solution.model.Usuario;
import br.com.fiap.global_solution.repository.UsuarioRepository;
import br.com.fiap.global_solution.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UsuarioViewControler {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/listaUsuarios")
    public ModelAndView listaUsuarios() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        List<UsuarioResponse> listaUsuariosResponse = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            listaUsuariosResponse.add(usuarioService.usuarioToResponse(usuario));
        }
        ModelAndView mv = new ModelAndView("usuarios");
        mv.addObject("listaUsuarios", listaUsuariosResponse);
        return mv;
    }

    @GetMapping("/usuario/template")
    public String template() {
        return "templateVazio";
    }

    @GetMapping("/listaUsuariosTemplate")
    public ModelAndView listaUsuariosTemplate() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        List<UsuarioResponse> listaUsuariosResponse = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            listaUsuariosResponse.add(usuarioService.usuarioToResponse(usuario));
        }
        ModelAndView mv = new ModelAndView("template");
        mv.addObject("page", "usuarios");
        mv.addObject("content", "listaUsuarios");
        mv.addObject("listaUsuarios", listaUsuariosResponse);
        return mv;
    }

    @GetMapping("/cadastroUsuario")
    public ModelAndView cadastroUsuario() {
        ModelAndView mv = new ModelAndView("template");
        mv.addObject("page", "usuarioCadastro");
        mv.addObject("usuarioRequest", "formCadastro");
        mv.addObject("usuarioRequest", new UsuarioRequest());
        return mv;
    }

    @RequestMapping(value = "cadastrarUsuario", method = RequestMethod.POST)
    public ModelAndView cadastrarUsuario(@ModelAttribute UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioService.requestToUsuario(usuarioRequest);
        usuarioRepository.save(usuario);
        return listaUsuariosTemplate();
    }

    @GetMapping("/editaUsuario/{idUsuario}")
    public ModelAndView editaUsuario(@PathVariable Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if(usuario.isEmpty()){
            return listaUsuariosTemplate();
        }
        ModelAndView mv = new ModelAndView("template");
        mv.addObject("page", "usuarioEdicao");
        mv.addObject("content", "formEdicao");
        mv.addObject("usuarioRequest", usuarioService.usuarioToRequest(usuario.get()));
        mv.addObject("idUsuario", idUsuario);
        return mv;
    }

    @PostMapping("/atualizarUsuario/{idUsuario}")
    public ModelAndView atualizarUsuario(@PathVariable Long idUsuario,
                                       @Valid @ModelAttribute UsuarioRequest usuarioRequest){
        if(usuarioRepository.findById(idUsuario).isEmpty()){
            //adicionar informação de erro
            return listaUsuariosTemplate();
        }
        Usuario usuario = usuarioService.requestToUsuario(usuarioRequest);
        usuario.setIdUsuario(idUsuario);
        usuarioRepository.save(usuario);
        return listaUsuariosTemplate();
    }

    @GetMapping("/deletarUsuario/{idUsuario}")
    public ModelAndView deletarUsuario(@PathVariable Long idUsuario){
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if(usuario.isEmpty()){
            //tratar exceção
            return listaUsuariosTemplate();
        }
        usuarioRepository.delete(usuario.get());
        return listaUsuariosTemplate();
    }














































}
