package com.project.app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.app.model.User;

@FeignClient(name = "Service-Utilisateur", url = "http://localhost:8081") 
public interface UserClient {

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") Long id);  // Assurer que le paramètre utilise l'annotation @PathVariable
}
