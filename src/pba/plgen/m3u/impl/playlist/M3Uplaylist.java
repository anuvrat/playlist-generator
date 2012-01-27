package pba.plgen.m3u.impl.playlist;

import java.util.List;

import pba.plgen.common.api.playlist.Playlist;
import pba.plgen.common.api.playlist.PlaylistFormatter;
import pba.plgen.common.api.song.SongDetails;
import pba.plgen.common.enums.PlaylistExtension;

/**
 * Implementation of the Playlist interface for the m3u format
 * 
 * @author AnuvratSingh
 */
public class M3Uplaylist implements Playlist {
	private String m_playlistName;
	private final PlaylistExtension m_extension;
	private final PlaylistFormatter m_playlistFormatter;
	private List<SongDetails> m_songDetails;
	private final String m_saveLocation;
	private final String m_searchLocation;

	/**
	 * @param playlistName Name of the playlist
	 * @param songDetails The list of details of the song
	 * @param saveLocation The location where the playlist needs to be saved
	 * @param searchLocation The location which needs to be searched for song files
	 */
	public M3Uplaylist(String playlistName, List<SongDetails> songDetails, String saveLocation, String searchLocation) {
		m_extension = PlaylistExtension.M3U;
		m_playlistName = playlistName;
		m_playlistFormatter = new M3UplaylistFormatter();
		m_songDetails = songDetails;
		m_saveLocation = saveLocation;
		m_searchLocation = searchLocation;
	}

	public PlaylistExtension getExtension() {
		return m_extension;
	}

	public PlaylistFormatter getPlaylistFormatter() {
		return m_playlistFormatter;
	}

	public String getPlaylistName() {
		return m_playlistName;
	}

	public List<SongDetails> getSongDetails() {
		return m_songDetails;
	}

	public String getSaveLocation() {
		return m_saveLocation;
	}

	public String getSearchLocation() {
		return m_searchLocation;
	}
}
