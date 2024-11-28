package ee.mihkel.kalkulaator;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class ToiduaineKontroller {
    List<Toiduaine> toiduained = new ArrayList<>(Arrays.asList(
            new Toiduaine("Piim", 5,2,1),
            new Toiduaine("Või",10,8,5),
            new Toiduaine("Juust",1,9,4)
    ));

    @GetMapping("/toiduained")
    public List<Toiduaine> saaToiduained() {
        return toiduained;
    }

    @DeleteMapping("kustuta-toiduaine/{index}")
    public List<Toiduaine> kustutaToiduaine(@PathVariable int index) {
        toiduained.remove(index);
        return toiduained;
    }

    @PostMapping("lisa-toiduaine")
    public List<Toiduaine> lisaToiduaine(@RequestBody Toiduaine toiduaine) {
        toiduained.add(toiduaine);
        return toiduained;
    }

    @PostMapping("lisa-toiduaine1")
    public List<Toiduaine> lisaToiduaine1(@RequestParam String nimi, @RequestParam int rasv, @RequestParam int sysivesik, @RequestParam int valk) {
        toiduained.add(new Toiduaine(nimi, rasv, sysivesik, valk));
        return toiduained;
    }

    @PostMapping("lisa-toiduaine2/{nimi}/{rasv}/{sysivesik}/{valk}")
    public List<Toiduaine> lisaToiduaine2(@PathVariable String nimi, @PathVariable int rasv, @PathVariable int sysivesik, @PathVariable int valk) {
        toiduained.add(new Toiduaine(nimi, rasv, sysivesik, valk));
        return toiduained;
    }

    @GetMapping("/liida-rasvad")
    public int liidaRasvad() {
        int summa = 0;
        for (Toiduaine t: toiduained) {
            summa = summa + t.getRasv();
        }
        return summa;
    }

    @GetMapping("/liida-valgud")
    public int liidaValgud() {
        int summa = 0;
        for (Toiduaine t: toiduained) {
            summa = summa + t.getValk();
        }
        return summa;
    }

    @GetMapping("/keskmine-rasv")
    public double keskmineRasv() {
        double summa = 0;
        for (Toiduaine t: toiduained) {
            summa = summa + t.getRasv();
        }
        return summa/toiduained.size();
    }

    @GetMapping("/toiduained-az")
    public List<Toiduaine> toiduainedAZ() {
        toiduained.sort(Comparator.comparing(a -> a.nimi));
        return toiduained;
    }

    @GetMapping("/toiduained-valk-kasvavalt")
    public List<Toiduaine> toiduainedValkKasvavalt() {
        toiduained.sort(Comparator.comparing(a -> a.valk));
        return toiduained;
    }
}
