package sample;

/**
 * Die Objekte der Klasse Memento speichern Strings damit sie später wiederverwendet werden können
 */
class Memento {
    private String state;

    /**
     * Konstruktor zum Erzeugen von Memento Objekten
     * @param state String der gesetzt werden soll
     */
    public Memento(String state) {
        this.state = state;
    }

    /**
     * Getter um State vom Objekt zu holen
     * @return liefert String state
     */
    public String getState() {
        return state;
    }

    /**
     * @return Liefert den Zustand wieder als String zurück
     */
    @Override
    public String toString() {
        return ""+state;
    }
}
