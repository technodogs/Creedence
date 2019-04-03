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

public class PixyDriveAssist extends Command {

  double centerOfPixy = 50; //one sec

  private Vector[] vectors;

  public PixyDriveAssist() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    requires(Robot.m_pixy);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    this.vectors = Robot.m_pixy.findVectors();

    double pixyDriveAssist = 0;

    double x0 = vectors[0].getX0();
    double x1 = vectors[0].getX1();

    double x_difference = ((x0 + x1) / 2);

    if (x_difference > 3)
    {
      double errorOutput = Robot.driveSubsystem.computePIDPower(x_difference, centerOfPixy);
      pixyDriveAssist = errorOutput * 3.75; //3.75 is the found value for nice movement
    }

    Robot.driveSubsystem.driveAssist(pixyDriveAssist, -Robot.oi.driverController.getLeftStickYValue(),
    -Robot.oi.driverController.getRightStickXValue(), 0,
    Robot.oi.driverController.getRightBumperPressed(), Robot.oi.driverController.getXButtonPressed());

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
