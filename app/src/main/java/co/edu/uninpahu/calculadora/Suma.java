package co.edu.uninpahu.calculadora;

/**
 * Created by ANDROID on 29/09/2017.
 */

public class Suma extends Operacion {

    public double a;
    public double b;

    @Override
    public double ejecutar() {
        return a+b;
    }
}
