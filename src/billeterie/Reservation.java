package billeterie;


import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.ButtonGroup;


public class Reservation {
	//Attributs 
	private String nom;
	private String prenom;
	private String mail;
	private String concert;
	private String place;
	

	//Constructeur
	public Reservation(JTextField nom, JTextField prenom, JTextField mail, JComboBox<String> concert, ButtonGroup place) throws FormulaireException{
		if (nom.getText().equals("") || prenom.getText().equals("") || mail.getText().equals("")) {
			throw new FormulaireException();
		} else {
			this.setNom(nom.getText());
			this.setPrenom(prenom.getText());
			this.setMail(mail.getText());
			this.setConcert(concert.getSelectedItem().toString());
			this.setPlace(place.getSelection().getActionCommand());

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
			nom.setText(null);
			prenom.setText(null);
			mail.setText(null);
			//JOptionPane jop1 = new JOptionPane();
			JOptionPane.showMessageDialog(null, "Vous avez bien réservé une place pour "+this.getConcert(), "Information", JOptionPane.INFORMATION_MESSAGE);
		}
		}
	//Les getter
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
	
	//Les setter
	public void setNom(String n) {
		this.nom=n;
	}
	public void setPrenom(String p) {
		this.prenom=p;
	}
	public void setMail(String m) {
		this.mail=m;
	}
	public void setConcert(String c) {
		this.concert=c;
	}
	public void setPlace(String p) {
		this.place=p;
	}
}

