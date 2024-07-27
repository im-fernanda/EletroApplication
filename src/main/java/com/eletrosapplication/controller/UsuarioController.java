package com.eletrosapplication.controller;

import com.eletrosapplication.domain.Usuario;
import com.eletrosapplication.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @GetMapping("/cadastroUsuario")
    public String getCadastroUsuarioPage(Model model) {
        model.addAttribute("user", new Usuario());
        System.out.println("entrou aqui");
        return "cadastroUsuario";
    }

    @PostMapping("/processCadastroUsuario")
    public String cadastroUsuario(@ModelAttribute @Valid Usuario user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        if(user.getIsAdmin() == null){
            user.setIsAdmin(false);
        }

        repository.save(user);
        System.out.println("cadastrou o usuario: " + user.getUsername() + user.getPassword() + user.isEnabled());
        return "redirect:/login";
    }
}
