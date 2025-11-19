package bowling;

public class Strike extends Tour {

    public Strike() {
        this.lancer1 = 10;
        this.lancer2 = 0;
    }

    @Override
    public boolean estComplet() {
        return true;
    }

    @Override
    public boolean estStrike() {
        return true;
    }

    @Override
    public boolean estSpare() {
        return false;
    }

    @Override
    public int getScoreDeBase() {
        return 10;
    }
}
