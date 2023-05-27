package Model.Dao;

import Model.Entites.Client;

import java.util.List;

public interface ClientDao {
    void insert(Client obj);
    void update(Client obj);
    Void finByName(Client obj);
    void deleteById(Integer id);
    Client findById(Integer id);
    List<Client> findAll();
}
