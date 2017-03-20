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
     *            �DƬ·�����ļ���
     */
    public Image(String imagePathAndName) {
        this.imageFile = new File(imagePathAndName);
        if (!this.imageFile.exists()) {
            throw new IllegalArgumentException("��ʼ��IMAGE����r���ļ�������:"
                    + imagePathAndName);
        }
        try {
            this.imageBuffer = ImageIO.read(imageFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("��ʼ��IMAGE����r�������D�Q��imageBuffer:"
                    + imagePathAndName);
        }

        this.width = this.imageBuffer.getWidth();
        this.height = this.imageBuffer.getHeight();
    }

    /**
     * �����O�ô�С������ԭ��Č��ȸ߶ȱ� �޸��˱������ر���
     * 
     * @param newWidth
     *            �µČ���
     * @return this
     */
    public Image reSize(int newWidth, int newHeight) {
        if (this.width < newWidth || 0 >= newWidth) {
            throw new IllegalArgumentException("���Ȳ����_���DƬֻ�ܿsС���ܷŴ����ȣ�"
                    + newWidth + "\tԭ�팒�ȣ�" + this.width + "\t�ļ���"
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
