// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3707.Creedence.subsystems;

import org.usfirst.frc3707.Creedence.Robot;
import org.usfirst.frc3707.Creedence.PIDInts.Constants;
import org.usfirst.frc3707.Creedence.commands.Drive;
import org.usfirst.frc3707.Creedence.lidar.Lidar;
import org.usfirst.frc3707.Creedence.swerve.SwerveDrive;
import org.usfirst.frc3707.Creedence.swerve.SwerveWheel;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {
    
    private AnalogPotentiometer frontRightEncoder = new AnalogPotentiometer(Constants.FREncoder, 360.0, 0.0);
    private VictorSP frontRightSwerve = new VictorSP(Constants.FRSwerve);
    private PIDController frontRightTwist = new PIDController(0.05, 0.0, 0.0, 0.0, frontRightEncoder, frontRightSwerve, 0.02);
    private AnalogPotentiometer frontLeftEncoder = new AnalogPotentiometer(Constants.FLEncoder, 360.0, 0.0);;
    private VictorSP frontLeftSwerve = new VictorSP(Constants.FLSwerve);
    private PIDController frontLeftTwist = new PIDController(0.05, 0.0, 0.0, 0.0, frontLeftEncoder, frontLeftSwerve, 0.02);;
    private AnalogPotentiometer backRightEncoder = new AnalogPotentiometer(Constants.BREncoder, 360.0, 0.0);
    private VictorSP backRightSwerve = new VictorSP(Constants.BrSwerve);
    private PIDController backRightTwist = new PIDController(0.05, 0.0, 0.0, 0.0, backRightEncoder, backRightSwerve, 0.02);
    private AnalogPotentiometer backLeftEncoder = new AnalogPotentiometer(Constants.BLEncoder, 360.0, 0.0);
    private VictorSP backLeftSwerve = new VictorSP(Constants.BLSwerve);
    private PIDController backLeftTwist = new PIDController(0.05, 0.0, 0.0, 0.0, backLeftEncoder, backLeftSwerve, 0.02);
    private VictorSP frontRightDrive = new VictorSP(Constants.FRDrive);
    private VictorSP frontLeftDrive = new VictorSP(Constants.FLDrive);
    private VictorSP backRightDrive= new VictorSP(Constants.BRDrive);
    private VictorSP backLeftDrive= new VictorSP(Constants.BLDrive);

    private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    Lidar lidarCrab = new Lidar(new DigitalInput(Constants.lidar1));
    
    private SwerveWheel frontLeftWheel = new SwerveWheel(frontLeftTwist, frontLeftDrive, 117);
    private SwerveWheel frontRightWheel = new SwerveWheel(frontRightTwist, frontRightDrive, 302);
    private SwerveWheel backLeftWheel = new SwerveWheel(backLeftTwist, backLeftDrive, 312);
    private SwerveWheel backRightWheel = new SwerveWheel(backRightTwist, backRightDrive, 278);
    public SwerveDrive swerve = new SwerveDrive(frontRightWheel, frontLeftWheel, backLeftWheel, backRightWheel, gyro);
    
   // LiveWindow.addSensor("Sensors", "gyro", gyro);

    
    public void init() {

        frontRightTwist.setInputRange(0.0, 360.0);
        frontRightTwist.setOutputRange(-1.0, 1.0);
        frontRightTwist.setContinuous(true);
        
        frontLeftTwist.setInputRange(0.0, 360.0);
        frontLeftTwist.setOutputRange(-1.0, 1.0);
        frontLeftTwist.setContinuous(true);
        
        backLeftTwist.setInputRange(0.0, 360.0);
        backLeftTwist.setOutputRange(-1.0, 1.0);
        backLeftTwist.setContinuous(true);
        
        backRightTwist.setInputRange(0.0, 360.0);
        backRightTwist.setOutputRange(-1.0, 1.0);
        backRightTwist.setContinuous(true);

        backRightSwerve.isAlive();
        backLeftSwerve.isAlive();

        gyro.reset();

    }


    // SAVED MOTOR DECLARATIONS

  

    public void enable() {
    	frontLeftTwist.enable();
    	frontRightWheel.enable();
    	backLeftWheel.enable();
        backRightWheel.enable();
        System.out.println("Enable Twist");
    }
    public void disable() {
    	frontLeftTwist.disable();
    	frontRightWheel.disable();
    	backLeftWheel.disable();
    	backRightWheel.disable();
    }
    public void drive(double directionX, double directionY, double rotation, boolean useGyro, boolean slowSpeed) {
    	swerve.drive(directionX, directionY, rotation, useGyro, slowSpeed);


      
    }
    public void readGyro(){
        SmartDashboard.putData(gyro);
    }
    public void setTolerances(){
        //.setPercentTolerance(0);

    }

public void setLastTime(long x) {
    lastTime = x;
}

long lastTime;
public double output = 0;
public double errSum, lastErr = 0;
double kp = 0.003;
double ki = 0;
public double computePIDPower(double input, double setpoint) {
   /*How long since we last calculated*/
   long now = System.currentTimeMillis();
   double timeChange = (double)(now - lastTime);
   /*Compute all the working error variables*/
   double error = setpoint - input;
   errSum += (error * timeChange);
  
   /*Compute PID Output*/
   output = kp * error + ki * errSum;
  
   /*Remember some variables for next time*/
   lastErr = error;
   lastTime = now;

    return output;
}
  
void SetTunings(double Kp, double Ki) {
   kp = Kp;
   ki = Ki;
}

public void moveLeftOrRight(double power){
    swerve.driveSimple(power, 270);
}

    public void moveLeft(){
        swerve.driveSimple(-0.2, 270);
        //Robot.driveSystem.swerve.drive(-.3, 0, 0, false, false);
    }
    public void moveRight(){
        swerve.driveSimple(0.2, 270);
        //Robot.driveSystem.swerve.drive(.3, 0, 0, false, false);
    }
    
    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new Drive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        
        // setDefaultCommand(new MySpecialCommand());
    }

    public double lidarDistance() {
        return lidarCrab.getDistance();
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putNumber("Front Right Encoder", frontRightEncoder.get());
        SmartDashboard.putNumber("Front Left Encoder", frontLeftEncoder.get());
        SmartDashboard.putNumber("Back Right Encoder", backRightEncoder.get());
        SmartDashboard.putNumber("Back Left Encoder", backLeftEncoder.get());
        SmartDashboard.putNumber("LIDAR Value", lidarCrab.getDistance());

        //System.out.println(lidarCrab.getDistance());
    }
    

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    NetworkTableEntry x_pos;
    NetworkTableEntry y_pos;
    NetworkTableInstance inst = NetworkTableInstance.getDefault();;
    double[] realx_pos;
    NetworkTable pixyData = inst.getTable("PixyData");;
    double[] defaultValue = {-1};
    double error;
    double[] lastError = new double[10];
    
    NetworkTableEntry x0;
    NetworkTableEntry x1;

    double[] real_x0Array;
    double[] real_x1Array;

    /*public double getError ()
    {
        inst = NetworkTableInstance.getDefault();
        pixyData = inst.getTable("PixyData");

        x_pos = pixyData.getEntry("x_pos");
        y_pos = pixyData.getEntry("y_pos");
        
        realx_pos = x_pos.getDoubleArray(defaultValue);

        if (realx_pos.length > 0 && realx_pos[0] != -1)
        {
            error = Robot.driveSystem.computePIDPower(realx_pos[0], 159);
            System.out.println(error);
            return error;
        }
        System.out.println(error);
        return 0;
    }*/

    public double getSpinValue ()
    {
        x0 = pixyData.getEntry("x0");
        x1 = pixyData.getEntry("x1");

        real_x0Array = x0.getDoubleArray(defaultValue);
        real_x1Array = x1.getDoubleArray(defaultValue);

        double mid0 = 0;
        double mid1 = 0;

        for (int x = 0; x < real_x0Array.length; x++)
        {
            if (x == 0)
            {
                mid0 = real_x0Array[0];
            }
            else if (Math.abs(real_x0Array[x] - 50) < Math.abs(mid0 - 50))
            {
                mid0 = real_x0Array[x];
            }
        }

        for(int x = 0; x < real_x1Array.length; x++)
        {
            if (x == 0)
            {
                mid1 = real_x1Array[0];
            }
            else if (Math.abs(real_x1Array[x] - 50) < Math.abs(mid1 - 50))
            {
                mid1 = real_x1Array[x];
            }
        }

        if (mid0 < mid1 && Math.abs(mid0 - mid1) > 4)
        {
            System.out.println("Right");
            return (.25);
        }
        else if (Math.abs(mid0 - mid1) > 4)
        {
            System.out.println("Left");
            return (-.25);
        }
        
        return 0;
    }

    public double getError2 ()
    {
        x0 = pixyData.getEntry("x0");
        x1 = pixyData.getEntry("x1");

        real_x0Array = x0.getDoubleArray(defaultValue);
        real_x1Array = x1.getDoubleArray(defaultValue);

        double mid0 = 0;
        double mid1 = 0;

        for (int x = 0; x < real_x0Array.length; x++)
        {
            if (x == 0)
            {
                mid0 = real_x0Array[0];
            }
            else if (Math.abs(real_x0Array[x] - 50) < Math.abs(mid0 - 50))
            {
                mid0 = real_x0Array[x];
            }
        }

        for(int x = 0; x < real_x1Array.length; x++)
        {
            if (x == 0)
            {
                mid1 = real_x1Array[0];
            }
            else if (Math.abs(real_x1Array[x] - 50) < Math.abs(mid1 - 50))
            {
                mid1 = real_x1Array[x];
            }
        }

        double x_difference = ((mid0 + mid1) / 2);

        if (x_difference > 5)
        {
            error = Robot.driveTrain.computePIDPower(x_difference, 50);
            System.out.println(-error * 3);
            return -error * 3;
        }
        System.out.println(error);
        return 0;
    }

    public boolean getSee (){
        NetworkTableEntry seeHolder = pixyData.getEntry("object_detected");
        boolean see = seeHolder.getBoolean(false);
        return see;
    }
}

