package com.Portfolio.Portfolio.Service.Tecnologia;

import com.Portfolio.Portfolio.Models.Tecnologia;
import java.util.List;

public interface TecnologiaService {

    List<Tecnologia> getAllTecno();

    Tecnologia saveTecno(Tecnologia tecno);

    boolean deleteTecno(Integer id);

    Tecnologia findTecno(Integer id);
}
