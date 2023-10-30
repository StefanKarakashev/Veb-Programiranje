package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.repository.BalloonRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BalloonServiceImpl implements BalloonService {
    private final BalloonRepository balloonRepo;


    public BalloonServiceImpl(BalloonRepository balloonRepo) {
        this.balloonRepo = balloonRepo;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepo.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepo.findAllByNameOrDescription(text);
    }
}
