import java.util.Arrays;

public class jantarComMonitor extends jantar{
    private Object[] filosofos;


    public jantarComMonitor (int totalFilosofos) {
        super();
        this.totalFilosofos = totalFilosofos;
        this.estados = new estadosFilosofo[this.totalFilosofos];
        this.filosofos = new Object[this.totalFilosofos];

        for(int i = 0; i < this.totalFilosofos; i++) {
            this.estados[i] = estadosFilosofo.PENSANDO;
            this.filosofos[i] = new Object();
        }
        System.out.println(Arrays.toString(estados));
    }

    public void pegarTalher (int idFilosofo) {
        estados[idFilosofo] = estadosFilosofo.COM_FOME;
        synchronized (filosofos[idFilosofo]){
            if (podeComer(idFilosofo)) {
                estados[idFilosofo] = estadosFilosofo.COMENDO;
            }else{
                try{
                    this.filosofos[idFilosofo].wait();
                }
                catch(InterruptedException e){
                    System.out.println("Interruped Exeception");
                }
            }
            System.out.println(Arrays.toString(estados));
        }
    }

    public void devolveTalher (int idFilosofo) {
        estados[idFilosofo] = estadosFilosofo.PENSANDO;
        System.out.println(Arrays.toString(estados));

        if (pegarEstadoDireita(idFilosofo) == estadosFilosofo.COM_FOME &&
                pegarEstadoDireita(pegarDireita(idFilosofo)) != estadosFilosofo.COMENDO) {
            estados[pegarDireita(idFilosofo)] = estadosFilosofo.COMENDO;
            synchronized (filosofos[pegarDireita(idFilosofo)]){
                filosofos[pegarDireita(idFilosofo)].notify();
            }
        }

        if (pegarEstadoEsquerda(idFilosofo) == estadosFilosofo.COM_FOME &&
                pegarEstadoEsquerda(pegarEsquerda(idFilosofo)) != estadosFilosofo.COMENDO) {
            estados[pegarEsquerda(idFilosofo)] = estadosFilosofo.COMENDO;
            synchronized (filosofos[pegarEsquerda(idFilosofo)]){
                filosofos[pegarEsquerda(idFilosofo)].notify();
            }
        }

    }
}

