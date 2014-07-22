package org.neto.patrones.watcher;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class AppTest{
	
//	@Test
	public void takeScheenshot(){
		final String shotname = "shot";
		try{
			Robot robot = new Robot();
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();
			Rectangle screenRect = new Rectangle(0, 0, screenSize.width, screenSize.height);
			BufferedImage image = robot.createScreenCapture(screenRect);

			File file = new java.io.File("D:/tmp/" + shotname + ".png");
			ImageIO.write(image, "png", file);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

//	@Test
	public void testApp(){
		
		final int[][] board = new int[9][9];
		
		final int boxlenght = (int)Math.sqrt(board.length);
		for(int i = 0; i < board.length; i++){
			System.out.println(i);
			final int x = (i % boxlenght) * boxlenght;
			final int y = (i / boxlenght) * boxlenght;
			
//			System.out.print("[" + x + "," + y + "] ");
			
			for(int m = x; m < x + boxlenght; m++){
				for(int n = y; n < y + boxlenght; n++){
					System.out.print("[" + m + "," + n + "] ");
				}
			}
			
			System.out.println("\n");
		}
	}
	
}
