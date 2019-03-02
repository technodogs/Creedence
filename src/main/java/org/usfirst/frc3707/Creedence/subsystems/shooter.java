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


import org.usfirst.frc3707.Creedence.PIDInts.Constants;
import org.usfirst.frc3707.Creedence.commands.Shoot;

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

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public shooter() {
        leftSpit = new PWMVictorSPX(Constants.LCargoSpit);
        addChild("leftSpit", leftSpit);
        leftSpit.setInverted(false);

        rightSpit = new PWMVictorSPX(Constants.RCargoSpit);
        addChild("rightSpit", rightSpit);
        rightSpit.setInverted(false);

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

    }
    public void reSucc(){
        
            leftSpit.set(-org.usfirst.frc3707.Creedence.Robot.oi.joystick2.getRawAxis(2));
            rightSpit.set(org.usfirst.frc3707.Creedence.Robot.oi.joystick2.getRawAxis(2));
        
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

