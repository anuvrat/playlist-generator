package pba.plgen.m3u.impl.playlist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import pba.plgen.common.api.playlist.Playlist;
import pba.plgen.common.api.playlist.PlaylistFormatter;
import pba.plgen.common.api.song.SongDetails;
import pba.plgen.m3u.impl.song.M3uSongDetails;

/**
 * A formatter which creates the m3u playlist
 * 
 * @author AnuvratSingh
 */
public class M3UplaylistFormatter implements PlaylistFormatter {
	private File m_playlist;
	private List<String> m_contents;

	public void createNewPlaylist(Playlist playlist) {
		m_playlist = new File(playlist.getSaveLocation());
		m_contents = new ArrayList<String>();
		m_contents.add("#EXTM3U");
	}

	public void addSongs(Playlist playlist) {
		for (SongDetails songGeneric : playlist.getSongDetails()) {
			File songFile = songGeneric.getFile();
			if (songFile == null)
				continue;
			M3uSongDetails song = (M3uSongDetails) songGeneric;
			StringBuffer songInfo = new StringBuffer("#EXTINF:");
			songInfo.append(song.getTrackLength());
			songInfo.append(", ");
			songInfo.append(song.getArtistName());
			songInfo.append(" - ");
			songInfo.append(song.getSongName());
			m_contents.add(songInfo.toString());
			m_contents.add(song.getFile().getAbsolutePath());
		}
	}

	public void savePlaylist() {
		Writer output = null;
		try {
			output = new BufferedWriter(new FileWriter(m_playlist));
			for (String content : m_contents) {
				output.write(content);
				output.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null)
				try {
					output.flush();
					output.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}

		m_playlist = null;
		m_contents = null;
	}
}
