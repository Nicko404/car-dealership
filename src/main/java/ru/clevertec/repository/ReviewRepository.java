package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Override
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "delete from Review where id = ?1")
    void deleteById(Long aLong);
}
