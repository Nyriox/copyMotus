package project.motus;

import java.io.IOException;

public class TestMotus {

	public static void main(String[] args) throws IOException {
		Motus motus = new Motus();
		
		
		motus.check("banane");
		motus.check("aaneat");
		motus.choiceWord();
		System.out.println(motus.getCurrentWord());
	}

}
