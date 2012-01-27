package pba.plgen.common.api.song;

import java.io.File;

/**
 * Details of a song are managed by this class
 * 
 * @author AnuvratSingh
 */
public interface SongDetails {
	/**
	 * @return Name of the song
	 */
	public String getSongName();

	/**
	 * @return Length of the track
	 */
	public int getTrackLength();

	/**
	 * @return The song fileu
	 */
	public File getFile();

	/**
	 * Attach a song file with the song
	 * 
	 * @param file The song file which needs to be attached
	 */
	public void setFile(File file);

	/**
	 * Add the information regarding the length of the track
	 * 
	 * @param trackLength
	 */
	public void setTrackLength(int trackLength);
}
