package ee.mihkel.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);
	}

}

// Console-s:
// ssl --> sertifikaadi viga https / http
// ERR_CONNECTION_REFUSED --> back-end ei tööta
// Failed to fetch ---> Network tabi

// 400 --> üldine front-end viga. seda pole võimalik täpsemalt kirjeldada. Body on üldse puudu.
// 404 --> URL vale. endpoint on vale. API otspunkt on vale.   localhost:8080/categoes
// 405 --> API otspunkt (url) on õige, aga sellisel otspunktil ei saa seda Methodit teha   GET --> POST --> PUT
// 415 --> Body on valel kujul. saadame nt kogemata JSON kujul asemel Stringi. Headerid kuju muutmiseks on puudu.
// 5xx --> back-end viga. kõik 4ga algavad on päringu tegija viga. 5ga algav on serveripoolne viga.
// 200 --> õnnestuv

// 24.12/25.12
// 30.12/02.01
