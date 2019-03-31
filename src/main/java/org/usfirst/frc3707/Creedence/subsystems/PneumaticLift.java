/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3707.Creedence.subsystems;

import org.usfirst.frc3707.Creedence.Configuration.Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class PneumaticLift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DoubleSolenoid pneumaticStage = new DoubleSolenoid(Constants.PneumaticSystem.getPcmCanId(), Constants.LiftSystem.getPneumaticLiftForward(), Constants.LiftSystem.getPneumaticLiftReverse());

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void slideUp()
  {
    pneumaticStage.set(Value.kForward);
  }
  public void slideDown()
  {
    pneumaticStage.set(Value.kReverse);
  }
}