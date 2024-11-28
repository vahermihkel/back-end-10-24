package ee.mihkel.kalkulaator;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")

public class LauseKontroller {

    public String lause = "Elas metsas mutionu, keset kuuski noori vanu, kadakapõõsa juures all";

    @GetMapping("/lause")
    public String saaLause() {
        return lause;
    }
    @GetMapping ("/lisaLoppuSona/{uusSonaLopus}")
    public String lisaLoppuSona(@PathVariable String uusSonaLopus) {
        lause = lause + " " + uusSonaLopus;
        return lause;
    }

    @GetMapping("mituTahte")
    public int tahed() {
        return lause.length();
    }

    @GetMapping("/sonadeArv")
    public int sonadeArv() {
        return lause.split(" ").length;
    }

    @GetMapping("mituAtahte")
    public int aTahte() {
//        return lause.stream()
//                .mapToInt(l->(int) l.chars()
//                        .filter(c -> c == 'A' || c == 'a').count()).sum();
        return Arrays.stream(lause.split("")).mapToInt(l -> (int) l.chars()
                .filter(c -> c == 'A' || c == 'a').count()).sum();
    }

//    @GetMapping("mituKtahte")
//    public int kTahte() {
//        return lause.stream()
//                .mapToInt(l->(int) l.chars()
//                        .filter(c -> c == 'K' || c == 'k').count()).sum();
//    }
//
//    @GetMapping("mituMtahte")
//    public int mTahte() {
//        return lause.stream()
//                .mapToInt(l->(int) l.chars()
//                        .filter(c -> c == 'M' || c == 'm').count()).sum();
//    }
//
//    @GetMapping("mituStahte")
//    public int sTahte() {
//        return lause.stream()
//                .mapToInt(l->(int) l.chars()
//                        .filter(c -> c == 'S' || c == 's').count()).sum();
//    }
}