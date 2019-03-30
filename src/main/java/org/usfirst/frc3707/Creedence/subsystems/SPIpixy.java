/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3707.Creedence.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc3707.Creedence.pixy2API.*;
import java.util.ArrayList;
import org.usfirst.frc3707.Creedence.pixy2API.Pixy2Line.Vector;
import org.usfirst.frc3707.Creedence.commands.GrabLine;

/**
 * Add your docs here.
 */
public class SPIpixy extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final Pixy2 pixy; 
  public final static int PixyResult = 0;


  private static Vector[] vectors;
  
  public SPIpixy() {
    pixy = Pixy2.createInstance(Pixy2.LinkType.SPI);
    pixy.init();
    pixy.changeProg("line_tracking".toCharArray());
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new TargetMode());
  }
  public Vector[] findVectors() {
    pixy.getLine().getAllFeatures(); //for some reason we need this because the guy couldn't get it correct himself
    return(pixy.getLine().getVectors());
  }
}