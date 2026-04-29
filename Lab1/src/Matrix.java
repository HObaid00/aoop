import java.util.Arrays;
import java.util.Random;


public class Matrix {

    private int r;
    private int c;
    private double[][] elements;

    public Matrix(int r, int c){
        this.r = r;
        this.c = c;
        elements = new double[r][c];
    }

    public void setElement(int r, int c, double value) {
        elements[r][c] = value;
    }

    public double getElement( int r, int c) {
        return elements[r][c];
    }

    public Matrix addMatrix (Matrix b){
        if(this.r != b.r ||this.c != b.c ){
            System.out.println("Invalid matrices to create a product");
        }

        int row = this.r;
        int colum = b.c;
        Matrix sum = new Matrix(row, colum);

        for(int x = 0; x < row; x++){
            for(int y = 0; y < colum; y++){
                double value = this.elements[x][y] + b.elements[x][y];
                sum.setElement(x, y, value );
                elements[x][y] = getElement(x,y);
            }
        }
        return sum;
    }

    public Matrix multiplyMatrix(Matrix b){
        if(this.c != b.r){
            System.out.println("invalid input");
        }
        int row = this.r;
        int col = b.c;
        Matrix product = new Matrix(row, col);
        double value = 0;

        for(int i = 0; i < this.r; i++){
            for(int j = 0; j < b.c; j++){
                for(int k = 0; k < b.r; k++){
                    value += this.elements[i][k] * b.elements[k][j];
                }
                product.setElement(i, j, value);
                elements[i][j] = getElement(i,j);
                value = 0;
            }
        }
        return product;
    }

    //create a random matrix from zero to an upper limit
    public void createRandomMatrix (int max){

        Random randomizer = new Random();
        for(int x = 0; x< r; x++){
            for (int y = 0; y < c; y++){
                int value = randomizer.nextInt(max);
                this.setElement(x,y,value);
            }
        }
    }

    @Override
    public String toString() {
        System.out.println();
        return "Matrix{" +
                "r=" + r +
                ", c=" + c +
                ", elements=" + Arrays.deepToString(elements) +
                '}';

    }


}
