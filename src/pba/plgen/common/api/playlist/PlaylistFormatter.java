package pba.plgen.common.api.playlist;

/**
 * Handles the formatting of the playlist file
 * 
 * @author AnuvratSingh
 */
public interface PlaylistFormatter {
	/**
	 * Creates a new playlist file
	 * 
	 * @param playlistName The name of the playlist file
	 */
	public void createNewPlaylist(Playlist playlist);

	/**
	 * Append all songs into the playlist
	 * 
	 * @param songDetails The song to be added
	 */
	public void addSongs(Playlist playlist);

	/**
	 * Save the playlist to the disk and get ready for a new playlist
	 */
	public void savePlaylist();
}
