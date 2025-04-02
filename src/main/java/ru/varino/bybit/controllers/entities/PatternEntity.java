package ru.varino.bybit.controllers.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pattern")
public class PatternEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column()
    private String title;

    @Column(length = 100)
    private String coin;

    @Column(length = 100)
    private String should;

    @Column(length = 100)
    private String side;

    @Column(length = 100)
    private String tp;

    @Column(length = 100)
    private String sl;

    @Column(name = "limit_price", length = 100, nullable = true)
    private String limitPrice;
}