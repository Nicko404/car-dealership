package ru.clevertec.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.entity.Category;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;

public class HibernateSessionFactoryUtils {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtils() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Car.class);
                configuration.addAnnotatedClass(CarShowroom.class);
                configuration.addAnnotatedClass(Category.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Review.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());

                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Exception during build session factory: " + e.getMessage());
            }
        }

        return sessionFactory;
    }
}
