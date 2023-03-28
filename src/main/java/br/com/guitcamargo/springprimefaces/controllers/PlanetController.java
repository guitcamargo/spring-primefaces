package br.com.guitcamargo.springprimefaces.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    @GetMapping()
    public String hello() {
        return "first commit";
    }
}
