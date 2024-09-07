package com.rsec.mx;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.rsec.mx.utils.BouncyCastleAES.*;
import static com.rsec.mx.utils.CifradoCesar.*;
import static com.rsec.mx.utils.CifradoVigenere.procesarTexto;
import static com.rsec.mx.utils.Hash.compararHash;
import static com.rsec.mx.utils.Hash.obtenerHash;

/**
 *
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion;

        do {
            System.out.println("\n|||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("||                   MENU                      ||");
            System.out.println("|| 1. Cifrado César                            ||");
            System.out.println("|| 2. Obtener y comparar hash de un archivo    ||");
            System.out.println("|| 3. Cifrado Vigenere                         ||");
            System.out.println("|| 4. Cifrado AES - BouncyCastle               ||");
            System.out.println("|| 5. Salir                                    ||");
            System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.print("\nSeleccione una opcion: ");
            opcion = Integer.parseInt(br.readLine());

            switch (opcion) {
                case 1:
                    System.out.print("\nEscriba el mensaje que desee cifrar: ");
                    String mensaje = br.readLine();

                    System.out.print("Escriba el número de clave: ");
                    int clave = Integer.parseInt(br.readLine());

                    String mensajeCifrado = cifrar(mensaje, clave);
                    System.out.println("\nMensaje cifrado: " + mensajeCifrado);

                    System.out.print("\nDesea descifrar el mensaje?: ");
                    String msjDescif = br.readLine();

                    if (msjDescif.equalsIgnoreCase("SI") || msjDescif.equalsIgnoreCase("s")) {
                        String mensajeDescifrado = descifrar(mensajeCifrado, clave);
                        System.out.println("Mensaje descifrado: " + mensajeDescifrado);
                    }

                    // Parte del Criptoanalisis por fuerza bruta
                    System.out.print("\nDesea descifrar la clave por fuerza bruta?: ");
                    String msjFuerzaBruta = br.readLine();

                    if (msjFuerzaBruta.equalsIgnoreCase("SI") || msjFuerzaBruta.equalsIgnoreCase("s")) {
                        fuerzaBruta(mensajeCifrado);
                    }

                    // Parte del criptoanalisis por analisis estadistico.
                    System.out.print("\nDesea calcular la frecuencia de letras en el mensaje cifrado: ");
                    String msjTexto = br.readLine();

                    if (msjTexto.equalsIgnoreCase("SI") || msjTexto.equalsIgnoreCase("s")) {
                        calcularFrecuencia(mensajeCifrado);
                    }

                    System.out.println("\nSaliendo al menu....");
                    break;
                case 2:
                    String rutaArchivo1 = "C:\\Users\\marco\\IdeaProjects\\Actividad_6\\src\\main\\java\\com\\rsec\\mx\\resources\\hash1.txt";
                    String rutaArchivo2 = "C:\\Users\\marco\\IdeaProjects\\Actividad_6\\src\\main\\java\\com\\rsec\\mx\\resources\\hash2.txt";

                    // Obtener el hash de los archivos
                    String hashArchivo1 = obtenerHash(rutaArchivo1);
                    String hashArchivo2 = obtenerHash(rutaArchivo2);

                    // Mostrar los hashes
                    System.out.println("Hash del archivo 1: " + hashArchivo1);
                    System.out.println("Hash del archivo 2: " + hashArchivo2);

                    // Comparar los hashes
                    if (compararHash(hashArchivo1, hashArchivo2)) {
                        System.out.println("Los archivos tienen el mismo hash (son idénticos).");
                    } else {
                        System.out.println("Los archivos tienen hashes diferentes.");
                    }
                    break;
                case 3:
                    System.out.print("\nEscriba el texto que desee cifrar: ");
                    String texto = br.readLine();

                    System.out.print("\nEscriba la clave: ");
                    String clav = br.readLine();

                    // Cifrar el texto
                    String textoCifrado = procesarTexto(texto, clav, true);
                    System.out.println("\nTexto Cifrado: " + textoCifrado);

                    // Descifrar el texto
                    String textoDescifrado = procesarTexto(textoCifrado, clav, false);
                    System.out.println("Texto Descifrado: " + textoDescifrado);
                    break;
                case 4:
                    SecretKey key = generarClaveAES();
                    IvParameterSpec iv = generarIV();

                    System.out.print("\nEscribe el mensaje que quieres cifrar: ");
                    String textoOriginal = br.readLine();

                    String textCifrado = cifrarAES(textoOriginal, key, iv);
                    System.out.println("\nTexto Cifrado: " + textCifrado);

                    String textDescifrado = descifrarAES(textCifrado, key, iv);
                    System.out.println("\nTexto Descifrado: " + textDescifrado);
                    break;
                case 5:
                    System.out.println("Saliendoo......");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;

            }
        } while (opcion != 5);
    }
}
