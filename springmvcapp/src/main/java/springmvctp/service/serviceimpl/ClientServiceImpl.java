/*package springmvctp.service.serviceimpl;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import springmvctp.dao.entities.Client;
	import springmvctp.dao.repositories.ClientRepository;
import springmvctp.service.Iservice.clientntService;

import java.util.List;
	import java.util.Optional;

	@Service
	public class ClientServiceImpl implements clientntService {

	    @Autowired
	    private ClientRepository clientRepository;

	    @Override
	    public void addClient(Client client) {
	        clientRepository.save(client);
	    }

	    @Override
	    public void deleteClient(Integer id) {
	        clientRepository.deleteById(id);
	    }

	    @Override
	    public Client getClient(Integer id) {
	        Optional<Client> clientOptional = clientRepository.findById(id);
	        return clientOptional.orElse(null);
	    }

	    @Override
	    public void updateClient(Client client) {
	        clientRepository.save(client);
	    }

	    @Override
	    public List<Client> getAllClients() {
	        return clientRepository.findAll();
	    }
	    @Override
	    public Client getClientByEmail(String email) {
	        return clientRepository.findByEmail(email);
	    }
	}*/
