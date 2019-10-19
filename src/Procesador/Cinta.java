/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesador;

/**
 *
 * @author ADL
 */
public class Cinta {
    // Constantes
    public static final char EOF = 0;
    public static final char EOLN = 10;
    public static final char BLANK = 32;
    public static final char TAB = 9;
    public static final char CR = 15;

    private int cabecera;
    public String texto;

    public Cinta(String texto) {
        this.texto = texto;
    }

    public void Init() {
        this.cabecera = 0;
    }

    public char cc() {
        if (this.cabecera == this.texto.length()) {
            return Cinta.EOF;
        }
        return this.texto.charAt(cabecera);
    }

    public void Avanzar() {
        if (this.cabecera != this.texto.length()) {
            this.cabecera++;
        }
    }

    public boolean Espacio(char c) {
        return (c == Cinta.EOLN || c == Cinta.BLANK || c == Cinta.TAB || c == Cinta.CR);
    }

    public boolean Digito(char c) {
        return (48 <= c && c <= 57);
    }

    public boolean Letra(char c) {
        return ((65 <= c && c <= 90) || (97 <= c && c <= 122));
    }
}
