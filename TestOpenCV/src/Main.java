import org.opencv.core.Core;
import org.opencv.core.Mat;

import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import static org.opencv.imgproc.Imgproc.cvtColor;

public class Main {
    public static void main(String[] args) {

        int x;
        Mat rawImage = imread("image.jpg");
        Mat img = new Mat();
        cvtColor(rawImage,img,COLOR_BGR2GRAY);
        for (int i = 0; i < img.rows(); i++){
            for (int j = 0; j < img.cols(); j++){
                x = (int)img.get(i,j)[0];
                System.out.println(i + " " + j + " = " + x);
            }
        }
    }
}