package ru.clevertec.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 75)
    private String name;

    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "client_contacts", joinColumns = {@JoinColumn(name = "client_id")})
    @Column(name = "contact")
    private List<String> contacts = new ArrayList<>();

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Car> cars = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "client")
    @Fetch(FetchMode.JOIN)
    private List<Review> reviews = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setClient(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setClient(null);
    }
}
