package bowling;

public class TourFinal extends Tour {

    private int troisiemeLancer = -1;

    @Override
    public boolean estTermine() {
        if (premierLancer == -1 || secondLancer == -1)
            return false;

        if (estStrike() || estSpare())
            return troisiemeLancer != -1;

        return true;
    }

    @Override
    public boolean enregistrerLancer(int quilles) {
        if (premierLancer == -1) {
            premierLancer = quilles;
            return !estTermine();
        }

        if (secondLancer == -1) {
            secondLancer = quilles;
            return !estTermine();
        }

        if ((estStrike() || estSpare()) && troisiemeLancer == -1) {
            troisiemeLancer = quilles;
            return !estTermine();
        }

        return false;
    }

    public int[] getLancers() {
        int l1 = Math.max(premierLancer, 0);
        int l2 = Math.max(secondLancer, 0);
        int l3 = Math.max(troisiemeLancer, 0);
        return new int[] { l1, l2, l3 };
    }
}package bowling;

public class TourFinal extends Tour {

    private int troisiemeLancer = -1;

    @Override
    public boolean estTermine() {
        if (premierLancer == -1 || secondLancer == -1)
            return false;

        if (estStrike() || estSpare())
            return troisiemeLancer != -1;

        return true;
    }

    @Override
    public boolean enregistrerLancer(int quilles) {
        if (premierLancer == -1) {
            premierLancer = quilles;
            return !estTermine();
        }

        if (secondLancer == -1) {
            secondLancer = quilles;
            return !estTermine();
        }

        if ((estStrike() || estSpare()) && troisiemeLancer == -1) {
            troisiemeLancer = quilles;
            return !estTermine();
        }

        return false;
    }

    public int[] getLancers() {
        int l1 = Math.max(premierLancer, 0);
        int l2 = Math.max(secondLancer, 0);
        int l3 = Math.max(troisiemeLancer, 0);
        return new int[] { l1, l2, l3 };
    }
}