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
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2=new Varasto(10,10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudenVarastonTilavuusPositiivinen() {
        Varasto nega = new Varasto(-20);
        assertEquals(0, nega.getTilavuus(), vertailuTarkkuus);
    }
    
     @Test 
    public void toinenKonstruktoriLuoSaldon() {
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test 
    public void toinenKonstruktoriTilavuus() {
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test 
    public void tilavuusEiVoiOllaNegatiivinen() {
        Varasto varasto3 = new Varasto(-10, 10);
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldoOnPositiivinen() {
        Varasto saldo= new Varasto(10, -10);
        assertEquals(0, saldo.getSaldo(), vertailuTarkkuus);
    }
            
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisätäYliTilavuuden() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisäysEiVaikuta() {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoEiVaikuta() {
        varasto.otaVarastosta(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    
    @Test
    public void eiVoidaOttaaLiikaaVarastosta() {
        double saldo=varasto2.getSaldo();
        assertEquals(saldo, varasto2.otaVarastosta(15), vertailuTarkkuus);
    }
    
    @Test public void toStringTest()  {
        assertTrue(varasto2.toString().contains("vielä tilaa"));
    }

    /*@Test public void () {
        assertEquals(vertailuTarkkuus);
    }*/
}