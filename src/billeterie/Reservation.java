package billeterie;


import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.ButtonGroup;


public class Reservation {
	private String nom;
	private String prenom;
	private String mail;
	private String concert;
	private String place;
	
	public String getNom() {
		return this.nom;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public String getMail() {
		return this.mail;
	}
	public String getConcert() {
		return this.concert;
	}
	public String getPlace() {
		return this.place;
	}
	public Reservation(JTextField nom, JTextField prenom, JTextField mail, JComboBox<String> concert,ButtonGroup place) throws FormulaireException{
		if (nom.getText().equals("") || prenom.getText().equals("") || mail.getText().equals("")) {
			throw new FormulaireException();
		} else {
			this.nom=nom.getText();
			this.prenom=prenom.getText();
			this.mail=mail.getText();
			this.concert=concert.getSelectedItem().toString();
			this.place=place.getSelection().getActionCommand();

			try {
				//Création d'une instance de l'objet Driver dans le fichier .jar qu'on a téléchargé
				//Un "pont" est crée entre notre BDD et Java mais il faut faire la connexion
				Class.forName("org.postgresql.Driver");
				System.out.println("Driver O.K.");

				//On fait la connexion : 
				String url = "jdbc:postgresql://localhost:5432/Utilisateurs";
				String user = "postgres";
				String passwd = "postgres";

				Connection conn = DriverManager.getConnection(url, user, passwd);
				System.out.println("Connexion effective !"); 

				//Création d'un objet Statement
				java.sql.Statement state = conn.createStatement();
				String query = "INSERT INTO spec VALUES ('"+this.getNom()+"','"+this.getPrenom()+"','"+this.getMail()+"','"+this.getConcert()+"','"+this.getPlace()+"')";
				state.executeUpdate(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		}
}

