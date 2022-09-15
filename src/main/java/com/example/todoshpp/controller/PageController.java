package com.example.todoshpp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @Operation(summary = "home page")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All good done")})
    @GetMapping("/")
    public String home() {
        return "home";
    }
}