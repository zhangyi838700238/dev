package cn.yyun.ppt.image.layout;

import java.util.ArrayList;

import cn.yyun.ppt.utils.ImageUtils;

public class DefaultLayout extends Layout {

    public final int ROW_IMAGE_COUNT = 3;

    @Override
    public void initImageList(int imageWidth, int imageHeight, int imageCount) {

        this.canvasHeight = 0;
        this.imageList = new ArrayList<>();

        // ��һ����Dһ��ֻ��һ��
        this.canvasHeight = this.addRow(1, this.canvasHeight, imageWidth,
                imageHeight);

        // �ڶ�����һ��ROW_IMAGE_COUNT����ֱ������һ����DҲ�Ϊ�һ��
        // �����ڶ��п��ܲ���ROW_IMAGE_COUNT����������
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

        // ����һ��
        this.canvasHeight = this.addRow(1, this.canvasHeight, imageWidth,
                imageHeight);

    }

    /**
     * ̎��һ�ЈDƬ�����DƬ������ӵ�imageList��
     * 
     * @param rowImageCount
     *            ��ǰ�ЈDƬ����
     * @param beginY
     *            �����_ʼ�ĸ߶�
     * @return ��ǰ�ЈDƬ�߶�
     */
    private int addRow(int rowImageCount, int beginY, int imageWidth,
            int imageHeight) {
        // ��ǰ��ÿ���DƬ�Č���
        int nowWidth = canvasWidth / rowImageCount;
        // ��ǰ�ЈDƬ�߶�
        int nowHeight = ImageUtils.getNewHeight(imageWidth, imageHeight,
                nowWidth);
        // ���ܕ����N�����ڵ�����һ��֮ǰ����
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
