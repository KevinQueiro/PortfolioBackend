package com.Portfolio.Portfolio.Service.Tecnologia;

import com.Portfolio.Portfolio.Models.Tecnologia;
import com.Portfolio.Portfolio.Repository.TecnologiaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnologiaServiceImpl implements TecnologiaService {

    @Autowired
    TecnologiaRepository tecnoRepository;

    @Override
    public List<Tecnologia> getAllTecno() {
        return (List<Tecnologia>) tecnoRepository.findAll();
    }

    @Override
    public Tecnologia saveTecno(Tecnologia tecno) {
        return tecnoRepository.save(tecno);
    }

    @Override
    public boolean deleteTecno(Integer id) {
        try {
            tecnoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Tecnologia findTecno(Integer id) {
        return tecnoRepository.findById(id).orElse(null);
    }

}
