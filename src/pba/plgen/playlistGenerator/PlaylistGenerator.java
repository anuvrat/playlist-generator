package pba.plgen.playlistGenerator;

import java.util.List;

import pba.plgen.common.api.playlist.Playlist;
import pba.plgen.common.api.playlist.PlaylistFormatter;
import pba.plgen.common.enums.ConfigFileParams;
import pba.plgen.common.utils.LastfmUtils;
import pba.plgen.common.utils.Scanner;
import pba.plgen.common.utils.XMLConfigReader;

/**
 * This is the main class which controls the flow of the application.
 * 
 * @author AnuvratSingh
 */
public class PlaylistGenerator {
	public void start(String configFilePath) {
		// Read the configuration file
		XMLConfigReader xmlConfigReader = new XMLConfigReader(configFilePath);
		xmlConfigReader.parse();

		// Download the list of songs from last.fm and prepare the playlists
		List<Playlist> playlists = LastfmUtils.downloadPlaylists(xmlConfigReader
		        .getParameterValue(ConfigFileParams.PLAYLIST));

		// Scan for the files mentioned in the playlists and attach them to it
		Scanner.scanFiles(playlists);

		// For each playlist, get the formatter, write to the file and save it
		for (Playlist playlist : playlists) {
			PlaylistFormatter formatter = playlist.getPlaylistFormatter();
			formatter.createNewPlaylist(playlist);
			formatter.addSongs(playlist);
			formatter.savePlaylist();
		}
	}
}
