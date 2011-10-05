import static java.lang.System.out;
import static java.lang.Math.pow;
import static java.lang.Math.log10;
import java.text.DecimalFormat;

class DistortionMeasure {
	int[] xn, yn;
	int N;

	public DistortionMeasure() {
	}

	public DistortionMeasure(int[] xn, int[] yn, int N) {
		this.xn = xn;
		this.yn = yn;
		this.N = N;
	}

	public double getMSE() {
		double sum = 0.0;
		for (int i = 0; i < N; i++) {
			sum += pow((xn[i] - yn[i]), 2);
		}

		return sum / N;
	}

	public double getSNR() {
		double ASV = 0.0;	// Average Square Value
		double MSE = getMSE();

		for (int i = 0; i < N; i++) {
			ASV += pow(xn[i], 2);
		}
		ASV /= N;
		ASV /= MSE;

		return 10 * log10(ASV);
	}

	public double getPSNR(int peak) {
		double MSE = getMSE();
		return 10 * log10(pow(peak, 2) / MSE);
	}
}

public class s97113117_Hw1 {
	public static void main(String[] args) {

		int[] xn = {9, 7, 1, 1, 3, 1, 1, 7};	// 學號 (xn)
		int[] yn = {1, 2, 3, 4, 5, 6, 7, 8};	// 重建資料 (yn)
		int N = xn.length;	// 資料長度 (N)
		int peak = 9;

		DistortionMeasure dm = new DistortionMeasure(xn, yn, N);

		double MSE = dm.getMSE();	// Mean Square Error
		double SNR = dm.getSNR();	// Signal to Noise Ratio
		double PSNR = dm.getPSNR(9);	// Peak Singal to Noise Ratio
	}
}
