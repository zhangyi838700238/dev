package cn.yyun.ppt.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
    private File imageFile;

    private BufferedImage imageBuffer;

    private int width;

    private int height;

    /**
     * 
     * 
     * @param imagePathAndName
     *            圖片路徑和文件名
     */
    public Image(String imagePathAndName) {
        this.imageFile = new File(imagePathAndName);
        if (!this.imageFile.exists()) {
            throw new IllegalArgumentException("初始化IMAGE對象時，文件不存在:"
                    + imagePathAndName);
        }
        try {
            this.imageBuffer = ImageIO.read(imageFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("初始化IMAGE對象時，不能轉換為imageBuffer:"
                    + imagePathAndName);
        }

        this.width = this.imageBuffer.getWidth();
        this.height = this.imageBuffer.getHeight();
    }

    /**
     * 重新設置大小，保留原來的寬度高度比 修改了本身，返回本身
     * 
     * @param newWidth
     *            新的寬度
     * @return this
     */
    public Image reSize(int newWidth, int newHeight) {
        if (this.width < newWidth || 0 >= newWidth) {
            throw new IllegalArgumentException("新寬度不正確，圖片只能縮小不能放大，新寬度："
                    + newWidth + "\t原來寬度：" + this.width + "\t文件："
                    + this.imageFile.getPath() + "\t"
                    + this.imageFile.getName());
        }

        BufferedImage newImageBuffer = new BufferedImage(newWidth, newHeight,
                BufferedImage.TYPE_INT_RGB);

        newImageBuffer.getGraphics().drawImage(
                this.imageBuffer.getScaledInstance(newWidth, newHeight,
                        java.awt.Image.SCALE_SMOOTH), 0, 0, null);

        this.imageBuffer = newImageBuffer;

        return this;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getImageBuffer() {
        return imageBuffer;
    }
}
