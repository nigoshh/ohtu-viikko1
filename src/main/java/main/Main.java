package main;

import ohtu.ohtuvarasto.Varasto;

public class Main {

    public static void main(String[] args) {

        final double TAIKA_TILAVUUS = 100.0;
        final double TAIKA_OLUEN_MAARA = 20.3;

        Varasto mehua = new Varasto(TAIKA_TILAVUUS);
        Varasto olutta = new Varasto(TAIKA_TILAVUUS, TAIKA_OLUEN_MAARA);

        System.out.println("Luonnin jälkeen:");
        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("Olutvarasto: " + olutta);
    }
}
