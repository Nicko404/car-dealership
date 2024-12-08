package ru.clevertec.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "cars")
@NamedEntityGraph(
        name = "car_entity_graph",
        attributeNodes = {
                @NamedAttributeNode("category"),
                @NamedAttributeNode("carShowroom"),
                @NamedAttributeNode("reviews")
        }
)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String model;

    @Column(nullable = false, length = 50)
    private String brand;

    @Column(name = "manufacture_date", nullable = false)
    private Integer manufactureDate;

    private Integer price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "car_showroom_id")
    private CarShowroom carShowroom;

    @Builder.Default
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

}
