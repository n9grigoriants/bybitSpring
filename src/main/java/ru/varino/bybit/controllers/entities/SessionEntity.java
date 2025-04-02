package ru.varino.bybit.controllers.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "session")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private BuyerEntity buyer;

    @Lob // Для хранения бинарных данных (LargeBinary в SQLAlchemy)
    private byte[] session;
}