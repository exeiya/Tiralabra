package domain;

public class MinHeap<E extends Comparable<E>> {

    private Object L[];
    private int last;
    private int size;

    public MinHeap() {
        L = new Object[1000];
        last = 0;
        size = 1000;
    }

    /**
     * Lisää uuden elementin kekoon
     *
     * @param element lisättävä alkio
     */
    public void add(Object element) {
        last++;
        L[last] = element;
        heapifyUp(last);
    }

    /**
     * Suorittaa keon korjauksen halutusta alkiosta ylöspäin
     *
     * @param i alkio, josta ylöspäin korjaus suoritetaan
     */
    private void heapifyUp(int i) {
        int smallest = i;
        while (i > 1) {
            int parent = i / 2;
            if (compare(L[i], L[parent]) >= 0) {
                break;
            } else {
                smallest = parent;
                Object temp = L[i];
                L[i] = L[parent];
                L[parent] = temp;
                heapifyUp(smallest);
            }
        }
    }

    /**
     * Hakee keon juurialkion, eli pienimmän alkion ja palauttaa sen sekä
     * poistaa keosta. Juureksi laitetaan listan viimeinen alkio ja siitä
     * lähtien suoritetaan keon korjaava metodi
     *
     * @return juurialkio, eli keon pienin alkio
     */
    public Object pollMin() {
        Object min = L[1];
        L[1] = L[last];
        last--;
        heapifyDown(1);
        return min;
    }

    /**
     * Suorittaa keon korjauksen annetusta alkiosta alaspäin
     *
     * @param i alkio, josta lähtien korjaus suoritetaan
     */
    private void heapifyDown(int i) {
        int leftc = 2 * i;
        int rightc = 2 * i + 1;
        int smallest = i;
        if ((leftc <= last) && (compare(L[leftc], L[smallest]) < 0)) {
            smallest = leftc;
        }
        if ((rightc <= last) && (compare(L[rightc], L[smallest]) < 0)) {
            smallest = rightc;
        }
        if (smallest != i) {
            Object temp = L[i];
            L[i] = L[smallest];
            L[smallest] = temp;
            heapifyDown(smallest);
        }
    }

    /**
     * Poistaa halutun kohteen keosta
     *
     * @param element poistettava elementti
     */
    public void remove(Object element) {
        for (int i = 1; i <= last; i++) {
            if (L[i] == element) {
                L[i] = L[last];
                last--;
                if (last != 0){
                if (compare(L[i], L[(i / 2)]) >= 0) {
                    heapifyDown(i);
                } else {
                    heapifyUp(i);
                }
                break;
                }
            }

        }
    }

    /**
     * Vertaa annettuja alkiota toisiinsa compareTo-metodilla
     *
     * @param n1
     * @param n2
     * @return
     */
    private int compare(Object n1, Object n2) {
        return ((E) n1).compareTo((E) n2);
    }

    /**
     * Tarkistaa, onko listamuuttuja tyhjä
     *
     * @return
     */
    public boolean isEmpty() {
        return (this.last == 0);
    }

    public void tulosta() {
        for (int i = 0; i <= last; i++) {
            System.out.print(L[i] + ",");
        }
        System.out.println("");
        //System.out.println("END");
    }
    
    public int getLast(){
        return last;
    }

}
