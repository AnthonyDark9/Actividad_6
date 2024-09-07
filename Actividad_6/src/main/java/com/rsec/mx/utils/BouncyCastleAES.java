package com.rsec.mx.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;

/**
 * @author marco
 * @version 1.0
 */
public class BouncyCastleAES {
    // Inicializar Bouncy Castle
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     *  Método para generar una clave o key AES-
     * @return Nos retorna la clave tipo AES cifrada.
     * @throws Exception Nos tira una excepción en caso de que la haya.
     */
    public static SecretKey generarClaveAES() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES", "BC");
        keyGen.init(256);  // Tamaño de clave AES (128, 192, o 256 bits)
        return keyGen.generateKey();
    }

    /**
     * Método para generar un vector de inicialización (IV)
     * @return Nos retorna un nuevo vector de inicialización.
     */
    public static IvParameterSpec generarIV() {
        byte[] iv = new byte[16];  // AES usa un IV de 16 bytes
        return new IvParameterSpec(iv);
    }

    /**
     *
     * @param texto Le enviamos como parámetro el texto que queremos cifrar.
     * @param clave Le mandamos como parámetro la clave generada.
     * @param iv Se envia el vector de inizialización.
     * @return Nos retorna el texto cifrado AES.
     * @throws Exception
     */
    public static String cifrarAES(String texto, SecretKey clave, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");  // AES en modo CBC con relleno PKCS5
        cipher.init(Cipher.ENCRYPT_MODE, clave, iv);

        byte[] textoCifrado = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(textoCifrado);  // Convertir a Base64
    }

    /**
     *
     * @param textoCifrado Le mandamos el texto cifrado.
     * @param clave Le mandamos la clave que nos genero.
     * @param iv Le enviamos el vector de inicialización.
     * @return Nos retorna el texto descifrado.
     * @throws Exception Nos retorna una excepción si la hay.
     */
    public static String descifrarAES(String textoCifrado, SecretKey clave, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, clave, iv);

        byte[] textoDescifrado = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
        return new String(textoDescifrado, StandardCharsets.UTF_8);
    }
}
