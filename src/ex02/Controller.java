package ex02;

import java.net.URL;
import java.util.ResourceBundle;

import ex02.mediaService.MediaService;
import ex02.mediaService.MediaServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class Controller implements Initializable {
	Parent root;
	MediaService ms;
	public void setRoot(Parent root) {
		this.root = root;
		ms.setMedia(root, "../../resources/media/video.m4v");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ms = new MediaServiceImpl();
		
	}
	public void myPlay() {
		System.out.println("시작버튼 클릭");
		ms.myStart();
	}
	public void myPause() {
		ms.myPause();
	}
	public void myStop() {
		ms.myStop();
	}
	public void volumeControll() {
		ms.volumeControll();
	}
}











