package application;

public class Matrix {

    private int nrows;
    private int ncols;
     double[][] data;

    public Matrix(double[][] dat) {
        this.data = dat;
        this.nrows = dat.length;
        this.ncols = dat[0].length;
    }

    
    public Matrix(int nrow, int ncol) {
        this.nrows = nrow;
        this.ncols = ncol;
        data = new double[nrow][ncol];
    }
    public double getValueAt(int i, int j) {
    	return data [i][j];
    }
    public double setValueAt(int i, int j, double d) {
    	 return data [i][j]=d;
    } 
    public int getNrows() {
    	return nrows;
    	
    }
    public void PrintMatrix() {
    	for(int i=0; i<nrows; i++) {
    		for(int j=0; j<ncols;j++) {
    			System.out.print(data[i][j]+" ");
    		}
    		System.out.println();
    	}
    }
    public int getNcols() {
    	return ncols;
    }
   
    
}