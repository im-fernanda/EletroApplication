package com.eletrosapplication.controller;

import com.eletrosapplication.domain.Eletro;
import com.eletrosapplication.service.EletroService;
import com.eletrosapplication.service.FileStorageService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.CacheControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Controller
public class EletroController {

    private final EletroService service;
    private final FileStorageService fileStorageService;

    public EletroController(EletroService service, FileStorageService fileStorageService) {
        this.service = service;
        this.fileStorageService = fileStorageService;
    }


    @GetMapping("/admin")
    public String listAll(Model model, HttpServletResponse response, HttpSession session) {
        // Pegando a lista de itens cadastrados, que não estão deletados
        List<Eletro> eletros = service.findAll();

        // Adicionando a lista a um model para passar pata o html
        model.addAttribute("eletros", eletros);

        List<Eletro> carrinho = (List<Eletro>) session.getAttribute("carrinho");

        int quantidade = (carrinho != null) ? carrinho.size() : 0;

        System.out.println("Quantidade de itens no carrinho: " + quantidade);
        model.addAttribute("quantidade", quantidade);

        return "index";
    }

    @GetMapping("/cadastroPage")
    public String getCadastroPage(Model model){
        // criando um objeto Eletro e usando o model para enviar para o html
        Eletro eletro = new Eletro();
        model.addAttribute("eletro", eletro);
        return "cadastroPage";
    }

    @PostMapping("/processSave/{editar_ou_cadastrar}")
    public ModelAndView processSave(@ModelAttribute @Valid Eletro eletro, @RequestParam("file") MultipartFile file, BindingResult result, @PathVariable String editar_ou_cadastrar){
        if(Objects.equals(editar_ou_cadastrar, "edit")){
            // pega o id do eletro que foi passado por parametro na url
            Optional<Eletro> eletro_b = service.findById(eletro.getId());

            // modifica com as novas informações pegas do html caso o eletro existir
            if(eletro_b.isPresent()) {
                eletro.setImageUrl(file.getOriginalFilename());
                fileStorageService.save(file);

                service.update(eletro);
                System.out.println("editou");
            }
        }

        if(Objects.equals(editar_ou_cadastrar, "cad")){
            if (result.hasErrors()) {
                ModelAndView mav = new ModelAndView("cadastroPage");
                mav.addObject("eletro", eletro);
                return mav;
            }
            //Salvando a imagem
            eletro.setImageUrl(file.getOriginalFilename());
            fileStorageService.save(file);

            // salva o objeto no banco de dados com as informações pegas do html
            service.create(eletro);

            System.out.println("cadastrou");
        }

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("msg", "Cadastro realizado com sucesso");
        modelAndView.addObject("eletros", service.findAll());

        return modelAndView;
    }


    @GetMapping("/editPage/{id}")
    public ModelAndView editPage(@PathVariable String id) {
        // procura o eletro para ser editado
        Optional<Eletro> eletro = service.findById(id);

        // se o eletro não existir redireciona de volta para o index
        if(!eletro.isPresent()){
            return new ModelAndView("redirect:/admin");
        }

        // redireciona para a página de editar levando o eletro escolhido
        ModelAndView modelAndView = new ModelAndView("editPage");
        modelAndView.addObject("eletro", eletro.get());

        return modelAndView;
    }

    /*@PostMapping("/processEditPage")
    public String processEditPage(@ModelAttribute @Valid Eletro eletro){

        // pega o id do eletro que foi passado por parametro na url
        Optional<Eletro> eletro_b = service.findById(eletro.getId());

        // modifica com as novas informações pegas do html caso o eletro existir
        if(eletro_b.isPresent()){
            service.update(eletro);
        }
        return "redirect:/index";
    }*/

    @GetMapping("/processDelete/{id}")
    public String processDelete(@PathVariable String id){
        // "remove" o eletro do id passado pela url, trocando seu atributo isDeleted
        service.delete(id);

        return "redirect:/admin";
    }
}
