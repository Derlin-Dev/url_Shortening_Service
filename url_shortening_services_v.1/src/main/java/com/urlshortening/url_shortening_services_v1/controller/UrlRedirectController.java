package com.urlshortening.url_shortening_services_v1.controller;

import com.urlshortening.url_shortening_services_v1.services.UrlServices;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UrlRedirectController {

    private final UrlServices services;

    public UrlRedirectController(UrlServices services) {
        this.services = services;
    }

    @GetMapping("/{urlCorta}")
    public ResponseEntity<Void> urlRedirec(@PathVariable String urlCorta){
        String urlOriginar = services.getUrlOriginal(urlCorta);

        if (urlOriginar != null){
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(urlOriginar));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
