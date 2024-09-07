package com.rsec.mx.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author marco
 * @version 1.0
 */
public class Hash {

    /**
     *
     * @param rutaArchivo Le enviamos como parámetro la ruta del archivo que vamos a evaluar.
     * @return Nos retorna un valor String.
     */
    public static String obtenerHash(String rutaArchivo) {
        try (FileInputStream fis = new FileInputStream(rutaArchivo)) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytesBuffer = new byte[1024];
            int bytesRead;

            // Leer el archivo y actualizar el digest con los bytes leídos
            while ((bytesRead = fis.read(bytesBuffer)) != -1) {
                digest.update(bytesBuffer, 0, bytesRead);
            }

            // Convertir el resultado del digest a una cadena hexadecimal
            byte[] hashBytes = digest.digest();
            StringBuilder hashString = new StringBuilder();

            for (byte b : hashBytes) {
                hashString.append(String.format("%02x", b));
            }

            return hashString.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para comparar dos valores hash

    /**
     *
     * @param hash1 Parametro que se envia para evaluar.
     * @param hash2 Parámetro que se envia para evaluar.
     * @return Nos retorna el valor hash.
     */
    public static boolean compararHash(String hash1, String hash2) {
        return hash1 != null && hash1.equals(hash2);
    }
}
