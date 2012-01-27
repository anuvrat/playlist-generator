package pba.plgen.internal.data;

/**
 * @author AnuvratSingh
 */
public class MusicData extends Data {
	private String m_location;

	public MusicData(String location) {
		super();
		m_location = location;
	}

	public String getLocation() {
		return m_location;
	}

	public void setLocation(String location) {
		m_location = location;
	}
}
