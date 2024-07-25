package com.eletrosapplication.controller;

import com.eletrosapplication.domain.Eletro;
import com.eletrosapplication.service.EletroService;
import com.eletrosapplication.service.FileStorageService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Optional;


@Controller
public class EletroController {

    private final EletroService service;
    private final FileStorageService fileStorageService;

    public EletroController(EletroService service, FileStorageService fileStorageService) {
        this.service = service;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/index")
    public String listAll(Model model, HttpServletResponse response) {
        // Pegando a lista de itens cadastrados, que não estão deletados
        List<Eletro> eletros = service.findAll();

        // Adicionando a lista a um model para passar pata o html
        model.addAttribute("eletros", eletros);

        // Criando o cookie de visita
        Cookie cookie_visita  = new Cookie("visita", "true");
        cookie_visita.setMaxAge(24 * 60 * 60); // cookie de 24 horas
        response.addCookie(cookie_visita);

        return "index";
    }

    @GetMapping("/cadastroPage")
    public String getCadastroPage(Model model){
        // criando um objeto Eletro e usando o model para enviar para o html
        Eletro eletro = new Eletro();
        model.addAttribute("eletro", eletro);
        return "cadastroPage";
    }

    @PostMapping("/processCadastroPage")
    public ModelAndView processCadastroPage(@ModelAttribute @Valid Eletro eletro){
        // salva o objeto no banco de dados com as informações pegas do html
        service.create(eletro);

        // levando a nova lista de eletros atualizada para o html como o modelAndView
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("eletros", service.findAll());

        return modelAndView;
    }

    @GetMapping("/editPage/{id}")
    public ModelAndView editPage(@PathVariable String id) {
        // procura o eletro para ser editado
        Optional<Eletro> eletro = service.findById(id);

        // se o eletro não existir redireciona de volta para o index
        if(!eletro.isPresent()){
            return new ModelAndView("redirect:/index");
        }

        // redireciona para a página de editar levando o eletro escolhido
        ModelAndView modelAndView = new ModelAndView("editPage");
        modelAndView.addObject("eletro", eletro.get());

        return modelAndView;
    }

    @PostMapping("/processEditPage")
    public String processEditPage(@ModelAttribute @Valid Eletro eletro){

        // pega o id do eletro que foi passado por parametro na url
        Optional<Eletro> eletro_b = service.findById(eletro.getId());

        // modifica com as novas informações pegas do html caso o eletro existir
        if(eletro_b.isPresent()){
            service.update(eletro);
        }
        return "redirect:/index";
    }

    @GetMapping("/processDelete/{id}")
    public String processDelete(@PathVariable String id){
        // "remove" o eletro do id passado pela url, trocando seu atributo isDeleted
        service.delete(id);

        return "redirect:/index";
    }
}
