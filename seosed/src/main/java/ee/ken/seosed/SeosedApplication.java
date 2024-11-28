package ee.ken.seosed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeosedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeosedApplication.class, args);
	}

}

//• Tee uus veebiprojekt. Loo klass (entity) auto andmete hoidmiseks (mark, pikkus, mass ning aasta).
// Primaarvõtmeks pane kas nimetus või automaatselt genereeritud ID – valik on sinu).
// Tee auto jaoks nii Repository kui ka Controller ning loo andmebaasiühendus.
// Võimalda autosid võtta andmebaasist, lisada andmebaasi kui ka kustutada andmebaasist.
// Tee võimalikult lihtne front-end Reactis, mis võimaldab nii võtta, lisada
// kui ka kustutada andmebaasist läbi Java back-endi.

//• Loo andmebaasi võimalus hoiustada Omanikku .
// Omadusteks anna omanikule nimetus (Primaarvõtmeks pane kas nimetus või automaatselt genereeritud ID – valik on sinu).
// Tee seos Auto sisse – lisa auto tabelisse Omanik @ManyToOne seosena.
// Lisa Omanikule Controller + Repository ning võimalda seda front-endis samuti lisada+kustutatada+kuvada.

// Võimalda omaniku kõiki autosid väljastada.
// Väljasta API otspunktide kaudu kõige pikemat ja kõige raskemat autot Omaniku osas küsida, andes sisendiks Omaniku.
// Kontrolli uue sõiduauto lisamise puhul, et mass ei ületaks viite tonni ning ära lase lisada kui see juhtub.
// Kontrolli auto lisamisel, et ühel omanikul ei oleks üle 3 autoga ning ära lase autot lisada kui see juhtub.

// K 20.11 9.00-12.15
// L 23.11 9.00-12.15
// K 27.11 9.00-12.15

// N 28.11 12.30-15.45 ??
// L 30.11 9.00-12.15  ??

// K 04.12 9.00-12.15
// T 10.12 9.00-12.15
// R 13.12 9.00-12.15
// jne

