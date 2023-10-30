package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class BalloonRepository {
    public List<Balloon> balloons;

    @PostConstruct
    private void init() {
        balloons = new ArrayList<>();

        for (int i = 1; i <=10 ; i++)
            balloons.add(new Balloon("baloon" + i, "description" + i));
    }

    public List<Balloon> findAllBalloons() {
        return balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return balloons.stream()
                .filter(b -> b.getName().contains(text) || b.getDescription().contains(text))
                .collect(Collectors.toList());
    }

}
