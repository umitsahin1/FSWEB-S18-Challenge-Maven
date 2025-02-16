package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepositoryImpl implements CardRepository {
    private final EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c", Card.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Card update(Card card) {
        return entityManager.merge(card);
    }

    @Transactional
    @Override
    public Card remove(Long id) {
        Card card = entityManager.find(Card.class, id);
        if (card != null) {
            entityManager.remove(card);
            return card;
        } else {
            throw new CardException("Card with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Card> findByColor(String color) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.color= :color", Card.class);
        query.setParameter("color", color);
        List<Card> cards = query.getResultList();
        if (cards.isEmpty()) {
            throw new CardException("No cards found with color: " + color, HttpStatus.NOT_FOUND);
        }
        return cards;
    }

    @Override
    public List<Card> findByValue(Integer value) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.value= :value", Card.class);
        query.setParameter("value", value);
        return query.getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.type= :type", Card.class);
        query.setParameter("type", type);
        return query.getResultList();
    }
}