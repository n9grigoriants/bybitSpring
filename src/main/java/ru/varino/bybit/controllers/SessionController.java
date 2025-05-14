package ru.varino.bybit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.varino.bybit.entities.SessionEntity;
import ru.varino.bybit.services.SessionService;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionEntity createSession(@RequestBody SessionEntity session) {
        sessionService.save(session);
        return session;
    }

    @GetMapping("/{id}")
    public SessionEntity getSession(@PathVariable Long id) {
        return sessionService.get(id);
    }

    @GetMapping
    public List<SessionEntity> getAllSessions() {
        return sessionService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSession(@PathVariable Long id) {
        sessionService.remove(id);
    }
}