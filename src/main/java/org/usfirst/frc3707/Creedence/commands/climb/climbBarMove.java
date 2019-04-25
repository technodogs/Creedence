// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3707.Creedence.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3707.Creedence.Robot;

/**
 *
 */
public class climbBarMove extends Command {

    private double currentVacuumPressure = 0;

    public climbBarMove() {    
        requires(Robot.climbSubsystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        //commenting out controlGrab so that we can use 7 and 8 for the new stage that they are making
        //Robot.climbSystem.controlGrab();
        if(Math.abs(Robot.oi.operatorController.getRightStickYValue()) > Math.abs(Robot.oi.operatorController.getRightStickXValue())){
            Robot.climbSubsystem.controlBar();
        }
        else {
            Robot.climbSubsystem.stopClimb();
        }

        
        if(Robot.oi.operatorController.getRightMenuButtonPressed()) {
            Robot.climbSubsystem.startVacuum();
        }
        if(Robot.oi.operatorController.getLeftMenuButtonPressed()) {
            Robot.climbSubsystem.stopVacuum();
            SmartDashboard.putBoolean("ClimbState", false);
        }
        // if you want to make the green light trigger earlier, lower the number in the if statement
        //if you want it to trigger later, increase the value

        if(SmartDashboard.getBoolean("Vacuum", false)) {
            currentVacuumPressure = Robot.climbSubsystem.vacuumPressure();

            SmartDashboard.putNumber("vacuumPressure", currentVacuumPressure);

            if(currentVacuumPressure < 1.5){
                SmartDashboard.putBoolean("ClimbState", false);
            }else if(currentVacuumPressure > 1.5){
                SmartDashboard.putBoolean("ClimbState", true);
            }
        }

        
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
