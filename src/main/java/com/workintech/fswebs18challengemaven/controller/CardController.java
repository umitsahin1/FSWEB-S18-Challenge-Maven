package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.repository.CardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CardController {

   private final CardRepository cardRepository;

   @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("/cards")
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @GetMapping("/cards/byColor/{color}")
    public List<Card> getCardsByColor(@PathVariable String color) {
        return cardRepository.findByColor(color);
    }

    @GetMapping("/cards/byValue/{value}")
    public List<Card> getCardsByValue(@PathVariable Integer value) {
        return cardRepository.findByValue(value);
    }

    @GetMapping("/cards/byType/{type}")
    public List<Card> getCardsByType(@PathVariable String type) {
        return cardRepository.findByType(type);
    }


    @PostMapping("/cards")
    public Card createCard(@RequestBody Card card) {

        return cardRepository.save(card);
    }

    @PutMapping("/cards/")
    public Card updateCard( @RequestBody Card card) {
        return cardRepository.update(card);
    }

    @DeleteMapping("/cards/{id}")
    public Card deleteCard(@PathVariable Long id) {
        return cardRepository.remove(id);
    }


}
