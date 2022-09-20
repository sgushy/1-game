package pointpointy;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

public class SoundManager {

	public SoundManager()
	{
		
	}
    public void play(String filePath) {
    	File file = new File(SMain.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"/pointpointy/resources/audio/"+filePath+".wav");
   
    	 try {
             // Open an audio input stream.           
              File soundFile = file;
              AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
             // Get a sound clip resource.
             Clip clip = AudioSystem.getClip();
             // Open audio clip and load samples from the audio input stream.
             clip.open(audioIn);
            // SMain.notifyConsole("starting");
             clip.start();
             
          } catch (UnsupportedAudioFileException e) {
             e.printStackTrace();
          } catch (IOException e) {
             e.printStackTrace();
          } catch (LineUnavailableException e) {
             e.printStackTrace();
          }
    		    try
    	    {
    	        final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));

    	        clip.addLineListener(new LineListener()
    	        {
    	            @Override
    	            public void update(LineEvent event)
    	            {
    	                if (event.getType() == LineEvent.Type.STOP)
    	                    clip.close();
    	            }
    	        });

    	        clip.open(AudioSystem.getAudioInputStream(file));
    	        clip.start();
    	    }
    	    catch (Exception exc)
    	    {
    	        exc.printStackTrace(System.out);
    	    }
    	}

    @SuppressWarnings("unused")
	private AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();

        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    private void stream(AudioInputStream in, SourceDataLine line) 
        throws IOException {
        final byte[] buffer = new byte[4096];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, n);
        }
    }
}