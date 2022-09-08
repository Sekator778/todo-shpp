package com.example.todoshpp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @Operation(summary = "international get request")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All good done")})
    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }

    @Operation(summary = "international get request on address home")
    @GetMapping("/")
    public String home() {
        return "home";
    }
}