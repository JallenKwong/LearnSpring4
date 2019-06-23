package org.fkit.crudrepositorytest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * 将properties文件的(\\u....)转换成正确可读字符
 * 
 * @author 白居布衣
 *
 */
public class Decoder {

	public static void main(String[] args) {
		try {
			Scanner in = new Scanner(getFile("application.properties"));
			
			while(in.hasNextLine()) {
				
				String line = in.nextLine();
				
				if(!line.startsWith("# ")) {
					System.out.println(line);
					continue;
				}
				
				System.out.print("# ");
				
				int existed = line.indexOf("\\u", 0);
				while(existed != -1) {
					String str = line.substring(existed + 2, existed + 6);
					
					System.out.print((char)(Integer.parseInt(str, 16)));
					
					existed = line.indexOf("\\u", existed + 5);
				}
				
				System.out.println();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static File getFile(String path) {
		String filePath = Decoder.class.getClassLoader().getResource(path).getPath();
		return new File(filePath);
	}
	
	
}
