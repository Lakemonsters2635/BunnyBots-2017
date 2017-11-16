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
	public CANTalon flywheel0;
	public CANTalon flywheel1;

	
	public Launcher() {
		flywheel0 = new CANTalon(RobotMap.LAUNCHER_0); //TODO: Set everything to real things
		flywheel1 = new CANTalon(RobotMap.LAUNCHER_1);
		
		flywheel0.changeControlMode(TalonControlMode.Speed);
		//flywheel0.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		
		flywheel0.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		flywheel0.reverseSensor(false);
		flywheel0.configEncoderCodesPerRev(4096);
		
		flywheel1.changeControlMode(TalonControlMode.Speed);
		flywheel1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		flywheel1.reverseSensor(false);
		flywheel1.configEncoderCodesPerRev(4096);
		
//		flywheel0.setF(RobotMap.LauncherF);
//        flywheel0.setP(RobotMap.LauncherP);
//        flywheel0.setI(RobotMap.LauncherI); 
//        flywheel0.setD(RobotMap.LauncherD);
//		
//        flywheel1.setF(RobotMap.LauncherF);
//        flywheel1.setP(RobotMap.LauncherP);
//        flywheel1.setI(RobotMap.LauncherI); 
//        flywheel1.setD(RobotMap.LauncherD);
        
        flywheel0.setProfile(0);
        flywheel1.setProfile(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void setPID(double P, double I, double D, double F) {
    	flywheel0.setF(F);
        flywheel0.setP(P);
        flywheel0.setI(I); 
        flywheel0.setD(D);
		
        flywheel1.setF(F);
        flywheel1.setP(P);
        flywheel1.setI(I); 
        flywheel1.setD(D);
    }
    public void startLauncher(double speed) {
    	System.out.println("Laucher done. Speed is: " + speed);
    	flywheel0.set(speed);
    	flywheel1.set(-speed);
    	
    	//System.out.println("Setpoint set to: " + speed);
    }
}

