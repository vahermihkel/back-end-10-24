package ee.mihkel.webshop.service;

import ee.mihkel.webshop.entity.Person;
import ee.mihkel.webshop.model.LoginResponse;
import ee.mihkel.webshop.model.SignupResponse;
import ee.mihkel.webshop.repository.PersonRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Value("${auth.secret-key}")
    String secretKey;

    public SignupResponse checkIfAllCorrect(Person person, SignupResponse response) {
        if (person.email == null || person.email.isEmpty()) {
            response.setSuccessful(false);
            response.setMessage("E-mail puudub!");
            return response;
        }
        if (!validate(person.email)) {
            response.setSuccessful(false);
            response.setMessage("E-mail ei ole korrektsel kujul!");
            return response;
        }
        if (person.username == null || person.username.isEmpty()) {
            response.setSuccessful(false);
            response.setMessage("Kasutajanimi puudub!");
            return response;
        }
        if (person.password == null || person.password.isEmpty()) {
            response.setSuccessful(false);
            response.setMessage("Parool puudub!");
            return response;
        }
        if (personRepository.findByUsername(person.username) != null) {
            response.setSuccessful(false);
            response.setMessage("Kasutajanimi juba olemas!");
            return response;
        }
        return null;
    }

    public Person checkForErrorsAndGetDbPerson(Person person) {
        if (person.username == null) {
            throw new RuntimeException("Kasutajanime pole saadetud");
        }

        if (person.password == null) {
            throw new RuntimeException("Parooli pole saadetud");
        }

        if (personRepository.findByUsername(person.username) == null) {
            throw new RuntimeException("Kasutajanime pole olemas");
        }

        Person dbPerson = personRepository.findByUsername(person.username);
        if (!dbPerson.password.equals(person.password)) {
            throw new RuntimeException("Parool on vale");
        }

        return dbPerson;
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    public LoginResponse generateToken(Person dbPerson) {
        // 1000 tähendab millisekundid sekunditesse
        // 60 tähendab sekundid minutitesse
        // 20 tähendab 20 minutit
        Date expirationDate = new Date((new Date()).getTime() + 20 * 60 * 1000);

        SecretKey signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        Map<String, String> claims = new HashMap<>();
        claims.put("id", dbPerson.id.toString());

        String token = Jwts.builder()
                .expiration(expirationDate)
                .claims(claims)
                .signWith(signingKey)
                .compact();

        LoginResponse response = new LoginResponse();
        response.setExpiration(expirationDate); // <-------
        response.setAdmin(dbPerson.admin);
        response.setToken(token);
        return response;
    }

    public Person findPersonByToken(String token) {

        SecretKey signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        Object id = Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id");

        return personRepository.findById(Long.parseLong((String) id)).orElseThrow();
    }
}
