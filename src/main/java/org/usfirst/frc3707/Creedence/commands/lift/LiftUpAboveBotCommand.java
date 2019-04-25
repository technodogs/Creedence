/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3707.Creedence.commands.lift;

import javax.lang.model.util.ElementScanner6;

import org.usfirst.frc3707.Creedence.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftUpAboveBotCommand extends Command {
  public LiftUpAboveBotCommand() {
    requires(Robot.liftSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.liftSubsystem.liftController.enable();
    Robot.liftSubsystem.liftController.setSetpoint(37);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (Robot.liftSubsystem.getLiftHeight() > 59) {
      return true;
    }
    else if (Robot.liftSubsystem.liftController.onTarget())
    {
      return true;
    }
    else
    {
      return Math.abs(Robot.oi.operatorController.getLeftStickYValue()) > 0.2;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    Robot.liftSubsystem.liftController.disable();
    Robot.liftSubsystem.holdLift();

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
