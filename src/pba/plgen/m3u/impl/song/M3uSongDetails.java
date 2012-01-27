package pba.plgen.m3u.impl.song;

import java.io.File;

import pba.plgen.common.api.song.SongDetails;

public class M3uSongDetails implements SongDetails {
	private String m_albumName;
	private String m_songName;
	private String m_artistName;
	private File m_file;
	private int m_trackLength;

	public M3uSongDetails(String songName, String albumName, String artistName) {
		m_albumName = albumName;
		m_songName = songName;
		m_artistName = artistName;
	}

	public String getAlbumName() {
		return m_albumName;
	}

	public String getArtistName() {
		return m_artistName;
	}

	public File getFile() {
		return m_file;
	}

	public int getTrackLength() {
		return m_trackLength;
	}

	public String getSongName() {
		return m_songName;
	}

	public void setFile(File file) {
		m_file = file;
	}

	public void setTrackLength(int trackLength) {
		m_trackLength = trackLength;
	}
}
