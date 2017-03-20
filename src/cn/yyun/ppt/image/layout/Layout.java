package cn.yyun.ppt.image.layout;

import java.util.List;

public abstract class Layout {

    /**
     * ��������
     */
    protected int canvasWidth;

    /**
     * ��������
     */
    protected int canvasHeight;

    /**
     * �DƬ���˼���
     */
    protected List<ImageProperty> imageList;

    /**
     * ��ʼ���DƬ����
     * 
     * @param imageWidth
     *            ÿ���DƬԭʼ����
     * @param imageHeight
     *            ÿ���DƬԭʼ�߶�
     * @param imageCount
     *            �DƬ����
     */
    public abstract void initImageList(int imageWidth, int imageHeight,
            int imageCount);

    public void initImageList(int imageWidth, int imageHeight, int imageCount,
            int canvasWidth) {
        this.canvasWidth = canvasWidth;
        this.initImageList(imageWidth, imageHeight, imageCount);
    }

    public List<ImageProperty> getImageList() {
        return this.imageList;
    }

    public int getCanvasHeight() {
        return this.canvasHeight;
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

}
