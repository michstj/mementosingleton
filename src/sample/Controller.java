package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

import java.sql.*;



public class Controller {

    @FXML public Button neuerEIntrag;
    @FXML public Button delete;
    @FXML public TextField titel;
    @FXML public TextField autor;
    @FXML public TextArea texteingabe;
    @FXML public TextArea ausgabeState;
    @FXML public ListView table;
    @FXML public ListView table2;
    @FXML public Label ausgabe;
    private int currID;

    Connection myConn = Singleton.getConn();
    Caretaker caretaker = new Caretaker();
    Originator originator = new Originator();


    ObservableList<Model> daten = FXCollections.observableArrayList();
    ObservableList<Model> versionen = FXCollections.observableArrayList();


    /**
     *Erstellt mit den eingegebenen Daten (Titel, Autor, Text) einen neuen Eintrag in der Datenbank
     * @throws SQLException
     */

    public void neuerEintrag() throws SQLException {

        PreparedStatement ps = myConn.prepareStatement("INSERT INTO notizen (id, autor, titel, text) values (?,?,?,?)");
        ps.setNull(1, java.sql.Types.INTEGER);

        ps.setString(2,autor.getText());
        ps.setString(3,titel.getText());
        ps.setString(4,texteingabe.getText());


        if (autor.getText().length() >= 1&& titel.getText().length() >= 1)
        {
            ps.executeUpdate();
        }
        else{
            JOptionPane.showMessageDialog(null, "Bitte Titel und Autor eintragen!", "Fehler:", JOptionPane.INFORMATION_MESSAGE);
        }


        ps.close();

        loadData();

    }

    /**
     * Aktualisiert die ListView mit allen Notizen aus der Datenbank
     * @throws SQLException
     */
    public void loadData() throws SQLException  {


        daten.clear();

                ResultSet resultSet = myConn.createStatement().executeQuery("SELECT * FROM notizen");
                while (resultSet.next()) {
                    daten.add(new Model(resultSet.getInt("id"), resultSet.getString("autor"),resultSet.getString("titel"), resultSet.getString("text")));
                }



        table.getItems().clear();


        for (int i = 0; i < daten.size(); i++) {

            table.getItems().addAll(daten.get(i));
        }
        for (int i = 0; i < versionen.size(); i++) {

            table2.getItems().addAll(caretaker.mementos.get(i));
        }


    }

    /**
     * löscht Notizen aus der Datenbank
     * Zum Löschen werden Autor und Titel mit der Datenbank verglichen
     * @throws SQLException
     */
    public void deleteNotiz() throws SQLException {


        PreparedStatement ps = myConn.prepareStatement("DELETE FROM notizen WHERE autor = ? AND titel = ? AND text = ?");

        ps.setString(1,autor.getText());
        ps.setString(2,titel.getText());
        ps.setString(3,texteingabe.getText());

        if (autor.getText().length() >= 1 && titel.getText().length() >= 1 && texteingabe.getText().length() >= 1)
        {
            ps.executeUpdate();
        }
        else{
            JOptionPane.showMessageDialog(null, "sepp", "InfoBox: " + "soacha", JOptionPane.INFORMATION_MESSAGE);
        }


        ps.close();

        loadData();
    }

    public void updateNotiz() throws SQLException {
        Model model = (Model) table.getSelectionModel().getSelectedItem();
        int id = model.getId();


        PreparedStatement ps = myConn.prepareStatement("UPDATE notizen SET autor = ?, titel = ?, text = ? WHERE id = ?;");

        ps.setString(1,autor.getText());
        ps.setString(2,titel.getText());
        ps.setString(3,texteingabe.getText());
        ps.setInt(4,id);

        if (autor.getText().length() >= 1 && titel.getText().length() >= 1 && texteingabe.getText().length() >= 1)
        {
            ps.executeUpdate();
        }
        else{
            JOptionPane.showMessageDialog(null, "sepp", "InfoBox: " + "soacha", JOptionPane.INFORMATION_MESSAGE);
        }


        ps.close();

        loadData();
    }


    /**
     * Die Daten von dem in der ListView ausgeähltem Objekt werden hier wieder in die zugehörigen input-Felder geschoben und können wieder bearbeitet werden.
     */
    public void selected(){
        Model model = (Model) table.getSelectionModel().getSelectedItem();
        titel.setText(""+model.getTitel());
        autor.setText(""+model.getAutor());
        texteingabe.setText(""+model.getText());
        currID = model.getId();

    }

    /**
     *Setzt den State von einem Objekt (Wird noch nicht gespeichert)
     */

    public void stateSetzen(){
        originator.setState(texteingabe.getText());
    }

    /**
     * Speichert den zuletzt gesetzten State im Caretaker
     */
    public void stateSpeichern(){
        caretaker.addMemento(originator.save());
        ausgabeState.setText(texteingabe.getText());
    }

    /**
     * Ruft die Funktion restore aus dem Originator auf und setzt den Text der TextArea dem Memento gleich.
     */
    public void stateRestore(){
        originator.restore( caretaker.getMemento() );
        texteingabe.setText(caretaker.getMemento().toString());
    }


}
