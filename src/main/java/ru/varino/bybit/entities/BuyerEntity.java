package ru.varino.bybit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "buyers")
@AllArgsConstructor
public class BuyerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(name = "api_key", length = 100)
    private String apiKey;

    @Column(name = "api_secret", length = 100)
    private String apiSecret;

    @Column(name = "is_active")
    private Boolean isActive;

    public BuyerEntity() {}
}