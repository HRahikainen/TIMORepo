package timo;
import java.io.File;

import javax.sound.sampled.*;

// Bomb-audio from http://soundbible.com/1234-Bomb.html
// Recorded by Mike Koenig, License: Attribution 3.0 https://creativecommons.org/licenses/by/3.0/
public class PlayBreakingAudio {
	
	private static Clip clip;
	public static void play() {
		try {
			File file = new File("src/timo/breaking.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
		    clip = AudioSystem.getClip(null); // Must give null because Ubuntu?
		    clip.open(ais);
		    clip.start();
		}		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
