package demos;

import java.awt.GraphicsEnvironment;

public class Font
{
        public static void main(String[] args)
        {
        		 GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                 String[] fontList = ge.getAvailableFontFamilyNames();
                 for(int i=0;i<fontList.length;i++)
                 {
                      System.out.println(fontList[i]);
                 }
        }
}