package ex01;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller implements Initializable{
	@FXML ListView<String> fxListView;
	@FXML ImageView fxImageView;
	ObservableList<String> phoneString;
	String url = "resources/img/phone/phone0";
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.setListView();
		fxListView.getSelectionModel().selectedIndexProperty().addListener(
				(obj,oldValue,newValue)->{
					System.out.println(obj); 
					System.out.println(oldValue); //이전 선택
					System.out.println(newValue); //지금 선택
					System.out.println("선택 값 : " +phoneString.get((int) newValue));
					fxImageView.setImage(new Image(url+((int) newValue+1)+".png"));

				});
		
	}
	private void setListView() {
		fxImageView.setImage(new Image(url+1+".png"));
		phoneString = FXCollections.observableArrayList();
		for(int i =1;i<8;i++) {
			phoneString.add("갤럭시S"+i);
		}
		fxListView.setItems(phoneString);
	}

}










