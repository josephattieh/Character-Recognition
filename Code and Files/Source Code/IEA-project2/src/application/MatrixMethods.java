package application;

public class MatrixMethods {

	
	public static void main (String [] args) throws Exception {
		
		double[][] n = { {1, 2 ,3 }, {4,5,6}};
		double [][] t = { {1}, {10},{9}};
		
		Matrix m = new Matrix(n);
		Matrix u = new Matrix(t);
		m.PrintMatrix();
		u.PrintMatrix();
		m= Multiply(m, u);
		m.PrintMatrix();
	}
	public static Matrix transpose(Matrix matrix) {
	    Matrix transposedMatrix = new Matrix(matrix.getNcols(), matrix.getNrows());
	    for (int i=0;i<matrix.getNrows();i++) {
	        for (int j=0;j<matrix.getNcols();j++) {
	            transposedMatrix.setValueAt(j, i, matrix.getValueAt(i, j));
	        }
	    }
	    return transposedMatrix;
	} 
	
	public static Matrix Multiply( Matrix m1, Matrix m2) throws Exception {
		if(m1.getNcols() != m2.getNrows())
			throw new Exception("The matrixes cannot be multiplied");
		
		Matrix m = new Matrix(m1.getNrows(), m2.getNcols());
		
		for(int i=0; i<m1.getNrows();i++) {
			for(int j=0; j<m2.getNcols();j++) {			
				m.setValueAt(i, j, MultiplyRowColum(m1, m2,i,j));
			}
		}
		
		return m;
	};
	public static Matrix Substract(Matrix m, double v) {
		Matrix n = new Matrix(m.getNrows(),m.getNcols());
		for(int i=0;i<n.getNrows(); i++) {
			for(int j=0; j<n.getNcols();j++) {
				n.setValueAt(i, j, m.getValueAt(i, j)-v);
			}
		}
		return n;
	}
	public static double MultiplyRowColum(Matrix m1, Matrix m2, int row, int column) {
		
		double v = 0.0;
		for(int i=0; i<m1.getNcols(); i++) {
				
				v += m1.getValueAt(row, i) *m2.getValueAt(i, column);
			}
		
		
		return v;
	}
	
}
