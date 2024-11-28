package ee.ken.seosed.controller;

import ee.ken.seosed.entity.Auto;
import ee.ken.seosed.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class AutoController {

    @Autowired
    AutoRepository autoRepository;

    @GetMapping("autod")
    public List<Auto> getAutod() {
        return autoRepository.findAll();
    }

    @PostMapping("autod")
    public List<Auto> lisaAuto(@RequestBody Auto auto) {
        autoRepository.save(auto);

        //        autod.forEach(a -> a.getOmanik().getNimi()); // Force fetching
//        for (Auto a: autod) {
//            if (a.getOmanik() != null) {
//                a.getOmanik().getNimi();
//            }
//        }
        return autoRepository.findAll();
    }

    @DeleteMapping("autod/{id}")
    public List<Auto> kustutaAuto(@PathVariable Long id) {
        autoRepository.deleteById(id);
        return autoRepository.findAll();
    }
}
