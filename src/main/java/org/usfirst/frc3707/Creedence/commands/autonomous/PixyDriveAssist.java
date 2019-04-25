/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3707.Creedence.commands.autonomous;

import org.usfirst.frc3707.Creedence.Robot;
import org.usfirst.frc3707.Creedence.pixy2API.Pixy2Line.Vector;
import org.usfirst.frc3707.Creedence.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PixyDriveAssist extends Command {               

  double speedIncrease =  -5.25; //3.5 last checked value
  double centerOfPixy = 40; //best use of 47 (larger is to the left)
  double rotationIncrease = 2;
  
  double rightCenterOfPixy = 0;
  double leftCenterOfPixy = 0;


  //For Translation
  public double output = 0;
  public double errSum, lastErr = 0;
  //For Rotation
  public double outputR = 0;
  public double errSumR, lastErrR = 0;

  long lastTime;
  double kp = 0.003;
  double ki = 0;


  private Vector[] vectors;

  
  public PixyDriveAssist() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    requires(Robot.driveSubsystem);
    
  }
  

  /**
   * A method to compute PID motor power.
   * 
   * @return A double indicating how much to power a motor
   * @param input    This is the current value being received from the sensor
   * @param setpoint The desired setpoint
   */
  public double computePIDPower(double input, double setpoint) {
    /* How long since we last calculated */
    long now = System.currentTimeMillis();
    double timeChange = (double) (now - lastTime);
    /* Compute all the working error variables */
    double error = setpoint - input;
    errSum += (error * timeChange);

    /* Compute PID Output */
    output = kp * error + ki * errSum;

    /* Remember some variables for next time */
    lastErr = error;
    lastTime = now;

    return output;
  }

  /**
   * A method to compute PID motor power.
   * 
   * @return A double indicating how much to power a motor
   * @param input    This is the current value being received from the sensor
   * @param setpoint The desired setpoint
   */
  public double computeRotationPIDPower(double input, double setpoint) {
    /* How long since we last calculated */
    long now = System.currentTimeMillis();
    double timeChange = (double) (now - lastTime);
    /* Compute all the working error variables */
    double error = setpoint - input;
    errSumR += (error * timeChange);

    /* Compute PID Output */
    outputR = kp * error + ki * errSumR;

    /* Remember some variables for next time */
    lastErrR = error;
    lastTime = now;

    return outputR;
  }

  private double getAngle(Vector line)
  {
    double angle = Math.toDegrees(Math.atan2(line.getY0() - line.getY1(), line.getX0() - line.getX1()));

    return angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    this.vectors = Robot.m_pixy.findVectors();

    //If we dont see the line, drive normal and return
    if(this.vectors == null) {
      Robot.oi.driveByJoystick(-Robot.oi.driverController.getLeftStickXValue(), -Robot.oi.driverController.getRightStickXValue());
      SmartDashboard.putBoolean("Pixy", false);
      return;
    }
    SmartDashboard.putBoolean("Pixy", false);

    double pixyDriveAssist = 0;

    double x0 = vectors[0].getX0();
    double x1 = vectors[0].getX1();

    double x_midpoint = ((x0 + x1) / 2);

    if (x_midpoint > .2)
    {
      double errorOutput = 0;

      errorOutput = computePIDPower(x_midpoint, centerOfPixy);

      pixyDriveAssist = errorOutput * speedIncrease;
      SmartDashboard.putNumber("midPoint", x_midpoint);
      SmartDashboard.putBoolean("Pixy", true);
    
      //Beginning of Rotation Assist on Pixy

      double pixyRotationAssist = 0;

      double angle = getAngle(vectors[0]);
      pixyRotationAssist = computeRotationPIDPower(angle, 90);

      //System.out.println(getAngle(vectors[0]));
      SmartDashboard.putNumber("My Angle", angle);
      SmartDashboard.putNumber("Angle PID", pixyRotationAssist);
    }

    Robot.oi.driveByJoystick(pixyDriveAssist, -Robot.oi.driverController.getRightStickXValue());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
