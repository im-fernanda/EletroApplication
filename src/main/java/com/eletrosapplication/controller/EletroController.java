package com.eletrosapplication.controller;

import com.eletrosapplication.domain.Eletro;
import com.eletrosapplication.service.EletroService;
import com.eletrosapplication.service.FileStorageService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class EletroController {

    private final EletroService service;
    private final FileStorageService fileStorageService;

    public EletroController(EletroService service, FileStorageService fileStorageService) {
        this.service = service;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/admin")
    public String getAdminPahge(Model model, HttpSession session) {
        // Pegando a lista de itens cadastrados, que não estão deletados
        List<Eletro> eletros = service.findAll();

        // Adicionando a lista a um model para passar para o HTML
        model.addAttribute("eletros", eletros);

        List<String> categorias = new ArrayList<>();

        for (Eletro eletro : eletros) {
            if(!categorias.contains(eletro.getCategoria())){
                categorias.add(eletro.getCategoria());
            }
        }

        model.addAttribute("categorias", categorias);

        return "admin";
    }

    @GetMapping("/index")
    public String getUserPage(Model model, HttpSession session) {
        // Pegando a lista de itens cadastrados, que não estão deletados
        List<Eletro> eletros = service.findAll();

        // Adicionando a lista a um model para passar para o HTML
        model.addAttribute("eletros", eletros);

        List<Eletro> carrinho = (List<Eletro>) session.getAttribute("carrinho");
        int quantidade = (carrinho != null) ? carrinho.size() : 0;

        System.out.println("Quantidade de itens no carrinho: " + quantidade);
        model.addAttribute("quantidade", quantidade);

        List<String> categorias = new ArrayList<>();

        for (Eletro eletro : eletros) {
            if(!categorias.contains(eletro.getCategoria())){
                categorias.add(eletro.getCategoria());
            }
        }

        model.addAttribute("categorias", categorias);

        return "index";
    }

    @GetMapping("/categorias")
    public String getCategorias(Model model) {
        List<Eletro> eletros = service.findAll();

        return "index";
    }

    @GetMapping("/cadastroPage")
    public String getCadastroPage(Model model) {
        // Criando um objeto Eletro e usando o model para enviar para o HTML
        Eletro eletro = new Eletro();
        model.addAttribute("eletro", eletro);
        return "cadastroPage";
    }

    @PostMapping("/processSave/{editar_ou_cadastrar}")
    public ModelAndView processSave(
            @ModelAttribute @Valid Eletro eletro, BindingResult result,
            @RequestParam("imagem") MultipartFile file, Errors errors,
            @PathVariable String editar_ou_cadastrar) {

        // Verifica se há erros após o upload do arquivo
        if (result.hasErrors()) {
            System.out.println("Erros de validação: " + result.getAllErrors());
            return new ModelAndView("cadastroPage").addObject("eletro", eletro);
        }

        if (Objects.equals(editar_ou_cadastrar, "edit")) {
            // Pega o id do eletro que foi passado por parâmetro na URL
            Optional<Eletro> eletro_b = service.findById(eletro.getId());

            // Modifica com as novas informações pegas do HTML caso o eletro exista
            if (eletro_b.isPresent()) {
                service.update(eletro);
                System.out.println("Editou");
            }
        }

        if (Objects.equals(editar_ou_cadastrar, "cad")) {
            // Gera um UUID para o nome da imagem
            String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            // Salvando a imagem com um nome único
            eletro.setImageUrl(uniqueFilename);
            fileStorageService.save(file, uniqueFilename);

            // Salva o objeto no banco de dados com as informações pegas do HTML
            service.create(eletro);
            System.out.println("Cadastrou");
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/admin");
        modelAndView.addObject("msg", "Cadastro realizado com sucesso");
        modelAndView.addObject("eletros", service.findAll());
        return modelAndView;
        //return "redirect:/admin?msg=Cadastro realizado com sucesso";
    }

    @GetMapping("/editPage/{id}")
    public ModelAndView editPage(@PathVariable String id) {
        // Procura o eletro para ser editado
        Optional<Eletro> eletro = service.findById(id);

        // Se o eletro não existir, redireciona de volta para o index
        if (!eletro.isPresent()) {
            return new ModelAndView("redirect:/admin");
        }

        // Redireciona para a página de editar levando o eletro escolhido
        ModelAndView modelAndView = new ModelAndView("editPage");
        modelAndView.addObject("eletro", eletro.get());

        return modelAndView;
    }


    @GetMapping("/processDelete/{id}")
    public String processDelete(@PathVariable String id) {
        // "Remove" o eletro do id passado pela URL, trocando seu atributo isDeleted
        service.delete(id);

        return "redirect:/admin?msg=Remoção realizada com sucesso";
    }

    @GetMapping("/getDisplayCategoria/{categoria}")
    public String getDisplayCategoria(@PathVariable String categoria, Model model) {
        List<Eletro> eletros = service.findAll();
        List<Eletro> eletro_categoria = new ArrayList<>();

        for (Eletro eletro : eletros) {
            if(eletro.getCategoria().equals(categoria)){
                eletro_categoria.add(eletro);
            }
        }

        model.addAttribute("eletros_categoria", eletro_categoria);

        return "categoria";
    }

    @GetMapping("/produtoDetails/{id}")
    public String getProdutoDetails(@PathVariable("id") String id, Model model) {
        Optional<Eletro> eletroOptional = service.findById(id);
        if (eletroOptional.isPresent()) {
            Eletro eletro= eletroOptional.get();
            model.addAttribute("eletro", eletro);
            return "produtoDetails";
        } else {

            return "error/404";
        }
    }
}
