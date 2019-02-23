package org.usfirst.frc3707.Creedence.swerve;

import org.usfirst.frc3707.Creedence.Robot;

import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveDrive implements PIDOutput {

    private SwerveWheel rightFrontWheel;
    private SwerveWheel leftFrontWheel;
    private SwerveWheel leftBackWheel;
    private SwerveWheel rightBackWheel;
    
    private GyroBase gyro;

    private double wheelbase = 22.5;
    private double trackwidth = 24.5;

    public SwerveDrive(SwerveWheel rightFront, SwerveWheel leftFront, SwerveWheel leftBack, SwerveWheel rightBack, GyroBase gyro){
    	this.rightFrontWheel = rightFront;
    	this.leftFrontWheel = leftFront;
    	this.leftBackWheel = leftBack;
        this.rightBackWheel = rightBack;
        
        System.out.println("SwerveDrive Initialized");
	    
        this.gyro = gyro;
    }
    
    public void drive(double directionX, double directionY, double rotation, boolean useGyro, boolean slowSpeed, boolean noPush) {
    	
    	SmartDashboard.putNumber("directionX", directionX);
    	SmartDashboard.putNumber("directionY", directionY);
        SmartDashboard.putNumber("rotation", rotation);
        // System.out.println(rotation);
        // System.out.println(directionX);
        // System.out.println(directionY);
        
        if(noPush){
            //Puts Motors into X formation and disables motors
            this.rightFrontWheel.updateSpeed(0);
        	this.leftFrontWheel.updateSpeed(0);
        	this.leftBackWheel.updateSpeed(0);
            this.rightBackWheel.updateSpeed(0);
            
            this.rightFrontWheel.updateRotation(45+90);
        	this.leftFrontWheel.updateRotation(315+90);
        	this.leftBackWheel.updateRotation(225+90);
        	this.rightBackWheel.updateRotation( 135+90);
        	return;
        }
        
    	
    	//if BOTH joystick in the center
    	if((directionX < 0.2 && directionX > -0.2) && (directionY < 0.2 && directionY > -0.2) && (rotation < 0.2 && rotation > -0.2)) {
    		this.rightFrontWheel.updateSpeed(0);
        	this.leftFrontWheel.updateSpeed(0);
        	this.leftBackWheel.updateSpeed(0);
        	this.rightBackWheel.updateSpeed(0);
        	return;
    	}
    	//if ROTATION joystick only near the center (this fixes the rotation drift)
    	else if (rotation < 0.2 && rotation > -0.2) {
    		rotation = 0;
    	}

    	
    	double L = this.wheelbase; 					//distance between front and back wheels
    	double W = this.trackwidth; 				//distance between front wheels
        double diameter = Math.sqrt ((L * L) + (W * W)); 	//radius of circle (actually it may be the diameter?)
        
        directionY *= -1; 							//invert Y
        directionX *= -1; 							//invert X
        rotation *= -1;

        double a = directionX - rotation * (L / diameter); //rear axle
        double b = directionX + rotation * (L / diameter); //front axle
        double c = directionY - rotation * (W / diameter); //left track
        double d = directionY + rotation * (W / diameter); //right track
        
        // SmartDashboard.putNumber("a", a);
        // SmartDashboard.putNumber("b", b);
        // SmartDashboard.putNumber("c", c);
        // SmartDashboard.putNumber("d", d);
        
        
        // System.out.println("A, B, C, and D");
        // System.out.println("A: " + a);
        // System.out.println("B: " + b);
        // System.out.println("C: "+c);
        // System.out.println("D: " + d);



        /*
         *                FRONT
         * 
         *            c          d
         *            | 		 |
         *       b ------------------ b
         *            |          |
         *            |          |
         * LEFT       |          |      RIGHT
         *            |          |
         *            |          |
         *       a ------------------ a
         *            |          |
         *            c          d
         * 
         *                BACK
         */

        //set motor speeds for each wheel
        double backRightSpeed = Math.sqrt ((a * a) + (d * d));
        double backLeftSpeed = Math.sqrt ((a * a) + (c * c));
        double frontRightSpeed = Math.sqrt ((b * b) + (d * d));
        double frontLeftSpeed = Math.sqrt ((b * b) + (c * c));
        
        //set wheel angle for each wheel
        double backRightAngle = (Math.atan2 (a, d) / Math.PI) * 180;
        double backLeftAngle = (Math.atan2 (a, c) / Math.PI) * 180;
        double frontRightAngle = (Math.atan2 (b, d) / Math.PI) * 180;
        double frontLeftAngle = (Math.atan2 (b, c) / Math.PI) * 180;

        // SmartDashboard.putNumber("frontLeft@", frontLeftAngle);
        // SmartDashboard.putNumber("frontRight@", frontRightAngle);
        // SmartDashboard.putNumber("backLeft@", backLeftAngle);
        // SmartDashboard.putNumber("backRight@", backRightAngle);

        
        

        //System.out.println("backLeftAngle " + backLeftAngle);
        // System.out.println("backRightAngle " + backRightAngle);
        // System.out.println("frontRightAngle " + frontRightAngle);
        // System.out.println("FrontLeftAngle " + frontLeftAngle );

        
        // System.out.println("Back Right Speed: " + backRightSpeed);
        // System.out.println("Back Left Speed: " + backLeftSpeed);
        // System.out.println("Front Right Speed: " + frontRightSpeed);
        // System.out.println("Front Left Speed: " + frontLeftSpeed);
        
        // System.out.println("Back Right Angle: " + backRightAngle);
        // System.out.println("Back Left Angle: " + backLeftAngle);
        // System.out.println("Front Right Angle: " + frontRightAngle);
        // System.out.println("Front Left Angle: " + frontLeftAngle);
        
        if(useGyro) {
            double gyroAngle = normalizeGyroAngle(gyro.getAngle()); 
            backRightAngle += gyroAngle;
            backLeftAngle += gyroAngle;
            frontRightAngle += gyroAngle;
            frontLeftAngle += gyroAngle;
        }
        if(slowSpeed) {
        	backRightSpeed *= 0.5;
        	backLeftSpeed *= 0.5;
        	frontRightSpeed *= 0.5;
        	frontLeftSpeed *= 0.5;
        }
        if(noPush){
            //Puts Motors into X formation and disables motors
            frontRightAngle = 45+90;
            frontLeftAngle = 315+90;
            backLeftAngle = 225+90;
            backRightAngle = 135+90;
            
            backRightSpeed = 0;
            backLeftSpeed = 0;
            frontRightSpeed = 0;
            frontLeftSpeed = 0;

        }
        

        
        // SmartDashboard.putNumber("frontRightAngleXX", frontRightAngle);
    	// SmartDashboard.putNumber("frontLeftAngleXX", frontLeftAngle);
    	// SmartDashboard.putNumber("backRightAngleXX", backRightAngle);
        // SmartDashboard.putNumber("backLeftAngleXX", backLeftAngle);
        
        //update the actual motors
    	this.rightFrontWheel.drive(frontRightSpeed, frontRightAngle);
    	this.leftFrontWheel.drive(frontLeftSpeed, frontLeftAngle);
        this.leftBackWheel.drive(backLeftSpeed, backLeftAngle);
        this.rightBackWheel.drive(backRightSpeed, backRightAngle);
        
    }
    

	@Override
	public void pidWrite(double output) {
		System.out.println("X");
		System.out.println(output);
        drive(output, 0, 0, false, false, false);
	}

    public double normalizeGyroAngle(double angle){
        return (angle - (Math.floor( angle / 360) * 360) );
    }

    public void driveSimple(double speed, double angle) {
        this.rightFrontWheel.drive(speed, angle);
		this.leftFrontWheel.drive(speed, angle);
		this.leftBackWheel.drive(speed, angle);
        this.rightBackWheel.drive(speed, angle);
    }

}
