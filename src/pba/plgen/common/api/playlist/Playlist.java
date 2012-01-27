package pba.plgen.common.api.playlist;

import java.util.List;

import pba.plgen.common.api.song.SongDetails;
import pba.plgen.common.enums.PlaylistExtension;

/**
 * The details of playlist are managed by this class
 * 
 * @author AnuvratSingh
 */
public interface Playlist {
	/**
	 * @return Name of the playlist file
	 */
	public String getPlaylistName();

	/**
	 * @return The extension of the playlist file
	 */
	public PlaylistExtension getExtension();

	/**
	 * @return The formatter associated with the playlist file
	 */
	public PlaylistFormatter getPlaylistFormatter();

	/**
	 * @return All the songs which need to be added to the playlist
	 */
	public List<SongDetails> getSongDetails();

	/**
	 * @return The location on hard disk which needs to be searched for song files
	 */
	public String getSearchLocation();

	/**
	 * @return The location on hard disk where the playlist needs to be saved
	 */
	public String getSaveLocation();
}
