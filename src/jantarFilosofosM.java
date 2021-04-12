public class jantarFilosofosM {
    private static final int NUMERO_DE_FILOSOFOS = 6;
    private static final int[] DURACAO_DE_COMER = {1000,1000,1000,1000,1000,1000};
    private static final int[] DURACAO_DE_PENSAR = {1000,1000,1000,1000,1000,1000};

    public static void main(String[] args) {

        jantarComMonitor jantar = new jantarComMonitor(NUMERO_DE_FILOSOFOS);

        for (int i = 0; i < NUMERO_DE_FILOSOFOS; i++) {
            new filosofo(i, DURACAO_DE_COMER[i], DURACAO_DE_PENSAR[i], jantar);
        }
    }
}
