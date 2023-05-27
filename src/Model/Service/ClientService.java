package Model.Service;

import Model.Dao.ClientDao;
import Model.Dao.DaoFactory;
import Model.Entites.Client;

import java.util.List;

public class ClientService {
    private ClientDao dao= DaoFactory.createClientDao();

    public List<Client> findAll(){

        return dao.findAll();
    }
}
