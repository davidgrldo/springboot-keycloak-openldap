package com.davidgrldo.simpleapi.controller;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidgrldo.simpleapi.config.SwaggerConfig;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api")
public class SimpleApiController {

    /**
     * The getPublic() method is mapped to the /public URL endpoint. This method does not require
     * any authentication or authorization, so anyone can access it.
     * 
     * @return "Hello world, this endpoint is public"
     */
    @Operation(summary = "Get string from public endpoint")
    @GetMapping("/public")
    public String getPublic() {
        return "Hello world, this endpoint is public";
    }

    /**
     * The getSecured() method is mapped to the /secured URL endpoint. This method takes a
     * JwtAuthenticationToken object as a parameter, which Spring will automatically inject into the
     * method when it's called. The response string is enriched with the value retrieved from the
     * getName() method of the JwtAuthenticationToken object. This method requires authentication,
     * so only users who have been authenticated can access it.
     * 
     * @param token
     * @return "Hello [name], this endpoint is secured"
     */
    @Operation(
            summary = "Get string from private/secured endpoint",
            security = {@SecurityRequirement(name = SwaggerConfig.BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("/secured")
    public String getSecured(JwtAuthenticationToken token) {
        return "Hello %s, this endpoint is secured".formatted(token.getName());
    }
}