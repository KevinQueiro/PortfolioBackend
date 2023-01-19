package com.Portfolio.Portfolio.Service.Proyecto;

import com.Portfolio.Portfolio.Models.Proyecto;
import com.Portfolio.Portfolio.Repository.ProyectoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    ProyectoRepository proRepository;

    @Override
    public List<Proyecto> getAllProjec() {
        return (List<Proyecto>) proRepository.findAll();
    }

    @Override
    public Proyecto saveProject(Proyecto project) {
        return proRepository.save(project);
    }

    @Override
    public boolean deleteProject(Integer id) {
        try {
            proRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Proyecto findProject(Integer id) {
        return proRepository.findById(id).orElse(null);
    }

    @Override
    public Proyecto findPro(Integer id) {
        return proRepository.findById(id).orElse(null);
    }

}
