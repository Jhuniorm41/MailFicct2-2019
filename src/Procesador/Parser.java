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
public class Parser {
    
    private Analex analex;
    public boolean errorFlag;

    public Parser(Analex analex) {
        this.analex = analex;
    }

    public void Expresion() {
        this.errorFlag = false;
        if (analex.Preanalisis().getNombre() == Token.HELP) {
            match(Token.HELP);
        } else {
            match(Token.FUNC);
            acomp();
        }
    }

    public void acomp() {
        if (analex.Preanalisis().getNombre() == Token.LLA) {
            match(Token.LLA);
            parametros();
            match(Token.LLC);
        } else if (analex.Preanalisis().getNombre() == Token.HELP) {
            match(Token.HELP);
        }
    }

    public void parametros() {
        param(); //ya tengo un parametro y busco la coma
        masparam();
    }

    public void param() {
        switch (analex.Preanalisis().getNombre()) {
            case Token.STRING:
                match(Token.STRING);
                break;
            case Token.NUM:
                match(Token.NUM);
                break;
            case Token.TRUE:
                match(Token.TRUE);
                break;
            case Token.FALSE:
                match(Token.FALSE);
                break;
            default:
                match(Token.GB);
                break;
        }
    }

    public void masparam() {
        if (analex.Preanalisis().getNombre() == Token.SEPARADOR) {
            match(Token.SEPARADOR);
            param();
            masparam();
        }
    }

    public void match(int token) {
        System.out.println("ESTE ES EL TOKEN A VERIFICAR ----------------> " + token);
        System.out.println("ESTE ES EL TOKEN QUE ANALIZA " + analex.Preanalisis().getNombre());
        if (analex.Preanalisis().getNombre() == token) {
            analex.Avanzar();
        } else {
            System.out.println("Error en Parser al procesar Token!");
            this.errorFlag = true;
        }
    }

    public void Init() {
        analex.Init();
    }
}
