package com.example.cafe.service;

import com.example.cafe.entity.Coworker;
import com.example.cafe.entity.Position;
import com.example.cafe.repository.CoworkerRepository;
import com.example.cafe.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoworkerService {

    private final CoworkerRepository coworkerRepository;

    private final PositionRepository positionRepository;

    public List<Position> getPositionList() {
        return positionRepository.findAll();
    }


    public List<Coworker> findAll() {
        return coworkerRepository.findAll();
    }

    public Coworker saveCoworker(String fio, String title) {
        Position position = positionRepository.findByTitle(title);
        return coworkerRepository.save(new Coworker(fio, position));
    }

    public Coworker updateCoworker(Long id, String fio, String title) {
        Coworker coworker = coworkerRepository.findById(id).orElseThrow();
        Position position = positionRepository.findByTitle(title);
        coworker.setFio(fio);
        coworker.setPosition(position);
        return coworkerRepository.save(coworker);
    }

    public List<Coworker> coworkerList(long id) {
        Optional<Coworker> coworker = coworkerRepository.findById(id);
        ArrayList<Coworker> res = new ArrayList<>();
        coworker.ifPresent(res::add);
        return res;
    }

    public void deleteCoworker(Long id) {
        coworkerRepository.deleteById(id);
    }
}
