package sg.asia21at.webdev.footprintimporter.fileHandler;

import java.io.IOException;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sg.asia21at.webdev.footprintimporter.log.LoggerCreator;

public class CheckDirVisitor extends SimpleFileVisitor<Path> {
	private final Pattern pattern;
	private int counter = 0;
	FileHandler fileHandler;

	public CheckDirVisitor(String patternString, FileHandler fileHandler) {
		this.pattern = Pattern.compile(patternString);
		this.fileHandler = fileHandler;
	}

	void find(Path dir) {
		Matcher matcher = pattern.matcher(dir.getFileName().toString());
		if (matcher.matches()) {
			LoggerCreator.getLogger().info("Found folder:" + matcher.group());
			this.counter++;
			fileHandler.process(dir);

		}
	}

	@Override
	public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
		find(dir);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) {
		if (exc instanceof FileSystemLoopException) {
			LoggerCreator.getLogger().error("cycle detected: " + file);
		} else {
			LoggerCreator.getLogger().error("Unable to copy:" + file + ":" + exc);
		}
		return FileVisitResult.CONTINUE;
	}
}
