package util;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileManager {
	
	public static String main(String arg) {
		String fName = null;
		JFileChooser fileopen = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Dialogs files", "txt");
		fileopen.addChoosableFileFilter(filter);

		int result = fileopen.showDialog(null, arg);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileopen.getSelectedFile();
			fName = file.getPath() ;
		}
		return fName;
	}
}