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


import org.usfirst.frc3707.Creedence.Configuration.Constants;
import org.usfirst.frc3707.Creedence.commands.cargoCommands.Shoot;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class shooter extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private PWMVictorSPX leftSpit;
    private PWMVictorSPX rightSpit;

    private Boolean invertLeftMotor = false;
    private Boolean invertRightMotor = false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public shooter() {
        leftSpit = new PWMVictorSPX(Constants.CargoSystem.getLCargoSpit());
        addChild("leftSpit", leftSpit);
        leftSpit.setInverted(Constants.CargoSystem.getLSpitInverted());

        rightSpit = new PWMVictorSPX(Constants.CargoSystem.getRCargoSpit());
        addChild("rightSpit", rightSpit);
        rightSpit.setInverted(Constants.CargoSystem.getRSpitInverted());

    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new Shoot());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void spit() {
        leftSpit.set(org.usfirst.frc3707.Creedence.Robot.oi.joystick2.getRawAxis(3));
        rightSpit.set(-org.usfirst.frc3707.Creedence.Robot.oi.joystick2.getRawAxis(3));

        // This is my idea for handling motor inversion
        // Double stickVal = org.usfirst.frc3707.Creedence.Robot.oi.joystick2.getRawAxis(3);
        // leftSpit.set(invertLeftMotor ? stickVal*-1 : stickVal);
        // rightSpit.set(invertRightMotor ? stickVal*-1 : stickVal);

    }

    public void reSucc() {

        leftSpit.set(-org.usfirst.frc3707.Creedence.Robot.oi.joystick2.getRawAxis(2));
        rightSpit.set(org.usfirst.frc3707.Creedence.Robot.oi.joystick2.getRawAxis(2));

        // This is my idea for handling motor inversion
        // Double stickVal = org.usfirst.frc3707.Creedence.Robot.oi.joystick2.getRawAxis(2);
        // leftSpit.set(invertLeftMotor ? stickVal*-1 : stickVal);
        // rightSpit.set(invertRightMotor ? stickVal*-1 : stickVal);

    }
    public void slowSpit(){
        if(org.usfirst.frc3707.Creedence.Robot.oi.joystick2.getRawButton(6) == true){
            leftSpit.set(.4);
            rightSpit.set(-.4);
        }
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}
