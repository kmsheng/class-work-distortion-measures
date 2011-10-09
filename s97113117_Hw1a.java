import static java.lang.System.out;
import static java.lang.Math.cos;
import static java.lang.Math.PI;
import static java.lang.Math.round;
import java.text.DecimalFormat;

class TransformCoding {
	int[] fi;
	public TransformCoding() {
	}

	public TransformCoding(int[] fi) {
		this.fi = fi;
		get1DDCT();
	}

	public double[] get1DDCT() {
		double[] dct = new double[fi.length];
		double cu;
		double PI = Math.PI;

		for (int u = 0; u < dct.length; u++) {
			for (int i = 0; i < dct.length; i++) {
				dct[u] += cos(((2 * i + 1) * u * PI) / 16) * fi[i];
			}
			// 保留小數後四位
			dct[u] = round(dct[u] * 10000) / 10000.0;
			// 判斷C(u)
			cu = (0 == u) ? 0.35355339059 : 0.5;
			dct[u] *= cu;
		}
		return dct;
	}

	public void show(double[] dct) {
		// 取至小數末四位
		DecimalFormat df = new DecimalFormat("#.####");

		for (double i : dct) {
			out.println(df.format(i));
		}
	}
}

public class s97113117_Hw1a {
	public static void main(String[] args) {
		// 範例
		// int[] fi = {8, 7, 6, 5, 4, 3, 2, 1};
		int[] fi = {9, 7, 1, 1, 3, 1, 1, 7};
		TransformCoding tc = new TransformCoding(fi);

		double[] dct = tc.get1DDCT();
		tc.show(dct);
	}
}
