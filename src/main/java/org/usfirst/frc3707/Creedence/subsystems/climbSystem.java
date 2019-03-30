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
import org.usfirst.frc3707.Creedence.Configuration.Constants;
import org.usfirst.frc3707.Creedence.commands.climbCommands.climbBarMove;
import org.usfirst.frc3707.Creedence.commands.liftCommands.liftUpAndDown;
import org.usfirst.frc3707.Creedence.lidar.Lidar;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class climbSystem extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private PWMVictorSPX climbBar = new PWMVictorSPX(Constants.climbBar);
    private PWMVictorSPX climbGrab = new PWMVictorSPX(Constants.climbGrab);
    private PWMVictorSPX climbPullForward = new PWMVictorSPX(Constants.climbPullForward);


    
    // public PIDController liftController = new PIDController(.2, 0, 0, lidarCrab,
    // elevator, .02);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public climbSystem() {
        climbBar.stopMotor();
        // SmartDashboard.putNumber("P", .2);
        // SmartDashboard.putNumber("I", 0);
        // SmartDashboard.putNumber("D", 0);

    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        //commenting out default command so all old climb code is ignored. We also commented out 7 and 8 buttons in climbbarmove
        //setDefaultCommand(new climbBarMove());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void controlBar(){
        if(Robot.oi.joystick2.getRawAxis(5) > 0.2 || Robot.oi.joystick2.getRawAxis(5) < -0.2) {
            climbBar.set(Robot.oi.joystick2.getRawAxis(5));
        }
        else {
            climbBar.set(0);
        }

    }
    public void controlGrab(){
        if(Robot.oi.joystick2.getRawButton(7)){

            climbGrab.set(1);
        } else if (Robot.oi.joystick2.getRawButton(8)){
            climbGrab.set(-1);
        } else climbGrab.set(0);
    }
    public void controlPullForward(){
        climbPullForward.set(Robot.oi.joystick2.getRawAxis(4));
    }

    /**
     * This method operates the teleop control of the game piece mechanism
     */
    

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}
