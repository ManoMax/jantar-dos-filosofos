public abstract class jantar {
    protected int totalFilosofos;
    protected estadosFilosofo[] estados;
    protected Object[] filosofos;

    protected boolean podeComer (int idFilosofo) {
        return (pegarEstadoDireita(idFilosofo) != estadosFilosofo.COMENDO &&
                pegarEstadoEsquerda(idFilosofo) != estadosFilosofo.COMENDO);
    }
    protected void pegarTalher(int idFilosofo){}

    protected void devolveTalher(int idFilosofo){}


    protected estadosFilosofo pegarEstadoDireita (int idFilosofo) {
        return estados[pegarDireita(idFilosofo)];
    }

    protected int pegarDireita (int position) {
        return (position + 1) % totalFilosofos;
    }

    protected estadosFilosofo pegarEstadoEsquerda (int idFilosofo) {
        return estados[pegarEsquerda((idFilosofo))];
    }

    protected int pegarEsquerda (int position) {
        return (position + totalFilosofos - 1) % totalFilosofos;
    }
}
