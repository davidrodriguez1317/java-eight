package com.dro.eight.nio2;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestPath {

	private static void gettingPaths() throws IOException, URISyntaxException
	{
		Path path01 = Paths.get(new URI("file:///resources/testfile.txt")); //URIs must reference absolute paths
		System.out.println(" current directory path01 --- " + path01.toAbsolutePath());

		// We access the local file system
		Path path02 = FileSystems.getDefault().getPath("resources/testfile.txt"); // it uses getPath(...)
		System.out.println(" current directory path02 --- " + path02.toAbsolutePath());

		// We access a remote file system. IT THROWS AN EXCEPTION!!!
		// FileSystem fileSystem = FileSystems.getFileSystem(new URI("http://www.selikoff.net")); // it uses getPath(...)
		// Path path03 = fileSystem.getPath("duck.txt");
		// System.out.println(" current directory path03 --- " + path03.toAbsolutePath());

		// Legacy File instances
		File file = new File("resources/testfile.txt");
		Path path04 = file.toPath();
		System.out.println(" current directory path04 --- " + path04.toAbsolutePath());

	}

	
	private static void several() throws IOException
	{
		// Get current directory
		Path path01 = Paths.get(".");	
		System.out.println(" current directory --- " + path01.toString());
		System.out.println(" current directory --- " + path01.toAbsolutePath());
		
		Path path02 = Paths.get("OCP", "resources");	
		System.out.println(" resources --- " + path02.toString());
		
		Path path03 = Paths.get("..").toAbsolutePath().resolve(path02);
		System.out.println(" path03 --- " + path03.toString());
		System.out.println(" path03 --- " + path03.normalize().toString());

		// System.out.println(" toRealPath - " + path01.toString() + " --- " + path01.toRealPath()); // Throws exception as it does not exist
		System.out.println(" toRealPath - " + path03.toAbsolutePath() + " --- " + path03.toAbsolutePath().toRealPath());

	}
	
	private static void equality() throws IOException
	{
		Path path01 = Paths.get("C:\\Users\\david\\development\\workspaces\\ws_sts\\OCP\\resources\\testfile.txt");
		Path path02 = Paths.get("resources\\testfile.txt");
		
		System.out.println(" toString --- " + path01.toString());
		System.out.println(" toAbsolutePath --- " + path01.toAbsolutePath());
		System.out.println(" toString --- " + path02.toString());
		System.out.println(" toAbsolutePath --- " + path02.toAbsolutePath());
		
		boolean resultEquals = path01.equals(path02);
		System.out.println(" resultEquals --- " + resultEquals);
		
		boolean resultIsSameFile = Files.isSameFile(path01, path02);
		System.out.println(" resultIsSameFile --- " + resultIsSameFile);
	}
	
	private static void testNormalize()
	{
		Path path01 = Paths.get("ws_sts/OCP/../TestCompanyApp/resources/testfile.txt");
		System.out.println(" before normalize --- " + path01.toString());
		path01 = path01.normalize();
		System.out.println(" after normalize --- " + path01.toString());
	
		
		Path path02 = Paths.get("resources", "testfile.txt").toAbsolutePath();
		Path path03 = Paths.get("resources", "intern", "internfile.txt").toAbsolutePath();
		System.out.println( " path02 --- " + path02);
		System.out.println( " path03 --- " + path03);
		

	}
	
	private static void testRepresentations()
	{
		Path path01 = Paths.get("resources");
		path01 = path01.toAbsolutePath();
		System.out.println(" path01 toAbsolutePath --- " + path01.toString());

		// The first element is not the root element (C: or /)
		for(int i=0; i<path01.getNameCount(); i++)
		{
			System.out.println("  Element " + i + " --- " + path01.getName(i));	
		}
		
		System.out.println(" path01 getFileName --- " + path01.getFileName());
		System.out.println(" path01 getRoot --- " + path01.getRoot());
		System.out.println(" path01 getParent --- " + path01.getParent());

		System.out.println(" path01 isAbsolute - " + path01 + " --- " + path01.isAbsolute());
		System.out.println(" path01 isAbsolute - " + path01.getFileName() + " --- " + path01.getFileName().isAbsolute());

		System.out.println(" path01 subPath (0, 2) - " + path01.subpath(0, 2));
		System.out.println(" path01 subPath (1, 4) - " + path01.subpath(1, 4));
		// System.out.println(" path01 subPath (2, 9) - " + path01.subpath(2, 9)); // Throws exception because of the 9

	}
	
	private static void testRelativize()
	{
		// relativize needs both paths to be of the same type (absolute or relative)
		
		Path path01 = Paths.get("resources", "testfile.txt");
		Path path02 = Paths.get("resources", "intern", "internfile.txt");
		Path path03 = Paths.get("resources", "testfile2.txt");

		System.out.println(" path01 --- " + path01);
		System.out.println(" path02 --- " + path02);
		System.out.println(" path03 --- " + path03);

		System.out.println(" from path01 to path02 --- " + path01.relativize(path02));
		System.out.println(" from path02 to path01 --- " + path02.relativize(path01));
		System.out.println(" from path01 to path03 --- " + path01.relativize(path03)); // it includes ..\ because it counts from the file itself

	}
	
	private static void testResolve()
	{
		Path path01 = Paths.get("resources", "testfile.txt");
		Path path02 = Paths.get("resources", "intern", "internfile.txt");
		Path path03 = Paths.get("C:\\Users\\david\\development\\workspaces\\ws_sts\\OCP");
				
		System.out.println(" path01 + path02 --- " + path01.resolve(path02));
		System.out.println(" path01 + path03 --- " + path01.resolve(path03)); // If an absolute path is in the second, it will return just the absolute path
		System.out.println(" path03 + path01 --- " + path03.resolve(path01));

		
	}
	
	
	
	public static void main(String... args)  throws IOException, URISyntaxException {
		
		System.out.println("gettingPaths -------------------");
		gettingPaths();
		System.out.println();
				
		System.out.println("several -------------------");
		several();
		System.out.println();
		
		System.out.println("equality -------------------");
		equality();
		System.out.println();
		
		System.out.println("testNormalize -------------------");
		testNormalize();
		System.out.println();
		
		System.out.println("testRepresentations -------------------");
		testRepresentations();
		System.out.println();
		
		System.out.println("testRelativize -------------------");
		testRelativize();
		System.out.println();
		
		System.out.println("testResolve -------------------");
		testResolve();
		System.out.println();
	}

		
}
