//package maven.sdm.triangle;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class TriangleApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	        System.out.println("------Triangle application Started------");
	        TriangleApp t1 = new TriangleApp();
	        System.out.println("Please Enter the values between 1 to 8");
	        System.out.println("Enter the value side a :");
	        Scanner scanner = new Scanner(System.in);
	       	String a = scanner.nextLine();
	        int side_a = validateSidesValue(Integer.parseInt(a));
	        System.out.println("Enter the value side b :");
	        String b = scanner.nextLine();
	        int side_b = validateSidesValue(Integer.parseInt(b));
	        System.out.println("Enter the value side c :");
	        String c = scanner.nextLine();
	        int side_c = validateSidesValue(Integer.parseInt(c));
	        boolean isATriangle = t1.isATriangle(side_a,side_b,side_c);
			
			
			if (isATriangle){
				System.out.println("------Start Drawing the triangle------");
				
				final Triangle t = new Triangle(side_a + 100, side_b +100, side_c + 100);
				final Point translation = new Point(100, 400);
	
				JFrame frame = new JFrame("Test");
	
				frame.add(new JComponent() {
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);
	
						Point[] p = t.triangle();
	
						g.translate(translation.x, translation.y);
	
						for (int i = 0; i < p.length; i++)
							g.drawLine(p[i].x, 
									   p[i].y, 
									   p[(i+1) % p.length].x, 
									   p[(i+1) % p.length].y);
					}
				});
	
				frame.setSize(800, 600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);

			}
			System.out.println("------Finished------");
	        
	    }

	    public static int validateSidesValue(int side){

	        while (true){
	                boolean  status = validateSidesValueInRange(side);
	                if (!status){
	                    System.out.println("The Given value is not in the range 1 to 8");
	                    System.out.println("Please Enter the values between 1 To 8");
	                    String a = System.console().readLine();
	                    side = Integer.parseInt(a);
	                }else{
	                    break;
	                }
	            }
	            return side;
	    }

	    public static boolean validateSidesValueInRange(int side){
	        boolean isRange = true;
	            if (side > 8 || side ==0){
	                isRange = false;
	            }
	        return isRange;
	    }

	    public boolean isATriangle(int a,int b,int c){
	        boolean isTriangle = false;
	        System.out.println("Given values are a :"+a+" b :"+b+" c :"+c);
	        if(((a + b) > c)  &&   ((c + b) > a)  &&  ((a + c) > b)   ){
	            System.out.println("True , Given sides Form the Triangle");
	            return true;
	        }
	        System.out.println("False , Given sides Failes to Form the Triangle");
	        return isTriangle;
	    }

}



class Triangle {
    double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double aAngle() {
        return Math.acos(-(Math.pow(a, 2) - Math.pow(b, 2) - Math.pow(c, 2)) / (2 * b * c));
    }
    public void getName() {
    	System.out.println("This is name");
    }

    public Point[] triangle() {

        double angle = aAngle();

        Point[] p = new Point[3];

        p[0] = new Point(0, 0);
        p[1] = new Point((int) b, 0);
        p[2] = new Point((int) (Math.cos(angle) * c), (int) (Math.sin(angle) * c));

        Point center = new Point((p[0].x + p[1].x + p[2].x) / 3, 
                                 (p[0].y + p[1].y + p[2].y) / 3);

        for (Point a : p)
            a.translate(-center.x, -center.y);

        return p;
    }
}

