package ru.varino.bybit.controllers.entities;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tg_id")
    private Long tgId;

    @Column(length = 200)
    private String username;
}