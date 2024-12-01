package ru.clevertec.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.clevertec.entity.Client;
import ru.clevertec.utils.ObjectsUtils;

import java.util.List;

@RequiredArgsConstructor
public class ClientRepository implements Repository<Client> {

    private final SessionFactory sessionFactory;

    @Override
    public List<Client> findAll() {
        Session session = sessionFactory.openSession();
        List<Client> result = session.createQuery("from Client", Client.class).list();
        session.close();
        return result;
    }

    @Override
    public Client findById(Long id) {
        Session session = sessionFactory.openSession();
        Client client = session.get(Client.class, id);
        session.close();
        return client;
    }

    @Override
    public Client save(Client client) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(client);
        session.getTransaction().commit();
        session.close();
        return client;
    }

    @Override
    public Client update(Long id, Client client) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Client saved = session.get(Client.class, id);
        ObjectsUtils.copyNotNullProperties(client, saved);
        session.persist(saved);
        session.getTransaction().commit();
        session.close();
        return saved;
    }

    @Override
    public void delete(Client client) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(client);
        session.getTransaction().commit();
        session.close();
    }
}
