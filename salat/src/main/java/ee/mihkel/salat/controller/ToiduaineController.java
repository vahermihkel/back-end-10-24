package ee.mihkel.salat.controller;

import ee.mihkel.salat.entity.Toiduaine;
import ee.mihkel.salat.repository.ToiduaineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ToiduaineController {

    // hiljem: võtame need andmebaasist
//    List<Toiduaine> toiduained = new ArrayList<>(Arrays.asList(
//            new Toiduaine("Kartul", 1, 15, 2),
//            new Toiduaine("Hapukoor", 10, 5, 15),
//            new Toiduaine("Vorst", 5, 15, 12)
//    ));

    @Autowired
    ToiduaineRepository toiduaineRepository;

    @GetMapping("toiduained")
    public List<Toiduaine> saaToiduained() {
        return toiduaineRepository.findAll();
    }

    @PostMapping("toiduained")
    public List<Toiduaine> lisaToiduaine(@RequestBody Toiduaine toiduaine) {
//        toiduained.add(toiduaine);
        toiduaineRepository.save(toiduaine);
        return toiduaineRepository.findAll();
    }

    @DeleteMapping("toiduained/{name}")
    public List<Toiduaine> kustutaToiduaine(@PathVariable String name) {
        toiduaineRepository.deleteById(name);
        return toiduaineRepository.findAll();
    }

    @PutMapping("toiduained")       // millist ta muudab? Body sees peaks olema õige ID
    public List<Toiduaine> muudaToiduaine(@RequestBody Toiduaine toiduaine) {
        toiduaineRepository.save(toiduaine);
        return toiduaineRepository.findAll();
    }

    @PatchMapping("toiduained/{name}") // ühe objekti väärtuse muutmiseks
    public List<Toiduaine> muudaToiduaine(@PathVariable String name) {
        Toiduaine toiduaine = toiduaineRepository.findById(name).get();
        toiduaine.setValk(10);
        toiduaineRepository.save(toiduaine);
        return toiduaineRepository.findAll();
    }
}
