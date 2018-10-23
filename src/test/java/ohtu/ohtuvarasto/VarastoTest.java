package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        setUp();
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        setUp();
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        setUp();
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysPienentaaVapaataTilaa() {
        setUp();
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        setUp();
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        setUp();
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ylimaaraMeneeHukkaan() {
        setUp();
        varasto.lisaaVarastoon(17);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void ottamallaEnemmanKuinSaldoSaadaanSaldo() {
        setUp();
        varasto.lisaaVarastoon(5);
        double saatuMaara = varasto.otaVarastosta(8);
        assertEquals(5, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottamallaEnemmanKuinSaldoSaldoLaskeeNollaksi() {
        setUp();
        varasto.lisaaVarastoon(3);
        varasto.otaVarastosta(7);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiTeeMitaan() {
        setUp();
        varasto.lisaaVarastoon(-4);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttaminenEiTeeMitaan() {
        setUp();
        varasto.otaVarastosta(-9);
        varasto.lisaaVarastoon(6);
        assertEquals(6, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void toStringPalauttaaOikeinFormatoituMerkkijono() {
        setUp();
        varasto.lisaaVarastoon(9);
        System.out.println(varasto);
        assertEquals("saldo = 9.0, vielä tilaa 1.0", varasto.toString());
    }

    @Test
    public void konstruktoritNegatiivisellaTilavuudellaLuovatKayttokelvotonVarasto() {
        varasto = new Varasto(-5);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(-5, 3);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriNegatiivisellaSaldollaAsettaaSaldonNollaksi() {
        varasto = new Varasto(6, -2);
        assertEquals(6, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(-2, -4);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAsettaaSaldonOikeinKunMahtuu() {
        varasto = new Varasto(7, 3);
        assertEquals(7, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(3, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAsettaaSaldonOikeinKunEiMahdu() {
        varasto = new Varasto(9, 21);
        assertEquals(9, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(9, varasto.getSaldo(), vertailuTarkkuus);
    }
}