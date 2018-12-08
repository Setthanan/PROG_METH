package uiInterface;
import java.net.URISyntaxException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Audio {
	private Media sound;
	private MediaPlayer mediaPlayer;
	
	public Audio(String musicfile) {
		
		try
		{
		    sound = new Media(ClassLoader.getSystemResource(musicfile).toURI().toString());
		}
		catch (URISyntaxException ex)
		{
		    ex.printStackTrace();
		}
		mediaPlayer = new MediaPlayer(sound);
	}
	
	public void play_audio() {
		mediaPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         mediaPlayer.seek(Duration.ZERO);
		       }
		});
		mediaPlayer.play();
	}
	
	public void play_once() {
		mediaPlayer.play();
	}
	
	public void stop_audio() {
		mediaPlayer.stop();
	}
	
	public void pause() {
		mediaPlayer.pause();
	}

}
