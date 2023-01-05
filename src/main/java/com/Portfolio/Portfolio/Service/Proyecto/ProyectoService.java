package com.Portfolio.Portfolio.Service.Proyecto;

import com.Portfolio.Portfolio.Models.Proyecto;
import java.util.List;

public interface ProyectoService {

    List<Proyecto> getAllProjec();

    Proyecto saveProject(Proyecto project);

    boolean deleteProject(Integer id);

    Proyecto findProject(Integer id);
}
