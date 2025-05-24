import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;


public class Main {
    static {
        System.loadLibrary("opencv_java4110"); // 加载 OpenCV 库
    }
    public static void main(String[] args) {
        String inputPath = "input.jpg";
        String outputPath = "output.jpg";

        // 1. 读取图像
        Mat image = Imgcodecs.imread(inputPath);
        if (image.empty()) {
            System.out.println("无法加载图像！");
            return;
        }

        // 2. 检测倾斜角度（优先地平线，其次建筑物）
        double skewAngle = ImageCheck.detectSkewAngle(image);

        int model = Core.BORDER_REPLICATE;
        


        // 3. 校正图像
        Mat corrected = ImageCheck.rotateImage(image, skewAngle,model);
        Imgcodecs.imwrite(outputPath, corrected);
        System.out.println("校正完成，倾斜角度: " + skewAngle + "°");
    }
}
