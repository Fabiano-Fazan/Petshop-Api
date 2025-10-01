package com.petshop.api.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "street")),
        @AttributeOverride(name = "city", column = @Column(name = "city")),
        @AttributeOverride(name = "state", column = @Column(name = "state")),
        @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code")),
        @AttributeOverride(name = "complement", column = @Column(name = "complement"))
    })
    private Address address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Animal> animals;


}
