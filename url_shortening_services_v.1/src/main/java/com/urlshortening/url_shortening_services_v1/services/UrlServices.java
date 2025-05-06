package com.urlshortening.url_shortening_services_v1.services;

import com.urlshortening.url_shortening_services_v1.data.enity.UrlEntity;
import com.urlshortening.url_shortening_services_v1.data.repositorie.UrlShortRepositorie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlServices {

    @Value("${app.url}")
    private String baseUrl;

    private final UrlShortRepositorie repositorie;

    public UrlServices(UrlShortRepositorie repositorie) {
        this.repositorie = repositorie;
    }

    public String getUrlOriginal(String urlCorta){
        Optional<UrlEntity> url = repositorie.findByUrlCorta(urlCorta);
        return url.map(UrlEntity::getUrlOriginal).orElse(null);
    }

    private String normalizeUrl(String url) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        return url.trim(); // puedes también usar .toLowerCase() si quieres ignorar mayúsculas
    }

    public String urlShort(String urlOriginal){

        urlOriginal = normalizeUrl(urlOriginal);

        Optional<UrlEntity> existUrl = repositorie.findByUrlOriginal(urlOriginal);

        if (existUrl.isPresent()){
            return baseUrl + "/" + existUrl.get().getUrlCorta();
        }

        String urlEncdole = UUID.randomUUID().toString().substring(0, 8);

        UrlEntity url = new UrlEntity();
        url.setUrlCorta(urlEncdole);
        url.setUrlOriginal(urlOriginal);
        repositorie.save(url);

        return baseUrl + "/" + urlEncdole;
    }
}
