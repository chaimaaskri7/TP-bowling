package bowling;

public class Tour {
    protected int lancer1 = -1;
    protected int lancer2 = -1;

    public void ajouterLancer(int quilles) {
        if (lancer1 == -1) {
            lancer1 = quilles;
        } else if (lancer2 == -1) {
            lancer2 = quilles;
        }
    }

    public boolean estComplet() {
        return estStrike() || (lancer1 != -1 && lancer2 != -1);
    }

    public boolean estStrike() {
        return lancer1 == 10;
    }

    public boolean estSpare() {
        return lancer1 != -1 && lancer2 != -1 && lancer1 + lancer2 == 10 && !estStrike();
    }

    public int getScoreDeBase() {
        int s1 = (lancer1 == -1) ? 0 : lancer1;
        int s2 = (lancer2 == -1) ? 0 : lancer2;
        return s1 + s2;
    }

    public int getLancer1() {
        return (lancer1 == -1) ? 0 : lancer1;
    }

    public int getLancer2() {
        return (lancer2 == -1) ? 0 : lancer2;
    }
}
