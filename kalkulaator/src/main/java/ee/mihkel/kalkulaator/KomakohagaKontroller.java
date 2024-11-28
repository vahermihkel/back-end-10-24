package ee.mihkel.kalkulaator;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class KomakohagaKontroller {
    List<Double> numbrid = new ArrayList<>(Arrays.asList(1.1, 2.3, 3.4, 4.5, 5.0));

    @GetMapping("saa-komakohaga")
    public List<Double> saaKomakohagaNumbrid() {
        return numbrid;
    }

    @DeleteMapping("kustuta-komakohaga/{index}")
    public List<Double> kustutaKomakohagaNumber(@PathVariable int index) {
        numbrid.remove(index);
        return numbrid;
    }

    // Put --> muutmine

    // Post --> ühe juurde lisamine
    @PostMapping("lisa-komakohaga/{number}")
    public List<Double> lisaKomakohagaNumber(@PathVariable double number) {
        numbrid.add(number);
        return numbrid;
    }

    @GetMapping("arvuta-kokku")
    public Double arvutaKokku() {
        double sum = 0;
        for (double nr: numbrid) {
            sum = sum + nr;
        }
        return sum;
    }

    @GetMapping("kogus")
    public int koguarv() {
        return numbrid.size();
    }

    @GetMapping("sonum")
    public String sonum() {
        if (numbrid.size() < 10) {
            return "Numbreid on vähem kui 10";
        } else {
            return "Numbreid on 10 või rohkem";
        }
    }

}
