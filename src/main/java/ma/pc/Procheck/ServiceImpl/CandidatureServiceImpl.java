package ma.pc.Procheck.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.pc.Procheck.Entity.Candidature;
import ma.pc.Procheck.Repository.CandidatureRepository;
import ma.pc.Procheck.Service.CandidatureService;

@Service
public class CandidatureServiceImpl implements CandidatureService{

	@Autowired
	private CandidatureRepository candidatureRepository;
	
	@Override
	public List<Candidature> getAllCandidatures() {
		return candidatureRepository.findAll();
	}

	@Override
	public Optional<Candidature> getCandidatureById(Long id) {
		return candidatureRepository.findById(id);
	}

	@Override
	public Candidature saveCandidature(Candidature candidature) {
		return candidatureRepository.save(candidature);
	}

}
