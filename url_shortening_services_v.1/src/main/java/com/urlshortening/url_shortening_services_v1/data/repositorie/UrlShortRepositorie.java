package com.urlshortening.url_shortening_services_v1.data.repositorie;

import com.urlshortening.url_shortening_services_v1.data.enity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlShortRepositorie extends JpaRepository<UrlEntity, Long> {

    Optional<UrlEntity> findByUrlCorta(String urlCorta);
    Optional<UrlEntity> findByUrlOriginal(String urlOriginal);
}
