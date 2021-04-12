public class filosofo implements Runnable {
    private int id;
    private int duracaoPensamento;
    private int duracaoComer;
    private jantar jantar;
    String type;

    public filosofo(int id, int duracaoPensamento , int duracaoComer, jantar jantar) {
        this.id = id;
        this.duracaoPensamento = duracaoPensamento;
        this.duracaoComer = duracaoComer;
        this.jantar = jantar;
        new Thread((Runnable)this, "filosofo" + id).start();
    }

    @Override
    public void run() {
        while(true) {
            pensar();
            pegarTalher();
            comer();
            devolveTalher();
        }
    }

    private void pensar () {
        try {
            Thread.sleep(this.duracaoPensamento);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pegarTalher () {
        jantar.pegarTalher(this.id);

    }

    private void comer () {
        try {
            Thread.sleep(this.duracaoComer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void devolveTalher () {
        jantar.devolveTalher(this.id);
    }
}
