package com.Portfolio.Portfolio.Service.Proyecto_Tecnologia;

import com.Portfolio.Portfolio.Models.Proyecto_Tecnologia;
import java.util.List;

public interface Proyecto_TecnologiaService {
    
    List<Proyecto_Tecnologia> getAll();
    
    Proyecto_Tecnologia saveProTec(Proyecto_Tecnologia proTec);
    
    boolean deleteProTec (Integer id);
    
}
