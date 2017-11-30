package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pike extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	DoubleSolenoid pike;
	
	public Pike() {
		pike = new DoubleSolenoid(0,1);
		pikeIn();
	}
	
	public void pikeOut() {
		pike.set(Value.kForward);
		System.out.println("Pike pikeOut() called");
	}
	
	public void pikeIn() {	
		pike.set(Value.kReverse);
		System.out.println("Pike pikeIn() called");
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

