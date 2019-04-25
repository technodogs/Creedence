/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3707.Creedence.commands.drive;

import org.usfirst.frc3707.Creedence.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class DriveBackwardsCommand extends TimedCommand {

  double speed = -.5;
  double angle = 180;

  public DriveBackwardsCommand() {

    this(.5);
  }

  public DriveBackwardsCommand(double timeOut) {

    super(timeOut);

    requires(Robot.driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    Robot.driveSubsystem.enable();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.driveSubsystem.driveSimple(speed, angle);
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
