package pba.plgen.common.enums;

/**
 * The node names which can be present in the xml config file
 * 
 * @author AnuvratSingh
 */
public enum XMLElementType {
	LOCATION, EXTENSION, SAVE_LOCATION, ARTIST, SIZE, DURATION, ENABLED;

	public String toString() {
		return super.toString().toLowerCase();
	}
}
