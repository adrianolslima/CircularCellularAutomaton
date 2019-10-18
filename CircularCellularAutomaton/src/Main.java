import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	static String readLn(int maxLg) { // utility function to read from stdin

		byte lin[] = new byte[maxLg];
		int lg = 0, car = -1;
		String line = "";

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

		int n, d;
		long m, k;
		long[] values;

		while ((input = Main.readLn(255)) != null) {

			idata = new StringTokenizer(input);
			n = Integer.parseInt(idata.nextToken());
			m = Integer.parseInt(idata.nextToken());
			d = Integer.parseInt(idata.nextToken());
			k = Integer.parseInt(idata.nextToken());

			values = new long[n];

			input = Main.readLn(5000);
			idata = new StringTokenizer(input);
			for (int i = 0; i < values.length; i++) {
				values[i] = Integer.parseInt(idata.nextToken());
			}

			new CircAut(n, m, d, k, values);
		}
	}
}

class CircAut {

	private int n, d;
	long m, k;

	private long[] values;

	ArrayList<long[]> results = new ArrayList<long[]>();

	public CircAut(int n, long m, int d, long k, long[] values) {

		this.n = n;
		this.m = m;
		this.d = d;
		this.k = k;
		this.values = values;

		step(k);
		
		String output = Arrays.toString(this.values).replaceAll("\\[|\\]|,|", "");
		
		System.out.print(output + "\n");
	}

	public boolean step(long k) {

		long[] newValues = new long[n];
		int neighbor;
		
		for (int i = 0; i < k; i++) {

			for (int j = 0; j < n; j++) {

				long newValue = values[j];

				for (int l = d; l > 0; l--) {

					neighbor = (n + j + l) % n;
					newValue += values[neighbor];
					neighbor = (n + j - l) % n;
					newValue += values[neighbor];
				}
				newValues[j] = newValue % m;
			}
			values = newValues.clone();

//			if (!results.isEmpty()) {
//				
//				if (Arrays.equals(newValues, results.get(0))) {
//					
//					int a;
//					a = (i == 1) ? 0 : (int) (k % (i + 1));
//					newValues = results.get(a);
//					values = newValues;
//					
//					return true;
//				}
//			}
//			results.add(values);
			System.out.println(Arrays.toString(this.values).replaceAll("\\[|\\]|,|", ""));
		}
		return true;
	}
	
	int maxn=500+5;

	int MOD;

	struct Matrix {
	    int n;
	    long M[maxn];
	    Matrix(){memset(M,0,sizeof(M));n=0;}
	    Matrix(int n):n(n){memset(M,0,sizeof(M));}
	    Matrix operator * (const Matrix& rhs) const {
	        Matrix ret(n);
	        for(int i=0;i<n;i++) {
	            for(int j=0;j<n;j++) 
	                ret.M[i]+=M[j]*rhs.M[(j-i+n)%n];
	            ret.M[i]%=MOD;
	        }
	        return ret;
	    }
	    Matrix operator *= (const Matrix& rhs) {
	        return *this=*this*rhs;
	    }
	}

	Matrix qpow(Matrix x,int y) {
	    Matrix ret=x;
	    if(--y==0) return x;
	    while(y>0) {
	        if(y&1) ret*=x;
	        x*=x;y>>=1;
	    }
	    return ret;
	}

//	int main()
//	{
//	    int n,d,k;
//	    while(~scanf("%d%d%d%d",&n,&MOD,&d,&k)) {
//	        Matrix A(n),F(n);
//	        for(int i=0;i<n;i++) scanf("%lld",&F.M[i]);
//	        for(int i=0;i<n;i++) if(min(i,n-i)<=d) A.M[i]=1;
//	        A=qpow(A,k);
//	        F=F*A;
//	        printf("%lld",F.M[0]);
//	        for(int i=1;i<n;i++) printf(" %lld",F.M[i]);
//	        printf("\n");
//	    }
//	    return 0;
//	}
}

class Matrix {
    
	static int N;	// size of the matrix
 
	/**
	 * compute pow(base, pow)
	 * O(N^3) * logN
	**/
	static long[][] matrixPower(long[][] base, long pow)	{
		long [][] ans = new long [N][N];
		// generate identity matrix
		for (int i = 0; i < N; i++)	ans[i][i] = 1;
 
		// binary exponentiation
		while ( pow != 0 )	{
			if	( (pow&1) != 0 )	ans = multiplyMatrix(ans, base);
 
			base = multiplyMatrix(base, base);
 
			pow >>= 1;
		}
 
		return	ans;
	}
 
	/**
	 * compute m * m2
	 * O(N^3)
	**/
	static long[][] multiplyMatrix(long[][] m, long[][] m2)	{
		long[][] ans = new long[N][N];
 
		for (int i = 0; i < N; i++)	for (int j = 0; j < N; j++)	{
			ans[i][j] = 0;
			for (int k = 0; k < N; k++)	{
				ans[i][j] += m[i][k] * m2[k][j];
			}
		}
 
		return	ans;
	}
 
}