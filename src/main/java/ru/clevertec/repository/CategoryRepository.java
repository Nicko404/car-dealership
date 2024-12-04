package ru.clevertec.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.clevertec.entity.Category;
import ru.clevertec.utils.ObjectsUtils;

import java.util.List;

@RequiredArgsConstructor
public class CategoryRepository implements Repository<Category> {

    private final SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.openSession();
        List<Category> result = session.createQuery("from Category", Category.class).list();
        session.close();
        return result;
    }

    @Override
    public Category findById(Long id) {
        Session session = sessionFactory.openSession();
        Category category = session.get(Category.class, id);
        session.close();
        return category;
    }

    @Override
    public Category save(Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(category);
        session.getTransaction().commit();
        session.close();
        return category;
    }

    @Override
    public Category update(Long id, Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Category saved = session.get(Category.class, id);
        ObjectsUtils.copyNotNullProperties(category, saved);
        session.persist(saved);
        session.getTransaction().commit();
        session.close();
        return saved;
    }

    @Override
    public void delete(Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(category);
        session.getTransaction().commit();
        session.close();
    }
}
