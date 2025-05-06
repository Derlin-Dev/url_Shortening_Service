package com.urlshortening.url_shortening_services_v1.controller;

import com.urlshortening.url_shortening_services_v1.model.UrlRequest;
import com.urlshortening.url_shortening_services_v1.services.UrlServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UrlShortController {

    private final UrlServices services;

    public UrlShortController(UrlServices services) {
        this.services = services;
    }

    @GetMapping("/url/shorten")
    public ResponseEntity<String> urlShort(@RequestBody UrlRequest urlRequest){
       try {
           String url = urlRequest.getUrl();
           String urlShort = services.urlShort(url);
           return ResponseEntity.status(HttpStatus.CREATED).body(urlShort);

       }catch (IllegalArgumentException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("URL inválida");
       } catch (RuntimeException e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
       }
    }

}
