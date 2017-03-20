package cn.yyun.ppt.image.layout;

import java.util.List;

public abstract class Layout {

    /**
     * 畫布寬度
     */
    protected int canvasWidth;

    /**
     * 畫布寬度
     */
    protected int canvasHeight;

    /**
     * 圖片坐標集合
     */
    protected List<ImageProperty> imageList;

    /**
     * 初始化圖片屬性
     * 
     * @param imageWidth
     *            每張圖片原始寬度
     * @param imageHeight
     *            每張圖片原始高度
     * @param imageCount
     *            圖片總數
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
