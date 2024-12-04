package ru.clevertec;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.entity.Category;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.CarShowroomRepository;
import ru.clevertec.repository.CategoryRepository;
import ru.clevertec.repository.ClientRepository;
import ru.clevertec.repository.ReviewRepository;
import ru.clevertec.service.CarService;
import ru.clevertec.service.CarShowroomService;
import ru.clevertec.service.CategoryService;
import ru.clevertec.service.ClientService;
import ru.clevertec.service.ReviewService;
import ru.clevertec.service.Service;
import ru.clevertec.utils.HibernateSessionFactoryUtils;

import java.time.LocalDate;
import java.util.List;

public class Main {

    private final static SessionFactory sessionFactory;
    private final static Service<Client> clientService;
    private final static Service<Car> carService;
    private final static Service<CarShowroom> carShowroomService;
    private final static Service<Category> categoryService;
    private final static Service<Review> reviewService;

    static {
        sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
        CarRepository carRepository = new CarRepository(sessionFactory);

        clientService = new ClientService(new ClientRepository(sessionFactory), carRepository);
        carService = new CarService(carRepository);
        carShowroomService = new CarShowroomService(new CarShowroomRepository(sessionFactory));
        categoryService = new CategoryService(new CategoryRepository(sessionFactory));
        reviewService = new ReviewService(new ReviewRepository(sessionFactory));
    }

    public static void main(String[] args) {
//        buyCar();
//        addClient();
//        addCar();
//        assignCarToShowroom();
//        findCarByFilter();
//        addReview();
//        getAllPageable();
//        dropDatabase();
    }

    private static void getAllPageable() {
        List<Car> result = ((CarService) carService).getAll(0, 100);
        result.forEach(c -> System.out.println(c.getBrand()));
    }

    private static void addReview() {
        Client client = clientService.getById(1L);
        Car car = carService.getById(3L);
        ((ReviewService) reviewService).addReview(client, car, "This is some new review!!!", 10);
    }

    private static void assignCarToShowroom() {
        Car car = carService.getById(1L);
        CarShowroom carShowroom = carShowroomService.getById(3L);
        ((CarShowroomService) carShowroomService).assignCarToShowroom(car, carShowroom);
    }

    private static void findCarByFilter() {
        List<Car> result = ((CarService) carService).getCarsByFilter(null, null, 2000, 0, 0, true);
        result.forEach(c -> System.out.println(c.getPrice()));
    }

    private static void addCar() {
        Car car = Car.builder()
                .brand("Jaguar")
                .model("Fast")
                .carShowroom(carShowroomService.getById(1L))
                .category(categoryService.getById(1L))
                .manufactureDate(2000)
                .price(5500)
                .build();

        carService.save(car);
    }

    private static void addClient() {
        Client client = Client.builder()
                .name("Some new Name")
                .contacts(List.of("some@email.com"))
                .registrationDate(LocalDate.of(2024, 11, 29))
                .build();

        clientService.save(client);
    }

    private static void buyCar() {
        Car car = carService.getById(1L);
        Client client = clientService.getById(4L);

        ((ClientService) clientService).buyCar(client, car);
    }

    private static void dropDatabase() {
        SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        String sql = "DROP SCHEMA public CASCADE; CREATE SCHEMA public;";
        Query query = session.createNativeQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}