package ru.clevertec.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.RootGraph;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Category;
import ru.clevertec.utils.ObjectsUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class CarRepository implements Repository<Car> {

    private final SessionFactory sessionFactory;

    @Override
    public List<Car> findAll() {
        Session session = sessionFactory.openSession();
        RootGraph<?> entityGraph = session.getEntityGraph("car_entity_graph");
        List<Car> result = session.createQuery("from Car", Car.class)
                .setHint("jakarta.persistence.fetchgraph", entityGraph)
                .list();
        session.close();
        return result;
    }

    public List<Car> findAll(int pageCount, int pageSize) {
        Session session = sessionFactory.openSession();
        Query<Car> query = session.createQuery("from Car", Car.class);
        query.setFirstResult(pageCount == 0 ? 0 : pageCount * pageSize);
        query.setMaxResults(pageCount * pageSize + pageSize);
        List<Car> result = query.list();
        session.close();
        return result;
    }

    @Override
    public Car findById(Long id) {
        Session session = sessionFactory.openSession();
        RootGraph<?> entityGraph = session.getEntityGraph("car_entity_graph");
        Map<String, Object> properties = new HashMap<>();
        properties.put("jakarta.persistence.fetchgraph", entityGraph);
        Car car = session.find(Car.class, id, properties);
        session.close();
        return car;
    }

    public List<Car> findByFilter(String brand, String category, int year, int minPrice, int maxPrice, boolean ascOrderByPrice) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<Car> query = cb.createQuery(Car.class);
        JpaRoot<Car> root = query.from(Car.class);

        if (brand != null) query.where(cb.equal(root.get("brand"), brand));
        if (year > 0) query.where(cb.equal(root.get("manufactureDate"), year));
        if (maxPrice > 0) query.where(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        if (minPrice > 0) query.where(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        if (category != null) {
            query.where(cb.equal(root.get("category"),
                    session.createQuery("from Category c where c.name like :name", Category.class)
                            .setParameter("name", category).uniqueResult()));
        }
        if (ascOrderByPrice) query.orderBy(cb.asc(root.get("price")));
        else query.orderBy(cb.desc(root.get("price")));

        List<Car> result = session.createQuery(query).getResultList();
        session.close();
        return result;
    }

    @Override
    public Car save(Car car) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(car);
        session.getTransaction().commit();
        session.close();
        return car;
    }

    @Override
    public Car update(Long id, Car car) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Car saved = session.get(Car.class, id);
        ObjectsUtils.copyNotNullProperties(car, saved);
        session.persist(saved);
        session.getTransaction().commit();
        session.close();
        return saved;
    }

    @Override
    public void delete(Car car) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(car);
        session.getTransaction().commit();
        session.close();
    }
}
