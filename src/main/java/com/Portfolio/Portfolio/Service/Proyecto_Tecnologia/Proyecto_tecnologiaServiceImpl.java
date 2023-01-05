package com.Portfolio.Portfolio.Service.Proyecto_Tecnologia;

import com.Portfolio.Portfolio.Models.Proyecto_Tecnologia;
import com.Portfolio.Portfolio.Repository.Proyecto_TecnologiaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Proyecto_tecnologiaServiceImpl implements  Proyecto_TecnologiaService{

    @Autowired
    Proyecto_TecnologiaRepository proTecRepository;
    
    @Override
    public List<Proyecto_Tecnologia> getAll() {
        return (List<Proyecto_Tecnologia>) proTecRepository.findAll();
    }

    @Override
    public Proyecto_Tecnologia saveProTec(Proyecto_Tecnologia proTec) {
        return proTecRepository.save(proTec);
    }

    @Override
    public boolean deleteProTec(Integer id) {
        try {
            proTecRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
