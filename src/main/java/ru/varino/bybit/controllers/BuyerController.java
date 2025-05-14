package ru.varino.bybit.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.varino.bybit.entities.BuyerEntity;
import ru.varino.bybit.services.BuyerService;

import java.util.List;

@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    private final BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BuyerEntity createBuyer(@RequestBody BuyerEntity buyer) {
        buyerService.save(buyer);
        return buyer;
    }

    @GetMapping("/{id}")
    public BuyerEntity getBuyer(@PathVariable Long id) {
        return buyerService.get(id);
    }

    @GetMapping
    public List<BuyerEntity> getAllBuyers() {
        return buyerService.getAll();
    }

    @PutMapping("/{id}")
    public BuyerEntity updateBuyer(@PathVariable Long id, @RequestBody BuyerEntity buyer) {
        buyer.setId(id);
        buyerService.save(buyer);
        return buyer;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBuyer(@PathVariable Long id) {
        buyerService.remove(id);
    }
}
