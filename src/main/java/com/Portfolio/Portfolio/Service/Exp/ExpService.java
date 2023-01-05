package com.Portfolio.Portfolio.Service.Exp;

import com.Portfolio.Portfolio.Models.Exp;
import java.util.List;

public interface ExpService {

    List<Exp> getAllExp();

    Exp saveExp(Exp ex);

    boolean deleteExp(Integer id);

    Exp findExp(Integer id);
}
