import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	static String readLn(int maxLg) { // utility function to read from stdin

		byte lin[] = new byte[maxLg];
		int lg = 0, car = -1;

		try {
			while (lg < maxLg) {
				car = System.in.read();
				if ((car < 0) || (car == '\n'))
					break;
				lin[lg++] += car;
			}
		} catch (IOException e) {
			return (null);
		}

		if ((car < 0) && (lg == 0))
			return (null); // eof
		return (new String(lin, 0, lg));
	}

	public static void main(String[] args) throws Exception {

		Main myWork = new Main(); // create a dynamic instance
		myWork.begin(); // the true entry point
	}

	void begin() {

		String input;
		StringTokenizer idata;

		int n, m, d, k;

		while ((input = Main.readLn(255)) != null) {

			idata = new StringTokenizer(input);
			n = Integer.parseInt(idata.nextToken());
			m = Integer.parseInt(idata.nextToken());
			d = Integer.parseInt(idata.nextToken());
			k = Integer.parseInt(idata.nextToken());

			Matrix A = new Matrix(n, m);
			Matrix F = new Matrix(n, m);

			input = Main.readLn(5000);
			idata = new StringTokenizer(input);
			
			for (int i = 0; i < n; i++) {
				F.cells[i] = Integer.parseInt(idata.nextToken());
			}
			
			for (int i = 0; i < n; i++) {

				if (Math.min(i, n - i) <= d)
					A.cells[i] = 1;
			}

			A = A.power(k);
			F = F.mult(A);

			String output = Arrays.toString(F.cells).replaceAll("\\[|\\]|,|", "");

			System.out.print(output + "\n");
		}
	}
}

class Matrix {

	int n;
	int m;
	long[] cells;

	Matrix(int n, int m) {

		this.n = n;
		this.m = m;
		cells = new long[n];
		Arrays.fill(cells, 0);
	}

	Matrix mult(Matrix x) {

		Matrix ret = new Matrix(n, m);

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {

				ret.cells[i] += this.cells[j] * x.cells[(j - i + n) % n];
			}
			ret.cells[i] %= m;
		}
		return ret;
	}

	Matrix power(int y) {

		Matrix ret = this;
		Matrix id = this;
		int p = y;

		if (p-- == 0)
			return this;

		while (p > 0) {

			if ((p & 1) == 1)
				ret = ret.mult(id);

			id = id.mult(id);
			p >>= 1;
		}
		return ret;
	}
}