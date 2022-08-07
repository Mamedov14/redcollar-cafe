package com.example.cafe.service;

import com.example.cafe.entity.Position;
import com.example.cafe.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;

    public Position savePosition(String namePosition) {
        return save(new Position(namePosition));
    }

    public Position updatePosition(Long id, String title) {
        Position position = findById(id);
        position.setTitle(title);
        return save(position);
    }

    public List<Position> positionList(long id) {
        Optional<Position> pos = positionRepository.findById(id);
        ArrayList<Position> res = new ArrayList<>();
        pos.ifPresent(res::add);
        return res;
    }



    public Position findById(Long id) {
        return positionRepository.getOne(id);
    }

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public Position save(Position position) {
        return positionRepository.save(position);
    }

    public void delete(Long id) {
        positionRepository.deleteById(id);
    }
}
