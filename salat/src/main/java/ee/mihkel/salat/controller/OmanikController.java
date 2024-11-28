package ee.mihkel.salat.controller;

import ee.mihkel.salat.entity.Omanik;
import ee.mihkel.salat.entity.Toiduaine;
import ee.mihkel.salat.repository.OmanikRepository;
import ee.mihkel.salat.repository.ToiduaineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OmanikController {


    @Autowired
    OmanikRepository omanikRepository;

    @GetMapping("omanikud")
    public List<Omanik> saaOmanikud() {
        return omanikRepository.findAll();
    }

    @PostMapping("omanikud")
    public List<Omanik> lisaOmanik(@RequestBody Omanik omanik) {
        omanikRepository.save(omanik);
        return omanikRepository.findAll();
    }

    @DeleteMapping("omanikud/{id}")
    public List<Omanik> kustutaOmanik(@PathVariable Long id) {
        omanikRepository.deleteById(id);
        return omanikRepository.findAll();
    }

    @PutMapping("omanikud")
    public List<Omanik> muudaOmanik(@RequestBody Omanik omanik) {
        omanikRepository.save(omanik);
        return omanikRepository.findAll();
    }

}
