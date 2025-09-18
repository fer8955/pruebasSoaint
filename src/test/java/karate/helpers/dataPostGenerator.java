package karate.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Random;


public class dataPostGenerator {
    private static final List<String> nomes = new ArrayList<>();
    private static final List<String> sobrenomes = new ArrayList<>();
    private static final Random RANDOM = new Random();

    /**
     * Genera un correo electrónico único utilizando un UUID.
     * Ejemplo: test_9f03c08b@qa.com
     */
    public static String generateUniqueEmail() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return "test_" + uuid + "@qa.com";
    }

    static {
        try (BufferedReader br = new BufferedReader(new FileReader("src/test/java/karate/util/data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    nomes.add(parts[0].trim());
                    sobrenomes.add(parts[1].trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer data.csv", e);
        }
    }

    public static String generateUniqueNome() {
        String nome = nomes.get(RANDOM.nextInt(nomes.size()));
        String sobrenome = sobrenomes.get(RANDOM.nextInt(sobrenomes.size()));
        return nome + " " + sobrenome;
    }

    public static String generateUniquePass(){
        // Generar UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String lettersOnly = uuid.replaceAll("[^a-zA-Z]", "");

        if (lettersOnly.length() < 5) {
            // Si por alguna razón no hay suficientes letras, generar otro UUID
            return generateUniquePass();
        }

        // Retornar el pass en minuscula
        return lettersOnly.substring(0, 5).toLowerCase();
    }

    public static String generateId(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 8);
    }


    public static String getRandomBooleanAsString() {
        return RANDOM.nextBoolean() ? "true" : "false";
    }


}
