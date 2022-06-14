package project.motus.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class homeController implements Initializable{
	
	@FXML
	public GridPane gridPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		/*
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				Label l = new Label();
				l.setText(String.valueOf((char) (i + j+j+ 65)));
				l.setAlignment(Pos.CENTER);
				l.setMaxHeight(Double.MAX_VALUE);
				l.setMaxWidth(Double.MAX_VALUE);
				
				if (i==0 && j == 1) {
					l.setStyle("-fx-background-color:#10c320; -fx-background-radius:50px; -fx-font-size: 30px;");
				} else if (i==0 && j == 4) {
					l.setStyle("-fx-background-color:#ffc500; -fx-background-radius:50px; -fx-font-size: 30px;"); 
				} else if (i== 0) {
					l.setStyle("-fx-background-color:#303030; -fx-background-radius:50px;-fx-font-size: 30px;-fx-text-fill: white"); 
				} else {
					l.setStyle("-fx-font-size: 35px; -fx-text-fill: white");
				}
				gridPane.add(l, j, i);
			}
		}*/
	}
}
