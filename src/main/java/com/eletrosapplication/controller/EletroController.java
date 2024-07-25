package com.eletrosapplication.controller;

import com.eletrosapplication.service.EletroService;
import com.eletrosapplication.service.FileStorageService;
import org.springframework.stereotype.Controller;

@Controller
public class EletroController {

    private final EletroService service;
    private final FileStorageService fileStorageService;

    public EletroController(EletroService service, FileStorageService fileStorageService) {
        this.service = service;
        this.fileStorageService = fileStorageService;
    }
}
