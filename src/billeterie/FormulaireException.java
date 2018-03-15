package billeterie;

public class FormulaireException extends Exception {
	public FormulaireException() {
		System.out.println("Vous n'avez pas rempli tous les champs.");
	}
}
