package com.pre_38.pre_project.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiTestController {

    @PostMapping("/resource")
    public ResponseEntity testUser(@RequestBody String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        return ResponseEntity.ok(map);
    }
}
