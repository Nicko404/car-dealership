package ru.clevertec.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.clevertec.entity.Review;
import ru.clevertec.utils.ObjectsUtils;

import java.util.List;

@RequiredArgsConstructor
public class ReviewRepository implements Repository<Review> {

    private final SessionFactory sessionFactory;

    @Override
    public List<Review> findAll() {
        Session session = sessionFactory.openSession();
        List<Review> result = session.createQuery("from Review", Review.class).list();
        session.close();
        return result;
    }

    @Override
    public Review findById(Long id) {
        Session session = sessionFactory.openSession();
        Review review = session.get(Review.class, id);
        session.close();
        return review;
    }

    @Override
    public Review save(Review review) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(review);
        session.getTransaction().commit();
        session.close();
        return review;
    }

    @Override
    public Review update(Long id, Review review) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Review saved = session.get(Review.class, id);
        ObjectsUtils.copyNotNullProperties(review, saved);
        session.persist(saved);
        session.getTransaction().commit();
        session.close();
        return saved;
    }

    @Override
    public void delete(Review review) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(review);
        session.getTransaction().commit();
        session.close();
    }
}
