package org.usfirst.frc.team2635.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Launcher extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon flywheel0;
	CANTalon flywheel1;
	PIDController pid0;
	PIDController pid1;
	Encoder encoder0;
	Encoder encoder1;
	
	public Launcher() {
		flywheel0 = new CANTalon(0); //TODO: Set everything to real things
		flywheel1 = new CANTalon(0);
		
		encoder0 = new Encoder(null, null);
		encoder1 = new Encoder(null, null);
		
		pid0 = new PIDController(0, 0, 0, 0, null, flywheel0); 
		pid1 = new PIDController(0, 0, 0, 0, null, flywheel1); 
		
		pid0.enable();
		pid1.enable();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	
}

