package sample;

/**
 * Der Originator ist die Klasse die gespeichert wird
 */
class Originator {
    private String state;

    /**
     * Setzt einen String als State
     * @param state Status welcher als State gesetzt werden soll
     */
    public void setState(String state) {
        System.out.println("Originator: Setting state to " + state);
        this.state = state;
    }

    /**
     * Speichert den zuletzt gesetzten State
     * @return Memento Erstellt ein neues Memento Obejkt mit dem gesetzten State und gibt dieses wieder zurück.
     */
    public Memento save() {
        System.out.println("Originator: Saving to Memento.");
        return new Memento(state);
    }

    /**
     * Stellt Mementos die über den Parameter übergeben werden wieder her
     * @param m sagt aus welcher Memento wiederhergestellt werden soll
     */
    public void restore(Memento m) {
        state = m.getState();
        System.out.println("Originator: State after restoring from Memento: " + state);
    }
}