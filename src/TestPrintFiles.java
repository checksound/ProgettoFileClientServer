import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

public class TestPrintFiles {

	public static void main(String[] args) throws IOException {
		Path startingDir = Paths.get("./files");
		
		EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		
		StringWriter writer = new StringWriter();
		PrintFiles pf = new PrintFiles(writer);
		Files.walkFileTree(startingDir, opts, 1, pf);
		
		System.out.println(writer.toString());

	}

}
