package de.fhw.flippermods;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service public class DataService {

    public List<DataPackage> findAll(){
        return new ArrayList<>();
    }
}
