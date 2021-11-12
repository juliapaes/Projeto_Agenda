package com.turma2.aplicacao;

import java.io.IOException;

public class CLS {

    public static void limparTela(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                //Runtime.getRuntime().exec("clear");
                System.out.print("\033\143");
        } catch (IOException | InterruptedException ex) {}
    }

}