package ee.mihkel.salat.controller;

import ee.mihkel.salat.entity.Lemmikloom;
import ee.mihkel.salat.repository.LemmikloomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LemmikloomController {


    @Autowired
    LemmikloomRepository lemmikloomRepository;

    @GetMapping("lemmikloomad")
    public List<Lemmikloom> saaLemmikloomad() {
        return lemmikloomRepository.findAll();
    }

    @PostMapping("lemmikloomad")
    public List<Lemmikloom> lisaLemmikloom(@RequestBody Lemmikloom lemmikloom) {
        lemmikloomRepository.save(lemmikloom);
        return lemmikloomRepository.findAll();
    }

    @DeleteMapping("lemmikloomad/{id}")
    public List<Lemmikloom> kustutaLemmikloom(@PathVariable Long id) {
        lemmikloomRepository.deleteById(id);
        return lemmikloomRepository.findAll();
    }

    @PutMapping("lemmikloomad")
    public List<Lemmikloom> muudaLemmikloom(@RequestBody Lemmikloom lemmikloom) {
        lemmikloomRepository.save(lemmikloom);
        return lemmikloomRepository.findAll();
    }

}
