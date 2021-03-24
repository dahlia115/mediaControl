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
		//System.out.println(slider.getValue()/100.0);//�����̴� �̵� ��
		mediaPlayer.setVolume(slider.getValue()/100.0);
	}
	
	@Override
	public void myStart() {
		//���۹�ư ����, ���⼭ �÷���
		mediaPlayer.setVolume(0.1);//���ۺ���
		slider.setValue(10.0);//�����̴� ��ġ
		mediaPlayer.play(); //������ ����
	}

	@Override
	public void myPause() {
		mediaPlayer.pause();//�Ͻ�����
	}

	@Override
	public void myStop() {//����
		mediaPlayer.stop();
		
	}
	

	@Override
	public void setMedia(Parent root, String mediaName) {
		setController(root);
		Media media = new Media(getClass().getResource(mediaName).toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		//mediaView ȭ�鿡 �����ִ� ����
		//��ư Ȱ��ȭ ��Ȱ��ȭ �޼ҵ� ���� �̺�Ʈ ó��
		//mediaPlayer ���ۿ� ���õ� ������ ���ش�
		mediaPlayer.setOnReady(()->{
			//�غ�ܰ迡�� �÷��� ��ư�� Ȱ��ȭ, �������� ��Ȱ��ȭ 
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
			
			
			mediaPlayer.currentTimeProperty().addListener((obj,oldValue,newVelue)->{
				double progress = 
						//�帣�� �ð� //�� �ð�
				mediaPlayer.getCurrentTime().toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
				progressBar.setProgress(progress);
				progressIndicator.setProgress(progress);
				labelTime.setText((int)mediaPlayer.getCurrentTime().toSeconds()+" / "+
						(int)mediaPlayer.getTotalDuration().toSeconds()+" sec");
			});
		});
		mediaPlayer.setOnPlaying(()->{//���� �� �̸�
			btnPlay.setDisable(true);
			btnPause.setDisable(false);
			btnStop.setDisable(false);
		});
		mediaPlayer.setOnPaused(()->{//�Ͻ����� �� �̸�
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(false);
		});
		mediaPlayer.setOnEndOfMedia(()->{//�̵� ����Ǿ��ٸ�
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
			
			mediaPlayer.stop(); // ��ž�ϸ� �̵� ó������ ���ư���.
			//mediaPlayer.seek(mediaPlayer.getStartTime());//�̵� ó������ ������
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








