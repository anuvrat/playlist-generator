package pba.plgen.main;

import pba.plgen.config.TextMessages;
import pba.plgen.playlistGenerator.PlaylistGenerator;

/**
 * @author AnuvratSingh
 */
public class Main {
	public static void main(String[] args) {
		String configFilePath = "";

		// Read the parameters from the arguments passed to the application
		int size = args.length;
		for (int i = 0; i < size; i++)
			if (args[i].equalsIgnoreCase(TextMessages.CONFIG))
				configFilePath = args[++i];

		// Run the playlist generator
		PlaylistGenerator playlistGenerator = new PlaylistGenerator();
		playlistGenerator.start(configFilePath);
	}
}
