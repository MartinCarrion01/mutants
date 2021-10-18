package com.mercadolibre.mutants.detector;

import com.mercadolibre.mutants.entities.Mutant;

import java.util.ArrayList;
import java.util.List;

//A esta clase se le asigno la responsabilidad de analizar si el sujeto es mutante.
public class MutantDetector {

    public int counter;

    //Este enum es usado para catalogar un elemento de la matriz segun la direcciones en las que se puede analizar
    //para encontrar mutantes.
    public enum WhereTo {
        AAD, AOE, ASSW     //aaa: analyze all directions, aoe: analyze only east, assw: analyze south & southwest
    }

    //Este metodo contiene la logica del analisis de secuencias de ADN
    public boolean dnaAnalyze(Mutant mutant) {

        counter = 0;
        long startTime = System.currentTimeMillis();
        List<char[]> dnaCharArr = new ArrayList<>();

        /*
        Esto me convierte la lista de strings de la secuencia de adn en una
        lista de char[], eso me permite trabajarlo como una matriz al problema.
         */
        for (String s : mutant.getBases()) {
            char[] buf = s.toCharArray();
            dnaCharArr.add(buf);
        }

        for (int i = 0; i < dnaCharArr.size(); i++) {
            for (int j = 0; j < dnaCharArr.get(i).length; j++) {


                //Si hay dos o mas cadenas que indiquen que el adn ingresado es mutante, retorno true.
                if (this.counter >= 2) {
                    long endTime = System.currentTimeMillis() - startTime;
                    System.out.println("Se tardó: " + endTime + "ms");
                    return true;
                }

                /*
                Si en el punto en el que estoy parado en la matriz puedo contar 4 pasos hacia la derecha
                Y 4 pasos hacia abajo, entonces puedo analizar teniendo como punto de partida el i,j
                actual haciendo 4 pasos hacia el este, sur, sureste y suroeste. Caso contario, no analizo
                y voy a la siguiente iteración.
                 */
                if (!((i + 4 > dnaCharArr.size()) & (j + 4 > dnaCharArr.get(i).length))) {

                    WhereTo where = switcher(i, j, dnaCharArr.size(), dnaCharArr.get(i).length); //Analizo hacia donde puedo caminar

                    /*
                    Segun donde pueda caminar teniendo como partida el i,j actual en el for,
                    analizo en las direcciones posibles, buscando si encuentro una cadena mutante.
                     */
                    switch (where) {

                        case AAD:

                            eastVerifier(i, j, dnaCharArr, mutant);

                            southVerifier(i, j, dnaCharArr, mutant);

                            southEastVerifier(i, j, dnaCharArr, mutant);

                            if (!(j - 4 < 0)) {        //verifico si se puede caminar hacia el suroeste
                                southWestVerifier(i, j, dnaCharArr, mutant);
                            }

                            break;
                        case AOE:

                            eastVerifier(i, j, dnaCharArr, mutant);

                            break;
                        case ASSW:

                            southVerifier(i, j, dnaCharArr, mutant);

                            if (!(j - 4 < 0)) {     //verifico si se puede caminar hacia el suroeste
                                southWestVerifier(i, j, dnaCharArr, mutant);
                            }

                            break;
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println("Se tardó: " + endTime + "ms");
        return false;
    }

    /*
    Este metodo determina en que direccion puedo caminar, teniendo como punto de partida el i,j actual
    en el bucle for.
     */
    public WhereTo switcher(int i, int j, int vertical, int horizontal) {

        /*
        Si tanto i + 4 y j + 4 dan como resultado un numero que es menor que el tamaño de la matriz
        entonces, puedo caminar teniendo como punto de partida el i,j actual, hacia el este, sur,
        sureste y suroeste.
         */
        if ((!(i + 4 > vertical)) & (!(j + 4 > horizontal))) {
            return WhereTo.AAD;
        }

        /*
        Si i + 4 da como resultado un numero mayor que la cantidad de filas y j + 4 da como resultado
        un numero menor a la cantidad de columnas, entonces teniendo como punto de partida el i,j actual
        solo puedo caminar hacia el este.
         */
        if (((i + 4 > vertical)) & (!(j + 4 > horizontal))) {
            return WhereTo.AOE;
        }

        /*
        Si i + 4 da como resultado un numero menor a la cantidad de filas y j + 4 da como resultado un
        numero mayor a la cantidad de columnas, entonces teniendo como punto de partida el i,j actual
        seguro que puedo caminar hacia al sur pero debo analizar en otra instancia si se puede caminar hacia
        el suroeste.
         */
        if ((!(i + 4 > vertical)) & ((j + 4 > horizontal))) {
            return WhereTo.ASSW;
        }
        return null;
    }

    /*
    Verifico si hay una cadena mutante hacia el este del i,j actual.
     */
    public void eastVerifier(int i, int j, List<char[]> dnaCharArr, Mutant mutant) {

        char aux = dnaCharArr.get(i)[j];

        /*
        Si el caracter que esta 3 lugares hacia el este del i,j actual, es igual al caracter
        aux que representa el caracter en el i,j actual, entonces se puede dar el caso que
        encuentre una cadena de 4 caracteres iguales seguidos hacia el este,
        caso contario no vale la pena analizar
         */
        if (aux == dnaCharArr.get(i)[j + 3]) {
            int check = 0;
            for (int k = 1; k < 3; k++) {
                if (aux == dnaCharArr.get(i)[j + k]) {
                    check++;
                }
            }

            //si encontro una cadena de 4 seguidos, incremento en 1 la cantidad de cadenas mutantes del sujeto de prueba
            if (check == 2) {
                //System.out.println("Begin: " + i + " , " + j + " east");
                this.counter++;
            }
        }
    }

    /*
    Verifico si hay una cadena mutante hacia el sur del i,j actual.
     */
    public void southVerifier(int i, int j, List<char[]> dnaCharArr, Mutant mutant) {

        char aux = dnaCharArr.get(i)[j];

        /*
        Si el caracter que esta 3 lugares hacia el sur del i,j actual, es igual al caracter
        aux que representa el caracter en el i,j actual, entonces se puede dar el caso que
        encuentre una cadena de 4 caracteres iguales seguidos hacia el sur,
        caso contario no vale la pena analizar
         */
        if (aux == dnaCharArr.get(i + 3)[j]) {
            int check = 0;
            for (int k = 1; k < 3; k++) {
                if (aux == dnaCharArr.get(i + k)[j]) {
                    check++;
                }
            }

            //si encontro una cadena de 4 seguidos, incremento en 1 la cantidad de cadenas mutantes del sujeto de prueba
            if (check == 2) {
                //System.out.println("Begin: " + i + " , " + j + " south");
                this.counter++;
            }
        }
    }

    /*
    Verifico si hay una cadena mutante hacia el sureste del i,j actual.
     */
    public void southEastVerifier(int i, int j, List<char[]> dnaCharArr, Mutant mutant) {

        char aux = dnaCharArr.get(i)[j];

        /*
        Si el caracter que esta 3 lugares hacia el sur del i,j actual y 3 hacia el este
        del i,j actual, es igual al caracter aux que representa el caracter en el i,j actual,
        entonces se puede dar el caso que encuentre una cadena de 4 caracteres iguales seguidos hacia el sureste,
        caso contario no vale la pena analizar
         */
        if (aux == dnaCharArr.get(i + 3)[j + 3]) {
            int check = 0;
            int l = 1;
            int k = 1;
            while (l < 3 && k < 3) {
                if (aux == dnaCharArr.get(i + k)[j + l]) {
                    check++;
                }
                l++;
                k++;
            }

            //si encontro una cadena de 4 seguidos, incremento en 1 la cantidad de cadenas mutantes del sujeto de prueba
            if (check == 2) {
                //System.out.println("Begin: " + i + " , " + j + " southeast");
                this.counter++;
            }
        }
    }

    /*
    Verifico si hay una cadena mutante hacia el suroeste del i,j actual.
     */
    public void southWestVerifier(int i, int j, List<char[]> dnaCharArr, Mutant mutant) {

        char aux = dnaCharArr.get(i)[j];

        /*
        Si el caracter que esta 3 lugares hacia el sur del i,j actual y 3 hacia el oeste
        del i,j actual, es igual al caracter aux que representa el caracter en el i,j actual,
        entonces se puede dar el caso que encuentre una cadena de 4 caracteres iguales seguidos hacia el suroeste,
        caso contario no vale la pena analizar
         */
        if (aux == dnaCharArr.get(i + 3)[j - 3]) {
            int check = 0;
            int l = 1;
            int k = 1;
            while (l < 3 && k < 3) {
                if (aux == dnaCharArr.get(i + k)[j - l]) {
                    check++;
                }
                l++;
                k++;
            }

            //si encontro una cadena de 4 seguidos, incremento en 1 la cantidad de cadenas mutantes del sujeto de prueba
            if (check == 2) {
                //System.out.println("Begin: " + i + " , " + j + " southwest");
                this.counter++;
            }
        }
    }
}

