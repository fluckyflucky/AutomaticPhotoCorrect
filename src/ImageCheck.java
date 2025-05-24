import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.util.ArrayList;
import java.util.List;

public class ImageCheck {





    /**
     * 检测图像倾斜角度（综合水平和垂直线）
     */
    public static double detectSkewAngle(Mat image) {
        // 转换为灰度图
        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);

        // Canny 边缘检测
        Mat edges = new Mat();
        Imgproc.Canny(gray, edges, 50, 150, 3, true);

        // Hough 变换检测直线
        Mat lines = new Mat();
        Imgproc.HoughLines(edges, lines, 1, Math.PI / 180, 100);

        // 分离水平线和垂直线角度
        List<Double> horizontalAngles = new ArrayList<>();
        List<Double> verticalAngles = new ArrayList<>();

        for (int i = 0; i < lines.rows(); i++) {
            double[] line = lines.get(i, 0);
            double rho = line[0], theta = line[1];
            double angle = Math.toDegrees(theta) - 90; // 转换为 -90°~90°

            // 分类：水平线（-30°~30°）或 垂直线（50°~130°）
            if (Math.abs(angle) < 30) {
                horizontalAngles.add(angle);
            } else if (Math.abs(angle) > 50) {
                verticalAngles.add(angle);
            }
        }

        // 策略1：优先地平线（水平线）
        if (!horizontalAngles.isEmpty()) {
            return getMedianAngle(horizontalAngles);
        }

        // 策略2：若无水平线，则校正建筑物（垂直线 - 90°）
        if (!verticalAngles.isEmpty()) {
            return getMedianAngle(verticalAngles) - 90; // 转换为水平校正角度
        }

        return 0; // 未检测到明显线条
    }

    /**
     * 计算角度中值（抗噪声）
     */
    private static double getMedianAngle(List<Double> angles) {
        angles.sort(Double::compare);
        int size = angles.size();
        if (size % 2 == 0) {
            return (angles.get(size / 2) + angles.get(size / 2 - 1)) / 2;
        } else {
            return angles.get(size / 2);
        }
    }

    /**
     * 旋转图像至水平
     */
    public static Mat rotateImage(Mat image, double angle,int model) {
        if (angle == 0) return image.clone();

        Point center = new Point(image.width() / 2.0, image.height() / 2.0);
        Mat rotationMat = Imgproc.getRotationMatrix2D(center, angle, 1.0);

        Mat rotated = new Mat();
        Imgproc.warpAffine(
                image, rotated, rotationMat, image.size(),
                Imgproc.INTER_LINEAR, model
        );

        return rotated;
    }


}