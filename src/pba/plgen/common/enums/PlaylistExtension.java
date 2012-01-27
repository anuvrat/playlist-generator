package pba.plgen.common.enums;

/**
 * Supported extensions/playlist formats
 * 
 * @author AnuvratSingh
 */
public enum PlaylistExtension {
	M3U("m3u");

	private final String m_value;

	private PlaylistExtension(String value) {
		m_value = value;
	}

	public String toString() {
		return m_value;
	}

	public static PlaylistExtension fromValue(String value) {
		for (PlaylistExtension playlistExtension : PlaylistExtension.values())
			if (playlistExtension.toString().equals(value))
				return playlistExtension;

		throw new IllegalArgumentException(value);
	}
}
