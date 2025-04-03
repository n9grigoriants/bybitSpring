package ru.varino.bybit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "session")
@AllArgsConstructor
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private BuyerEntity buyer;

    @Lob // Для хранения бинарных данных (LargeBinary в SQLAlchemy)
    private byte[] session;

    public SessionEntity() {}
}