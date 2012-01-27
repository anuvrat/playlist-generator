package pba.plgen.common.enums;

/**
 * Elements which can be present in the config file
 * 
 * @author AnuvratSingh
 */
public enum ConfigFileParams {
	MUSIC, PLAYLIST;

	public String toString() {
		return super.toString().toLowerCase();
	}
}
