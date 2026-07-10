package db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clie_id")
    private Integer id;
    @Column(name = "clie_cedula")
    private String cedula;
    @Column(name = "clie_email")
    private String email;

}
