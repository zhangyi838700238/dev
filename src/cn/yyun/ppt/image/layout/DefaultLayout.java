package cn.yyun.ppt.image.layout;

import java.util.ArrayList;

import cn.yyun.ppt.utils.ImageUtils;

public class DefaultLayout extends Layout {

    public final int ROW_IMAGE_COUNT = 3;

    @Override
    public void initImageList(int imageWidth, int imageHeight, int imageCount) {

        this.canvasHeight = 0;
        this.imageList = new ArrayList<>();

        // 第一張大圖一行只有一張
        this.canvasHeight = this.addRow(1, this.canvasHeight, imageWidth,
                imageHeight);

        // 第二張起一行ROW_IMAGE_COUNT張，直到最後一張大圖也單獨一行
        // 倒數第二行可能不足ROW_IMAGE_COUNT張，不留空
        int residue = (imageCount - 2) % ROW_IMAGE_COUNT;
        int rouCount = (imageCount - 2 - residue) / ROW_IMAGE_COUNT;
        for (int i = 0; i < rouCount; i++) {
            this.canvasHeight = this.addRow(ROW_IMAGE_COUNT, this.canvasHeight,
                    imageWidth, imageHeight);
        }
        if (0 < residue) {
            this.canvasHeight = this.addRow(residue, this.canvasHeight,
                    imageWidth, imageHeight);
        }

        // 最後一行
        this.canvasHeight = this.addRow(1, this.canvasHeight, imageWidth,
                imageHeight);

    }

    /**
     * 處理一行圖片，將圖片屬性添加到imageList中
     * 
     * @param rowImageCount
     *            當前行圖片數量
     * @param beginY
     *            本行開始的高度
     * @return 當前行圖片高度
     */
    private int addRow(int rowImageCount, int beginY, int imageWidth,
            int imageHeight) {
        // 當前行每張圖片的寬度
        int nowWidth = canvasWidth / rowImageCount;
        // 當前行圖片高度
        int nowHeight = ImageUtils.getNewHeight(imageWidth, imageHeight,
                nowWidth);
        // 可能會有餘數，在倒數第一張之前留空
        int residue = canvasWidth % rowImageCount;
        int beginX = 0;

        for (int i = 0; i < rowImageCount; i++) {
            if (rowImageCount == i + 1) {
                beginX += residue;
            }

            ImageProperty property = new ImageProperty(beginX, beginY,
                    nowWidth, nowHeight);
            this.imageList.add(property);

            beginX += nowWidth;
        }
        return beginY + nowHeight;
    }

}
