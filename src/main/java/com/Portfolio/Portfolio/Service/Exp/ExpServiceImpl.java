package com.Portfolio.Portfolio.Service.Exp;

import com.Portfolio.Portfolio.Models.Exp;
import com.Portfolio.Portfolio.Repository.ExpRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpServiceImpl implements ExpService {

    @Autowired
    ExpRepository expRepository;

    @Override
    public List<Exp> getAllExp() {
        return (List<Exp>) expRepository.findAll();
    }

    @Override
    public Exp saveExp(Exp ex) {
        return expRepository.save(ex);
    }

    @Override
    public boolean deleteExp(Integer id) {
        try {
            expRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Exp findExp(Integer id) {
        return expRepository.findById(id).orElse(null);
    }
    
}
