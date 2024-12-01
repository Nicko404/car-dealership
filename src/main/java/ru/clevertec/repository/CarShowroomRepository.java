package ru.clevertec.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.utils.ObjectsUtils;

import java.util.List;

@RequiredArgsConstructor
public class CarShowroomRepository implements Repository<CarShowroom> {

    private final SessionFactory sessionFactory;

    @Override
    public List<CarShowroom> findAll() {
        Session session = sessionFactory.openSession();
        List<CarShowroom> result = session.createQuery("from CarShowroom", CarShowroom.class).list();
        session.close();
        return result;
    }

    @Override
    public CarShowroom findById(Long id) {
        Session session = sessionFactory.openSession();
        CarShowroom carShowroom = session.get(CarShowroom.class, id);
        session.close();
        return carShowroom;
    }

    @Override
    public CarShowroom save(CarShowroom carShowroom) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(carShowroom);
        session.getTransaction().commit();
        session.close();
        return carShowroom;
    }

    @Override
    public CarShowroom update(Long id, CarShowroom carShowroom) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CarShowroom saved = session.get(CarShowroom.class, id);
        ObjectsUtils.copyNotNullProperties(carShowroom, saved);
        session.persist(saved);
        session.getTransaction().commit();
        session.close();
        return saved;
    }

    @Override
    public void delete(CarShowroom carShowroom) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(carShowroom);
        session.getTransaction().commit();
        session.close();
    }

    public void assignCarToShowroom(Car car, CarShowroom carShowroom) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(car);
        session.merge(carShowroom);
        session.getTransaction().commit();
        session.close();
    }
}
