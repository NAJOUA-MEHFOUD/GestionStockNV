package springmvctp.service.serviceimpl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import springmvctp.dao.entities.Fournisseur;
import springmvctp.dao.repositories.IFournisseurRepos;
import springmvctp.service.Iservice.IServiceFournisseur;

@Service
@Transactional
@AllArgsConstructor
public class ServiceFournisseurImpl implements IServiceFournisseur {

	private final IFournisseurRepos IfournisseurRepos;

	@Override
	public void ajouterFournisseur(Fournisseur fournisseur) {
		IfournisseurRepos.save(fournisseur);
	}

	@Override
	public void supprimerFournisseur(Integer id) {
		Optional<Fournisseur> fournisseur = IfournisseurRepos.findById(id);
		if (fournisseur.isEmpty()) {
			throw new RuntimeException("Fournisseur not found");
		} else {
			IfournisseurRepos.deleteById(id);
		}
	}

	@Override
	public Fournisseur rechercherFournisseur(Integer id) {
		Optional<Fournisseur> fournisseur = IfournisseurRepos.findById(id);
		if (fournisseur.isEmpty()) {
			throw new RuntimeException("Fournisseur not found");
		} else {
			return fournisseur.get();
		}
	}

	@Override
	public void modifierFournisseur(Fournisseur fournisseur) {
		IfournisseurRepos.save(fournisseur);
	}

	@Override
	public List<Fournisseur> listerFournisseurs() {
		return IfournisseurRepos.findAll();
	}
}
