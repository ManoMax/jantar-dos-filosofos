import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class jantarComSemaforo extends jantar {

    private Semaphore mutex;

    public jantarComSemaforo(int totalFilosofos) {
        super();
        this.mutex = new Semaphore(1);
        this.totalFilosofos = totalFilosofos;
        this.estados = new estadosFilosofo[this.totalFilosofos];
        this.filosofos = new Semaphore[this.totalFilosofos];

        for (int i = 0; i < this.totalFilosofos; i++) {
            this.estados[i] = estadosFilosofo.PENSANDO;
            this.filosofos[i] = new Semaphore(0);
        }
        System.out.println(Arrays.toString(estados));
    }

    public void pegarTalher(int idFilosofo) {
        try {
            mutex.acquire();//DOWN
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        estados[idFilosofo] = estadosFilosofo.COM_FOME;
        if (podeComer(idFilosofo)) {
            ((Semaphore)filosofos[idFilosofo]).release(); //UP
            estados[idFilosofo] = estadosFilosofo.COMENDO;
        }
        mutex.release(); //UP
        try {
            ((Semaphore)filosofos[idFilosofo]).acquire(); //DOWN
            System.out.println(Arrays.toString(estados));
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
    }

    public void devolveTalher(int idFilosofo) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        System.out.println(Arrays.toString(estados));
        estados[idFilosofo] = estadosFilosofo.PENSANDO;
        if (pegarEstadoDireita(idFilosofo) == estadosFilosofo.COM_FOME &&
                pegarEstadoDireita(pegarDireita(idFilosofo)) != estadosFilosofo.COMENDO) {
            estados[pegarDireita(idFilosofo)] = estadosFilosofo.COMENDO;
            ((Semaphore)filosofos[pegarDireita(idFilosofo)]).release();
        }
        if (pegarEstadoEsquerda(idFilosofo) == estadosFilosofo.COM_FOME &&
                pegarEstadoEsquerda(pegarEsquerda(idFilosofo)) != estadosFilosofo.COMENDO) {
            estados[pegarEsquerda(idFilosofo)] = estadosFilosofo.COMENDO;
            ((Semaphore)filosofos[pegarEsquerda(idFilosofo)]).release();
        }
        mutex.release();
    }

}

