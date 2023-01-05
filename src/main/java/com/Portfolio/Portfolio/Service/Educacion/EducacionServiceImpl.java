package com.Portfolio.Portfolio.Service.Educacion;

import com.Portfolio.Portfolio.Models.Educacion;
import com.Portfolio.Portfolio.Repository.EducacionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducacionServiceImpl implements EducacionService {

    @Autowired
    EducacionRepository eduRepository;

    @Override
    public List<Educacion> getAllEdu() {
        return (List<Educacion>) eduRepository.findAll();
    }

    @Override
    public Educacion saveEdu(Educacion edu) {
        return eduRepository.save(edu);
    }

    @Override
    public boolean deleteEdu(Integer id) {
        try {
            eduRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Educacion findEdu(Integer id) {
        return eduRepository.findById(id).orElse(null);
    }

}
