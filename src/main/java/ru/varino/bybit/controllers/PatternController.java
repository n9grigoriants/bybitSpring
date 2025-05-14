package ru.varino.bybit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.varino.bybit.entities.PatternEntity;
import ru.varino.bybit.services.PatternService;

import java.util.List;

@RestController
@RequestMapping("/api/patterns")
public class PatternController {

    private final PatternService patternService;

    @Autowired
    public PatternController(PatternService patternService) {
        this.patternService = patternService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatternEntity createPattern(@RequestBody PatternEntity pattern) {
        patternService.save(pattern);
        return pattern;
    }

    @GetMapping("/{id}")
    public PatternEntity getPattern(@PathVariable Long id) {
        return patternService.get(id);
    }

    @GetMapping
    public List<PatternEntity> getAllPatterns() {
        return patternService.getAll();
    }

    @PutMapping("/{id}")
    public PatternEntity updatePattern(@PathVariable Long id, @RequestBody PatternEntity pattern) {
        pattern.setId(id);
        patternService.save(pattern);
        return pattern;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePattern(@PathVariable Long id) {
        patternService.remove(id);
    }
}
