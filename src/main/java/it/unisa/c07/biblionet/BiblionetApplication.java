package it.unisa.c07.biblionet;

import it.unisa.c07.biblionet.model.entity.compositeKey.PossessoId;

import org.springframework.context.ApplicationContext;
import org.apache.commons.io.FileUtils;
import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;

/**
 * Questa è la main class del progetto, che fa partire l'applicazione e popola
 * il database.
 */
@SpringBootApplication
public class BiblionetApplication {


    public static void main(String[] args) throws NoSuchAlgorithmException {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(BiblionetApplication.class, args);

    }

    public static String getCopertinaClubFromUrl(String filePath) {
        try{
            byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
            return Base64.getEncoder().encodeToString(fileContent);
        }
        catch (IOException ex){
            ex.printStackTrace();
            return "noImage";
        }

    }
}
