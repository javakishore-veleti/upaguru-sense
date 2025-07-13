package com.upaguru.sense.controller;

import com.upaguru.sense.entity.PromptSessionLog;
import com.upaguru.sense.service.PromptSessionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prompt-sessions")
@RequiredArgsConstructor
public class PromptSessionLogController {

    private final PromptSessionLogService promptSessionLogService;

    @PostMapping
    public ResponseEntity<PromptSessionLog> save(@RequestBody PromptSessionLog prompt) {
        PromptSessionLog saved = promptSessionLogService.save(prompt);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromptSessionLog> getById(@PathVariable String id) {
        Optional<PromptSessionLog> response = promptSessionLogService.findById(id);
        if(response.isPresent()) {
            return ResponseEntity.ok(response.get());
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public List<PromptSessionLog> getAll() {
        return promptSessionLogService.findAll();
    }
}
