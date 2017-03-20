package cn.yyun.ppt.image;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import cn.yyun.ppt.image.layout.ImageProperty;
import cn.yyun.ppt.image.layout.Layout;

public class Canvas {

    public static final String EXTENSION_NAME_SPLIT = ",";

    private List<Image> imageList;
    private Layout layout;
    private BufferedImage imageBuffer;
    private int imageCount;

    public Canvas(Layout layout, String filePath,
            String extensionNameCaseInsensitive) {

        this.layout = layout;
        imageList = new ArrayList<>();

        String[] fileNames;
        File file = new File(filePath);
        if (!StringUtils.isBlank(extensionNameCaseInsensitive)) {
            fileNames = file.list(new FileExtensionNameFilter(
                    extensionNameCaseInsensitive.split(EXTENSION_NAME_SPLIT)));
        } else {
            fileNames = file.list();
        }

        Arrays.sort(fileNames, new FileNameComparator());

        int width = 0;
        int height = 0;
        for (int i = 0; i < fileNames.length; i++) {
            Image image = new Image(filePath + File.separator + fileNames[i]);
            if (0 == i) {
                width = image.getWidth();
                height = image.getHeight();
            } else {
                if (width != image.getWidth() || height != image.getHeight()) {
                    throw new IllegalArgumentException("每張圖片的長寬必須相等,圖片："
                            + fileNames[i]);
                }
            }
            imageList.add(image);
        }

        this.layout.initImageList(width, height, fileNames.length);
        this.imageBuffer = new BufferedImage(layout.getCanvasWidth(),
                layout.getCanvasHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public Canvas drawImages() {
        Graphics graphics = this.imageBuffer.getGraphics();
        List<ImageProperty> propertyList = this.layout.getImageList();
        for (int i = 0; i < propertyList.size(); i++) {
            ImageProperty property = propertyList.get(i);
            Image image = this.imageList.get(i);
            image.reSize(property.getWidth(), property.getHeight());
            graphics.drawImage(image.getImageBuffer(), property.getX(),
                    property.getY(), null);
        }
        return this;
    }

    /**
     * 保存到文件
     * 
     * @param fileFullPath
     * @throws IOException
     */
    public void saveTofile(String newfileFullPath) throws IOException {
        // 拓展名
        String formatName = newfileFullPath.substring(newfileFullPath
                .lastIndexOf(".") + 1);

        ImageIO.write(this.imageBuffer, formatName, new File(newfileFullPath));

    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

}

class FileNameComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        Integer int1 = 0;
        Integer int2 = 0;
        try {
            int1 = Integer.valueOf(o1.replaceAll("\\D", ""));
            int2 = Integer.valueOf(o2.replaceAll("\\D", ""));
        } catch (NumberFormatException e) {
            return o1.compareTo(o2);
        }
        return int1.compareTo(int2);
    }

}

class FileExtensionNameFilter implements FilenameFilter {

    private String[] extensionName;

    public FileExtensionNameFilter(String[] extensionName) {
        this.extensionName = extensionName;
    }

    @Override
    public boolean accept(File dir, String name) {
        for (int i = 0; i < extensionName.length; i++) {
            if (name.toLowerCase().endsWith(extensionName[i].toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}