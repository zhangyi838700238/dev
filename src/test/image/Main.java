package test.image;

import java.io.File;
import java.io.IOException;


import cn.yyun.ppt.image.Canvas;
import cn.yyun.ppt.image.layout.DefaultLayout;
import cn.yyun.ppt.image.layout.Layout;

public class Main {

    private static final String ROOT_DIR = "/Users/null/test"+ File.separator;
    
    private static final String EXT_NAME = "jpg"+Canvas.EXTENSION_NAME_SPLIT+"png";
    private static final String SAVE_NAME = "all.jpg";
    
    public static void main(String[] args) {
        
        Layout layout = new DefaultLayout();
        layout.setCanvasWidth(900);
        
        File[] files = new File(ROOT_DIR).listFiles();
        for (int i = 0; i < files.length; i++) {
            if(files[i].isDirectory()){
                String nowPaht = ROOT_DIR+File.separator+files[i].getName()+File.separator;
                Canvas c = new Canvas(layout,nowPaht, EXT_NAME);
                try {
                    c.drawImages().saveTofile(nowPaht + SAVE_NAME);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
