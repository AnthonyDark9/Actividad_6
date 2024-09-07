package com.rsec.mx.utils;

/***
 * @author marco
 * @version 1.0
 */
public class CifradoVigenere {

    /**
     *
     * @param texto Le enviamos como parámetro el texto que vamos a descifrar o cifrar.
     * @param clave Le enviamos la clave como parámetro.
     * @param cifrar Le enviamos como parámetro si queremos cifrar (true) o descifrar (false).
     * @return Nos retorna el valor dependiendo de lo que hayamos elegido.
     */
    public static String procesarTexto(String texto, String clave, boolean cifrar) {
        StringBuilder resultado = new StringBuilder();
        clave = clave.toUpperCase();

        int longitudClave = clave.length();

        for (int i = 0; i < texto.length(); i++) {
            char letra = texto.charAt(i);
            if (Character.isLetter(letra)) {
                char base = Character.isUpperCase(letra) ? 'A' : 'a';
                int desplazamiento = clave.charAt(i % longitudClave) - 'A';
                if (!cifrar) {  // Si no es cifrado, restamos el desplazamiento
                    desplazamiento = -desplazamiento;
                }
                letra = (char) ((letra - base + desplazamiento + 26) % 26 + base);
            }
            resultado.append(letra);  // Agregar la letra cifrada o descifrada
        }
        return resultado.toString();
    }

}
