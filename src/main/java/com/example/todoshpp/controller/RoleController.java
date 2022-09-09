package com.example.todoshpp.controller;

import com.example.todoshpp.model.RoleEntity;
import com.example.todoshpp.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class RoleController {
    private final Logger log = LoggerFactory.getLogger(RoleController.class);
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @Operation(summary = "controller for work with role")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All good done")})
    @GetMapping("/role")
    public ResponseEntity<Iterable<RoleEntity>> getAllRole() {
        log.info("used get mapping to /role");
        return ResponseEntity.ok(service.findAll());
    }
//
//    @Operation(summary = "international get request on address home")
//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }
}