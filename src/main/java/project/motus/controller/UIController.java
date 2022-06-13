package project.motus.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import project.motus.App;
import project.motus.Motus;

public class UIController implements Initializable {
	@FXML
	private Button homeButton;

	@FXML
	private FontAwesomeIconView homeIcone;

	@FXML
	private Button joueurButton;

	@FXML
	private FontAwesomeIconView joueurIcone;

	@FXML
	private Button leaderboardButton;

	@FXML
	private FontAwesomeIconView leaderboardIcone;

	@FXML
	private Button statButton;

	@FXML
	private FontAwesomeIconView statIcone;

	private String currentWindow = "home";

	@FXML
	private Pane currentPane;

	private Stage stage;

	private boolean enJeu = true;

	public UIController(Stage stage) {
		this.stage = stage;
	}

	private String currantWord = "";
	private int step = 0;

	public GridPane gridPane;

	private String[][] plateau = new String[6][6];
	private String[][] plateauCheck = new String[6][6];

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Parent fxml = null;
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("home.fxml"));
			fxml = fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("je rentre dans le catch, Youpi !");
			e.printStackTrace();
		}
		Motus motus = new Motus();

		currentPane.getChildren().removeAll();
		currentPane.getChildren().setAll(fxml);

		gridPane = ((GridPane) fxml.getChildrenUnmodifiable().get(0));

		stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (enJeu) {
				if (event.getCode() == KeyCode.BACK_SPACE) {
					if (currantWord.length() > 0) {
						this.currantWord = (String) this.currantWord.subSequence(0, this.currantWord.length() - 1);
					}
				}
				if (97 <= (int) ((char) event.getText().charAt(0))
						&& (int) ((char) event.getText().charAt(0)) <= 97 + 25) {
					this.currantWord += event.getText().charAt(0);
				}

				Node node = gridPane.getChildren().get(0);
				gridPane.getChildren().clear();
				gridPane.getChildren().add(0, node);

				for (int i = 0; i < this.step; i++) {
					for (int j = 0; j < 6; j++) {
						Label l = new Label();
						l.setText(plateau[i][j]);
						l.setAlignment(Pos.CENTER);
						l.setMaxHeight(Double.MAX_VALUE);
						l.setMaxWidth(Double.MAX_VALUE);
						if (plateauCheck[i][j].equals("T")) {
							l.setStyle(
									"-fx-background-color:#10c320; -fx-background-radius:50px; -fx-font-size: 30px;-fx-border-style: none none dotted none; -fx-border-color: black;");
						} else if (plateauCheck[i][j].equals("A")) {
							l.setStyle(
									"-fx-background-color:#ffc500; -fx-background-radius:50px; -fx-font-size: 30px;-fx-border-style: none none dotted none; -fx-border-color: black;");
						} else {
							l.setStyle(
									"-fx-background-color:#303030; -fx-background-radius:50px;-fx-font-size: 30px;-fx-text-fill: white;-fx-border-style: none none dotted none; -fx-border-color: black;");
						}
						gridPane.add(l, j, i);
					}
				}

				for (int i = 0; i < 6; i++) {
					if (i < this.currantWord.length()) {
						Label l = new Label();
						l.setText(String.valueOf(this.currantWord.charAt(i)));
						l.setAlignment(Pos.CENTER);
						l.setMaxHeight(Double.MAX_VALUE);
						l.setMaxWidth(Double.MAX_VALUE);
						l.setStyle(
								"-fx-font-size: 35px; -fx-text-fill: white; fx-border-style: none none dotted none; -fx-border-color: black;");
						gridPane.add(l, i, this.step);
					} else {
						Label l = new Label();
						l.setMaxHeight(Double.MAX_VALUE);
						l.setMaxWidth(Double.MAX_VALUE);
						l.setStyle("-fx-border-style: none none dotted none; -fx-border-color: black;");
						gridPane.add(l, i, this.step);
					}
				}

				if (this.currantWord.length() == 6) {
					String[] reponse = motus.check(this.currantWord);
					boolean isEnd = true;
					for (int i = 0; i < 6; i++) {
						plateau[this.step][i] = String.valueOf(this.currantWord.charAt(i));
						plateauCheck[this.step][i] = reponse[i];
						if (!reponse[i].equals("T")) {
							isEnd = false;
						}
					}
					

					for (int i = 0; i < this.step + 1; i++) {
						for (int j = 0; j < 6; j++) {
							Label l = new Label();
							l.setText(plateau[i][j]);
							l.setAlignment(Pos.CENTER);
							l.setMaxHeight(Double.MAX_VALUE);
							l.setMaxWidth(Double.MAX_VALUE);
							if (plateauCheck[i][j].equals("T")) {
								l.setStyle(
										"-fx-background-color:#10c320; -fx-background-radius:50px; -fx-font-size: 30px; -fx-border-style: none none dotted none; -fx-border-color: black;");
							} else if (plateauCheck[i][j].equals("A")) {
								l.setStyle(
										"-fx-background-color:#ffc500; -fx-background-radius:50px; -fx-font-size: 30px;-fx-border-style: none none dotted none; -fx-border-color: black;");
							} else {
								l.setStyle(
										"-fx-background-color:#303030; -fx-background-radius:50px;-fx-font-size: 30px;-fx-text-fill: white;-fx-border-style: none none dotted none; -fx-border-color: black;");
							}
							gridPane.add(l, j, i);
						}
					}
					currantWord = "";
					this.step++;
					
					if (isEnd || this.step == 6) {
						if (!isEnd) {
							System.out.println("Ta perdu, t'es pas ouf");
							System.out.println("Le mot était : " + motus.getCurrentWord());
						} else {
							System.out.println("Point faible : Trop fort !");
						}
						enJeu=false;
					} else {
						for (int j = 0; j < 6; j++) {
							Label l = new Label();
							l.setMaxHeight(Double.MAX_VALUE);
							l.setMaxWidth(Double.MAX_VALUE);
							l.setStyle("-fx-border-style: none none dotted none; -fx-border-color: black;");
							gridPane.add(l, j, this.step);
						}
					}
					
					
					
					
				}

				// System.out.println(this.currantWord);
			}
		});

	}

	@FXML
	public void joueurPress(ActionEvent e) throws IOException {
		if (currentWindow.equals("home")) {
			homeButton.getStyleClass().clear();
			homeButton.getStyleClass().add("main-btns");

			homeIcone.getStyleClass().clear();
			homeIcone.getStyleClass().add("icons");
		} else if (currentWindow.equals("leaderboard")) {
			leaderboardButton.getStyleClass().clear();
			leaderboardButton.getStyleClass().add("main-btns");

			leaderboardIcone.getStyleClass().clear();
			leaderboardIcone.getStyleClass().add("icons");
		} else if (currentWindow.equals("stat")) {
			statButton.getStyleClass().clear();
			statButton.getStyleClass().add("main-btns");

			statIcone.getStyleClass().clear();
			statIcone.getStyleClass().add("icons");
		}
		currentWindow = "joueur";
		joueurButton.getStyleClass().clear();
		joueurButton.getStyleClass().add("main-btns-selected");

		joueurIcone.getStyleClass().clear();
		joueurIcone.getStyleClass().add("selected");
	}

	@FXML
	public void leaderBoardPress(ActionEvent e) {
		if (currentWindow.equals("home")) {
			homeButton.getStyleClass().clear();
			homeButton.getStyleClass().add("main-btns");

			homeIcone.getStyleClass().clear();
			homeIcone.getStyleClass().add("icons");
		} else if (currentWindow.equals("joueur")) {
			joueurButton.getStyleClass().clear();
			joueurButton.getStyleClass().add("main-btns");

			joueurIcone.getStyleClass().clear();
			joueurIcone.getStyleClass().add("icons");
		} else if (currentWindow.equals("stat")) {
			statButton.getStyleClass().clear();
			statButton.getStyleClass().add("main-btns");

			statIcone.getStyleClass().clear();
			statIcone.getStyleClass().add("icons");
		}
		currentWindow = "leaderboard";
		leaderboardButton.getStyleClass().clear();
		leaderboardButton.getStyleClass().add("main-btns-selected");

		leaderboardIcone.getStyleClass().clear();
		leaderboardIcone.getStyleClass().add("selected");

	}

	@FXML
	public void statPress(ActionEvent e) {

		if (currentWindow.equals("home")) {
			homeButton.getStyleClass().clear();
			homeButton.getStyleClass().add("main-btns");

			homeIcone.getStyleClass().clear();
			homeIcone.getStyleClass().add("icons");
		} else if (currentWindow.equals("joueur")) {
			joueurButton.getStyleClass().clear();
			joueurButton.getStyleClass().add("main-btns");

			joueurIcone.getStyleClass().clear();
			joueurIcone.getStyleClass().add("icons");
		} else if (currentWindow.equals("leaderboard")) {
			leaderboardButton.getStyleClass().clear();
			leaderboardButton.getStyleClass().add("main-btns");

			leaderboardIcone.getStyleClass().clear();
			leaderboardIcone.getStyleClass().add("icons");
		}

		currentWindow = "stat";

		statButton.getStyleClass().clear();
		statButton.getStyleClass().add("main-btns-selected");

		statIcone.getStyleClass().clear();
		statIcone.getStyleClass().add("selected");
	}

	@FXML
	public void homePress(ActionEvent e) {

		if (currentWindow.equals("leaderboard")) {
			leaderboardButton.getStyleClass().clear();
			leaderboardButton.getStyleClass().add("main-btns");

			leaderboardIcone.getStyleClass().clear();
			leaderboardIcone.getStyleClass().add("icons");

		} else if (currentWindow.equals("joueur")) {
			joueurButton.getStyleClass().clear();
			joueurButton.getStyleClass().add("main-btns");

			joueurIcone.getStyleClass().clear();
			joueurIcone.getStyleClass().add("icons");
		} else if (currentWindow.equals("stat")) {
			statButton.getStyleClass().clear();
			statButton.getStyleClass().add("main-btns");

			statIcone.getStyleClass().clear();
			statIcone.getStyleClass().add("icons");
		}

		currentWindow = "home";

		homeButton.getStyleClass().clear();
		homeButton.getStyleClass().add("main-btns-selected");

		homeIcone.getStyleClass().clear();
		homeIcone.getStyleClass().add("selected");
	}

}
