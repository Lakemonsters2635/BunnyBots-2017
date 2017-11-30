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

	CANTalon talon;
	
	double p;
	double i;
	double d;
	double f;
	double setpoint;
	double output0;
	double output1;
	
	double countsPer100ms;
	double fullscaleToPercentBus;
	
	double loadSpeedNU;
	double setpointNU;
	double error;
	double pCmd;
	double fCmd;
	double motorCmdUnlimited;
	double motorCmdPercentBus;
	double sumAll;
	
	public Launcher() {
		countsPer100ms = 6.8264;
		fullscaleToPercentBus = 9.775E-4;
		
	}

	public void setPIDF(double p, double i, double d, double f) {
		this.p = p;
		this.i = i;
		this.d = d;
		this.f = f;
	}
	
	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
	}
	
	public double calculateSpeed(double speed) {
		loadSpeedNU = speed * countsPer100ms;
		setpointNU = setpoint * countsPer100ms;
		error = setpointNU - loadSpeedNU;
		pCmd = error * p;
		fCmd = setpointNU * f;
		
		sumAll = fCmd + pCmd;
		
		motorCmdUnlimited = sumAll * fullscaleToPercentBus;
		
		if(motorCmdUnlimited > 1.0) {
			motorCmdPercentBus = 1;
		} else if(motorCmdUnlimited < -1.0) {
			motorCmdPercentBus = -1;
		} else {
			motorCmdPercentBus = motorCmdUnlimited;
		}
		
		return motorCmdPercentBus;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
		
		
		
		
		
		
		
		
		
		
		
		
//		flywheel0 = new CANTalon(RobotMap.LAUNCHER_0); //TODO: Set everything to real things
//		flywheel1 = new CANTalon(RobotMap.LAUNCHER_1);
//		
//		flywheel0.changeControlMode(TalonControlMode.Speed);
//		//flywheel0.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
//		
//		flywheel0.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
//		flywheel0.reverseSensor(false);
//		flywheel0.configEncoderCodesPerRev(4096);
//		
//		flywheel1.changeControlMode(TalonControlMode.Speed);
//		flywheel1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
//		flywheel1.reverseSensor(false);
//		flywheel1.configEncoderCodesPerRev(4096);
//		
////		flywheel0.setF(RobotMap.LauncherF);
////        flywheel0.setP(RobotMap.LauncherP);
////        flywheel0.setI(RobotMap.LauncherI); 
////        flywheel0.setD(RobotMap.LauncherD);
////		
////        flywheel1.setF(RobotMap.LauncherF);
////        flywheel1.setP(RobotMap.LauncherP);
////        flywheel1.setI(RobotMap.LauncherI); 
////        flywheel1.setD(RobotMap.LauncherD);
//        
//        flywheel0.setProfile(0);
//        flywheel1.setProfile(0);
//	}
//	
//    public void initDefaultCommand() {
//        // Set the default command for a subsystem here.
//        //setDefaultCommand(new MySpecialCommand());
//    }
//    public void setPID(double P, double I, double D, double F) {
//    	flywheel0.setF(F);
//        flywheel0.setP(P);
//        flywheel0.setI(I); 
//        flywheel0.setD(D);
//		
//        flywheel1.setF(F);
//        flywheel1.setP(P);
//        flywheel1.setI(I); 
//        flywheel1.setD(D);
//    }
//    public void startLauncher(double speed) {
//    	//System.out.println("Laucher done. Speed is: " + speed);
//    	flywheel0.set(speed);
//    	flywheel1.set(-speed);
//    	
//    	//System.out.println("Setpoint set to: " + speed);
    }


