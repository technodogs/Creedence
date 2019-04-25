/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3707.Creedence.commands.autonomous;

import org.usfirst.frc3707.Creedence.Robot;
import org.usfirst.frc3707.Creedence.commands.drive.DriveBackwardsCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class ConditionalDriveBackward extends ConditionalCommand {
  public ConditionalDriveBackward() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    super(new DriveBackwardsCommand(0.5), new blankCommand());
  }

  @Override
  protected boolean condition() {
    return Robot.hatchSubsystem.getButton1() || Robot.hatchSubsystem.getButton2();
  }

}
