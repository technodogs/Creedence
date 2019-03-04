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
import org.usfirst.frc3707.Creedence.PIDInts.Constants;
import org.usfirst.frc3707.Creedence.commands.liftUpAndDown;
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
public class liftSystem extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private PWMVictorSPX elevator = new PWMVictorSPX(Constants.Elevator);
    public Lidar lidarCrab = new Lidar(new DigitalInput(Constants.lidar1));
    public PIDController liftController = new PIDController(SmartDashboard.getNumber("P", .2),
            SmartDashboard.getNumber("I", 0.0), SmartDashboard.getNumber("D", 0.0), lidarCrab, elevator);
    // public PIDController liftController = new PIDController(.2, 0, 0, lidarCrab,
    // elevator, .02);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public liftSystem() {
        liftController.setAbsoluteTolerance(0.5);
        SmartDashboard.putNumber("Lift Height Value", 40);
        SmartDashboard.putNumber("P", .2);
        SmartDashboard.putNumber("I", 0);
        SmartDashboard.putNumber("D", 0);
        
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new liftUpAndDown());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    /**
     * This method operates the teleop control of the game piece mechanism
     */
    public void liftUpAndDown() {
        if (Robot.oi.joystick2.getRawAxis(1) > .3 || Robot.oi.joystick2.getRawAxis(1) < -.3) {
            elevator.set(Robot.oi.joystick2.getRawAxis(1));
        } else {
            holdLift();
        }
    }

    public void liftUp() {
        elevator.set(-.5);
    }

    public void liftDown() {
        elevator.set(.5);
    }

    public void holdLift() {
        elevator.set(0.1);
    }

    public void liftUpOrDown(double power) {
        elevator.set(power);
    }

    public double getTargetHeight() {
        return SmartDashboard.getNumber("Lift Height Value", 40);
    }

    public double getLiftHeight() {
        return lidarCrab.getDistance();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("lidar #", lidarCrab.getDistance());
        // Put code here to be run every loop
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}
