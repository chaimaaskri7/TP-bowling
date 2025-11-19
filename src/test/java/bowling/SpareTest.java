import static org.junit.Assert.*;
import org.junit.Test;

public class SpareTest {

    @Test
    public void testEstSpare() {
        Spare spare = new Spare();
        spare.ajouterLancer(6);
        spare.ajouterLancer(4);

        assertTrue("Un lancer 6 + 4 doit être un spare", spare.estSpare());
    }

    @Test
    public void testScoreDeBase() {
        Spare spare = new Spare();
        spare.ajouterLancer(7);
        spare.ajouterLancer(3);

        assertEquals(10, spare.getScoreDeBase());
    }

    @Test
    public void testPasSpareSiStrike() {
        Spare spare = new Spare();
        spare.ajouterLancer(10);

        assertFalse("Un strike n'est pas un spare", spare.estSpare());
    }
}
