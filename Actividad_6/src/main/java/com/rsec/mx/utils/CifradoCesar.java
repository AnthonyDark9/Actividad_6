package com.rsec.mx.utils;

/**
 * @author marco
 * @version 1.0
 */
public class CifradoCesar {
    // Método para cifrar el texto usando el cifrado César
    public static String cifrar(String texto, int clave) {
        StringBuilder resultado = new StringBuilder(); // StringBuilder para construir el texto cifrado

        // Recorremos cada carácter del texto
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) { // Verificamos si el carácter es una letra
                char base = Character.isLowerCase(c) ? 'a' : 'A'; // Determinamos la base ('a' o 'A')
                // Aplicamos el cifrado: desplazamos la letra y ajustamos el ciclo del alfabeto
                c = (char) ((c - base + clave) % 26 + base);
            }
            resultado.append(c); // Añadimos el carácter cifrado al resultado
        }

        return resultado.toString(); // Devolvemos el texto cifrado
    }

    /**
     *
     * @param texto
     * @param clave
     * @return
     */
    public static String descifrar(String texto, int clave) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base - clave + 26) % 26 + base);
            }
            resultado.append(c);
        }
        return resultado.toString();
    }

    // Método para calcular la frecuencia de las letras en un texto

    /**
     *Método que sirve para  calcular la frecuencia de letras del mensaje cifrado y hacer su respectivo conteo.
     * @param texto Le enviamos el texto cifrado como parámetro.
     */
    public static void calcularFrecuencia(String texto) {
        int[] frecuencia = new int[26]; // Array para contar las 26 letras del alfabeto

        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c);
                frecuencia[c - 'a']++; // Incrementa el contador correspondiente a cada letra
            }
        }

        // Mostrar la frecuencia de cada letra
        for (int i = 0; i < 26; i++) {
            if (frecuencia[i] > 0) {
                System.out.println((char) (i + 'a') + ": " + frecuencia[i]);
            }
        }
    }

    /**
     * Método que sirve para usar la fuerza bruta en un máximo de 25 posibles combincaciones
     * de acuerdo al algoritmo César.
     * @param cifrado Le enviamos como parámetro el mensaje cifrado.
     */
    public static void fuerzaBruta(String cifrado) {
        for (int clave = 1; clave < 26; clave++) {
            String descifrado = descifrar(cifrado, clave);
            System.out.println("Clave " + clave + ": " + descifrado);
        }
    }
}
