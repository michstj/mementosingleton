package sample;

/**
 * Klasse zum Erstellen von Notizobjekten
 */
public class Model
{
    int id;
    String titel;
    String autor;
    String text;


    /**
     * Konstruktor mit Parametern zum erstellen einer Notiz
     * @param id ID die in der Datenbank hinterlegt werden soll
     * @param autor Name des Autors der Notiz
     * @param titel Titel der Notiz
     * @param text TextInhalt der Notiz
     */
    public Model(int id, String autor, String titel, String text) {
        this.id = id;
        this.titel = titel;
        this.autor = autor;
        this.text = text;
    }

    /**
     * Getter für ID
     * @return id liefert die ID
     */
    public int getId() {
        return id;
    }

    /**
     * Setter für ID
     * @param id setzt die ID fürs Objekt
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter für Titel
     * @return Titel der Notiz
     */
    public String getTitel() {
        return titel;
    }

    /**
     * Setter für Titel
     * @param titel String mit Titel
     */
    public void setTitel(String titel) {
        this.titel = titel;
    }

    /**
     * Getter für Autor
     * @return Name des Autors
     */

    public String getAutor() {
        return autor;
    }

    /**
     * Setter für Autor
     * @param autor Name des Autors
     */

    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Getter des Textinhaltes
     * @return Textinhalt
     */

    public String getText() {
        return text;
    }

    /**
     * Setter für Textinhalt
     * @param text setzt den Inhalt des Textes welcher in das Notizobjekt gespeichert wird
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return titel + " | " + autor;
    }
}