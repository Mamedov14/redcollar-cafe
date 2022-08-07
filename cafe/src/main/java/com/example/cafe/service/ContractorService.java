package com.example.cafe.service;

import com.example.cafe.entity.Contractor;
import com.example.cafe.repository.ContractorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractorService {

    private final ContractorRepository contractorRepository;

    public List<Contractor> findAll() {
        return contractorRepository.findAll();
    }

    public Contractor save(String nameContractor, String address, String phoneNumber) {
        return contractorRepository.save(new Contractor(nameContractor, address, phoneNumber));
    }

    public List<Contractor> contractorList(Long id) {
        Optional<Contractor> contractor = contractorRepository.findById(id);
        ArrayList<Contractor> res = new ArrayList<>();
        contractor.ifPresent(res::add);
        return res;
    }

    public Contractor update(Long id, String nameContractor, String address, String phoneNumber) {
        Contractor contractor = contractorRepository.findById(id).orElseThrow();
        contractor.setName(nameContractor);
        contractor.setAddress(address);
        contractor.setPhoneNumber(phoneNumber);
        return contractorRepository.save(contractor);
    }

    public void delete(Long id) {
        contractorRepository.deleteById(id);
    }
}
