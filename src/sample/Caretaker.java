package sample;
import java.util.ArrayList;

/**
 * Speichert alle Mementos in einer ArrayList vom Typ Memento
 */
class Caretaker {
    public ArrayList<Memento> mementos = new ArrayList<>();

    /**
     * Fügt der ArrayList ein übergebenes Memento hinzu
    @param  m  übergibt Memento-Objekt, welches gespeichert werden soll
    */
    public void addMemento(Memento m) {
        mementos.add(m);
    }


    /**
    @return  liefert das letzte Memento-Objekt in der Arraylist zurück
    */
    public Memento getMemento() {
        return mementos.get(mementos.size()-1);
    }
}