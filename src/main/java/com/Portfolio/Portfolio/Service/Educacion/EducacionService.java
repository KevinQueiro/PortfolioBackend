package com.Portfolio.Portfolio.Service.Educacion;

import com.Portfolio.Portfolio.Models.Educacion;
import java.util.List;

public interface EducacionService {

    List<Educacion> getAllEdu();

    Educacion saveEdu(Educacion edu);

    boolean deleteEdu(Integer id);

    Educacion findEdu(Integer id);
}