package interfaces;
import dto.Account;
import dto.Client;

import java.util.List;

public interface ClientI {
    Client add(Client client);
    Client searchByCode(String code);
    boolean delete(String id);
    List<Client> showList();
    Client update(Client client);
}
