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
public class Analex {
    public Cinta M;
    private Token R = new Token();
    private String AC = "";

    public Analex(Cinta cinta) {
        M = cinta;
        Init();
    }

    private void dt() {
        int Estado = 0;
        while (true) {
            char c = M.cc();
            switch (Estado) {
                case 0:
                    if (M.Letra(c)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 3;
                    } else if (c == ",".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 13;
                    } else if (c == "\"".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 5;
                    } else if (M.Digito(c)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 7;
                    } else if (c == "_".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 15;
                    } else if (c == "{".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 9;
                    } else if (c == "}".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 11;
                    } else if (M.Espacio(c)) {
                        M.Avanzar();
                    } else if (c == Cinta.EOF) {
                        Estado = 1;
                    } else {
                        Estado = 2;
                    }
                    break;
                case 1:
                    AC = "EOF";
                    R = new Token(Token.FIN, -1, AC);
                    return;
                case 2:
                    R = new Token(Token.ERROR, -1, AC);
                    return;
                case 3:
                    if (M.Letra(c)) {
                        AC += String.valueOf(c);
                        M.Avanzar();
                    } else {
                        Estado = 4;
                    }
                    break;
                case 4:
                    Token token = TPC.estaEnTPC(AC);
                    if (token != null) {
                        R = token;
                        return;
                    } else {
                        Estado = 2;
                    }
                    break;
                case 5:
                    if (c != Cinta.EOF && c != Cinta.EOLN && c != "\"".charAt(0)) {
                        AC += String.valueOf(c);
                        M.Avanzar();
                    } else if (c == "\"".charAt(0)) {
                        AC += String.valueOf(c);
                        M.Avanzar();
                        Estado = 6;
                    } else {
                        Estado = 2;
                    }
                    break;
                case 6:
                    R = new Token(Token.STRING, 1, AC);
                    return;
                case 7:
                    if (M.Digito(c)) {
                        AC += String.valueOf(c);
                        M.Avanzar();
                    } else {
                        Estado = 8;
                    }
                    break;
                case 8:
                    R = new Token(Token.NUM, Integer.parseInt(AC), AC);
                    return;
                case 9:
                    Estado = 10;
                    break;
                case 10:
                    R = new Token(Token.LLA, -1, AC);
                    return;
                case 11:
                    Estado = 12;
                    break;
                case 12:
                    R = new Token(Token.LLC, -1, AC);
                    return;
                case 13:
                    Estado = 14;
                    break;
                case 14:
                    R = new Token(Token.SEPARADOR, -1, AC);
                    return;
                case 15:
                    Estado = 16;
                    break;
                case 16:
                    R = new Token(Token.GB, -1, AC);
                    return;
                default:
                    break;
            }
        }
    }

    public void Init() {
        M.Init();
        dt();
    }

    public Token Preanalisis() {
        return R;
    }

    public String Lexema() {
        return AC;
    }

    public void Avanzar() {
        dt();
    }
}
