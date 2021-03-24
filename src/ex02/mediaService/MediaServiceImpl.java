package ex02.mediaService;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MediaServiceImpl implements MediaService{
	MediaPlayer mediaPlayer;
	MediaView mediaView;
	Button btnPlay, btnPause ,btnStop;
	
	Label labelTime;
	ProgressBar progressBar;
	ProgressIndicator progressIndicator;
	Slider slider;
	
	public void volumeControll() {
		//System.out.println(slider.getValue()/100.0);//슬라이더 이동 값
		mediaPlayer.setVolume(slider.getValue()/100.0);
	}
	
	@Override
	public void myStart() {
		//시작버튼 연동, 여기서 플레이
		mediaPlayer.setVolume(0.1);//시작볼륨
		slider.setValue(10.0);//슬라이더 위치
		mediaPlayer.play(); //동영상 시작
	}

	@Override
	public void myPause() {
		mediaPlayer.pause();//일시중지
	}

	@Override
	public void myStop() {//끄기
		mediaPlayer.stop();
		
	}
	

	@Override
	public void setMedia(Parent root, String mediaName) {
		setController(root);
		Media media = new Media(getClass().getResource(mediaName).toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		//mediaView 화면에 보여주는 역할
		//버튼 활성화 비활성화 메소드 생성 이벤트 처리
		//mediaPlayer 동작에 관련된 역할을 해준다
		mediaPlayer.setOnReady(()->{
			//준비단계에는 플레이 버튼만 활성화, 나머지는 비활성화 
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
			
			
			mediaPlayer.currentTimeProperty().addListener((obj,oldValue,newVelue)->{
				double progress = 
						//흐르는 시간 //총 시간
				mediaPlayer.getCurrentTime().toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
				progressBar.setProgress(progress);
				progressIndicator.setProgress(progress);
				labelTime.setText((int)mediaPlayer.getCurrentTime().toSeconds()+" / "+
						(int)mediaPlayer.getTotalDuration().toSeconds()+" sec");
			});
		});
		mediaPlayer.setOnPlaying(()->{//실행 중 이면
			btnPlay.setDisable(true);
			btnPause.setDisable(false);
			btnStop.setDisable(false);
		});
		mediaPlayer.setOnPaused(()->{//일시중지 중 이면
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(false);
		});
		mediaPlayer.setOnEndOfMedia(()->{//미디어가 종료되었다면
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
			
			mediaPlayer.stop(); // 스탑하면 미디어가 처음으로 돌아간다.
			//mediaPlayer.seek(mediaPlayer.getStartTime());//미디어를 처음으로 돌려라
		});
		mediaPlayer.setOnStopped(()->{
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
		});
		
	}

	public void setController(Parent root) {
		mediaView = (MediaView) root.lookup("#fxMediaView");
		btnPlay = (Button) root.lookup("#btnPlay");
		btnPause = (Button) root.lookup("#btnPause");
		btnStop = (Button) root.lookup("#btnStop");
		
		labelTime =(Label)root.lookup("#labelTime");
		progressBar = (ProgressBar)root.lookup("#progressBar");
		progressIndicator =(ProgressIndicator)root.lookup("#progressIndicator");
		slider = (Slider)root.lookup("#slider");
	}
	

}








