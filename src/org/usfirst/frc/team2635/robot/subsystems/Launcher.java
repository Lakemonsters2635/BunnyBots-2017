package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.model.LauncherPidOutput;
import org.usfirst.frc.team2635.robot.model.LauncherPidSource;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

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
	public LauncherPidSource pidSource0;
	public LauncherPidSource pidSource1;
	public LauncherPidOutput pidOutput0;
	public LauncherPidOutput pidOutput1;
	
	public Launcher() {
		flywheel0 = new CANTalon(RobotMap.LAUNCHER_0); //TODO: Set everything to real things
		flywheel1 = new CANTalon(RobotMap.LAUNCHER_1);
		
		flywheel0.changeControlMode(TalonControlMode.Speed);
		flywheel0.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		flywheel0.configEncoderCodesPerRev(250);
		
		flywheel1.changeControlMode(TalonControlMode.Speed);
		flywheel1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		flywheel1.configEncoderCodesPerRev(250);
		
		pidSource0 = new LauncherPidSource(flywheel0);
		pidSource1 = new LauncherPidSource(flywheel1);
		
		pidOutput0 = new LauncherPidOutput(flywheel0);
		pidOutput1 = new LauncherPidOutput(flywheel1);
		
		pid0 = new PIDController(0, 0, 0, 0, pidSource0, pidOutput1); 
		pid1 = new PIDController(0, 0, 0, 0, pidSource1, pidOutput0); 
		
		pid0.enable();
		pid1.enable();
		
		pid0.setSetpoint(0);
		pid1.setSetpoint(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	
}

