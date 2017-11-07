package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.model.LauncherPidOutput;
import org.usfirst.frc.team2635.robot.model.LauncherPidSource;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
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

	
	public Launcher() {
		flywheel0 = new CANTalon(RobotMap.LAUNCHER_0); //TODO: Set everything to real things
		flywheel1 = new CANTalon(RobotMap.LAUNCHER_1);
		
		flywheel0.changeControlMode(TalonControlMode.Speed);
		flywheel0.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		flywheel0.reverseSensor(true);
		//flywheel0.configEncoderCodesPerRev(250);
		
		flywheel1.changeControlMode(TalonControlMode.Speed);
		flywheel1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		flywheel1.reverseSensor(true);
		//flywheel1.configEncoderCodesPerRev(250);
		
		flywheel0.setF(RobotMap.LauncherF);
        flywheel0.setP(RobotMap.LauncherP);
        flywheel0.setI(RobotMap.LauncherI); 
        flywheel0.setD(RobotMap.LauncherD);
		
        flywheel1.setF(RobotMap.LauncherF);
        flywheel1.setP(RobotMap.LauncherP);
        flywheel1.setI(RobotMap.LauncherI); 
        flywheel1.setD(RobotMap.LauncherD);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startLauncher(double speed) {
    	flywheel0.set(speed);
    	flywheel1.set(-speed);
    	//System.out.println("Setpoint set to: " + speed);
    }
}

