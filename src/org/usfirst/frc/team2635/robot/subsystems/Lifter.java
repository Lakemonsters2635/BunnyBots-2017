package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lifter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon rotate;
	DoubleSolenoid vertical;
	DoubleSolenoid horizontal;
	
	public Lifter() {
		//rotate = new CANTalon(0); //TODO: Give a real constants
		
		vertical = new DoubleSolenoid(RobotMap.LIFT_CHANNEL_0, RobotMap.LIFT_CHANNEL_1);
		horizontal = new DoubleSolenoid(RobotMap.GRAB_CHANNEL_0,RobotMap.GRAB_CHANNEL_1);
		liftUp();
		clampOut();
	}
	
	public void rotate() {
		
	}
	
	public void liftUp() {
		vertical.set(Value.kReverse);
	}
	
	public void liftDown() {
		vertical.set(Value.kForward);
	}
	
	public void clampIn() {
		horizontal.set(Value.kForward);
	}
	
	public void clampOut() {
		horizontal.set(Value.kReverse);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

