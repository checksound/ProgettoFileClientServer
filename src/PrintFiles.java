import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class PrintFiles
    extends SimpleFileVisitor<Path> {
	
	private StringWriter out;
	
	public PrintFiles(StringWriter out) {
		this.out = out;
	}

    // Print information about
    // each type of file.
    @Override
    public FileVisitResult visitFile(Path file,
                                   BasicFileAttributes attr) {
    	int pethLength = file.getNameCount();
    	// System.out.println(file + " " + pethLength);
        if (attr.isSymbolicLink()) {
            out.write(String.format("%s ", file.subpath(pethLength - 1, pethLength)));
        } else if (attr.isRegularFile()) {
        	out.write(String.format("%s ", file.subpath(pethLength - 1, pethLength)));
        } else {
        	out.write(String.format("%s/ ", file.subpath(pethLength - 1, pethLength)));
        }
        out.write(String.format("(" + attr.size() + " bytes)\n"));
        return CONTINUE;
    }

    // Print each directory visited.
    @Override
    public FileVisitResult postVisitDirectory(Path dir,
                                          IOException exc) {
        // System.out.format("Directory: %s%n", dir);
        return CONTINUE;
    }

    // If there is some error accessing
    // the file, let the user know.
    // If you don't override this method
    // and an error occurs, an IOException 
    // is thrown.
    @Override
    public FileVisitResult visitFileFailed(Path file,
                                       IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }
}
