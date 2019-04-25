/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3707.Creedence.commands.autonomous;

import org.usfirst.frc3707.Creedence.Robot;
import org.usfirst.frc3707.Creedence.pixy2API.Pixy2Line.Vector;

import edu.wpi.first.wpilibj.command.Command;

public class PixyContrast extends Command {

  double difference = 1000;

  public PixyContrast() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Vector myVector = Robot.m_pixy.findVectors()[0];

    difference = (myVector.getX0() - myVector.getX1()) / 2;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    return (difference > -1.5 && difference < 1.5);

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
