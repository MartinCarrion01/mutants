package com.mercadolibre.mutants;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Esta clase genera secuencias de ADN random de tamaño 600x600 hasta 999x999
public class Generator {
    public static List<String> randomSequence() {
        int[] letras = {65, 67, 71, 84};
        List<String> secuencia = new ArrayList<>();
        Random r = new Random();
        int size = r.nextInt(400) + 600;
        for (int i = 0; i < size; i++) {
            String s = "";
            for (int j = 0; j < size; j++) {
                char randomChar = (char) letras[(new Random()).nextInt(4)];
                s += randomChar;
            }
            secuencia.add(s);
        }
        String printable = "[";
        for (String s : secuencia) {
            printable += "\"" + s + "\",";
        }
        printable += "]";
        try {
            File file = new File("C:\\Users\\Martín\\Favorites\\Desktop\\test.txt");
            FileWriter fw = new FileWriter(file);
            if (file.length() == 0) {
                fw.write(printable);
                fw.close();
            } else {
                fw.write("");
                fw.write(printable);
                fw.close();
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return secuencia;
    }
}