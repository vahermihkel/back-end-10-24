package ee.mihkel.salat.controller;

import ee.mihkel.salat.entity.Toiduaine;
import ee.mihkel.salat.entity.Toit;
import ee.mihkel.salat.repository.ToitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ToitController {
//    List<Toit> toidud = new ArrayList<>(Arrays.asList(
//            new Toit("Kartulisalat", 500, new ArrayList<>(Arrays.asList("Kartul", "Vorst", "Hapukoor", "Muna"))),
//            new Toit("Vorstivõileib", 100, new ArrayList<>(Arrays.asList("Vorst", "Või", "Leib"))),
//            new Toit("Praemuna", 250, new ArrayList<>(Arrays.asList("Muna", "Vorst", "Juust")))
//    ));

    @Autowired
    ToitRepository toitRepository;

    @GetMapping("toit")
    public List<Toit> saaToidud() {
        return toitRepository.findAll();
    }

    @PostMapping("toit")
    public List<Toit> lisaToit(@RequestBody Toit toit) {
//        toidud.add(toit);
        toitRepository.save(toit);
        return toitRepository.findAll();
    }

    @DeleteMapping("toit/{name}")
    public List<Toit> kustutaToit(@PathVariable String name) {
        toitRepository.deleteById(name);
        return toitRepository.findAll();
    }

    // TEISTELE KA ---> Kalkulaatori sisse / Autod / Töötajad
    @PutMapping("toit")
    public List<Toit> muudaToit(@RequestBody Toit toit) {
//        toidud.set(index, toit);
        toitRepository.save((toit));
        return toitRepository.findAll();
    }

    @GetMapping("toit/{name}")
    public Toit saaToit(@PathVariable String name) {
        return toitRepository.findById(name).get();
    }
}

// Kõrgkoolis edasijõudnum -> Spring/React
// Kutsekoolis -> Praktilisem
