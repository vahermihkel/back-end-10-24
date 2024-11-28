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
public class SonadKontroller {
    List<String> sonad = new ArrayList<>(Arrays.asList("Üks", "Kaks", "Kolm"));



    @GetMapping("arvuta-tahed/{index}")
    public int arvutaTahed(@PathVariable int index) {
        return sonad.get(index).length();
    }

    @GetMapping("esimene-taht/{index}")
    public String esimeneTaht(@PathVariable int index) {
        return sonad.get(index).substring(0,1);
    }

    @GetMapping("viimane-taht/{index}")
    public String viimaneTaht(@PathVariable int index) {
        String sona = sonad.get(index);
        int eelviimaneIndex = sona.length()-1;
        int viimaneIndex = sona.length();
        return sona.substring(eelviimaneIndex,viimaneIndex);
    }
}
