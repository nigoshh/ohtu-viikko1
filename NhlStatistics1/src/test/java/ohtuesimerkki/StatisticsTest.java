package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    private Reader readerStub = new Reader() {

        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    private Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchReturnsTheCorrectPlayer() {
        Player p = stats.search("Kurri");
        assertEquals("Kurri", p.getName());
        assertEquals("EDM", p.getTeam());
        assertEquals(37, p.getGoals());
        assertEquals(53, p.getAssists());
    }

    @Test
    public void searchReturnsNullForNonexistentPlayer() {
        Player p = stats.search("Mario");
        assertNull(p);
    }

    @Test
    public void teamReturnsCorrectTeam() {
        List<Player> team = stats.team("DET");
        assertEquals(1, team.size());
        Player p = team.get(0);
        assertEquals("Yzerman", p.getName());
        assertEquals("DET", p.getTeam());
        assertEquals(42, p.getGoals());
        assertEquals(56, p.getAssists());
    }

    @Test
    public void topScorersReturnsCorrectPlayers() {
        List<Player> topScorers = stats.topScorers(2);
        assertEquals(2, topScorers.size());
        Player p1 = topScorers.get(0);
        Player p2 = topScorers.get(1);
        assertEquals("Gretzky", p1.getName());
        assertEquals("Lemieux", p2.getName());
    }
}
