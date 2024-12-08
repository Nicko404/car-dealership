package ru.clevertec.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "categories")
@NamedEntityGraph(
        name = "category_entity_graph",
        attributeNodes = {
                @NamedAttributeNode(value = "cars", subgraph = "cars_subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "cars_subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("reviews")
                        }
                )
        }
)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Car> cars = new ArrayList<>();
}
